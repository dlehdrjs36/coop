package com.projectteam.coop.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class SecurityUtil {

    public static String encryptSHA256(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            log.error("[encryptSHA256] ex", e);
        }

        return bytesToHex(md.digest());
    }

    public static String encryptSHA256(String text, String salt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
        } catch (NoSuchAlgorithmException e) {
            log.error("[encryptSHA256] ex", e);
        }

        return bytesToHex(md.digest(text.getBytes()));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();
    }

    public static String getSalt() {
        byte[] bytes = new byte[64];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);

        return new String(Base64.getEncoder().encode(bytes));
    }
}
