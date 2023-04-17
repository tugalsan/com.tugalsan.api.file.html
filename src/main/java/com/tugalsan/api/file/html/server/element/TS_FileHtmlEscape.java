package com.tugalsan.api.file.html.server.element;

import com.tugalsan.api.callable.client.*;

public class TS_FileHtmlEscape implements TGS_CallableType1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TS_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
