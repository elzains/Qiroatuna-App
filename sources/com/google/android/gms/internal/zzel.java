package com.google.android.gms.internal;

@zzme
public class zzel {
    private static final Object zztX = new Object();
    private static zzel zzzT;
    private final zzqe zzzU = new zzqe();
    private final zzek zzzV = new zzek(new zzeb(), new zzea(), new zzfj(), new zzht(), new zzny(), new zzlk(), new zzky());

    static {
        zza(new zzel());
    }

    protected zzel() {
    }

    protected static void zza(zzel zzel) {
        synchronized (zztX) {
            zzzT = zzel;
        }
    }

    private static zzel zzeS() {
        zzel zzel;
        synchronized (zztX) {
            zzel = zzzT;
        }
        return zzel;
    }

    public static zzqe zzeT() {
        return zzeS().zzzU;
    }

    public static zzek zzeU() {
        return zzeS().zzzV;
    }
}
