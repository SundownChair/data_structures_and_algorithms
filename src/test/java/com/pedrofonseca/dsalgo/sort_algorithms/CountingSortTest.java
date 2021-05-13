package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CountingSortTest {

    @Test
    public void testAscendingSort() {
        CountingSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, CountingSort.sortAsc(singleArray));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {1, 10};
        assertArrayEquals(biArrayS, CountingSort.sortAsc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {1, 10};
        assertArrayEquals(sortedBiArrayS, CountingSort.sortAsc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {-1, 0, 1, 3, 7, 9, 10, 10};
        assertArrayEquals(arrayS, CountingSort.sortAsc(array));

        Integer[] sortedArray = {12, -1, 54, 2, 3};
        Integer[] sortedArrayS = {-1, 2, 3, 12, 54};
        assertArrayEquals(sortedArrayS, CountingSort.sortAsc(sortedArray));
    }

    @Test
    public void testDescendingSort() {
        CountingSort.sortDesc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, CountingSort.sortDesc(singleArray));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {10, 1};
        assertArrayEquals(biArrayS, CountingSort.sortDesc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {10, 1};
        assertArrayEquals(sortedBiArrayS, CountingSort.sortDesc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {10, 10, 9, 7, 3, 1, 0, -1};
        assertArrayEquals(arrayS, CountingSort.sortDesc(array));

        Integer[] sortedArray = {12, -1, 54, 2, 3};
        Integer[] sortedArrayS = {54, 12, 3, 2, -1};
        assertArrayEquals(sortedArrayS, CountingSort.sortDesc(sortedArray));
    }
}
