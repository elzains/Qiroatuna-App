package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzacb;
import com.google.android.gms.internal.zzacc;
import com.google.android.gms.internal.zzacd;
import java.lang.ref.WeakReference;

public abstract class zza {
    private boolean zzaEA = true;
    final C0767zza zzaEu;
    protected int zzaEv = 0;
    protected int zzaEw = 0;
    protected boolean zzaEx = false;
    private boolean zzaEy = true;
    private boolean zzaEz = false;

    /* renamed from: com.google.android.gms.common.images.zza$zza  reason: collision with other inner class name */
    static final class C0767zza {
        public final Uri uri;

        public C0767zza(Uri uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0767zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return zzaa.equal(((C0767zza) obj).uri, this.uri);
        }

        public int hashCode() {
            return zzaa.hashCode(this.uri);
        }
    }

    public static final class zzb extends zza {
        private WeakReference<ImageView> zzaEB;

        public zzb(ImageView imageView, int i) {
            super((Uri) null, i);
            com.google.android.gms.common.internal.zzc.zzt(imageView);
            this.zzaEB = new WeakReference<>(imageView);
        }

        public zzb(ImageView imageView, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzt(imageView);
            this.zzaEB = new WeakReference<>(imageView);
        }

        private void zza(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzacc)) {
                int zzxu = ((zzacc) imageView).zzxu();
                if (this.zzaEw != 0 && zzxu == this.zzaEw) {
                    return;
                }
            }
            boolean zzc = zzc(z, z2);
            Drawable zza = zzc ? zza(imageView.getDrawable(), drawable) : drawable;
            imageView.setImageDrawable(zza);
            if (imageView instanceof zzacc) {
                zzacc zzacc = (zzacc) imageView;
                zzacc.zzr(z3 ? this.zzaEu.uri : null);
                zzacc.zzcQ(z4 ? this.zzaEw : 0);
            }
            if (zzc) {
                ((zzacb) zza).startTransition(250);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.zzaEB.get();
            ImageView imageView2 = (ImageView) ((zzb) obj).zzaEB.get();
            return (imageView2 == null || imageView == null || !zzaa.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.zzaEB.get();
            if (imageView != null) {
                zza(imageView, drawable, z, z2, z3);
            }
        }
    }

    public static final class zzc extends zza {
        private WeakReference<ImageManager.OnImageLoadedListener> zzaEC;

        public zzc(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzt(onImageLoadedListener);
            this.zzaEC = new WeakReference<>(onImageLoadedListener);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzaEC.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zzc.zzaEC.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && zzaa.equal(onImageLoadedListener2, onImageLoadedListener) && zzaa.equal(zzc.zzaEu, this.zzaEu);
        }

        public int hashCode() {
            return zzaa.hashCode(this.zzaEu);
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageManager.OnImageLoadedListener onImageLoadedListener;
            if (!z2 && (onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zzaEC.get()) != null) {
                onImageLoadedListener.onImageLoaded(this.zzaEu.uri, drawable, z3);
            }
        }
    }

    public zza(Uri uri, int i) {
        this.zzaEu = new C0767zza(uri);
        this.zzaEw = i;
    }

    private Drawable zza(Context context, zzacd zzacd, int i) {
        return context.getResources().getDrawable(i);
    }

    /* access modifiers changed from: protected */
    public zzacb zza(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof zzacb) {
            drawable = ((zzacb) drawable).zzxs();
        }
        return new zzacb(drawable, drawable2);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzc.zzt(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzacd zzacd) {
        if (this.zzaEA) {
            zza((Drawable) null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzacd zzacd, boolean z) {
        Drawable drawable = null;
        if (this.zzaEw != 0) {
            drawable = zza(context, zzacd, this.zzaEw);
        }
        zza(drawable, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public boolean zzc(boolean z, boolean z2) {
        return this.zzaEy && !z2 && !z;
    }

    public void zzcO(int i) {
        this.zzaEw = i;
    }
}
