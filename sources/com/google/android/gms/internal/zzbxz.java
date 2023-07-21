package com.google.android.gms.internal;

import android.support.p000v4.view.MotionEventCompat;
import com.google.ads.AdSize;
import java.io.IOException;

public interface zzbxz {

    public static final class zza extends zzbxn<zza> {
        private static volatile zza[] zzcvG;
        public String zzcvH;

        public zza() {
            zzafi();
        }

        public static zza[] zzafh() {
            if (zzcvG == null) {
                synchronized (zzbxr.zzcuI) {
                    if (zzcvG == null) {
                        zzcvG = new zza[0];
                    }
                }
            }
            return zzcvG;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvH != null && !this.zzcvH.equals("")) {
                zzbxm.zzq(1, this.zzcvH);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaV */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        this.zzcvH = zzbxl.readString();
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

        public zza zzafi() {
            this.zzcvH = "";
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            return (this.zzcvH == null || this.zzcvH.equals("")) ? zzu : zzu + zzbxm.zzr(1, this.zzcvH);
        }
    }

    public static final class zzb extends zzbxn<zzb> {
        public String zzcvH;
        public String zzcvI;
        public long zzcvJ;
        public String zzcvK;
        public int zzcvL;
        public int zzcvM;
        public String zzcvN;
        public String zzcvO;
        public String zzcvP;
        public String zzcvQ;
        public String zzcvR;
        public int zzcvS;
        public zza[] zzcvT;

        public zzb() {
            zzafj();
        }

        public static zzb zzak(byte[] bArr) throws zzbxs {
            return (zzb) zzbxt.zza(new zzb(), bArr);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvH != null && !this.zzcvH.equals("")) {
                zzbxm.zzq(1, this.zzcvH);
            }
            if (this.zzcvI != null && !this.zzcvI.equals("")) {
                zzbxm.zzq(2, this.zzcvI);
            }
            if (this.zzcvJ != 0) {
                zzbxm.zzb(3, this.zzcvJ);
            }
            if (this.zzcvK != null && !this.zzcvK.equals("")) {
                zzbxm.zzq(4, this.zzcvK);
            }
            if (this.zzcvL != 0) {
                zzbxm.zzJ(5, this.zzcvL);
            }
            if (this.zzcvM != 0) {
                zzbxm.zzJ(6, this.zzcvM);
            }
            if (this.zzcvN != null && !this.zzcvN.equals("")) {
                zzbxm.zzq(7, this.zzcvN);
            }
            if (this.zzcvO != null && !this.zzcvO.equals("")) {
                zzbxm.zzq(8, this.zzcvO);
            }
            if (this.zzcvP != null && !this.zzcvP.equals("")) {
                zzbxm.zzq(9, this.zzcvP);
            }
            if (this.zzcvQ != null && !this.zzcvQ.equals("")) {
                zzbxm.zzq(10, this.zzcvQ);
            }
            if (this.zzcvR != null && !this.zzcvR.equals("")) {
                zzbxm.zzq(11, this.zzcvR);
            }
            if (this.zzcvS != 0) {
                zzbxm.zzJ(12, this.zzcvS);
            }
            if (this.zzcvT != null && this.zzcvT.length > 0) {
                for (zza zza : this.zzcvT) {
                    if (zza != null) {
                        zzbxm.zza(13, (zzbxt) zza);
                    }
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaW */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        this.zzcvH = zzbxl.readString();
                        continue;
                    case 18:
                        this.zzcvI = zzbxl.readString();
                        continue;
                    case MotionEventCompat.AXIS_DISTANCE:
                        this.zzcvJ = zzbxl.zzaeq();
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_3:
                        this.zzcvK = zzbxl.readString();
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_9:
                        this.zzcvL = zzbxl.zzaer();
                        continue;
                    case 48:
                        this.zzcvM = zzbxl.zzaer();
                        continue;
                    case 58:
                        this.zzcvN = zzbxl.readString();
                        continue;
                    case 66:
                        this.zzcvO = zzbxl.readString();
                        continue;
                    case 74:
                        this.zzcvP = zzbxl.readString();
                        continue;
                    case 82:
                        this.zzcvQ = zzbxl.readString();
                        continue;
                    case AdSize.LARGE_AD_HEIGHT:
                        this.zzcvR = zzbxl.readString();
                        continue;
                    case 96:
                        int zzaer = zzbxl.zzaer();
                        switch (zzaer) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcvS = zzaer;
                                break;
                            default:
                                continue;
                        }
                    case 106:
                        int zzb = zzbxw.zzb(zzbxl, 106);
                        int length = this.zzcvT == null ? 0 : this.zzcvT.length;
                        zza[] zzaArr = new zza[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcvT, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new zza();
                            zzbxl.zza(zzaArr[length]);
                            zzbxl.zzaen();
                            length++;
                        }
                        zzaArr[length] = new zza();
                        zzbxl.zza(zzaArr[length]);
                        this.zzcvT = zzaArr;
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

        public zzb zzafj() {
            this.zzcvH = "";
            this.zzcvI = "";
            this.zzcvJ = 0;
            this.zzcvK = "";
            this.zzcvL = 0;
            this.zzcvM = 0;
            this.zzcvN = "";
            this.zzcvO = "";
            this.zzcvP = "";
            this.zzcvQ = "";
            this.zzcvR = "";
            this.zzcvS = 0;
            this.zzcvT = zza.zzafh();
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvH != null && !this.zzcvH.equals("")) {
                zzu += zzbxm.zzr(1, this.zzcvH);
            }
            if (this.zzcvI != null && !this.zzcvI.equals("")) {
                zzu += zzbxm.zzr(2, this.zzcvI);
            }
            if (this.zzcvJ != 0) {
                zzu += zzbxm.zzf(3, this.zzcvJ);
            }
            if (this.zzcvK != null && !this.zzcvK.equals("")) {
                zzu += zzbxm.zzr(4, this.zzcvK);
            }
            if (this.zzcvL != 0) {
                zzu += zzbxm.zzL(5, this.zzcvL);
            }
            if (this.zzcvM != 0) {
                zzu += zzbxm.zzL(6, this.zzcvM);
            }
            if (this.zzcvN != null && !this.zzcvN.equals("")) {
                zzu += zzbxm.zzr(7, this.zzcvN);
            }
            if (this.zzcvO != null && !this.zzcvO.equals("")) {
                zzu += zzbxm.zzr(8, this.zzcvO);
            }
            if (this.zzcvP != null && !this.zzcvP.equals("")) {
                zzu += zzbxm.zzr(9, this.zzcvP);
            }
            if (this.zzcvQ != null && !this.zzcvQ.equals("")) {
                zzu += zzbxm.zzr(10, this.zzcvQ);
            }
            if (this.zzcvR != null && !this.zzcvR.equals("")) {
                zzu += zzbxm.zzr(11, this.zzcvR);
            }
            if (this.zzcvS != 0) {
                zzu += zzbxm.zzL(12, this.zzcvS);
            }
            if (this.zzcvT == null || this.zzcvT.length <= 0) {
                return zzu;
            }
            int i = zzu;
            for (zza zza : this.zzcvT) {
                if (zza != null) {
                    i += zzbxm.zzc(13, (zzbxt) zza);
                }
            }
            return i;
        }
    }
}
