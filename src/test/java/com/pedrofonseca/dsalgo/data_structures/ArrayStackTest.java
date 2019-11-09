package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

public class ArrayStackTest {

    private ArrayStack<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new ArrayStack<>();
    }

    @Test
    public void testCompleteStack() {
        for(int i = 1; i <= 256; i++) {
            mTestedClass.push(i);
        }

        Integer lControl = 0;
        while(lControl != null) {
            lControl = mTestedClass.pop();
        }

        assert(mTestedClass.size() == 0);

        for(int i = 1; i <= 256; i++) {
            mTestedClass.push(i);
        }

        while(true) {
            lControl = mTestedClass.pop();
            if (mTestedClass.peek() == null) {
                break;
            }
        }

        assert(lControl == 1);
    }

    @Test
    public void testResizing() {
        for(int i = 1; i <= 1024; i++) {
            mTestedClass.push(i);
        }

        assert(mTestedClass.size() == 1024);

        while(mTestedClass.peek() != null) {
            mTestedClass.pop();
        }

        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testPush() {
        assert(mTestedClass.push(1));
        assert(mTestedClass.size() == 1);
        assert(mTestedClass.peek() == 1);
    }

    @Test
    public void testPop() {
        mTestedClass.push(1);

        assert(mTestedClass.pop() == 1);
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testPeek() {
        mTestedClass.push(1);

        assert(mTestedClass.peek() == 1);
        assert(mTestedClass.size() == 1);
    }

    @Test
    public void testNullPush() {
        assert(!mTestedClass.push(null));
        assert(mTestedClass.size() == 0);
        assert(mTestedClass.peek() == null);
    }

    @Test
    public void testNullPop() {
        assert(mTestedClass.pop() == null);
    }

    @Test
    public void testNullPeek() {
        assert(mTestedClass.peek() == null);
    }
}
