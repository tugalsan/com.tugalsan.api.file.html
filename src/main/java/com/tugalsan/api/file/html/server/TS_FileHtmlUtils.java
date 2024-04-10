package com.tugalsan.api.file.html.server;

import com.tugalsan.api.file.html.client.element.*;
import com.tugalsan.api.file.server.TS_FileUtils;
import com.tugalsan.api.file.txt.server.*;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.os.server.TS_OsPlatformUtils;
import com.tugalsan.api.os.server.TS_OsProcess;
import com.tugalsan.api.tuple.client.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_Union;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import com.tugalsan.api.url.client.TGS_Url;
import java.nio.file.*;
import net.htmlparser.jericho.*;
import org.apache.commons.text.*;

public class TS_FileHtmlUtils {

    final private static TS_Log d = TS_Log.of(TS_FileHtmlUtils.class);

//TODO 
//    public static boolean String embedPlainFiles(CharSequence htmlContent){
//        var htmlContentInit = htmlContent.toString();
//        
//        return 
//    }
    
    public static boolean browse(TGS_Url url) {
        var edge = Path.of("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        if (!TS_FileUtils.isExistFile(edge)) {
            d.ce("browse", "File not exist", edge);
            return false;
        }
        if (!TS_OsPlatformUtils.isWindows()) {
            d.ce("browse", "os not supported");
            return false;
        }
        var cmd = edge.toAbsolutePath().toString() + " " + url.toString();
        d.ci("browse", "edge", cmd);
        return TS_OsProcess.of(cmd).exitValueOk();
    }

    public static TGS_UnionExcuse write2File(TGS_FileHtml src, Path filePath) {
        var content = src.toString();
        return TS_FileTxtUtils.toFile(content, filePath, false);
    }

    public static String escape(CharSequence html) {//converts chars to html &tags
        return StringEscapeUtils.escapeHtml4(html.toString());
    }

    public static String toText(CharSequence html) {//converts html &tags to chars
        return new Source(html).getTextExtractor().toString();
    }

