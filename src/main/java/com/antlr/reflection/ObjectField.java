package com.antlr.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ObjectField {
    private Object obj;
    private Field field;

    private String access;
    private String staticField;
    private String value;

    public ObjectField(Field field, Object obj) {
        this.field = field;
        this.obj = obj;

        this.access = "public";
        this.staticField = Modifier.isStatic(field.getModifiers()) ? "static" : "";
        this.value = "unknown";

        try {
            value = this.field.get(this.obj) == null ? null : field.get(this.obj).toString();
        } catch (IllegalAccessException iaEx) {
            access = "private";
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s %s := %s",
            this.access, 
            this.staticField, 
            this.field.getName(), 
            this.value
        );
    }

}
