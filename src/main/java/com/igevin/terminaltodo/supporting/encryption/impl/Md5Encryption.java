package com.igevin.terminaltodo.supporting.encryption.impl;

import com.igevin.terminaltodo.supporting.encryption.Encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encryption implements Encryption {
    @Override
    public byte[] generateEncryptedBytes(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
}
