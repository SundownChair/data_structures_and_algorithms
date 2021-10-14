package com.pedrofonseca.dsalgo.sort_algorithms;

/**
 * <p>On each iteration, set a pointer to index = 0+i (where i is the current iteration, starting at 0) and compare all
 * elements from index to n, setting the most extreme at index. Repeat until array is sorted.</p>
 * <p>Similar to Insert Sort, but worst case scenario will still perform a full scan, even if no swaps are made.</p>
 * <p></p>
 * <p>See <a href="https://www.youtube.com/watch?v=xWBP4lzkoyM">https://www.youtube.com/watch?v=xWBP4lzkoyM</a></p>
 * <p></p>
 * <b><p>Average Time Complexity: O(n²)</p></b>
 * <p>Worst Time Complexity: O(n²)</p>
 * <p>Best Time Complexity: O(n²)</p>
 */
public class SelectSort {

    private static int sortModifier;

    public static <T extends Comparable<? super T>> T[] sortDesc(T[] pArray) {
        sortModifier = -1;
        return sort(pArray);
    }

    public static <T extends Comparable<? super T>> T[] sortAsc(T[] pArray) {
        sortModifier = 1;
        return sort(pArray);
    }

    private static <T extends Comparable<? super T>> T[] sort(T[] pArray) {
        if (pArray == null || pArray.length == 1) {
            return pArray;
        }

        for (int index = 0; index < pArray.length - 1; index++) {
            int extremeIndex = findExtremeIndexFromSubarray(pArray, index);
            T tmp = pArray[index];
            pArray[index] = pArray[extremeIndex];
            pArray[extremeIndex] = tmp;
        }

        return pArray;
    }

    private static <T extends Comparable<? super T>> int findExtremeIndexFromSubarray(T[] pArray, int pStartIndex) {
        T extreme = pArray[pStartIndex];
        int extremeIndex = pStartIndex;

        for (int index = pStartIndex + 1; index < pArray.length; index++) {
            T cur = pArray[index];
            if (cur.compareTo(extreme) * sortModifier < 0) {
                extreme = cur;
                extremeIndex = index;
            }
        }

        return extremeIndex;
    }

}
