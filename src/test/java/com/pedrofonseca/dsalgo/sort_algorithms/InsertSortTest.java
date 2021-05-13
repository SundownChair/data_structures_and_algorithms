package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertSortTest {

    @Test
    public void testAscendingSort() {

        InsertSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, InsertSort.sortAsc(singleArray));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {1, 10};
        assertArrayEquals(biArrayS, InsertSort.sortAsc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {1, 10};
        assertArrayEquals(sortedBiArrayS, InsertSort.sortAsc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {-1, 0, 1, 3, 7, 9, 10, 10};
        assertArrayEquals(arrayS, InsertSort.sortAsc(array));

        Integer[] sortedArray = {-1, 0, 1, 2, 3, 4};
        Integer[] sortedArrayS = {-1, 0, 1, 2, 3, 4};
        assertArrayEquals(sortedArrayS, InsertSort.sortAsc(sortedArray));
    }

    @Test
    public void testDescendingSort() {

        InsertSort.sortDesc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, InsertSort.sortDesc(singleArray));

        Integer[] biArray = {1, 10};
        Integer[] biArrayS = {10, 1};
        assertArrayEquals(biArrayS, InsertSort.sortDesc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {10, 1};
        assertArrayEquals(sortedBiArrayS, InsertSort.sortDesc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {10, 10, 9, 7, 3, 1, 0, -1};
        assertArrayEquals(arrayS, InsertSort.sortDesc(array));

        Integer[] sortedArray = {4, 3, 2, 1, 0, -1};
        Integer[] sortedArrayS = {4, 3, 2, 1, 0, -1};
        assertArrayEquals(sortedArrayS, InsertSort.sortDesc(sortedArray));
    }
}
