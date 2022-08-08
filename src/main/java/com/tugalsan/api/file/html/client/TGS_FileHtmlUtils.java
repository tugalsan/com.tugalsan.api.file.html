package com.tugalsan.api.file.html.client;

import com.tugalsan.api.string.client.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringJoiner;

public class TGS_FileHtmlUtils {

    @Deprecated
    public static String wrapInIFrame(List<String> htmlLines, CharSequence iframeId) {
        var sb = new StringBuilder();
        sb.append("var s =\"\";\n");
        htmlLines.forEach(htmlLine -> {
            htmlLine = htmlLine.trim().replace("\"", "'");
            if (htmlLine.isEmpty()) {
                return;
            }
            sb.append("s+=\"");
            sb.append(htmlLine);
            sb.append("\";\n");
        });

        return TGS_StringUtils.concat(
                "<script>\n",
                sb,
                "var iframe = document.getElementById(\"", iframeId, "\");\n",
                "iframe.src = ", "\"data:text/html;charset=utf-8,\"", "+s", "\n",
                "</script>\n"
        );
    }

//    public static String beginLines(CharSequence browserTitle, boolean addDefaultCss, boolean addBorder, int leftMargin, int topMargin, CharSequence optional_hrefPngIcon, boolean addDivCenter) {
//        return beginLines(browserTitle, addDefaultCss, addBorder, leftMargin, topMargin, optional_hrefPngIcon, addDivCenter, null);
//    }

    public static String beginLines(CharSequence browserTitle, boolean addDefaultCss, boolean addBorder, int leftMargin, int topMargin, CharSequence optional_hrefPngIcon, boolean addDivCenter, CharSequence optionalCustomDomain) {
        var sj = new StringJoiner("\n");
        //DOCTYPE
        sj.add("<!doctype html>");
        //HTML START
        sj.add("<html lang=\"tr\" dir=\"ltr\">");
        //HTML->HEAD START
        sj.add("<head>");
        //HTML->HEAD->FAV ICON
        if (optional_hrefPngIcon != null) {
            sj.add("<link id=\"favicon\" rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"" + optional_hrefPngIcon + "\"/>");
        }
        //HTML->HEAD->CHARSET
        sj.add(TGS_StringUtils.concat("<meta charset='", StandardCharsets.UTF_8.name(), "'>"));
        //HTML->HEAD->TITLE
        sj.add(TGS_StringUtils.concat("<title>", browserTitle, "</title>"));
        //HTML->HEAD->BORDER STYLE
        if (addBorder) {
            sj.add("<style>");
            sj.add("table, th, td {border: 1px solid black !important}");
            sj.add("</style>");
        }
        //HTML->HEAD->MARGIN STYLE
        sj.add("<style>");
        sj.add("body {");
        sj.add("   margin-left: " + leftMargin + "px !important;");
        sj.add("   margin-right: " + leftMargin + "px !important;");
        sj.add("   margin-top: " + topMargin + "px !important;");
        sj.add("   margin-bottom: " + topMargin + "px !important;");
        sj.add("}");
        sj.add("</style>");
        //HTML->HEAD->CSS
        if (addDefaultCss) {
            sj.add("<script>");
            sj.add("window.boot_loader_main = function () {");
            sj.add("console.log(\"index.jsp: welcome\");");
            sj.add("};");
            sj.add("</script>");
            if (optionalCustomDomain == null) {
                sj.add("<script src=\"../res-common/res/js/boot_loader.js\"></script>");
            } else {
                sj.add("<script src=\"" + optionalCustomDomain + "/res-common/res/js/boot_loader.js\"></script>");
            }
        } else {
            sj.add("<style>");
            sj.add("html * {");
            sj.add("   font-size: 1em !important");
            sj.add("   font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif !important");
            sj.add("}");
            sj.add("</style>");
        }
        //HTML->HEAD END
        sj.add("</head>");
        //HTML->BODY START
        sj.add("<body>");
        //HTML->BODY->DIV START
        if (addDivCenter) {
            sj.add("<div class=\"AppModule_configLayout\">");
        }
        return sj.toString();
    }

    public static String endLines(boolean addDiv) {
        var sj = new StringJoiner("\n");
        if (addDiv) {
            sj.add("</div>");
        }
        sj.add("</body>");
        sj.add("</html>");
        return sj.toString();
    }
}
