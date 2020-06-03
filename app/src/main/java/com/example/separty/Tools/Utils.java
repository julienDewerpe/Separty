package com.example.separty.Tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * created by separty team on <Date du jour>
 **/
public class Utils {

    public static String sha(final String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
