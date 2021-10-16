package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;

import java.util.*;

/**
 * <p>Graph represented as a map of nodes that list all edges of itself. Each node tracks all its outgoing edges (weight
 * included).</p>
 * <p>Pros: space efficient for sparse graphs (only keeps used edges), making iteration more efficient</p>
 * <p>Cons: less efficient for dense graphs, accessing a specific edge is O(num of edges in node)</p>
 * <p>Space complexity: O(num of vertex + num of edges) (only contains existing vertices)</p>
 * <p></p>
 * <p>Average Vertex Access Time Complexity: O(1)</p>
 * <p>Average Vertex Search Time Complexity: O(1)</p>
 * <p>Average Vertex Insert Time Complexity: O(1)</p>
 * <p>Average Vertex Delete Time Complexity: O(num of vertices)</p>
 * <p>Average Edge Access Time Complexity: O(1)</p>
 * <p>Average Edge Search Time Complexity: O(1)</p>
 * <p>Average Edge Insert Time Complexity: O(1)</p>
 * <p>Average Edge Delete Time Complexity: O(1)</p>
 */
public class AdjacencyList<T extends Comparable<? super T>> implements IGraph<T> {

    private final boolean IS_DIRECTED;
    private final Integer DEFAULT_WEIGHT = 1;

    private Map<T, Map<T, Integer>> adjList;

    private int numEdges;

    public AdjacencyList() {
        this(false);
    }

    public AdjacencyList(Boolean pIsDirected) {
        adjList = new HashMap<>();

        IS_DIRECTED = pIsDirected;
    }

    @Override
    public void addVertex(T pLabel) {
        if(pLabel != null && !adjList.containsKey(pLabel)) {
            adjList.put(pLabel, new HashMap<>());
        }
    }

    @Override
    public void removeVertex(T pLabel) {
        if(pLabel != null && adjList.containsKey(pLabel)) {
            adjList.values().forEach(x -> x.remove(pLabel));
            adjList.remove(pLabel);
        }
    }

    @Override
    public void addEdge(T pLabel1, T pLabel2) {
        addEdge(pLabel1, pLabel2, DEFAULT_WEIGHT);
    }

    @Override
    public void addEdge(T pLabel1, T pLabel2, Integer pEdgeValue) {
        if(pLabel1 == null || pLabel2 == null) return;
        addVertex(pLabel1);
        addVertex(pLabel2);

        add(pLabel1, pLabel2, pEdgeValue);
        if(!IS_DIRECTED) add(pLabel2, pLabel1, pEdgeValue);
    }

    @Override
    public void removeEdge(T pLabel1, T pLabel2) {
        if(pLabel1 == null || pLabel2 == null) return;

        if(!adjList.containsKey(pLabel1) || !adjList.containsKey(pLabel2)) return;

        remove(pLabel1, pLabel2);
        if(!IS_DIRECTED) remove(pLabel2, pLabel1);
    }

    private void add(T pFrom, T pTo, Integer pEdgeValue) {
        if(adjList.get(pFrom).get(pTo) == null) {
            adjList.get(pFrom).put(pTo, pEdgeValue);
            numEdges++;
        }
    }

    private void remove(T pFrom, T pTo) {
        if(adjList.get(pFrom).get(pTo) != null) {
            adjList.get(pFrom).remove(pTo);
            numEdges--;
        }
    }

    @Override
    public int countVertex() {
        return adjList.size();
    }

    @Override
    public int countEdge() {
        return numEdges;
    }

    @Override
    public List<T> getLinkedVertices(T pLabel) {
        List<T> labelList = new ArrayList<>();
        if (pLabel == null) return labelList;

        if(!adjList.containsKey(pLabel)) return labelList;

        for (Map.Entry e : adjList.get(pLabel).entrySet()) {
            labelList.add((T) e.getKey());
        }

        return labelList;
    }

    @Override
    public boolean contains(T pLabel) {
        return adjList.containsKey(pLabel);
    }

    @Override
    public List<T> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    @Override
    public Integer getWeight(T from, T to) {
        if(from != null && to != null && adjList.containsKey(from) && adjList.get(from).containsKey(to)) {
            return adjList.get(from).get(to);
        }
        return null;
    }

    public List<Edge> getEdgeList() {
        List<Edge> edgeList = new ArrayList<>();
        if(countEdge() == 0) {
            return edgeList;
        }

        Set<T> visited = new HashSet<>();
        for(T vector : adjList.keySet()) {
            // Iterate to account for graphs not fully linked
            if(!visited.contains(vector)) {
                dfsFillEdgeList(vector, edgeList, visited);
            }
        }
        return edgeList;
    }

    private void dfsFillEdgeList(T pCurrentVector, List<Edge> pEdgeList, Set<T> pVisited) {
        if(pVisited.contains(pCurrentVector)) {
            return;
        }
        pVisited.add(pCurrentVector);

        for(T node : getLinkedVertices(pCurrentVector)) {
            pEdgeList.add(new Edge<T>(pCurrentVector, node, adjList.get(pCurrentVector).get(node)));
            dfsFillEdgeList(node, pEdgeList, pVisited);
        }
    }
}
