package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import com.google.android.gms.internal.zzme;

@zzme
@TargetApi(14)
public class zzab implements AudioManager.OnAudioFocusChangeListener {
    private final AudioManager mAudioManager;
    private boolean zzNY;
    private final zza zzPj;
    private boolean zzPk;
    private boolean zzPl;
    private float zzPm = 1.0f;

    interface zza {
        void zzhC();
    }

    public zzab(Context context, zza zza2) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.zzPj = zza2;
    }

    private void zziB() {
        boolean z = this.zzNY && !this.zzPl && this.zzPm > 0.0f;
        if (z && !this.zzPk) {
            zziC();
            this.zzPj.zzhC();
        } else if (!z && this.zzPk) {
            zziD();
            this.zzPj.zzhC();
        }
    }

    private void zziC() {
        boolean z = true;
        if (this.mAudioManager != null && !this.zzPk) {
            if (this.mAudioManager.requestAudioFocus(this, 3, 2) != 1) {
                z = false;
            }
            this.zzPk = z;
        }
    }

    private void zziD() {
        if (this.mAudioManager != null && this.zzPk) {
            this.zzPk = this.mAudioManager.abandonAudioFocus(this) == 0;
        }
    }

    public void onAudioFocusChange(int i) {
        this.zzPk = i > 0;
        this.zzPj.zzhC();
    }

    public void setMuted(boolean z) {
        this.zzPl = z;
        zziB();
    }

    public void zzb(float f) {
        this.zzPm = f;
        zziB();
    }

    public float zziA() {
        float f = this.zzPl ? 0.0f : this.zzPm;
        if (this.zzPk) {
            return f;
        }
        return 0.0f;
    }

    public void zzix() {
        this.zzNY = true;
        zziB();
    }

    public void zziy() {
        this.zzNY = false;
        zziB();
    }
}
