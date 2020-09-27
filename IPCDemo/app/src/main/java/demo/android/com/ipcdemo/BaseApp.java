package demo.android.com.ipcdemo;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {
    private static BaseApp instance;
    public static BaseApp getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
