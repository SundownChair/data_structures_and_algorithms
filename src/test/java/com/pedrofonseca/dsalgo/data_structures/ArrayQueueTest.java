package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

public class ArrayQueueTest {

    private ArrayQueue<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new ArrayQueue<>();
    }

    @Test
    public void testWrapResizingDown() {
        for(int i = 1; i <= 10; i++) {
            mTestedClass.add(i);
        }
        while(mTestedClass.size() > 3) {
            mTestedClass.poll();
        }
        mTestedClass.add(11);
        mTestedClass.poll();
        // Current array: |11| | | | | | | |9|10|
        // Indexes:                         ^ ^

        mTestedClass.poll();
        // Current array: |10|11| | | |
        // Indexes:        ^  ^

        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testWrapResizing() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);
        mTestedClass.add(4);
        mTestedClass.add(5);
        mTestedClass.poll();
        mTestedClass.poll();
        mTestedClass.add(6);
        mTestedClass.add(7);
        // Current array: |6|7|3|4|5|
        // Indexes:          ^ ^

        mTestedClass.add(8);
        // Expected array: |3|4|5|6|7|8| | | | |
        // Indexes:         ^         ^

        assert(mTestedClass.size() == 6);
        assert(mTestedClass.poll() == 3);
    }

    @Test
    public void testWrapAroundResizing() {
        mTestedClass.add(1);
        mTestedClass.add(2);
        mTestedClass.add(3);
        mTestedClass.add(4);
        mTestedClass.add(5);
        mTestedClass.poll();
        mTestedClass.poll();
        mTestedClass.poll();
        mTestedClass.poll();
        mTestedClass.add(6);
        mTestedClass.add(7);
        mTestedClass.poll();
        mTestedClass.add(8);
        mTestedClass.add(9);
        mTestedClass.add(10);
        // Current array: |6|7|8|9|10|
        // Indexes:        ^       ^

        mTestedClass.add(11);
        // Expected array: |6|7|8|9|10|11| | | | |
        // Indexes:         ^          ^

        assert(mTestedClass.size() == 6);
        assert(mTestedClass.poll() == 6);
    }

    @Test
    public void testResizing() {
        for (int i = 1; i <=6; i++) {
            mTestedClass.add(i);
        }

        assert(mTestedClass.size() == 6);
    }

    @Test
    public void testResizingDown() {
        for (int i = 1; i <=6; i++) {
            mTestedClass.add(i);
        }
        while (mTestedClass.size() > 2) {
            mTestedClass.poll();
        }

        assert(mTestedClass.size() == 2);
    }

    @Test
    public void testPeek() {
        for (int i = 1; i <=6; i++) {
            mTestedClass.add(i);
        }

        assert(mTestedClass.peek() == 1);
        assert (mTestedClass.size() == 6);
    }

    @Test
    public void testPoll() {
        for (int i = 1; i <=6; i++) {
            mTestedClass.add(i);
        }

        assert(mTestedClass.poll() == 1);
        assert (mTestedClass.size() == 5);
    }

    @Test
    public void testEmptyPoll() {
        assert(mTestedClass.poll() == null);
    }

    @Test
    public void testNullAdd() {
        assert(mTestedClass.add(null) == false);
    }
}
