package com.pedrofonseca.dsalgo.sort_algorithms;

import java.util.Arrays;

public class MergeSort {

    public static <T extends Comparable<? super T>> T[] sortAsc(T[] pArray) {
        return sort(pArray, 1);
    }

    public static <T extends Comparable<? super T>> T[] sortDesc(T[] pArray) {
        return sort(pArray, -1);
    }

    private static <T extends Comparable<? super T>> T[] sort(T[] pArray, int pSortTypeModifier) {
        if (pArray == null || pArray.length == 1) {
            return pArray;
        }

        int start = 0;
        int mid = pArray.length / 2;
        int end = pArray.length;

        T[] leftArray = sort(Arrays.copyOfRange(pArray, start, mid), pSortTypeModifier);
        T[] rightArray = sort(Arrays.copyOfRange(pArray, mid, end), pSortTypeModifier);

        // Merge arrays
        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = 0;
        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            int compare = leftArray[leftIndex].compareTo(rightArray[rightIndex]) * pSortTypeModifier;
            if (compare < 0) {
                pArray[arrayIndex++] = leftArray[leftIndex++];
            } else {
                pArray[arrayIndex++] = rightArray[rightIndex++];
            }
        }

        // Add remaining values
        while (leftIndex < leftArray.length) {
            pArray[arrayIndex++] = leftArray[leftIndex++];
        }
        while (rightIndex < rightArray.length) {
            pArray[arrayIndex++] = rightArray[rightIndex++];
        }

        return pArray;
    }
}
