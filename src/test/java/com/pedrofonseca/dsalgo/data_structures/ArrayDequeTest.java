package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

public class ArrayDequeTest {

    private ArrayDeque<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new ArrayDeque<>();
    }

    @Test
    public void testAddFirst() {
        assert(mTestedClass.addFirst(1));
        assert(mTestedClass.addFirst(2));
        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testRemoveFirst() {
        mTestedClass.addFirst(1);
        mTestedClass.addFirst(2);
        assert(mTestedClass.removeFirst() == 2);
        assert(mTestedClass.removeFirst() == 1);
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testAddLast() {
        assert(mTestedClass.addLast(1));
        assert(mTestedClass.addLast(2));
        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testRemoveLast() {
        mTestedClass.addLast(1);
        mTestedClass.addLast(2);
        assert(mTestedClass.removeLast() == 2);
        assert(mTestedClass.removeLast() == 1);
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testNulls() {
        assert(!mTestedClass.addLast(null));
        assert(!mTestedClass.addFirst(null));
        assert(mTestedClass.removeLast() == null);
        assert(mTestedClass.removeFirst() == null);
    }

    @Test
    public void testResize() {
        // |2|3|4| ... | | |1| -> |1|2|3|4|...
        mTestedClass.addFirst(1);
        mTestedClass.addFirst(0);
        for(int i = 2; i <= 127; i++) {
            mTestedClass.addLast(i);
        }
        assert(mTestedClass.size() == 128);
        assert(mTestedClass.removeFirst() == 0);
        for (int i = 127; i > 0; i--) {
            assert(mTestedClass.removeLast() == i);
        }
        mTestedClass.removeLast();
        assert(mTestedClass.size() == 0);

        // | | |1|2| ... |100| -> |1|2|3| ... |100| | |...
        for(int i = 0; i <= 127; i++) {
            mTestedClass.addLast(i);
        }
        assert(mTestedClass.size() == 128);
        assert(mTestedClass.removeFirst() == 0);
        assert(mTestedClass.removeLast() == 127);
    }

    @Test
    public void testPeek() {
        mTestedClass.addLast(1);
        mTestedClass.addLast(2);
        mTestedClass.addLast(3);

        assert(mTestedClass.peekFirst() == 1);
        assert(mTestedClass.peekLast() == 3);
        assert(mTestedClass.size() == 3);
    }
}
