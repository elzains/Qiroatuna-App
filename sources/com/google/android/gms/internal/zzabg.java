package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzabg extends Fragment implements zzabf {
    private static WeakHashMap<Activity, WeakReference<zzabg>> zzaCS = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public int zzJO = 0;
    private Map<String, zzabe> zzaCT = new ArrayMap();
    /* access modifiers changed from: private */
    public Bundle zzaCU;

    private void zzb(final String str, @NonNull final zzabe zzabe) {
        if (this.zzJO > 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (zzabg.this.zzJO >= 1) {
                        zzabe.onCreate(zzabg.this.zzaCU != null ? zzabg.this.zzaCU.getBundle(str) : null);
                    }
                    if (zzabg.this.zzJO >= 2) {
                        zzabe.onStart();
                    }
                    if (zzabg.this.zzJO >= 3) {
                        zzabe.onStop();
                    }
                    if (zzabg.this.zzJO >= 4) {
                        zzabe.onDestroy();
                    }
                }
            });
        }
    }

    public static zzabg zzt(Activity activity) {
        zzabg zzabg;
        WeakReference weakReference = zzaCS.get(activity);
        if (weakReference == null || (zzabg = (zzabg) weakReference.get()) == null) {
            try {
                zzabg = (zzabg) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
                if (zzabg == null || zzabg.isRemoving()) {
                    zzabg = new zzabg();
                    activity.getFragmentManager().beginTransaction().add(zzabg, "LifecycleFragmentImpl").commitAllowingStateLoss();
                }
                zzaCS.put(activity, new WeakReference(zzabg));
            } catch (ClassCastException e) {
                throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e);
            }
        }
        return zzabg;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzabe dump : this.zzaCT.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzabe onActivityResult : this.zzaCT.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzJO = 1;
        this.zzaCU = bundle;
        for (Map.Entry next : this.zzaCT.entrySet()) {
            ((zzabe) next.getValue()).onCreate(bundle != null ? bundle.getBundle((String) next.getKey()) : null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.zzJO = 4;
        for (zzabe onDestroy : this.zzaCT.values()) {
            onDestroy.onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Map.Entry next : this.zzaCT.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzabe) next.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) next.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        this.zzJO = 2;
        for (zzabe onStart : this.zzaCT.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        this.zzJO = 3;
        for (zzabe onStop : this.zzaCT.values()) {
            onStop.onStop();
        }
    }

    public <T extends zzabe> T zza(String str, Class<T> cls) {
        return (zzabe) cls.cast(this.zzaCT.get(str));
    }

    public void zza(String str, @NonNull zzabe zzabe) {
        if (!this.zzaCT.containsKey(str)) {
            this.zzaCT.put(str, zzabe);
            zzb(str, zzabe);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
    }

    public Activity zzwV() {
        return getActivity();
    }
}
