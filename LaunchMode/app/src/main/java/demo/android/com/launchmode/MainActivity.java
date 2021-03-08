package demo.android.com.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    public static final int RESULT_CODE = 1;
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.standard_button:
                Intent i1 = new Intent(this, StandardActivity.class);
                startActivity(i1);
                break;
            case R.id.onActivityResult:
                startActivityForResult(new Intent(MainActivity.this, ForResultActivity.class), RESULT_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");
        Log.d(TAG, "onActivityResult result: " + result);
    }
}
