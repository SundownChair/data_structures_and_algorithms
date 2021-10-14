package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class IndexedPriorityQueueTest {

    IndexedPriorityQueue<String, Integer> mTestedClass;

    private static final String WORK_ORDER = "WORK";
    private static final String BUILD_ORDER = "BUILD";
    private static final String FIGHT_ORDER = "FIGHT";

    @Before
    public void setup() {
        mTestedClass = new IndexedPriorityQueue<>(String.class, Integer.class, ArrayHeap.HeapType.Min);
    }

    @Test
    public void testInsert() {
        try{
            mTestedClass.insert(null, null);
            fail();
        } catch (NullPointerException e) {
            // all good
        }

        assertEquals(0, mTestedClass.size());
        mTestedClass.insert(WORK_ORDER, 10);
        assertEquals(1, mTestedClass.size());
        mTestedClass.insert(BUILD_ORDER, 1);
        assertEquals(2, mTestedClass.size());
        mTestedClass.insert(FIGHT_ORDER, 0);
        assertEquals(3, mTestedClass.size());
        mTestedClass.insert(FIGHT_ORDER, 0); // Impl does not support multiples of same element
        assertEquals(3, mTestedClass.size());
    }

    @Test
    public void testPoll() {
        try {
            mTestedClass.poll();
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }

        mTestedClass.insert(WORK_ORDER, 10);
        mTestedClass.insert(BUILD_ORDER, 1);
        mTestedClass.insert(FIGHT_ORDER, 0);

        assertEquals(FIGHT_ORDER, mTestedClass.poll());

        assertEquals(BUILD_ORDER, mTestedClass.poll());

        mTestedClass.insert(FIGHT_ORDER, 0);
        assertEquals(FIGHT_ORDER, mTestedClass.poll());

        mTestedClass.insert(FIGHT_ORDER, 0);
        mTestedClass.insert(BUILD_ORDER, 1);
        assertEquals(FIGHT_ORDER, mTestedClass.poll());
        assertEquals(BUILD_ORDER, mTestedClass.poll());
        assertEquals(WORK_ORDER, mTestedClass.poll());

        try {
            mTestedClass.poll();
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }
    }

    @Test
    public void testDelete() {
        try {
            mTestedClass.delete(null);
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }

        mTestedClass.insert(WORK_ORDER, 10);
        mTestedClass.insert(BUILD_ORDER, 1);
        mTestedClass.insert(FIGHT_ORDER, 0);

        assertEquals(FIGHT_ORDER, mTestedClass.delete(FIGHT_ORDER));

        assertEquals(BUILD_ORDER, mTestedClass.poll());

        mTestedClass.insert(FIGHT_ORDER, 0);

        assertEquals(WORK_ORDER, mTestedClass.delete(WORK_ORDER));
        assertEquals(FIGHT_ORDER, mTestedClass.poll());

        try {
            mTestedClass.delete(WORK_ORDER);
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }
    }

    @Test
    public void testUpdate() {
        try {
            mTestedClass.update(null, null);
            fail();
        } catch(NullPointerException e) {
            // all good
        }

        try {
            mTestedClass.update(BUILD_ORDER, null);
            fail();
        } catch(NullPointerException e) {
            // all good
        }

        try {
            mTestedClass.update(BUILD_ORDER, 10);
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }

        mTestedClass.insert(WORK_ORDER, 10);
        mTestedClass.insert(BUILD_ORDER, 1);
        assertEquals(BUILD_ORDER, mTestedClass.peek());

        mTestedClass.update(WORK_ORDER, 0);
        assertEquals(WORK_ORDER, mTestedClass.peek());
        assertEquals(Integer.valueOf(0), mTestedClass.peekValue());

        mTestedClass.update(WORK_ORDER, 100);
        assertEquals(BUILD_ORDER, mTestedClass.peek());

        mTestedClass.poll();
        assertEquals(WORK_ORDER, mTestedClass.peek());
        assertEquals(Integer.valueOf(100), mTestedClass.peekValue());

        mTestedClass.insert(BUILD_ORDER, 10);
        assertEquals(Integer.valueOf(10), mTestedClass.peekValue());
        mTestedClass.updateMoreExtreme(BUILD_ORDER, 1);
        assertEquals(Integer.valueOf(1), mTestedClass.peekValue());
        mTestedClass.updateMoreExtreme(BUILD_ORDER, 10);
        assertEquals(Integer.valueOf(1), mTestedClass.peekValue());
        mTestedClass.updateLessExtreme(BUILD_ORDER, -1);
        assertEquals(Integer.valueOf(1), mTestedClass.peekValue());
        mTestedClass.updateLessExtreme(BUILD_ORDER, 10);
        assertEquals(Integer.valueOf(10), mTestedClass.peekValue());

        mTestedClass.updateMoreExtreme(WORK_ORDER, -10);
        assertEquals(WORK_ORDER, mTestedClass.peek());
        assertEquals(Integer.valueOf(-10), mTestedClass.peekValue());

        mTestedClass.updateLessExtreme(WORK_ORDER, 1000);
        assertEquals(BUILD_ORDER, mTestedClass.peek());
    }

    @Test
    public void testPeek() {
        try {
            mTestedClass.peek();
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }

        try {
            mTestedClass.peekValue();
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }

        mTestedClass.insert(WORK_ORDER, 10);
        mTestedClass.insert(BUILD_ORDER, 1);
        mTestedClass.insert(FIGHT_ORDER, 0);

        assertEquals(FIGHT_ORDER, mTestedClass.peek());
        assertEquals(FIGHT_ORDER, mTestedClass.peek());
        assertEquals(Integer.valueOf(0), mTestedClass.peekValue());
        mTestedClass.poll();
        assertEquals(BUILD_ORDER, mTestedClass.peek());
        assertEquals(Integer.valueOf(1), mTestedClass.peekValue());
        mTestedClass.poll();
        mTestedClass.poll();

        try {
            mTestedClass.peek();
            fail();
        } catch(NoSuchElementException e) {
            // all good
        }
    }

    @Test
    public void testContains() {
        assertFalse(mTestedClass.contains(FIGHT_ORDER));
        mTestedClass.insert(FIGHT_ORDER, 0);
        assertTrue(mTestedClass.contains(FIGHT_ORDER));
        mTestedClass.poll();
        assertFalse(mTestedClass.contains(FIGHT_ORDER));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mTestedClass.isEmpty());
        mTestedClass.insert(FIGHT_ORDER, 0);
        assertFalse(mTestedClass.isEmpty());
        mTestedClass.poll();
        assertTrue(mTestedClass.isEmpty());
    }
}
