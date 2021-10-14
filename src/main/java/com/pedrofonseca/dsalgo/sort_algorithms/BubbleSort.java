package com.pedrofonseca.dsalgo.sort_algorithms;

/**
 * <p>Compares adjacent elements in an array and swaps elements if the leftmost is more extreme than the rightmost.
 * Every iteration is sure to leave the most extreme element in the last position of the array, so the next iteration
 * decreases the working range by one position (ie. run 1: 0 to n; run 2: 0 to n-1, element at n is the most
 * extreme).</p>
 * <p></p>
 * <p>See <a href="https://www.youtube.com/watch?v=nmhjrI-aW5o">https://www.youtube.com/watch?v=nmhjrI-aW5o</a></p>
 * <p></p>
 * <b><p>Average Time Complexity: O(n²)</p></b>
 * <p>Worst Time Complexity: O(n²)</p>
 * <p>Best Time Complexity: O(n)</p>
 */
public class BubbleSort {

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

        boolean noSwap = false;
        while (!noSwap) {
            noSwap = true;
            for(int i = 0; i < pArray.length - 1; i++) {
                if(pArray[i].compareTo(pArray[i+1]) * sortModifier > 0) {
                    noSwap = false;
                    T tmp = pArray[i];
                    pArray[i] = pArray[i+1];
                    pArray[i+1] = tmp;
                }
            }
        }

        return pArray;
    }
}
