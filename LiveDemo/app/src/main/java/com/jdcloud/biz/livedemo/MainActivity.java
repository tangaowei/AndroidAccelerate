package com.jdcloud.biz.livedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jdcloud.biz.livedemo.cameraSurfaceView.CameraSurfaceViewActivity;
import com.jdcloud.biz.livedemo.glsurfaceviewdemo.OpenGLES20Activity;

// grafika TextureFromCameraActivity
public class MainActivity extends AppCompatActivity {
    private Button mCameraSurfaceView;
    private Button mTextureFromCamera;
    private Button mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCameraSurfaceView = (Button) findViewById(R.id.cameraSurfaceView);
        mTextureFromCamera = (Button) findViewById(R.id.textureFromCamera);
        mGLSurfaceView = (Button) findViewById(R.id.glSurfaceView);
        mCameraSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraSurfaceViewActivity.class));
            }
        });
        mTextureFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TextureFromCameraActivity.class));
            }
        });
        mGLSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OpenGLES20Activity.class));
            }
        });
    }
}