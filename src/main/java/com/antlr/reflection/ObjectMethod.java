package com.antlr.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ObjectMethod {
    private Object obj;
    private Method method;
    private int modifiers;

    private String access;
    private String staticMethod;
    private String returnType;
    private List<String> parametersType = new ArrayList<String>();
            
    public ObjectMethod(Method method, Object obj) {
        this.obj = obj;
        this.method = method;
        this.modifiers = method.getModifiers();

        this.access = Modifier.isPublic(this.modifiers) ? "public" : "private";
        this.staticMethod = Modifier.isStatic(this.modifiers) ? "static" : "";
        this.returnType = method.getReturnType().getName();

        for (Class parameter : method.getParameterTypes()) {
            parametersType.add(parameter.getName());
        }
    }

    @Override
    public String toString() {
        String params = String.join(", ", parametersType);

        return String.format("%s %s %s %s(%s)",
            this.access, 
            this.staticMethod, 
            this.returnType, 
            this.method.getName(), 
            params
        );
    }
}
