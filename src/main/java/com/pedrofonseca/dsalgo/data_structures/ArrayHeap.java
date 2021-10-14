package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IHeap;

import java.util.Arrays;

/**
 * <p>Array based Priority Queue that always keeps the most extreme element ready for access.</p>
 * <p></p>
 * <p>Average Access Time Complexity: O(1)<sup><i>Worst: O(1)</i></sup></p>
 * <p>Average Search Time Complexity: O(n)<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Insert Time Complexity: O(log(n))<sup><i>Worst: O(log(n))</i></sup></p>
 * <p>Average Delete Time Complexity: O(log(n))<sup><i>Worst: O(log(n))</i></sup></p>
 */
public class ArrayHeap<T extends Comparable<? super T>> implements IHeap<T> {

    private T[] mHeap;

    private int mSize;

    private final static int MIN_SIZE = 516;

    private HeapType mType;

    public static enum HeapType{
        Min(-1), Max(1);

        private int modifier;

        private HeapType(int pModifier) {
            modifier = pModifier;
        }

        public int getModifier() {
            return modifier;
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayHeap(HeapType pType) {
        mHeap = (T[]) new Comparable[MIN_SIZE];
        mSize = 0;
        mType = pType;
    }

    public ArrayHeap() {
        this(HeapType.Min);
    }

    @Override
    public void add(T pElement) {
        if(pElement == null)  {
            return;
        }

        if (mSize >= mHeap.length) {
            increaseSize();
        }

        mHeap[mSize] = pElement;
        heapifyUp(mSize);
        mSize++;

        return;
    }

    @Override
    public T peek() {
        if (mSize == 0) {
            return null;
        }

        return mHeap[0];
    }

    @Override
    public T poll() {
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
        if(lParentNode != null && moreExtreme(lNode, lParentNode)) {
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
        if (lLeftNode != null && !moreExtreme(lNode, lLeftNode) &&
            lRightNode != null && !moreExtreme(lNode, lRightNode)) {
            if (moreExtreme(lLeftNode, lRightNode)) {
                lNewIndex = lLeft;
                lNewNode = lLeftNode;
            } else {
                lNewIndex = lRight;
                lNewNode = lRightNode;
            }
        }
        else if (lRightNode != null && !moreExtreme(lNode, lRightNode)) {
            lNewIndex = lRight;
            lNewNode = lRightNode;
        }
        else if (lLeftNode != null && !moreExtreme(lNode, lLeftNode)){
            lNewIndex = lLeft;
            lNewNode = lLeftNode;
        }

        if(lNewNode == null) {
            return;
        }

        swapNodes(lNewIndex, pIndex);

        heapifyDown(lNewIndex);
    }

    /**
     * Returns true if first node is more extreme than second node, false otherwise.
     * @param pNode1
     * @param pNode2
     * @return
     */
    private boolean moreExtreme(T pNode1, T pNode2) {
        int compare = pNode1.compareTo(pNode2);
        if (mType.equals(HeapType.Min)) {
            if(compare < 0)
                return true;
            else
                return false;
        } else {
            if(compare > 0)
                return true;
            else
                return false;
        }
    }

    private void increaseSize() {
        mHeap = Arrays.copyOf(mHeap, mHeap.length * 2);
    }

    private void decreaseSize() {
        mHeap = Arrays.copyOf(mHeap, mHeap.length / 2);
    }
}
