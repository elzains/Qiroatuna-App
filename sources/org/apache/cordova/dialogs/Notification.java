package org.apache.cordova.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Notification extends CordovaPlugin {
    private static final String LOG_TAG = "Notification";
    public int confirmResult = -1;
    public ProgressDialog progressDialog = null;
    public ProgressDialog spinnerDialog = null;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (this.f21cordova.getActivity().isFinishing()) {
            return true;
        }
        if (action.equals("beep")) {
            beep(args.getLong(0));
        } else if (action.equals("alert")) {
            alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
            return true;
        } else if (action.equals("confirm")) {
            confirm(args.getString(0), args.getString(1), args.getJSONArray(2), callbackContext);
            return true;
        } else if (action.equals("prompt")) {
            prompt(args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), callbackContext);
            return true;
        } else if (action.equals("activityStart")) {
            activityStart(args.getString(0), args.getString(1));
        } else if (action.equals("activityStop")) {
            activityStop();
        } else if (action.equals("progressStart")) {
            progressStart(args.getString(0), args.getString(1));
        } else if (action.equals("progressValue")) {
            progressValue(args.getInt(0));
        } else if (!action.equals("progressStop")) {
            return false;
        } else {
            progressStop();
        }
        callbackContext.success();
        return true;
    }

    public void beep(final long count) {
        this.f21cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Ringtone notification = RingtoneManager.getRingtone(Notification.this.f21cordova.getActivity().getBaseContext(), RingtoneManager.getDefaultUri(2));
                if (notification != null) {
                    for (long i = 0; i < count; i++) {
                        notification.play();
                        long timeout = 5000;
                        while (notification.isPlaying() && timeout > 0) {
                            timeout -= 100;
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }
            }
        });
    }

    public synchronized void alert(String message, String title, String buttonLabel, CallbackContext callbackContext) {
        final CordovaInterface cordova2 = this.f21cordova;
        final String str = message;
        final String str2 = title;
        final String str3 = buttonLabel;
        final CallbackContext callbackContext2 = callbackContext;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder dlg = Notification.this.createDialog(cordova2);
                dlg.setMessage(str);
                dlg.setTitle(str2);
                dlg.setCancelable(true);
                dlg.setPositiveButton(str3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                });
                dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                });
                Notification.this.changeTextDirection(dlg);
            }
        });
    }

    public synchronized void confirm(String message, String title, JSONArray buttonLabels, CallbackContext callbackContext) {
        final CordovaInterface cordova2 = this.f21cordova;
        final String str = message;
        final String str2 = title;
        final JSONArray jSONArray = buttonLabels;
        final CallbackContext callbackContext2 = callbackContext;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder dlg = Notification.this.createDialog(cordova2);
                dlg.setMessage(str);
                dlg.setTitle(str2);
                dlg.setCancelable(true);
                if (jSONArray.length() > 0) {
                    try {
                        dlg.setNegativeButton(jSONArray.getString(0), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, 1));
                            }
                        });
                    } catch (JSONException e) {
                        LOG.m21d(Notification.LOG_TAG, "JSONException on first button.");
                    }
                }
                if (jSONArray.length() > 1) {
                    try {
                        dlg.setNeutralButton(jSONArray.getString(1), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, 2));
                            }
                        });
                    } catch (JSONException e2) {
                        LOG.m21d(Notification.LOG_TAG, "JSONException on second button.");
                    }
                }
                if (jSONArray.length() > 2) {
                    try {
                        dlg.setPositiveButton(jSONArray.getString(2), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, 3));
                            }
                        });
                    } catch (JSONException e3) {
                        LOG.m21d(Notification.LOG_TAG, "JSONException on third button.");
                    }
                }
                dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                });
                Notification.this.changeTextDirection(dlg);
            }
        });
    }

    public synchronized void prompt(String message, String title, JSONArray buttonLabels, String defaultText, CallbackContext callbackContext) {
        final CordovaInterface cordova2 = this.f21cordova;
        final String str = defaultText;
        final String str2 = message;
        final String str3 = title;
        final JSONArray jSONArray = buttonLabels;
        final CallbackContext callbackContext2 = callbackContext;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                final EditText promptInput = new EditText(cordova2.getActivity());
                promptInput.setHint(str);
                AlertDialog.Builder dlg = Notification.this.createDialog(cordova2);
                dlg.setMessage(str2);
                dlg.setTitle(str3);
                dlg.setCancelable(true);
                dlg.setView(promptInput);
                final JSONObject result = new JSONObject();
                if (jSONArray.length() > 0) {
                    try {
                        dlg.setNegativeButton(jSONArray.getString(0), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                try {
                                    result.put("buttonIndex", 1);
                                    result.put("input1", promptInput.getText().toString().trim().length() == 0 ? str : promptInput.getText());
                                } catch (JSONException e) {
                                    LOG.m22d(Notification.LOG_TAG, "JSONException on first button.", (Throwable) e);
                                }
                                callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
                            }
                        });
                    } catch (JSONException e) {
                        LOG.m21d(Notification.LOG_TAG, "JSONException on first button.");
                    }
                }
                if (jSONArray.length() > 1) {
                    try {
                        dlg.setNeutralButton(jSONArray.getString(1), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                try {
                                    result.put("buttonIndex", 2);
                                    result.put("input1", promptInput.getText().toString().trim().length() == 0 ? str : promptInput.getText());
                                } catch (JSONException e) {
                                    LOG.m22d(Notification.LOG_TAG, "JSONException on second button.", (Throwable) e);
                                }
                                callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
                            }
                        });
                    } catch (JSONException e2) {
                        LOG.m21d(Notification.LOG_TAG, "JSONException on second button.");
                    }
                }
                if (jSONArray.length() > 2) {
                    try {
                        dlg.setPositiveButton(jSONArray.getString(2), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                try {
                                    result.put("buttonIndex", 3);
                                    result.put("input1", promptInput.getText().toString().trim().length() == 0 ? str : promptInput.getText());
                                } catch (JSONException e) {
                                    LOG.m22d(Notification.LOG_TAG, "JSONException on third button.", (Throwable) e);
                                }
                                callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
                            }
                        });
                    } catch (JSONException e3) {
                        LOG.m21d(Notification.LOG_TAG, "JSONException on third button.");
                    }
                }
                dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                        try {
                            result.put("buttonIndex", 0);
                            result.put("input1", promptInput.getText().toString().trim().length() == 0 ? str : promptInput.getText());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        callbackContext2.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
                    }
                });
                Notification.this.changeTextDirection(dlg);
            }
        });
    }

    public synchronized void activityStart(String title, String message) {
        if (this.spinnerDialog != null) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
        final CordovaInterface cordova2 = this.f21cordova;
        final String str = title;
        final String str2 = message;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                this.spinnerDialog = Notification.this.createProgressDialog(cordova2);
                this.spinnerDialog.setTitle(str);
                this.spinnerDialog.setMessage(str2);
                this.spinnerDialog.setCancelable(true);
                this.spinnerDialog.setIndeterminate(true);
                this.spinnerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        this.spinnerDialog = null;
                    }
                });
                this.spinnerDialog.show();
            }
        });
    }

    public synchronized void activityStop() {
        if (this.spinnerDialog != null) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
    }

    public synchronized void progressStart(String title, String message) {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
        final CordovaInterface cordova2 = this.f21cordova;
        final String str = title;
        final String str2 = message;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                this.progressDialog = Notification.this.createProgressDialog(cordova2);
                this.progressDialog.setProgressStyle(1);
                this.progressDialog.setTitle(str);
                this.progressDialog.setMessage(str2);
                this.progressDialog.setCancelable(true);
                this.progressDialog.setMax(100);
                this.progressDialog.setProgress(0);
                this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        this.progressDialog = null;
                    }
                });
                this.progressDialog.show();
            }
        });
    }

    public synchronized void progressValue(int value) {
        if (this.progressDialog != null) {
            this.progressDialog.setProgress(value);
        }
    }

    public synchronized void progressStop() {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public AlertDialog.Builder createDialog(CordovaInterface cordova2) {
        if (Build.VERSION.SDK_INT >= 11) {
            return new AlertDialog.Builder(cordova2.getActivity(), 5);
        }
        return new AlertDialog.Builder(cordova2.getActivity());
    }

    /* access modifiers changed from: private */
    @SuppressLint({"InlinedApi"})
    public ProgressDialog createProgressDialog(CordovaInterface cordova2) {
        if (Build.VERSION.SDK_INT >= 14) {
            return new ProgressDialog(cordova2.getActivity(), 5);
        }
        return new ProgressDialog(cordova2.getActivity());
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void changeTextDirection(AlertDialog.Builder dlg) {
        int currentapiVersion = Build.VERSION.SDK_INT;
        dlg.create();
        AlertDialog dialog = dlg.show();
        if (currentapiVersion >= 17) {
            ((TextView) dialog.findViewById(16908299)).setTextDirection(5);
        }
    }
}
