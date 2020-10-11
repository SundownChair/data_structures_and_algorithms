package com.pedrofonseca.dsalgo.sort_algorithms;

public class SelectSort {

    private static SortEnum.SortType currentSortType;

    public static <T extends Comparable<? super T>> T[] sortDesc(T[] pArray) {
        currentSortType = SortEnum.SortType.DSC;
        return sort(pArray);
    }

    public static <T extends Comparable<? super T>> T[] sortAsc(T[] pArray) {
        currentSortType = SortEnum.SortType.ASC;
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
            if (cur.compareTo(extreme) * (currentSortType == SortEnum.SortType.ASC ? 1 : -1) < 0) {
                extreme = cur;
                extremeIndex = index;
            }
        }

        return extremeIndex;
    }

}
