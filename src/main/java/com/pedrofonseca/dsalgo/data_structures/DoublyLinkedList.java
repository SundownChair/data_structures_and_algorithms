package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.ILinkedList;

public class DoublyLinkedList<T> implements ILinkedList<T> {

    private int mSize = 0;
    private Node<T> mHead = null;
    private Node<T> mTail = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T pElement) {
        if (pElement == null) {
            return false;
        }

        Node<T> newNode = new Node<>(pElement);

        if(mSize == 0) {
            mHead = newNode;
            mTail = newNode;
        } else {
            newNode.previous = mTail;
            mTail.next = newNode;
            mTail = newNode;
        }

        mSize++;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAt(T pElement, int pIndex) {
        if (pIndex == 0) {
            return push(pElement);
        } else if (pIndex == mSize) {
            return add(pElement);
        } else if (pIndex < 0 || pIndex > mSize || pElement == null) {
            return false;
        }

        Node<T> lNewNode = new Node<>(pElement);
        Node lNode = mHead;
        Node lPrevNode = null;
        for(int index = 0; index < mSize; index++) {
            if(index == pIndex - 1) {
                lPrevNode = lNode;
            } else if (index == pIndex) {
                break;
            }
            lNode = lNode.next;
        }

        lNewNode.previous = lPrevNode;
        lNewNode.next = lNode;
        lPrevNode.next = lNewNode;
        lNode.previous = lNewNode;

        mSize++;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean push(T pElement) {
        if (pElement == null) {
            return false;
        }

        if(mSize == 0) {
            return add(pElement);
        }

        Node<T> lNode = new Node<>(pElement);
        lNode.next = mHead;
        mHead.previous = lNode;
        mHead = lNode;

        mSize++;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T peekFirst() {
        return mSize > 0 ? mHead.value : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T peekLast() {
        return mSize > 0 ? mTail.value : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T popFirst() {
        if(mSize == 0) {
            return null;
        }

        Node<T> lReturn = mHead;
        mHead = mHead.next;
        lReturn.next = null;

        mSize--;
        return lReturn.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T popLast() {
        if(mSize == 0) {
            return null;
        }

        Node<T> lReturn = mTail;
        mTail = mTail.previous;
        lReturn.previous = null;

        mSize--;
        return lReturn.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return mSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T get(int pIndex) {
        Node<T> lCurrent = mHead;

        for(int index = 0; index < mSize; index++) {
            if (index == pIndex) {
                return lCurrent.value;
            }
            lCurrent = lCurrent.next;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T popAt(int pIndex) {
        Node<T> lCurrent = mHead;
        Node<T> lPrev = null;

        for (int index = 0; index < mSize; index++) {
            if (index == pIndex) {
                if (index == 0) {
                    return popFirst();
                } else if (index == mSize - 1) {
                    return popLast();
                }
                Node<T> lNext = lCurrent.next;
                lPrev.next = lNext;
                lNext.previous = lPrev;
                mSize--;
                return lCurrent.value;
            }

            lPrev = lCurrent;
            lCurrent = lCurrent.next;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T pElement) {
        if(pElement == null) {
            return false;
        }

        Node<T> lCurrent = mHead;

        while(lCurrent != null) {
            if (lCurrent.value.equals(pElement)) {
                return true;
            }
            lCurrent = lCurrent.next;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverse() {
        Node<T> lCurrent = mHead;
        Node<T> lPrev = null;
        Node<T> lNext = null;

        mTail = lCurrent;

        while(lCurrent != null) {
            lNext = lCurrent.next;
            lCurrent.next = lPrev;
            lCurrent.previous = lNext;
            lPrev = lCurrent;
            lCurrent = lNext;
        }

        mHead = lPrev;
    }

    private static class Node<T> {
        private T value = null;
        private Node<T> previous = null;
        private Node<T> next = null;

        public Node() { }

        public Node(T pValue) {
            value = pValue;
        }
    }
}
