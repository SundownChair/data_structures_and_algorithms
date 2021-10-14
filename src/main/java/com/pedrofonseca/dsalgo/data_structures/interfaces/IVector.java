package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IVector<T> {

    /**
     * Add element to end of the vector.
     * @param pValue Element to add.
     * @return True if insert successful.
     */
    void add(T pValue);

    /**
     * Insert element at given index, if index exists. Transpose remaining elements forward.
     * @param index Index to insert element at.
     * @param pValue Element to add.
     * @return True if insert successful.
     */
    void insert(int index, T pValue);

    /**
     * Remove element at given index. Transpose remaining elements backwards.
     * @param pIndex Index to remove element from.
     * @return Removed element.
     */
    T remove(int pIndex);

    /**
     * Remove first instance of given element. Transpose remaining elements backwards.
     * @param pValue Value to be removed.
     * @return Removed element.
     */
    T remove(T pValue);

    /**
     * Returns element at given index.
     *
     * @param pIndex Index to get element from.
     * @return Element at given index.
     */
    T get(int pIndex);

    /**
     * Returns index of first occurrence of given element.
     *
     * @param pValue Element to find.
     * @return int index of element in array, -1 if non existent.
     */
    int getIndex(T pValue);

    /**
     * Returns element that matches given element.
     *
     * @param pValue Element to find.
     * @return Element found, null if non existent.
     */
    T get(T pValue);

    /**
     * Returns number of elements currently at the array.
     *
     * @return int of number of elements.
     */
    int size();

    /**
     * Returns current capacity.
     *
     * @return int current capacity.
     */
    int capacity();

    /**
     * Reverses array.
     */
    void reverse();
}
