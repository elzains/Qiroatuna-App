package p003de.appplant.cordova.plugin.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.AbstractClearReceiver */
public abstract class AbstractClearReceiver extends BroadcastReceiver {
    public abstract void onClear(Notification notification);

    public void onReceive(Context context, Intent intent) {
        try {
            onClear(new Builder(context, new JSONObject(intent.getExtras().getString("NOTIFICATION_OPTIONS"))).build());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
