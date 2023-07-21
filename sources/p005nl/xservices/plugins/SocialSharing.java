package p005nl.xservices.plugins;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.Base64;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: nl.xservices.plugins.SocialSharing */
public class SocialSharing extends CordovaPlugin {
    private static final String ACTION_AVAILABLE_EVENT = "available";
    private static final String ACTION_CAN_SHARE_VIA = "canShareVia";
    private static final String ACTION_CAN_SHARE_VIA_EMAIL = "canShareViaEmail";
    private static final String ACTION_SHARE_EVENT = "share";
    private static final String ACTION_SHARE_VIA = "shareVia";
    private static final String ACTION_SHARE_VIA_EMAIL_EVENT = "shareViaEmail";
    private static final String ACTION_SHARE_VIA_FACEBOOK_EVENT = "shareViaFacebook";
    private static final String ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT = "shareViaFacebookWithPasteMessageHint";
    private static final String ACTION_SHARE_VIA_INSTAGRAM_EVENT = "shareViaInstagram";
    private static final String ACTION_SHARE_VIA_SMS_EVENT = "shareViaSMS";
    private static final String ACTION_SHARE_VIA_TWITTER_EVENT = "shareViaTwitter";
    private static final String ACTION_SHARE_VIA_WHATSAPP_EVENT = "shareViaWhatsApp";
    private static final int ACTIVITY_CODE_SEND = 1;
    private static final int ACTIVITY_CODE_SENDVIAEMAIL = 2;
    private static final int ACTIVITY_CODE_SENDVIAWHATSAPP = 3;
    private CallbackContext _callbackContext;
    /* access modifiers changed from: private */
    public String pasteMessage;

    /* renamed from: nl.xservices.plugins.SocialSharing$SocialSharingRunnable */
    private abstract class SocialSharingRunnable implements Runnable {
        public CallbackContext callbackContext;

