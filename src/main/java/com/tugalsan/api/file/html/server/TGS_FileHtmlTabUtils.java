package com.tugalsan.api.file.html.server;

import com.tugalsan.api.tuple.client.*;
import java.util.*;

public class TGS_FileHtmlTabUtils {

    final public static String css() {
        return """
               /* Style the tab */
               .tab {
                 overflow: hidden;
                 border: 1px solid #ccc;
                 background-color: #f1f1f1;
               }
               
               /* Style the buttons that are used to open the tab content */
               .tab button {
                 background-color: inherit;
                 float: left;
                 border: none;
                 outline: none;
                 cursor: pointer;
                 padding: 14px 16px;
                 transition: 0.3s;
               }
               
               /* Change background color of buttons on hover */
               .tab button:hover {
                 background-color: #ddd;
               }
               
               /* Create an active/current tablink class */
               .tab button.active {
                 background-color: #ccc;
               }
               
               /* Style the tab content */
               .tabcontent {
                 display: none;
                 padding: 6px 12px;
                 border: 1px solid #ccc;
                 border-top: none;
               }""";
    }

    final public static String js() {
        return """
               function openTab(evt, cityName) {
                 // Declare all variables
                 var i, tabcontent, tablinks;
               
                 // Get all elements with class="tabcontent" and hide them
                 tabcontent = document.getElementsByClassName("tabcontent");
                 for (i = 0; i < tabcontent.length; i++) {
                   tabcontent[i].style.display = "none";
                 }
               
                 // Get all elements with class="tablinks" and remove the class "active"
                 tablinks = document.getElementsByClassName("tablinks");
                 for (i = 0; i < tablinks.length; i++) {
                   tablinks[i].className = tablinks[i].className.replace(" active", "");
                 }
               
                 // Show the current tab, and add an "active" class to the button that opened the tab
                 document.getElementById(cityName).style.display = "block";
                 evt.currentTarget.className += " active";
               }""";
    }

    final public static String buttons(TGS_Tuple2<String, String>... btnNamesAndIds) {
        var sb = new StringBuilder();
        sb.append("<!-- Tab links -->\n");
        sb.append("<div class=\"tab\">\n");
        Arrays.stream(btnNamesAndIds).forEachOrdered(btn -> {
            sb.append("  <button class=\"tablinks\" onclick=\"openTab(event, '").append(btn.value1).append("')\">");
            sb.append(btn.value0);
            sb.append("</button>\n");
        });
        sb.append("</div>\n");
        return sb.toString();
    }

    final public static String contentStart(CharSequence id) {
        return "<div id=\"" + id + "\" class=\"tabcontent\">";
    }

    final public static String contentEnd() {
        return "</div>";
    }
}
