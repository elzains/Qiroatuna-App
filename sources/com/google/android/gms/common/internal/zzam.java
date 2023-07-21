package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C0156R;

public class zzam {
    private final Resources zzaGK;
    private final String zzaGL = this.zzaGK.getResourcePackageName(C0156R.string.common_google_play_services_unknown_issue);

    public zzam(Context context) {
        zzac.zzw(context);
        this.zzaGK = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.zzaGK.getIdentifier(str, "string", this.zzaGL);
        if (identifier == 0) {
            return null;
        }
        return this.zzaGK.getString(identifier);
    }
}
