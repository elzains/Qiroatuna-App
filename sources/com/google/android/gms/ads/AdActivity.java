package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzqf;

public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzkz zzrv;

    private void zzbo() {
        if (this.zzrv != null) {
            try {
                this.zzrv.zzbo();
            } catch (RemoteException e) {
                zzqf.zzc("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzrv.onActivityResult(i, i2, intent);
        } catch (Exception e) {
            zzqf.zzc("Could not forward onActivityResult to ad overlay:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        boolean z = true;
        try {
            if (this.zzrv != null) {
                z = this.zzrv.zzhF();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onBackPressed to ad overlay:", e);
        }
        if (z) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzrv.zzo(zzd.zzA(configuration));
        } catch (RemoteException e) {
            zzqf.zzc("Failed to wrap configuration.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzrv = zzel.zzeU().zzc((Activity) this);
        if (this.zzrv == null) {
            zzqf.zzbh("Could not create ad overlay.");
            finish();
            return;
        }
        try {
            this.zzrv.onCreate(bundle);
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onCreate to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            if (this.zzrv != null) {
                this.zzrv.onDestroy();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        try {
            if (this.zzrv != null) {
                this.zzrv.onPause();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        try {
            if (this.zzrv != null) {
                this.zzrv.onRestart();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            if (this.zzrv != null) {
                this.zzrv.onResume();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zzrv != null) {
                this.zzrv.onSaveInstanceState(bundle);
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            if (this.zzrv != null) {
                this.zzrv.onStart();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        try {
            if (this.zzrv != null) {
                this.zzrv.onStop();
            }
        } catch (RemoteException e) {
            zzqf.zzc("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        zzbo();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        zzbo();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzbo();
    }
}
