package demo.android.com.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by tangaowei on 17/11/1.
 */

public class RemoteService extends Service {

    private final ISimpleMath.Stub mBinder = new ISimpleMath.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public int subtract(int a, int b) throws RemoteException {
            return a - b;
        }
    };

    private final MySimpleMath.Stub mMyBinder = new MySimpleMath.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public int subtract(int a, int b) throws RemoteException {
            return a - b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
