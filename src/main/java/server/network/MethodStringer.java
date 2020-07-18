package server.network;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class MethodStringer {

    public static Object runMethodReturnJson(String action) {
        String[] parts = action.trim().split(" ");
        try {
            Class srcClass = Class.forName(parts[0]);
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
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
