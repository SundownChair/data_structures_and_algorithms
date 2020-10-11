package com.pedrofonseca.dsalgo.sort_algorithms;

public class InsertSort {

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

        for (int index = 1; index < pArray.length; index++) {
            for (int innerIndex = index; innerIndex > 0; innerIndex--) {
                T cur = pArray[innerIndex];
                T curPrev = pArray[innerIndex - 1];
                if (currentSortType == SortEnum.SortType.ASC && cur.compareTo(curPrev) < 0) {
                    pArray[innerIndex] = curPrev;
                    pArray[innerIndex - 1] = cur;
                } else if (currentSortType == SortEnum.SortType.DSC && cur.compareTo(curPrev) > 0) {
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
