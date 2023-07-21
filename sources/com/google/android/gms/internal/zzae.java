package com.google.android.gms.internal;

import android.support.p000v4.view.MotionEventCompat;
import java.io.IOException;

public interface zzae {

    public static final class zza extends zzbxn<zza> {
        public zzb zzaK;
        public zzc zzaL;

        public zza() {
            this.zzcuJ = -1;
        }

        public static zza zzc(byte[] bArr) throws zzbxs {
            return (zza) zzbxt.zza(new zza(), bArr);
        }

        /* renamed from: zza */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        if (this.zzaK == null) {
                            this.zzaK = new zzb();
                        }
                        zzbxl.zza(this.zzaK);
                        continue;
                    case 18:
                        if (this.zzaL == null) {
                            this.zzaL = new zzc();
                        }
                        zzbxl.zza(this.zzaL);
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaen)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaK != null) {
                zzbxm.zza(1, (zzbxt) this.zzaK);
            }
            if (this.zzaL != null) {
                zzbxm.zza(2, (zzbxt) this.zzaL);
            }
            super.zza(zzbxm);
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaK != null) {
                zzu += zzbxm.zzc(1, (zzbxt) this.zzaK);
            }
            return this.zzaL != null ? zzu + zzbxm.zzc(2, (zzbxt) this.zzaL) : zzu;
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public Integer zzaM = null;

        public zzb() {
            this.zzcuJ = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaM != null) {
                zzbxm.zzJ(27, this.zzaM.intValue());
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzc */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 216:
                        int zzaer = zzbxl.zzaer();
                        switch (zzaer) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.zzaM = Integer.valueOf(zzaer);
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!super.zza(zzbxl, zzaen)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            return this.zzaM != null ? zzu + zzbxm.zzL(27, this.zzaM.intValue()) : zzu;
        }
    }

    public static final class zzc extends zzbxn<zzc> {
        public String zzaN = null;
        public String zzaO = null;
        public String zzaP = null;
        public String zzaQ = null;
        public String zzaR = null;

        public zzc() {
            this.zzcuJ = -1;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaN != null) {
                zzbxm.zzq(1, this.zzaN);
            }
            if (this.zzaO != null) {
                zzbxm.zzq(2, this.zzaO);
            }
            if (this.zzaP != null) {
                zzbxm.zzq(3, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzbxm.zzq(4, this.zzaQ);
            }
            if (this.zzaR != null) {
                zzbxm.zzq(5, this.zzaR);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzd */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        this.zzaN = zzbxl.readString();
                        continue;
                    case 18:
                        this.zzaO = zzbxl.readString();
                        continue;
                    case 26:
                        this.zzaP = zzbxl.readString();
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_3:
                        this.zzaQ = zzbxl.readString();
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_11:
                        this.zzaR = zzbxl.readString();
                        continue;
                    default:
                        if (!super.zza(zzbxl, zzaen)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaN != null) {
                zzu += zzbxm.zzr(1, this.zzaN);
            }
            if (this.zzaO != null) {
                zzu += zzbxm.zzr(2, this.zzaO);
            }
            if (this.zzaP != null) {
                zzu += zzbxm.zzr(3, this.zzaP);
            }
            if (this.zzaQ != null) {
                zzu += zzbxm.zzr(4, this.zzaQ);
            }
            return this.zzaR != null ? zzu + zzbxm.zzr(5, this.zzaR) : zzu;
        }
    }
}
