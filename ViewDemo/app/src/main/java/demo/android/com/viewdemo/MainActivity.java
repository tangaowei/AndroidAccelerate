package demo.android.com.viewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.android.com.viewdemo.dialog.DialogActivity;
import demo.android.com.viewdemo.flowexample.FlowActivity;
import demo.android.com.viewdemo.touchexample.TouchExampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.touch_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TouchExampleActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.flowlayout_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FlowActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.dialog_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(i);
            }
        });
    }
}
