package com.pedrofonseca.dsalgo.sort_algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSortTest {

    private HeapSort<Integer> mTestedClass;

    @Test
    public void testSort() {
        Integer[] i = {-1,5,6,10,22,100};
        mTestedClass = new HeapSort<>();
        mTestedClass.add(i[3]);
        mTestedClass.add(i[1]);
        mTestedClass.add(i[4]);
        mTestedClass.add(i[0]);
        mTestedClass.add(i[2]);
        mTestedClass.add(i[5]);

        List<Integer> sort = mTestedClass.sort();
        for(int in = 0; in < i.length; in++){
            assert(sort.get(in).equals(i[in]));
        }
    }
}
