package com.halo.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Util {

    /**
     * Field hexDigits
     * Description
     */
    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    };

    /**
     * Method byteArrayToHexString
     * Description 说明：
     *
     * @param b 说明：
     *
     * @return 返回值说明：
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();

        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }

        return resultSb.toString();
    }

    /**
     * Method byteToHexString
     * Description 说明：
     *
     * @param b 说明：
     *
     * @return 返回值说明：
     */
    private static String byteToHexString(byte b) {
        int n = b;

        if (n < 0) {
            n += 256;
        }

        int d1 = n >>> 4;
        int d2 = n % 16;

        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * Method MD5Encode
     * Description 说明：
     *
     * @param origin 说明：
     *
     * @return 返回值说明：
     */
    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = origin;

            MessageDigest md = MessageDigest.getInstance("MD5");

            resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultString;
    }

    /**
     * Method md5
     * Description 说明：
     *
     * @param password 说明：
     *
     * @return 返回值说明：
     *
     * @throws NoSuchAlgorithmException 异常：
     */
    public static String md5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(password.getBytes());

        return new BigInteger(1, md.digest()).toString(16);
    }

    /**
     * Method md5
     * Description 说明：
     *
     * @param username 说明：
     * @param password 说明：
     *
     * @return 返回值说明：
     *
     * @throws NoSuchAlgorithmException 异常：
     */
    public static String md5(String username, String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(username.getBytes());
        md.update(password.getBytes());

        return new BigInteger(1, md.digest()).toString(16);
    }

    public static String md5T16(){
        String uuid = String.valueOf(UUID.randomUUID());
        return md5T16(uuid);
    }

    public static String md5T16(String origin){
        return  MD5Encode(origin).substring(8, 24);
    }
    /**
     * Method main
     * Description 说明：
     *
     * @param args 说明：
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String utf8 = MD5Util.byteArrayToHexString("123456".getBytes("utf8"));
        System.out.println(utf8);
        String s = MD5Encode("123456");
        System.out.println(s);
    }
}

