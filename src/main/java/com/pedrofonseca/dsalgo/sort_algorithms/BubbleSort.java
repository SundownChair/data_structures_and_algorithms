package com.pedrofonseca.dsalgo.sort_algorithms;

public class BubbleSort {

    private static SortEnum.SortType currentSortType;

    public static <T extends Comparable<? super T>> T[] sortAsc(T[] pArray) {
        currentSortType = SortEnum.SortType.ASC;
        return sort(pArray);
    }

    public static <T extends Comparable<? super T>> T[] sortDesc(T[] pArray) {
        currentSortType = SortEnum.SortType.DSC;
        return sort(pArray);
    }

    private static <T extends Comparable<? super T>> T[] sort(T[] pArray) {
        if (pArray == null || pArray.length == 0) {
            return null;
        }

        boolean noSwap = false;
        while (noSwap == false) {
            noSwap = true;
            for(int i = 0; i < pArray.length - 1; i++) {
                if(pArray[i].compareTo(pArray[i+1]) * (currentSortType == SortEnum.SortType.ASC ? 1 : -1) > 0) {
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
