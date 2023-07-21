package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface zzkz extends IInterface {

    public static abstract class zza extends Binder implements zzkz {

        /* renamed from: com.google.android.gms.internal.zzkz$zza$zza  reason: collision with other inner class name */
        private static class C0830zza implements zzkz {
            private IBinder zzrk;

            C0830zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onBackPressed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onCreate(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDestroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onRestart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onResume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSaveInstanceState(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzbo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzhF() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzo(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        }

        public static zzkz zzT(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzkz)) ? new C0830zza(iBinder) : (zzkz) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Intent] */
        /* JADX WARNING: type inference failed for: r0v30 */
        /* JADX WARNING: type inference failed for: r0v31 */
        /* JADX WARNING: type inference failed for: r0v32 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r6, android.os.Parcel r7, android.os.Parcel r8, int r9) throws android.os.RemoteException {
            /*
                r5 = this;
                r2 = 0
                r0 = 0
                r1 = 1
                switch(r6) {
                    case 1: goto L_0x0011;
                    case 2: goto L_0x002b;
                    case 3: goto L_0x0037;
                    case 4: goto L_0x0043;
                    case 5: goto L_0x004f;
                    case 6: goto L_0x005b;
                    case 7: goto L_0x0081;
                    case 8: goto L_0x008e;
                    case 9: goto L_0x009b;
                    case 10: goto L_0x00a8;
                    case 11: goto L_0x00b5;
                    case 12: goto L_0x00cb;
                    case 13: goto L_0x00ee;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r1 = super.onTransact(r6, r7, r8, r9)
            L_0x000a:
                return r1
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r8.writeString(r0)
                goto L_0x000a
            L_0x0011:
                java.lang.String r2 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                if (r2 == 0) goto L_0x0024
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0024:
                r5.onCreate(r0)
                r8.writeNoException()
                goto L_0x000a
            L_0x002b:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onRestart()
                r8.writeNoException()
                goto L_0x000a
            L_0x0037:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onStart()
                r8.writeNoException()
                goto L_0x000a
            L_0x0043:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onResume()
                r8.writeNoException()
                goto L_0x000a
            L_0x004f:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onPause()
                r8.writeNoException()
                goto L_0x000a
            L_0x005b:
                java.lang.String r3 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r3)
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x006e
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x006e:
                r5.onSaveInstanceState(r0)
                r8.writeNoException()
                if (r0 == 0) goto L_0x007d
                r8.writeInt(r1)
                r0.writeToParcel(r8, r1)
                goto L_0x000a
            L_0x007d:
                r8.writeInt(r2)
                goto L_0x000a
            L_0x0081:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onStop()
                r8.writeNoException()
                goto L_0x000a
            L_0x008e:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onDestroy()
                r8.writeNoException()
                goto L_0x000a
            L_0x009b:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.zzbo()
                r8.writeNoException()
                goto L_0x000a
            L_0x00a8:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                r5.onBackPressed()
                r8.writeNoException()
                goto L_0x000a
            L_0x00b5:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                boolean r0 = r5.zzhF()
                r8.writeNoException()
                if (r0 == 0) goto L_0x00c9
                r0 = r1
            L_0x00c4:
                r8.writeInt(r0)
                goto L_0x000a
            L_0x00c9:
                r0 = r2
                goto L_0x00c4
            L_0x00cb:
                java.lang.String r2 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r2)
                int r2 = r7.readInt()
                int r3 = r7.readInt()
                int r4 = r7.readInt()
                if (r4 == 0) goto L_0x00e6
                android.os.Parcelable$Creator r0 = android.content.Intent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.content.Intent r0 = (android.content.Intent) r0
            L_0x00e6:
                r5.onActivityResult(r2, r3, r0)
                r8.writeNoException()
                goto L_0x000a
            L_0x00ee:
                java.lang.String r0 = "com.google.android.gms.ads.internal.overlay.client.IAdOverlay"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r0)
                r5.zzo(r0)
                r8.writeNoException()
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzkz.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void onActivityResult(int i, int i2, Intent intent) throws RemoteException;

    void onBackPressed() throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onPause() throws RemoteException;

    void onRestart() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void zzbo() throws RemoteException;

    boolean zzhF() throws RemoteException;

    void zzo(IObjectWrapper iObjectWrapper) throws RemoteException;
}
