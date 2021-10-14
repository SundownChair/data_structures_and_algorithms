package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import com.pedrofonseca.dsalgo.sort_algorithms.TopSort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Applies topological sort to a Directed Acyclic Graph in order to determine the shortest/longest path between two
 * nodes.</p>
 * <p></p>
 * <p>Average Time Complexity: O(num of vertices + num of edges)</p>
 */
public class DAGExtremePathFinder {

    /**
     * <p>Returns map with minimum distance from start node to node in question. Nodes with no link from start have
     * null distance</p>
     * @param pGraph Graph to search.
     * @param pStartNode Label of the node to start search at.
     * @return Map with max distance from start node to given node.
     */
    public static <T extends Comparable<? super T>> Map<T, Integer> shortestPath(IGraph<T> pGraph, T pStartNode) {
        return mostExtremePath(pGraph, pStartNode, 1);
    }

    /**
     * <p>Returns map with max distance from start node to node in question. Nodes with no link from start have
     * null distance</p>
     * @param pGraph Graph to search.
     * @param pStartNode Label of the node to start search at.
     * @return Map with max distance from start node to given node.
     */
    public static <T extends Comparable<? super T>> Map<T, Integer> longestPath(IGraph<T> pGraph, T pStartNode) {
        return mostExtremePath(pGraph, pStartNode, -1);
    }

    private static <T extends Comparable<? super T>> Map<T, Integer> mostExtremePath(IGraph<T> pGraph,
                                                                                  T pStartNode,
                                                                                  int pSortModifier) {
        Map<T, Integer> distances = new HashMap<>();
        if(pGraph == null || !pGraph.contains(pStartNode)) {
            return distances;
        }

        // ToDo: check if DAG

        List<T> topSorted = TopSort.sort(pGraph);
        topSorted.forEach(x -> distances.put(x, null)); // Quick & dirty map init for graph
        distances.put(pStartNode, 0); // Init start as zero - can change to get actual edge weight if self connected

        boolean isStartNodeReached = false;
        for(T node : topSorted) {
            if(!isStartNodeReached && node.equals(pStartNode)) {
                isStartNodeReached = true;
            }

            if(!isStartNodeReached) {
                // No need to calc distances before start is reached, since DAG will only link forward
                continue;
            }

            List<T> neighbours = pGraph.getLinkedVertices(node);
            for(T neighbour : neighbours) {
                // Calc total distance to node
                Integer totalDist = distances.get(node) + pGraph.getWeight(node, neighbour);

                if(distances.get(neighbour) == null) {
                    // First time node is reached, set weight as is
                    distances.put(neighbour, totalDist);
                } else if(distances.get(neighbour).compareTo(totalDist) * pSortModifier > 0) {
                    distances.put(neighbour, totalDist);
                }
            }
        }

        return distances;
    }
}
