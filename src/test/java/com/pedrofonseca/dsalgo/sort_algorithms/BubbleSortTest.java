package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class BubbleSortTest {

    private BubbleSort<Integer> mTestedClass;

    @Test
    public void testSort() {
        mTestedClass = new BubbleSort<>();

        assertNull(mTestedClass.sort(null));

        Integer[] nullArray = {};
        assertNull(mTestedClass.sort(nullArray));

        Integer[] singleArray = {1};
        Integer[] singleArrayS = {1};
        assert(Arrays.equals(singleArrayS, mTestedClass.sort(singleArray)));

        Integer[] sortedArray = {1,2,3};
        assert(Arrays.equals(sortedArray, mTestedClass.sort(sortedArray)));

        Integer[] unsortedArray = {9, 10, 2, 5, 6, 7, 4, 1, 3, 8, 1};
        Integer[] unsortedArrayS = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assert(Arrays.equals(unsortedArrayS, mTestedClass.sort(unsortedArray)));
    }
}
