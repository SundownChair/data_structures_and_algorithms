package com.pedrofonseca.dsalgo.sort_algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Divide and conquer sorting algorithm that splits the array in two halves, recursively calls itself for both and
 * then merges them back together.</p>
 * <p></p>
 * <p>See <a href="https://www.youtube.com/watch?v=JSceec-wEyw">https://www.youtube.com/watch?v=JSceec-wEyw</a></p>
 * <p></p>
 * <b><p>Average Time Complexity: O(n log(n))</p></b>
 * <p>Worst Time Complexity: O(n log(n))</p>
 * <p>Best Time Complexity: O(n log(n))</p>
 */
public class MergeSort {

    private static int sortModifier;

    public static <T extends Comparable<? super T>> T[] sortAsc(T[] pArray) {
        sortModifier = 1;
        return sort(pArray);
    }

    public static <T extends Comparable<? super T>> T[] sortDesc(T[] pArray) {
        sortModifier = -1;
        return sort(pArray);
    }

    private static <T extends Comparable<? super T>> T[] sort(T[] pArray) {
        if (pArray == null || pArray.length <= 1) {
            return pArray;
        }

        return sort(pArray, 0, pArray.length - 1);
    }

    private static <T extends Comparable<? super T>> T[] sort(T[] pArray, int pStartIndex, int pEndIndex) {
        if(pStartIndex < pEndIndex) {
            // Determine middle index
            int midIndex = (pStartIndex + pEndIndex) / 2;

            // Sort both halves
            sort(pArray, pStartIndex, midIndex);
            sort(pArray, midIndex + 1, pEndIndex);

            // Merge sorted halves
            mergeHalves(pArray, pStartIndex, midIndex, pEndIndex);
        }

        return pArray;
    }

    private static <T extends Comparable<? super T>> void mergeHalves(T[] pArray, int pStartIndex, int pMidIndex, int pEndIndex) {
        List<T> tmp = new ArrayList<>();
        int leftPointer = pStartIndex;
        int rightPointer = pMidIndex + 1; // +1 since sorting assumes right array start index as mid+1

        // Increment pointers, add to temp list more extreme value of the two halves until any pointer iterates all elements
        while (leftPointer <= pMidIndex && rightPointer <= pEndIndex) {
            if(pArray[leftPointer].compareTo(pArray[rightPointer]) * sortModifier < 0) {
                tmp.add(pArray[leftPointer++]);
            } else {
                tmp.add(pArray[rightPointer++]);
            }
        }

        // Add remaining left half to temp array
        while (leftPointer <= pMidIndex) {
            tmp.add(pArray[leftPointer++]);
        }

        // Add remaining right half to temp array
        while (rightPointer <= pEndIndex) {
            tmp.add(pArray[rightPointer++]);
        }

        // Set temp elements to array inside interval
        int i = pStartIndex;
        for (T t : tmp) {
            pArray[i++] = t;
        }
    }
}
