package com.pedrofonseca.dsalgo.sort_algorithms;

public class QuickSort {

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
        if(pArray == null || pArray.length == 1) {
            return pArray;
        }

        // First step sends entire to recursive method
        return rSort(pArray, 0, pArray.length-1);
    }

    private static <T extends Comparable<? super T>> T[] rSort(T[] pArray, int pStart, int pEnd) {
        // Return if indexes are impossible
        if(pStart >= pEnd) {
            return pArray;
        }

        // Partition and get pivot index
        int pivotIndex = partition(pArray, pStart, pEnd);

        rSort(pArray, pStart, pivotIndex - 1);
        rSort(pArray, pivotIndex, pEnd);

        return pArray;
    }

    private static <T extends Comparable<? super T>> int partition(T[] pArray, int pStart, int pEnd) {
        // Set pivot value as last value int given range
        T pivotValue = pArray[pEnd];

        // Set initial pivot index outside of given range
        int pivotIndex = pStart - 1;

        // Iterate through array, swap values where applicable
        for(int curIndex = pStart; curIndex < pEnd; curIndex++) {
            if(pArray[curIndex].compareTo(pivotValue) * sortModifier <= 0) {
                swap(pArray, curIndex, ++pivotIndex);
            }
        }

        // Swap value value at pivot index +1 with pivot value
        swap(pArray, pEnd, ++pivotIndex);
        return pivotIndex;
    }

    private static <T extends Comparable<? super T>> void swap(T[] pArray, int index1, int index2) {
        T tmp = pArray[index1];
        pArray[index1] = pArray[index2];
        pArray[index2] = tmp;
    }
}
