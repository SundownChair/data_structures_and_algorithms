package com.pedrofonseca.dsalgo.sort_algorithms;

public class BubbleSort<T extends Comparable<? super T>> {

    public T[] sort(T[] pArray) {
        if (pArray == null || pArray.length == 0) {
            return null;
        }

        boolean noSwap = false;
        while (noSwap == false) {
            noSwap = true;
            for(int i = 0; i < pArray.length - 1; i++) {
                if(pArray[i].compareTo(pArray[i+1]) > 0) {
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
