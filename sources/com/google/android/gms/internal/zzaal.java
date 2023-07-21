package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class zzaal {
    /* access modifiers changed from: private */
    public final Map<zzaaf<?>, Boolean> zzaBc = Collections.synchronizedMap(new WeakHashMap());
    /* access modifiers changed from: private */
    public final Map<TaskCompletionSource<?>, Boolean> zzaBd = Collections.synchronizedMap(new WeakHashMap());

    private void zza(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.zzaBc) {
            hashMap = new HashMap(this.zzaBc);
        }
        synchronized (this.zzaBd) {
            hashMap2 = new HashMap(this.zzaBd);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((zzaaf) entry.getKey()).zzC(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new zza(status));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(final zzaaf<? extends Result> zzaaf, boolean z) {
        this.zzaBc.put(zzaaf, Boolean.valueOf(z));
        zzaaf.zza((PendingResult.zza) new PendingResult.zza() {
            public void zzy(Status status) {
                zzaal.this.zzaBc.remove(zzaaf);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public <TResult> void zza(final TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zzaBd.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener<TResult>() {
            public void onComplete(@NonNull Task<TResult> task) {
                zzaal.this.zzaBd.remove(taskCompletionSource);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean zzvY() {
        return !this.zzaBc.isEmpty() || !this.zzaBd.isEmpty();
    }

    public void zzvZ() {
        zza(false, zzaax.zzaCn);
    }

    public void zzwa() {
        zza(true, zzaby.zzaDu);
    }
}
