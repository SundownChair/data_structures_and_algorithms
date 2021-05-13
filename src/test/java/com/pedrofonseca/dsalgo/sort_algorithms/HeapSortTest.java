package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HeapSortTest {

    @Test
    public void testAscendingSort() {
        Integer[] i = {5,100,6,22,-1,10};
        Integer[] iS = {-1,5,6,10,22,100};

        assertArrayEquals(iS, HeapSort.sortAsc(i).toArray());
    }

    @Test
    public void testDescendingSort() {
        Integer[] i = {5,100,6,22,-1,10};
        Integer[] iS = {100,22,10,6,5,-1,};

        assertArrayEquals(iS, HeapSort.sortDesc(i).toArray());
    }
}
