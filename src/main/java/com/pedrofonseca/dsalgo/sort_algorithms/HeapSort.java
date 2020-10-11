package com.pedrofonseca.dsalgo.sort_algorithms;

import com.pedrofonseca.dsalgo.data_structures.ArrayHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort {

    public static <T extends Comparable<? super T>> List<T> sortAsc(T[] pArray) {
        return sort(pArray, ArrayHeap.HeapType.Min);
    }

    public static <T extends Comparable<? super T>> List<T> sortDesc(T[] pArray) {
        return sort(pArray, ArrayHeap.HeapType.Max);
    }

    private static <T extends Comparable<? super T>> List<T> sort(T[] pArray, ArrayHeap.HeapType pHeapType) {
        ArrayHeap<T> lHeap = new ArrayHeap<>(pHeapType);
        Arrays.stream(pArray).forEach(lHeap::add);

        List<T> sortedArray = new ArrayList<>();

        while (lHeap.size() > 0) {
            sortedArray.add(lHeap.removeHead());
        }

        return sortedArray;
    }
}
