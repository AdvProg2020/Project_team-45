package server.encryption;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.KeyPair;

public class Main {
    public static void main(String[] args) {
//        KeyPair keypair = AsymmetricEncryption.getInstance().generateKeyPair();
//
//        String plainText = "I love bagheri";
//
//        byte[] cipherText = AsymmetricEncryption.getInstance().encrypt(plainText, keypair.getPublic());
//
//        System.out.print("Asymmetric Encrypted Text is: ");
//
//        System.out.println(DatatypeConverter.printHexBinary(cipherText));
//
//        String decryptedText = AsymmetricEncryption.getInstance().decrypt(cipherText, keypair.getPrivate());
//
//        System.out.println("Asymmetric decrypted text is: " + decryptedText);


        SecretKey secretKey = SymmetricEncryption.getInstance().generateSecretKey();

        byte[] initializationVector = SymmetricEncryption.getInstance().generateInitializationVector();

        String plainText = "I love bagheri";

        byte[] cipherText = SymmetricEncryption.getInstance().encrypt(plainText, secretKey, initializationVector);

        System.out.print("Symmetric Encrypted Text is: ");

        System.out.println(DatatypeConverter.printHexBinary(cipherText));

        String decryptedText = SymmetricEncryption.getInstance().decrypt(cipherText, secretKey, initializationVector);

        System.out.print("Symmetric Decrypted Text is: " + decryptedText);
    }
}