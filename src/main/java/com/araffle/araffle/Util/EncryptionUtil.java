package com.araffle.araffle.Util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {

    // 生成密钥
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 设置密钥长度
        return keyGen.generateKey();
    }

    // 加密
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // 解密
    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decrypted);
    }

    public static String secretKeyToString(SecretKey secretKey) {
        byte[] keyBytes = secretKey.getEncoded(); // 获取密钥的字节数组
        return Base64.getEncoder().encodeToString(keyBytes); // 将字节数组编码为Base64
    }

    public static SecretKey stringToSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey); // 解码Base64字符串
        return new SecretKeySpec(decodedKey, "AES"); // 创建SecretKey
    }
    public static void main(String[] args) {
        try {
            SecretKey key = generateKey();
            String originalPassword = "mySecurePassword";
            String encryptedPassword = encrypt(originalPassword, key);
            String decryptedPassword = decrypt(encryptedPassword, key);

            System.out.println("Original: " + originalPassword);
            System.out.println("Encrypted: " + encryptedPassword);
            System.out.println("Decrypted: " + decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
