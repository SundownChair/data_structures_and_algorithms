package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ShellSortTest {

    @Test
    public void testAscendingSort() {

        ShellSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, ShellSort.sortAsc(singleArray));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {1, 10};
        assertArrayEquals(biArrayS, ShellSort.sortAsc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {1, 10};
        assertArrayEquals(sortedBiArrayS, ShellSort.sortAsc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {-1, 0, 1, 3, 7, 9, 10, 10};
        assertArrayEquals(arrayS, ShellSort.sortAsc(array));

        Integer[] sortedArray = {12, -1, 54, 2, 3}; // Multiple jump test, 2 has to swap twice in same step
        Integer[] sortedArrayS = {-1, 2, 3, 12, 54};
        assertArrayEquals(sortedArrayS, ShellSort.sortAsc(sortedArray));
    }

    @Test
    public void testDescendingSort() {

        ShellSort.sortDesc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assertArrayEquals(singleArrayS, ShellSort.sortDesc(singleArray));

        Integer[] biArray = {1, 10};
        Integer[] biArrayS = {10, 1};
        assertArrayEquals(biArrayS, ShellSort.sortDesc(biArray));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {10, 1};
        assertArrayEquals(sortedBiArrayS, ShellSort.sortDesc(sortedBiArray));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {10, 10, 9, 7, 3, 1, 0, -1};
        assertArrayEquals(arrayS, ShellSort.sortDesc(array));

        Integer[] sortedArray = {4, 3, 2, 1, 0, -1};
        Integer[] sortedArrayS = {4, 3, 2, 1, 0, -1};
        assertArrayEquals(sortedArrayS, ShellSort.sortDesc(sortedArray));
    }
}
