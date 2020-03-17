package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {

    BinarySearchTree<Integer> mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new BinarySearchTree<>();
    }

    @Test
    public void testAdd() {
        assert(!mTestedClass.add(null));
        assert(mTestedClass.add(2));
        assert(mTestedClass.add(1));
        assert(mTestedClass.add(3));
        assert(mTestedClass.add(4));
        assert(mTestedClass.add(4));
    }

    @Test
    public void testContains()  {
        assert(!mTestedClass.contains(null));
        assert(!mTestedClass.contains(1));

        mTestedClass.add(1);
        assert(mTestedClass.contains(1));

        mTestedClass.add(2);
        mTestedClass.add(3);
        mTestedClass.add(0);
        assert(mTestedClass.contains(0));
        assert(mTestedClass.contains(1));
        assert(mTestedClass.contains(2));
        assert(mTestedClass.contains(3));
    }

    @Test
    public void testRemove() {
        assert(mTestedClass.remove(null) == null);
        assert(mTestedClass.remove(1) == null);

        mTestedClass.add(1);
        assert(mTestedClass.remove(10) == null);

        // Removing leaves
        mTestedClass.add(0);
        mTestedClass.add(2);
        assert(mTestedClass.remove(0) == 0);
        assert(mTestedClass.remove(2) == 2);
        assert(mTestedClass.remove(1) == 1);

        // Removing node with single child
        // Left case
        mTestedClass.add(2);
        mTestedClass.add(1);
        mTestedClass.add(0);
        assert(mTestedClass.remove(1) == 1);
        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(0,2));
        List<Integer> inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();

        // Right case
        mTestedClass.add(0);
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.remove(1) == 1);
        inOrderCheckList = new ArrayList<>(Arrays.asList(0,2));
        inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();

        // Root
        mTestedClass.add(0);
        mTestedClass.add(1);
        mTestedClass.add(2);
        assert(mTestedClass.remove(0) == 0);
        inOrderCheckList = new ArrayList<>(Arrays.asList(1,2));
        inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();

        // Removing node with 2 children (root)
        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(5);
        mTestedClass.add(3);
        mTestedClass.add(6);
        assert(mTestedClass.remove(1) == 1);
        inOrderCheckList = new ArrayList<>(Arrays.asList(0,3,5,6));
        inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();

        // Removing node with 2 children
        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(5);
        mTestedClass.add(4);
        mTestedClass.add(7);
        mTestedClass.add(6);
        mTestedClass.add(9);
        assert(mTestedClass.remove(5) == 5);
        inOrderCheckList = new ArrayList<>(Arrays.asList(0,1,4,6,7,9));
        inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();

        // Removing node with 2 children (replacement has children as well)
        mTestedClass.add(10);
        mTestedClass.add(20);
        mTestedClass.add(15);
        mTestedClass.add(25);
        mTestedClass.add(21);
        mTestedClass.add(24);
        assert(mTestedClass.remove(20) == 20);
        inOrderCheckList = new ArrayList<>(Arrays.asList(10,15,21,24,25));
        inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));
    }

    @Test
    public void testSize() {
        assert(mTestedClass.size() == 0);
        mTestedClass.add(1);
        assert(mTestedClass.size() == 1);
        mTestedClass.add(1);
        assert(mTestedClass.size() == 1);
        mTestedClass.add(2);
        mTestedClass.add(3);
        assert(mTestedClass.size() == 3);
        mTestedClass.remove(3);
        assert(mTestedClass.size() == 2);
        mTestedClass.remove(10);
        assert(mTestedClass.size() == 2);
        mTestedClass.clear();
        assert(mTestedClass.size() == 0);
    }

    @Test
    public void testDepthFirstTraversal() {
        try {
            List<Integer> lNullList = mTestedClass.getDepthFirstTraversal(null);
            assert(false);
        } catch (IllegalArgumentException e) {
            assert(true);
        }

        mTestedClass.add(4);
        mTestedClass.add(5);
        mTestedClass.add(2);
        mTestedClass.add(1);
        mTestedClass.add(3);

        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<Integer> inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        List<Integer> preOrderCheckList = new ArrayList<>(Arrays.asList(4,2,1,3,5));
        List<Integer> preOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.preOrder);
        assert(preOrderList.equals(preOrderCheckList));

        List<Integer> postOrderCheckList = new ArrayList<>(Arrays.asList(1,3,2,5,4));
        List<Integer> postOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.postOrder);
        assert(postOrderList.equals(postOrderCheckList));
    }

    @Test
    public void testBreadthFirstTraversal() {
        assert(mTestedClass.getBreadthFirstTraversal().isEmpty());

        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(3);
        mTestedClass.add(2);
        mTestedClass.add(4);
        List<Integer> bfsCheckList = new ArrayList<>(Arrays.asList(1,0,3,2,4));
        List<Integer> bfsList = mTestedClass.getBreadthFirstTraversal();
        assert(bfsList.equals(bfsCheckList));
    }

    @Test
    public void testNodeBalance() {

    }

    @Test
    public void testFlipTree() {
        mTestedClass.flipTree();

        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(2);
        mTestedClass.flipTree();
        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(2,1,0));
        List<Integer> inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));

        mTestedClass.clear();

        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(2);
        mTestedClass.add(3);
        mTestedClass.add(4);
        mTestedClass.add(5);
        mTestedClass.flipTree();
        inOrderCheckList = new ArrayList<>(Arrays.asList(5,4,3,2,1,0));
        inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));
    }

    @Test
    public void testRightRotate() {
        mTestedClass.rightRotateRoot();

        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(2);
        mTestedClass.rightRotateRoot();
        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(0,1,2));
        List<Integer> inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));
    }

    @Test
    public void testleftRotate() {
        mTestedClass.leftRotateRoot();

        mTestedClass.add(1);
        mTestedClass.add(0);
        mTestedClass.add(2);
        mTestedClass.leftRotateRoot();
        List<Integer> inOrderCheckList = new ArrayList<>(Arrays.asList(0,1,2));
        List<Integer> inOrderList = mTestedClass.getDepthFirstTraversal(BinarySearchTree.DfsOrder.inOrder);
        assert(inOrderList.equals(inOrderCheckList));
    }

}
