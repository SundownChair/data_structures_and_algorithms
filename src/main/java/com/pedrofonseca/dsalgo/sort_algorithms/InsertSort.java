package com.pedrofonseca.dsalgo.sort_algorithms;

public class InsertSort {

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

        for (int index = 1; index < pArray.length; index++) {
            for (int innerIndex = index; innerIndex > 0; innerIndex--) {
                T cur = pArray[innerIndex];
                T curPrev = pArray[innerIndex - 1];
                if (cur.compareTo(curPrev) * sortModifier < 0) {
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
