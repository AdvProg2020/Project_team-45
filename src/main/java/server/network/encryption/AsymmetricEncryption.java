package server.network.encryption;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.*;

public class AsymmetricEncryption {
    private static final AsymmetricEncryption instance = new AsymmetricEncryption();
    private final String encryptionAlgorithm;

    private AsymmetricEncryption() {
        encryptionAlgorithm = "RSA";
    }

    public static AsymmetricEncryption getInstance() {
        return instance;
    }

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(encryptionAlgorithm);
            keyPairGenerator.initialize(2048, new SecureRandom());
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encrypt(String plainText, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return DatatypeConverter.printHexBinary(cipher.doFinal(plainText.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String cipherText, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(cipherText)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}