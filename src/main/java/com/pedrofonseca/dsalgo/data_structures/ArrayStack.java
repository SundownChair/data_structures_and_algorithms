package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IStack;

import java.util.Arrays;

/**
 * <p>Data Strcuture where data is stored and retrieved in a LIFO approach (last in/first out).</p>
 * <p></p>
 * <p>Average Access Time Complexity: O(n)<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Search Time Complexity: O(n)<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Insert Time Complexity: O(1)<sup><i>Worst: O(1)</i></sup></p>
 * <p>Average Delete Time Complexity: O(1)<sup><i>Worst: O(1)</i></sup></p>
 */
public class ArrayStack<T> implements IStack<T> {

    private T[] mStack;

    private int mSize;

    private static final int MIN_SIZE = 128;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        mStack = (T[]) new Object[MIN_SIZE];
        mSize = 0;
    }

    @Override
    public boolean push(T pElement) {
        if (pElement == null) {
            return false;
        }

        if (mSize >= mStack.length) {
            increaseSize();
        }

        mStack[mSize++] = pElement;

        return true;
    }

    @Override
    public T pop() {
        if (mSize == 0) {
            return null;
        }

        T lReturnable = mStack[--mSize];

        if (mSize < mStack.length / 4) {
            decreaseSize();
        }

        return lReturnable;
    }

    @Override
    public T peek() {
        if (mSize == 0) {
            return null;
        }

        return mStack[mSize - 1];
    }

    @Override
    public int size() {
        return mSize;
    }

    private void increaseSize() {
        mStack = Arrays.copyOf(mStack, mStack.length * 2);
    }

    private void decreaseSize() {
        mStack = Arrays.copyOf(mStack, mStack.length / 2);
    }
}
