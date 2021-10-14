package com.pedrofonseca.dsalgo.sort_algorithms;

import com.pedrofonseca.dsalgo.data_structures.ArrayHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Sorting algorithm that builds a heap from the array elements then removes the root node into a new array until the
 * heap is empty. The heap constraints ensure that the root node is always the most extreme in the heap, so removing it
 * will produce an ordered array.</p>
 * <p></p>
 * <p>See <a href="https://www.youtube.com/watch?v=MtQL_ll5KhQ">https://www.youtube.com/watch?v=MtQL_ll5KhQ</a></p>
 * <p></p>
 * <b><p>Average Time Complexity: O(n log(n))</p></b>
 * <p>Worst Time Complexity: O(n log(n))</p>
 * <p>Best Time Complexity: O(n log(n))</p>
 */
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
            sortedArray.add(lHeap.poll());
        }

        return sortedArray;
    }
}
