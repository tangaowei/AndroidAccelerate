package demo.android.com.ipcdemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by tangaowei on 17/11/2.
 */

public interface MySimpleMath extends IInterface {
    public static final String TAG = MainActivity.TAG;

    public int add(int a, int b) throws RemoteException;
    public int subtract(int a, int b) throws RemoteException;

    public static abstract class Stub extends Binder implements MySimpleMath {
        static final int TRANSACTION_ADD = IBinder.FIRST_CALL_TRANSACTION + 0;
        static final int TRANSACTION_SUBTRACT = IBinder.FIRST_CALL_TRANSACTION + 1;

        private static final String DESCRIPTOR = "demo.android.com.ipcdemo.MySimpleMath";
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static MySimpleMath asInterface(IBinder obj) {
            Log.d(TAG, "step 0: call MySimpleMath.asInterface Obj: " + obj + ", processname: " + IPCUtils.getCurrentProcessName());
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && iin instanceof MySimpleMath) {
                return ((MySimpleMath) iin);
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Log.d(TAG, "step 3: call Stub.onTransact(), code: " + code + ", process: " + IPCUtils.getCurrentProcessName());
            switch(code) {
                case TRANSACTION_ADD: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    int _result = this.add(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_SUBTRACT: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    int _result = this.subtract(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        public static class Proxy implements MySimpleMath {
            private IBinder mRemote;
            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public int add(int a, int b) throws RemoteException {
                Log.d(TAG, "step 2: call Proxy.add(), process: " + IPCUtils.getCurrentProcessName());
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(a);
                    _data.writeInt(b);
                    mRemote.transact(Stub.TRANSACTION_ADD, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _data.recycle();
                    _reply.recycle();
                }
                return _result;
            }

            @Override
            public int subtract(int a, int b) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(a);
                    _data.writeInt(b);
                    mRemote.transact(Stub.TRANSACTION_SUBTRACT, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }
    }
}
