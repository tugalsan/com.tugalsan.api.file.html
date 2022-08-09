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
//        if (addDefaultCss) {
//            sj.add("<html lang=\"tr\" dir=\"ltr\" class=\"Dark-Black\">");
//        } else {
        sj.add("<html lang=\"tr\" dir=\"ltr\">");
//        }
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
            if (addDefaultCss) {
                sj.add("<div class=\"theme_container\">\n");
                sj.add("<div class=\"theme_switch\">\n");
                sj.add("<label for=\"theme_toggle\">\n");
                sj.add("<input id=\"theme_toggle\" class=\"theme_toggle-switch\" type=\"checkbox\">\n");
                sj.add("<div class=\"theme_sun-moon\"><div class=\"theme_dots\"></div></div>\n");
                sj.add("<div class=\"theme_background\"><div class=\"theme_stars1\"></div><div class=\"theme_stars2\"></div></div>\n");
                sj.add("</label>\n");
                sj.add("</div>\n");
                sj.add("</div>\n");
                sj.add("<script>\n");
                sj.add("var theme_el = document.getElementsByTagName('html')[0];\n");
                sj.add("if (theme_el.classList.contains('Dark-Black')){\n");
                sj.add("	document.getElementById(\"theme_toggle\").checked=false;\n");
                sj.add("}\n");
                sj.add("if (theme_el.classList.contains('Light-Cream')){\n");
                sj.add("	document.getElementById(\"theme_toggle\").checked=true;\n");
                sj.add("}\n");
                sj.add("document.getElementById(\"theme_toggle\").addEventListener(\"click\", function(){\n");
                sj.add("	if (theme_el.classList.contains('Dark-Black')){\n");
                sj.add("		theme_el.classList.remove('Dark-Black');\n");
                sj.add("		theme_el.classList.add('Light-Cream');\n");
                sj.add("	} else {\n");
                sj.add("		theme_el.classList.add('Dark-Black');\n");
                sj.add("		theme_el.classList.remove('Light-Cream');\n");
                sj.add("	}\n");
                sj.add("});\n");
                sj.add("</script>\n");
            }
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
