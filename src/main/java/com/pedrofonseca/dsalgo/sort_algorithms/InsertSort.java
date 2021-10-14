package com.pedrofonseca.dsalgo.sort_algorithms;

/**
 * <p>In a gradually increasing interval, place the first element outside of the interval in its correct position. The
 * interval is then increased by 1 and the process iterated until the array is sorted.</p>
 * <p>Suited only for small arrays or nearly sorted arrays since it moves elements frequently (the same element can be
 * swapped multiple times).</p>
 * <p></p>
 * <p>See <a href="https://www.youtube.com/watch?v=OGzPmgsI-pQ">https://www.youtube.com/watch?v=OGzPmgsI-pQ</a></p>
 * <p></p>
 * <b><p>Average Time Complexity: O(n²)</p></b>
 * <p>Worst Time Complexity: O(n²)</p>
 * <p>Best Time Complexity: O(n)</p>
 */
public class InsertSort {

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
        if (pArray == null || pArray.length == 0) {
            return null;
        }

        for (int index = 1; index < pArray.length; index++) {
            for (int innerIndex = index; innerIndex > 0; innerIndex--) {
                T cur = pArray[innerIndex];
                T curPrev = pArray[innerIndex - 1];
                if (cur.compareTo(curPrev) * sortModifier < 0) {
                    pArray[innerIndex] = curPrev;
                    pArray[innerIndex - 1] = cur;
                } else {
                    break;
                }
            }
        }

        return pArray;
    }
}
