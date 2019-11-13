package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IDeque<T> {

    /**
     * Add element to the end of the deque
     *
     * @param pElement Element to add
     * @return
     */
    boolean addLast(T pElement);

    /**
     * Remove element from the end of the deque
     *
     * @return
     */
    T removeLast();

    /**
     * Add element to the front of the deque
     *
     * @param pElement Element to be pushed into the stack
     * @return
     */
    boolean addFirst(T pElement);

    /**
     * Remove element from the front of the deque
     *
     * @return
     */
    T removeFirst();

    T peekFirst();

    T peekLast();

    int size();
}
