package client.network;


import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MethodStringer {

    public static String stringTheMethod(Method method, Object... inputs) throws ClassNotFoundException {
        StringBuilder output = new StringBuilder();
        output.append(method.getDeclaringClass().getName());
        output.append("%").append(method.getName()).append("%");
        Class[] classes = method.getParameterTypes();
        for (int i = 0; i < inputs.length; i++) {
            output.append(classes[i].getName()).append("&").append((new Gson()).toJson(inputs[i])).append("%");
        }
//        System.out.println(output);
        return output.toString();
    }

    public static String runMethodReturnJson(String action) {
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
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            return e.getTargetException().getClass() + ":" + (new Gson()).toJson(e.getTargetException());
        }
    }

//    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
//        AdminController adminController = AdminController.getInstance();
//        Method method = AdminController.class.getDeclaredMethod("sayHi", String.class);
//
//        System.out.println(runMethodReturnJson(stringTheMethod(method, "ali")));
////        adminController.sayHi("ali");
//    }

    public static Object sampleMethod(Class enclosingClass, String methodName, Object... inputs) throws Throwable {
        try {

            Method[] nameMatchedMethods = Arrays.stream(enclosingClass.getMethods())
                    .filter(method -> method.getName().equals(methodName)).toArray(Method[]::new);
            Class[] parameterTypes = new Class[inputs.length];
            for (int i = 0; i < inputs.length; i++) {
                parameterTypes[i] = inputs[i].getClass();
            }

//            Method method = enclosingClass.getDeclaredMethod(methodName, parameterTypes);
            Method method;
            if (nameMatchedMethods.length == 1) {
                method = nameMatchedMethods[0];
            } else {
                System.out.println("overload darim :|");
                method = enclosingClass.getDeclaredMethod(methodName, parameterTypes);
//                for (Method nameMatchedMethod : nameMatchedMethods) {
//                    // TODO : age omri baghi bood
//                }
            }


//            method.getExceptionTypes()

            String action = MethodStringer.stringTheMethod(method, inputs);
            String returnJson = ClientSocket.getInstance().sendAction(action);
            if (returnJson.startsWith("E::")) {
                String eType = returnJson.split("::")[1];
                String eJson = returnJson.split("::")[2];
                Throwable t = (Throwable) (new Gson()).fromJson(eJson, Class.forName(eType));
                throw t;
            }
            Class a = method.getReturnType();
            if (a.getSimpleName().equals("void"))
                return null;
            return (new Gson()).fromJson(returnJson, method.getReturnType());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

}
