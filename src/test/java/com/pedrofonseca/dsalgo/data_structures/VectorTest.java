package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class VectorTest {

    Vector<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new Vector<>();
    }

    @Test
    public void testAdd() {
        assert(mTestedClass.capacity() == 5);
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);
        assert(mTestedClass.capacity() == 10);
        assert(mTestedClass.get(0) == 1);
        assert(mTestedClass.get(1) == 2);
        assert(mTestedClass.get(2) == 3);
        assert(mTestedClass.get(4) == null);
    }

    @Test
    public void testInsert() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);

        mTestedClass.insert(1, 4);

        // Case 1 - regular insert, check transpose
        assert(mTestedClass.get(0)==1);
        assert(mTestedClass.get(1)==4);
        assert(mTestedClass.get(2)==2);
        assert(mTestedClass.get(3)==3);

        assert(mTestedClass.capacity() == 10);

        // Case 2 - non sequential (null elements exist between values)
        mTestedClass.insert(9, 10);

        // Case 3 - invalid insert, out of bounds
        try {
            mTestedClass.insert(100, 10);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // All good here
        }
    }

    @Test
    public void testGetIndex() {
        mTestedClass.add(1);

        assert(mTestedClass.getIndex(1) == 0);
        assert(mTestedClass.getIndex(2) == -1);
    }

    @Test
    public void testGetElement() {
        mTestedClass.add(1);

        assert(mTestedClass.get(0) == 1);
        assert(mTestedClass.get(1) == null);
    }

    @Test
    public void testRemoveIndex() {
        mTestedClass.add(1);

        assert(mTestedClass.remove(0).equals(1));
        assert(mTestedClass.size() == 0);

        try {
            mTestedClass.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // All good here
        }
    }

    @Test
    public void testRemoveElement() {
        mTestedClass.add(1);

        assert(mTestedClass.remove(Integer.valueOf(1)).equals(1));
        assert(mTestedClass.remove(Integer.valueOf(1)) == null);
        assert(mTestedClass.size() == 0);
    }
}
