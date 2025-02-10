package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.function.client.maythrow.uncheckedexceptions.TGS_FuncMTUCE_OutTyped_In1;
import com.tugalsan.api.string.client.*;

public class TGS_FileHtmlTableRowCellOccupied extends TGS_FileHtmlTableRowCell {

    public TGS_FileHtmlTableRowCellOccupied(boolean header, TGS_FuncMTUCE_OutTyped_In1<String, CharSequence> escapeHTML) {
        super(header, escapeHTML, "null", "1", "1", "");
    }

    @Override
    public String toString() {
        return TGS_StringUtils.cmn().concat(" <! -- ", TGS_FileHtmlTableRowCellOccupied.class.getSimpleName(), " -->\n");
    }
}
