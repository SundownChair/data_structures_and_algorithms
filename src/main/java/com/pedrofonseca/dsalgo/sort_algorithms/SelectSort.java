package com.pedrofonseca.dsalgo.sort_algorithms;

public class SelectSort<T extends Comparable<? super T>> {

    public static enum SortType { ASC, DSC }

    private SortType currentSortType = SortType.ASC;

    public SelectSort() { }

    public SelectSort(SortType pSortType) {
        currentSortType = pSortType;
    }

    public T[] sort(T[] pArray) {
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

    private int findExtremeIndexFromSubarray(T[] pArray, int pStartIndex) {
        T extreme = pArray[pStartIndex];
        int extremeIndex = pStartIndex;

        for (int index = pStartIndex + 1; index < pArray.length; index++) {
            T cur = pArray[index];
            if (currentSortType == SortType.ASC && cur.compareTo(extreme) < 0) {
                extreme = cur;
                extremeIndex = index;
            } else if (currentSortType == SortType.DSC && cur.compareTo(extreme) > 0) {
                extreme = cur;
                extremeIndex = index;
            }
        }

        return extremeIndex;
    }

}
