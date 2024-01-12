package com.antlr.demonstrative;

import java.time.LocalDate;

public class A {
    public int attrA1;
    public String attrA2;
    private int attrA3;
    private String attrA4;

    public int methodA1(int a, Integer b) { return a + b; }
    public int methodA2() { return 2; }

    public static LocalDate getLocalDate() { return LocalDate.now(); }
}
