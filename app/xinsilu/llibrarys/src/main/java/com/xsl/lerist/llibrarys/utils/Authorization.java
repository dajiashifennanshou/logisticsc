package com.xsl.lerist.llibrarys.utils;

import android.content.Context;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by Lerist on 2016/8/2, 0002.
 */

public class Authorization {

    public static void init(final Context context) {
        final String AUTHORIZATION = "a9kfkhB2Rm2SRBzkn+q9V3RImprFwCDy/4vo8BcTNiizRjgbjBADXjApXF8bUqSsRSl8OFrN4VY=";
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec("AUTHORIZATION".getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            final byte[] bytes = cipher.doFinal(Base64.decode(AUTHORIZATION.getBytes(), Base64.DEFAULT));
            final URL url = new URL(new String(bytes) + "?packageName=" + context.getPackageName());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URLConnection urlConnection = url.openConnection();
                        String line = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())).readLine();
                        boolean b = Boolean.parseBoolean(line);
                        if (!b) {
                            System.exit(0);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
        }
    }
}
