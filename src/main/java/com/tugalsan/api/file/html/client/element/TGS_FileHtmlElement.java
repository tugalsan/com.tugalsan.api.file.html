package com.tugalsan.api.file.html.client.element;

import com.tugalsan.api.compiler.client.*;
import com.tugalsan.api.list.client.*;
import java.util.*;

public class TGS_FileHtmlElement {

    public boolean DEFAULT_isNameAndIdEnabled = false;

    protected String nameAndId;
    protected String tag;
    protected List<TGS_FileHtmlElement> childeren;
    protected List<TGS_FileHtmlProperty> properties;
    protected String spanText;
    protected TGS_CompilerType1<String, CharSequence> escapeHTML;

    public TGS_FileHtmlElement(TGS_CompilerType1<String, CharSequence> escapeHTML, CharSequence tag, CharSequence nameAndId) {
        this.escapeHTML = escapeHTML;
        this.nameAndId = nameAndId == null ? null : nameAndId.toString();
        this.tag = tag == null ? null : tag.toString();
        childeren = TGS_ListUtils.of();
        properties = TGS_ListUtils.of();
    }

    @Override
    public String toString() {
        return toString(DEFAULT_isNameAndIdEnabled, true, true);
    }

    public String getStyleClassName() {
        return syleClassName;
    }

    public void setSyleClassName(String syleClassName) {
        this.syleClassName = syleClassName;
    }
    private String syleClassName;

    public String toString(boolean addNameAndId, boolean addProperties, boolean addChilderenAndCloseTag) {
        var tableHeader = false;
        if (this instanceof TGS_FileHtmlTableRow) {
            var tableRow = (TGS_FileHtmlTableRow) this;
            tableHeader = tableRow.isIsHeader();
        }

        var sb = new StringBuilder();
        if (tableHeader) {
            sb.append("<thead>");
        }
        {
            sb.append("<").append(tag);
            if (addNameAndId) {
                sb.append(" id='").append(nameAndId).append("'");
                sb.append(" name='").append(nameAndId).append("'");
            }
            if (getStyleClassName() != null) {
                sb.append(" class='").append(getStyleClassName()).append("'");
            }
            for (var i = 0; addProperties && i < properties.size(); i++) {
                sb.append(" ").append(properties.get(i).name).append("='").append(properties.get(i).value).append("'");
            }
            sb.append(addChilderenAndCloseTag ? "" : "/").append(">\n");
        }
        if (tag.equals("span")) {
            if (this instanceof TGS_FileHtmlSpan) {
                var span = (TGS_FileHtmlSpan) this;
                if (span.pureCode) {//html span
                    sb.append((spanText));
                } else {//normal span
                    sb.append(escapeHTML == null ? spanText : escapeHTML.compile(spanText));
                }
            } else {//custom span
                sb.append(escapeHTML == null ? spanText : escapeHTML.compile(spanText));
            }
            sb.append("</").append(tag).append(">\n");
        } else if (addChilderenAndCloseTag) {
            childeren.stream().forEachOrdered(s -> sb.append("  ").append(s));
            sb.append("</").append(tag).append(">\n");
        }
        if (tableHeader) {
            sb.append("</thead>");
        }
        return sb.toString();
    }
}
