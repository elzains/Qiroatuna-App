package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.zzw;

@zzme
public class zzpl extends Handler {
    public zzpl(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        try {
            super.handleMessage(message);
        } catch (Exception e) {
            zzw.zzcQ().zza((Throwable) e, "AdMobHandler.handleMessage");
        }
    }
}
