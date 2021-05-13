package com.pedrofonseca.dsalgo.sort_algorithms;

public class ShellSort {

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
        if(pArray == null || pArray.length == 0) {
            return null;
        }

        // Gradually reduce step from half length to 1
        for (int step = pArray.length / 2; step > 0; step /= 2) {
            for (int index = step; index < pArray.length; index++) {
                int tmpIndex = index;
                // Swap value with previous while index is larger than step and previous value is less extreme
                while(tmpIndex >= step && pArray[tmpIndex].compareTo(pArray[tmpIndex - step]) * sortModifier < 0) {
                    T tmp = pArray[tmpIndex];
                    pArray[tmpIndex] = pArray[tmpIndex - step];
                    pArray[tmpIndex - step] = tmp;
                    tmpIndex -= step;
                }
            }
        }

        return pArray;
    }

}
