package com.google.android.gms.ads;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzfa;
import com.google.android.gms.internal.zzfs;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqf;

@zzme
public final class VideoController {
    private final Object zzrJ = new Object();
    @Nullable
    private zzfa zzrK;
    @Nullable
    private VideoLifecycleCallbacks zzrL;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }
    }

    public float getAspectRatio() {
        float f = 0.0f;
        synchronized (this.zzrJ) {
            if (this.zzrK != null) {
                try {
                    f = this.zzrK.getAspectRatio();
                } catch (RemoteException e) {
                    zzqf.zzb("Unable to call getAspectRatio on video controller.", e);
                }
            }
        }
        return f;
    }

    @Nullable
    public VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.zzrJ) {
            videoLifecycleCallbacks = this.zzrL;
        }
        return videoLifecycleCallbacks;
    }

    public boolean hasVideoContent() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzrK != null;
        }
        return z;
    }

    public void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzac.zzb(videoLifecycleCallbacks, (Object) "VideoLifecycleCallbacks may not be null.");
        synchronized (this.zzrJ) {
            this.zzrL = videoLifecycleCallbacks;
            if (this.zzrK != null) {
                try {
                    this.zzrK.zza(new zzfs(videoLifecycleCallbacks));
                } catch (RemoteException e) {
                    zzqf.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                }
                return;
            }
            return;
        }
    }

    public void zza(zzfa zzfa) {
        synchronized (this.zzrJ) {
            this.zzrK = zzfa;
            if (this.zzrL != null) {
                setVideoLifecycleCallbacks(this.zzrL);
            }
        }
    }

    public zzfa zzbs() {
        zzfa zzfa;
        synchronized (this.zzrJ) {
            zzfa = this.zzrK;
        }
        return zzfa;
    }
}
