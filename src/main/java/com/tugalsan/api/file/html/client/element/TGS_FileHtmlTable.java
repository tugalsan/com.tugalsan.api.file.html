package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.compiler.client.*;
import com.tugalsan.api.list.client.*;
import java.util.List;
import java.util.stream.*;

public class TGS_FileHtmlTable extends TGS_FileHtmlElement {

    public static TGS_FileHtmlTable of(TGS_CompilerType1<String, CharSequence> escapeHTML, TGS_ListTable table) {
        var cs = table.getMaxColumnSize();
        var rs = table.getRowSize();
        var htmlTable = new TGS_FileHtmlTable("", "");
        IntStream.range(0, rs).forEachOrdered(ri -> {
            var row = new TGS_FileHtmlTableRow("");
            htmlTable.getChilderen().add(row);
            IntStream.range(0, cs).forEachOrdered(ci -> {
                var cell = new TGS_FileHtmlTableRowCell(escapeHTML, "", "", "", "");
                row.getChilderen().add(cell);

                var spanValue = table.getValueAsString(ri, ci);
                var span = new TGS_FileHtmlSpan(escapeHTML, "", spanValue, "");
                cell.getChilderen().add(span);
            });
        });
        return htmlTable;
    }

    public TGS_FileHtmlTable setHeaderRowCount(int headerRowCount) {
        IntStream.range(0, getChilderen().size()).forEachOrdered(i -> {
            var el = getChilderen().get(i);
            if (el instanceof TGS_FileHtmlTableRow) {
                var tableRow = (TGS_FileHtmlTableRow) el;
                tableRow.setIsHeader(i < headerRowCount);
            }
        });
        return this;
    }

    public void setStyle_Properties0(CharSequence style) {
        properties.get(0).value = style.toString();
    }

    public String getStyle_Properties0() {
        return properties.get(0).value;
    }
    public static int counter = 0;

    public TGS_FileHtmlTable(CharSequence nameAndId, CharSequence style) {//width:100%
        super(null, "table", nameAndId);
        setSyleClassName("styled-table");
        counter++;
        properties.add(new TGS_FileHtmlProperty("style", style));
    }

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }
}
