package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import java.util.List;

public class HeapSortTest {

    @Test
    public void testAscendingSort() {
        Integer[] i = {5,100,6,22,-1,10};
        Integer[] iS = {-1,5,6,10,22,100};

        List<Integer> sort = HeapSort.sortAsc(i);
        for(int in = 0; in < iS.length; in++){
            assert(sort.get(in).equals(iS[in]));
        }
    }

    @Test
    public void testDescendingSort() {
        Integer[] i = {5,100,6,22,-1,10};
        Integer[] iS = {100,22,10,6,5,-1,};

        List<Integer> sort = HeapSort.sortDesc(i);
        for(int in = 0; in < iS.length; in++){
            assert(sort.get(in).equals(iS[in]));
        }
    }
}
