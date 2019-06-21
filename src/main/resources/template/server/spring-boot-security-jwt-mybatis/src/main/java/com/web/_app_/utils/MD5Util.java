package com.web.system.utils;

import java.security.MessageDigest;

public class MD5Util {

    public final static String encode(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public final static String tokenKey(long source){
        char[] chars = null;
        String key = String.valueOf(source);
        if(key.length() <= 4){
            chars = new char[4];
        }else if(key.length() <= 6){
            chars = new char[6];
        }else if(key.length() <= 8){
            chars = new char[8];
        }else if(key.length() <= 10){
            chars = new char[10];
        }else if(key.length() <= 12){
            chars = new char[12];
        }
        for(int i = 0; i < chars.length ;i ++){
            if(i+key.length() >= chars.length){
                chars[i] = key.charAt(key.length() + i - chars.length);
            }else{
                chars[i] = '0';
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.encode("123456"));
        System.out.println(MD5Util.tokenKey(12331));
        System.out.println(Integer.toHexString(12331));
    }
}