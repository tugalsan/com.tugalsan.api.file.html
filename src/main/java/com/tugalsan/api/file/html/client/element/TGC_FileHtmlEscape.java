package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.callable.client.*;
import com.tugalsan.api.file.html.client.*;

public class TGC_FileHtmlEscape implements TGS_CallableType1<String, CharSequence> {

    @Override
    public String call(CharSequence unsafeHtmlText) {
        return TGC_FileHtmlUtils.escape(unsafeHtmlText);
    }
}
