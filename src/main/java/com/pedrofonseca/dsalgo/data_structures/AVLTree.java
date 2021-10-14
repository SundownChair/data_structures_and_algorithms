package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.ITree;

/**
 * <p>Binary Search Tree that is rebalanced (via node rotation) every operation to maintain a log(n) height (n being the
 * number of nodes). In essence, behaves like a BST but rebalancing prevents O(n) worst cases.</p>
 * <p></p>
 * <p>Average Access Time Complexity: O(log(n))<sup><i>Worst: O(log(n))</i></sup></p>
 * <p>Average Search Time Complexity: O(log(n))<sup><i>Worst: O(log(n))</i></sup></p>
 * <p>Average Insert Time Complexity: O(log(n))<sup><i>Worst: O(log(n))</i></sup></p>
 * <p>Average Delete Time Complexity: O(log(n))<sup><i>Worst: O(log(n))</i></sup></p>
 */
public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> implements ITree<T>  {

    @Override
    public boolean add(T pValue) {
        if (pValue == null) {
            return false;
        }

        Node<T> lNode = super.addValue(pValue);

        balanceTreeAfterChange(lNode);

        return true;
    }

    @Override
    public T remove(T pValue) {
        Node<T> nodeToRemove = get(pValue);
        Node<T> replacementNode = null;

        if(nodeToRemove == null) {
            return null;
        }

        replacementNode = super.removeNode(nodeToRemove);
        replacementNode = replacementNode == null ? nodeToRemove.parent : replacementNode;

        if (replacementNode != null) {
            balanceTreeAfterChange(replacementNode);
        }

        return nodeToRemove == null ? nodeToRemove.value : nodeToRemove.value;
    }

    private void balanceTreeAfterChange(Node<T> pNode) {
        while (pNode != null) {
            pNode.updateHeight();
            int pBalance = pNode.getBalance();
            int cBalance;
            Node<T> lChild;
            if (pBalance < -1) {
                lChild = pNode.right;
                cBalance = lChild.getBalance();
                if (cBalance < 0) {
                    leftRotate(pNode);

                    pNode.updateHeight();
                }
                else if (cBalance > 0) {
                    rightRotate(lChild);
                    leftRotate(pNode);

                    lChild.updateHeight();
                    pNode.updateHeight();
                }
            } else if (pBalance > 1) {
                lChild = pNode.left;
                cBalance = lChild.getBalance();
                if (cBalance > 0) {
                    rightRotate(pNode);

                    pNode.updateHeight();
                } else if (cBalance < 0) {
                    leftRotate(lChild);
                    rightRotate(pNode);

                    lChild.updateHeight();
                    pNode.updateHeight();
                }
            }

            pNode = pNode.parent;
        }
    }
}
