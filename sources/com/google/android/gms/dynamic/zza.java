package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zzaRA;
    /* access modifiers changed from: private */
    public Bundle zzaRB;
    /* access modifiers changed from: private */
    public LinkedList<C0777zza> zzaRC;
    private final zze<T> zzaRD = new zze<T>() {
        public void zza(T t) {
            LifecycleDelegate unused = zza.this.zzaRA = t;
            Iterator it = zza.this.zzaRC.iterator();
            while (it.hasNext()) {
                ((C0777zza) it.next()).zzb(zza.this.zzaRA);
            }
            zza.this.zzaRC.clear();
            Bundle unused2 = zza.this.zzaRB = null;
        }
    };

    /* renamed from: com.google.android.gms.dynamic.zza$zza  reason: collision with other inner class name */
    private interface C0777zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    private void zza(Bundle bundle, C0777zza zza) {
        if (this.zzaRA != null) {
            zza.zzb(this.zzaRA);
            return;
        }
        if (this.zzaRC == null) {
            this.zzaRC = new LinkedList<>();
        }
        this.zzaRC.add(zza);
        if (bundle != null) {
            if (this.zzaRB == null) {
                this.zzaRB = (Bundle) bundle.clone();
            } else {
                this.zzaRB.putAll(bundle);
            }
        }
        zza(this.zzaRD);
    }

    @VisibleForTesting
    static void zza(FrameLayout frameLayout, GoogleApiAvailability googleApiAvailability) {
        final Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String zzi = zzh.zzi(context, isGooglePlayServicesAvailable);
        String zzk = zzh.zzk(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zzi);
        linearLayout.addView(textView);
        final Intent zzb = googleApiAvailability.zzb(context, isGooglePlayServicesAvailable, (String) null);
        if (zzb != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zzk);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    try {
                        context.startActivity(zzb);
                    } catch (ActivityNotFoundException e) {
                        Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
                    }
                }
            });
        }
    }

    public static void zzb(FrameLayout frameLayout) {
        zza(frameLayout, GoogleApiAvailability.getInstance());
    }

    private void zzgt(int i) {
        while (!this.zzaRC.isEmpty() && this.zzaRC.getLast().getState() >= i) {
            this.zzaRC.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, (C0777zza) new C0777zza() {
            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaRA.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, (C0777zza) new C0777zza() {
            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.zzaRA.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.zzaRA == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.zzaRA != null) {
            this.zzaRA.onDestroy();
        } else {
            zzgt(1);
        }
    }

    public void onDestroyView() {
        if (this.zzaRA != null) {
            this.zzaRA.onDestroyView();
        } else {
            zzgt(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, (C0777zza) new C0777zza() {
            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaRA.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.zzaRA != null) {
            this.zzaRA.onLowMemory();
        }
    }

    public void onPause() {
        if (this.zzaRA != null) {
            this.zzaRA.onPause();
        } else {
            zzgt(5);
        }
    }

    public void onResume() {
        zza((Bundle) null, (C0777zza) new C0777zza() {
            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaRA.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.zzaRA != null) {
            this.zzaRA.onSaveInstanceState(bundle);
        } else if (this.zzaRB != null) {
            bundle.putAll(this.zzaRB);
        }
    }

    public void onStart() {
        zza((Bundle) null, (C0777zza) new C0777zza() {
            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.zzaRA.onStart();
            }
        });
    }

    public void onStop() {
        if (this.zzaRA != null) {
            this.zzaRA.onStop();
        } else {
            zzgt(4);
        }
    }

    public T zzBM() {
        return this.zzaRA;
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zze<T> zze);
}
