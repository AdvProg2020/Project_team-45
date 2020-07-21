package server.network;


import client.network.ClientSocket;
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

    public static String runMethodReturnJson(String action) {
        String[] parts = action.trim().split(" ");
        try {
            Class srcClass = Class.forName(parts[0].replace("client", "server"));
            Class[] classes = new Class[parts.length - 2];
            for (int i = 2; i < parts.length; i++) {
                classes[i-2] = Class.forName(parts[i].split(":")[0]);
            }
            Method method = srcClass.getDeclaredMethod(parts[1], classes);

            ArrayList<String> inputsStrings = new ArrayList<>();
            Collections.addAll(inputsStrings, parts);
            inputsStrings.remove(0);
            inputsStrings.remove(0);

            Object[] inputs = inputsStrings.stream().map(string -> {
                try {
                    String[] strings = string.split(":");
                    Class objectClass = Class.forName(strings[0]);
                    return (new Gson()).fromJson(strings[1], objectClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }).toArray(Object[]::new);

            Object instance = srcClass.getDeclaredMethod("getInstance").invoke(null);
            Object output = method.invoke(instance, inputs);
            return (new Gson()).toJson(output);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            Exception targetException = (Exception) e.getTargetException();
            return targetException.getClass().getName() + "::" + (new Gson()).toJson(targetException);
        }
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
            String returnJson = ClientSocket.getInstance().sendAction(action);
            return (new Gson()).fromJson(returnJson, String.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
