package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.callable.client.*;

public class TGS_FileHtmlLink extends TGS_FileHtmlElement {

    public void setHref_Properties0(CharSequence href) {
        properties.get(0).value = href.toString();
    }

    public String getHref_Properties0() {
        return properties.get(0).value;
    }

    public TGS_FileHtmlLink(TGS_CallableType1<String, CharSequence> escapeHTML, CharSequence nameAndId, CharSequence href) {
        super(escapeHTML, "a", nameAndId);
        properties.add(new TGS_FileHtmlProperty("href", href));
    }
}
