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
            if (addDefaultCss) {
                sj.add("<style>\n"//DO NOT USE TEXT BLOCK GWT DOES NOT LIKE IT
                        + "                       .container{\n"
                        + "                           width: 100%;\n"
                        + "                           margin-right: 20px;\n"
                        + "                           position: relative;\n"
                        + "                       }\n"
                        + "                       .theme_switch {\n"
                        + "                       	line-height: 1.8;\n"
                        + "                           opacity: 25%;\n"
                        + "                           z-index: 9999;\n"
                        + "                           position: fixed;\n"
                        + "                           top: 20px;\n"
                        + "                           right: 30px;\n"
                        + "                           color: var(--colorTextPrimary);\n"
                        + "                       	\n"
                        + "                         width: 7.5rem;\n"
                        + "                         height: 4rem;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input {\n"
                        + "                         position: absolute;\n"
                        + "                         top: 0;\n"
                        + "                         left: 0;\n"
                        + "                         z-index: 2;\n"
                        + "                         opacity: 0;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch label {\n"
                        + "                         cursor: pointer;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_background {\n"
                        + "                         z-index: 1;\n"
                        + "                         position: absolute;\n"
                        + "                         width: 7.5rem;\n"
                        + "                         height: 4rem;\n"
                        + "                         border-radius: 2.5rem;\n"
                        + "                         border: 0.25rem var(--widgetBorder);\n"
                        + "                         background: linear-gradient(to right, var(--widgetBorder) 0%,#202020 100%);\n"
                        + "                         transition: all 0.3s;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .fill {\n"
                        + "                         background: #E9F8FD;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars1,\n"
                        + "                       .theme_stars2 {\n"
                        + "                         position: absolute;\n"
                        + "                         height: 0.4rem;\n"
                        + "                         width: 0.4rem; \n"
                        + "                         background: #FFFFFF;\n"
                        + "                         border-radius: 50%;\n"
                        + "                         transition: 0.3s all ease;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars1 {\n"
                        + "                         top: 6px;\n"
                        + "                         right: 23px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars2 {\n"
                        + "                         top: 40px;\n"
                        + "                         right: 48px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars1:after,\n"
                        + "                       .theme_stars1:before,\n"
                        + "                       .theme_stars2:after,\n"
                        + "                       .theme_stars2:before{\n"
                        + "                         position: absolute;\n"
                        + "                         content: \"\";\n"
                        + "                         display: block;\n"
                        + "                         height: 0.25rem;\n"
                        + "                         width: 0.25rem;\n"
                        + "                         background: #FFFFFF;\n"
                        + "                         border-radius: 50%;\n"
                        + "                         transition: 0.2s all ease;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars1:after {\n"
                        + "                         top: 8px;\n"
                        + "                         right: 20px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars1:before {\n"
                        + "                         top: 18px;\n"
                        + "                         right: -12px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars2:after {\n"
                        + "                         top: -8px;\n"
                        + "                         right: -16px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_stars2:before {\n"
                        + "                         top: 6px;\n"
                        + "                         right: -26px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_sun-moon {\n"
                        + "                         z-index: 2;\n"
                        + "                         position: absolute;\n"
                        + "                         left: 0;\n"
                        + "                         display: inline-block;\n"
                        + "                         height: 3rem;\n"
                        + "                         width: 3rem;\n"
                        + "                         margin: 0.5rem;\n"
                        + "                         background: #FFFDF2;\n"
                        + "                         border-radius: 50%;\n"
                        + "                         transition: all 0.5s ease;\n"
                        + "                         \n"
                        + "                         /* Default to Moon */\n"
                        + "                         border: 0.25rem solid #DEE2C6;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_sun-moon .theme_dots {\n"
                        + "                         position: absolute;\n"
                        + "                         top: 3px;\n"
                        + "                         left: 23px;\n"
                        + "                         height: 1rem;\n"
                        + "                         width: 1rem; \n"
                        + "                         background: #EFEEDB;\n"
                        + "                         border: 0.25rem solid #DEE2C6;\n"
                        + "                         border-radius: 50%;\n"
                        + "                         transition: 0.4s all ease;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_sun-moon .theme_dots:after,\n"
                        + "                       .theme_sun-moon .theme_dots:before {\n"
                        + "                         position: absolute;\n"
                        + "                         content: \"\";\n"
                        + "                         display: block;\n"
                        + "                         height: 0.25rem;\n"
                        + "                         width: 0.25rem;\n"
                        + "                         background: #EFEEDB;\n"
                        + "                         border: 0.25rem solid #DEE2C6;\n"
                        + "                         border-radius: 50%;\n"
                        + "                         transition: 0.4s all ease;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_sun-moon .theme_dots:after {\n"
                        + "                         top: -4px;\n"
                        + "                         left: -26px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_sun-moon .theme_dots:before {\n"
                        + "                         top: 18px;\n"
                        + "                         left: -10px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       /* Transition to Sun */\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon {\n"
                        + "                         left: calc(100% - 4rem);\n"
                        + "                         background: #F5EC59;\n"
                        + "                         border-color: #E7C65C;\n"
                        + "                         transform: rotate(-25deg);\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots,\n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:after,\n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:before {\n"
                        + "                         background: #FFFFFF;\n"
                        + "                         border-color: #FFFFFF;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots {\n"
                        + "                         height: 1.5rem;\n"
                        + "                         width: 1.5rem;\n"
                        + "                         top: 0px;\n"
                        + "                         left: -20px;\n"
                        + "                         transform: rotate(25deg);\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:after {\n"
                        + "                         height: 0.65rem;\n"
                        + "                         width: 0.65rem;\n"
                        + "                         top: 2px;\n"
                        + "                         left: -12px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:before {\n"
                        + "                         height: 0.4rem;\n"
                        + "                         width: 0.4rem;\n"
                        + "                         top: 6px;\n"
                        + "                         left: 14px;\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_background .theme_stars1,\n"
                        + "                       .theme_switch input:checked ~ .theme_background .theme_stars2 {\n"
                        + "                         opacity: 0;\n"
                        + "                         transform: translateY(2rem);\n"
                        + "                       }\n"
                        + "                       \n"
                        + "                       .theme_switch input:checked ~ .theme_background {\n"
                        + "                         border: 0.25rem solid #78C1D5;\n"
                        + "                         background: linear-gradient(to right, #78C1D5 0%, #BBE7F5 100%);\n"
                        + "                       }\n"
                        + "                       </style>\n"
                        + "                       <div class=\"container\">\n"
                        + "                       <div class=\"theme_switch\">\n"
                        + "                       <label for=\"theme_toggle\">\n"
                        + "                       <input id=\"theme_toggle\" class=\"theme_toggle-switch\" type=\"checkbox\">\n"
                        + "                       <div class=\"theme_sun-moon\"><div class=\"theme_dots\"></div></div>\n"
                        + "                       <div class=\"theme_background\"><div class=\"theme_stars1\"></div><div class=\"theme_stars2\"></div></div>\n"
                        + "                       </label>\n"
                        + "                       </div>\n"
                        + "                       </div>\n"
                        + "                       <script>\n"
                        + "                       document.getElementById(\"theme_toggle\").addEventListener(\"click\", function(){\n"
                        + "                           document.getElementsByTagName('html')[0].classList.toggle('Light-Cream');\n"
                        + "                           document.getElementsByTagName('html')[0].classList.toggle('Dark-Black');\n"
                        + "                       });\n"
                        + "                       </script>");
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
