package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IStack<T> {

    /**
     * Pushes an element to the top of the array.
     *
     * @param pElement Element to be pushed into the stack
     * @return true if the element was successfully pushed into the stack; false otherwise
     */
    boolean push(T pElement);

    /**
     * Pops the element at the top of the stack.
     *
     * @return Element at the top of the stack; null if empty
     */
    T pop();

    /**
     * Looks at the element at the top of the stack.
     *
     * @return Element at the top of the stack; null if empty
     */
    T peek();

    /**
     * Returns number of elements currently stacked.
     *
     * @return Number of elements stacked
     */
    int size();
}
