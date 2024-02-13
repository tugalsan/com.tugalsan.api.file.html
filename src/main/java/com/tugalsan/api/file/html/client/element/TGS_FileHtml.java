package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.callable.client.*;
import com.tugalsan.api.file.html.client.*;
import com.tugalsan.api.list.client.*;
import com.tugalsan.api.url.client.TGS_Url;
import java.util.*;

public class TGS_FileHtml {

    public String browserTitle;
    public String browserIconHrefPng;
    public CharSequence optionalCustomDomain;

    public List<TGS_FileHtmlElement> getChilderen() {
        return childeren;
    }
    private final List<TGS_FileHtmlElement> childeren;

    public TGS_FileHtml(CharSequence pageTitle, TGS_Url hrefPngIcon) {
        this(pageTitle, hrefPngIcon, null);
    }

    public TGS_FileHtml(CharSequence pageTitle, TGS_Url hrefPngIcon, CharSequence optionalCustomDomain) {
        this.browserTitle = pageTitle == null ? null : pageTitle.toString();
        this.browserIconHrefPng = hrefPngIcon == null ? null : hrefPngIcon.toString();
        this.optionalCustomDomain = optionalCustomDomain;
        childeren = TGS_ListUtils.of();
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("\n");
        sj.add(TGS_FileHtmlUtils.beginLines(browserTitle, useDefaultCss, addTableBorder, 5, 5, browserIconHrefPng, addDivCenter, optionalCustomDomain));
        childeren.stream().forEachOrdered(s -> sj.add(s.toString()));
        sj.add(TGS_FileHtmlUtils.endLines(true));
        return sj.toString();
    }
    public boolean useDefaultCss = true;
    public boolean addTableBorder = false;
    public boolean addDivCenter = true;

    public static TGS_FileHtml of(TGS_CallableType1<String, CharSequence> escapeHTML, TGS_ListTable lstTable, CharSequence title, CharSequence hrefPngIcon) {
        var htmlTable = TGS_FileHtmlTable.of(escapeHTML, lstTable);
        var html = new TGS_FileHtml(title, null);
        html.getChilderen().add(htmlTable);
        return html;
    }
}
