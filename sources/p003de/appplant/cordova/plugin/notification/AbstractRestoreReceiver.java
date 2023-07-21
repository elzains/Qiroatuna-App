package p003de.appplant.cordova.plugin.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.AbstractRestoreReceiver */
public abstract class AbstractRestoreReceiver extends BroadcastReceiver {
    public abstract Notification buildNotification(Builder builder);

    public abstract void onRestore(Notification notification);

    public void onReceive(Context context, Intent intent) {
        for (JSONObject data : Manager.getInstance(context).getOptions()) {
            onRestore(buildNotification(new Builder(context, data)));
        }
    }
}
