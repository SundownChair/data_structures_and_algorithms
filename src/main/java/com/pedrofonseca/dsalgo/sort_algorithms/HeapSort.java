package com.pedrofonseca.dsalgo.sort_algorithms;

import com.pedrofonseca.dsalgo.data_structures.ArrayHeap;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IHeap;

import java.util.ArrayList;
import java.util.List;

public class HeapSort<T extends Comparable<? super T>> extends ArrayHeap<T> implements IHeap<T> {
    public HeapSort(HeapType pType) {
        super(pType);
    }

    public HeapSort() {
        super();
    }

    public List<T> sort() {
        List<T> sortedArray = new ArrayList<>();

        while (size() > 0) {
            sortedArray.add(removeHead());
        }

        return sortedArray;
    }
}
