package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeq;

public interface zzer extends IInterface {

    public static abstract class zza extends Binder implements zzer {

        /* renamed from: com.google.android.gms.internal.zzer$zza$zza  reason: collision with other inner class name */
        private static class C0801zza implements zzer {
            private IBinder zzrk;

            C0801zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzhc zzhc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    if (zzhc != null) {
                        obtain.writeInt(1);
                        zzhc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzhp zzhp) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzhp != null ? zzhp.asBinder() : null);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzhq zzhq) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzhq != null ? zzhq.asBinder() : null);
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzhs zzhs, zzhr zzhr) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(zzhs != null ? zzhs.asBinder() : null);
                    if (zzhr != null) {
                        iBinder = zzhr.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzep zzep) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzep != null ? zzep.asBinder() : null);
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzex zzex) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzex != null ? zzex.asBinder() : null);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzeq zzck() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzeq.zza.zzn(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        }

        public static zzer zzo(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzer)) ? new C0801zza(iBinder) : (zzer) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.google.android.gms.internal.zzhc} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v25, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v31 */
        /* JADX WARNING: type inference failed for: r0v32 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 0
                r1 = 1
                switch(r5) {
                    case 1: goto L_0x0011;
                    case 2: goto L_0x0028;
                    case 3: goto L_0x003d;
                    case 4: goto L_0x0052;
                    case 5: goto L_0x0067;
                    case 6: goto L_0x0088;
                    case 7: goto L_0x00a4;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r0 = super.onTransact(r5, r6, r7, r8)
            L_0x0009:
                return r0
            L_0x000a:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r7.writeString(r0)
                r0 = r1
                goto L_0x0009
            L_0x0011:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r2)
                com.google.android.gms.internal.zzeq r2 = r4.zzck()
                r7.writeNoException()
                if (r2 == 0) goto L_0x0023
                android.os.IBinder r0 = r2.asBinder()
            L_0x0023:
                r7.writeStrongBinder(r0)
                r0 = r1
                goto L_0x0009
            L_0x0028:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzep r0 = com.google.android.gms.internal.zzep.zza.zzm(r0)
                r4.zzb((com.google.android.gms.internal.zzep) r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x003d:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzhp r0 = com.google.android.gms.internal.zzhp.zza.zzH(r0)
                r4.zza((com.google.android.gms.internal.zzhp) r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0052:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzhq r0 = com.google.android.gms.internal.zzhq.zza.zzI(r0)
                r4.zza((com.google.android.gms.internal.zzhq) r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0067:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                android.os.IBinder r2 = r6.readStrongBinder()
                com.google.android.gms.internal.zzhs r2 = com.google.android.gms.internal.zzhs.zza.zzK(r2)
                android.os.IBinder r3 = r6.readStrongBinder()
                com.google.android.gms.internal.zzhr r3 = com.google.android.gms.internal.zzhr.zza.zzJ(r3)
                r4.zza(r0, r2, r3)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x0088:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x009b
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzhc> r0 = com.google.android.gms.internal.zzhc.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzhc r0 = (com.google.android.gms.internal.zzhc) r0
            L_0x009b:
                r4.zza((com.google.android.gms.internal.zzhc) r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            L_0x00a4:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzex r0 = com.google.android.gms.internal.zzex.zza.zzt(r0)
                r4.zzb((com.google.android.gms.internal.zzex) r0)
                r7.writeNoException()
                r0 = r1
                goto L_0x0009
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzer.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(zzhc zzhc) throws RemoteException;

    void zza(zzhp zzhp) throws RemoteException;

    void zza(zzhq zzhq) throws RemoteException;

    void zza(String str, zzhs zzhs, zzhr zzhr) throws RemoteException;

    void zzb(zzep zzep) throws RemoteException;

    void zzb(zzex zzex) throws RemoteException;

    zzeq zzck() throws RemoteException;
}
