package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzfa;

public interface zzet extends IInterface {

    public static abstract class zza extends Binder implements zzet {

        /* renamed from: com.google.android.gms.internal.zzet$zza$zza  reason: collision with other inner class name */
        private static class C0803zza implements zzet {
            private IBinder zzrk;

            C0803zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void destroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getMediationAdapterClassName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isLoading() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(23, obtain, obtain2, 0);
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

            public boolean isReady() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(3, obtain, obtain2, 0);
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

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setManualImpressionsEnabled(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setUserId(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeString(str);
                    this.zzrk.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInterstitial() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopLoading() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzeg zzeg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (zzeg != null) {
                        obtain.writeInt(1);
                        zzeg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzeo zzeo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzeo != null ? zzeo.asBinder() : null);
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzep zzep) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzep != null ? zzep.asBinder() : null);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzev zzev) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzev != null ? zzev.asBinder() : null);
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzex zzex) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzex != null ? zzex.asBinder() : null);
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzfc zzfc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (zzfc != null) {
                        obtain.writeInt(1);
                        zzfc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzft zzft) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (zzft != null) {
                        obtain.writeInt(1);
                        zzft.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzgp zzgp) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzgp != null ? zzgp.asBinder() : null);
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzle zzle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzle != null ? zzle.asBinder() : null);
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzli zzli, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zzli != null ? zzli.asBinder() : null);
                    obtain.writeString(str);
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zznw zznw) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    obtain.writeStrongBinder(zznw != null ? zznw.asBinder() : null);
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzb(zzec zzec) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper zzbB() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzeg zzbC() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? zzeg.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzbE() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzfa zzbF() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManager");
                    this.zzrk.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzfa.zza.zzw(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IAdManager");
        }

        public static zzet zzq(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzet)) ? new C0803zza(iBinder) : (zzet) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.google.android.gms.internal.zzfc} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.google.android.gms.internal.zzft} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v54, resolved type: com.google.android.gms.internal.zzec} */
        /* JADX WARNING: type inference failed for: r0v0 */
        /* JADX WARNING: type inference failed for: r0v9, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v37, types: [com.google.android.gms.internal.zzeg] */
        /* JADX WARNING: type inference failed for: r0v65, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v68 */
        /* JADX WARNING: type inference failed for: r0v69 */
        /* JADX WARNING: type inference failed for: r0v70 */
        /* JADX WARNING: type inference failed for: r0v71 */
        /* JADX WARNING: type inference failed for: r0v72 */
        /* JADX WARNING: type inference failed for: r0v73 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r2 = 0
                r0 = 0
                r1 = 1
                switch(r5) {
                    case 1: goto L_0x0011;
                    case 2: goto L_0x0027;
                    case 3: goto L_0x0033;
                    case 4: goto L_0x0048;
                    case 5: goto L_0x0069;
                    case 6: goto L_0x0075;
                    case 7: goto L_0x0081;
                    case 8: goto L_0x0096;
                    case 9: goto L_0x00ab;
                    case 10: goto L_0x00b8;
                    case 11: goto L_0x00c5;
                    case 12: goto L_0x00d2;
                    case 13: goto L_0x00ed;
                    case 14: goto L_0x0108;
                    case 15: goto L_0x011d;
                    case 18: goto L_0x0136;
                    case 19: goto L_0x0147;
                    case 20: goto L_0x015c;
                    case 21: goto L_0x0171;
                    case 22: goto L_0x0186;
                    case 23: goto L_0x019a;
                    case 24: goto L_0x01ae;
                    case 25: goto L_0x01c3;
                    case 26: goto L_0x01d4;
                    case 29: goto L_0x01eb;
                    case 30: goto L_0x0206;
                    case 1598968902: goto L_0x000b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                boolean r1 = super.onTransact(r5, r6, r7, r8)
            L_0x000a:
                return r1
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r7.writeString(r0)
                goto L_0x000a
            L_0x0011:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r2)
                com.google.android.gms.dynamic.IObjectWrapper r2 = r4.zzbB()
                r7.writeNoException()
                if (r2 == 0) goto L_0x0023
                android.os.IBinder r0 = r2.asBinder()
            L_0x0023:
                r7.writeStrongBinder(r0)
                goto L_0x000a
            L_0x0027:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                r4.destroy()
                r7.writeNoException()
                goto L_0x000a
            L_0x0033:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                boolean r0 = r4.isReady()
                r7.writeNoException()
                if (r0 == 0) goto L_0x0046
                r0 = r1
            L_0x0042:
                r7.writeInt(r0)
                goto L_0x000a
            L_0x0046:
                r0 = r2
                goto L_0x0042
            L_0x0048:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r3)
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x005b
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r0 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzec r0 = (com.google.android.gms.internal.zzec) r0
            L_0x005b:
                boolean r0 = r4.zzb(r0)
                r7.writeNoException()
                if (r0 == 0) goto L_0x0065
                r2 = r1
            L_0x0065:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x0069:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                r4.pause()
                r7.writeNoException()
                goto L_0x000a
            L_0x0075:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                r4.resume()
                r7.writeNoException()
                goto L_0x000a
            L_0x0081:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzep r0 = com.google.android.gms.internal.zzep.zza.zzm(r0)
                r4.zza((com.google.android.gms.internal.zzep) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0096:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzev r0 = com.google.android.gms.internal.zzev.zza.zzs(r0)
                r4.zza((com.google.android.gms.internal.zzev) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x00ab:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                r4.showInterstitial()
                r7.writeNoException()
                goto L_0x000a
            L_0x00b8:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                r4.stopLoading()
                r7.writeNoException()
                goto L_0x000a
            L_0x00c5:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                r4.zzbE()
                r7.writeNoException()
                goto L_0x000a
            L_0x00d2:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                com.google.android.gms.internal.zzeg r0 = r4.zzbC()
                r7.writeNoException()
                if (r0 == 0) goto L_0x00e8
                r7.writeInt(r1)
                r0.writeToParcel(r7, r1)
                goto L_0x000a
            L_0x00e8:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x00ed:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x0100
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzeg> r0 = com.google.android.gms.internal.zzeg.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzeg r0 = (com.google.android.gms.internal.zzeg) r0
            L_0x0100:
                r4.zza((com.google.android.gms.internal.zzeg) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0108:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzle r0 = com.google.android.gms.internal.zzle.zza.zzX(r0)
                r4.zza((com.google.android.gms.internal.zzle) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x011d:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzli r0 = com.google.android.gms.internal.zzli.zza.zzab(r0)
                java.lang.String r2 = r6.readString()
                r4.zza(r0, r2)
                r7.writeNoException()
                goto L_0x000a
            L_0x0136:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                java.lang.String r0 = r4.getMediationAdapterClassName()
                r7.writeNoException()
                r7.writeString(r0)
                goto L_0x000a
            L_0x0147:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzgp r0 = com.google.android.gms.internal.zzgp.zza.zzA(r0)
                r4.zza((com.google.android.gms.internal.zzgp) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x015c:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzeo r0 = com.google.android.gms.internal.zzeo.zza.zzl(r0)
                r4.zza((com.google.android.gms.internal.zzeo) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0171:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zzex r0 = com.google.android.gms.internal.zzex.zza.zzt(r0)
                r4.zza((com.google.android.gms.internal.zzex) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0186:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x0192
                r2 = r1
            L_0x0192:
                r4.setManualImpressionsEnabled(r2)
                r7.writeNoException()
                goto L_0x000a
            L_0x019a:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                boolean r0 = r4.isLoading()
                r7.writeNoException()
                if (r0 == 0) goto L_0x01a9
                r2 = r1
            L_0x01a9:
                r7.writeInt(r2)
                goto L_0x000a
            L_0x01ae:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                android.os.IBinder r0 = r6.readStrongBinder()
                com.google.android.gms.internal.zznw r0 = com.google.android.gms.internal.zznw.zza.zzaj(r0)
                r4.zza((com.google.android.gms.internal.zznw) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x01c3:
                java.lang.String r0 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                r4.setUserId(r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x01d4:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r2)
                com.google.android.gms.internal.zzfa r2 = r4.zzbF()
                r7.writeNoException()
                if (r2 == 0) goto L_0x01e6
                android.os.IBinder r0 = r2.asBinder()
            L_0x01e6:
                r7.writeStrongBinder(r0)
                goto L_0x000a
            L_0x01eb:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x01fe
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzft> r0 = com.google.android.gms.internal.zzft.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzft r0 = (com.google.android.gms.internal.zzft) r0
            L_0x01fe:
                r4.zza((com.google.android.gms.internal.zzft) r0)
                r7.writeNoException()
                goto L_0x000a
            L_0x0206:
                java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdManager"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x0219
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzfc> r0 = com.google.android.gms.internal.zzfc.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                com.google.android.gms.internal.zzfc r0 = (com.google.android.gms.internal.zzfc) r0
            L_0x0219:
                r4.zza((com.google.android.gms.internal.zzfc) r0)
                r7.writeNoException()
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzet.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void destroy() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzeg zzeg) throws RemoteException;

    void zza(zzeo zzeo) throws RemoteException;

    void zza(zzep zzep) throws RemoteException;

    void zza(zzev zzev) throws RemoteException;

    void zza(zzex zzex) throws RemoteException;

    void zza(zzfc zzfc) throws RemoteException;

    void zza(zzft zzft) throws RemoteException;

    void zza(zzgp zzgp) throws RemoteException;

    void zza(zzle zzle) throws RemoteException;

    void zza(zzli zzli, String str) throws RemoteException;

    void zza(zznw zznw) throws RemoteException;

    boolean zzb(zzec zzec) throws RemoteException;

    IObjectWrapper zzbB() throws RemoteException;

    zzeg zzbC() throws RemoteException;

    void zzbE() throws RemoteException;

    zzfa zzbF() throws RemoteException;
}
