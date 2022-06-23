package com.example.vendaa.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;


public class Utils {

//    public static String getClientIpAddress(HttpServletRequest request) {
//        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
//        if (xForwardedForHeader == null) {
//            return request.getRemoteAddr();
//        } else {
//            return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
//        }
//    }

    public String generateHash(String toHash) {
//    String toHash = "someRandomString";
        MessageDigest md = null;
        byte[] hash = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
            hash = md.digest(toHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return convertToHex(hash);
    }

    private String convertToHex(byte[] raw) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < raw.length; i++) {
            sb.append(Integer.toString((raw[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public long getCurrentTime() {
        TimeZone lagosTimeZone = TimeZone.getTimeZone("Africa/Lagos");
        Calendar calendar = Calendar.getInstance(lagosTimeZone);
        return calendar.getTimeInMillis() / 1000;
    }

    public String nonce() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public String signature(String quicktellerClientId, String clientSecret, String signatureMethod, String url, long timestamp, String nonce, String httpMethood) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String clientSecretKey = clientSecret;  // put your client secret here
        String signatureCipher = httpMethood + "&" + URLEncoder.encode(url, "UTF-8") + "&" + timestamp + "&" + nonce + "&" + quicktellerClientId + "&" + clientSecretKey;
        MessageDigest messageDigest = MessageDigest.getInstance(signatureMethod);
//        byte[] signatureBytes = messageDigest.digest(signatureCipher.getBytes());
//        return (Base64.encodeBase64String(signatureBytes)).trim();

        byte[] signatureBytes = messageDigest.digest(signatureCipher.getBytes());

       String signaturetrim =  new String(Base64.encodeBase64(signatureBytes));
        return (signaturetrim ).trim();
    }
}