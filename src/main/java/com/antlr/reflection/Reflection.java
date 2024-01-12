package com.antlr.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection<T> {
    private T obj;
    private Class objClass;
    private Class superClass;

    public Reflection(T obj) {
        this.obj = obj;
        this.objClass = obj.getClass();
        this.superClass = this.objClass.getSuperclass();
    }

    //#region Super
    public boolean hasSuperClass() { 
        return !superClass.equals(Object.class);
    }

    public Class getSuper() {
        return this.objClass.getSuperclass();
    }

    public Class getObjectClass() {
        return this.obj.getClass();
    }
    //#endregion


    //#region List
    private List<Field> getClassAttributes(Class objClass) {
        List<Field> fields = new ArrayList<Field>();

        if (!objClass.equals(Object.class)) {
            fields.addAll(
                this.getClassAttributes(objClass.getSuperclass())
            );
        }

        for (Field field : objClass.getDeclaredFields()) {
            fields.add(field);
        }

        return fields;
    }

    public List<Field> getClassAttributes() { 
        return this.getClassAttributes(this.objClass);
    }

    private List<Method> getClassMethods(Class objClass) {
        List<Method> methods = new ArrayList<Method>();

        if (!objClass.getSuperclass().equals(Object.class)) {
            methods.addAll(
                getClassMethods(objClass.getSuperclass())
            );
        }

        for (Method method : objClass.getDeclaredMethods()) {
            methods.add(method);
        }

        return methods;
    }

    public List<Method> getClassMethods() { 
        return this.getClassMethods(this.objClass);
    }
    //#endregion

    public void printClassAttributes() {
        for (Field field : this.getClassAttributes()) {
            ObjectField objectField = new ObjectField(field, obj);
            
            System.out.println(objectField);
        }
    }

    public void printClassMethods() {
        for (Method mehtod : this.getClassMethods()) {
            ObjectMethod objectMethod = new ObjectMethod(mehtod, obj);

            System.out.println(objectMethod);
        }
    }
}
