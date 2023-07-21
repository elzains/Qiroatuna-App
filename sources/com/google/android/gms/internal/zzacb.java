package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public final class zzacb extends Drawable implements Drawable.Callback {
    private int mFrom;
    private int zzaED;
    private int zzaEE;
    private int zzaEF;
    private int zzaEG;
    private int zzaEH;
    private boolean zzaEI;
    private zzb zzaEJ;
    private Drawable zzaEK;
    private Drawable zzaEL;
    private boolean zzaEM;
    private boolean zzaEN;
    private boolean zzaEO;
    private int zzaEP;
    private boolean zzaEy;
    private long zzafe;

    private static final class zza extends Drawable {
        /* access modifiers changed from: private */
        public static final zza zzaEQ = new zza();
        private static final C0786zza zzaER = new C0786zza();

        /* renamed from: com.google.android.gms.internal.zzacb$zza$zza  reason: collision with other inner class name */
        private static final class C0786zza extends Drawable.ConstantState {
            private C0786zza() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return zza.zzaEQ;
            }
        }

        private zza() {
        }

        public void draw(Canvas canvas) {
        }

        public Drawable.ConstantState getConstantState() {
            return zzaER;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    static final class zzb extends Drawable.ConstantState {
        int mChangingConfigurations;
        int zzaES;

        zzb(zzb zzb) {
            if (zzb != null) {
                this.mChangingConfigurations = zzb.mChangingConfigurations;
                this.zzaES = zzb.zzaES;
            }
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Drawable newDrawable() {
            return new zzacb(this);
        }
    }

    public zzacb(Drawable drawable, Drawable drawable2) {
        this((zzb) null);
        drawable = drawable == null ? zza.zzaEQ : drawable;
        this.zzaEK = drawable;
        drawable.setCallback(this);
        this.zzaEJ.zzaES |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? zza.zzaEQ : drawable2;
        this.zzaEL = drawable2;
        drawable2.setCallback(this);
        this.zzaEJ.zzaES |= drawable2.getChangingConfigurations();
    }

    zzacb(zzb zzb2) {
        this.zzaED = 0;
        this.zzaEF = 255;
        this.zzaEH = 0;
        this.zzaEy = true;
        this.zzaEJ = new zzb(zzb2);
    }

    public boolean canConstantState() {
        if (!this.zzaEM) {
            this.zzaEN = (this.zzaEK.getConstantState() == null || this.zzaEL.getConstantState() == null) ? false : true;
            this.zzaEM = true;
        }
        return this.zzaEN;
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.zzaED) {
            case 1:
                this.zzafe = SystemClock.uptimeMillis();
                this.zzaED = 2;
                break;
            case 2:
                if (this.zzafe >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzafe)) / ((float) this.zzaEG);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.zzaED = 0;
                    }
                    this.zzaEH = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.zzaEE + 0))) + 0.0f);
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.zzaEH;
        boolean z3 = this.zzaEy;
        Drawable drawable = this.zzaEK;
        Drawable drawable2 = this.zzaEL;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zzaEF) {
                drawable2.setAlpha(this.zzaEF);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.zzaEF - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.zzaEF);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zzaEF);
        }
        invalidateSelf();
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzaEJ.mChangingConfigurations | this.zzaEJ.zzaES;
    }

    public Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zzaEJ.mChangingConfigurations = getChangingConfigurations();
        return this.zzaEJ;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.zzaEK.getIntrinsicHeight(), this.zzaEL.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.zzaEK.getIntrinsicWidth(), this.zzaEL.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.zzaEO) {
            this.zzaEP = Drawable.resolveOpacity(this.zzaEK.getOpacity(), this.zzaEL.getOpacity());
            this.zzaEO = true;
        }
        return this.zzaEP;
    }

    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Drawable mutate() {
        if (!this.zzaEI && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzaEK.mutate();
            this.zzaEL.mutate();
            this.zzaEI = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.zzaEK.setBounds(rect);
        this.zzaEL.setBounds(rect);
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public void setAlpha(int i) {
        if (this.zzaEH == this.zzaEF) {
            this.zzaEH = i;
        }
        this.zzaEF = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.zzaEK.setColorFilter(colorFilter);
        this.zzaEL.setColorFilter(colorFilter);
    }

    public void startTransition(int i) {
        this.mFrom = 0;
        this.zzaEE = this.zzaEF;
        this.zzaEH = 0;
        this.zzaEG = i;
        this.zzaED = 1;
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public Drawable zzxs() {
        return this.zzaEL;
    }
}
