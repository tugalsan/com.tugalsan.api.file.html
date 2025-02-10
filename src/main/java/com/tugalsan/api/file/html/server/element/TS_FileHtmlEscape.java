package com.tugalsan.api.file.html.server.element;

import com.tugalsan.api.function.client.maythrow.uncheckedexceptions.TGS_FuncMTUCE_OutTyped_In1;
import com.tugalsan.api.file.html.server.TS_FileHtmlUtils;

public class TS_FileHtmlEscape implements TGS_FuncMTUCE_OutTyped_In1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TS_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
