package com.google.android.gms.internal;

import android.os.RemoteException;

public abstract class zzaqa<T> {
    private final int zzAW;
    private final String zzAX;
    private final T zzAY;

    public static class zza extends zzaqa<Boolean> {
        public zza(int i, String str, Boolean bool) {
            super(i, str, bool);
        }

        /* renamed from: zzb */
        public Boolean zza(zzaqd zzaqd) {
            try {
                return Boolean.valueOf(zzaqd.getBooleanFlagValue(getKey(), ((Boolean) zzfr()).booleanValue(), getSource()));
            } catch (RemoteException e) {
                return (Boolean) zzfr();
            }
        }
    }

    public static class zzb extends zzaqa<Integer> {
        public zzb(int i, String str, Integer num) {
            super(i, str, num);
        }

        /* renamed from: zzc */
        public Integer zza(zzaqd zzaqd) {
            try {
                return Integer.valueOf(zzaqd.getIntFlagValue(getKey(), ((Integer) zzfr()).intValue(), getSource()));
            } catch (RemoteException e) {
                return (Integer) zzfr();
            }
        }
    }

    public static class zzc extends zzaqa<Long> {
        public zzc(int i, String str, Long l) {
            super(i, str, l);
        }

        /* renamed from: zzd */
        public Long zza(zzaqd zzaqd) {
            try {
                return Long.valueOf(zzaqd.getLongFlagValue(getKey(), ((Long) zzfr()).longValue(), getSource()));
            } catch (RemoteException e) {
                return (Long) zzfr();
            }
        }
    }

    public static class zzd extends zzaqa<String> {
        public zzd(int i, String str, String str2) {
            super(i, str, str2);
        }

        /* renamed from: zze */
        public String zza(zzaqd zzaqd) {
            try {
                return zzaqd.getStringFlagValue(getKey(), (String) zzfr(), getSource());
            } catch (RemoteException e) {
                return (String) zzfr();
            }
        }
    }

    private zzaqa(int i, String str, T t) {
        this.zzAW = i;
        this.zzAX = str;
        this.zzAY = t;
        zzaqe.zzDD().zza(this);
    }

    public static zza zzb(int i, String str, Boolean bool) {
        return new zza(i, str, bool);
    }

    public static zzb zzb(int i, String str, int i2) {
        return new zzb(i, str, Integer.valueOf(i2));
    }

    public static zzc zzb(int i, String str, long j) {
        return new zzc(i, str, Long.valueOf(j));
    }

    public static zzd zzc(int i, String str, String str2) {
        return new zzd(i, str, str2);
    }

    public T get() {
        return zzaqe.zzDE().zzb(this);
    }

    public String getKey() {
        return this.zzAX;
    }

    public int getSource() {
        return this.zzAW;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(zzaqd zzaqd);

    public T zzfr() {
        return this.zzAY;
    }
}
