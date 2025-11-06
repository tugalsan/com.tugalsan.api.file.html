package com.tugalsan.api.file.html.client.element;

import java.util.List;

public class TGS_FileHtmlTableRow extends TGS_FileHtmlElement {

    public static int counter = 0;

    public TGS_FileHtmlTableRow(CharSequence nameAndId) {
        super(null, "tr", nameAndId);
        counter++;
    }

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }

    private boolean isHeader = false;

    public boolean IsHeader() {
        return isHeader;
    }

    public void setHeader(boolean isHeader) {
        this.isHeader = isHeader;
        getChilderen().forEach(e -> {
            if (e instanceof TGS_FileHtmlTableRowCell) {
                var c = (TGS_FileHtmlTableRowCell) e;
                c.setHeader(isHeader);
            }
        });
    }
}
