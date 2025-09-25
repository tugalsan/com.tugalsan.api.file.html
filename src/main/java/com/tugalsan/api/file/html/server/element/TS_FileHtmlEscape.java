package com.tugalsan.api.file.html.server.element;

import module com.tugalsan.api.function;
import module com.tugalsan.api.file.html;

public class TS_FileHtmlEscape implements TGS_FuncMTU_OutTyped_In1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TS_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
