package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzhl;
import java.util.List;

@zzme
public class zzgw extends zzhl.zza implements zzha.zzb {
    private Bundle mExtras;
    private zzha zzGA;
    private zzhf zzGB;
    private String zzGC;
    private String zzGp;
    private List<zzgu> zzGq;
    private String zzGr;
    private String zzGt;
    @Nullable
    private zzgs zzGx;
    @Nullable
    private zzfa zzGy;
    @Nullable
    private View zzGz;
    private Object zzrJ = new Object();

    public zzgw(String str, List list, String str2, zzhf zzhf, String str3, String str4, @Nullable zzgs zzgs, Bundle bundle, zzfa zzfa, View view) {
        this.zzGp = str;
        this.zzGq = list;
        this.zzGr = str2;
        this.zzGB = zzhf;
        this.zzGt = str3;
        this.zzGC = str4;
        this.zzGx = zzgs;
        this.mExtras = bundle;
        this.zzGy = zzfa;
        this.zzGz = view;
    }

    public void destroy() {
        this.zzGp = null;
        this.zzGq = null;
        this.zzGr = null;
        this.zzGB = null;
        this.zzGt = null;
        this.zzGC = null;
        this.zzGx = null;
        this.mExtras = null;
        this.zzrJ = null;
        this.zzGA = null;
        this.zzGy = null;
        this.zzGz = null;
    }

    public String getAdvertiser() {
        return this.zzGC;
    }

    public String getBody() {
        return this.zzGr;
    }

    public String getCallToAction() {
        return this.zzGt;
    }

    public String getCustomTemplateId() {
        return "";
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getHeadline() {
        return this.zzGp;
    }

    public List getImages() {
        return this.zzGq;
    }

    public void zzb(zzha zzha) {
        synchronized (this.zzrJ) {
            this.zzGA = zzha;
        }
    }

    public zzfa zzbF() {
        return this.zzGy;
    }

    public IObjectWrapper zzfR() {
        return zzd.zzA(this.zzGA);
    }

    public String zzfS() {
        return "1";
    }

    public zzgs zzfT() {
        return this.zzGx;
    }

    public View zzfU() {
        return this.zzGz;
    }

    public zzhf zzfV() {
        return this.zzGB;
    }
}
