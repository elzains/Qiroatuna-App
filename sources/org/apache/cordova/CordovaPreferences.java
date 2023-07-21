package org.apache.cordova;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CordovaPreferences {
    private Bundle preferencesBundleExtras;
    private HashMap<String, String> prefs = new HashMap<>(20);

    public void setPreferencesBundle(Bundle extras) {
        this.preferencesBundleExtras = extras;
    }

    public void set(String name2, String value) {
        this.prefs.put(name2.toLowerCase(Locale.ENGLISH), value);
    }

    public void set(String name2, boolean value) {
        set(name2, "" + value);
    }

    public void set(String name2, int value) {
        set(name2, "" + value);
    }

    public void set(String name2, double value) {
        set(name2, "" + value);
    }

    public Map<String, String> getAll() {
        return this.prefs;
    }

    public boolean getBoolean(String name2, boolean defaultValue) {
        String value = this.prefs.get(name2.toLowerCase(Locale.ENGLISH));
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public boolean contains(String name2) {
        return getString(name2, (String) null) != null;
    }

    public int getInteger(String name2, int defaultValue) {
        String value = this.prefs.get(name2.toLowerCase(Locale.ENGLISH));
        if (value != null) {
            return (int) Long.decode(value).longValue();
        }
        return defaultValue;
    }

    public double getDouble(String name2, double defaultValue) {
        String value = this.prefs.get(name2.toLowerCase(Locale.ENGLISH));
        if (value != null) {
            return Double.valueOf(value).doubleValue();
        }
        return defaultValue;
    }

    public String getString(String name2, String defaultValue) {
        String value = this.prefs.get(name2.toLowerCase(Locale.ENGLISH));
        return value != null ? value : defaultValue;
    }
}
