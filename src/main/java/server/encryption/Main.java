package server.encryption;

import javax.crypto.SecretKey;
import java.security.KeyPair;

public class Main {
    public static void main(String[] args) {
//        KeyPair keypair = AsymmetricEncryption.getInstance().generateKeyPair();
//
//        String plainText = "I love bagheri";
//
//        String cipherText = AsymmetricEncryption.getInstance().encrypt(plainText, keypair.getPublic());
//
//        System.out.print("Asymmetric Encrypted Text is: ");
//
//        System.out.println(cipherText);
//
//        String decryptedText = AsymmetricEncryption.getInstance().decrypt(cipherText, keypair.getPrivate());
//
//        System.out.println("Asymmetric decrypted text is: " + decryptedText);


        SecretKey secretKey = SymmetricEncryption.getInstance().generateSecretKey();

        String initializationVector = SymmetricEncryption.getInstance().generateInitializationVector();

        String plainText = "I love bagheri";

        String cipherText = SymmetricEncryption.getInstance().encrypt(plainText, secretKey, initializationVector);

        System.out.println("Symmetric Encrypted Text is: " + cipherText);

        String decryptedText = SymmetricEncryption.getInstance().decrypt(cipherText, secretKey, initializationVector);

        System.out.println("Symmetric Decrypted Text is: " + decryptedText);
    }
}