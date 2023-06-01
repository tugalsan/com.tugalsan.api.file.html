package com.tugalsan.api.file.html.server;

import com.tugalsan.api.file.server.TS_FileUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.os.server.TS_OsPlatformUtils;
import com.tugalsan.api.os.server.TS_OsProcess;
import com.tugalsan.api.url.client.TGS_Url;
import java.nio.file.Path;

public class TS_FileHtmlUtils {

    final private static TS_Log d = TS_Log.of(TS_FileHtmlUtils.class);

    public static boolean browse(TGS_Url url) {
        var edge = Path.of("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        if (!TS_FileUtils.isExistFile(edge)) {
            d.ce("browse", "File not exist", edge);
            return false;
        }
        if (!TS_OsPlatformUtils.isWindows()) {
            d.ce("browse", "os not supported");
            return false;
        }
        var cmd = edge.toAbsolutePath().toString() + " " + url.toString();
        d.ci("browse", "edge", cmd);
        return TS_OsProcess.of(cmd).exitValueOk();
    }
}
