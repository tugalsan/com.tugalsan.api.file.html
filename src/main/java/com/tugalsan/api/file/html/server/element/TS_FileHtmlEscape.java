package com.tugalsan.api.file.html.server.element;

import com.tugalsan.api.compiler.client.*;

public class TS_FileHtmlEscape implements TGS_CompilerType1<String, CharSequence> {

    @Override
    public String compile(CharSequence unsafeHtmlText) {
        return TS_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
