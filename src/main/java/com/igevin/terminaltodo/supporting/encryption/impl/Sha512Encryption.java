package com.igevin.terminaltodo.supporting.encryption.impl;

import com.igevin.terminaltodo.supporting.encryption.Encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512Encryption implements Encryption {
    @Override
    public byte[] generateEncryptedBytes(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
}
