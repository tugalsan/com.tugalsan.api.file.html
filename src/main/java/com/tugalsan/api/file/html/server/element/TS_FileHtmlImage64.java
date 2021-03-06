package com.tugalsan.api.file.html.server.element;

import com.tugalsan.api.file.html.client.element.*;
import com.tugalsan.api.file.server.*;
import com.tugalsan.api.file.txt.server.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.url.client.*;
import com.tugalsan.api.url.server.*;
import java.nio.file.*;

public class TS_FileHtmlImage64 extends TGS_FileHtmlElement {

    public void setBase64_Properties0(CharSequence base64) {
        properties.get(0).value = base64 == null ? null : base64.toString();
    }

    public String getBase64_Properties0() {
        return properties.get(0).value;
    }

    public void setWidth_Properties1(CharSequence width) {
        properties.get(1).value = width == null ? null : width.toString();
    }

    public String getWidth_Properties1() {
        return properties.get(1).value;
    }

    public void setHeight_Properties2(CharSequence height) {
        properties.get(2).value = height == null ? null : height.toString();
    }

    public String getHeight_Properties2() {
        return properties.get(2).value;
    }

    public void setRotation_Properties3(CharSequence rotation) {
        properties.get(3).value = rotation == null ? null : rotation.toString();
    }

    public String getRotation_Properties3() {
        return properties.get(3).value;
    }

    public TS_FileHtmlImage64(CharSequence nameAndId) {
        super(null, "img", nameAndId);
    }

    public TS_FileHtmlImage64(CharSequence nameAndId, CharSequence fileLoc, CharSequence width, CharSequence height, CharSequence rotationInDegrees_0_90_180_270) {
        super(null, "img", nameAndId);
        var fileLocStr = fileLoc.toString();
        String base64;
        String imageFileType;
        if (fileLocStr.startsWith("http") || fileLocStr.startsWith("ftp")) {
            var url = TGS_Url.of(fileLocStr);
            base64 = TS_UrlDownloadUtils.toBase64(url);
            if (base64 == null) {
                base64 = "null";
            }
            imageFileType = TGS_UrlUtils.getFileNameType(url);
        } else {
            var path = Path.of(fileLocStr);
            base64 = TS_FileTxtUtils.toBase64(path);
            imageFileType = TS_FileUtils.getNameType(path);
        }
        properties.add(new TGS_FileHtmlProperty("data", "image/" + imageFileType + ";base64, " + base64));
        properties.add(new TGS_FileHtmlProperty("width", width));
        properties.add(new TGS_FileHtmlProperty("height", height));
        properties.add(new TGS_FileHtmlProperty("rotation", rotationInDegrees_0_90_180_270));
    }

    @Override
    public String toString() {
        var rotate = "";
        if (!"0".equals(getRotation_Properties3())) {
            rotate = "transform:rotate(" + getRotation_Properties3() + "deg);";
            if ("90".equals(getRotation_Properties3()) || "270".equals(getRotation_Properties3())) {
                rotate += "padding:15px;";
            }
        }
        var size_fix = "max-height:100%; max-width:100%;";
        var size_width = getWidth_Properties1() == null ? "" : ("width:" + getWidth_Properties1() + ";");
        var size_height = getHeight_Properties2() == null ? "" : ("height:" + getHeight_Properties2() + ";");
        var size = size_fix + size_width + size_height;
        return TGS_StringUtils.concat("<img ", (DEFAULT_isNameAndIdEnabled ? TGS_StringUtils.concat("id='", nameAndId, "' name='", nameAndId, "' ") : ""), "style='display:block;", rotate, size, "' src='data:", getBase64_Properties0(), "' />\n");
    }
}
