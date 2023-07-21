package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzabe {
    protected final zzabf zzaCR;

    protected zzabe(zzabf zzabf) {
        this.zzaCR = zzabf;
    }

    protected static zzabf zzc(zzabd zzabd) {
        return zzabd.zzwS() ? zzabu.zza(zzabd.zzwU()) : zzabg.zzt(zzabd.zzwT());
    }

    public static zzabf zzs(Activity activity) {
        return zzc(new zzabd(activity));
    }

    @MainThread
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public Activity getActivity() {
        return this.zzaCR.zzwV();
    }

    @MainThread
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @MainThread
    public void onCreate(Bundle bundle) {
    }

    @MainThread
    public void onDestroy() {
    }

    @MainThread
    public void onSaveInstanceState(Bundle bundle) {
    }

    @MainThread
    public void onStart() {
    }

    @MainThread
    public void onStop() {
    }
}
