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
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testpeek() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.peek() == 1);
        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testpoll() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.poll() == 1);
        assert(mTestedClass.size() == 1);
    }

    @Test
    public void testNull() {
        mTestedClass.add(null);
        assert(mTestedClass.peek() == null);
        assert(mTestedClass.poll() == null);
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testHeapifyUp() {
        mTestedClass.add(3);
        mTestedClass.add(2);
        assert(mTestedClass.peek() == 2);
        mTestedClass.add(1);
        assert(mTestedClass.peek() == 1);
    }

    @Test
    public void testMassHeapifyUp() {
        for (int i = 1; i <= 1024; i++ ) {
            mTestedClass.add(i);
        }
        mTestedClass.add(0);
        assert(mTestedClass.peek() == 0);
        for (int i = 0; i <= 1024; i++ ) {
            assert(mTestedClass.poll() == i);
        }
    }

    @Test
    public void testMassHeapifyDown() {
        for (int i = 1024; i >= 0; i--) {
            mTestedClass.add(i);
        }
        assert(mTestedClass.peek() == 0);
        for (int i = 0; i <= 1024; i++) {
            assert(mTestedClass.poll() == i);
        }
    }
}
