package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.function.client.*;
import com.tugalsan.api.file.html.client.*;

public class TGC_FileHtmlEscape implements TGS_Func_OutTyped_In1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TGC_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
