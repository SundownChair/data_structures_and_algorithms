package com.pedrofonseca.dsalgo.data_structures.interfaces;

public interface IUnionFind<T> {

    void createSet(T pValue);

    /**
     * <p>Returns the inner index of the given value's set's root. If a value is returned, node exists in managed sets;
     * if not, node is not managed.</p>
     * <p>This also performs path compression by setting any traversed node's path to the set's root.</p>
     * @param pValue Value to find.
     * @return Inner index of the value's root.
     */
    Integer find(T pValue);

    /**
     * <p>Joins the corresponding sets of the two given nodes if not joined already (if so, no change is performed).
     * Joins are performed by set size.</p>
     * @param pNode1 First value to join.
     * @param pNode2 Second value to join.
     */
    void union(T pNode1, T pNode2);

    /**
     * <p>Checks if given nodes are connected by checking if they are in the same set.</p>
     * @param pNode1 First node.
     * @param pNode2 Second node.
     * @return true when nodes are in the same set, false otherwise.
     */
    boolean isConnected(T pNode1, T pNode2);
}
