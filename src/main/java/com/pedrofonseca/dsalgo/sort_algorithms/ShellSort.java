package com.pedrofonseca.dsalgo.sort_algorithms;

public class ShellSort<T extends Comparable<? super T>> {

    public static enum SortType { ASC, DSC }

    private SortType currentSortType = SortType.ASC;

    public ShellSort() { }

    public ShellSort(SortType pSortType) {
        currentSortType = pSortType;
    }

    public T[] sort(T[] pArray) {
        if(pArray == null || pArray.length == 0) {
            return null;
        }

        for (int step = pArray.length / 2; step > 0; step /= 2) {
            for (int index = step; index < pArray.length; index++) {
                T cur = pArray[index];
                T curPrev = pArray[index - step];
                if (currentSortType == SortType.ASC && cur.compareTo(curPrev) < 0) {
                    pArray[index] = curPrev;
                    pArray[index - step] = cur;
                } else if (currentSortType == SortType.DSC && cur.compareTo(curPrev) > 0) {
                    pArray[index] = curPrev;
                    pArray[index - step] = cur;
                }
            }
        }

        return pArray;
    }

}
