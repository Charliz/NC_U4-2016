package com.ncproject.webstore.utils;

import de.rtner.security.auth.spi.PBKDF2HexFormatter;
import de.rtner.security.auth.spi.PBKDF2Parameters;
import de.rtner.security.auth.spi.SimplePBKDF2;

/**
 * Created by Champion on 26.01.2017.
 */
public class EncryptionUtil {

    public static String hash(String plainText, String storedPassword) {
        if (plainText == null) return null;
        SimplePBKDF2 crypto = new SimplePBKDF2();
        PBKDF2Parameters params = crypto.getParameters();
        params.setHashCharset("UTF-8");
        params.setHashAlgorithm("HMacSHA256");
        params.setIterationCount(1000);
        if (storedPassword != null) {
            new PBKDF2HexFormatter().fromString(params, storedPassword);
        }
        return crypto.deriveKeyFormatted(plainText);
    }
}
