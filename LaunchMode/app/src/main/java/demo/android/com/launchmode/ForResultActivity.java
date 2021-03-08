package demo.android.com.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForResultActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forresultactivity_layout);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.back_btn:
                Intent intent = new Intent();
                intent.putExtra("result", "this is result");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
