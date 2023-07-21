package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpo;

@zzme
class zzaa implements Runnable {
    private zzl zzPi;
    private boolean zzxK = false;

    zzaa(zzl zzl) {
        this.zzPi = zzl;
    }

    private void zziz() {
        zzpo.zzXC.removeCallbacks(this);
        zzpo.zzXC.postDelayed(this, 250);
    }

    public void pause() {
        this.zzxK = true;
    }

    public void resume() {
        this.zzxK = false;
        zziz();
    }

    public void run() {
        if (!this.zzxK) {
            this.zzPi.zzid();
            zziz();
        }
    }
}
