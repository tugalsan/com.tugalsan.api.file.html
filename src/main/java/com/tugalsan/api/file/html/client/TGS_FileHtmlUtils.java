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
                sj.add("""
                       <style>
                       .container{
                           width: 100%;
                           margin-right: 20px;
                           position: relative;
                       }
                       .theme_switch {
                       	line-height: 1.8;
                           opacity: 25%;
                           z-index: 9999;
                           position: fixed;
                           top: 20px;
                           right: 30px;
                           color: var(--colorTextPrimary);
                       	
                         width: 7.5rem;
                         height: 4rem;
                       }
                       
                       .theme_switch input {
                         position: absolute;
                         top: 0;
                         left: 0;
                         z-index: 2;
                         opacity: 0;
                       }
                       
                       .theme_switch label {
                         cursor: pointer;
                       }
                       
                       .theme_background {
                         z-index: 1;
                         position: absolute;
                         width: 7.5rem;
                         height: 4rem;
                         border-radius: 2.5rem;
                         border: 0.25rem var(--widgetBorder);
                         background: linear-gradient(to right, var(--widgetBorder) 0%,#202020 100%);
                         transition: all 0.3s;
                       }
                       
                       .theme_switch input:checked ~ .fill {
                         background: #E9F8FD;
                       }
                       
                       .theme_stars1,
                       .theme_stars2 {
                         position: absolute;
                         height: 0.4rem;
                         width: 0.4rem; 
                         background: #FFFFFF;
                         border-radius: 50%;
                         transition: 0.3s all ease;
                       }
                       
                       .theme_stars1 {
                         top: 6px;
                         right: 23px;
                       }
                       
                       .theme_stars2 {
                         top: 40px;
                         right: 48px;
                       }
                       
                       .theme_stars1:after,
                       .theme_stars1:before,
                       .theme_stars2:after,
                       .theme_stars2:before{
                         position: absolute;
                         content: "";
                         display: block;
                         height: 0.25rem;
                         width: 0.25rem;
                         background: #FFFFFF;
                         border-radius: 50%;
                         transition: 0.2s all ease;
                       }
                       
                       .theme_stars1:after {
                         top: 8px;
                         right: 20px;
                       }
                       
                       .theme_stars1:before {
                         top: 18px;
                         right: -12px;
                       }
                       
                       .theme_stars2:after {
                         top: -8px;
                         right: -16px;
                       }
                       
                       .theme_stars2:before {
                         top: 6px;
                         right: -26px;
                       }
                       
                       .theme_sun-moon {
                         z-index: 2;
                         position: absolute;
                         left: 0;
                         display: inline-block;
                         height: 3rem;
                         width: 3rem;
                         margin: 0.5rem;
                         background: #FFFDF2;
                         border-radius: 50%;
                         transition: all 0.5s ease;
                         
                         /* Default to Moon */
                         border: 0.25rem solid #DEE2C6;
                       }
                       
                       .theme_sun-moon .theme_dots {
                         position: absolute;
                         top: 3px;
                         left: 23px;
                         height: 1rem;
                         width: 1rem; 
                         background: #EFEEDB;
                         border: 0.25rem solid #DEE2C6;
                         border-radius: 50%;
                         transition: 0.4s all ease;
                       }
                       
                       .theme_sun-moon .theme_dots:after,
                       .theme_sun-moon .theme_dots:before {
                         position: absolute;
                         content: "";
                         display: block;
                         height: 0.25rem;
                         width: 0.25rem;
                         background: #EFEEDB;
                         border: 0.25rem solid #DEE2C6;
                         border-radius: 50%;
                         transition: 0.4s all ease;
                       }
                       
                       .theme_sun-moon .theme_dots:after {
                         top: -4px;
                         left: -26px;
                       }
                       
                       .theme_sun-moon .theme_dots:before {
                         top: 18px;
                         left: -10px;
                       }
                       
                       /* Transition to Sun */
                       
                       .theme_switch input:checked ~ .theme_sun-moon {
                         left: calc(100% - 4rem);
                         background: #F5EC59;
                         border-color: #E7C65C;
                         transform: rotate(-25deg);
                       }
                       
                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots,
                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:after,
                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:before {
                         background: #FFFFFF;
                         border-color: #FFFFFF;
                       }
                       
                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots {
                         height: 1.5rem;
                         width: 1.5rem;
                         top: 0px;
                         left: -20px;
                         transform: rotate(25deg);
                       }
                       
                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:after {
                         height: 0.65rem;
                         width: 0.65rem;
                         top: 2px;
                         left: -12px;
                       }
                       
                       .theme_switch input:checked ~ .theme_sun-moon .theme_dots:before {
                         height: 0.4rem;
                         width: 0.4rem;
                         top: 6px;
                         left: 14px;
                       }
                       
                       .theme_switch input:checked ~ .theme_background .theme_stars1,
                       .theme_switch input:checked ~ .theme_background .theme_stars2 {
                         opacity: 0;
                         transform: translateY(2rem);
                       }
                       
                       .theme_switch input:checked ~ .theme_background {
                         border: 0.25rem solid #78C1D5;
                         background: linear-gradient(to right, #78C1D5 0%, #BBE7F5 100%);
                       }
                       </style>
                       <div class="container">
                       <div class="theme_switch">
                       <label for="theme_toggle">
                       <input id="theme_toggle" class="theme_toggle-switch" type="checkbox">
                       <div class="theme_sun-moon"><div class="theme_dots"></div></div>
                       <div class="theme_background"><div class="theme_stars1"></div><div class="theme_stars2"></div></div>
                       </label>
                       </div>
                       </div>
                       <script>
                       document.getElementById("theme_toggle").addEventListener("click", function(){
                           document.getElementsByTagName('html')[0].classList.toggle('Light-Cream');
                           document.getElementsByTagName('html')[0].classList.toggle('Dark-Black');
                       });
                       </script>
                       """);
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
