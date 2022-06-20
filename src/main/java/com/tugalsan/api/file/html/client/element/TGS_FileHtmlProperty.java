package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.string.client.*;

public class TGS_FileHtmlProperty {

    public String name;
    public String value;

    public TGS_FileHtmlProperty(CharSequence name, CharSequence value) {
        this.name = name.toString();
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return TGS_StringUtils.concat(name, "='", value, "'");
    }
}
