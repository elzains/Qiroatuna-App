package com.google.android.gms.internal;

public abstract class zzaca<T> {
    private static String READ_PERMISSION = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static zza zzaDC = null;
    private static int zzaDD = 0;
    private static final Object zztX = new Object();
    protected final String zzAX;
    protected final T zzAY;
    private T zzaDE = null;

    private interface zza {
    }

    protected zzaca(String str, T t) {
        this.zzAX = str;
        this.zzAY = t;
    }

    public static zzaca<String> zzB(String str, String str2) {
        return new zzaca<String>(str, str2) {
        };
    }

    public static zzaca<Float> zza(String str, Float f) {
        return new zzaca<Float>(str, f) {
        };
    }

    public static zzaca<Integer> zza(String str, Integer num) {
        return new zzaca<Integer>(str, num) {
        };
    }

    public static zzaca<Long> zza(String str, Long l) {
        return new zzaca<Long>(str, l) {
        };
    }

    public static zzaca<Boolean> zzj(String str, boolean z) {
        return new zzaca<Boolean>(str, Boolean.valueOf(z)) {
        };
    }
}
