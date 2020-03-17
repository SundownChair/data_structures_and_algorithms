package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new DoublyLinkedList<>();
    }

    @Test
    public void testAdd() {
        assert(mTestedClass.add(1));
        assert(mTestedClass.add(2));

        assert(mTestedClass.get(0).equals(1));
        assert(mTestedClass.get(1).equals(2));
        assert(mTestedClass.get(2) == null);
        assert(mTestedClass.size() == 2);

        assert(!mTestedClass.add(null));
    }

    @Test
    public void testAddAt() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.addAt(0, 1));

        assert(mTestedClass.get(0).equals(1));
        assert(mTestedClass.get(1).equals(0));
        assert(mTestedClass.get(2).equals(2));
        assert(mTestedClass.size() == 3);

        assert(!mTestedClass.addAt(0, -1));
        assert(!mTestedClass.addAt(0, 999));
        assert(!mTestedClass.addAt(null, 0));
    }

    @Test
    public void testPush() {
        assert(mTestedClass.push(1));
        assert(mTestedClass.push(2));

        assert(mTestedClass.get(0).equals(2));
        assert(mTestedClass.get(1).equals(1));
        assert(mTestedClass.get(2) == null);
        assert(mTestedClass.size() == 2);

        assert(!mTestedClass.push(null));
    }

    @Test
    public void testPeek() {
        assert(mTestedClass.peekFirst() == null);
        assert(mTestedClass.peekLast() == null);

        mTestedClass.add(0);
        mTestedClass.add(1);

        assert(mTestedClass.peekFirst().equals(0));
        assert(mTestedClass.peekLast().equals(1));
    }

    @Test
    public void testPop() {
        assert(mTestedClass.popFirst() == null);
        assert(mTestedClass.popLast() == null);
        assert(mTestedClass.size() == 0);

        mTestedClass.add(0);
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);

        assert(mTestedClass.size() == 4);
        assert(mTestedClass.popLast().equals(3));
        assert(mTestedClass.size() == 3);
        assert(mTestedClass.popFirst().equals(0));
        assert(mTestedClass.size() == 2);
        assert(mTestedClass.popFirst().equals(1));
        assert(mTestedClass.size() == 1);
        assert(mTestedClass.popFirst().equals(2));
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testPopAt() {
        assert(mTestedClass.popAt(0) == null);
        assert(mTestedClass.popAt(-1) == null);
        assert(mTestedClass.popAt(999) == null);
        assert(mTestedClass.size() == 0);

        mTestedClass.add(0);
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);

        assert(mTestedClass.popAt(0).equals(0));
        assert(mTestedClass.size() == 3);
        assert(mTestedClass.popAt(1).equals(2));
        assert(mTestedClass.size() == 2);
        assert(mTestedClass.popAt(1).equals(3));
        assert(mTestedClass.size() == 1);
        assert(mTestedClass.popAt(0).equals(1));
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testContains() {
        assert(!mTestedClass.contains(0));

        mTestedClass.add(0);
        mTestedClass.add(1);
        mTestedClass.add(2);

        assert(mTestedClass.contains(0));
        assert(mTestedClass.contains(1));
        assert(mTestedClass.contains(2));
        assert(!mTestedClass.contains(3));
        assert(!mTestedClass.contains(null));
    }

    @Test
    public void testReverse() {
        mTestedClass.reverse();

        mTestedClass.add(0);
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);

        mTestedClass.reverse();

        assert(mTestedClass.get(0).equals(3));
        assert(mTestedClass.get(1).equals(2));
        assert(mTestedClass.get(2).equals(1));
        assert(mTestedClass.get(3).equals(0));
    }
}
