package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface ITree<T extends Comparable<? super T>> {

    /**
     * Adds value to tree.
     *
     * @param pValue Element to add.
     * @return true if successfully inserted, false otherwise.
     */
    boolean add(T pValue);

    /**
     * Removes first occurrence of value from tree.
     *
     * @param pValue Element to remove
     * @return Element removed, null if none found.
     */
    T remove(T pValue);

    /**
     * Current number of nodes in the tree.
     *
     * @return Number of nodes.
     */
    int size();

    /**
     * Checks if element currently exists in the tree.
     *
     * @return true if element exists, false otherwise.
     */
    boolean contains(T pValue);
}
