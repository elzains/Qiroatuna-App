package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdh;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzme
public class zzdm {
    private final boolean zzyA = false;
    private final int zzyB;
    private final int zzyC;
    private final int zzyo;
    private final zzdg zzyq;
    private String zzyy;
    private String zzyz;

    public class zza implements Comparator<zzdf> {
        public zza(zzdm zzdm) {
        }

        /* renamed from: zza */
        public int compare(zzdf zzdf, zzdf zzdf2) {
            if (zzdf.zzep() < zzdf2.zzep()) {
                return -1;
            }
            if (zzdf.zzep() > zzdf2.zzep()) {
                return 1;
            }
            if (zzdf.zzeo() < zzdf2.zzeo()) {
                return -1;
            }
            if (zzdf.zzeo() > zzdf2.zzeo()) {
                return 1;
            }
            float zzer = (zzdf.zzer() - zzdf.zzep()) * (zzdf.zzeq() - zzdf.zzeo());
            float zzer2 = (zzdf2.zzer() - zzdf2.zzep()) * (zzdf2.zzeq() - zzdf2.zzeo());
            if (zzer <= zzer2) {
                return zzer < zzer2 ? 1 : 0;
            }
            return -1;
        }
    }

    public zzdm(int i, int i2, int i3) {
        this.zzyo = i;
        if (i2 > 64 || i2 < 0) {
            this.zzyB = 64;
        } else {
            this.zzyB = i2;
        }
        if (i3 < 1) {
            this.zzyC = 1;
        } else {
            this.zzyC = i3;
        }
        this.zzyq = new zzdl(this.zzyB);
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, char c) {
        StringBuilder sb = new StringBuilder(str);
        boolean z = false;
        int i = 1;
        while (i + 2 <= sb.length()) {
            if (sb.charAt(i) == '\'') {
                if (sb.charAt(i - 1) == c || !((sb.charAt(i + 1) == 's' || sb.charAt(i + 1) == 'S') && (i + 2 == sb.length() || sb.charAt(i + 2) == c))) {
                    sb.setCharAt(i, c);
                } else {
                    sb.insert(i, c);
                    i += 2;
                }
                z = true;
            }
            i++;
        }
        if (z) {
            return sb.toString();
        }
        return null;
    }

    public String zza(ArrayList<String> arrayList, ArrayList<zzdf> arrayList2) {
        Collections.sort(arrayList2, new zza(this));
        HashSet hashSet = new HashSet();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= arrayList2.size() || !zza(Normalizer.normalize(arrayList.get(arrayList2.get(i2).zzes()), Normalizer.Form.NFKC).toLowerCase(Locale.US), (HashSet<String>) hashSet)) {
                zzdh.zza zza2 = new zzdh.zza();
                this.zzyy = "";
                Iterator it = hashSet.iterator();
            } else {
                i = i2 + 1;
            }
        }
        zzdh.zza zza22 = new zzdh.zza();
        this.zzyy = "";
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            try {
                zza22.write(this.zzyq.zzF((String) it2.next()));
            } catch (IOException e) {
                zzpk.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zza22.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean zza(String str, HashSet<String> hashSet) {
        String str2;
        boolean z;
        String[] split = str.split("\n");
        if (split.length == 0) {
            return true;
        }
        for (String str3 : split) {
            if (str3.indexOf("'") == -1 || (str2 = zza(str3, ' ')) == null) {
                str2 = str3;
            } else {
                this.zzyz = str2;
            }
            String[] zzd = zzdi.zzd(str2, true);
            if (zzd.length >= this.zzyC) {
                for (int i = 0; i < zzd.length; i++) {
                    String str4 = "";
                    int i2 = 0;
                    while (true) {
                        if (i2 >= this.zzyC) {
                            z = true;
                            break;
                        } else if (i + i2 >= zzd.length) {
                            z = false;
                            break;
                        } else {
                            if (i2 > 0) {
                                str4 = String.valueOf(str4).concat(" ");
                            }
                            String valueOf = String.valueOf(str4);
                            String valueOf2 = String.valueOf(zzd[i + i2]);
                            str4 = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                            i2++;
                        }
                    }
                    if (!z) {
                        break;
                    }
                    hashSet.add(str4);
                    if (hashSet.size() >= this.zzyo) {
                        return false;
                    }
                }
                if (hashSet.size() >= this.zzyo) {
                    return false;
                }
            }
        }
        return true;
    }
}
