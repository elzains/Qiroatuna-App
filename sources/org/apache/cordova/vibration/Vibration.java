package org.apache.cordova.vibration;

import android.media.AudioManager;
import android.os.Vibrator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Vibration extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("vibrate")) {
            vibrate(args.getLong(0));
        } else if (action.equals("vibrateWithPattern")) {
            JSONArray pattern = args.getJSONArray(0);
            int repeat = args.getInt(1);
            long[] patternArray = new long[(pattern.length() + 1)];
            patternArray[0] = 0;
            for (int i = 0; i < pattern.length(); i++) {
                patternArray[i + 1] = pattern.getLong(i);
            }
            vibrateWithPattern(patternArray, repeat);
        } else if (!action.equals("cancelVibration")) {
            return false;
        } else {
            cancelVibration();
        }
        callbackContext.success();
        return true;
    }

    public void vibrate(long time) {
        if (time == 0) {
            time = 500;
        }
        if (((AudioManager) this.f21cordova.getActivity().getSystemService("audio")).getRingerMode() != 0) {
            ((Vibrator) this.f21cordova.getActivity().getSystemService("vibrator")).vibrate(time);
        }
    }

    public void vibrateWithPattern(long[] pattern, int repeat) {
        if (((AudioManager) this.f21cordova.getActivity().getSystemService("audio")).getRingerMode() != 0) {
            ((Vibrator) this.f21cordova.getActivity().getSystemService("vibrator")).vibrate(pattern, repeat);
        }
    }

    public void cancelVibration() {
        ((Vibrator) this.f21cordova.getActivity().getSystemService("vibrator")).cancel();
    }
}
