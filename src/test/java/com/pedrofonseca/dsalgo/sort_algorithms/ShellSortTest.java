package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import java.util.Arrays;

public class ShellSortTest {

    ShellSort<Integer> mTestedClass;

    @Test
    public void testAscendingSort() {
        mTestedClass = new ShellSort<>();

        mTestedClass.sort(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assert(Arrays.equals(mTestedClass.sort(singleArray), singleArrayS));

        Integer[] biArray = {10, 1};
        Integer[] biArrayS = {1, 10};
        assert(Arrays.equals(mTestedClass.sort(biArray), biArrayS));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {1, 10};
        assert(Arrays.equals(mTestedClass.sort(sortedBiArray), sortedBiArrayS));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {-1, 0, 1, 3, 7, 9, 10, 10};
        assert(Arrays.equals(mTestedClass.sort(array), arrayS));

        Integer[] sortedArray = {-1, 0, 1, 2, 3, 4};
        Integer[] sortedArrayS = {-1, 0, 1, 2, 3, 4};
        assert(Arrays.equals(mTestedClass.sort(sortedArray), sortedArrayS));
    }

    @Test
    public void testDescendingSort() {
        mTestedClass = new ShellSort<>(ShellSort.SortType.DSC);

        mTestedClass.sort(null);

        Integer[] singleArray = {0};
        Integer[] singleArrayS = {0};
        assert(Arrays.equals(mTestedClass.sort(singleArray), singleArrayS));

        Integer[] biArray = {1, 10};
        Integer[] biArrayS = {10, 1};
        assert(Arrays.equals(mTestedClass.sort(biArray), biArrayS));

        Integer[] sortedBiArray = {10, 1};
        Integer[] sortedBiArrayS = {10, 1};
        assert(Arrays.equals(mTestedClass.sort(sortedBiArray), sortedBiArrayS));

        Integer[] array = {10, 1, 7, 9, 3, 10, -1, 0};
        Integer[] arrayS = {10, 10, 9, 7, 3, 1, 0, -1};
        assert(Arrays.equals(mTestedClass.sort(array), arrayS));

        Integer[] sortedArray = {4, 3, 2, 1, 0, -1};
        Integer[] sortedArrayS = {4, 3, 2, 1, 0, -1};
        assert(Arrays.equals(mTestedClass.sort(sortedArray), sortedArrayS));
    }
}
