package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.stats.zza;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;

@zzme
public final class zzg extends zzlh.zza implements ServiceConnection {
    private Context mContext;
    private int mResultCode;
    private zzf zzPD;
    private boolean zzPJ = false;
    private Intent zzPK;
    zzb zzPt;
    private String zzPz;

    public zzg(Context context, String str, boolean z, int i, Intent intent, zzf zzf) {
        this.zzPz = str;
        this.mResultCode = i;
        this.zzPK = intent;
        this.zzPJ = z;
        this.mContext = context;
        this.zzPD = zzf;
    }

    public void finishPurchase() {
        int zzd = zzw.zzda().zzd(this.zzPK);
        if (this.mResultCode == -1 && zzd == 0) {
            this.zzPt = new zzb(this.mContext);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            zza.zzyJ().zza(this.mContext, intent, (ServiceConnection) this, 1);
        }
    }

    public String getProductId() {
        return this.zzPz;
    }

    public Intent getPurchaseData() {
        return this.zzPK;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public boolean isVerified() {
        return this.zzPJ;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzpk.zzbg("In-app billing service connected.");
        this.zzPt.zzV(iBinder);
        String zzaF = zzw.zzda().zzaF(zzw.zzda().zze(this.zzPK));
        if (zzaF != null) {
            if (this.zzPt.zzm(this.mContext.getPackageName(), zzaF) == 0) {
                zzh.zzu(this.mContext).zza(this.zzPD);
            }
            zza.zzyJ().zza(this.mContext, this);
            this.zzPt.destroy();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzpk.zzbg("In-app billing service disconnected.");
        this.zzPt.destroy();
    }
}
