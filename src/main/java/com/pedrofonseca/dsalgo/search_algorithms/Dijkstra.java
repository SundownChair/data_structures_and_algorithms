package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.IndexedPriorityQueue;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;

import java.util.*;

/**
 * <p>Implements Dijkstra's Algorithm to find single source shortest paths in the graph relative to a given start node.
 * Implemented via Index Priority Queue.</p>
 * <p></p>
 * <p>Average Time Complexity: O(num of edges * log(num of vertices))</p>
 */
public class Dijkstra {

    /**
     * Traverses graph for the optimal path between given start node and all other connected nodes.
     * Negative edge weights unsupported due to possible infinite negative cycles.
     * @param pGraph Graph to traverse.
     * @param pStart Start node.
     * @param <T> Map of connected vertices with total cost from start and immediate previous node.
     * @return
     */
    public static<T extends Comparable<? super T>> Map<T, DOutput> run(IGraph<T> pGraph, T pStart) {
        if(pGraph == null || pStart == null) {
            throw new NullPointerException();
        } else if (!pGraph.contains(pStart)) {
            throw new NoSuchElementException("Start element not in graph");
        }

        Set<T> visited = new HashSet<>();
        Map<T, T> previous = new HashMap<>();
        Map<T, Integer> distance = new HashMap<>();
        IndexedPriorityQueue<T, Integer> queue = new IndexedPriorityQueue<>(pStart.getClass(), Integer.class);

        distance.put(pStart, 0);
        queue.insert(pStart, 0);
        previous.put(pStart, null);

        while(!queue.isEmpty()) {
            Integer currentDistance = queue.peekValue();
            if(currentDistance < 0) {
                throw new IllegalArgumentException("Traversal does not support negative edge weights.");
            }
            T currentNode = queue.poll();
            visited.add(currentNode);

            if(distance.get(currentNode).compareTo(currentDistance) < 0) {
                continue;
            }

            for(T childNode : pGraph.getLinkedVertices(currentNode)) {
                if(visited.contains(childNode)) {
                    continue;
                }

                Integer newDistance = distance.get(currentNode) + pGraph.getWeight(currentNode, childNode);
                if(!distance.containsKey(childNode) || newDistance < distance.get(childNode)) {
                    distance.put(childNode, newDistance);
                    previous.put(childNode, currentNode);
                    if(!queue.contains(childNode)) {
                        queue.insert(childNode, newDistance);
                    } else {
                        queue.update(childNode, newDistance);
                    }
                }
            }
        }

        Map<T, DOutput> outputList = new HashMap<>();
        for(T element : previous.keySet()) {
            DOutput<T> output = new DOutput<>();
            output.setPrevious(previous.get(element));
            output.setDistanceFromStart(distance.get(element));
            outputList.put(element, output);
        }

        return outputList;
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
