package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RadixSortTest {

    @Test
    public void testAscendingSort() {
        RadixSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, RadixSort.sortAsc(singleArray));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {1, 10};
        assertArrayEquals(biArrayS, RadixSort.sortAsc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {1, 10};
        assertArrayEquals(sortedBiArrayS, RadixSort.sortAsc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, 0};
        Integer[] arrayS = {0, 1, 3, 7, 9, 10, 10};
        assertArrayEquals(arrayS, RadixSort.sortAsc(array));

        Integer[] sortedArray = {12, 54, 2, 3};
        Integer[] sortedArrayS = {2, 3, 12, 54};
        assertArrayEquals(sortedArrayS, RadixSort.sortAsc(sortedArray));
    }

    @Test
    public void testDescendingSort() {
        RadixSort.sortDesc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, RadixSort.sortDesc(singleArray));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {10, 1};
        assertArrayEquals(biArrayS, RadixSort.sortDesc(biArray));

        Integer[] sortedBiArray = {1, 10};
        Integer[] sortedBiArrayS = {10, 1};
        assertArrayEquals(sortedBiArrayS, RadixSort.sortDesc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, 0};
        Integer[] arrayS = {10, 10, 9, 7, 3, 1, 0};
        assertArrayEquals(arrayS, RadixSort.sortDesc(array));

        Integer[] sortedArray = {12, 54, 2, 3};
        Integer[] sortedArrayS = {54, 12, 3, 2};
        assertArrayEquals(sortedArrayS, RadixSort.sortDesc(sortedArray));
    }
}
