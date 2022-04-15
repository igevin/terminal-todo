package com.igevin.terminaltodo.supporting.encryption;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
    byte[] generateEncryptedBytes(String input) throws NoSuchAlgorithmException;
    default String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    default String encrypt(String input) throws NoSuchAlgorithmException {
        byte[] hash = generateEncryptedBytes(input);
        return toHexString(hash);
    }
}
