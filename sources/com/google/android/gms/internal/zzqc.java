package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.common.zzg;
import java.util.concurrent.Callable;

@zzme
@TargetApi(17)
public class zzqc {
    private static zzqc zzYH = null;
    String zzIA;

    private zzqc() {
    }

    public static zzqc zzld() {
        if (zzYH == null) {
            zzYH = new zzqc();
        }
        return zzYH;
    }

    public void zzaa(final Context context) {
        if (TextUtils.isEmpty(this.zzIA)) {
            final Context remoteContext = zzg.getRemoteContext(context);
            this.zzIA = (String) zzqb.zzb(new Callable<String>(this) {
                /* renamed from: zzbY */
                public String call() {
                    SharedPreferences sharedPreferences;
                    boolean z = true;
                    if (remoteContext != null) {
                        zzpk.m19v("Attempting to read user agent from Google Play Services.");
                        sharedPreferences = remoteContext.getSharedPreferences("admob_user_agent", 1);
                        z = false;
                    } else {
                        zzpk.m19v("Attempting to read user agent from local cache.");
                        sharedPreferences = context.getSharedPreferences("admob_user_agent", 0);
                    }
                    String string = sharedPreferences.getString("user_agent", "");
                    if (TextUtils.isEmpty(string)) {
                        zzpk.m19v("Reading user agent from WebSettings");
                        string = WebSettings.getDefaultUserAgent(context);
                        if (z) {
                            context.getSharedPreferences("admob_user_agent", 0).edit().putString("user_agent", string).apply();
                            zzpk.m19v("Persisting user agent.");
                        }
                    }
                    return string;
                }
            });
        }
    }

    public void zzab(Context context) {
        zzpk.m19v("Updating user agent.");
        String defaultUserAgent = WebSettings.getDefaultUserAgent(context);
        if (!defaultUserAgent.equals(this.zzIA)) {
            if (zzg.getRemoteContext(context) == null) {
                context.getSharedPreferences("admob_user_agent", 0).edit().putString("user_agent", WebSettings.getDefaultUserAgent(context)).apply();
            }
            this.zzIA = defaultUserAgent;
        }
        zzpk.m19v("User agent is updated.");
    }

    public String zzac(Context context) {
        zzaa(context);
        return this.zzIA;
    }
}
