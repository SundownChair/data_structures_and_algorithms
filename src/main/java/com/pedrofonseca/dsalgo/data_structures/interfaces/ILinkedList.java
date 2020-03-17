package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface ILinkedList<T> {

    /**
     * Adds element to the end of the list.
     *
     * @param pElement Element to add.
     * @return true if element added, false otherwise.
     */
    boolean add(T pElement);

    /**
     * Adds element at given index, if able.
     *
     * @param pElement Element to add.
     * @param pIndex Index where the element should be added.
     * @return true if element added, false otherwise.
     */
    boolean addAt(T pElement, int pIndex);

    /**
     * Adds element to the head of the list.
     *
     * @param pElement Element to add.
     * @return true if element added, false otherwise.
     */
    boolean push(T pElement);

    /**
     * Returns first element of the list.
     *
     * @return First element of the list, null if list is empty.
     */
    T peekFirst();

    /**
     * Returns last element of the list.
     *
     * @return Last element of the list, null if list is empty.
     */
    T peekLast();

    /**
     * Removes and returns first element of the list.
     *
     * @return First element of the list, null if list is empty.
     */
    T popFirst();

    /**
     * Removes and returns last element of the list.
     *
     * @return Last element of the list, null if list is empty.
     */
    T popLast();

    /**
     * Returns the number of elements currently contained in the list.
     *
     * @return
     */
    int size();

    /**
     * Returns the element at the given index, if able.
     *
     * @param pIndex Index from where to retrieve the element.
     * @return Element at given index, null if index is invalid.
     */
    T get(int pIndex);

    /**
     * Removes and returns the element at the given index, if able.
     *
     * @param pIndex Index from where to retrieve the element.
     * @return Element at given index, null if index is invalid.
     */
    T popAt(int pIndex);

    /**
     * Checks if given element exists in the list.
     *
     * @param pElement Element to check.
     * @return true if element exists in list, null otherwise.
     */
    boolean contains(T pElement);

    /**
     * Reverses list order.
     */
    void reverse();
}
