package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {

    @Test
    public void testAscendingSort() {

        MergeSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assert(Arrays.equals(MergeSort.sortAsc(singleArray), singleArrayS));

        Integer[] doubleArray = {1, 0};
        Integer[] doubleArrayS = {0, 1};
        assert(Arrays.equals(MergeSort.sortAsc(doubleArray), doubleArrayS));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {-3, 1, 5, 7, 10};
        assert(Arrays.equals(MergeSort.sortAsc(array), arrayS));

        Integer[] sortedArray = {1, 2, 3, 5, 100};
        Integer[] sortedArrayS = {1, 2, 3, 5, 100};
        assert(Arrays.equals(MergeSort.sortAsc(sortedArray), sortedArrayS));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {-3, 1, 7, 7, 10};
        assert(Arrays.equals(MergeSort.sortAsc(duplicateArray), duplicateArrayS));
    }

    @Test
    public void testDescendingSort() {

        MergeSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assert(Arrays.equals(MergeSort.sortDesc(singleArray), singleArrayS));

        Integer[] doubleArray = {0, 1};
        Integer[] doubleArrayS = {1, 0};
        assert(Arrays.equals(MergeSort.sortDesc(doubleArray), doubleArrayS));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {10, 7, 5, 1, -3};
        assert(Arrays.equals(MergeSort.sortDesc(array), arrayS));

        Integer[] sortedArray = {100, 5, 3, 2, 1};
        Integer[] sortedArrayS = {100, 5, 3, 2, 1};
        assert(Arrays.equals(MergeSort.sortDesc(sortedArray), sortedArrayS));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {10, 7, 7, 1, -3};
        assert(Arrays.equals(MergeSort.sortDesc(duplicateArray), duplicateArrayS));
    }
}
