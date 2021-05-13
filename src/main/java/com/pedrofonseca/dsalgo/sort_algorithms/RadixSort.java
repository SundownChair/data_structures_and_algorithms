package com.pedrofonseca.dsalgo.sort_algorithms;

import java.util.ArrayList;

public class RadixSort {

    private static int indexModifier;

    // Made for Integer. Use <T extends Number & Comparable<? super T>> for generic approach, offset will need adjusting
    public static Integer[] sortAsc(Integer[] pArray) {
        indexModifier = 0;
        return sort(pArray);
    }

    public static Integer[] sortDesc(Integer[] pArray) {
        indexModifier = 9;
        return sort(pArray);
    }

    private static Integer[] sort(Integer[] pArray) {
        if (pArray == null || pArray.length <= 1) {
            return pArray;
        }

        // Determine max/min values
        int max = pArray[0];
        for (Integer i : pArray) {
            max = Math.max(max, i);
        }

        // Loop through digits and perform counting sort
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(pArray, exp);
        }

        return pArray;
    }

    private static Integer[] countingSort(Integer[] pArray, int pExp) {
        // Create buckets (0 to 9)
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Fill buckets. Use absolute value to account for 0 modifier
        for(Integer i : pArray) {
            buckets[Math.abs(indexModifier - (i / pExp % 10))].add(i);
        }

        // Sort array
        int intIndex = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer value : bucket) {
                pArray[intIndex++] = value;
            }
        }

        return pArray;
    }
}
