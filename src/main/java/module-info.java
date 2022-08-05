module com.tugalsan.api.file.html {
    requires jericho.html;
    requires org.apache.commons.text;
    requires com.tugalsan.api.unsafe;
    requires com.tugalsan.api.compiler;
    requires com.tugalsan.api.executable;
    requires com.tugalsan.api.list;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.string;
    requires com.tugalsan.api.pack;
    requires com.tugalsan.api.network;
    requires com.tugalsan.api.url;
    requires com.tugalsan.api.file;
    requires com.tugalsan.api.file.txt;
    exports com.tugalsan.api.file.html.client;
    exports com.tugalsan.api.file.html.client.element;
    exports com.tugalsan.api.file.html.server;
    exports com.tugalsan.api.file.html.server.element;
}
