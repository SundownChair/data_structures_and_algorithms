package com.pedrofonseca.dsalgo.sort_algorithms;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Produces a list of topographically sorted elements of a directed acyclic graph.</p>
 * <p></p>
 * <b><p>Average Time Complexity: O(num of vertices + num of edges)</p></b>
 * <p>Worst Time Complexity: O(num of vertices + num of edges</p>
 * <p>Best Time Complexity: O(num of vertices + num of edges)</p>
 */
public class TopSort {

    /**
     * Returns a topographically sorted list os elements from the given graph.
     * @param pGraph Graph to sort.
     * @param <T>
     * @return Topographically sorted list.
     */
    public static <T extends Comparable<? super T>> List<T> sort(IGraph<T> pGraph) {
        if(pGraph == null) {
            return new ArrayList<>();
        }

        // ToDo: add a DAG check

        List<T> vertices = pGraph.getVertices();
        int numOfVertices = vertices.size();
        List<T> topSorted = new ArrayList<>(); // Not an array since static is generic, to avoid Array instance casting
        while(topSorted.size() < numOfVertices) { topSorted.add(null);}
        Set<T> visited = new HashSet<>();
        int topSortedIndex = numOfVertices - 1;

        for(T node : vertices) {
            if(!visited.contains(node)) {
                topSortedIndex = topSortDFS(pGraph, node, visited, topSortedIndex, topSorted);
            }
        }

        return topSorted;
    }

    private static <T extends Comparable<? super T>> int topSortDFS(IGraph<T> pGraph, T curNode, Set<T> visited,
                                                                    int pCurIndex, List<T> topSorted) {
        int curIndex = pCurIndex;
        if(visited.contains(curNode)) {
            return pCurIndex;
        }
        visited.add(curNode);

        List<T> neighbours = pGraph.getLinkedVertices(curNode);
        if(neighbours.isEmpty()) {
            topSorted.set(curIndex--, curNode);
            return curIndex;
        }
        for(T node : neighbours) {
            curIndex = topSortDFS(pGraph, node, visited, curIndex, topSorted);
        }
        topSorted.set(curIndex--, curNode);
        return curIndex;
    }
}
