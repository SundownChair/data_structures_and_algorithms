package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IHeap;

import java.util.Arrays;

/**
 * Min Heap implementation.
 * @param <T>
 */
public class ArrayHeap<T extends Comparable<? super T>> implements IHeap<T> {

    private T[] mHeap;

    private int mSize;

    private final static int MIN_SIZE = 516;

    @SuppressWarnings("unchecked")
    public ArrayHeap() {
        mHeap = (T[]) new Comparable[MIN_SIZE];
        mSize = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T pElement) {
        if(pElement == null)  {
            return false;
        }

        if (mSize >= mHeap.length) {
            increaseSize();
        }

        mHeap[mSize] = pElement;
        heapifyUp(mSize);
        mSize++;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getHead() {
        if (mSize == 0) {
            return null;
        }

        return mHeap[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T removeHead() {
        if (mSize == 0) {
            return null;
        }

        T lHead = mHeap[0];
        mSize--;

        if (mSize <= mHeap.length / 4) {
            decreaseSize();
        }

        mHeap[0] = mHeap[mSize];
        mHeap[mSize] = null;
        heapifyDown(0);

        return lHead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return mSize;
    }

    private int getParentIndex(int pIndex) {
        return pIndex > 0 ? (pIndex - 1) / 2 : -1;
    }

    private int getLeftIndex(int pIndex) {
        return 2 * pIndex + 1;
    }

    private int getRightIndex(int pIndex) {
        return 2 * pIndex + 2;
    }

    private void swapNodes(int pIndex1, int pIndex2) {
        T tmp = mHeap[pIndex1];
        mHeap[pIndex1] = mHeap[pIndex2];
        mHeap[pIndex2] = tmp;
    }

    private void heapifyUp(int pIndex) {
        T lNode = mHeap[pIndex];
        if (lNode == null || pIndex == 0) {
            return;
        }

        int lParent = getParentIndex(pIndex);
        T lParentNode = mHeap[lParent];
        if(lParentNode != null && lNode.compareTo(lParentNode) < 0) {
            swapNodes(lParent, pIndex);
            heapifyUp(lParent);
        }
    }

    private void heapifyDown(int pIndex) {
        T lNode = mHeap[pIndex];
        if (lNode == null) {
            return;
        }

        int lLeft = getLeftIndex(pIndex);
        int lRight = getRightIndex(pIndex);
        T lLeftNode = lLeft > 0 && lLeft < mSize ? mHeap[lLeft] : null;
        T lRightNode = lRight > 0 && lRight < mSize ? mHeap[lRight] : null;

        int lNewIndex = -1;
        T lNewNode = null;
        // Both are lesser than node
        if (lLeftNode != null && lNode.compareTo(lLeftNode) > 0 &&
            lRightNode != null && lNode.compareTo(lRightNode) > 0) {
            // Swap with smallest one, if equal, use right
            if (lLeftNode.compareTo(lRightNode) < 0) {
                lNewIndex = lLeft;
                lNewNode = lLeftNode;
            } else {
                lNewIndex = lRight;
                lNewNode = lRightNode;
            }
        }
        // Right is lesser than node
        else if (lRightNode != null && lNode.compareTo(lRightNode) > 0) {
            lNewIndex = lRight;
            lNewNode = lRightNode;
        }
        // Left is lesser than node
        else if (lLeftNode != null && lNode.compareTo(lLeftNode) > 0){
            lNewIndex = lLeft;
            lNewNode = lLeftNode;
        }

        if(lNewNode == null) {
            return;
        }

        swapNodes(lNewIndex, pIndex);

        heapifyDown(lNewIndex);
    }

    private void increaseSize() {
        mHeap = Arrays.copyOf(mHeap, mHeap.length * 2);
    }

    private void decreaseSize() {
        mHeap = Arrays.copyOf(mHeap, mHeap.length / 2);
    }
}
