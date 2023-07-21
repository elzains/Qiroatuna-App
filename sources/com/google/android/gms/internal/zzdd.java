package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzt;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzdd {
    private final Object zzxG = new Object();
    private zza zzxH = null;
    private boolean zzxI = false;

    @TargetApi(14)
    static class zza implements Application.ActivityLifecycleCallbacks {
        @Nullable
        private Activity mActivity;
        private Context mContext;
        /* access modifiers changed from: private */
        public List<zzb> mListeners = new ArrayList();
        /* access modifiers changed from: private */
        public final Object zzrJ = new Object();
        private boolean zztZ = false;
        /* access modifiers changed from: private */
        public boolean zzxJ = true;
        /* access modifiers changed from: private */
        public boolean zzxK = false;
        private Runnable zzxL;
        private long zzxM;

        zza() {
        }

        private void setActivity(Activity activity) {
            synchronized (this.zzrJ) {
                if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                    this.mActivity = activity;
                }
            }
        }

        @Nullable
        public Activity getActivity() {
            return this.mActivity;
        }

        @Nullable
        public Context getContext() {
            return this.mContext;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onActivityDestroyed(android.app.Activity r3) {
            /*
                r2 = this;
                java.lang.Object r1 = r2.zzrJ
                monitor-enter(r1)
                android.app.Activity r0 = r2.mActivity     // Catch:{ all -> 0x0016 }
                if (r0 != 0) goto L_0x0009
                monitor-exit(r1)     // Catch:{ all -> 0x0016 }
            L_0x0008:
                return
            L_0x0009:
                android.app.Activity r0 = r2.mActivity     // Catch:{ all -> 0x0016 }
                boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x0016 }
                if (r0 == 0) goto L_0x0014
                r0 = 0
                r2.mActivity = r0     // Catch:{ all -> 0x0016 }
            L_0x0014:
                monitor-exit(r1)     // Catch:{ all -> 0x0016 }
                goto L_0x0008
            L_0x0016:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0016 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdd.zza.onActivityDestroyed(android.app.Activity):void");
        }

        public void onActivityPaused(Activity activity) {
            setActivity(activity);
            this.zzxK = true;
            if (this.zzxL != null) {
                zzpo.zzXC.removeCallbacks(this.zzxL);
            }
            Handler handler = zzpo.zzXC;
            C03281 r1 = new Runnable() {
                public void run() {
                    synchronized (zza.this.zzrJ) {
                        if (!zza.this.zzxJ || !zza.this.zzxK) {
                            zzpk.zzbf("App is still foreground");
                        } else {
                            boolean unused = zza.this.zzxJ = false;
                            zzpk.zzbf("App went background");
                            for (zzb zzk : zza.this.mListeners) {
                                try {
                                    zzk.zzk(false);
                                } catch (Exception e) {
                                    zzpk.zzb("OnForegroundStateChangedListener threw exception.", e);
                                }
                            }
                        }
                    }
                }
            };
            this.zzxL = r1;
            handler.postDelayed(r1, this.zzxM);
        }

        public void onActivityResumed(Activity activity) {
            boolean z = false;
            setActivity(activity);
            this.zzxK = false;
            if (!this.zzxJ) {
                z = true;
            }
            this.zzxJ = true;
            if (this.zzxL != null) {
                zzpo.zzXC.removeCallbacks(this.zzxL);
            }
            synchronized (this.zzrJ) {
                if (z) {
                    for (zzb zzk : this.mListeners) {
                        try {
                            zzk.zzk(true);
                        } catch (Exception e) {
                            zzpk.zzb("OnForegroundStateChangedListener threw exception.", e);
                        }
                    }
                } else {
                    zzpk.zzbf("App is still foreground.");
                }
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            setActivity(activity);
        }

        public void onActivityStopped(Activity activity) {
        }

        public void zza(Application application, Context context) {
            if (!this.zztZ) {
                application.registerActivityLifecycleCallbacks(this);
                if (context instanceof Activity) {
                    setActivity((Activity) context);
                }
                this.mContext = context;
                this.zzxM = zzgd.zzCQ.get().longValue();
                this.zztZ = true;
            }
        }

        public void zza(zzb zzb) {
            this.mListeners.add(zzb);
        }
    }

    public interface zzb {
        void zzk(boolean z);
    }

    @Nullable
    public Activity getActivity() {
        Activity activity;
        synchronized (this.zzxG) {
            zzt.zzzg();
            activity = this.zzxH != null ? this.zzxH.getActivity() : null;
        }
        return activity;
    }

    @Nullable
    public Context getContext() {
        Context context;
        synchronized (this.zzxG) {
            zzt.zzzg();
            context = this.zzxH != null ? this.zzxH.getContext() : null;
        }
        return context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.Object r2 = r4.zzxG
            monitor-enter(r2)
            boolean r0 = r4.zzxI     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x0047
            com.google.android.gms.common.util.zzt.zzzg()     // Catch:{ all -> 0x0031 }
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzCP     // Catch:{ all -> 0x0031 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0031 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0031 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x001a
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
        L_0x0019:
            return
        L_0x001a:
            r1 = 0
            android.content.Context r0 = r5.getApplicationContext()     // Catch:{ all -> 0x0031 }
            if (r0 != 0) goto L_0x0022
            r0 = r5
        L_0x0022:
            boolean r3 = r0 instanceof android.app.Application     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0049
            android.app.Application r0 = (android.app.Application) r0     // Catch:{ all -> 0x0031 }
        L_0x0028:
            if (r0 != 0) goto L_0x0034
            java.lang.String r0 = "Can not cast Context to Application"
            com.google.android.gms.internal.zzpk.zzbh(r0)     // Catch:{ all -> 0x0031 }
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            goto L_0x0019
        L_0x0031:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            throw r0
        L_0x0034:
            com.google.android.gms.internal.zzdd$zza r1 = r4.zzxH     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x003f
            com.google.android.gms.internal.zzdd$zza r1 = new com.google.android.gms.internal.zzdd$zza     // Catch:{ all -> 0x0031 }
            r1.<init>()     // Catch:{ all -> 0x0031 }
            r4.zzxH = r1     // Catch:{ all -> 0x0031 }
        L_0x003f:
            com.google.android.gms.internal.zzdd$zza r1 = r4.zzxH     // Catch:{ all -> 0x0031 }
            r1.zza((android.app.Application) r0, (android.content.Context) r5)     // Catch:{ all -> 0x0031 }
            r0 = 1
            r4.zzxI = r0     // Catch:{ all -> 0x0031 }
        L_0x0047:
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            goto L_0x0019
        L_0x0049:
            r0 = r1
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdd.initialize(android.content.Context):void");
    }

    public void zza(zzb zzb2) {
        synchronized (this.zzxG) {
            zzt.zzzg();
            if (zzgd.zzCP.get().booleanValue()) {
                if (this.zzxH == null) {
                    this.zzxH = new zza();
                }
                this.zzxH.zza(zzb2);
            }
        }
    }
}
