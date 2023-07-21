package com.google.android.gms.internal;

import android.support.p000v4.media.TransportMediator;
import android.support.p000v4.view.MotionEventCompat;
import com.google.ads.AdSize;
import java.io.IOException;
import java.util.Arrays;

public interface zzbxy {

    public static final class zza extends zzbxn<zza> implements Cloneable {
        public String[] zzcuY;
        public String[] zzcuZ;
        public int[] zzcva;
        public long[] zzcvb;
        public long[] zzcvc;

        public zza() {
            zzaeU();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (!zzbxr.equals((Object[]) this.zzcuY, (Object[]) zza.zzcuY) || !zzbxr.equals((Object[]) this.zzcuZ, (Object[]) zza.zzcuZ) || !zzbxr.equals(this.zzcva, zza.zzcva) || !zzbxr.equals(this.zzcvb, zza.zzcvb) || !zzbxr.equals(this.zzcvc, zza.zzcvc)) {
                return false;
            }
            return (this.zzcuA == null || this.zzcuA.isEmpty()) ? zza.zzcuA == null || zza.zzcuA.isEmpty() : this.zzcuA.equals(zza.zzcuA);
        }

        public int hashCode() {
            return ((this.zzcuA == null || this.zzcuA.isEmpty()) ? 0 : this.zzcuA.hashCode()) + ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzbxr.hashCode((Object[]) this.zzcuY)) * 31) + zzbxr.hashCode((Object[]) this.zzcuZ)) * 31) + zzbxr.hashCode(this.zzcva)) * 31) + zzbxr.hashCode(this.zzcvb)) * 31) + zzbxr.hashCode(this.zzcvc)) * 31);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcuY != null && this.zzcuY.length > 0) {
                for (String str : this.zzcuY) {
                    if (str != null) {
                        zzbxm.zzq(1, str);
                    }
                }
            }
            if (this.zzcuZ != null && this.zzcuZ.length > 0) {
                for (String str2 : this.zzcuZ) {
                    if (str2 != null) {
                        zzbxm.zzq(2, str2);
                    }
                }
            }
            if (this.zzcva != null && this.zzcva.length > 0) {
                for (int zzJ : this.zzcva) {
                    zzbxm.zzJ(3, zzJ);
                }
            }
            if (this.zzcvb != null && this.zzcvb.length > 0) {
                for (long zzb : this.zzcvb) {
                    zzbxm.zzb(4, zzb);
                }
            }
            if (this.zzcvc != null && this.zzcvc.length > 0) {
                for (long zzb2 : this.zzcvc) {
                    zzbxm.zzb(5, zzb2);
                }
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaP */
        public zza zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzbxw.zzb(zzbxl, 10);
                        int length = this.zzcuY == null ? 0 : this.zzcuY.length;
                        String[] strArr = new String[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcuY, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zzbxl.readString();
                            zzbxl.zzaen();
                            length++;
                        }
                        strArr[length] = zzbxl.readString();
                        this.zzcuY = strArr;
                        continue;
                    case 18:
                        int zzb2 = zzbxw.zzb(zzbxl, 18);
                        int length2 = this.zzcuZ == null ? 0 : this.zzcuZ.length;
                        String[] strArr2 = new String[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcuZ, 0, strArr2, 0, length2);
                        }
                        while (length2 < strArr2.length - 1) {
                            strArr2[length2] = zzbxl.readString();
                            zzbxl.zzaen();
                            length2++;
                        }
                        strArr2[length2] = zzbxl.readString();
                        this.zzcuZ = strArr2;
                        continue;
                    case MotionEventCompat.AXIS_DISTANCE:
                        int zzb3 = zzbxw.zzb(zzbxl, 24);
                        int length3 = this.zzcva == null ? 0 : this.zzcva.length;
                        int[] iArr = new int[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcva, 0, iArr, 0, length3);
                        }
                        while (length3 < iArr.length - 1) {
                            iArr[length3] = zzbxl.zzaer();
                            zzbxl.zzaen();
                            length3++;
                        }
                        iArr[length3] = zzbxl.zzaer();
                        this.zzcva = iArr;
                        continue;
                    case 26:
                        int zzqZ = zzbxl.zzqZ(zzbxl.zzaew());
                        int position = zzbxl.getPosition();
                        int i = 0;
                        while (zzbxl.zzaeB() > 0) {
                            zzbxl.zzaer();
                            i++;
                        }
                        zzbxl.zzrb(position);
                        int length4 = this.zzcva == null ? 0 : this.zzcva.length;
                        int[] iArr2 = new int[(i + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzcva, 0, iArr2, 0, length4);
                        }
                        while (length4 < iArr2.length) {
                            iArr2[length4] = zzbxl.zzaer();
                            length4++;
                        }
                        this.zzcva = iArr2;
                        zzbxl.zzra(zzqZ);
                        continue;
                    case 32:
                        int zzb4 = zzbxw.zzb(zzbxl, 32);
                        int length5 = this.zzcvb == null ? 0 : this.zzcvb.length;
                        long[] jArr = new long[(zzb4 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzcvb, 0, jArr, 0, length5);
                        }
                        while (length5 < jArr.length - 1) {
                            jArr[length5] = zzbxl.zzaeq();
                            zzbxl.zzaen();
                            length5++;
                        }
                        jArr[length5] = zzbxl.zzaeq();
                        this.zzcvb = jArr;
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_3:
                        int zzqZ2 = zzbxl.zzqZ(zzbxl.zzaew());
                        int position2 = zzbxl.getPosition();
                        int i2 = 0;
                        while (zzbxl.zzaeB() > 0) {
                            zzbxl.zzaeq();
                            i2++;
                        }
                        zzbxl.zzrb(position2);
                        int length6 = this.zzcvb == null ? 0 : this.zzcvb.length;
                        long[] jArr2 = new long[(i2 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzcvb, 0, jArr2, 0, length6);
                        }
                        while (length6 < jArr2.length) {
                            jArr2[length6] = zzbxl.zzaeq();
                            length6++;
                        }
                        this.zzcvb = jArr2;
                        zzbxl.zzra(zzqZ2);
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_9:
                        int zzb5 = zzbxw.zzb(zzbxl, 40);
                        int length7 = this.zzcvc == null ? 0 : this.zzcvc.length;
                        long[] jArr3 = new long[(zzb5 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzcvc, 0, jArr3, 0, length7);
                        }
                        while (length7 < jArr3.length - 1) {
                            jArr3[length7] = zzbxl.zzaeq();
                            zzbxl.zzaen();
                            length7++;
                        }
                        jArr3[length7] = zzbxl.zzaeq();
                        this.zzcvc = jArr3;
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_11:
                        int zzqZ3 = zzbxl.zzqZ(zzbxl.zzaew());
                        int position3 = zzbxl.getPosition();
                        int i3 = 0;
                        while (zzbxl.zzaeB() > 0) {
                            zzbxl.zzaeq();
                            i3++;
                        }
                        zzbxl.zzrb(position3);
                        int length8 = this.zzcvc == null ? 0 : this.zzcvc.length;
                        long[] jArr4 = new long[(i3 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzcvc, 0, jArr4, 0, length8);
                        }
                        while (length8 < jArr4.length) {
                            jArr4[length8] = zzbxl.zzaeq();
                            length8++;
                        }
                        this.zzcvc = jArr4;
                        zzbxl.zzra(zzqZ3);
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

        public /* synthetic */ zzbxn zzaeG() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public zza zzaeU() {
            this.zzcuY = zzbxw.zzcuT;
            this.zzcuZ = zzbxw.zzcuT;
            this.zzcva = zzbxw.zzcuO;
            this.zzcvb = zzbxw.zzcuP;
            this.zzcvc = zzbxw.zzcuP;
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* renamed from: zzaeV */
        public zza clone() {
            try {
                zza zza = (zza) super.clone();
                if (this.zzcuY != null && this.zzcuY.length > 0) {
                    zza.zzcuY = (String[]) this.zzcuY.clone();
                }
                if (this.zzcuZ != null && this.zzcuZ.length > 0) {
                    zza.zzcuZ = (String[]) this.zzcuZ.clone();
                }
                if (this.zzcva != null && this.zzcva.length > 0) {
                    zza.zzcva = (int[]) this.zzcva.clone();
                }
                if (this.zzcvb != null && this.zzcvb.length > 0) {
                    zza.zzcvb = (long[]) this.zzcvb.clone();
                }
                if (this.zzcvc != null && this.zzcvc.length > 0) {
                    zza.zzcvc = (long[]) this.zzcvc.clone();
                }
                return zza;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int i;
            int zzu = super.zzu();
            if (this.zzcuY == null || this.zzcuY.length <= 0) {
                i = zzu;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (String str : this.zzcuY) {
                    if (str != null) {
                        i3++;
                        i2 += zzbxm.zzkb(str);
                    }
                }
                i = zzu + i2 + (i3 * 1);
            }
            if (this.zzcuZ != null && this.zzcuZ.length > 0) {
                int i4 = 0;
                int i5 = 0;
                for (String str2 : this.zzcuZ) {
                    if (str2 != null) {
                        i5++;
                        i4 += zzbxm.zzkb(str2);
                    }
                }
                i = i + i4 + (i5 * 1);
            }
            if (this.zzcva != null && this.zzcva.length > 0) {
                int i6 = 0;
                for (int zzrf : this.zzcva) {
                    i6 += zzbxm.zzrf(zzrf);
                }
                i = i + i6 + (this.zzcva.length * 1);
            }
            if (this.zzcvb != null && this.zzcvb.length > 0) {
                int i7 = 0;
                for (long zzbf : this.zzcvb) {
                    i7 += zzbxm.zzbf(zzbf);
                }
                i = i + i7 + (this.zzcvb.length * 1);
            }
            if (this.zzcvc == null || this.zzcvc.length <= 0) {
                return i;
            }
            int i8 = 0;
            for (long zzbf2 : this.zzcvc) {
                i8 += zzbxm.zzbf(zzbf2);
            }
            return i + i8 + (this.zzcvc.length * 1);
        }
    }

    public static final class zzb extends zzbxn<zzb> implements Cloneable {
        public String version;
        public int zzcvd;
        public String zzcve;

        public zzb() {
            zzaeW();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzcvd != zzb.zzcvd) {
                return false;
            }
            if (this.zzcve == null) {
                if (zzb.zzcve != null) {
                    return false;
                }
            } else if (!this.zzcve.equals(zzb.zzcve)) {
                return false;
            }
            if (this.version == null) {
                if (zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzb.version)) {
                return false;
            }
            return (this.zzcuA == null || this.zzcuA.isEmpty()) ? zzb.zzcuA == null || zzb.zzcuA.isEmpty() : this.zzcuA.equals(zzb.zzcuA);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzcve == null ? 0 : this.zzcve.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzcvd) * 31)) * 31)) * 31;
            if (this.zzcuA != null && !this.zzcuA.isEmpty()) {
                i = this.zzcuA.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvd != 0) {
                zzbxm.zzJ(1, this.zzcvd);
            }
            if (this.zzcve != null && !this.zzcve.equals("")) {
                zzbxm.zzq(2, this.zzcve);
            }
            if (this.version != null && !this.version.equals("")) {
                zzbxm.zzq(3, this.version);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaQ */
        public zzb zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 8:
                        this.zzcvd = zzbxl.zzaer();
                        continue;
                    case 18:
                        this.zzcve = zzbxl.readString();
                        continue;
                    case 26:
                        this.version = zzbxl.readString();
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

        public /* synthetic */ zzbxn zzaeG() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public zzb zzaeW() {
            this.zzcvd = 0;
            this.zzcve = "";
            this.version = "";
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* renamed from: zzaeX */
        public zzb clone() {
            try {
                return (zzb) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvd != 0) {
                zzu += zzbxm.zzL(1, this.zzcvd);
            }
            if (this.zzcve != null && !this.zzcve.equals("")) {
                zzu += zzbxm.zzr(2, this.zzcve);
            }
            return (this.version == null || this.version.equals("")) ? zzu : zzu + zzbxm.zzr(3, this.version);
        }
    }

    public static final class zzc extends zzbxn<zzc> implements Cloneable {
        public byte[] zzcvf;
        public String zzcvg;
        public byte[][] zzcvh;
        public boolean zzcvi;

        public zzc() {
            zzaeY();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (!Arrays.equals(this.zzcvf, zzc.zzcvf)) {
                return false;
            }
            if (this.zzcvg == null) {
                if (zzc.zzcvg != null) {
                    return false;
                }
            } else if (!this.zzcvg.equals(zzc.zzcvg)) {
                return false;
            }
            if (!zzbxr.zza(this.zzcvh, zzc.zzcvh) || this.zzcvi != zzc.zzcvi) {
                return false;
            }
            return (this.zzcuA == null || this.zzcuA.isEmpty()) ? zzc.zzcuA == null || zzc.zzcuA.isEmpty() : this.zzcuA.equals(zzc.zzcuA);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcvi ? 1231 : 1237) + (((((this.zzcvg == null ? 0 : this.zzcvg.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzcvf)) * 31)) * 31) + zzbxr.zzb(this.zzcvh)) * 31)) * 31;
            if (this.zzcuA != null && !this.zzcuA.isEmpty()) {
                i = this.zzcuA.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (!Arrays.equals(this.zzcvf, zzbxw.zzcuV)) {
                zzbxm.zzb(1, this.zzcvf);
            }
            if (this.zzcvh != null && this.zzcvh.length > 0) {
                for (byte[] bArr : this.zzcvh) {
                    if (bArr != null) {
                        zzbxm.zzb(2, bArr);
                    }
                }
            }
            if (this.zzcvi) {
                zzbxm.zzg(3, this.zzcvi);
            }
            if (this.zzcvg != null && !this.zzcvg.equals("")) {
                zzbxm.zzq(4, this.zzcvg);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaR */
        public zzc zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        this.zzcvf = zzbxl.readBytes();
                        continue;
                    case 18:
                        int zzb = zzbxw.zzb(zzbxl, 18);
                        int length = this.zzcvh == null ? 0 : this.zzcvh.length;
                        byte[][] bArr = new byte[(zzb + length)][];
                        if (length != 0) {
                            System.arraycopy(this.zzcvh, 0, bArr, 0, length);
                        }
                        while (length < bArr.length - 1) {
                            bArr[length] = zzbxl.readBytes();
                            zzbxl.zzaen();
                            length++;
                        }
                        bArr[length] = zzbxl.readBytes();
                        this.zzcvh = bArr;
                        continue;
                    case MotionEventCompat.AXIS_DISTANCE:
                        this.zzcvi = zzbxl.zzaet();
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_3:
                        this.zzcvg = zzbxl.readString();
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

        public /* synthetic */ zzbxn zzaeG() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public zzc zzaeY() {
            this.zzcvf = zzbxw.zzcuV;
            this.zzcvg = "";
            this.zzcvh = zzbxw.zzcuU;
            this.zzcvi = false;
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* renamed from: zzaeZ */
        public zzc clone() {
            try {
                zzc zzc = (zzc) super.clone();
                if (this.zzcvh != null && this.zzcvh.length > 0) {
                    zzc.zzcvh = (byte[][]) this.zzcvh.clone();
                }
                return zzc;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (!Arrays.equals(this.zzcvf, zzbxw.zzcuV)) {
                zzu += zzbxm.zzc(1, this.zzcvf);
            }
            if (this.zzcvh != null && this.zzcvh.length > 0) {
                int i = 0;
                int i2 = 0;
                for (byte[] bArr : this.zzcvh) {
                    if (bArr != null) {
                        i2++;
                        i += zzbxm.zzai(bArr);
                    }
                }
                zzu = zzu + i + (i2 * 1);
            }
            if (this.zzcvi) {
                zzu += zzbxm.zzh(3, this.zzcvi);
            }
            return (this.zzcvg == null || this.zzcvg.equals("")) ? zzu : zzu + zzbxm.zzr(4, this.zzcvg);
        }
    }

    public static final class zzd extends zzbxn<zzd> implements Cloneable {
        public String tag;
        public boolean zzceh;
        public int[] zzcvA;
        public long zzcvB;
        public zzf zzcvC;
        public long zzcvj;
        public long zzcvk;
        public long zzcvl;
        public int zzcvm;
        public zze[] zzcvn;
        public byte[] zzcvo;
        public zzb zzcvp;
        public byte[] zzcvq;
        public String zzcvr;
        public String zzcvs;
        public zza zzcvt;
        public String zzcvu;
        public long zzcvv;
        public zzc zzcvw;
        public byte[] zzcvx;
        public String zzcvy;
        public int zzcvz;
        public int zzri;

        public zzd() {
            zzafa();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) obj;
            if (this.zzcvj != zzd.zzcvj || this.zzcvk != zzd.zzcvk || this.zzcvl != zzd.zzcvl) {
                return false;
            }
            if (this.tag == null) {
                if (zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzd.tag)) {
                return false;
            }
            if (this.zzcvm != zzd.zzcvm || this.zzri != zzd.zzri || this.zzceh != zzd.zzceh || !zzbxr.equals((Object[]) this.zzcvn, (Object[]) zzd.zzcvn) || !Arrays.equals(this.zzcvo, zzd.zzcvo)) {
                return false;
            }
            if (this.zzcvp == null) {
                if (zzd.zzcvp != null) {
                    return false;
                }
            } else if (!this.zzcvp.equals(zzd.zzcvp)) {
                return false;
            }
            if (!Arrays.equals(this.zzcvq, zzd.zzcvq)) {
                return false;
            }
            if (this.zzcvr == null) {
                if (zzd.zzcvr != null) {
                    return false;
                }
            } else if (!this.zzcvr.equals(zzd.zzcvr)) {
                return false;
            }
            if (this.zzcvs == null) {
                if (zzd.zzcvs != null) {
                    return false;
                }
            } else if (!this.zzcvs.equals(zzd.zzcvs)) {
                return false;
            }
            if (this.zzcvt == null) {
                if (zzd.zzcvt != null) {
                    return false;
                }
            } else if (!this.zzcvt.equals(zzd.zzcvt)) {
                return false;
            }
            if (this.zzcvu == null) {
                if (zzd.zzcvu != null) {
                    return false;
                }
            } else if (!this.zzcvu.equals(zzd.zzcvu)) {
                return false;
            }
            if (this.zzcvv != zzd.zzcvv) {
                return false;
            }
            if (this.zzcvw == null) {
                if (zzd.zzcvw != null) {
                    return false;
                }
            } else if (!this.zzcvw.equals(zzd.zzcvw)) {
                return false;
            }
            if (!Arrays.equals(this.zzcvx, zzd.zzcvx)) {
                return false;
            }
            if (this.zzcvy == null) {
                if (zzd.zzcvy != null) {
                    return false;
                }
            } else if (!this.zzcvy.equals(zzd.zzcvy)) {
                return false;
            }
            if (this.zzcvz != zzd.zzcvz || !zzbxr.equals(this.zzcvA, zzd.zzcvA) || this.zzcvB != zzd.zzcvB) {
                return false;
            }
            if (this.zzcvC == null) {
                if (zzd.zzcvC != null) {
                    return false;
                }
            } else if (!this.zzcvC.equals(zzd.zzcvC)) {
                return false;
            }
            return (this.zzcuA == null || this.zzcuA.isEmpty()) ? zzd.zzcuA == null || zzd.zzcuA.isEmpty() : this.zzcuA.equals(zzd.zzcuA);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzcvC == null ? 0 : this.zzcvC.hashCode()) + (((((((((this.zzcvy == null ? 0 : this.zzcvy.hashCode()) + (((((this.zzcvw == null ? 0 : this.zzcvw.hashCode()) + (((((this.zzcvu == null ? 0 : this.zzcvu.hashCode()) + (((this.zzcvt == null ? 0 : this.zzcvt.hashCode()) + (((this.zzcvs == null ? 0 : this.zzcvs.hashCode()) + (((this.zzcvr == null ? 0 : this.zzcvr.hashCode()) + (((((this.zzcvp == null ? 0 : this.zzcvp.hashCode()) + (((((((this.zzceh ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzcvj ^ (this.zzcvj >>> 32)))) * 31) + ((int) (this.zzcvk ^ (this.zzcvk >>> 32)))) * 31) + ((int) (this.zzcvl ^ (this.zzcvl >>> 32)))) * 31)) * 31) + this.zzcvm) * 31) + this.zzri) * 31)) * 31) + zzbxr.hashCode((Object[]) this.zzcvn)) * 31) + Arrays.hashCode(this.zzcvo)) * 31)) * 31) + Arrays.hashCode(this.zzcvq)) * 31)) * 31)) * 31)) * 31)) * 31) + ((int) (this.zzcvv ^ (this.zzcvv >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.zzcvx)) * 31)) * 31) + this.zzcvz) * 31) + zzbxr.hashCode(this.zzcvA)) * 31) + ((int) (this.zzcvB ^ (this.zzcvB >>> 32)))) * 31)) * 31;
            if (this.zzcuA != null && !this.zzcuA.isEmpty()) {
                i = this.zzcuA.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvj != 0) {
                zzbxm.zzb(1, this.zzcvj);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzbxm.zzq(2, this.tag);
            }
            if (this.zzcvn != null && this.zzcvn.length > 0) {
                for (zze zze : this.zzcvn) {
                    if (zze != null) {
                        zzbxm.zza(3, (zzbxt) zze);
                    }
                }
            }
            if (!Arrays.equals(this.zzcvo, zzbxw.zzcuV)) {
                zzbxm.zzb(4, this.zzcvo);
            }
            if (!Arrays.equals(this.zzcvq, zzbxw.zzcuV)) {
                zzbxm.zzb(6, this.zzcvq);
            }
            if (this.zzcvt != null) {
                zzbxm.zza(7, (zzbxt) this.zzcvt);
            }
            if (this.zzcvr != null && !this.zzcvr.equals("")) {
                zzbxm.zzq(8, this.zzcvr);
            }
            if (this.zzcvp != null) {
                zzbxm.zza(9, (zzbxt) this.zzcvp);
            }
            if (this.zzceh) {
                zzbxm.zzg(10, this.zzceh);
            }
            if (this.zzcvm != 0) {
                zzbxm.zzJ(11, this.zzcvm);
            }
            if (this.zzri != 0) {
                zzbxm.zzJ(12, this.zzri);
            }
            if (this.zzcvs != null && !this.zzcvs.equals("")) {
                zzbxm.zzq(13, this.zzcvs);
            }
            if (this.zzcvu != null && !this.zzcvu.equals("")) {
                zzbxm.zzq(14, this.zzcvu);
            }
            if (this.zzcvv != 180000) {
                zzbxm.zzd(15, this.zzcvv);
            }
            if (this.zzcvw != null) {
                zzbxm.zza(16, (zzbxt) this.zzcvw);
            }
            if (this.zzcvk != 0) {
                zzbxm.zzb(17, this.zzcvk);
            }
            if (!Arrays.equals(this.zzcvx, zzbxw.zzcuV)) {
                zzbxm.zzb(18, this.zzcvx);
            }
            if (this.zzcvz != 0) {
                zzbxm.zzJ(19, this.zzcvz);
            }
            if (this.zzcvA != null && this.zzcvA.length > 0) {
                for (int zzJ : this.zzcvA) {
                    zzbxm.zzJ(20, zzJ);
                }
            }
            if (this.zzcvl != 0) {
                zzbxm.zzb(21, this.zzcvl);
            }
            if (this.zzcvB != 0) {
                zzbxm.zzb(22, this.zzcvB);
            }
            if (this.zzcvC != null) {
                zzbxm.zza(23, (zzbxt) this.zzcvC);
            }
            if (this.zzcvy != null && !this.zzcvy.equals("")) {
                zzbxm.zzq(24, this.zzcvy);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaS */
        public zzd zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 8:
                        this.zzcvj = zzbxl.zzaeq();
                        continue;
                    case 18:
                        this.tag = zzbxl.readString();
                        continue;
                    case 26:
                        int zzb = zzbxw.zzb(zzbxl, 26);
                        int length = this.zzcvn == null ? 0 : this.zzcvn.length;
                        zze[] zzeArr = new zze[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzcvn, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zzbxl.zza(zzeArr[length]);
                            zzbxl.zzaen();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zzbxl.zza(zzeArr[length]);
                        this.zzcvn = zzeArr;
                        continue;
                    case MotionEventCompat.AXIS_GENERIC_3:
                        this.zzcvo = zzbxl.readBytes();
                        continue;
                    case AdSize.PORTRAIT_AD_HEIGHT:
                        this.zzcvq = zzbxl.readBytes();
                        continue;
                    case 58:
                        if (this.zzcvt == null) {
                            this.zzcvt = new zza();
                        }
                        zzbxl.zza(this.zzcvt);
                        continue;
                    case 66:
                        this.zzcvr = zzbxl.readString();
                        continue;
                    case 74:
                        if (this.zzcvp == null) {
                            this.zzcvp = new zzb();
                        }
                        zzbxl.zza(this.zzcvp);
                        continue;
                    case 80:
                        this.zzceh = zzbxl.zzaet();
                        continue;
                    case 88:
                        this.zzcvm = zzbxl.zzaer();
                        continue;
                    case 96:
                        this.zzri = zzbxl.zzaer();
                        continue;
                    case 106:
                        this.zzcvs = zzbxl.readString();
                        continue;
                    case 114:
                        this.zzcvu = zzbxl.readString();
                        continue;
                    case 120:
                        this.zzcvv = zzbxl.zzaev();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        if (this.zzcvw == null) {
                            this.zzcvw = new zzc();
                        }
                        zzbxl.zza(this.zzcvw);
                        continue;
                    case 136:
                        this.zzcvk = zzbxl.zzaeq();
                        continue;
                    case 146:
                        this.zzcvx = zzbxl.readBytes();
                        continue;
                    case 152:
                        int zzaer = zzbxl.zzaer();
                        switch (zzaer) {
                            case 0:
                            case 1:
                            case 2:
                                this.zzcvz = zzaer;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        int zzb2 = zzbxw.zzb(zzbxl, 160);
                        int length2 = this.zzcvA == null ? 0 : this.zzcvA.length;
                        int[] iArr = new int[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzcvA, 0, iArr, 0, length2);
                        }
                        while (length2 < iArr.length - 1) {
                            iArr[length2] = zzbxl.zzaer();
                            zzbxl.zzaen();
                            length2++;
                        }
                        iArr[length2] = zzbxl.zzaer();
                        this.zzcvA = iArr;
                        continue;
                    case 162:
                        int zzqZ = zzbxl.zzqZ(zzbxl.zzaew());
                        int position = zzbxl.getPosition();
                        int i = 0;
                        while (zzbxl.zzaeB() > 0) {
                            zzbxl.zzaer();
                            i++;
                        }
                        zzbxl.zzrb(position);
                        int length3 = this.zzcvA == null ? 0 : this.zzcvA.length;
                        int[] iArr2 = new int[(i + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzcvA, 0, iArr2, 0, length3);
                        }
                        while (length3 < iArr2.length) {
                            iArr2[length3] = zzbxl.zzaer();
                            length3++;
                        }
                        this.zzcvA = iArr2;
                        zzbxl.zzra(zzqZ);
                        continue;
                    case 168:
                        this.zzcvl = zzbxl.zzaeq();
                        continue;
                    case 176:
                        this.zzcvB = zzbxl.zzaeq();
                        continue;
                    case 186:
                        if (this.zzcvC == null) {
                            this.zzcvC = new zzf();
                        }
                        zzbxl.zza(this.zzcvC);
                        continue;
                    case 194:
                        this.zzcvy = zzbxl.readString();
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

        public /* synthetic */ zzbxn zzaeG() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public zzd zzafa() {
            this.zzcvj = 0;
            this.zzcvk = 0;
            this.zzcvl = 0;
            this.tag = "";
            this.zzcvm = 0;
            this.zzri = 0;
            this.zzceh = false;
            this.zzcvn = zze.zzafc();
            this.zzcvo = zzbxw.zzcuV;
            this.zzcvp = null;
            this.zzcvq = zzbxw.zzcuV;
            this.zzcvr = "";
            this.zzcvs = "";
            this.zzcvt = null;
            this.zzcvu = "";
            this.zzcvv = 180000;
            this.zzcvw = null;
            this.zzcvx = zzbxw.zzcuV;
            this.zzcvy = "";
            this.zzcvz = 0;
            this.zzcvA = zzbxw.zzcuO;
            this.zzcvB = 0;
            this.zzcvC = null;
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* renamed from: zzafb */
        public zzd clone() {
            try {
                zzd zzd = (zzd) super.clone();
                if (this.zzcvn != null && this.zzcvn.length > 0) {
                    zzd.zzcvn = new zze[this.zzcvn.length];
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= this.zzcvn.length) {
                            break;
                        }
                        if (this.zzcvn[i2] != null) {
                            zzd.zzcvn[i2] = (zze) this.zzcvn[i2].clone();
                        }
                        i = i2 + 1;
                    }
                }
                if (this.zzcvp != null) {
                    zzd.zzcvp = (zzb) this.zzcvp.clone();
                }
                if (this.zzcvt != null) {
                    zzd.zzcvt = (zza) this.zzcvt.clone();
                }
                if (this.zzcvw != null) {
                    zzd.zzcvw = (zzc) this.zzcvw.clone();
                }
                if (this.zzcvA != null && this.zzcvA.length > 0) {
                    zzd.zzcvA = (int[]) this.zzcvA.clone();
                }
                if (this.zzcvC != null) {
                    zzd.zzcvC = (zzf) this.zzcvC.clone();
                }
                return zzd;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvj != 0) {
                zzu += zzbxm.zzf(1, this.zzcvj);
            }
            if (this.tag != null && !this.tag.equals("")) {
                zzu += zzbxm.zzr(2, this.tag);
            }
            if (this.zzcvn != null && this.zzcvn.length > 0) {
                int i = zzu;
                for (zze zze : this.zzcvn) {
                    if (zze != null) {
                        i += zzbxm.zzc(3, (zzbxt) zze);
                    }
                }
                zzu = i;
            }
            if (!Arrays.equals(this.zzcvo, zzbxw.zzcuV)) {
                zzu += zzbxm.zzc(4, this.zzcvo);
            }
            if (!Arrays.equals(this.zzcvq, zzbxw.zzcuV)) {
                zzu += zzbxm.zzc(6, this.zzcvq);
            }
            if (this.zzcvt != null) {
                zzu += zzbxm.zzc(7, (zzbxt) this.zzcvt);
            }
            if (this.zzcvr != null && !this.zzcvr.equals("")) {
                zzu += zzbxm.zzr(8, this.zzcvr);
            }
            if (this.zzcvp != null) {
                zzu += zzbxm.zzc(9, (zzbxt) this.zzcvp);
            }
            if (this.zzceh) {
                zzu += zzbxm.zzh(10, this.zzceh);
            }
            if (this.zzcvm != 0) {
                zzu += zzbxm.zzL(11, this.zzcvm);
            }
            if (this.zzri != 0) {
                zzu += zzbxm.zzL(12, this.zzri);
            }
            if (this.zzcvs != null && !this.zzcvs.equals("")) {
                zzu += zzbxm.zzr(13, this.zzcvs);
            }
            if (this.zzcvu != null && !this.zzcvu.equals("")) {
                zzu += zzbxm.zzr(14, this.zzcvu);
            }
            if (this.zzcvv != 180000) {
                zzu += zzbxm.zzh(15, this.zzcvv);
            }
            if (this.zzcvw != null) {
                zzu += zzbxm.zzc(16, (zzbxt) this.zzcvw);
            }
            if (this.zzcvk != 0) {
                zzu += zzbxm.zzf(17, this.zzcvk);
            }
            if (!Arrays.equals(this.zzcvx, zzbxw.zzcuV)) {
                zzu += zzbxm.zzc(18, this.zzcvx);
            }
            if (this.zzcvz != 0) {
                zzu += zzbxm.zzL(19, this.zzcvz);
            }
            if (this.zzcvA != null && this.zzcvA.length > 0) {
                int i2 = 0;
                for (int zzrf : this.zzcvA) {
                    i2 += zzbxm.zzrf(zzrf);
                }
                zzu = zzu + i2 + (this.zzcvA.length * 2);
            }
            if (this.zzcvl != 0) {
                zzu += zzbxm.zzf(21, this.zzcvl);
            }
            if (this.zzcvB != 0) {
                zzu += zzbxm.zzf(22, this.zzcvB);
            }
            if (this.zzcvC != null) {
                zzu += zzbxm.zzc(23, (zzbxt) this.zzcvC);
            }
            return (this.zzcvy == null || this.zzcvy.equals("")) ? zzu : zzu + zzbxm.zzr(24, this.zzcvy);
        }
    }

    public static final class zze extends zzbxn<zze> implements Cloneable {
        private static volatile zze[] zzcvD;
        public String value;
        public String zzaB;

        public zze() {
            zzafd();
        }

        public static zze[] zzafc() {
            if (zzcvD == null) {
                synchronized (zzbxr.zzcuI) {
                    if (zzcvD == null) {
                        zzcvD = new zze[0];
                    }
                }
            }
            return zzcvD;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze zze = (zze) obj;
            if (this.zzaB == null) {
                if (zze.zzaB != null) {
                    return false;
                }
            } else if (!this.zzaB.equals(zze.zzaB)) {
                return false;
            }
            if (this.value == null) {
                if (zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zze.value)) {
                return false;
            }
            return (this.zzcuA == null || this.zzcuA.isEmpty()) ? zze.zzcuA == null || zze.zzcuA.isEmpty() : this.zzcuA.equals(zze.zzcuA);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzaB == null ? 0 : this.zzaB.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzcuA != null && !this.zzcuA.isEmpty()) {
                i = this.zzcuA.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzbxm.zzq(1, this.zzaB);
            }
            if (this.value != null && !this.value.equals("")) {
                zzbxm.zzq(2, this.value);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaT */
        public zze zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 10:
                        this.zzaB = zzbxl.readString();
                        continue;
                    case 18:
                        this.value = zzbxl.readString();
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

        public /* synthetic */ zzbxn zzaeG() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public zze zzafd() {
            this.zzaB = "";
            this.value = "";
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* renamed from: zzafe */
        public zze clone() {
            try {
                return (zze) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzaB != null && !this.zzaB.equals("")) {
                zzu += zzbxm.zzr(1, this.zzaB);
            }
            return (this.value == null || this.value.equals("")) ? zzu : zzu + zzbxm.zzr(2, this.value);
        }
    }

    public static final class zzf extends zzbxn<zzf> implements Cloneable {
        public int zzcvE;
        public int zzcvF;

        public zzf() {
            zzaff();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) obj;
            if (this.zzcvE == zzf.zzcvE && this.zzcvF == zzf.zzcvF) {
                return (this.zzcuA == null || this.zzcuA.isEmpty()) ? zzf.zzcuA == null || zzf.zzcuA.isEmpty() : this.zzcuA.equals(zzf.zzcuA);
            }
            return false;
        }

        public int hashCode() {
            return ((this.zzcuA == null || this.zzcuA.isEmpty()) ? 0 : this.zzcuA.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.zzcvE) * 31) + this.zzcvF) * 31);
        }

        public void zza(zzbxm zzbxm) throws IOException {
            if (this.zzcvE != -1) {
                zzbxm.zzJ(1, this.zzcvE);
            }
            if (this.zzcvF != 0) {
                zzbxm.zzJ(2, this.zzcvF);
            }
            super.zza(zzbxm);
        }

        /* renamed from: zzaU */
        public zzf zzb(zzbxl zzbxl) throws IOException {
            while (true) {
                int zzaen = zzbxl.zzaen();
                switch (zzaen) {
                    case 0:
                        break;
                    case 8:
                        int zzaer = zzbxl.zzaer();
                        switch (zzaer) {
                            case -1:
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case MotionEventCompat.AXIS_RX:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                                this.zzcvE = zzaer;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        int zzaer2 = zzbxl.zzaer();
                        switch (zzaer2) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case MotionEventCompat.AXIS_RX:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 100:
                                this.zzcvF = zzaer2;
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

        public /* synthetic */ zzbxn zzaeG() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public zzf zzaff() {
            this.zzcvE = -1;
            this.zzcvF = 0;
            this.zzcuA = null;
            this.zzcuJ = -1;
            return this;
        }

        /* renamed from: zzafg */
        public zzf clone() {
            try {
                return (zzf) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: protected */
        public int zzu() {
            int zzu = super.zzu();
            if (this.zzcvE != -1) {
                zzu += zzbxm.zzL(1, this.zzcvE);
            }
            return this.zzcvF != 0 ? zzu + zzbxm.zzL(2, this.zzcvF) : zzu;
        }
    }
}
