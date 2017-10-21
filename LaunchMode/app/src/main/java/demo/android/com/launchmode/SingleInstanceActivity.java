package demo.android.com.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by tangaowei on 17/10/21.
 */

public class SingleInstanceActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.singleinstance_layout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.singleinstance_button:
                Intent i1 = new Intent(this, SingleInstanceActivity.class);
                startActivity(i1);
                break;
            case R.id.standard_button:
                Intent i2 = new Intent(this, StandardActivity.class);
                startActivity(i2);
                break;
        }
    }
}