        SocialSharingRunnable(CallbackContext cb) {
            this.callbackContext = cb;
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this._callbackContext = callbackContext;
        this.pasteMessage = null;
        if (ACTION_AVAILABLE_EVENT.equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return true;
        } else if (ACTION_SHARE_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), (String) null, false);
        } else if (ACTION_SHARE_VIA_TWITTER_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "twitter", false);
        } else if (ACTION_SHARE_VIA_FACEBOOK_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "com.facebook.katana", false);
        } else if (ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT.equals(action)) {
            this.pasteMessage = args.getString(4);
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "com.facebook.katana", false);
        } else if (ACTION_SHARE_VIA_WHATSAPP_EVENT.equals(action)) {
            if (notEmpty(args.getString(4))) {
                return shareViaWhatsAppDirectly(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4));
            }
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "whatsapp", false);
        } else if (ACTION_SHARE_VIA_INSTAGRAM_EVENT.equals(action)) {
            if (notEmpty(args.getString(0))) {
                copyHintToClipboard(args.getString(0), "Instagram paste message");
            }
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "instagram", false);
        } else if (ACTION_CAN_SHARE_VIA.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4), true);
        } else if (ACTION_CAN_SHARE_VIA_EMAIL.equals(action)) {
            if (isEmailAvailable()) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                return true;
            }
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "not available"));
            return false;
        } else if (ACTION_SHARE_VIA.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4), false);
        } else if (ACTION_SHARE_VIA_SMS_EVENT.equals(action)) {
            return invokeSMSIntent(callbackContext, args.getJSONObject(0), args.getString(1));
        } else {
            if (ACTION_SHARE_VIA_EMAIL_EVENT.equals(action)) {
                return invokeEmailIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.isNull(3) ? null : args.getJSONArray(3), args.isNull(4) ? null : args.getJSONArray(4), args.isNull(5) ? null : args.getJSONArray(5));
            }
            callbackContext.error("socialSharing." + action + " is not a supported function. Did you mean '" + ACTION_SHARE_EVENT + "'?");
            return false;
        }
    }

    private boolean isEmailAvailable() {
        if (this.f21cordova.getActivity().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "someone@domain.com", (String) null)), 0).size() > 0) {
            return true;
        }
        return false;
    }

    private boolean invokeEmailIntent(CallbackContext callbackContext, String message, String subject, JSONArray to, JSONArray cc, JSONArray bcc, JSONArray files) throws JSONException {
        final String str = message;
        final String str2 = subject;
        final JSONArray jSONArray = to;
        final JSONArray jSONArray2 = cc;
        final JSONArray jSONArray3 = bcc;
        final JSONArray jSONArray4 = files;
        this.f21cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
            public void run() {
                String dir;
                Intent draft = new Intent("android.intent.action.SEND_MULTIPLE");
                if (SocialSharing.notEmpty(str)) {
                    if (Pattern.compile(".*\\<[^>]+>.*", 32).matcher(str).matches()) {
                        draft.putExtra("android.intent.extra.TEXT", Html.fromHtml(str));
                        draft.setType("text/html");
                    } else {
                        draft.putExtra("android.intent.extra.TEXT", str);
                        draft.setType("text/plain");
                    }
                }
                if (SocialSharing.notEmpty(str2)) {
                    draft.putExtra("android.intent.extra.SUBJECT", str2);
                }
                try {
                    if (jSONArray != null && jSONArray.length() > 0) {
                        draft.putExtra("android.intent.extra.EMAIL", SocialSharing.toStringArray(jSONArray));
                    }
                    if (jSONArray2 != null && jSONArray2.length() > 0) {
                        draft.putExtra("android.intent.extra.CC", SocialSharing.toStringArray(jSONArray2));
                    }
                    if (jSONArray3 != null && jSONArray3.length() > 0) {
                        draft.putExtra("android.intent.extra.BCC", SocialSharing.toStringArray(jSONArray3));
                    }
                    if (jSONArray4.length() > 0 && (dir = SocialSharing.this.getDownloadDir()) != null) {
                        ArrayList<Uri> fileUris = new ArrayList<>();
                        for (int i = 0; i < jSONArray4.length(); i++) {
                            Uri fileUri = SocialSharing.this.getFileUriAndSetType(draft, dir, jSONArray4.getString(i), str2, i);
                            if (fileUri != null) {
                                fileUris.add(fileUri);
                            }
                        }
                        if (!fileUris.isEmpty()) {
                            draft.putExtra("android.intent.extra.STREAM", fileUris);
                        }
                    }
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
                draft.addFlags(268435456);
                draft.setType("application/octet-stream");
                SocialSharing.this.f21cordova.startActivityForResult(this, Intent.createChooser(draft, "Choose Email App"), 2);
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public String getDownloadDir() throws IOException {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        String dir = this.webView.getContext().getExternalFilesDir((String) null) + "/socialsharing-downloads";
        createOrCleanDir(dir);
        return dir;
    }

    private boolean doSendIntent(CallbackContext callbackContext, String msg, String subject, JSONArray files, String url, String appPackageName, boolean peek) {
        final CordovaInterface mycordova = this.f21cordova;
        final String str = msg;
        final JSONArray jSONArray = files;
        final String str2 = subject;
        final String str3 = url;
        final String str4 = appPackageName;
        final boolean z = peek;
        this.f21cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
            /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x009f  */
            /* JADX WARNING: Removed duplicated region for block: B:38:0x00c6  */
            /* JADX WARNING: Removed duplicated region for block: B:43:0x00e1  */
            /* JADX WARNING: Removed duplicated region for block: B:67:0x018f  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r20 = this;
                    r0 = r20
                    java.lang.String r14 = r3
                    r0 = r20
                    org.json.JSONArray r2 = r4
                    int r2 = r2.length()
                    r5 = 1
                    if (r2 <= r5) goto L_0x0070
                    r12 = 1
                L_0x0010:
                    android.content.Intent r3 = new android.content.Intent
                    if (r12 == 0) goto L_0x0072
                    java.lang.String r2 = "android.intent.action.SEND_MULTIPLE"
                L_0x0016:
                    r3.<init>(r2)
                    r2 = 524288(0x80000, float:7.34684E-40)
                    r3.addFlags(r2)
                    r0 = r20
                    org.json.JSONArray r2 = r4     // Catch:{ Exception -> 0x0129 }
                    int r2 = r2.length()     // Catch:{ Exception -> 0x0129 }
                    if (r2 <= 0) goto L_0x013e
                    java.lang.String r2 = ""
                    r0 = r20
                    org.json.JSONArray r5 = r4     // Catch:{ Exception -> 0x0129 }
                    r6 = 0
                    java.lang.String r5 = r5.getString(r6)     // Catch:{ Exception -> 0x0129 }
                    boolean r2 = r2.equals(r5)     // Catch:{ Exception -> 0x0129 }
                    if (r2 != 0) goto L_0x013e
                    r0 = r20
                    nl.xservices.plugins.SocialSharing r2 = p005nl.xservices.plugins.SocialSharing.this     // Catch:{ Exception -> 0x0129 }
                    java.lang.String r4 = r2.getDownloadDir()     // Catch:{ Exception -> 0x0129 }
                    if (r4 == 0) goto L_0x0137
                    java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x0129 }
                    r11.<init>()     // Catch:{ Exception -> 0x0129 }
                    r10 = 0
                    r7 = 0
                L_0x004a:
                    r0 = r20
                    org.json.JSONArray r2 = r4     // Catch:{ Exception -> 0x0129 }
                    int r2 = r2.length()     // Catch:{ Exception -> 0x0129 }
                    if (r7 >= r2) goto L_0x0075
                    r0 = r20
                    nl.xservices.plugins.SocialSharing r2 = p005nl.xservices.plugins.SocialSharing.this     // Catch:{ Exception -> 0x0129 }
                    r0 = r20
                    org.json.JSONArray r5 = r4     // Catch:{ Exception -> 0x0129 }
                    java.lang.String r5 = r5.getString(r7)     // Catch:{ Exception -> 0x0129 }
                    r0 = r20
                    java.lang.String r6 = r5     // Catch:{ Exception -> 0x0129 }
                    android.net.Uri r10 = r2.getFileUriAndSetType(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0129 }
                    if (r10 == 0) goto L_0x006d
                    r11.add(r10)     // Catch:{ Exception -> 0x0129 }
                L_0x006d:
                    int r7 = r7 + 1
                    goto L_0x004a
                L_0x0070:
                    r12 = 0
                    goto L_0x0010
                L_0x0072:
                    java.lang.String r2 = "android.intent.action.SEND"
                    goto L_0x0016
                L_0x0075:
                    boolean r2 = r11.isEmpty()     // Catch:{ Exception -> 0x0129 }
                    if (r2 != 0) goto L_0x0082
                    if (r12 == 0) goto L_0x0122
                    java.lang.String r2 = "android.intent.extra.STREAM"
                    r3.putExtra(r2, r11)     // Catch:{ Exception -> 0x0129 }
                L_0x0082:
                    r0 = r20
                    java.lang.String r2 = r5
                    boolean r2 = p005nl.xservices.plugins.SocialSharing.notEmpty(r2)
                    if (r2 == 0) goto L_0x0095
                    java.lang.String r2 = "android.intent.extra.SUBJECT"
                    r0 = r20
                    java.lang.String r5 = r5
                    r3.putExtra(r2, r5)
                L_0x0095:
                    r0 = r20
                    java.lang.String r2 = r6
                    boolean r2 = p005nl.xservices.plugins.SocialSharing.notEmpty(r2)
                    if (r2 == 0) goto L_0x00c0
                    boolean r2 = p005nl.xservices.plugins.SocialSharing.notEmpty(r14)
                    if (r2 == 0) goto L_0x0145
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.StringBuilder r2 = r2.append(r14)
                    java.lang.String r5 = " "
                    java.lang.StringBuilder r2 = r2.append(r5)
                    r0 = r20
                    java.lang.String r5 = r6
                    java.lang.StringBuilder r2 = r2.append(r5)
                    java.lang.String r14 = r2.toString()
                L_0x00c0:
                    boolean r2 = p005nl.xservices.plugins.SocialSharing.notEmpty(r14)
                    if (r2 == 0) goto L_0x00d6
                    java.lang.String r2 = "android.intent.extra.TEXT"
                    r3.putExtra(r2, r14)
                    int r2 = android.os.Build.VERSION.SDK_INT
                    r5 = 21
                    if (r2 >= r5) goto L_0x00d6
                    java.lang.String r2 = "sms_body"
                    r3.putExtra(r2, r14)
                L_0x00d6:
                    r2 = 268435456(0x10000000, float:2.5243549E-29)
                    r3.addFlags(r2)
                    r0 = r20
                    java.lang.String r2 = r7
                    if (r2 == 0) goto L_0x018f
                    r0 = r20
                    java.lang.String r15 = r7
                    r16 = 0
                    java.lang.String r2 = "/"
                    boolean r2 = r15.contains(r2)
                    if (r2 == 0) goto L_0x00ff
                    r0 = r20
                    java.lang.String r2 = r7
                    java.lang.String r5 = "/"
                    java.lang.String[] r13 = r2.split(r5)
                    r2 = 0
                    r15 = r13[r2]
                    r2 = 1
                    r16 = r13[r2]
                L_0x00ff:
                    r0 = r20
                    nl.xservices.plugins.SocialSharing r2 = p005nl.xservices.plugins.SocialSharing.this
                    r0 = r20
                    org.apache.cordova.CallbackContext r5 = r0.callbackContext
                    android.content.pm.ActivityInfo r8 = r2.getActivity(r5, r3, r15)
                    if (r8 == 0) goto L_0x0121
                    r0 = r20
                    boolean r2 = r8
                    if (r2 == 0) goto L_0x014b
                    r0 = r20
                    org.apache.cordova.CallbackContext r2 = r0.callbackContext
                    org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
                    org.apache.cordova.PluginResult$Status r6 = org.apache.cordova.PluginResult.Status.OK
                    r5.<init>(r6)
                    r2.sendPluginResult(r5)
                L_0x0121:
                    return
                L_0x0122:
                    java.lang.String r2 = "android.intent.extra.STREAM"
                    r3.putExtra(r2, r10)     // Catch:{ Exception -> 0x0129 }
                    goto L_0x0082
                L_0x0129:
                    r9 = move-exception
                    r0 = r20
                    org.apache.cordova.CallbackContext r2 = r0.callbackContext
                    java.lang.String r5 = r9.getMessage()
                    r2.error((java.lang.String) r5)
                    goto L_0x0082
                L_0x0137:
                    java.lang.String r2 = "text/plain"
                    r3.setType(r2)     // Catch:{ Exception -> 0x0129 }
                    goto L_0x0082
                L_0x013e:
                    java.lang.String r2 = "text/plain"
                    r3.setType(r2)     // Catch:{ Exception -> 0x0129 }
                    goto L_0x0082
                L_0x0145:
                    r0 = r20
                    java.lang.String r14 = r6
                    goto L_0x00c0
                L_0x014b:
                    java.lang.String r2 = "android.intent.category.LAUNCHER"
                    r3.addCategory(r2)
                    android.content.ComponentName r2 = new android.content.ComponentName
                    android.content.pm.ApplicationInfo r5 = r8.applicationInfo
                    java.lang.String r5 = r5.packageName
                    if (r16 == 0) goto L_0x018a
                L_0x0158:
                    r0 = r16
                    r2.<init>(r5, r0)
                    r3.setComponent(r2)
                    r0 = r20
                    org.apache.cordova.CordovaInterface r2 = r9
                    r0 = r20
                    org.apache.cordova.CordovaPlugin r5 = r10
                    r6 = 0
                    r2.startActivityForResult(r5, r3, r6)
                    r0 = r20
                    nl.xservices.plugins.SocialSharing r2 = p005nl.xservices.plugins.SocialSharing.this
                    java.lang.String r2 = r2.pasteMessage
                    if (r2 == 0) goto L_0x0121
                    java.util.Timer r2 = new java.util.Timer
                    r2.<init>()
                    nl.xservices.plugins.SocialSharing$2$1 r5 = new nl.xservices.plugins.SocialSharing$2$1
                    r0 = r20
                    r5.<init>()
                    r18 = 2000(0x7d0, double:9.88E-321)
                    r0 = r18
                    r2.schedule(r5, r0)
                    goto L_0x0121
                L_0x018a:
                    java.lang.String r0 = r8.name
                    r16 = r0
                    goto L_0x0158
                L_0x018f:
                    r0 = r20
                    boolean r2 = r8
                    if (r2 == 0) goto L_0x01a5
                    r0 = r20
                    org.apache.cordova.CallbackContext r2 = r0.callbackContext
                    org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
                    org.apache.cordova.PluginResult$Status r6 = org.apache.cordova.PluginResult.Status.OK
                    r5.<init>(r6)
                    r2.sendPluginResult(r5)
                    goto L_0x0121
                L_0x01a5:
                    r0 = r20
                    org.apache.cordova.CordovaInterface r2 = r9
                    r0 = r20
                    org.apache.cordova.CordovaPlugin r5 = r10
                    r6 = 0
                    android.content.Intent r6 = android.content.Intent.createChooser(r3, r6)
                    r17 = 1
                    r0 = r17
                    r2.startActivityForResult(r5, r6, r0)
                    goto L_0x0121
                */
                throw new UnsupportedOperationException("Method not decompiled: p005nl.xservices.plugins.SocialSharing.C06382.run():void");
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void copyHintToClipboard(String msg, String label) {
        if (Build.VERSION.SDK_INT >= 11) {
            ((ClipboardManager) this.f21cordova.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(label, msg));
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void showPasteMessage(String label) {
        if (Build.VERSION.SDK_INT >= 11) {
            Toast toast = Toast.makeText(this.webView.getContext(), label, 1);
            toast.setGravity(17, 0, 0);
            toast.show();
        }
    }

    /* access modifiers changed from: private */
    public Uri getFileUriAndSetType(Intent sendIntent, String dir, String image, String subject, int nthFile) throws IOException {
        String fileName;
        String localImage = image;
        sendIntent.setType("image/*");
        if (image.startsWith("http") || image.startsWith("www/")) {
            String filename = getFileName(image);
            localImage = "file://" + dir + "/" + filename;
            if (image.startsWith("http")) {
                URLConnection connection = new URL(image).openConnection();
                String disposition = connection.getHeaderField("Content-Disposition");
                if (disposition != null) {
                    Matcher matcher = Pattern.compile("filename=([^;]+)").matcher(disposition);
                    if (matcher.find()) {
                        filename = matcher.group(1).replaceAll("[^a-zA-Z0-9._-]", "");
                        if (filename.length() == 0) {
                            filename = "file";
                        }
                        localImage = "file://" + dir + "/" + filename;
                    }
                }
                saveFile(getBytes(connection.getInputStream()), dir, filename);
            } else {
                saveFile(getBytes(this.webView.getContext().getAssets().open(image)), dir, filename);
            }
        } else if (image.startsWith("data:")) {
            if (!image.contains(";base64,")) {
                sendIntent.setType("text/plain");
                return null;
            }
            String encodedImg = image.substring(image.indexOf(";base64,") + 8);
            if (!image.contains("data:image/")) {
                sendIntent.setType(image.substring(image.indexOf("data:") + 5, image.indexOf(";base64")));
            }
            String imgExtension = image.substring(image.indexOf("/") + 1, image.indexOf(";base64"));
            if (notEmpty(subject)) {
                fileName = sanitizeFilename(subject) + (nthFile == 0 ? "" : "_" + nthFile) + "." + imgExtension;
            } else {
                fileName = "file" + (nthFile == 0 ? "" : "_" + nthFile) + "." + imgExtension;
            }
            saveFile(Base64.decode(encodedImg, 0), dir, fileName);
            localImage = "file://" + dir + "/" + fileName;
        } else if (image.startsWith("df:")) {
            if (!image.contains(";base64,")) {
                sendIntent.setType("text/plain");
                return null;
            }
            String fileName2 = image.substring(image.indexOf("df:") + 3, image.indexOf(";data:"));
            String fileType = image.substring(image.indexOf(";data:") + 6, image.indexOf(";base64,"));
            String encodedImg2 = image.substring(image.indexOf(";base64,") + 8);
            sendIntent.setType(fileType);
            saveFile(Base64.decode(encodedImg2, 0), dir, sanitizeFilename(fileName2));
            localImage = "file://" + dir + "/" + fileName2;
        } else if (!image.startsWith("file://")) {
            throw new IllegalArgumentException("URL_NOT_SUPPORTED");
        }
        return Uri.parse(localImage);
    }

    private boolean shareViaWhatsAppDirectly(CallbackContext callbackContext, String message, String subject, JSONArray files, String url, String number) {
        if (notEmpty(url)) {
            if (notEmpty(message)) {
                message = message + " " + url;
            } else {
                message = url;
            }
        }
        final String shareMessage = message;
        final String str = number;
        final String str2 = subject;
        final JSONArray jSONArray = files;
        this.f21cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) {
            public void run() {
                boolean hasMultipleAttachments = true;
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse("smsto:" + str));
                intent.putExtra("sms_body", shareMessage);
                intent.putExtra("sms_subject", str2);
                intent.setPackage("com.whatsapp");
                try {
                    if (jSONArray.length() > 0 && !"".equals(jSONArray.getString(0))) {
                        if (jSONArray.length() <= 1) {
                            hasMultipleAttachments = false;
                        }
                        String dir = SocialSharing.this.getDownloadDir();
                        if (dir != null) {
                            ArrayList<Uri> fileUris = new ArrayList<>();
                            Uri fileUri = null;
                            for (int i = 0; i < jSONArray.length(); i++) {
                                fileUri = SocialSharing.this.getFileUriAndSetType(intent, dir, jSONArray.getString(i), str2, i);
                                if (fileUri != null) {
                                    fileUris.add(fileUri);
                                }
                            }
                            if (!fileUris.isEmpty()) {
                                if (hasMultipleAttachments) {
                                    intent.putExtra("android.intent.extra.STREAM", fileUris);
                                } else {
                                    intent.putExtra("android.intent.extra.STREAM", fileUri);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
                try {
                    intent.addFlags(268435456);
                    SocialSharing.this.f21cordova.startActivityForResult(this, intent, 3);
                } catch (Exception e2) {
                    this.callbackContext.error(e2.getMessage());
                }
            }
        });
        return true;
    }

    private boolean invokeSMSIntent(CallbackContext callbackContext, JSONObject options, String p_phonenumbers) {
        final String message = options.optString("message");
        final String phonenumbers = getPhoneNumbersWithManufacturerSpecificSeparators(p_phonenumbers);
        this.f21cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext, (String) null, (String) null) {
            final /* synthetic */ String val$subject;

            {
                this.val$subject = r5;
            }

            public void run() {
                Intent intent;
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= 19) {
                    intent = new Intent("android.intent.action.SENDTO");
                    intent.setData(Uri.parse("smsto:" + (SocialSharing.notEmpty(phonenumbers) ? phonenumbers : "")));
                } else {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setType("vnd.android-dir/mms-sms");
                    if (phonenumbers != null) {
                        intent.putExtra("address", phonenumbers);
                    }
                }
                intent.putExtra("sms_body", message);
                intent.putExtra("sms_subject", this.val$subject);
                try {
                    if (!(null == null || "".equals(null) || (fileUri = SocialSharing.this.getFileUriAndSetType(intent, SocialSharing.this.getDownloadDir(), null, this.val$subject, 0)) == null)) {
                        intent.putExtra("android.intent.extra.STREAM", fileUri);
                    }
                    intent.addFlags(268435456);
                    SocialSharing.this.f21cordova.startActivityForResult(this, intent, 0);
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            }
        });
        return true;
    }

    private static String getPhoneNumbersWithManufacturerSpecificSeparators(String phonenumbers) {
        char separator;
        if (!notEmpty(phonenumbers)) {
            return null;
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
            separator = ',';
        } else {
            separator = ';';
        }
        return phonenumbers.replace(';', separator).replace(',', separator);
    }

    /* access modifiers changed from: private */
    public ActivityInfo getActivity(CallbackContext callbackContext, Intent shareIntent, String appPackageName) {
        List<ResolveInfo> activityList = this.webView.getContext().getPackageManager().queryIntentActivities(shareIntent, 0);
        for (ResolveInfo app : activityList) {
            if (app.activityInfo.packageName.contains(appPackageName)) {
                return app.activityInfo;
            }
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, getShareActivities(activityList)));
        return null;
    }

    private JSONArray getShareActivities(List<ResolveInfo> activityList) {
        List<String> packages = new ArrayList<>();
        for (ResolveInfo app : activityList) {
            packages.add(app.activityInfo.packageName);
        }
        return new JSONArray(packages);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (this._callbackContext == null) {
            return;
        }
        if (2 == requestCode) {
            this._callbackContext.success();
        } else {
            this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, resultCode == -1));
        }
    }

    private void createOrCleanDir(String downloadDir) throws IOException {
        File dir = new File(downloadDir);
        if (dir.exists()) {
            cleanupOldFiles(dir);
        } else if (!dir.mkdirs()) {
            throw new IOException("CREATE_DIRS_FAILED");
        }
    }

    private static String getFileName(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        Matcher m = Pattern.compile(".*/([^?#]+)?").matcher(url);
        if (m.find()) {
            return m.group(1);
        }
        return "file";
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[16384];
        while (true) {
            int nRead = is.read(data, 0, data.length);
            if (nRead != -1) {
                buffer.write(data, 0, nRead);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }

    private void saveFile(byte[] bytes, String dirName, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(new File(dirName), fileName));
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    private void cleanupOldFiles(File dir) {
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

    /* access modifiers changed from: private */
    public static boolean notEmpty(String what) {
        return what != null && !"".equals(what) && !"null".equalsIgnoreCase(what);
    }

    /* access modifiers changed from: private */
    public static String[] toStringArray(JSONArray jsonArray) throws JSONException {
        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    public static String sanitizeFilename(String name2) {
        return name2.replaceAll("[:\\\\/*?|<> ]", "_");
    }
}
