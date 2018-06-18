package demo.android.com.viewdemo;

import android.app.Application;

/**
 * Created by tangaowei on 18/5/10.
 */

public class DemoApp extends Application {
    private static DemoApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static DemoApp getContext() {
        return sInstance;
    }
}
