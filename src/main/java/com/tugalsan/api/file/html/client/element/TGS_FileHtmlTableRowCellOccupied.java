package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.compiler.client.*;
import com.tugalsan.api.string.client.*;

public class TGS_FileHtmlTableRowCellOccupied extends TGS_FileHtmlTableRowCell {

    public TGS_FileHtmlTableRowCellOccupied(TGS_CompilerType1<String, CharSequence> escapeHTML) {
        super(escapeHTML, "null", "1", "1", "");
    }

    @Override
    public String toString() {
        return TGS_StringUtils.concat(" <! -- ", TGS_FileHtmlTableRowCellOccupied.class.getSimpleName(), " -->\n");
    }
}
