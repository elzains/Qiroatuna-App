package org.apache.cordova.contacts;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ContactInfoDTO {
    JSONArray addresses = new JSONArray();
    String birthday;
    HashMap<String, Object> desiredFieldsWithVals = new HashMap<>();
    String displayName = "";
    JSONArray emails = new JSONArray();
    JSONArray ims = new JSONArray();

    /* renamed from: name  reason: collision with root package name */
    JSONObject f25name = new JSONObject();
    String nickname = "";
    String note = "";
    JSONArray organizations = new JSONArray();
    JSONArray phones = new JSONArray();
    JSONArray photos = new JSONArray();
    JSONArray websites = new JSONArray();
}
