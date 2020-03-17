package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

public class ArrayHeapTest {

    ArrayHeap<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new ArrayHeap<>(ArrayHeap.HeapType.Min);
    }

    @Test
    public void testAdd() {
        assert(mTestedClass.add(1));
        assert(mTestedClass.add(2));
        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testGetHead() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.getHead() == 1);
        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testRemoveHead() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.removeHead() == 1);
        assert(mTestedClass.size() == 1);
    }

    @Test
    public void testNull() {
        assert(!mTestedClass.add(null));
        assert(mTestedClass.getHead() == null);
        assert(mTestedClass.removeHead() == null);
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testHeapifyUp() {
        mTestedClass.add(3);
        mTestedClass.add(2);
        assert(mTestedClass.getHead() == 2);
        mTestedClass.add(1);
        assert(mTestedClass.getHead() == 1);
    }

    @Test
    public void testMassHeapifyUp() {
        for (int i = 1; i <= 1024; i++ ) {
            mTestedClass.add(i);
        }
        mTestedClass.add(0);
        assert(mTestedClass.getHead() == 0);
        for (int i = 0; i <= 1024; i++ ) {
            assert(mTestedClass.removeHead() == i);
        }
    }

    @Test
    public void testMassHeapifyDown() {
        for (int i = 1024; i >= 0; i--) {
            mTestedClass.add(i);
        }
        assert(mTestedClass.getHead() == 0);
        for (int i = 0; i <= 1024; i++) {
            assert(mTestedClass.removeHead() == i);
        }
    }
}
