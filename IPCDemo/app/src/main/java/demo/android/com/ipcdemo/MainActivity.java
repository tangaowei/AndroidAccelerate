package demo.android.com.ipcdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "IPCTest";

    ISimpleMath mService;
    MySimpleMath mMyService;

    private ServiceConnection mMyConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyService = MySimpleMath.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMyService = null;
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ISimpleMath.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        Intent myIntent = new Intent(this, MyRemoteService.class);
        bindService(myIntent, mMyConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                aidl_add();
                break;
            case R.id.button_subtract:
                aidl_subtract();
                break;
            case R.id.button_my_add:
                my_add();
                break;
            case R.id.button_my_subtract:
                my_subtract();
                break;
        }
    }

    private void aidl_add() {
        try {
            int result = mService.add(1, 2);
            Log.d(TAG, "aidl 1 + 2 result: " + result);
        } catch(RemoteException e) {
            Log.d(TAG, "aidl remoteException: " + e.getMessage());
        } catch(Exception e) {
            Log.d(TAG, "aidl Exception: " + e.getMessage());
        }
    }

    private void aidl_subtract() {
        try {
            int result = mService.subtract(1, 2);
            Log.d(TAG, "aidl 1 -2 result: " + result);
        } catch(RemoteException e) {
            Log.d(TAG, "aidl remoteException: " + e.getMessage());
        } catch(Exception e) {
            Log.d(TAG, "aidl Exception: " + e.getMessage());
        }
    }

    private void my_add() {
        try {
            int result = mMyService.add(1, 2);
            Log.d(TAG, "1 + 2 result: " + result);
        } catch(RemoteException e) {
            Log.d(TAG, "remoteException: " + e.getMessage());
        } catch(Exception e) {
            Log.d(TAG, "Exception: " + e.getMessage());
        }
    }

    private void my_subtract() {
        try {
            int result = mMyService.subtract(1, 2);
            Log.d(TAG, "1 -2 result: " + result);
        } catch(RemoteException e) {
            Log.d(TAG, "remoteException: " + e.getMessage());
        } catch(Exception e) {
            Log.d(TAG, "Exception: " + e.getMessage());
        }
    }
}
