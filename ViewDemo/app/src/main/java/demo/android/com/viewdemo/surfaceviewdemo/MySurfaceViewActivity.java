package demo.android.com.viewdemo.surfaceviewdemo;

import android.app.Activity;
import android.os.Bundle;

public class MySurfaceViewActivity extends Activity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySurfaceView view = new MySurfaceView(this);

        setContentView(view);
    }
}