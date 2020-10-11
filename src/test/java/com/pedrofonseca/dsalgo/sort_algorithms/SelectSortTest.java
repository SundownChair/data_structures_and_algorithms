package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.*;

import java.util.Arrays;

public class SelectSortTest {

    @Test
    public void testAscendingSort() {
        SelectSort.sortAsc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assert(Arrays.equals(SelectSort.sortAsc(singleArray), singleArrayS));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {-3, 1, 5, 7, 10};
        assert(Arrays.equals(SelectSort.sortAsc(array), arrayS));

        Integer[] sortedArray = {1, 2, 3, 5, 100};
        Integer[] sortedArrayS = {1, 2, 3, 5, 100};
        assert(Arrays.equals(SelectSort.sortAsc(sortedArray), sortedArrayS));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {-3, 1, 7, 7, 10};
        assert(Arrays.equals(SelectSort.sortAsc(duplicateArray), duplicateArrayS));
    }

    @Test
    public void testDescendingSort() {
        SelectSort.sortDesc(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assert(Arrays.equals(SelectSort.sortDesc(singleArray), singleArrayS));

        Integer[] array = {10, 1, 7, -3, 5};
        Integer[] arrayS = {10, 7, 5, 1, -3};
        assert(Arrays.equals(SelectSort.sortDesc(array), arrayS));

        Integer[] sortedArray = {100, 5, 3, 2, 1};
        Integer[] sortedArrayS = {100, 5, 3, 2, 1};
        assert(Arrays.equals(SelectSort.sortDesc(sortedArray), sortedArrayS));

        Integer[] duplicateArray = {10, 1, 7, -3, 7};
        Integer[] duplicateArrayS = {10, 7, 7, 1, -3};
        assert(Arrays.equals(SelectSort.sortDesc(duplicateArray), duplicateArrayS));
    }
}
