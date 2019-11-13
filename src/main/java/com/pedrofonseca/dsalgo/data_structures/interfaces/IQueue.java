package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IQueue<T> {
    /**
     * Add element to last position of the queue
     *
     * @param pElement Element to add
     * @return true if insert was successful; false if not
     */
    boolean add(T pElement);

    /**
     * Removes element at the front of the queue
     *
     * @return Element at front of the queue. null if queue is empty
     */
    T poll();

    /**
     * Returns element at the front of the queue without removing it.
     *
     * @return Element at front of the queue. null if queue is empty
     */
    T peek();

    /**
     * Returns number of elements currently queued
     *
     * @return Number of queued elements
     */
    int size();
}
