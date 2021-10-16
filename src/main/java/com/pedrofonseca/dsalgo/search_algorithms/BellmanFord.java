package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import com.pedrofonseca.dsalgo.search_algorithms.helpers.GenericTemplates;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Implements Bellman-Ford Algorithm to find single source shortest paths in the graph relative to a given start
 * node.</p>
 * <p></p>
 * <p>Average Time Complexity: O(num of edges * num of vertices)</p>
 */
public class BellmanFord {

    /**
     * Traverses graph for the optimal path between given start node and all other connected nodes.
     * Negative edge weights supported, but no path is returned (considered optimally unreachable without stop
     * condition).
     * @param pGraph Graph to traverse.
     * @param pStart Start node.
     * @param <T> Map of connected vertices with total cost from start and immediate previous node.
     * @return
     */
    public static <T extends Comparable<? super T>> Map<T, DOutput<T>> run(IGraph<T> pGraph, T pStart) {
        Map<T, DOutput<T>> outputMap = new HashMap<>();

        if(pGraph == null || pStart == null) {
            throw new NullPointerException();
        } else if (!pGraph.contains(pStart)) {
            throw new NoSuchElementException("Start element not in graph");
        } else if (pGraph.getLinkedVertices(pStart).isEmpty()) {
            return outputMap; // No elements connected to start
        }

        List<IGraph.Edge> edgeList = pGraph.getEdgeList();
        int numOfVertex = pGraph.countVertex();

        // Map nodes to index map
        int nodeIndex = 0;
        Map<T, Integer> nodeToIndex = new HashMap<>();
        T[] indexToNode = (T[]) Array.newInstance(pStart.getClass(), numOfVertex);
        for(T node : pGraph.getVertices()) {
            outputMap.put(node, new DOutput<>());
            nodeToIndex.put(node, nodeIndex);
            indexToNode[nodeIndex] = node;
            nodeIndex++;
        }

        // Init distance array
        Integer[] dist = new Integer[numOfVertex];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[nodeToIndex.get(pStart)] = 0;
        outputMap.get(pStart).setDistanceFromStart(0);

        // First cycle relaxes all edges regarding start index up to the optimal solution (negative cycles excluded)
        GenericTemplates firstCycleValue = (a, b) -> {
            return sum(a, b);
        };
        cycle(numOfVertex, edgeList, nodeToIndex, dist, firstCycleValue, outputMap);

        // Second cycle detects and marks negative cycles
        // Any path that can be improved past the optimal solution is in a negative cycle
        GenericTemplates secondCycleValue = (a, b) -> {
            return Integer.MIN_VALUE;
        };
        cycle(numOfVertex, edgeList, nodeToIndex, dist, secondCycleValue, outputMap);

        // Cleanup
        if(!outputMap.isEmpty()) {
            outputMap = outputMap.entrySet().stream()
                    .filter(x -> x.getValue().getDistanceFromStart() != Integer.MIN_VALUE)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        return outputMap;
    }

    // Control function to prevent integer overflow
    private static Integer sum(Integer a, Integer b) {
        if(a == Integer.MAX_VALUE || b == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if(a == Integer.MIN_VALUE || b == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return a+b;
    }

    // This is just to prevent repeating the same code. Not needed, but lambdas make it look nice
    private static <T extends Comparable<? super T>> void cycle(int numOfVertex,
                                                                List<IGraph.Edge> edgeList,
                                                                Map<T, Integer> nodeToIndex,
                                                                Integer[] dist,
                                                                GenericTemplates valueFunction,
                                                                Map<T, DOutput<T>> outputMap) {
        for(int i = 0; i < numOfVertex-1; i++) {
            for(IGraph.Edge edge : edgeList) {
                int from = nodeToIndex.get(edge.getFrom());
                int to = nodeToIndex.get(edge.getTo());
                Integer fromCost = dist[from];
                Integer toCost = dist[to];

                if(sum(fromCost, edge.getCost()) < toCost) {
                    dist[to] = valueFunction.runInteger(fromCost, edge.getCost());
                    outputMap.get(edge.getTo()).setPrevious((T) edge.getFrom());
                    outputMap.get(edge.getTo()).setDistanceFromStart(dist[to]);
                }
            }
        }
    }

    public static class DOutput<J> {
        private J previous;
        private Integer distanceFromStart;

        public J getPrevious() {
            return previous;
        }

        void setPrevious(J pPrevious) {
            previous = pPrevious;
        }

        public Integer getDistanceFromStart() {
            return distanceFromStart;
        }

        void setDistanceFromStart(Integer pDistanceFromStart) {
            distanceFromStart = pDistanceFromStart;
        }
    }
}
