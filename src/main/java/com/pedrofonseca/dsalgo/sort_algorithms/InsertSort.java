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
                T cur = pArray[innerIndex];
                T curPrev = pArray[innerIndex - 1];
                if (currentSortType == SortType.ASC && cur.compareTo(curPrev) < 0) {
                    pArray[innerIndex] = curPrev;
                    pArray[innerIndex - 1] = cur;
                } else if (currentSortType == SortType.DSC && cur.compareTo(curPrev) > 0) {
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
