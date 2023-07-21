package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzaa;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class zzayz extends com.google.android.gms.common.internal.safeparcel.zza implements Comparable<zzayz> {
    public static final Parcelable.Creator<zzayz> CREATOR = new zzaza();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zza zzbBK = new zza();

    /* renamed from: name  reason: collision with root package name */
    public final String f18name;
    final String zzaGV;
    final long zzbBG;
    final byte[] zzbBH;
    public final int zzbBI;
    public final int zzbBJ;
    final boolean zzbhm;
    final double zzbho;

    public static class zza implements Comparator<zzayz> {
        /* renamed from: zza */
        public int compare(zzayz zzayz, zzayz zzayz2) {
            return zzayz.zzbBJ == zzayz2.zzbBJ ? zzayz.f18name.compareTo(zzayz2.f18name) : zzayz.zzbBJ - zzayz2.zzbBJ;
        }
    }

    public zzayz(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.f18name = str;
        this.zzbBG = j;
        this.zzbhm = z;
        this.zzbho = d;
        this.zzaGV = str2;
        this.zzbBH = bArr;
        this.zzbBI = i;
        this.zzbBJ = i2;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzayz)) {
            return false;
        }
        zzayz zzayz = (zzayz) obj;
        if (!zzaa.equal(this.f18name, zzayz.f18name) || this.zzbBI != zzayz.zzbBI || this.zzbBJ != zzayz.zzbBJ) {
            return false;
        }
        switch (this.zzbBI) {
            case 1:
                return this.zzbBG == zzayz.zzbBG;
            case 2:
                return this.zzbhm == zzayz.zzbhm;
            case 3:
                return this.zzbho == zzayz.zzbho;
            case 4:
                return zzaa.equal(this.zzaGV, zzayz.zzaGV);
            case 5:
                return Arrays.equals(this.zzbBH, zzayz.zzbBH);
            default:
                throw new AssertionError(new StringBuilder(31).append("Invalid enum value: ").append(this.zzbBI).toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        zza(sb);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaza.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzayz zzayz) {
        int compareTo = this.f18name.compareTo(zzayz.f18name);
        if (compareTo != 0) {
            return compareTo;
        }
        int compare = compare(this.zzbBI, zzayz.zzbBI);
        if (compare != 0) {
            return compare;
        }
        switch (this.zzbBI) {
            case 1:
                return compare(this.zzbBG, zzayz.zzbBG);
            case 2:
                return compare(this.zzbhm, zzayz.zzbhm);
            case 3:
                return Double.compare(this.zzbho, zzayz.zzbho);
            case 4:
                return compare(this.zzaGV, zzayz.zzaGV);
            case 5:
                if (this.zzbBH == zzayz.zzbBH) {
                    return 0;
                }
                if (this.zzbBH == null) {
                    return -1;
                }
                if (zzayz.zzbBH == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.zzbBH.length, zzayz.zzbBH.length); i++) {
                    int compare2 = compare(this.zzbBH[i], zzayz.zzbBH[i]);
                    if (compare2 != 0) {
                        return compare2;
                    }
                }
                return compare(this.zzbBH.length, zzayz.zzbBH.length);
            default:
                throw new AssertionError(new StringBuilder(31).append("Invalid enum value: ").append(this.zzbBI).toString());
        }
    }

    public String zza(StringBuilder sb) {
        sb.append("Flag(");
        sb.append(this.f18name);
        sb.append(", ");
        switch (this.zzbBI) {
            case 1:
                sb.append(this.zzbBG);
                break;
            case 2:
                sb.append(this.zzbhm);
                break;
            case 3:
                sb.append(this.zzbho);
                break;
            case 4:
                sb.append("'");
                sb.append(this.zzaGV);
                sb.append("'");
                break;
            case 5:
                if (this.zzbBH != null) {
                    sb.append("'");
                    sb.append(new String(this.zzbBH, UTF_8));
                    sb.append("'");
                    break;
                } else {
                    sb.append("null");
                    break;
                }
            default:
                String str = this.f18name;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.zzbBI).toString());
        }
        sb.append(", ");
        sb.append(this.zzbBI);
        sb.append(", ");
        sb.append(this.zzbBJ);
        sb.append(")");
        return sb.toString();
    }
}
