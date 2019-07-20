package cn.kgc.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    public static String getStrMD5(String inStr) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(inStr.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static void main(String[] args) {
        System.out.println(SecurityUtil.getStrMD5("123"));
    }

}
