package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzfa;
import com.rjfun.cordova.plugin.nativeaudio.NativeAudio;
import java.util.HashMap;
import java.util.Map;

@zzme
public class zzrb extends zzfa.zza {
    /* access modifiers changed from: private */
    public final zzqw zzIs;
    private int zzaaA;
    /* access modifiers changed from: private */
    public zzfb zzaaB;
    /* access modifiers changed from: private */
    public boolean zzaaC;
    private boolean zzaaD;
    private float zzaaE;
    private float zzaaF;
    private final float zzaaz;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private boolean zzrM = true;

    public zzrb(zzqw zzqw, float f) {
        this.zzIs = zzqw;
        this.zzaaz = f;
    }

    private void zzbn(String str) {
        zzd(str, (Map<String, String>) null);
    }

    private void zzd(String str, @Nullable Map<String, String> map) {
        final HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                zzrb.this.zzIs.zza("pubVideoCmd", (Map<String, ?>) hashMap);
            }
        });
    }

    private void zzl(final int i, final int i2) {
        zzw.zzcM().runOnUiThread(new Runnable() {
            public void run() {
                boolean z = false;
                synchronized (zzrb.this.zzrJ) {
                    boolean z2 = i != i2;
                    boolean z3 = !zzrb.this.zzaaC && i2 == 1;
                    boolean z4 = z2 && i2 == 1;
                    boolean z5 = z2 && i2 == 2;
                    boolean z6 = z2 && i2 == 3;
                    zzrb zzrb = zzrb.this;
                    if (zzrb.this.zzaaC || z3) {
                        z = true;
                    }
                    boolean unused = zzrb.zzaaC = z;
                    if (zzrb.this.zzaaB != null) {
                        if (z3) {
                            try {
                                zzrb.this.zzaaB.zzeY();
                            } catch (RemoteException e) {
                                zzpk.zzc("Unable to call onVideoStart()", e);
                            }
                        }
                        if (z4) {
                            try {
                                zzrb.this.zzaaB.zzeZ();
                            } catch (RemoteException e2) {
                                zzpk.zzc("Unable to call onVideoPlay()", e2);
                            }
                        }
                        if (z5) {
                            try {
                                zzrb.this.zzaaB.zzfa();
                            } catch (RemoteException e3) {
                                zzpk.zzc("Unable to call onVideoPause()", e3);
                            }
                        }
                        if (z6) {
                            try {
                                zzrb.this.zzaaB.onVideoEnd();
                            } catch (RemoteException e4) {
                                zzpk.zzc("Unable to call onVideoEnd()", e4);
                            }
                        }
                    }
                }
            }
        });
    }

    public float getAspectRatio() {
        float f;
        synchronized (this.zzrJ) {
            f = this.zzaaF;
        }
        return f;
    }

    public int getPlaybackState() {
        int i;
        synchronized (this.zzrJ) {
            i = this.zzaaA;
        }
        return i;
    }

    public boolean isMuted() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzaaD;
        }
        return z;
    }

    public void pause() {
        zzbn("pause");
    }

    public void play() {
        zzbn(NativeAudio.PLAY);
    }

    public void zzQ(boolean z) {
        synchronized (this.zzrJ) {
            this.zzrM = z;
        }
        zzd("initialState", zzf.zzd("muteStart", z ? "1" : "0"));
    }

    public void zza(float f, int i, boolean z, float f2) {
        int i2;
        synchronized (this.zzrJ) {
            this.zzaaE = f;
            this.zzaaD = z;
            i2 = this.zzaaA;
            this.zzaaA = i;
            this.zzaaF = f2;
        }
        zzl(i2, i);
    }

    public void zza(zzfb zzfb) {
        synchronized (this.zzrJ) {
            this.zzaaB = zzfb;
        }
    }

    public float zzeW() {
        return this.zzaaz;
    }

    public float zzeX() {
        float f;
        synchronized (this.zzrJ) {
            f = this.zzaaE;
        }
        return f;
    }

    public void zzn(boolean z) {
        zzbn(z ? "mute" : "unmute");
    }
}
