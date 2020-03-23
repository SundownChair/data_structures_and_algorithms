package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLTreeTest {

    private AVLTree<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new AVLTree<>();
    }

    @Test
    public void testAdd() {
        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(5);
        mTestedClass.add(3);
        mTestedClass.add(10);
        mTestedClass.add(15);

        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(5,1,10,0,3,15));
        List<Integer> inOrderList = mTestedClass.getBreadthFirstTraversal();
        assert(inOrderList.equals(inOrderCheckList));
    }

    @Test
    public void testRemove() {
        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(5);
        mTestedClass.add(3);
        mTestedClass.add(10);
        mTestedClass.add(15);

        // Case 1: leaf removal
        mTestedClass.remove(15);
        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(5,1,10,0,3));
        List<Integer> inOrderList = mTestedClass.getBreadthFirstTraversal();
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.add(15);

        // Case 2: remove node with only one parent (short circuit)
        mTestedClass.remove(10);
        inOrderCheckList = new ArrayList<>(Arrays.asList(5,1,15,0,3));
        inOrderList = mTestedClass.getBreadthFirstTraversal();
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();
        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(5);
        mTestedClass.add(3);
        mTestedClass.add(10);
        mTestedClass.add(15);
        mTestedClass.add(2);

        // Case 3: remove node with 2 children (Left/Right rotation case)
        mTestedClass.remove(5);
        inOrderCheckList = new ArrayList<>(Arrays.asList(3, 1, 10, 0, 2, 15));
        inOrderList = mTestedClass.getBreadthFirstTraversal();
        assert(inOrderList.equals(inOrderCheckList));
    }

}
