package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.C0156R;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;

@zzme
public class zzkt extends zzkw {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzFP;

    public zzkt(zzqw zzqw, Map<String, String> map) {
        super(zzqw, "storePicture");
        this.zzFP = map;
        this.mContext = zzqw.zzlr();
    }

    public void execute() {
        if (this.mContext == null) {
            zzaz("Activity context is not available");
        } else if (!zzw.zzcM().zzN(this.mContext).zzfn()) {
            zzaz("Feature is not supported by the device.");
        } else {
            final String str = this.zzFP.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzaz("Image url cannot be empty.");
            } else if (!URLUtil.isValidUrl(str)) {
                String valueOf = String.valueOf(str);
                zzaz(valueOf.length() != 0 ? "Invalid image url: ".concat(valueOf) : new String("Invalid image url: "));
            } else {
                final String zzay = zzay(str);
                if (!zzw.zzcM().zzaZ(zzay)) {
                    String valueOf2 = String.valueOf(zzay);
                    zzaz(valueOf2.length() != 0 ? "Image type not recognized: ".concat(valueOf2) : new String("Image type not recognized: "));
                    return;
                }
                Resources resources = zzw.zzcQ().getResources();
                AlertDialog.Builder zzM = zzw.zzcM().zzM(this.mContext);
                zzM.setTitle(resources != null ? resources.getString(C0156R.string.store_picture_title) : "Save image");
                zzM.setMessage(resources != null ? resources.getString(C0156R.string.store_picture_message) : "Allow Ad to store image in Picture gallery?");
                zzM.setPositiveButton(resources != null ? resources.getString(C0156R.string.accept) : "Accept", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            ((DownloadManager) zzkt.this.mContext.getSystemService("download")).enqueue(zzkt.this.zzk(str, zzay));
                        } catch (IllegalStateException e) {
                            zzkt.this.zzaz("Could not store picture.");
                        }
                    }
                });
                zzM.setNegativeButton(resources != null ? resources.getString(C0156R.string.decline) : "Decline", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        zzkt.this.zzaz("User canceled the download.");
                    }
                });
                zzM.create().show();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String zzay(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    /* access modifiers changed from: package-private */
    public DownloadManager.Request zzk(String str, String str2) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        zzw.zzcO().zza(request);
        return request;
    }
}
