package com.google.android.gms.internal;

public final class zzaqe {
    private static zzaqe zzaXl;
    private final zzaqb zzaXm = new zzaqb();
    private final zzaqc zzaXn = new zzaqc();

    static {
        zza(new zzaqe());
    }

    private zzaqe() {
    }

    private static zzaqe zzDC() {
        zzaqe zzaqe;
        synchronized (zzaqe.class) {
            zzaqe = zzaXl;
        }
        return zzaqe;
    }

    public static zzaqb zzDD() {
        return zzDC().zzaXm;
    }

    public static zzaqc zzDE() {
        return zzDC().zzaXn;
    }

    protected static void zza(zzaqe zzaqe) {
        synchronized (zzaqe.class) {
            zzaXl = zzaqe;
        }
    }
}
