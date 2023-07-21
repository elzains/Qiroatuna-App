package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class zzx implements zzz {
    protected final HttpClient zzaD;

    public static final class zza extends HttpEntityEnclosingRequestBase {
        public zza() {
        }

        public zza(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    public zzx(HttpClient httpClient) {
        this.zzaD = httpClient;
    }

    private static void zza(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, zzl<?> zzl) throws zza {
        byte[] zzm = zzl.zzm();
        if (zzm != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(zzm));
        }
    }

    private static void zza(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String next : map.keySet()) {
            httpUriRequest.setHeader(next, map.get(next));
        }
    }

    static HttpUriRequest zzb(zzl<?> zzl, Map<String, String> map) throws zza {
        switch (zzl.getMethod()) {
            case -1:
                byte[] zzj = zzl.zzj();
                if (zzj == null) {
                    return new HttpGet(zzl.getUrl());
                }
                HttpPost httpPost = new HttpPost(zzl.getUrl());
                httpPost.addHeader("Content-Type", zzl.zzi());
                httpPost.setEntity(new ByteArrayEntity(zzj));
                return httpPost;
            case 0:
                return new HttpGet(zzl.getUrl());
            case 1:
                HttpPost httpPost2 = new HttpPost(zzl.getUrl());
                httpPost2.addHeader("Content-Type", zzl.zzl());
                zza((HttpEntityEnclosingRequestBase) httpPost2, zzl);
                return httpPost2;
            case 2:
                HttpPut httpPut = new HttpPut(zzl.getUrl());
                httpPut.addHeader("Content-Type", zzl.zzl());
                zza((HttpEntityEnclosingRequestBase) httpPut, zzl);
                return httpPut;
            case 3:
                return new HttpDelete(zzl.getUrl());
            case 4:
                return new HttpHead(zzl.getUrl());
            case 5:
                return new HttpOptions(zzl.getUrl());
            case 6:
                return new HttpTrace(zzl.getUrl());
            case 7:
                zza zza2 = new zza(zzl.getUrl());
                zza2.addHeader("Content-Type", zzl.zzl());
                zza((HttpEntityEnclosingRequestBase) zza2, zzl);
                return zza2;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    public HttpResponse zza(zzl<?> zzl, Map<String, String> map) throws IOException, zza {
        HttpUriRequest zzb = zzb(zzl, map);
        zza(zzb, map);
        zza(zzb, zzl.getHeaders());
        HttpParams params = zzb.getParams();
        int zzp = zzl.zzp();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, zzp);
        return this.zzaD.execute(zzb);
    }
}
