package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IDeque<T> {

    /**
     * Add element to the end of the deque.
     *
     * @param pElement Element to add.
     * @return true if element was added, false otherwise.
     */
    boolean addLast(T pElement);

    /**
     * Remove element from the end of the deque.
     *
     * @return Removed element.
     */
    T removeLast();

    /**
     * Add element to the front of the deque.
     *
     * @param pElement Element to be pushed into the stack.
     * @return true if element was added, false otherwise.
     */
    boolean addFirst(T pElement);

    /**
     * Remove element from the front of the deque.
     *
     * @return Removed element.
     */
    T removeFirst();

    /**
     * Get the element in the first position of the deque. Deque remains unchanged.
     *
     * @return Element at first position.
     */
    T peekFirst();

    /**
     * Get the element in the last position of the deque. Deque remains unchanged.
     *
     * @return Element at last position.
     */
    T peekLast();

    /**
     * Get amount of elements in deque.
     *
     * @return Number of elements.
     */
    int size();
}
