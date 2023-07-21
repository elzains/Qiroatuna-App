package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;

@zzme
@TargetApi(19)
public class zzlt extends zzlr {
    private Object zzQb = new Object();
    private PopupWindow zzQc;
    private boolean zzQd = false;

    zzlt(Context context, zzpb.zza zza, zzqw zzqw, zzlq.zza zza2) {
        super(context, zza, zzqw, zza2);
    }

    private void zziR() {
        synchronized (this.zzQb) {
            this.zzQd = true;
            if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isDestroyed()) {
                this.zzQc = null;
            }
            if (this.zzQc != null) {
                if (this.zzQc.isShowing()) {
                    this.zzQc.dismiss();
                }
                this.zzQc = null;
            }
        }
    }

    public void cancel() {
        zziR();
        super.cancel();
    }

    /* access modifiers changed from: protected */
    public void zzQ(int i) {
        zziR();
        super.zzQ(i);
    }

    /* access modifiers changed from: protected */
    public void zziQ() {
        Window window = this.mContext instanceof Activity ? ((Activity) this.mContext).getWindow() : null;
        if (window != null && window.getDecorView() != null && !((Activity) this.mContext).isDestroyed()) {
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            frameLayout.addView(this.zzIs.getView(), -1, -1);
            synchronized (this.zzQb) {
                if (!this.zzQd) {
                    this.zzQc = new PopupWindow(frameLayout, 1, 1, false);
                    this.zzQc.setOutsideTouchable(true);
                    this.zzQc.setClippingEnabled(false);
                    zzpk.zzbf("Displaying the 1x1 popup off the screen.");
                    try {
                        this.zzQc.showAtLocation(window.getDecorView(), 0, -1, -1);
                    } catch (Exception e) {
                        this.zzQc = null;
                    }
                }
            }
        }
    }
}
