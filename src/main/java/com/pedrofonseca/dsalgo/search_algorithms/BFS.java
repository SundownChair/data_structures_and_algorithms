package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import com.pedrofonseca.dsalgo.search_algorithms.helpers.Validators;

import java.util.*;

public class BFS {

    /**
     * Traverses graph breadth first. Just a proof of concept, this method doesn't do much.
     *
     * @param pGraph Graph to traverse.
     * @param pStart Vertex to start traversal at.
     */
    public static <T extends Comparable<? super T>> void traverse(IGraph<T> pGraph, T pStart) {
        if(pGraph != null && pGraph.contains(pStart)) {
            Set<T> visited = new HashSet<>();
            visited.add(pStart);
            Queue<T> queue = new LinkedList<>();
            queue.add(pStart);

            while(!queue.isEmpty()) {
                T currentVertex = (T) queue.poll();

                List<T> neighbours = pGraph.getLinkedVertices(currentVertex);
                for(T nextVertex : neighbours) {
                    if(!visited.contains(nextVertex)) {
                        visited.add(nextVertex);
                        queue.add(nextVertex);
                    }
                }
            }
        }
    }

    /**
     * Returns path (breadth first) from start vertex to end vertex in given graph. Returns empty list if no path is found.
     *
     * @param pGraph Graph to traverse.
     * @param pStart Vertex to start traversal at.
     * @param pEnd Vertex to end traversal at.
     * @return
     */
    public static <T extends Comparable<? super T>> List<T> getPath(IGraph<T> pGraph, T pStart, T pEnd) {
        List<T> path = new ArrayList<>();
        if(pGraph != null && pGraph.contains(pStart)) {
            Set<T> visited = new HashSet<>();
            visited.add(pStart);
            Queue<T> queue = new LinkedList<>();
            queue.add(pStart);
            HashMap<T, T> parentMap = new HashMap<>();
            parentMap.put(pStart, null);

            // Find target
            T target = null;
            while(!queue.isEmpty()) {
                T currentVertex = (T) queue.poll();

                // Check if target is found
                if(currentVertex.equals(pEnd)) {
                    target = currentVertex;
                    queue.clear();
                    break;
                }

                List<T> neighbours = pGraph.getLinkedVertices(currentVertex);
                for(T nextVertex : neighbours) {
                    if(!visited.contains(nextVertex)) {
                        visited.add(nextVertex);
                        queue.add(nextVertex);
                        parentMap.put(nextVertex, currentVertex);
                    }
                }
            }

            // If target is found, rebuild path from start to target
            if(target != null) {
                path.add(target);
                target = parentMap.get(target);
                while(target != null) {
                    path.add(target);
                    target = parentMap.get(target);
                }
                Collections.reverse(path);
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
                    components.add(getConnectedComponents(pGraph, entry, visited));
                }
            }
        }

        return components;
    }

    private static <T extends Comparable<? super T>> Set<T> getConnectedComponents(IGraph<T> pGraph, T pVertex, Set<T> pVisited) {
        Set<T> components = new HashSet<>();
        Queue<T> queue = new LinkedList<T>();
        queue.add(pVertex);

        while(!queue.isEmpty()) {
            T currentVertex = queue.poll();
            pVisited.add(currentVertex);
            components.add(currentVertex);

            for(T entry : pGraph.getLinkedVertices(currentVertex)) {
                if(!pVisited.contains(entry)) {
                    queue.add(entry);
                }
            }
        }
        return components;
    }

    /**
     * Takes an array-represented maze and returns ordered list from starting point to desired end point
     * (if any exists). Makes use of BFS characteristics to determine the shortest path.
     * @param pMazeArray Array representation of a 2d maze.
     * @param pStart Label of starting point.
     * @param pEnd Label of ending point.
     * @param validator Validator function that determines invalid nodes (-1), valid nodes (1) and end nodes (0).
     * @return Ordered list of path from start node to end node. Empty list if no path is found.
     */
    public static <T extends Comparable<? super T>> List<T> mazeSolver(T[][] pMazeArray, T pStart, T pEnd, Validators<T> validator) {
        int[] hMoves = {-1, 1, 0, 0};
        int[] vMoves = {0, 0, 1, -1};

        // Maze to graph
        IGraph<T> mazeGraph = new AdjacencyList<>();
        for(int i = 0; i < pMazeArray.length; i++) {
            for(int m = 0; m <pMazeArray[i].length; m++) {
                T currentNode = pMazeArray[i][m];

                // Validate current node
                if(validator.validate(currentNode) < 0){
                    continue;
                }

                // Add current node
                mazeGraph.addVertex(currentNode);

                // Add edges (and new nodes, if any)
                for(int mov = 0; mov < 4; mov++) {
                    int iIndex = i + hMoves[mov];
                    int mIndex = m + vMoves[mov];
                    if(iIndex >= pMazeArray.length || mIndex >= pMazeArray[i].length
                        || iIndex < 0 || mIndex < 0) {
                        continue;
                    }
                    if(validator.validate(pMazeArray[iIndex][mIndex]) >= 0) {
                        mazeGraph.addEdge(currentNode, pMazeArray[iIndex][mIndex]);
                    }
                }
            }
        }

        // Solve S -> E
        return getPath(mazeGraph, pStart, pEnd);
    }
}
