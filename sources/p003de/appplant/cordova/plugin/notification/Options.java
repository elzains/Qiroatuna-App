package p003de.appplant.cordova.plugin.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.p000v4.view.ViewCompat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.Options */
public class Options {
    static final String EXTRA = "NOTIFICATION_OPTIONS";
    private final AssetUtil assets;
    private final Context context;
    private long interval = 0;
    private JSONObject options = new JSONObject();

    public Options(Context context2) {
        this.context = context2;
        this.assets = AssetUtil.getInstance(context2);
    }

    public Options parse(JSONObject options2) {
        this.options = options2;
        parseInterval();
        parseAssets();
        return this;
    }

    private void parseInterval() {
        String every = this.options.optString("every").toLowerCase();
        if (every.isEmpty()) {
            this.interval = 0;
        } else if (every.equals("second")) {
            this.interval = 1000;
        } else if (every.equals("minute")) {
            this.interval = 60000;
        } else if (every.equals("hour")) {
            this.interval = 3600000;
        } else if (every.equals("day")) {
            this.interval = 86400000;
        } else if (every.equals("week")) {
            this.interval = 604800000;
        } else if (every.equals("month")) {
            this.interval = 2678400000L;
        } else if (every.equals("quarter")) {
            this.interval = 7884000000L;
        } else if (every.equals("year")) {
            this.interval = 31536000000L;
        } else {
            try {
                this.interval = (long) (Integer.parseInt(every) * 60000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseAssets() {
        if (!this.options.has("iconUri") || this.options.optBoolean("updated")) {
            Uri iconUri = this.assets.parse(this.options.optString("icon", "icon"));
            Uri soundUri = this.assets.parseSound(this.options.optString("sound", (String) null));
            try {
                this.options.put("iconUri", iconUri.toString());
                this.options.put("soundUri", soundUri.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public JSONObject getDict() {
        return this.options;
    }

    public String getText() {
        return this.options.optString("text", "");
    }

    public long getRepeatInterval() {
        return this.interval;
    }

    public int getBadgeNumber() {
        return this.options.optInt("badge", 0);
    }

    public Boolean isOngoing() {
        return Boolean.valueOf(this.options.optBoolean("ongoing", false));
    }

    public Boolean isAutoClear() {
        return Boolean.valueOf(this.options.optBoolean("autoClear", false));
    }

    public Integer getId() {
        return Integer.valueOf(this.options.optInt("id", 0));
    }

    public String getIdStr() {
        return getId().toString();
    }

    public Date getTriggerDate() {
        return new Date(getTriggerTime());
    }

    public long getTriggerTime() {
        return this.options.optLong("at", 0) * 1000;
    }

    public String getTitle() {
        String title = this.options.optString("title", "");
        if (title.isEmpty()) {
            return this.context.getApplicationInfo().loadLabel(this.context.getPackageManager()).toString();
        }
        return title;
    }

    public int getLedColor() {
        String hex = this.options.optString("led", (String) null);
        if (hex == null) {
            return 4;
        }
        return ViewCompat.MEASURED_STATE_MASK + Integer.parseInt(hex, 16);
    }

    public int getColor() {
        String hex = this.options.optString("color", (String) null);
        if (hex == null) {
            return 0;
        }
        return ViewCompat.MEASURED_STATE_MASK + Integer.parseInt(hex, 16);
    }

    public Uri getSoundUri() {
        try {
            return Uri.parse(this.options.optString("soundUri"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap getIconBitmap() {
        try {
            return this.assets.getIconFromUri(Uri.parse(this.options.optString("iconUri")));
        } catch (Exception e) {
            e.printStackTrace();
            return this.assets.getIconFromDrawable("icon");
        }
    }

    public int getIcon() {
        int resId = this.assets.getResIdForDrawable(this.options.optString("icon", ""));
        if (resId == 0) {
            resId = getSmallIcon();
        }
        if (resId == 0) {
            return 17301598;
        }
        return resId;
    }

    public int getSmallIcon() {
        return this.assets.getResIdForDrawable(this.options.optString("smallIcon", ""));
    }

    public String toString() {
        return this.options.toString();
    }
}
