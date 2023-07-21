package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p000v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.zza;
import com.google.android.gms.internal.zzacd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    /* access modifiers changed from: private */
    public static final Object zzaEf = new Object();
    /* access modifiers changed from: private */
    public static HashSet<Uri> zzaEg = new HashSet<>();
    private static ImageManager zzaEh;
    private static ImageManager zzaEi;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final ExecutorService zzaEj = Executors.newFixedThreadPool(4);
    /* access modifiers changed from: private */
    public final zza zzaEk;
    /* access modifiers changed from: private */
    public final zzacd zzaEl;
    /* access modifiers changed from: private */
    public final Map<zza, ImageReceiver> zzaEm;
    /* access modifiers changed from: private */
    public final Map<Uri, ImageReceiver> zzaEn;
    /* access modifiers changed from: private */
    public final Map<Uri, Long> zzaEo;

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        /* access modifiers changed from: private */
        public final ArrayList<zza> zzaEp = new ArrayList<>();

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public void onReceiveResult(int i, Bundle bundle) {
            ImageManager.this.zzaEj.execute(new zzb(this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public void zzb(zza zza) {
            com.google.android.gms.common.internal.zzc.zzdj("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzaEp.add(zza);
        }

        public void zzc(zza zza) {
            com.google.android.gms.common.internal.zzc.zzdj("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzaEp.remove(zza);
        }

        public void zzxr() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class zza extends LruCache<zza.C0767zza, Bitmap> {
        public zza(Context context) {
            super(zzaR(context));
        }

        private static int zzaR(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (int) (((float) (((context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass()) * 1048576)) * 0.33f);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public int sizeOf(zza.C0767zza zza, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public void entryRemoved(boolean z, zza.C0767zza zza, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, zza, bitmap, bitmap2);
        }
    }

    private final class zzb implements Runnable {
        private final Uri mUri;
        private final ParcelFileDescriptor zzaEr;

        public zzb(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.zzaEr = parcelFileDescriptor;
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzdk("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zzaEr != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.zzaEr.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.mUri);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z = true;
                }
                try {
                    this.zzaEr.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(new zze(this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.mUri);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    private final class zzc implements Runnable {
        private final zza zzaEs;

        public zzc(zza zza) {
            this.zzaEs = zza;
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzdj("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzaEm.get(this.zzaEs);
            if (imageReceiver != null) {
                ImageManager.this.zzaEm.remove(this.zzaEs);
                imageReceiver.zzc(this.zzaEs);
            }
            zza.C0767zza zza = this.zzaEs.zzaEu;
            if (zza.uri == null) {
                this.zzaEs.zza(ImageManager.this.mContext, ImageManager.this.zzaEl, true);
                return;
            }
            Bitmap zza2 = ImageManager.this.zza(zza);
            if (zza2 != null) {
                this.zzaEs.zza(ImageManager.this.mContext, zza2, true);
                return;
            }
            Long l = (Long) ImageManager.this.zzaEo.get(zza.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zzaEs.zza(ImageManager.this.mContext, ImageManager.this.zzaEl, true);
                    return;
                }
                ImageManager.this.zzaEo.remove(zza.uri);
            }
            this.zzaEs.zza(ImageManager.this.mContext, ImageManager.this.zzaEl);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.zzaEn.get(zza.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = new ImageReceiver(zza.uri);
                ImageManager.this.zzaEn.put(zza.uri, imageReceiver2);
            }
            imageReceiver2.zzb(this.zzaEs);
            if (!(this.zzaEs instanceof zza.zzc)) {
                ImageManager.this.zzaEm.put(this.zzaEs, imageReceiver2);
            }
            synchronized (ImageManager.zzaEf) {
                if (!ImageManager.zzaEg.contains(zza.uri)) {
                    ImageManager.zzaEg.add(zza.uri);
                    imageReceiver2.zzxr();
                }
            }
        }
    }

    private static final class zzd implements ComponentCallbacks2 {
        private final zza zzaEk;

        public zzd(zza zza) {
            this.zzaEk = zza;
        }

        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
            this.zzaEk.evictAll();
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                this.zzaEk.evictAll();
            } else if (i >= 20) {
                this.zzaEk.trimToSize(this.zzaEk.size() / 2);
            }
        }
    }

    private final class zze implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private boolean zzaEt;
        private final CountDownLatch zztj;

        public zze(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zzaEt = z;
            this.zztj = countDownLatch;
        }

        private void zza(ImageReceiver imageReceiver, boolean z) {
            ArrayList zza = imageReceiver.zzaEp;
            int size = zza.size();
            for (int i = 0; i < size; i++) {
                zza zza2 = (zza) zza.get(i);
                if (z) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.zzaEo.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.zzaEl, false);
                }
                if (!(zza2 instanceof zza.zzc)) {
                    ImageManager.this.zzaEm.remove(zza2);
                }
            }
        }

        public void run() {
            com.google.android.gms.common.internal.zzc.zzdj("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.zzaEk != null) {
                if (this.zzaEt) {
                    ImageManager.this.zzaEk.evictAll();
                    System.gc();
                    this.zzaEt = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (z) {
                    ImageManager.this.zzaEk.put(new zza.C0767zza(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.zzaEn.remove(this.mUri);
            if (imageReceiver != null) {
                zza(imageReceiver, z);
            }
            this.zztj.countDown();
            synchronized (ImageManager.zzaEf) {
                ImageManager.zzaEg.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
        if (z) {
            this.zzaEk = new zza(this.mContext);
            this.mContext.registerComponentCallbacks(new zzd(this.zzaEk));
        } else {
            this.zzaEk = null;
        }
        this.zzaEl = new zzacd();
        this.zzaEm = new HashMap();
        this.zzaEn = new HashMap();
        this.zzaEo = new HashMap();
    }

    public static ImageManager create(Context context) {
        return zzg(context, false);
    }

    /* access modifiers changed from: private */
    public Bitmap zza(zza.C0767zza zza2) {
        if (this.zzaEk == null) {
            return null;
        }
        return (Bitmap) this.zzaEk.get(zza2);
    }

    public static ImageManager zzg(Context context, boolean z) {
        if (z) {
            if (zzaEi == null) {
                zzaEi = new ImageManager(context, true);
            }
            return zzaEi;
        }
        if (zzaEh == null) {
            zzaEh = new ImageManager(context, false);
        }
        return zzaEh;
    }

    public void loadImage(ImageView imageView, int i) {
        zza((zza) new zza.zzb(imageView, i));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        zza((zza) new zza.zzb(imageView, uri));
    }

    public void loadImage(ImageView imageView, Uri uri, int i) {
        zza.zzb zzb2 = new zza.zzb(imageView, uri);
        zzb2.zzcO(i);
        zza((zza) zzb2);
    }

    public void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zza((zza) new zza.zzc(onImageLoadedListener, uri));
    }

    public void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zza.zzc zzc2 = new zza.zzc(onImageLoadedListener, uri);
        zzc2.zzcO(i);
        zza((zza) zzc2);
    }

    public void zza(zza zza2) {
        com.google.android.gms.common.internal.zzc.zzdj("ImageManager.loadImage() must be called in the main thread");
        new zzc(zza2).run();
    }
}
