package p004id.myconstruct2game;

import android.os.Bundle;
import org.apache.cordova.CordovaActivity;

/* renamed from: id.myconstruct2game.MyGame2 */
public class MyGame2 extends CordovaActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }
        loadUrl(this.launchUrl);
    }
}
