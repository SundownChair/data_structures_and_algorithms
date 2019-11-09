package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IQueue;

public class ArrayQueue<T> implements IQueue<T> {

    private static final int MIN_SIZE = 5;

    private T[] mQueue;
    private int mFrontIndex;
    private int mBackIndex;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        mQueue = (T[]) new Object[MIN_SIZE];
        mFrontIndex = 0;
        mBackIndex = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T pElement) {
        if(pElement == null) {
            return false;
        }

        if (size() >= mQueue.length) {
            increaseSize();
        }

        mBackIndex++;
        mQueue[backIndex()] = pElement;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T poll() {
        if (size() == 0) {
            return null;
        }

        T lReturnable = mQueue[frontIndex()];
        mQueue[frontIndex()] = null;
        mFrontIndex++;

        if (size() > 0 && size() < mQueue.length / 4) {
            decreaseSize();
        }

        return lReturnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T peek() {
        if (size() == 0) {
            return null;
        }

        return mQueue[frontIndex()];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return mBackIndex - mFrontIndex + 1;
    }

    /**
     * Duplicates length of the queue array, sequences data and resets pointers
     */
    @SuppressWarnings("unchecked")
    private void increaseSize() {
        T[] lNewArray = (T[]) new Object[mQueue.length * 2];

        resize(lNewArray);
    }

    /**
     * Halves length of the queue array, sequences data and resets pointers
     */
    @SuppressWarnings("unchecked")
    private void decreaseSize() {
        T[] lNewArray = (T[]) new Object[mQueue.length / 2];

        resize(lNewArray);
    }

    private void resize(T[] pNewArray) {
        int lSublength = 0;

        // If array is wrapped, get last chunk
        if (backIndex() < frontIndex()) {
            lSublength = size() - (mQueue.length - frontIndex());
            System.arraycopy(mQueue, 0, pNewArray, mQueue.length - lSublength, lSublength);
        }

        System.arraycopy(mQueue, frontIndex(), pNewArray, 0,((mBackIndex - mFrontIndex)) - lSublength + 1);

        mQueue = pNewArray;
        mBackIndex = size() - 1;
        mFrontIndex = 0;
    }

    private int frontIndex() {
        return mFrontIndex % mQueue.length;
    }

    private int backIndex() {
        return mBackIndex % mQueue.length;
    }
}
