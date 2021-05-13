package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

public class SelectSortTest {

    @Test
    public void testAscendingSort() {
        SelectSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, SelectSort.sortAsc(singleArray));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {-3, 1, 5, 7, 10};
        assertArrayEquals(arrayS, SelectSort.sortAsc(array));

        Integer[] sortedArray = {1, 2, 3, 5, 100};
        Integer[] sortedArrayS = {1, 2, 3, 5, 100};
        assertArrayEquals(sortedArrayS, SelectSort.sortAsc(sortedArray));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {-3, 1, 7, 7, 10};
        assertArrayEquals(duplicateArrayS, SelectSort.sortAsc(duplicateArray));
    }

    @Test
    public void testDescendingSort() {
        SelectSort.sortDesc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, SelectSort.sortDesc(singleArray));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {10, 7, 5, 1, -3};
        assertArrayEquals(arrayS, SelectSort.sortDesc(array));

        Integer[] sortedArray = {100, 5, 3, 2, 1};
        Integer[] sortedArrayS = {100, 5, 3, 2, 1};
        assertArrayEquals(sortedArrayS, SelectSort.sortDesc(sortedArray));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {10, 7, 7, 1, -3};
        assertArrayEquals(duplicateArrayS, SelectSort.sortDesc(duplicateArray));
    }
}
