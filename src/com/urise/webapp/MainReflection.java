package com.urise.webapp;

import com.urise.webapp.model.Resume;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        Resume resume = new Resume("uuid100");
        MainReflection mainReflection = new MainReflection();
        mainReflection.printMethodViaArray(resume);
        mainReflection.printMethodViaName(resume);
    }

    private void printMethodViaArray(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("toString")) {
                System.out.println(method.invoke(object));
            }
        }
    }

    private void printMethodViaName(Object object) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Class<?> clazz = object.getClass();
        Method method = clazz.getMethod("toString");
        System.out.println(method.invoke(object));
    }
}
