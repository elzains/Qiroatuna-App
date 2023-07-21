package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class zzaa implements zzz {
    private final zza zzaE;
    private final SSLSocketFactory zzaF;

    public interface zza {
        String zzh(String str);
    }

    public zzaa() {
        this((zza) null);
    }

    public zzaa(zza zza2) {
        this(zza2, (SSLSocketFactory) null);
    }

    public zzaa(zza zza2, SSLSocketFactory sSLSocketFactory) {
        this.zzaE = zza2;
        this.zzaF = sSLSocketFactory;
    }

    private HttpURLConnection zza(URL url, zzl<?> zzl) throws IOException {
        HttpURLConnection zza2 = zza(url);
        int zzp = zzl.zzp();
        zza2.setConnectTimeout(zzp);
        zza2.setReadTimeout(zzp);
        zza2.setUseCaches(false);
        zza2.setDoInput(true);
        if ("https".equals(url.getProtocol()) && this.zzaF != null) {
            ((HttpsURLConnection) zza2).setSSLSocketFactory(this.zzaF);
        }
        return zza2;
    }

    private static HttpEntity zza(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            errorStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(errorStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    static void zza(HttpURLConnection httpURLConnection, zzl<?> zzl) throws IOException, zza {
        switch (zzl.getMethod()) {
            case -1:
                byte[] zzj = zzl.zzj();
                if (zzj != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", zzl.zzi());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(zzj);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                zzb(httpURLConnection, zzl);
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zzb(httpURLConnection, zzl);
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zzb(httpURLConnection, zzl);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static boolean zza(int i, int i2) {
        return (i == 4 || (100 <= i2 && i2 < 200) || i2 == 204 || i2 == 304) ? false : true;
    }

    private static void zzb(HttpURLConnection httpURLConnection, zzl<?> zzl) throws IOException, zza {
        byte[] zzm = zzl.zzm();
        if (zzm != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzl.zzl());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzm);
            dataOutputStream.close();
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection zza(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    public HttpResponse zza(zzl<?> zzl, Map<String, String> map) throws IOException, zza {
        String str;
        String url = zzl.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzl.getHeaders());
        hashMap.putAll(map);
        if (this.zzaE != null) {
            str = this.zzaE.zzh(url);
            if (str == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }
        } else {
            str = url;
        }
        HttpURLConnection zza2 = zza(new URL(str), zzl);
        for (String str2 : hashMap.keySet()) {
            zza2.addRequestProperty(str2, (String) hashMap.get(str2));
        }
        zza(zza2, zzl);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (zza2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        BasicStatusLine basicStatusLine = new BasicStatusLine(protocolVersion, zza2.getResponseCode(), zza2.getResponseMessage());
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        if (zza(zzl.getMethod(), basicStatusLine.getStatusCode())) {
            basicHttpResponse.setEntity(zza(zza2));
        }
        for (Map.Entry entry : zza2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
