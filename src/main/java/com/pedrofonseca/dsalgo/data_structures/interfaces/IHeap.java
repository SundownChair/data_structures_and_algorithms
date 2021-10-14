package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IHeap<T extends Comparable<? super T>> {

    /**
     * Add value to the heap.
     *
     * @param pElement Element to add.
     * @return true if element correctly added to heap; false otherwise.5
     */
    void add(T pElement);

    /**
     * Returns head element from heap (most extreme value). Returns null if heap is empty.
     *
     * @return Most extreme element on heap.
     */
    T peek();

    /**
     * Removes and returns head from heap (most extreme value). Returns null if heap id empty.
     *
     * @return Most extreme element on heap.
     */
    T poll();

    /**
     * Returns current amount of elements on the Heap.
     *
     * @return int number of elements contained.
     */
    int size();
}
