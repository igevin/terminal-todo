package com.igevin.terminaltodo.supporting.encryption.impl;

import com.igevin.terminaltodo.supporting.encryption.Encryption;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class NoEncryption implements Encryption {
    @Override
    public byte[] generateEncryptedBytes(String input) throws NoSuchAlgorithmException {
        return input.getBytes(StandardCharsets.UTF_8);
    }
}
