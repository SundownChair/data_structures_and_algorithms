package com.pedrofonseca.dsalgo.data_structures.interfaces;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;

import java.util.List;

public interface IGraph<T extends Comparable<? super T>> {

    /**
     * Add new vertex with given label. If not unique, no change is performed.
     *
     * @param pLabel Label of vertex to add.
     */
    void addVertex(T pLabel);

    /**
     * Remove vertex for given label. If not present, no change is performed.
     *
     * @param pLabel Label of vertex to remove.
     */
    void removeVertex(T pLabel);

    /**
     * Add unweighted edge between two given vertices. If any vertex does not exist, it will be created.
     * If graph is directed, edge will only be created in "from" -> "to" direction.
     *
     * @param pLabel1 Label of "from" vertex.
     * @param pLabel2 Label of "to" vertex.
     */
    void addEdge(T pLabel1, T pLabel2);

    /**
     * Add weighted edge between two given vertices. If any vertex does not exist, it will be created.
     * If graph is directed, edge will only be created in "from" -> "to" direction. If undirected,
     * value will be the same for both vertices.
     *
     * @param pLabel1 Label of "from" vertex.
     * @param pLabel2 Label of "to" vertex.
     * @param pEdgeValue Value of the edge to create.
     */
    void addEdge(T pLabel1, T pLabel2, Integer pEdgeValue);

    /**
     * Remove edge between given vertices. If not present, no change is performed.
     * If graph is directed, edge will only be removed in "from" -> "to" direction.
     *
     * @param pLabel1 Label of "from" vertex.
     * @param pLabel2 Label of "to" vertex.
     */
    void removeEdge(T pLabel1, T pLabel2);

    /**
     * Returns the current amount vertices in the graph.
     *
     * @return Number of vertices.
     */
    int countVertex();

    /**
     * Returns the current amount edges in the graph.
     *
     * @return Number of edges.
     */
    int countEdge();

    /**
     * Returns a list of all vertices connected to the given vertex.
     *
     * @param pLabel Label of source vertex.
     * @return List of connected vertices' labels.
     */
    List<T> getLinkedVertices(T pLabel);

    /**
     * Checks if given vertex currently exists in map.
     *
     * @param pLabel Label of vertex to validate.
     * @return true if vertex is present; false otherwise.
     */
    boolean contains(T pLabel);

    /**
     * Returns all vertices present in graph.
     * @return List of vertices in graph.
     */
    List<T> getVertices();

    /**
     * Returns directed weight of edge between two nodes.
     * @param from Start node label.
     * @param to End node label.
     * @return Weight os edge between from and to.
     */
    Integer getWeight(T from, T to);

    /**
     * Returns list of all currently existing edges in the graph.
     * @return Edge list.
     */
    List<Edge> getEdgeList();

    class Edge<J> {
        private J from;
        private J to;
        private Integer cost;

        public Edge(J pFrom, J pTo, Integer pCost) {
            from = pFrom;
            to = pTo;
            cost = pCost;
        }

        public J getFrom() {
            return from;
        }

        public J getTo() {
            return to;
        }

        public Integer getCost() {
            return cost;
        }
    }
}
