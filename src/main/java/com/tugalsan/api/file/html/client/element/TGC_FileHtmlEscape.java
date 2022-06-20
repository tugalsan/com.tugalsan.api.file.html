package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.compiler.client.*;
import com.tugalsan.api.file.html.client.*;

public class TGC_FileHtmlEscape implements TGS_CompilerType1<String, CharSequence> {

    @Override
    public String compile(CharSequence unsafeHtmlText) {
        return TGC_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
