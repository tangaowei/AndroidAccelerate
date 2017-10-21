package demo.android.com.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * adb shell dumpsys activity activities
         */
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.standard_button:
                Intent i1 = new Intent(this, StandardActivity.class);
                startActivity(i1);
                break;
        }
    }
}
