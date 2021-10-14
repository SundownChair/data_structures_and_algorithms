package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;

import java.util.*;

public class DFS {

    /**
     * Traverses graph depth first. Just a proof of concept, this method doesn't do much.
     *
     * @param pGraph Graph to traverse.
     * @param pStart Vertex to start traversal at.
     */
    public static <T extends Comparable<? super T>> void traverse(IGraph<T> pGraph, T pStart) {
        if(pGraph != null && pGraph.contains(pStart)) {
            Set<T> visited = new HashSet<>();
            traverseDFS(pGraph, pStart, visited);
        }
    }

    private static <T extends Comparable<? super T>> void traverseDFS(IGraph<T> pGraph, T pVertex, Set<T> pVisited) {
        if (pVisited.contains(pVertex)) {
            return;
        }
        pVisited.add(pVertex);

        for(T vertex : pGraph.getLinkedVertices(pVertex)) {
            traverseDFS(pGraph, vertex, pVisited);
        }
    }

    /**
     * Returns path (depth first) from start vertex to end vertex in given graph. Returns empty list if no path is found.
     *
     * @param pGraph Graph to traverse.
     * @param pStart Vertex to start traversal at.
     * @param pEnd Vertex to end traversal at.
     * @return
     */
    public static <T extends Comparable<? super T>> List<T> getPath(IGraph<T> pGraph, T pStart, T pEnd) {
        List<T> path = new ArrayList<>();
        if(pGraph != null && pGraph.contains(pStart) && pGraph.contains(pEnd)) {
            Set<T> visited = new HashSet<>();
            path = getPath(pGraph, pStart, pEnd, visited);
            Collections.reverse(path);
        }
        return path;
    }

    private static <T extends Comparable<? super T>> List<T> getPath(IGraph<T> pGraph, T pVertex, T pEnd, Set<T> pVisited) {
        List<T> path = new ArrayList<>();
        if (pVisited.contains(pVertex)) {
            return path;
        }
        pVisited.add(pVertex);

        if(pVertex.compareTo(pEnd) == 0) {
            path.add(pVertex);
            return path;
        }

        for(T vertex : pGraph.getLinkedVertices(pVertex)) {
            path = getPath(pGraph, vertex, pEnd, pVisited);
            if(!path.isEmpty()) {
                path.add(pVertex);
                return path;
            }
        }

        return path;
    }

    /**
     * Returns List of sets containing connected components.
     *
     * @param pGraph Graph to analyze.
     * @return List of Sets with connected components.
     */
    public static <T extends Comparable<? super T>> List<Set<T>> getConnectedComponents(IGraph<T> pGraph) {
        List<Set<T>> components = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        if(pGraph != null) {
            for(T entry : pGraph.getVertices()) {
                if(!visited.contains(entry)) {
                    Set<T> component = new HashSet<>();
                    getConnectedComponents(pGraph, entry, visited, component);
                    components.add(component);
                }
            }
        }
        return components;
    }

    private static <T extends Comparable<? super T>> void getConnectedComponents(IGraph<T> pGraph, T pVertex, Set<T> pVisited, Set<T> pComponents) {
        if(pVisited.contains(pVertex)) {
            return;
        }

        pVisited.add(pVertex);
        pComponents.add(pVertex);

        for(T vertex : pGraph.getLinkedVertices(pVertex)) {
            getConnectedComponents(pGraph, vertex, pVisited, pComponents);
        }
    }

}
