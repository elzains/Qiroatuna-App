package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import com.google.android.gms.internal.zzacs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class zzacz extends zzact {
    public static final Parcelable.Creator<zzacz> CREATOR = new zzada();
    private final String mClassName;
    private final zzacw zzaHf;
    private final Parcel zzaHm;
    private final int zzaHn = 2;
    private int zzaHo;
    private int zzaHp;
    private final int zzaiI;

    zzacz(int i, Parcel parcel, zzacw zzacw) {
        this.zzaiI = i;
        this.zzaHm = (Parcel) zzac.zzw(parcel);
        this.zzaHf = zzacw;
        if (this.zzaHf == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zzaHf.zzyF();
        }
        this.zzaHo = 2;
    }

    private static SparseArray<Map.Entry<String, zzacs.zza<?, ?>>> zzY(Map<String, zzacs.zza<?, ?>> map) {
        SparseArray<Map.Entry<String, zzacs.zza<?, ?>>> sparseArray = new SparseArray<>();
        for (Map.Entry next : map.entrySet()) {
            sparseArray.put(((zzacs.zza) next.getValue()).zzyx(), next);
        }
        return sparseArray;
    }

    private void zza(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(zzq.zzdy(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzc.zzq((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzc.zzr((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                zzr.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException(new StringBuilder(26).append("Unknown type = ").append(i).toString());
        }
    }

    private void zza(StringBuilder sb, zzacs.zza<?, ?> zza, Parcel parcel, int i) {
        switch (zza.zzyu()) {
            case 0:
                zzb(sb, zza, (Object) zza(zza, Integer.valueOf(zzb.zzg(parcel, i))));
                return;
            case 1:
                zzb(sb, zza, (Object) zza(zza, zzb.zzk(parcel, i)));
                return;
            case 2:
                zzb(sb, zza, (Object) zza(zza, Long.valueOf(zzb.zzi(parcel, i))));
                return;
            case 3:
                zzb(sb, zza, (Object) zza(zza, Float.valueOf(zzb.zzl(parcel, i))));
                return;
            case 4:
                zzb(sb, zza, (Object) zza(zza, Double.valueOf(zzb.zzn(parcel, i))));
                return;
            case 5:
                zzb(sb, zza, (Object) zza(zza, zzb.zzp(parcel, i)));
                return;
            case 6:
                zzb(sb, zza, (Object) zza(zza, Boolean.valueOf(zzb.zzc(parcel, i))));
                return;
            case 7:
                zzb(sb, zza, (Object) zza(zza, zzb.zzq(parcel, i)));
                return;
            case 8:
            case 9:
                zzb(sb, zza, (Object) zza(zza, zzb.zzt(parcel, i)));
                return;
            case 10:
                zzb(sb, zza, (Object) zza(zza, zzr(zzb.zzs(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException(new StringBuilder(36).append("Unknown field out type = ").append(zza.zzyu()).toString());
        }
    }

    private void zza(StringBuilder sb, String str, zzacs.zza<?, ?> zza, Parcel parcel, int i) {
        sb.append("\"").append(str).append("\":");
        if (zza.zzyA()) {
            zza(sb, zza, parcel, i);
        } else {
            zzb(sb, zza, parcel, i);
        }
    }

    private void zza(StringBuilder sb, Map<String, zzacs.zza<?, ?>> map, Parcel parcel) {
        SparseArray<Map.Entry<String, zzacs.zza<?, ?>>> zzY = zzY(map);
        sb.append('{');
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            Map.Entry entry = zzY.get(zzb.zzdc(zzaX));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                zza(sb, (String) entry.getKey(), (zzacs.zza) entry.getValue(), parcel, zzaX);
                z = true;
            }
        }
        if (parcel.dataPosition() != zzaY) {
            throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
        }
        sb.append('}');
    }

    private void zzb(StringBuilder sb, zzacs.zza<?, ?> zza, Parcel parcel, int i) {
        if (zza.zzyv()) {
            sb.append("[");
            switch (zza.zzyu()) {
                case 0:
                    com.google.android.gms.common.util.zzb.zza(sb, zzb.zzw(parcel, i));
                    break;
                case 1:
                    com.google.android.gms.common.util.zzb.zza(sb, (T[]) zzb.zzy(parcel, i));
                    break;
                case 2:
                    com.google.android.gms.common.util.zzb.zza(sb, zzb.zzx(parcel, i));
                    break;
                case 3:
                    com.google.android.gms.common.util.zzb.zza(sb, zzb.zzz(parcel, i));
                    break;
                case 4:
                    com.google.android.gms.common.util.zzb.zza(sb, zzb.zzA(parcel, i));
                    break;
                case 5:
                    com.google.android.gms.common.util.zzb.zza(sb, (T[]) zzb.zzB(parcel, i));
                    break;
                case 6:
                    com.google.android.gms.common.util.zzb.zza(sb, zzb.zzv(parcel, i));
                    break;
                case 7:
                    com.google.android.gms.common.util.zzb.zza(sb, zzb.zzC(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] zzG = zzb.zzG(parcel, i);
                    int length = zzG.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        zzG[i2].setDataPosition(0);
                        zza(sb, zza.zzyC(), zzG[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (zza.zzyu()) {
            case 0:
                sb.append(zzb.zzg(parcel, i));
                return;
            case 1:
                sb.append(zzb.zzk(parcel, i));
                return;
            case 2:
                sb.append(zzb.zzi(parcel, i));
                return;
            case 3:
                sb.append(zzb.zzl(parcel, i));
                return;
            case 4:
                sb.append(zzb.zzn(parcel, i));
                return;
            case 5:
                sb.append(zzb.zzp(parcel, i));
                return;
            case 6:
                sb.append(zzb.zzc(parcel, i));
                return;
            case 7:
                sb.append("\"").append(zzq.zzdy(zzb.zzq(parcel, i))).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzc.zzq(zzb.zzt(parcel, i))).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzc.zzr(zzb.zzt(parcel, i)));
                sb.append("\"");
                return;
            case 10:
                Bundle zzs = zzb.zzs(parcel, i);
                Set<String> keySet = zzs.keySet();
                keySet.size();
                sb.append("{");
                boolean z = true;
                for (String str : keySet) {
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"").append(str).append("\"");
                    sb.append(":");
                    sb.append("\"").append(zzq.zzdy(zzs.getString(str))).append("\"");
                    z = false;
                }
                sb.append("}");
                return;
            case 11:
                Parcel zzF = zzb.zzF(parcel, i);
                zzF.setDataPosition(0);
                zza(sb, zza.zzyC(), zzF);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void zzb(StringBuilder sb, zzacs.zza<?, ?> zza, Object obj) {
        if (zza.zzyt()) {
            zzb(sb, zza, (ArrayList<?>) (ArrayList) obj);
        } else {
            zza(sb, zza.zzys(), obj);
        }
    }

    private void zzb(StringBuilder sb, zzacs.zza<?, ?> zza, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            zza(sb, zza.zzys(), (Object) arrayList.get(i));
        }
        sb.append("]");
    }

    public static HashMap<String, String> zzr(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int getVersionCode() {
        return this.zzaiI;
    }

    public String toString() {
        zzac.zzb(this.zzaHf, (Object) "Cannot convert to JSON on client side.");
        Parcel zzyH = zzyH();
        zzyH.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.zzaHf.zzdw(this.mClassName), zzyH);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzada.zza(this, parcel, i);
    }

    public Object zzds(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public boolean zzdt(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Parcel zzyH() {
        switch (this.zzaHo) {
            case 0:
                this.zzaHp = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(this.zzaHm);
                com.google.android.gms.common.internal.safeparcel.zzc.zzJ(this.zzaHm, this.zzaHp);
                this.zzaHo = 2;
                break;
            case 1:
                com.google.android.gms.common.internal.safeparcel.zzc.zzJ(this.zzaHm, this.zzaHp);
                this.zzaHo = 2;
                break;
        }
        return this.zzaHm;
    }

    /* access modifiers changed from: package-private */
    public zzacw zzyI() {
        switch (this.zzaHn) {
            case 0:
                return null;
            case 1:
                return this.zzaHf;
            case 2:
                return this.zzaHf;
            default:
                throw new IllegalStateException(new StringBuilder(34).append("Invalid creation type: ").append(this.zzaHn).toString());
        }
    }

    public Map<String, zzacs.zza<?, ?>> zzyr() {
        if (this.zzaHf == null) {
            return null;
        }
        return this.zzaHf.zzdw(this.mClassName);
    }
}
