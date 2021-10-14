package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {

    private final static String STORMWIND = "Stormwind";
    private final static String IRONFORGE = "Ironforge";
    private final static String DARNASSUS = "Darnassus";
    private final static String ORGRIMMAR = "Orgrimmar";

    private UnionFind<String> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new UnionFind<>();
    }

    @Test
    public void testCreateSet() {
        try {
            mTestedClass.createSet(STORMWIND);
            mTestedClass.createSet(STORMWIND);
            mTestedClass.createSet(IRONFORGE);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testUnion() {
        try {
            mTestedClass.createSet(STORMWIND);
            mTestedClass.createSet(IRONFORGE);
            mTestedClass.union(STORMWIND, IRONFORGE);
            mTestedClass.union(STORMWIND, IRONFORGE);
            mTestedClass.union(IRONFORGE, STORMWIND);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void testFind() {
        mTestedClass.createSet(STORMWIND);
        assertNotNull(mTestedClass.find(STORMWIND));
        assertNull(mTestedClass.find(IRONFORGE));
        mTestedClass.createSet(IRONFORGE);
        assertNotNull(mTestedClass.find(IRONFORGE));
        assertNotEquals(mTestedClass.find(STORMWIND), mTestedClass.find(IRONFORGE));
        mTestedClass.union(STORMWIND, IRONFORGE);
        assertEquals(mTestedClass.find(STORMWIND), mTestedClass.find(IRONFORGE));
        mTestedClass.createSet(DARNASSUS);
        assertNotEquals(mTestedClass.find(STORMWIND), mTestedClass.find(DARNASSUS));
        assertNotEquals(mTestedClass.find(IRONFORGE), mTestedClass.find(DARNASSUS));
    }

    @Test
    public void testIsConnected() {
        assertFalse(mTestedClass.isConnected(null, null));
        assertFalse(mTestedClass.isConnected(STORMWIND, IRONFORGE));
        mTestedClass.createSet(STORMWIND);
        assertFalse(mTestedClass.isConnected(STORMWIND, IRONFORGE));
        mTestedClass.createSet(IRONFORGE);
        assertFalse(mTestedClass.isConnected(STORMWIND, IRONFORGE));
        mTestedClass.union(STORMWIND, IRONFORGE);
        assertTrue(mTestedClass.isConnected(STORMWIND, IRONFORGE));
        assertTrue(mTestedClass.isConnected(IRONFORGE, STORMWIND));
        assertFalse(mTestedClass.isConnected(DARNASSUS, IRONFORGE));
        mTestedClass.createSet(DARNASSUS);
        mTestedClass.createSet(ORGRIMMAR);
        mTestedClass.union(ORGRIMMAR, DARNASSUS);
        assertFalse(mTestedClass.isConnected(DARNASSUS, IRONFORGE));
        mTestedClass.union(STORMWIND, ORGRIMMAR);
        assertTrue(mTestedClass.isConnected(IRONFORGE, DARNASSUS));
    }
}
