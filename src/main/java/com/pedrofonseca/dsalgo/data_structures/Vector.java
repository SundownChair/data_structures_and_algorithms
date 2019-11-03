package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IVector;

public class Vector<T> implements IVector<T> {

    private static final int STARTING_SIZE = 5;

    private T[] mArray;

    private int mSize;

    public Vector() {
        mArray = (T[]) new Object[STARTING_SIZE];
        mSize = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T pValue) {
        mArray[mSize] = pValue;
        mSize++;

        resize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(int pIndex, T pValue) {
        indexCheck(pIndex);

        T curr = pValue;
        int index = pIndex;
        while(curr != null) {
            T tmp = mArray[index];
            mArray[index] = curr;
            curr = tmp;

            index++;
        }

        mSize++;

        resize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(int pIndex) {
        indexCheck(pIndex);

        T returnable = mArray[pIndex];

        int lIndex;
        for (lIndex = pIndex; lIndex < mSize - 1; lIndex++) {
            mArray[lIndex] = mArray[lIndex + 1];
        }
        mArray[lIndex] = null;

        mSize--;

        resize();

        return returnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(T pValue) {
        T returnable = null;

        for (int index = 0; index < mSize; index++) {
            if (mArray[index].equals(pValue)) {
                returnable = remove(index);
                break;
            }
        }

        return returnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int pIndex) {
        indexCheck(pIndex);

        return mArray[pIndex];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndex(T pValue) {
        for (int index = 0; index < mSize; index++) {
            if(mArray[index].equals(pValue)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(T pValue) {
        for (T ele : mArray) {
            if (ele.equals(pValue)) {
                return ele;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return mSize;
    }

    @Override
    public int capacity() {
        return mArray.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {

    }

    private void indexCheck(int pIndex) {
        if (pIndex >= mArray.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void resize() {
        if (mSize > 0) {
            if (mSize > mArray.length / 2) {
                T[] newArray = (T[]) new Object[mArray.length * 2];
                for(int index = 0; index < mArray.length; index++ ) {
                    newArray[index] = mArray[index];
                }
                mArray = newArray;
            } else if (mSize < mArray.length / 4)  {
                T[] newArray = (T[]) new Object[mArray.length / 2];
                for(int index = 0; index < mArray.length; index++ ) {
                    newArray[index] = mArray[index];
                }
                mArray = newArray;
            }
        }
    }
}
