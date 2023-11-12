module com.tugalsan.api.file.html {
    requires gwt.user;
    requires jericho.html;
    requires org.apache.commons.text;
    requires com.tugalsan.api.unsafe;
    requires com.tugalsan.api.crypto;
    requires com.tugalsan.api.callable;
    requires com.tugalsan.api.os;
    requires com.tugalsan.api.runnable;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.charset;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.tuple;
    requires com.tugalsan.api.network;
    requires com.tugalsan.api.url;
    requires com.tugalsan.api.file;
    requires com.tugalsan.api.file.txt;
    exports com.tugalsan.api.file.html.client;
    exports com.tugalsan.api.file.html.client.element;
    exports com.tugalsan.api.file.html.server;
    exports com.tugalsan.api.file.html.server.element;
}
