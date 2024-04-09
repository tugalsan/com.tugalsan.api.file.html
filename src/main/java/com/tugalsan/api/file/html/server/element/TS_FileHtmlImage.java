package com.tugalsan.api.file.html.server.element;

import com.tugalsan.api.file.html.client.element.TGS_FileHtmlProperty;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_Union;
import java.io.IOException;

public class TS_FileHtmlImage extends TS_FileHtmlImage64 {

    public TS_FileHtmlImage(CharSequence nameAndId, CharSequence src, CharSequence width, CharSequence height, CharSequence rotationInDegrees_0_90_180_270) {
        super(nameAndId);
        properties.add(new TGS_FileHtmlProperty("src", src.toString()));
        properties.add(new TGS_FileHtmlProperty("width", width.toString()));
        properties.add(new TGS_FileHtmlProperty("height", height.toString()));
        properties.add(new TGS_FileHtmlProperty("rotation", rotationInDegrees_0_90_180_270.toString()));
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
//        var size_fix = "max-height:100%; max-width:100%;";
        var size_fix = "max-width:100%;";
        var size_width = getWidth_Properties1() == null ? "" : ("width:" + getWidth_Properties1() + ";");
        var size_height = getHeight_Properties2() == null ? "" : ("height:" + getHeight_Properties2() + ";");
        var size = size_fix + size_width + size_height;
        return TGS_StringUtils.concat("<img ", (DEFAULT_isNameAndIdEnabled ? TGS_StringUtils.concat("id='", nameAndId, "' name='", nameAndId, "' ") : ""), " style='display:block;", rotate, size, "' src='", getBase64_Properties0(), "' />\n");
    }

    public TGS_Union<TS_FileHtmlImage64> cloneToImage64() {
        try {
            return TGS_Union.of(
                    new TS_FileHtmlImage64(
                            nameAndId,
                            super.getBase64_Properties0(),
                            getWidth_Properties1(),
                            getHeight_Properties2(),
                            getRotation_Properties3()
                    )
            );
        } catch (IOException ex) {
            return TGS_Union.ofExcuse(ex);
        }
    }
}
