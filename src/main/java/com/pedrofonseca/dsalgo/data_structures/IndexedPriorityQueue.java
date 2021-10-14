package com.pedrofonseca.dsalgo.data_structures;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class IndexedPriorityQueue<T, J extends Comparable<? super J>> {

    // Starting value for all arrays
    private static final int MIN_SIZE = 128;

    private static int sortModifier;

    // Number of elements in the IPQ
    private int size;

    // Index counter and map to assign
    private HashMap<T, Integer> elementToIndex;

    // 1 based binary heap with elements
    private int[] pq;

    // qp[i] is the index og pq for given element ran through the element map
    private int[] qp;

    // Stored values for elements. pq[i] is the element, keys[i] is the value
    private J[] keys;

    //
    private T[] elements;


    public IndexedPriorityQueue(Class clazz1, Class clazz2) {
        this(clazz1, clazz2, ArrayHeap.HeapType.Min);
    }

    public IndexedPriorityQueue(Class clazz1, Class clazz2, ArrayHeap.HeapType pHeapType) {
        sortModifier = pHeapType.getModifier();
        size = 0;
        elementToIndex = new HashMap<>();
        pq = new int[MIN_SIZE];
        for(int i = 0; i < pq.length-1; i++) {
            pq[i] = -1;
        }
        qp = new int[MIN_SIZE];
        for(int i = 0; i < qp.length-1; i++) {
            qp[i] = -1;
        }
        keys = (J[]) Array.newInstance(clazz2, MIN_SIZE);
        elements = (T[]) Array.newInstance(clazz1, MIN_SIZE);
    }

    /**
     * Inserts given element with associated value into the queue.
     * @param pElement
     * @param pElementValue
     */
    public void insert(T pElement, J pElementValue) {
        if(pElement == null || pElementValue == null) {
            throw new NullPointerException();
        }

        if(elementToIndex.containsKey(pElement)) {
            // send to update function
            return;
        }

        size++;

        // Get inner index for new element
        int index;
        for(index = 0; index < elements.length-1; index++) {
            if(elements[index] == null) break;
        }

        pq[size] = index;
        qp[index] = size;
        keys[index] = pElementValue;
        elements[index] = pElement;

        elementToIndex.put(pElement, index);

        // Heapify up the element just inserted into the heap
        heapifyUp(size);
    }

    public void update(T pElement, J pElementValue) {
        validateUpdate(pElement, pElementValue);

        int indexToUpdate = elementToIndex.get(pElement);
        keys[indexToUpdate] = pElementValue;
        heapifyUp(qp[indexToUpdate]);
        heapifyDown(qp[indexToUpdate]);
    }

    public void updateMoreExtreme(T pElement, J pElementValue) {
        validateUpdate(pElement, pElementValue);

        int indexToUpdate = elementToIndex.get(pElement);
        if(pElementValue.compareTo(keys[indexToUpdate])*sortModifier > 0) {
            keys[indexToUpdate] = pElementValue;
            heapifyUp(qp[indexToUpdate]);
        }
    }

    public void updateLessExtreme(T pElement, J pElementValue) {
        validateUpdate(pElement, pElementValue);

        int indexToUpdate = elementToIndex.get(pElement);
        if(pElementValue.compareTo(keys[indexToUpdate])*sortModifier < 0) {
            keys[indexToUpdate] = pElementValue;
            heapifyDown(qp[indexToUpdate]);
        }
    }

    private void validateUpdate(T pElement, J pElementValue) {
        if(pElement == null || pElementValue == null) {
            throw new NullPointerException();
        }
        if(!contains(pElement)) {
            throw new NoSuchElementException("Element not in queue");
        }
    }

    public J peekValue() {
        if(size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return keys[pq[1]];
    }

    public T peek() {
        if(size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return elements[pq[1]];
    }

    public T poll() {
        if(size == 0) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return delete(elements[pq[1]]);
    }

    public T delete(T pElement) {
        Integer indexToRemove = elementToIndex.get(pElement);
        if(indexToRemove == null) {
            throw new NoSuchElementException("Element not in queue");
        }
        T deleted = elements[indexToRemove];

        // Swap last element and one to delete
        swap(qp[indexToRemove], size);

        // Clean support arrays
        keys[indexToRemove] = null;
        elements[indexToRemove] = null;
        qp[indexToRemove] = -1;
        pq[size] = -1;
        elementToIndex.remove(deleted);

        // Heapify swapped element
        heapifyUp(qp[indexToRemove]);
        heapifyDown(qp[indexToRemove]);

        size--;

        return deleted;
    }

    public boolean contains(T pElement) {
        return elementToIndex.containsKey(pElement);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapifyUp(int pPQIndex) {
        while( pPQIndex > 1 && isMoreExtreme(pPQIndex, getParentIndex(pPQIndex))) {
            swap(pPQIndex, getParentIndex(pPQIndex));
            pPQIndex = getParentIndex(pPQIndex);
        }
    }

    private void heapifyDown(int pPQIndex) {
        while(getLeftIndex(pPQIndex) <= size) {
            int lIndex = getLeftIndex(pPQIndex);
            int rIndex = getRightIndex(pPQIndex);
            int newIndex = -1;

            if(lIndex > 0 && lIndex <= size && isMoreExtreme(lIndex, pPQIndex)) {
                newIndex = lIndex;
            }

            if(rIndex > 0 && rIndex <= size && isMoreExtreme(rIndex, pPQIndex)) {
                if (newIndex == -1 || isMoreExtreme(rIndex, newIndex)) {
                    newIndex = rIndex;
                }
            }

            if(newIndex != -1) {
                swap(newIndex, pPQIndex);
                pPQIndex = newIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        // Update heap
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        // Update index
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean isMoreExtreme(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]])*sortModifier > 0;
    }

    // Heap is 1 based, adjust getter formulas
    private int getParentIndex(int pIndex) {
        return pIndex > 0 ? pIndex / 2 : -1;
    }

    private int getLeftIndex(int pIndex) {
        return 2 * pIndex;
    }

    private int getRightIndex(int pIndex) {
        return 2 * pIndex + 1;
    }
}
