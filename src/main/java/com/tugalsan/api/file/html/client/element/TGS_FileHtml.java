package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.callable.client.*;
import com.tugalsan.api.file.html.client.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.url.client.TGS_Url;
import java.util.*;

public class TGS_FileHtml {

    public CharSequence browserTitle;
    public TGS_Url browserIconHrefPng;
    public TGS_Url bootLoaderJs;

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }
    private final List<TGS_FileHtmlElement> childeren;

    private TGS_FileHtml(CharSequence pageTitle, TGS_Url hrefPngIcon, TGS_Url bootLoaderJs) {
        this.browserTitle = pageTitle;
        this.browserIconHrefPng = hrefPngIcon;
        this.bootLoaderJs = bootLoaderJs;
        childeren = TGS_ListUtils.of();
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("\n");
        sj.add(TGS_FileHtmlUtils.beginLines(browserTitle, addTableBorder, 5, 5, browserIconHrefPng, addDivCenter, bootLoaderJs));
        childeren.stream().forEachOrdered(s -> sj.add(s.toString()));
        sj.add(TGS_FileHtmlUtils.endLines(true));
        return sj.toString();
    }
    public boolean addTableBorder = false;
    public boolean addDivCenter = true;

    public static TGS_FileHtml of(TGS_CallableType1<String, CharSequence> optional_escapeHTML, TGS_ListTable optional_lstTable, CharSequence title, TGS_Url optional_hrefPngIcon, TGS_Url optional_bootLoaderJs) {
        var html = new TGS_FileHtml(title, optional_hrefPngIcon, optional_bootLoaderJs);
        if (optional_lstTable != null) {
            var htmlTable = TGS_FileHtmlTable.of(optional_escapeHTML, optional_lstTable);
            html.getChilderen().add(htmlTable);
        }
        return html;
    }
}
