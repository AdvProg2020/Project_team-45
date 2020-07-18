package client.network;


import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;


public class MethodStringer {

    public static String stringTheMethod(Method method, Object... inputs) throws ClassNotFoundException {
        StringBuilder output = new StringBuilder();
        output.append(method.getDeclaringClass().getName());
        output.append(" ").append(method.getName()).append(" ");
        for (Object input : inputs) {
            output.append(input.getClass().getName()).append(":").append((new Gson()).toJson(input)).append(" ");
        }
        System.out.println(output);
        return output.toString();
    }

//    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
//        AdminController adminController = AdminController.getInstance();
//        Method method = AdminController.class.getDeclaredMethod("sayHi", String.class);
//
//        System.out.println(runMethodReturnJson(stringTheMethod(method, "ali")));
////        adminController.sayHi("ali");
//    }

    public String sampleMethod(String name) {
        Method me = getClass().getEnclosingMethod();
        try {
            String action = MethodStringer.stringTheMethod(me, name);
            String returnJson = ClientSocket.sendAction(action);
            return (new Gson()).fromJson(returnJson, String.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
