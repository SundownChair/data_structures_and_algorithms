package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.ITree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * <p>Tree structure where every node has a parent node (except root) and at most two child nodes - where the left
 * node's value is less extreme than the parent's and the right one's more. Not forcibly a complete tree. Can be
 * binary searched (hence the name).</p>
 * <p></p>
 * <p>Average Access Time Complexity: O(log(n))<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Search Time Complexity: O(log(n))<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Insert Time Complexity: O(log(n))<sup><i>Worst: O(n)</i></sup></p>
 * <p>Average Delete Time Complexity: O(log(n))<sup><i>Worst: O(n)</i></sup></p>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements ITree<T> {

    protected int mSize;
    protected Node<T> mRoot;

    public enum DfsOrder {
        inOrder, postOrder, preOrder
    }

    public BinarySearchTree() {
        mSize = 0;
        mRoot = null;
    }

    @Override
    public boolean add(T pValue) {
        if (pValue == null) {
            return false;
        }

        addValue(pValue);
        return true;
    }

    protected Node<T> addValue(T pValue) {

        Node<T> lNew = new Node<>(pValue);

        if (mRoot == null) {
            mRoot = lNew;
            mSize++;
            return lNew;
        }  else {
            Node<T> curr = mRoot;

            while (curr != null) {
                int compare = curr.compareTo(lNew);
                if (compare > 0) {
                    if (curr.left != null) {
                        curr = curr.left;
                    } else {
                        curr.left = lNew;
                        lNew.parent = curr;
                        mSize++;
                    }
                } else if (compare < 0) {
                    if (curr.right != null) {
                        curr = curr.right;
                    } else {
                        curr.right = lNew;
                        lNew.parent = curr;
                        mSize++;
                    }
                } else {
                    // Default condition - implementation does not replace same values
                    curr = null;
                }
            }
        }

        return lNew;
    }

    protected Node<T> get(T pValue) {
        Node<T> lCurr = mRoot;

        while (lCurr != null) {
            if(pValue.compareTo(lCurr.value) == 0) {
                return lCurr;
            } else if(pValue.compareTo(lCurr.value) > 0) {
                lCurr = lCurr.right;
            } else if(pValue.compareTo(lCurr.value) < 0) {
                lCurr = lCurr.left;
            }
        }

        return null;
    }

    public List<T> getDepthFirstTraversal(DfsOrder pOrder) {
        //T[] lTraversal = (T[]) new Object[mSize]; // alternate: (T[])Array.newInstance(mRoot.value.getClass(), mSize);
        List<T> lTraversal = new ArrayList<>();

        if (pOrder == null) {
            throw new IllegalArgumentException("Traversal type must be set");
        }
        else if (pOrder.equals(DfsOrder.inOrder)) {
            inOrder(mRoot, lTraversal);
        } else if (pOrder.equals(DfsOrder.postOrder)) {
            postOrder(mRoot, lTraversal);
        } else if (pOrder.equals(DfsOrder.preOrder)) {
            preOrder(mRoot, lTraversal);
        }

        return lTraversal;
    }

    public List<T> getBreadthFirstTraversal() {
        List<T> lTraversal = new ArrayList<>();
        Queue<Node<T>> lNodeQueue = new LinkedList<>();
        lNodeQueue.add(mRoot);

        while (lNodeQueue.peek() != null) {
            Node<T> curr = lNodeQueue.poll();
            lTraversal.add(curr.value);
            if (curr.left != null) {
                lNodeQueue.add(curr.left);
            }
            if (curr.right != null) {
                lNodeQueue.add(curr.right);
            }
        }

        return lTraversal;
    }

    private void inOrder(Node<T> pCurr, List<T> pReturn) {
        if (pCurr == null) {
            return;
        }

        inOrder(pCurr.left, pReturn);
        pReturn.add(pCurr.value);
        inOrder(pCurr.right, pReturn);
    }

    private void preOrder(Node<T> pCurr, List<T> pReturn) {
        if (pCurr == null) {
            return;
        }

        pReturn.add(pCurr.value);
        preOrder(pCurr.left, pReturn);
        preOrder(pCurr.right, pReturn);
    }

    private void postOrder(Node<T> pCurr, List<T> pReturn) {
        if (pCurr == null) {
            return;
        }

        postOrder(pCurr.left, pReturn);
        postOrder(pCurr.right, pReturn);
        pReturn.add(pCurr.value);
    }

    public void clear() {
        mRoot = null;
        mSize = 0;
    }

    @Override
    public T remove(T pValue) {
        Node<T> nodeToRemove = get(pValue);

        if (nodeToRemove == null) {
            return null;
        }

        removeNode(nodeToRemove);
        return nodeToRemove.value;
    }

    protected Node<T> removeNode(Node<T> pNodeToRemove) {
        Node<T> replacementNode;

        if (pNodeToRemove.left == null && pNodeToRemove.right == null) {
            replacementNode = null;
        }
        else if (pNodeToRemove.left != null && pNodeToRemove.right == null) {
            replacementNode = pNodeToRemove.left;
        }
        else if (pNodeToRemove.left == null && pNodeToRemove.right != null) {
            replacementNode = pNodeToRemove.right;
        } else {
            replacementNode = getReplacementNode(pNodeToRemove);
        }

        replaceNode(pNodeToRemove, replacementNode);

        mSize--;

        return replacementNode;
    }

    /**
     * Get replacement node for given node.
     * @param pNodeToReplace Tree node to be replaced. Must have 2 children.
     */
    protected Node<T> getReplacementNode(Node<T> pNodeToReplace) {
        // Find replacement node : leftmost node of right child (when no left node, short circuit right child)
        Node<T> replacement = pNodeToReplace.right;
        while(replacement.left != null) {
            replacement = replacement.left;
        }
        return replacement;
    }

    /**
     * Replaces given pNodeToReplace with pReplacement.
     * @param pNodeToReplace Node to be replaced.
     * @param pReplacement Node to replace pNodeToReplace.
     */
    protected void replaceNode(Node<T> pNodeToReplace, Node<T> pReplacement) {
        if(pReplacement != null) {
            // Remove link from replacementParent to replacement & update any old children
            // Only when not a short circuit of the tree (replacement parent is not nodeToRemove)
            if (pReplacement.parent != null && pReplacement.parent != pNodeToReplace) {
                // Update right child of replacement to left child of replacementParent
                // or update left child of replacement to right child of replacementParent
                if (pReplacement.parent.left != null && pReplacement.parent.left.equals(pReplacement)) {
                    pReplacement.parent.left = pReplacement.right;
                    if (pReplacement.right != null) {
                        pReplacement.right.parent = pReplacement.parent;
                    }
                } else if (pReplacement.parent.right != null && pReplacement.parent.right.equals(pReplacement)) {
                    pReplacement.parent.right = pReplacement.left;
                    if (pReplacement.left != null) {
                        pReplacement.left.parent = pReplacement.parent;
                    }
                }
            }

            // Update replacement children
            if (pNodeToReplace.left != null && !pNodeToReplace.left.equals(pReplacement)) {
                pReplacement.left = pNodeToReplace.left;
                pNodeToReplace.left.parent = pReplacement;
            }
            if (pNodeToReplace.right != null && !pNodeToReplace.right.equals(pReplacement)) {
                pReplacement.right = pNodeToReplace.right;
                pNodeToReplace.right.parent = pReplacement;
            }
        }

        // Update tree structure
        if(pNodeToReplace.parent == null) {
            mRoot = pReplacement;
            if (pReplacement != null) {
                pReplacement.parent = null;
            }
        } else if (pNodeToReplace.parent.left != null && pNodeToReplace.parent.left.equals(pNodeToReplace)) {
            pNodeToReplace.parent.left = pReplacement;
            if(pReplacement != null) {
                pReplacement.parent = pNodeToReplace.parent;
            }
        } else if (pNodeToReplace.parent.right != null && pNodeToReplace.parent.right.equals(pNodeToReplace)) {
            pNodeToReplace.parent.right = pReplacement;
            if(pReplacement != null) {
                pReplacement.parent = pNodeToReplace.parent;
            }
        }
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean contains(T pValue) {
        if (pValue == null) {
            return false;
        } else {
            return get(pValue) != null;
        }
    }

    public void balance() {
        List<T> lOrderedList = getDepthFirstTraversal(DfsOrder.inOrder);
        clear();

        if (!lOrderedList.isEmpty()) {
            recBalance(lOrderedList);
        }
    }

    /**
     * <p><b>WILL BREAK BST CONSTRAINTS.</b></p>
     * Recursively flips tree and subtrees to mirror original distribution.
     */
    @Deprecated
    public void flipTree() {
        recFlipTree(mRoot);
    }

    private void recFlipTree(Node<T> pNode) {
        if(pNode == null) {
            return;
        }

        Node<T> tmp = pNode.left;
        pNode.left = pNode.right;
        pNode.right = tmp;

        recFlipTree(pNode.left);
        recFlipTree(pNode.right);
    }

    private void recBalance(List<T> p) {
        if(p.isEmpty()) {
            return;
        }

        int mid = p.size() / 2;
        add(p.get(mid));

        if(p.size() > 1) {
            recBalance(p.subList(0, mid));
            recBalance(p.subList(mid+1, p.size()));
        }

    }

    /**
     * <p><b>WILL BREAK BST CONSTRAINTS.</b></p>
     * Right rotates root.
     */
    @Deprecated
    public void rightRotateRoot() {
        rightRotate(mRoot);
    }

    /**
     * <p><b>WILL BREAK BST CONSTRAINTS.</b></p>
     * Left rotates root.
     */
    @Deprecated
    public void leftRotateRoot() {
        leftRotate(mRoot);
    }

    /**
     * Makes given node (A) the left child of it's current right child (B). B's left child becomes A's new right child.
     * @param pRoot
     * @return
     */
    protected Node<T> leftRotate(Node<T> pRoot) {
        if (pRoot == null || pRoot.right == null) {
            return pRoot;
        }

        Node<T> lRootParent = pRoot.parent;
        Node<T> lNewRoot = pRoot.right;

        // Rotate children
        pRoot.right = lNewRoot.left;
        if(pRoot.right != null) {
            pRoot.right.parent = pRoot;
        }

        // Rotate nodes
        lNewRoot.left = pRoot;
        lNewRoot.parent = pRoot.parent;
        pRoot.parent = lNewRoot;

        // Update parent
        if(lNewRoot.parent != null) {
            if (lNewRoot.parent.left.equals(pRoot)) {
                lNewRoot.parent.left = lNewRoot;
            } else if (lNewRoot.parent.right.equals(pRoot)) {
                lNewRoot.parent.right = lNewRoot;
            }
        } else {
            mRoot = lNewRoot;
        }

        return lNewRoot;
    }

    /**
     * Makes given node (A) the right child of it's current left child (B). B's right child becomes A's new left child.
     * @param pRoot
     * @return
     */
    protected Node<T> rightRotate(Node<T> pRoot) {
        if (pRoot == null || pRoot.left == null) {
            return pRoot;
        }

        Node<T> lRootParent = pRoot.parent;
        Node<T> lNewRoot = pRoot.left;

        // Rotate children
        pRoot.left = lNewRoot.right;
        if(pRoot.left != null) {
            pRoot.left.parent = pRoot;
        }

        // Rotate nodes
        lNewRoot.right = pRoot;
        pRoot.parent = lNewRoot;
        lNewRoot.parent = lRootParent;

        // Update parent node
        if(lRootParent != null) {
            if (lRootParent.left.equals(pRoot)) {
                lRootParent.left = lNewRoot;
            } else if (lRootParent.right.equals(pRoot)) {
                lRootParent.right = lNewRoot;
            }
        } else {
            mRoot = lNewRoot;
        }

        return lNewRoot;
    }

    /**
     * Inner Node class for BST. Right child > node > left child.
     * @param <L>
     */
    public class Node<L extends Comparable<? super L>> implements Comparable<Node<L>>{
        L value;
        int height;
        Node<L> left;
        Node<L> right;
        Node<L> parent;

        Node(L pValue) {
            value = pValue;
            left = null;
            right = null;
            parent = null;
            height = 0;
        }

        Node() {
            this(null);
        }

        public void updateHeight() {
            height = Math.max((left != null ? left.height : -1),
                    (right != null ? right.height : -1)) + 1;
        }

        public int getBalance() {
            return (left != null ? left.height : 0) -
                    (right != null ? right.height : 0);
        }

        @Override
        public int compareTo(Node<L> o) {
            return value.compareTo(o.value);
        }
    }
}
