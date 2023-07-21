package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzme;

@zzme
public class zzp extends FrameLayout implements View.OnClickListener {
    private final ImageButton zzOi;
    private final zzv zzOj;

    static class zza {
        public int paddingBottom = 0;
        public int paddingLeft = 0;
        public int paddingRight = 0;
        public int paddingTop = 0;
        public int size = 32;

        zza() {
        }
    }

    public zzp(Context context, zza zza2, zzv zzv) {
        super(context);
        this.zzOj = zzv;
        setOnClickListener(this);
        this.zzOi = new ImageButton(context);
        this.zzOi.setImageResource(17301527);
        this.zzOi.setBackgroundColor(0);
        this.zzOi.setOnClickListener(this);
        this.zzOi.setPadding(zzel.zzeT().zzb(context, zza2.paddingLeft), zzel.zzeT().zzb(context, 0), zzel.zzeT().zzb(context, zza2.paddingRight), zzel.zzeT().zzb(context, zza2.paddingBottom));
        this.zzOi.setContentDescription("Interstitial close button");
        zzel.zzeT().zzb(context, zza2.size);
        addView(this.zzOi, new FrameLayout.LayoutParams(zzel.zzeT().zzb(context, zza2.size + zza2.paddingLeft + zza2.paddingRight), zzel.zzeT().zzb(context, zza2.size + 0 + zza2.paddingBottom), 17));
    }

    public void onClick(View view) {
        if (this.zzOj != null) {
            this.zzOj.zzhE();
        }
    }

    public void zza(boolean z, boolean z2) {
        if (!z2) {
            this.zzOi.setVisibility(0);
        } else if (z) {
            this.zzOi.setVisibility(4);
        } else {
            this.zzOi.setVisibility(8);
        }
    }
}
