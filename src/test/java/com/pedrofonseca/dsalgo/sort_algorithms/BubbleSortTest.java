package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest {

    @Test
    public void testAscendingSort() {
        assertNull(BubbleSort.sortAsc(null));

        Integer[] nullArray = {};
        assertNull(BubbleSort.sortAsc(nullArray));

        Integer[] singleArray = {1};
        Integer[] singleArrayS = {1};
        assertArrayEquals(singleArrayS, BubbleSort.sortAsc(singleArray));

        Integer[] sortedArray = {1,2,3};
        assertArrayEquals(sortedArray, BubbleSort.sortAsc(sortedArray));

        Integer[] unsortedArray = {9, 10, 2, 5, 6, 7, 4, 1, 3, 8, 1};
        Integer[] unsortedArrayS = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(unsortedArrayS, BubbleSort.sortAsc(unsortedArray));
    }

    @Test
    public void testDescendingSort() {
        assertNull(BubbleSort.sortDesc(null));

        Integer[] nullArray = {};
        assertNull(BubbleSort.sortDesc(nullArray));

        Integer[] singleArray = {1};
        Integer[] singleArrayS = {1};
        assertArrayEquals(singleArrayS, BubbleSort.sortDesc(singleArray));

        Integer[] sortedArray = {3,2,1};
        assertArrayEquals(sortedArray, BubbleSort.sortDesc(sortedArray));

        Integer[] unsortedArray = {9, 10, 2, 5, 6, 7, 4, 1, 3, 8, 1};
        Integer[] unsortedArrayS = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};
        assertArrayEquals(unsortedArrayS, BubbleSort.sortDesc(unsortedArray));
    }
}
