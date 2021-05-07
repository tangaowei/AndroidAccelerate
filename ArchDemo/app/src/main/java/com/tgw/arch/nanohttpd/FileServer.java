package com.tgw.arch.nanohttpd;

import android.content.Context;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class FileServer extends NanoHTTPD {

    private final static int PORT = 33445;
    private Context _mainContext;

    /*
    主构造函数，也用来启动http服务
    */
    public FileServer(Context context) throws IOException {
        super(PORT);
        _mainContext = context;
        start();
        System.out.println("\nRunning! Point your browsers to [http://0.0.0.0:33445/](http://localhost:33445/)\n");
    }

    /*
    解析的主入口函数，所有请求从这里进，也从这里出
    */
    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        msg += "<p>We serve " + session.getUri() + " !</p>";
        return newFixedLengthResponse( msg + "</body></html>\n" );
    }
}
