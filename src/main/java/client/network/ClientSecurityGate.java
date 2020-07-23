package client.network;

import client.network.encryption.AsymmetricEncryption;
import client.network.encryption.SymmetricEncryption;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;

import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Type;
import java.security.PublicKey;

public class ClientSecurityGate {
    private PublicKey serverPublicKey;
    private SecretKey ourSecretKey;
    private String ourInitializationVector;

    public void exchangeKeys(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        try {
            String[] receivedPublicKey = dataInputStream.readUTF().split("::");
            serverPublicKey = (new Gson()).fromJson(receivedPublicKey[1], (Type) Class.forName(receivedPublicKey[0]));

            ourSecretKey = SymmetricEncryption.getInstance().generateSecretKey();
            ourInitializationVector = SymmetricEncryption.getInstance().generateInitializationVector();

            dataOutputStream.writeUTF(AsymmetricEncryption.getInstance()
                    .encrypt(ourSecretKey.getClass().getName() + "::" + (new Gson()).toJson(ourSecretKey) + "::" + ourInitializationVector, serverPublicKey));
            dataOutputStream.flush();

            System.out.println(serverPublicKey);
            System.out.println(ourSecretKey);
            System.out.println(ourInitializationVector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
