package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void testAscendingSort() {

        QuickSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, QuickSort.sortAsc(singleArray));

        Integer[] doubleArray = {1, 0};
        Integer[] doubleArrayS = {0, 1};
        assertArrayEquals(doubleArrayS, QuickSort.sortAsc(doubleArray));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {-3, 1, 5, 7, 10};
        assertArrayEquals(arrayS, QuickSort.sortAsc(array));

        Integer[] sortedArray = {1, 2, 3, 5, 100};
        Integer[] sortedArrayS = {1, 2, 3, 5, 100};
        assertArrayEquals(sortedArrayS, QuickSort.sortAsc(sortedArray));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {-3, 1, 7, 7, 10};
        assertArrayEquals(duplicateArrayS, QuickSort.sortAsc(duplicateArray));
    }

    @Test
    public void testDescendingSort() {

        QuickSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, QuickSort.sortDesc(singleArray));

        Integer[] doubleArray = {0, 1};
        Integer[] doubleArrayS = {1, 0};
        assertArrayEquals(doubleArrayS, QuickSort.sortDesc(doubleArray));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {10, 7, 5, 1, -3};
        assertArrayEquals(arrayS, QuickSort.sortDesc(array));

        Integer[] sortedArray = {100, 5, 3, 2, 1};
        Integer[] sortedArrayS = {100, 5, 3, 2, 1};
        assertArrayEquals(sortedArrayS, QuickSort.sortDesc(sortedArray));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {10, 7, 7, 1, -3};
        assertArrayEquals(duplicateArrayS, QuickSort.sortDesc(duplicateArray));
    }
}
