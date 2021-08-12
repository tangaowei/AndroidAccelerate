package demo.android.com.viewdemo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.android.com.viewdemo.databinding.ActivityMainBinding;
import demo.android.com.viewdemo.dialog.DialogActivity;
import demo.android.com.viewdemo.flowexample.FlowActivity;
import demo.android.com.viewdemo.opengles.FirstOpenGLProjectActivity;
import demo.android.com.viewdemo.recyclerview.RecyclerViewActivity;
import demo.android.com.viewdemo.surfaceviewdemo.MySurfaceViewActivity;
import demo.android.com.viewdemo.touchexample.TouchExampleActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.touchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TouchExampleActivity.class);
                startActivity(i);
            }
        });

        binding.flowlayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FlowActivity.class);
                startActivity(i);
            }
        });

        binding.dialogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(i);
            }
        });

        binding.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(i);
            }
        });

        binding.surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, MySurfaceViewActivity.class);
                Intent i = new Intent(MainActivity.this, FirstOpenGLProjectActivity.class);
                startActivity(i);
            }
        });
    }
}
