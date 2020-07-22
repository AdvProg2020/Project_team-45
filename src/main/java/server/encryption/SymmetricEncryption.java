package server.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

public class SymmetricEncryption {
    private static final SymmetricEncryption instance = new SymmetricEncryption();
    private final String encryptionAlgorithm;
    private final String cipherEncryptionAlgorithm;

    private SymmetricEncryption() {
        encryptionAlgorithm = "AES";
        cipherEncryptionAlgorithm = "AES/CBC/PKCS5PADDING";
    }

    public static SymmetricEncryption getInstance() {
        return instance;
    }

    public SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionAlgorithm);
            keyGenerator.init(256, new SecureRandom());
            return keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateInitializationVector() {
        byte[] initializationVector = new byte[16];
        (new SecureRandom()).nextBytes(initializationVector);
        return DatatypeConverter.printHexBinary(initializationVector);
    }

    public String encrypt(String plainText, SecretKey secretKey, String initializationVector) {
        try {
            Cipher cipher = Cipher.getInstance(cipherEncryptionAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(DatatypeConverter.parseHexBinary(initializationVector)));
            return DatatypeConverter.printHexBinary(cipher.doFinal(plainText.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String cipherText, SecretKey secretKey, String initializationVector) {
        try {
            Cipher cipher = Cipher.getInstance(cipherEncryptionAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(DatatypeConverter.parseHexBinary(initializationVector)));
            return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(cipherText)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}