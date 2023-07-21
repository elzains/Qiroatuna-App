package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest {
    private final Date zzcR;
    private final AdRequest.Gender zzcS;
    private final Set<String> zzcT;
    private final boolean zzcU;
    private final Location zzcV;

    public MediationAdRequest(Date date, AdRequest.Gender gender, Set<String> set, boolean z, Location location) {
        this.zzcR = date;
        this.zzcS = gender;
        this.zzcT = set;
        this.zzcU = z;
        this.zzcV = location;
    }

    public Integer getAgeInYears() {
        if (this.zzcR == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(this.zzcR);
        Integer valueOf = Integer.valueOf(instance2.get(1) - instance.get(1));
        return (instance2.get(2) < instance.get(2) || (instance2.get(2) == instance.get(2) && instance2.get(5) < instance.get(5))) ? Integer.valueOf(valueOf.intValue() - 1) : valueOf;
    }

    public Date getBirthday() {
        return this.zzcR;
    }

    public AdRequest.Gender getGender() {
        return this.zzcS;
    }

    public Set<String> getKeywords() {
        return this.zzcT;
    }

    public Location getLocation() {
        return this.zzcV;
    }

    public boolean isTesting() {
        return this.zzcU;
    }
}
