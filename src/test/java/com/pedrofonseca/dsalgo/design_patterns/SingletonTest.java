package com.pedrofonseca.dsalgo.design_patterns;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingletonTest {

    @Test
    public void testSingleton() {

        Singleton singleton_1 = Singleton.getInstance();
        Singleton singleton_2 = Singleton.getInstance();


        assertEquals(0,singleton_1.foo());
        assertEquals(1, singleton_1.foo());
        assertEquals(2, singleton_2.foo());
        assertEquals(3, singleton_1.foo());
    }
}
