package server.network;

import client.ClientMain;
import client.network.ClientSocket;
import client.network.MethodStringer;
import com.google.gson.Gson;

import java.lang.reflect.Method;

public class Testii {
    public static String sayHi(String name) {
        Method me = ClientMain.class.getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, name);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, String.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
