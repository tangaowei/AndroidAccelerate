package demo.android.com.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by tangaowei on 17/11/4.
 */

public class MyRemoteService extends Service {
    private final MySimpleMath.Stub mMyBinder = new MySimpleMath.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            Log.d(MainActivity.TAG, "MyRemoteService add");
            return a + b;
        }

        @Override
        public int subtract(int a, int b) throws RemoteException {
            return a - b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }
}
