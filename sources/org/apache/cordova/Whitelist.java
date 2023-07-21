package org.apache.cordova;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Whitelist {
    public static final String TAG = "Whitelist";
    private ArrayList<URLPattern> whiteList = new ArrayList<>();

    private static class URLPattern {
        public Pattern host;
        public Pattern path;
        public Integer port;
        public Pattern scheme;

        private String regexFromPattern(String pattern, boolean allowWildcards) {
            StringBuilder regex = new StringBuilder();
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (c == '*' && allowWildcards) {
                    regex.append(".");
                } else if ("\\.[]{}()^$?+|".indexOf(c) > -1) {
                    regex.append('\\');
                }
                regex.append(c);
            }
            return regex.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x0025 A[Catch:{ NumberFormatException -> 0x0043 }] */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[Catch:{ NumberFormatException -> 0x0043 }] */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0086 A[Catch:{ NumberFormatException -> 0x0043 }] */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0093 A[Catch:{ NumberFormatException -> 0x0043 }] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0018 A[Catch:{ NumberFormatException -> 0x0043 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public URLPattern(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.net.MalformedURLException {
            /*
                r4 = this;
                r4.<init>()
                if (r5 == 0) goto L_0x000d
                java.lang.String r1 = "*"
                boolean r1 = r1.equals(r5)     // Catch:{ NumberFormatException -> 0x0043 }
                if (r1 == 0) goto L_0x0036
            L_0x000d:
                r1 = 0
                r4.scheme = r1     // Catch:{ NumberFormatException -> 0x0043 }
            L_0x0010:
                java.lang.String r1 = "*"
                boolean r1 = r1.equals(r6)     // Catch:{ NumberFormatException -> 0x0043 }
                if (r1 == 0) goto L_0x004c
                r1 = 0
                r4.host = r1     // Catch:{ NumberFormatException -> 0x0043 }
            L_0x001b:
                if (r7 == 0) goto L_0x0025
                java.lang.String r1 = "*"
                boolean r1 = r1.equals(r7)     // Catch:{ NumberFormatException -> 0x0043 }
                if (r1 == 0) goto L_0x0086
            L_0x0025:
                r1 = 0
                r4.port = r1     // Catch:{ NumberFormatException -> 0x0043 }
            L_0x0028:
                if (r8 == 0) goto L_0x0032
                java.lang.String r1 = "/*"
                boolean r1 = r1.equals(r8)     // Catch:{ NumberFormatException -> 0x0043 }
                if (r1 == 0) goto L_0x0093
            L_0x0032:
                r1 = 0
                r4.path = r1     // Catch:{ NumberFormatException -> 0x0043 }
            L_0x0035:
                return
            L_0x0036:
                r1 = 0
                java.lang.String r1 = r4.regexFromPattern(r5, r1)     // Catch:{ NumberFormatException -> 0x0043 }
                r2 = 2
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)     // Catch:{ NumberFormatException -> 0x0043 }
                r4.scheme = r1     // Catch:{ NumberFormatException -> 0x0043 }
                goto L_0x0010
            L_0x0043:
                r0 = move-exception
                java.net.MalformedURLException r1 = new java.net.MalformedURLException
                java.lang.String r2 = "Port must be a number"
                r1.<init>(r2)
                throw r1
            L_0x004c:
                java.lang.String r1 = "*."
                boolean r1 = r6.startsWith(r1)     // Catch:{ NumberFormatException -> 0x0043 }
                if (r1 == 0) goto L_0x0079
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0043 }
                r1.<init>()     // Catch:{ NumberFormatException -> 0x0043 }
                java.lang.String r2 = "([a-z0-9.-]*\\.)?"
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ NumberFormatException -> 0x0043 }
                r2 = 2
                java.lang.String r2 = r6.substring(r2)     // Catch:{ NumberFormatException -> 0x0043 }
                r3 = 0
                java.lang.String r2 = r4.regexFromPattern(r2, r3)     // Catch:{ NumberFormatException -> 0x0043 }
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ NumberFormatException -> 0x0043 }
                java.lang.String r1 = r1.toString()     // Catch:{ NumberFormatException -> 0x0043 }
                r2 = 2
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)     // Catch:{ NumberFormatException -> 0x0043 }
                r4.host = r1     // Catch:{ NumberFormatException -> 0x0043 }
                goto L_0x001b
            L_0x0079:
                r1 = 0
                java.lang.String r1 = r4.regexFromPattern(r6, r1)     // Catch:{ NumberFormatException -> 0x0043 }
                r2 = 2
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1, r2)     // Catch:{ NumberFormatException -> 0x0043 }
                r4.host = r1     // Catch:{ NumberFormatException -> 0x0043 }
                goto L_0x001b
            L_0x0086:
                r1 = 10
                int r1 = java.lang.Integer.parseInt(r7, r1)     // Catch:{ NumberFormatException -> 0x0043 }
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ NumberFormatException -> 0x0043 }
                r4.port = r1     // Catch:{ NumberFormatException -> 0x0043 }
                goto L_0x0028
            L_0x0093:
                r1 = 1
                java.lang.String r1 = r4.regexFromPattern(r8, r1)     // Catch:{ NumberFormatException -> 0x0043 }
                java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch:{ NumberFormatException -> 0x0043 }
                r4.path = r1     // Catch:{ NumberFormatException -> 0x0043 }
                goto L_0x0035
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.Whitelist.URLPattern.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
        }

        public boolean matches(Uri uri) {
            try {
                if (this.scheme != null && !this.scheme.matcher(uri.getScheme()).matches()) {
                    return false;
                }
                if (this.host != null && !this.host.matcher(uri.getHost()).matches()) {
                    return false;
                }
                if (this.port != null && !this.port.equals(Integer.valueOf(uri.getPort()))) {
                    return false;
                }
                if (this.path == null || this.path.matcher(uri.getPath()).matches()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                LOG.m21d(Whitelist.TAG, e.toString());
                return false;
            }
        }
    }

    public void addWhiteListEntry(String origin, boolean subdomains) {
        if (this.whiteList != null) {
            try {
                if (origin.compareTo("*") == 0) {
                    LOG.m21d(TAG, "Unlimited access to network resources");
                    this.whiteList = null;
                    return;
                }
                Matcher m = Pattern.compile("^((\\*|[A-Za-z-]+):(//)?)?(\\*|((\\*\\.)?[^*/:]+))?(:(\\d+))?(/.*)?").matcher(origin);
                if (m.matches()) {
                    String scheme = m.group(2);
                    String host = m.group(4);
                    if (("file".equals(scheme) || "content".equals(scheme)) && host == null) {
                        host = "*";
                    }
                    String port = m.group(8);
                    String path = m.group(9);
                    if (scheme == null) {
                        this.whiteList.add(new URLPattern("http", host, port, path));
                        this.whiteList.add(new URLPattern("https", host, port, path));
                        return;
                    }
                    this.whiteList.add(new URLPattern(scheme, host, port, path));
                }
            } catch (Exception e) {
                LOG.m23d(TAG, "Failed to add origin %s", origin);
            }
        }
    }

    public boolean isUrlWhiteListed(String uri) {
        if (this.whiteList == null) {
            return true;
        }
        Uri parsedUri = Uri.parse(uri);
        Iterator<URLPattern> pit = this.whiteList.iterator();
        while (pit.hasNext()) {
            if (pit.next().matches(parsedUri)) {
                return true;
            }
        }
        return false;
    }
}
