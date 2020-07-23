package server.network;

import client.network.encryption.AsymmetricEncryption;
import com.google.gson.Gson;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Type;
import java.security.KeyPair;

public class ServerSecurityGate {
    private static final KeyPair myKeyPair = AsymmetricEncryption.getInstance().generateKeyPair();
    private SecretKey ourSecretKey;
    private String ourInitializationVector;

    public void exchangeKeys(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        try {
            dataOutputStream.writeUTF(myKeyPair.getPublic().getClass().getName() + "::" + (new Gson()).toJson(myKeyPair.getPublic()));
            dataOutputStream.flush();

            String[] plainTexts = AsymmetricEncryption.getInstance().decrypt(dataInputStream.readUTF(),
                    myKeyPair.getPrivate()).split("::");
            ourSecretKey = (new Gson()).fromJson(plainTexts[1], (Type) Class.forName(plainTexts[0]));
            ourInitializationVector = plainTexts[2];

            System.out.println(myKeyPair);
            System.out.println(ourSecretKey);
            System.out.println(ourInitializationVector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
