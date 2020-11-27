package com.jdcloud.biz.livedemo;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.jdcloud.biz.livedemo.utils.CameraUtils;
import com.jdcloud.biz.livedemo.utils.PermissionUtils;

import java.lang.ref.WeakReference;

public class TextureFromCameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final String TAG = "MainActivity";

    // Requested values; actual may differ.
    private static final int REQ_CAMERA_FPS = 30;

    private static SurfaceHolder sSurfaceHolder;
    private RenderThread mRenderThread;
    private MainHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_from_camera);

        mHandler = new MainHandler(this);
        SurfaceView sv = (SurfaceView) findViewById(R.id.cameraOnTexture_surfaceView);
        SurfaceHolder sh = sv.getHolder();
        sh.addCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!PermissionUtils.hasCameraPermission(this)) {
            PermissionUtils.requestCameraPermission(this, false);
            return;
        }
        mRenderThread = new RenderThread(mHandler);
        mRenderThread.setName("renderThread");
        mRenderThread.start();
        mRenderThread.waitToReady();

        RenderThread.RenderHandler rh = mRenderThread.getHandler();
        if (sSurfaceHolder != null) {
            Log.d(TAG, "Sending previous surface");
            rh.sendSurfaceAvailable(sSurfaceHolder, false);
        } else {
            Log.d(TAG, "No previous surface");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mRenderThread == null) {
            return;
        }
        RenderThread.RenderHandler rh = mRenderThread.getHandler();
        rh.sendShutdown();
        try {
            mRenderThread.join();
        } catch (InterruptedException e) {
            //
        }
        mRenderThread = null;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.d(TAG, "surfaceCreated holder=" + surfaceHolder + " (static=" + sSurfaceHolder + ")");
        if (sSurfaceHolder != null) {
            throw new RuntimeException("sSurfaceHolder is already set");
        }
        sSurfaceHolder = surfaceHolder;

        if (mRenderThread != null) {
            // Normal case -- render thread is running, tell it about the new surface.
            RenderThread.RenderHandler rh = mRenderThread.getHandler();
            rh.sendSurfaceAvailable(surfaceHolder, true);
        } else {
            Log.d(TAG, "render thread not running");
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        sSurfaceHolder = null;
    }

    private static class MainHandler extends Handler {
        private WeakReference<TextureFromCameraActivity> mWeakActivity;

        public MainHandler(TextureFromCameraActivity activity) {
            mWeakActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            TextureFromCameraActivity activity = mWeakActivity.get();
            if (activity == null) {
                Log.d("", "");
                return;
            }
        }
    }

    private static class RenderThread extends Thread implements SurfaceTexture.OnFrameAvailableListener {
        private volatile RenderHandler mHandler;
//        private Camera mCamera;
        private MainHandler mMainHandler;

        private boolean isReady = false;

        // Receives the output from the camera preview.
        private SurfaceTexture mCameraTexture;
        public RenderThread(MainHandler mHandler) {
            this.mMainHandler = mHandler;
        }
        @Override
        public void run() {
            Looper.prepare();
            mHandler = new RenderHandler(this);
            synchronized (this) {
                isReady = true;
                notifyAll();
            }
//            openCamera(REQ_CAMERA_WIDTH, REQ_CAMERA_HEIGHT, REQ_CAMERA_FPS);
            CameraUtils.openFrontalCamera(REQ_CAMERA_FPS);
            Looper.loop();

            CameraUtils.releaseCamera();
            synchronized (this) {
                isReady = false;
            }
        }

        public void waitToReady() {
            try {
                synchronized (this) {
                    while (!isReady) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
            }
        }

        /**
         * Shuts everything down.
         */
        private void shutdown() {
            Log.d(TAG, "shutdown");
            Looper.myLooper().quit();
        }

        @Override
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            //
        }

        public RenderHandler getHandler() {
            return mHandler;
        }

        private void  surfaceAvailable(SurfaceHolder holder, boolean newSurface) {
            //
            Surface surface = holder.getSurface();
            Log.d(TAG, "call surfaceAvailable on thread: " + Thread.currentThread().getName() + ", newSurface: " + newSurface);
            try {
                CameraUtils.startPreviewDisplay(holder);
            } catch(Exception e) {
                Log.d(TAG, "call surfaceAvailable on thread: " + e.getMessage());
            }
        }

        private static class RenderHandler extends Handler {
            private static final int MSG_SURFACE_AVAILABLE = 0;
            private static final int MSG_SHUTDOWN = 3;

            private WeakReference<RenderThread> mWeakRenderThread;
            public RenderHandler(RenderThread rt) {
                mWeakRenderThread = new WeakReference<RenderThread>(rt);
            }

            public void sendSurfaceAvailable(SurfaceHolder holder, boolean newSurface) {
                sendMessage(obtainMessage(MSG_SURFACE_AVAILABLE, newSurface ? 1 : 0, 0, holder));
            }

            public void sendShutdown() {
                sendMessage(obtainMessage(MSG_SHUTDOWN));
            }

            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                RenderThread renderThread = mWeakRenderThread.get();
                if (renderThread == null) {
                    Log.w(TAG, "RenderHandler.handleMessage: weak ref is null");
                    return;
                }
                switch (what) {
                    case MSG_SURFACE_AVAILABLE:
                        renderThread.surfaceAvailable((SurfaceHolder) msg.obj, msg.arg1 != 0);
                        break;
                    case MSG_SHUTDOWN:
                        renderThread.shutdown();
                        break;
                }
            }
        }
    }
}
