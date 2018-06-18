package demo.android.com.viewdemo.touchexample;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by tangaowei on 18/5/8.
 */

public class TouchExampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TouchExampleView view = new TouchExampleView(this);

        setContentView(view);
    }
}
