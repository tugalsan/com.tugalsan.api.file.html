package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.callable.client.*;
import com.tugalsan.api.string.client.*;
import java.util.List;
import java.util.stream.IntStream;

public class TGS_FileHtmlTableRowCell extends TGS_FileHtmlElement {

    public static int counter = 0; 

    public void setRowspan_Properties0(CharSequence rowspan) {
        properties.get(0).value = rowspan.toString();
    }

    public String getRowspan_Properties0() {
        return properties.get(0).value;
    }

    public void setColspan_Properties1(CharSequence colspan) {
        properties.get(1).value = colspan.toString();
    }

    public String getColspan_Properties1() {
        return properties.get(1).value;
    }

    public void setStyle_Properties2(CharSequence style) {
        properties.get(2).value = style.toString();
    }

    public String getStyle_Properties2() {
        return properties.get(2).value;
    }

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }

    public TGS_FileHtmlTableRowCell(TGS_CallableType1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence rowspan, CharSequence colspan, CharSequence style) {
        super(escapeHTML, "td", nameAndId);
        counter++;
        properties.add(new TGS_FileHtmlProperty("rowspan", rowspan));
        properties.add(new TGS_FileHtmlProperty("colspan", colspan));
        properties.add(new TGS_FileHtmlProperty("style", style));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("<").append(tag);
        if (DEFAULT_isNameAndIdEnabled) {
            sb.append(" id='").append(nameAndId).append("'");
            sb.append(" name='").append(nameAndId).append("'");
        }
        var htmlTDFix = "overflow-wrap:normal;word-wrap:break-word;";
        IntStream.range(0, properties.size()).forEachOrdered(i -> {
            if ((i == 0 || i == 1) && "1".equals(properties.get(i).value)) {//HTML FIX
                //SKİP COLSPAN AND ROWSPAN FOR VALUE 1
                return;
            }
            if ("style".equals(properties.get(i).name)) {
                var p = TGS_StringUtils.concat(" ", properties.get(i).name, "='", htmlTDFix, properties.get(i).value, "'");
                sb.append(p);
            } else {
                var p = TGS_StringUtils.concat(" ", properties.get(i).name, "='", properties.get(i).value, "'");
                sb.append(p);
            }
        });
        sb.append(">\n");
        childeren.stream().forEachOrdered(s -> sb.append(s));
        sb.append("</").append(tag).append(">\n");
        return sb.toString();
    }
}
