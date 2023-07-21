package name.ratson.cordova.admob;

import org.json.JSONObject;

public class CordovaEventBuilder {
    private String eventName;
    private String jsonData;

    public CordovaEventBuilder(String eventName2) {
        this.eventName = eventName2;
    }

    public CordovaEventBuilder withData(String data) {
        this.jsonData = data;
        return this;
    }

    public CordovaEventBuilder withData(JSONObject jsonObj) {
        if (jsonObj == null) {
            return withData("");
        }
        return withData(jsonObj.toString());
    }

    public String build() {
        StringBuilder js = new StringBuilder();
        js.append("javascript:cordova.fireDocumentEvent('");
        js.append(this.eventName);
        js.append("'");
        if (this.jsonData != null && !"".equals(this.jsonData)) {
            js.append(",");
            js.append(this.jsonData);
        }
        js.append(");");
        return js.toString();
    }
}
