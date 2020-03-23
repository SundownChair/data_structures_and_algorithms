package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.ITree;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> implements ITree<T>  {

    /**
     * {@inheritDoc}
     */
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
