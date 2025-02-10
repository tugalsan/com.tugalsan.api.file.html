package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.function.client.maythrow.uncheckedexceptions.TGS_FuncMTUCE_OutTyped_In1;

public class TGS_FileHtmlSpan extends TGS_FileHtmlElement {

    public static int counter = 0;
    public boolean pureCode = false;

    public void setStyle_Properties0(CharSequence style) {
        properties.get(0).value = style.toString();
    }

    public String getStyle_Properties0() {
        return properties.get(0).value;
    }

    public TGS_FileHtmlSpan(TGS_FuncMTUCE_OutTyped_In1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence spanText, CharSequence style) {
        super(escapeHTML, "span", nameAndId);
        counter++;
        super.spanText = spanText.toString();
        properties.add(new TGS_FileHtmlProperty("style", style));
    }
}
