package com.igevin.terminaltodo.core.user;

import com.igevin.terminaltodo.supporting.ApplicationContextTool;
import com.igevin.terminaltodo.supporting.encryption.Encryption;
import com.igevin.terminaltodo.supporting.id.IdGenerator;
import lombok.AccessLevel;
import lombok.Getter;

import java.security.NoSuchAlgorithmException;

@Getter
public class User {
    private final Long id;
    private final String username;
    @Getter(value = AccessLevel.PRIVATE)
    private String password;

    private static final IdGenerator idGenerator;
    private static final Encryption encryption;

    static {
        idGenerator = ApplicationContextTool.getBeanByClass(IdGenerator.class);
        encryption = ApplicationContextTool.getBeanByClass(Encryption.class);
    }

    public User(String username, String password) {
        this.id = idGenerator.nextId();
        this.username = username;
        this.password = createEncryptPasswordOrDefault(password);
    }

    private String createEncryptPasswordOrDefault(String password) {
        try {
            return encryption.encrypt(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "ExceptionEncryptedPassword";
    }

    public void changePassword(String newPassword) {
        this.password = createEncryptPasswordOrDefault(newPassword);
    }

    public boolean validateId(String password) {
        try {
            return this.password.equals(encryption.encrypt(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
}