    public static int getIdxBodyStartBefore(CharSequence html) {
        var titleTagStart = "<body";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxBodyStartAfter(CharSequence html) {
        return html.toString().indexOf(">", TS_FileHtmlUtils.getIdxBodyStartBefore(html) + 1) + 1;
    }

    public static int getIdxBodyEndBefore(CharSequence html) {
        var titleTagStart = "</body>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxBodyEndAfter(CharSequence html) {
        var titleTagStart = "</body>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxTitleStartBefore(CharSequence html) {
        var titleTagStart = "<title>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxTitleEndBefore(CharSequence html) {
        var titleTagStart = "</title>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxTitleStartAfter(CharSequence html) {
        var titleTagStart = "<title>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxTitleEndAfter(CharSequence html) {
        var titleTagStart = "</title>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxHeadStartBefore(CharSequence html) {
        var titleTagStart = "<head>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxHeadEndBefore(CharSequence html) {
        var titleTagStart = "</head>";
        return html.toString().indexOf(titleTagStart);
    }

    public static int getIdxHeadStartAfter(CharSequence html) {
        var titleTagStart = "<head>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static int getIdxHeadEndAfter(CharSequence html) {
        var titleTagStart = "</head>";
        return html.toString().indexOf(titleTagStart) + titleTagStart.length();
    }

    public static String updateTitleContent(CharSequence html, CharSequence content) {
        return TGS_StringUtils.concat(
                html.subSequence(0, getIdxTitleStartAfter(html)),
                content,
                html.subSequence(getIdxTitleEndBefore(html), html.length())
        );
    }

    public static String updateBodyTag(CharSequence html, CharSequence newBodyTag) {
        return TGS_StringUtils.concat(
                html.subSequence(0, getIdxBodyStartBefore(html)),
                newBodyTag,
                html.subSequence(getIdxBodyStartAfter(html), html.length())
        );
    }

    public static TGS_Tuple2<String, String> ribHeadContentAfterTitle_returnHtmlAndHeadContent(CharSequence html) {
        var idxTitleEndAfter = TS_FileHtmlUtils.getIdxTitleEndAfter(html);
        var idxHeadEndBefore = TS_FileHtmlUtils.getIdxHeadEndBefore(html);
        var ribContent = html.subSequence(idxTitleEndAfter, idxHeadEndBefore).toString();
        var newHtm = TGS_StringUtils.concat(html.subSequence(0, idxTitleEndAfter), html.subSequence(idxHeadEndBefore, html.length()));
        return new TGS_Tuple2(newHtm, ribContent);
    }

    public static String appendToHead(CharSequence html, CharSequence content) {
        var headEnd = getIdxHeadEndBefore(html);
        return TGS_StringUtils.concat(html.subSequence(0, headEnd), content, html.subSequence(headEnd, html.length()));
    }

    public static String appendToBodyStartAfter(CharSequence html, CharSequence content) {
        var bodyStartAfter = getIdxBodyStartAfter(html);
        return TGS_StringUtils.concat(html.subSequence(0, bodyStartAfter), content, html.subSequence(bodyStartAfter, html.length()));
    }

    public static String appendToBodyEndBefore(CharSequence html, CharSequence content) {
        var bodyEndBefore = getIdxBodyEndBefore(html);
        return TGS_StringUtils.concat(html.subSequence(0, bodyEndBefore), content, html.subSequence(bodyEndBefore, html.length()));
    }

    public static String addLoader(CharSequence htm) {
        var r = TS_FileHtmlUtils.ribHeadContentAfterTitle_returnHtmlAndHeadContent(htm);
        htm = r.value0;
        var heavyContent = r.value1;
        htm = TS_FileHtmlUtils.appendToHead(htm,
                """
        <style>
        /* Center the loader */
        #loader {
          position: absolute;
          left: 50%;
          top: 50%;
          z-index: 1;
          width: 120px;
          height: 120px;
          margin: -76px 0 0 -76px;
          border: 16px solid #f3f3f3;
          border-radius: 50%;
          border-top: 16px solid #3498db;
          -webkit-animation: spin 2s linear infinite;
          animation: spin 2s linear infinite;
        }
        
        @-webkit-keyframes spin {
          0% { -webkit-transform: rotate(0deg); }
          100% { -webkit-transform: rotate(360deg); }
        }
        
        @keyframes spin {
          0% { transform: rotate(0deg); }
          100% { transform: rotate(360deg); }
        }
        
        /* Add animation to "page content" */
        .animate-bottom {
          position: relative;
          -webkit-animation-name: animatebottom;
          -webkit-animation-duration: 1s;
          animation-name: animatebottom;
          animation-duration: 1s
        }
        
        @-webkit-keyframes animatebottom {
          from { bottom:-100px; opacity:0 } 
          to { bottom:0px; opacity:1 }
        }
        
        @keyframes animatebottom { 
          from{ bottom:-100px; opacity:0 } 
          to{ bottom:0; opacity:1 }
        }
        
        #myDiv {
          display: none;
          text-align: center;
        }
        </style>
        <script>
        var myVar;
        
        function filterTable() {
          myVar = setTimeout(showPage, 3000);
        }
        
        function showPage() {
          document.getElementById("loader").style.display = "none";
          document.getElementById("myDiv").style.display = "block";
        }
        </script>"""
        );
        htm = TS_FileHtmlUtils.updateBodyTag(htm, "<body  onload=\"filterTable()\" >");
        htm = TS_FileHtmlUtils.appendToBodyStartAfter(htm, "<div id=\"loader\"></div><div style=\"display:none;\" id=\"myDiv\" class=\"animate-bottom\">");
        htm = TS_FileHtmlUtils.appendToBodyEndBefore(htm, "</div>");
        return TS_FileHtmlUtils.appendToBodyEndBefore(htm, heavyContent);
    }

    public static String IFRAME_VIDEO_VADI_LOCA() {
        return "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/U-BzhUxnEu8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
    }

    public static String HEADER_CONTENT_VADI_LOCA() {
        return """
                                                   <div class='container2'>
                                                        		<div class="left">
                                                        			<img src='index.png' class='iconDetails'>
                                                        		</div>
                                                        	<div class="right" >
                                                        		<h4>VADİ LOCA SİTESİ</h4>
                                                        		<div style="padding-left: 6px;font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;font-size:1em;float:left;color: green;">YAŞAM VADİSİNİN YANI BAŞINDA,</div>
                                                        		<div style="padding-left: 6px;font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;font-size:1em;float:right;color: green;">AKTİF HAYATIN TAM ORTASINDA...</div>
                                                        	</div>
                                                        </div>
                                                        	<style>
                                                        	 .iconDetails {
                                                         margin-left:2%;
                                                        float:left;
                                                        width:65px;
                                                        }
                                                        
                                                        .container2 {
                                                        	height:auto;
                                                        	padding:1%;
                                                            float:left;
                                                        }
                                                        h4{
                                                        margin:0;
                                                        font-family: fontText, Arial Unicode MS, Arial,Helvetica,sans-serif;
                                                        font-size:1.5em;
                                                        padding-top: 10px;
                                                        }
                                                        .left {float:left;width:70px;}
                                                        .right {float:left;margin:0 0 0 5px;}
                                                        	</style>""";
    }

    public static String appendResponsiveVideo(CharSequence htm, CharSequence iframe_video) {
        return appendToBodyStartAfter(htm, TGS_StringUtils.concat(
                "<div class=\"video-container\">",
                iframe_video,
                """
                </div>
                		<style>
                		.video-container {
                    overflow: hidden;
                    position: relative;
                    width:100%;
                }
                
                .video-container::after {
                    padding-top: 56.25%;
                    display: block;
                    content: '';
                }
                
                .video-container iframe {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    max-width: 960px;
                    padding-left: 8px;
                    height: 100%;
                }
                		</style>"""
        ));
    }
}
