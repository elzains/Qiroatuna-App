package p003de.appplant.cordova.plugin.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: de.appplant.cordova.plugin.notification.AbstractClickActivity */
public abstract class AbstractClickActivity extends Activity {
    public abstract Notification buildNotification(Builder builder);

    public abstract void onClick(Notification notification);

    public void onCreate(Bundle state) {
        super.onCreate(state);
        Bundle bundle = getIntent().getExtras();
        try {
            onClick(buildNotification(new Builder(getApplicationContext(), new JSONObject(bundle.getString("NOTIFICATION_OPTIONS")))));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        finish();
    }

    public void launchApp() {
        Context context = getApplicationContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(537001984);
        context.startActivity(intent);
    }
}
