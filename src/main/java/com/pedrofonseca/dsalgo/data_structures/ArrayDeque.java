package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IDeque;

/**
 * <p>Short for Double Ended Queue. Data Structure that can simultaneously work as a queue and a stack. Array insertion
 * and deletion is controlled via two pointers, allowing for queueing/dequeueing and stacking/unstacking.</p>
 * <p></p>
 * <p>Average Access Time Complexity: O(n)<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Search Time Complexity: O(n)<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Insert Time Complexity: O(1)<sup><i>Worst: O(1)</i></sup></p>
 * <p>Average Delete Time Complexity: O(1)<sup><i>Worst: O(1)</i></sup></p>
 */
public class ArrayDeque<T> implements IDeque<T> {

    private final static int MIN_SIZE = 128;

    private T[] mDeque;
    private int mFrontIndex;
    private int mBackIndex;
    private int mSize;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        mDeque = (T[]) new Object[MIN_SIZE];
        mFrontIndex = -1;
        mBackIndex = -1;
        mSize = 0;
    }

    public boolean addLast(T pElement) {
        if (pElement == null) {
            return false;
        }

        // Set back index
        if(mFrontIndex == -1) {
            // Deque empty, reset indexes
            mFrontIndex = 0;
            mBackIndex = 0;
        } else if (mBackIndex >= mDeque.length - 1) {
            // Wrap back index to 0
            mBackIndex = 0;
        } else {
            mBackIndex++;
        }

        mDeque[mBackIndex] = pElement;
        mSize++;

        if (mSize >= mDeque.length) {
            resizeUp();
        }

        return true;
    }

    public T removeLast() {
        if (mSize == 0) {
            return null;
        }

        T lReturn = mDeque[mBackIndex];
        mSize--;

        // Set back index
        if (mSize == 0) {
            // Deque empty, reset indexes
            mFrontIndex = -1;
            mBackIndex = 0;
        } else if (mBackIndex <= 0) {
            // Wrap index to end
            mBackIndex = mDeque.length;
        } else {
            // Decrease back index
            mBackIndex--;
        }

        if (mSize > 0 && mSize < mDeque.length / 4) {
            resizeDown();
        }

        return lReturn;
    }

    public boolean addFirst(T pElement) {
        if (pElement == null) {
            return false;
        }

        // Set front index
        if(mFrontIndex == -1) {
            // Deque empty, reset indexes
            mFrontIndex = 0;
            mBackIndex = 0;
        } else if (mFrontIndex == 0) {
            // Wrap front index
            mFrontIndex = mDeque.length - 1;
        } else {
            // Decrement front index
            mFrontIndex--;
        }

        mDeque[mFrontIndex] = pElement;
        mSize++;

        if(mSize == mDeque.length) {
            resizeUp();
        }

        return true;
    }

    public T removeFirst() {
        if (mSize == 0) {
            return null;
        }

        T lReturn = mDeque[mFrontIndex];
        mDeque[mFrontIndex] = null;
        mSize--;

        // Set front index
        if (mSize == 0) {
            // Deque empty, reset indexes
            mFrontIndex = -1;
            mBackIndex = 0;
        } else if(mFrontIndex + 1 >= mDeque.length) {
            // Wrap index back to 0
            mFrontIndex = 0;
        } else {
            // Increment front index
            mFrontIndex++;
        }

        if (mSize > 0 && mSize < mDeque.length / 4) {
            resizeDown();
        }

        return lReturn;
    }

    @Override
    public T peekFirst() {
        if (mSize == 0) {
            return null;
        } else {
            return mDeque[mFrontIndex];
        }
    }

    @Override
    public T peekLast() {
        if (mSize == 0) {
            return null;
        } else {
            return mDeque[mBackIndex];
        }
    }

    @Override
    public int size() {
        return mSize;
    }

    private void resizeUp() {
        resize(mDeque.length * 2);
    }

    private void resizeDown() {
        resize(mDeque.length / 2);
    }

    @SuppressWarnings("unchecked")
    private void resize(int pNewSize) {
        T[] lNewArray = (T[]) new Object[pNewSize];
        if(mFrontIndex > mBackIndex) {
            // Copy from front index to end
            System.arraycopy(mDeque, mFrontIndex, lNewArray, 0, mDeque.length - mFrontIndex);
            // copy from start to last index
            System.arraycopy(mDeque, 0, lNewArray, mDeque.length - mFrontIndex, mSize - (mDeque.length - mFrontIndex));
        } else {
            // Copy from front index to last index
            System.arraycopy(mDeque, mFrontIndex, lNewArray, 0, mSize);
        }

        mDeque = lNewArray;
        mFrontIndex = 0;
        mBackIndex = mSize - 1;
    }
}
