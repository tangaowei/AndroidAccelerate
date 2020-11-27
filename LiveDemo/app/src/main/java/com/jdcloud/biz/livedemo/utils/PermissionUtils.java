package com.jdcloud.biz.livedemo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    public static final int  RC_PERMISSION_REQUEST = 9222;
    public static boolean hasCameraPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestCameraPermission(Activity activity, boolean requestWritePermission) {

        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA) || (requestWritePermission &&
                ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE));
        if (showRationale) {
            Toast.makeText(activity,
                    "Camera permission is needed to run this application", Toast.LENGTH_LONG).show();
        } else {

            // No explanation needed, we can request the permission.

            String permissions[] = requestWritePermission ? new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}: new String[]{Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(activity,permissions,RC_PERMISSION_REQUEST);
        }
    }
}
