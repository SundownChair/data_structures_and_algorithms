package com.pedrofonseca.dsalgo.sort_algorithms;

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
