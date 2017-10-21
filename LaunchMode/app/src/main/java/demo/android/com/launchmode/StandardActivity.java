package demo.android.com.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by tangaowei on 17/10/21.
 */

public class StandardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standard_layout);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.standard_button:
                Intent intent = new Intent(this, StandardActivity.class);
                startActivity(intent);
                break;
            case R.id.singletop_button:
                Intent i1 = new Intent(this, SingleTopActivity.class);
                startActivity(i1);
                break;
            case R.id.singletask_button:
                Intent i2 = new Intent(this, SingleTaskActivity.class);
                startActivity(i2);
                break;
            case R.id.singleinstance_button:
                Intent i3 = new Intent(this, SingleInstanceActivity.class);
                startActivity(i3);
                break;
        }
    }
}