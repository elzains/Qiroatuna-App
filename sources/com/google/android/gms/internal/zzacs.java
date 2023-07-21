package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class zzacs {

    public static class zza<I, O> extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final zzacu CREATOR = new zzacu();
        protected final int zzaGX;
        protected final boolean zzaGY;
        protected final int zzaGZ;
        protected final boolean zzaHa;
        protected final String zzaHb;
        protected final int zzaHc;
        protected final Class<? extends zzacs> zzaHd;
        protected final String zzaHe;
        private zzacw zzaHf;
        /* access modifiers changed from: private */
        public zzb<I, O> zzaHg;
        private final int zzaiI;

        zza(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, zzacn zzacn) {
            this.zzaiI = i;
            this.zzaGX = i2;
            this.zzaGY = z;
            this.zzaGZ = i3;
            this.zzaHa = z2;
            this.zzaHb = str;
            this.zzaHc = i4;
            if (str2 == null) {
                this.zzaHd = null;
                this.zzaHe = null;
            } else {
                this.zzaHd = zzacz.class;
                this.zzaHe = str2;
            }
            if (zzacn == null) {
                this.zzaHg = null;
            } else {
                this.zzaHg = zzacn.zzyp();
            }
        }

        protected zza(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends zzacs> cls, zzb<I, O> zzb) {
            this.zzaiI = 1;
            this.zzaGX = i;
            this.zzaGY = z;
            this.zzaGZ = i2;
            this.zzaHa = z2;
            this.zzaHb = str;
            this.zzaHc = i3;
            this.zzaHd = cls;
            if (cls == null) {
                this.zzaHe = null;
            } else {
                this.zzaHe = cls.getCanonicalName();
            }
            this.zzaHg = zzb;
        }

        public static zza zza(String str, int i, zzb<?, ?> zzb, boolean z) {
            return new zza(7, z, 0, false, str, i, (Class<? extends zzacs>) null, zzb);
        }

        public static <T extends zzacs> zza<T, T> zza(String str, int i, Class<T> cls) {
            return new zza<>(11, false, 11, false, str, i, cls, (zzb) null);
        }

        public static <T extends zzacs> zza<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new zza<>(11, true, 11, true, str, i, cls, (zzb) null);
        }

        public static zza<Integer, Integer> zzk(String str, int i) {
            return new zza<>(0, false, 0, false, str, i, (Class<? extends zzacs>) null, (zzb) null);
        }

        public static zza<Boolean, Boolean> zzl(String str, int i) {
            return new zza<>(6, false, 6, false, str, i, (Class<? extends zzacs>) null, (zzb) null);
        }

        public static zza<String, String> zzm(String str, int i) {
            return new zza<>(7, false, 7, false, str, i, (Class<? extends zzacs>) null, (zzb) null);
        }

        public I convertBack(O o) {
            return this.zzaHg.convertBack(o);
        }

        public int getVersionCode() {
            return this.zzaiI;
        }

        public String toString() {
            zzaa.zza zzg = zzaa.zzv(this).zzg("versionCode", Integer.valueOf(this.zzaiI)).zzg("typeIn", Integer.valueOf(this.zzaGX)).zzg("typeInArray", Boolean.valueOf(this.zzaGY)).zzg("typeOut", Integer.valueOf(this.zzaGZ)).zzg("typeOutArray", Boolean.valueOf(this.zzaHa)).zzg("outputFieldName", this.zzaHb).zzg("safeParcelFieldId", Integer.valueOf(this.zzaHc)).zzg("concreteTypeName", zzyz());
            Class<? extends zzacs> zzyy = zzyy();
            if (zzyy != null) {
                zzg.zzg("concreteType.class", zzyy.getCanonicalName());
            }
            if (this.zzaHg != null) {
                zzg.zzg("converterName", this.zzaHg.getClass().getCanonicalName());
            }
            return zzg.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacu.zza(this, parcel, i);
        }

        public void zza(zzacw zzacw) {
            this.zzaHf = zzacw;
        }

        public boolean zzyA() {
            return this.zzaHg != null;
        }

        /* access modifiers changed from: package-private */
        public zzacn zzyB() {
            if (this.zzaHg == null) {
                return null;
            }
            return zzacn.zza(this.zzaHg);
        }

        public Map<String, zza<?, ?>> zzyC() {
            zzac.zzw(this.zzaHe);
            zzac.zzw(this.zzaHf);
            return this.zzaHf.zzdw(this.zzaHe);
        }

        public int zzys() {
            return this.zzaGX;
        }

        public boolean zzyt() {
            return this.zzaGY;
        }

        public int zzyu() {
            return this.zzaGZ;
        }

        public boolean zzyv() {
            return this.zzaHa;
        }

        public String zzyw() {
            return this.zzaHb;
        }

        public int zzyx() {
            return this.zzaHc;
        }

        public Class<? extends zzacs> zzyy() {
            return this.zzaHd;
        }

        /* access modifiers changed from: package-private */
        public String zzyz() {
            if (this.zzaHe == null) {
                return null;
            }
            return this.zzaHe;
        }
    }

    public interface zzb<I, O> {
        I convertBack(O o);
    }

    private void zza(StringBuilder sb, zza zza2, Object obj) {
        if (zza2.zzys() == 11) {
            sb.append(((zzacs) zza2.zzyy().cast(obj)).toString());
        } else if (zza2.zzys() == 7) {
            sb.append("\"");
            sb.append(zzq.zzdy((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private void zza(StringBuilder sb, zza zza2, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, zza2, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map<String, zza<?, ?>> zzyr = zzyr();
        StringBuilder sb = new StringBuilder(100);
        for (String next : zzyr.keySet()) {
            zza zza2 = zzyr.get(next);
            if (zza(zza2)) {
                Object zza3 = zza(zza2, zzb(zza2));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(next).append("\":");
                if (zza3 != null) {
                    switch (zza2.zzyu()) {
                        case 8:
                            sb.append("\"").append(zzc.zzq((byte[]) zza3)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(zzc.zzr((byte[]) zza3)).append("\"");
                            break;
                        case 10:
                            zzr.zza(sb, (HashMap) zza3);
                            break;
                        default:
                            if (!zza2.zzyt()) {
                                zza(sb, zza2, zza3);
                                break;
                            } else {
                                zza(sb, zza2, (ArrayList<Object>) (ArrayList) zza3);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public <O, I> I zza(zza<I, O> zza2, Object obj) {
        return zza2.zzaHg != null ? zza2.convertBack(obj) : obj;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zza zza2) {
        return zza2.zzyu() == 11 ? zza2.zzyv() ? zzdv(zza2.zzyw()) : zzdu(zza2.zzyw()) : zzdt(zza2.zzyw());
    }

    /* access modifiers changed from: protected */
    public Object zzb(zza zza2) {
        String zzyw = zza2.zzyw();
        if (zza2.zzyy() == null) {
            return zzds(zza2.zzyw());
        }
        zzds(zza2.zzyw());
        zzac.zza(true, "Concrete field shouldn't be value object: %s", zza2.zzyw());
        zza2.zzyv();
        try {
            char upperCase = Character.toUpperCase(zzyw.charAt(0));
            String valueOf = String.valueOf(zzyw.substring(1));
            return getClass().getMethod(new StringBuilder(String.valueOf(valueOf).length() + 4).append("get").append(upperCase).append(valueOf).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zzds(String str);

    /* access modifiers changed from: protected */
    public abstract boolean zzdt(String str);

    /* access modifiers changed from: protected */
    public boolean zzdu(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public boolean zzdv(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public abstract Map<String, zza<?, ?>> zzyr();
}
