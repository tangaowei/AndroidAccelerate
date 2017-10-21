package demo.android.com.launchmode;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by tangaowei on 17/10/21.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final String TAG = "LaunchMode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "----onCreate----");
        Log.d(TAG, "onCreate " + getClass().getName() + ", taskId: " + getTaskId() + ", hashCode: " + hashCode());
        dumpTaskAffinity();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "----onNewIntent----");
        Log.d(TAG, "onNewIntent " + getClass().getName() + ", taskId: " + getTaskId() + ", hashCode: " + hashCode());
        dumpTaskAffinity();
    }

    protected void dumpTaskAffinity() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(),
                    PackageManager.GET_META_DATA);
            Log.d(TAG, "taskAffinity: " + info.taskAffinity);
        } catch(PackageManager.NameNotFoundException e) {
            Log.d(TAG, "e: " + e.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
    }
}