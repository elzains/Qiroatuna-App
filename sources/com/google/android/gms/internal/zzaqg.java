package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzag;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class zzaqg {

    static class zza implements zzf.zzb, zzf.zzc {
        private final String packageName;
        protected zzaqh zzbgf;
        private final String zzbgg;
        private final LinkedBlockingQueue<zzag.zza> zzbgh;
        private final HandlerThread zzbgi = new HandlerThread("GassClient");

        public zza(Context context, String str, String str2) {
            this.packageName = str;
            this.zzbgg = str2;
            this.zzbgi.start();
            this.zzbgf = new zzaqh(context, this.zzbgi.getLooper(), this, this);
            this.zzbgh = new LinkedBlockingQueue<>();
            connect();
        }

        /* access modifiers changed from: protected */
        public void connect() {
            this.zzbgf.zzxz();
        }

        public void onConnected(Bundle bundle) {
            zzaqm zzGJ = zzGJ();
            if (zzGJ != null) {
                try {
                    this.zzbgh.put(zzGJ.zza(new zzaqi(this.packageName, this.zzbgg)).zzGM());
                } catch (Throwable th) {
                } finally {
                    zzjn();
                    this.zzbgi.quit();
                }
            }
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                this.zzbgh.put(new zzag.zza());
            } catch (InterruptedException e) {
            }
        }

        public void onConnectionSuspended(int i) {
            try {
                this.zzbgh.put(new zzag.zza());
            } catch (InterruptedException e) {
            }
        }

        /* access modifiers changed from: protected */
        public zzaqm zzGJ() {
            try {
                return this.zzbgf.zzGK();
            } catch (DeadObjectException | IllegalStateException e) {
                return null;
            }
        }

        public zzag.zza zzaR() {
            return zzjx(5000);
        }

        public void zzjn() {
            if (this.zzbgf == null) {
                return;
            }
            if (this.zzbgf.isConnected() || this.zzbgf.isConnecting()) {
                this.zzbgf.disconnect();
            }
        }

        public zzag.zza zzjx(int i) {
            zzag.zza zza;
            try {
                zza = this.zzbgh.poll((long) i, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                zza = null;
            }
            return zza == null ? new zzag.zza() : zza;
        }
    }

    public static zzag.zza zzq(Context context, String str, String str2) {
        return new zza(context, str, str2).zzaR();
    }
}
