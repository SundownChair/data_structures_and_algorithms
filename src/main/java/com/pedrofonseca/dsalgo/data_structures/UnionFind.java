package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IUnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Also known as Disjoint-Set. Keeps track of a set of objects split into one or more subsets. Union by Size and Path
 * Compression are both applied. Performs two main operations:
 * <ul>
 *     <li>find - checks what subset an element belongs by checking the root element. Elements in the same set share
 *     the same root and can be assumed connected.</li>
 *     <li>union - joins two subsets into a single subset.</li>
 * </ul></p>
 * <p></p>
 * <p>Find: O(α(n))</p>
 * <p>Union: O(α(n))</p>
 */
public class UnionFind<T> implements IUnionFind<T> {

    private Map<T, Integer> indexMapper = new HashMap<>();
    private List<Integer> parents = new ArrayList<>();
    private List<Integer> size = new ArrayList<>();

    @Override
    public void createSet(T pValue) {
        if(!indexMapper.containsKey(pValue)) {
            int newIndex = parents.size();
            indexMapper.put(pValue, newIndex);
            parents.add(newIndex, newIndex); // A new element is it's own parent
            size.add(newIndex, 1); // A new element has only itself
        }
    }

    @Override
    public Integer find(T pValue) {
        if(pValue == null || indexMapper.get(pValue) == null) {
            return null;
        }

        int curIndex = indexMapper.get(pValue);
        return find(curIndex);
    }

    private int find(int pIndex) {
        if(pIndex == parents.get(pIndex)) {
            return pIndex;
        }
        return parents.set(pIndex, find(parents.get(pIndex)));
    }

    @Override
    public void union(T pNode1, T pNode2) {
        Integer index1 = find(pNode1);
        Integer index2 = find(pNode2);
        if (index1 != null && index2 != null && !index1.equals(index2)){
            if(size.get(index1) > size.get(index2)) {
                parents.set(index2, index1);
                size.set(index1, size.get(index1) + size.get(index2));
            } else {
                parents.set(index1, index2);
                size.set(index2, size.get(index1) + size.get(index2));
            }
        }
    }

    @Override
    public boolean isConnected(T pNode1, T pNode2) {
        Integer set1 = find(pNode1);
        Integer set2 = find(pNode2);
        if(set1 == null || set2 == null)
            return false;
        return set1.equals(set2);
    }
}
