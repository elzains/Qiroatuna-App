package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.Button;
import com.google.android.gms.C0156R;

public final class zzak extends Button {
    public zzak(Context context) {
        this(context, (AttributeSet) null);
    }

    public zzak(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 16842824);
    }

    private void zza(Resources resources) {
        setTypeface(Typeface.DEFAULT_BOLD);
        setTextSize(14.0f);
        float f = resources.getDisplayMetrics().density;
        setMinHeight((int) ((f * 48.0f) + 0.5f));
        setMinWidth((int) ((f * 48.0f) + 0.5f));
    }

    private void zzb(Resources resources, int i, int i2) {
        Drawable wrap = DrawableCompat.wrap(resources.getDrawable(zze(i, zzg(i2, C0156R.C0157drawable.common_google_signin_btn_icon_dark, C0156R.C0157drawable.common_google_signin_btn_icon_light, C0156R.C0157drawable.common_google_signin_btn_icon_light), zzg(i2, C0156R.C0157drawable.common_google_signin_btn_text_dark, C0156R.C0157drawable.common_google_signin_btn_text_light, C0156R.C0157drawable.common_google_signin_btn_text_light))));
        DrawableCompat.setTintList(wrap, resources.getColorStateList(C0156R.color.common_google_signin_btn_tint));
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_ATOP);
        setBackgroundDrawable(wrap);
    }

    private void zzc(Resources resources, int i, int i2) {
        setTextColor((ColorStateList) zzac.zzw(resources.getColorStateList(zzg(i2, C0156R.color.common_google_signin_btn_text_dark, C0156R.color.common_google_signin_btn_text_light, C0156R.color.common_google_signin_btn_text_light))));
        switch (i) {
            case 0:
                setText(resources.getString(C0156R.string.common_signin_button_text));
                break;
            case 1:
                setText(resources.getString(C0156R.string.common_signin_button_text_long));
                break;
            case 2:
                setText((CharSequence) null);
                break;
            default:
                throw new IllegalStateException(new StringBuilder(32).append("Unknown button size: ").append(i).toString());
        }
        setTransformationMethod((TransformationMethod) null);
    }

    private int zze(int i, int i2, int i3) {
        switch (i) {
            case 0:
            case 1:
                return i3;
            case 2:
                return i2;
            default:
                throw new IllegalStateException(new StringBuilder(32).append("Unknown button size: ").append(i).toString());
        }
    }

    private int zzg(int i, int i2, int i3, int i4) {
        switch (i) {
            case 0:
                return i2;
            case 1:
                return i3;
            case 2:
                return i4;
            default:
                throw new IllegalStateException(new StringBuilder(33).append("Unknown color scheme: ").append(i).toString());
        }
    }

    public void zza(Resources resources, int i, int i2) {
        zza(resources);
        zzb(resources, i, i2);
        zzc(resources, i, i2);
    }
}
