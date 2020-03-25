package com.pedrofonseca.dsalgo.sort_algorithms;

public class InsertSort<T extends Comparable<? super T>> {

    public static enum SortType { ASC, DSC }

    private SortType currentSortType = SortType.ASC;

    public InsertSort() { }

    public InsertSort(SortType pSortType) {
        currentSortType = pSortType;
    }

    public T[] sort(T[] pArray) {
        if (pArray == null || pArray.length == 0) {
            return null;
        }

        for (int index = 1; index < pArray.length; index++) {
            for (int innerIndex = index; innerIndex > 0; innerIndex--) {
                T tmp = pArray[innerIndex];
                if (currentSortType == SortType.ASC && pArray[innerIndex].compareTo(pArray[innerIndex - 1]) < 0) {
                    pArray[innerIndex] = pArray[innerIndex - 1];
                    pArray[innerIndex - 1] = tmp;
                } else if (currentSortType == SortType.DSC && pArray[innerIndex].compareTo(pArray[innerIndex - 1]) > 0) {
                    pArray[innerIndex] = pArray[innerIndex - 1];
                    pArray[innerIndex - 1] = tmp;
                } else {
                    break;
                }
            }
        }

        return pArray;
    }
}
