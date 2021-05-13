package com.pedrofonseca.dsalgo.sort_algorithms;

public class CountingSort {

    private static int sortModifier;

    // Made for Integer. Use <T extends Number & Comparable<? super T>> for generic approach, offset will need adjusting
    public static Integer[] sortAsc(Integer[] pArray) {
        sortModifier = 1;
        return sort(pArray);
    }

    public static Integer[] sortDesc(Integer[] pArray) {
        sortModifier = -1;
        return sort(pArray);
    }

    private static Integer[] sort(Integer[] pArray) {
        if (pArray == null || pArray.length <= 1) {
            return pArray;
        }

        // Determine max/min values
        int max = pArray[0];
        int min = 0;
        for (Integer i : pArray) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        // Calculate offset to account for negative numbers
        int offset = Math.abs(min);

        // Create buckets
        int[] buckets = new int[offset + max + 1];

        // Fill buckets
        for(Integer i : pArray) {
            buckets[offset + i]++;
        }

        // Sort array
        int intIndex = sortModifier > 0 ? 0 : pArray.length - 1;
        for (int i = 0; i < buckets.length; i++) {
            while(buckets[i] > 0) {
                pArray[intIndex] = i - offset;
                intIndex += sortModifier;
                buckets[i]--;
            }
        }

        return pArray;
    }
}
