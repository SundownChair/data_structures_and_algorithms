package com.pedrofonseca.dsalgo.design_patterns.singleton;

public class Singleton {

    private int counter;

    private static Singleton instance;

    private Singleton() {
        counter = 0;
    }

    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public int foo() {
        return counter++;
    }
}
