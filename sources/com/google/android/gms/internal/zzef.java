package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzme
public class zzef {
    public static final zzef zzzx = new zzef();

    protected zzef() {
    }

    public static zzef zzeD() {
        return zzzx;
    }

    public zzec zza(Context context, zzfe zzfe) {
        Date birthday = zzfe.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = zzfe.getContentUrl();
        int gender = zzfe.getGender();
        Set<String> keywords = zzfe.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = zzfe.isTestDevice(context);
        int zzff = zzfe.zzff();
        Location location = zzfe.getLocation();
        Bundle networkExtrasBundle = zzfe.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzfe.getManualImpressionsEnabled();
        String publisherProvidedId = zzfe.getPublisherProvidedId();
        SearchAdRequest zzfc = zzfe.zzfc();
        zzfp zzfp = zzfc != null ? new zzfp(zzfc) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            str = zzel.zzeT().zza(Thread.currentThread().getStackTrace(), applicationContext.getPackageName());
        }
        return new zzec(7, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, zzff, manualImpressionsEnabled, publisherProvidedId, zzfp, location, contentUrl, zzfe.zzfe(), zzfe.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzfe.zzfg())), zzfe.zzfb(), str, zzfe.isDesignedForFamilies());
    }

    public zzoa zza(Context context, zzfe zzfe, String str) {
        return new zzoa(zza(context, zzfe), str);
    }
}
