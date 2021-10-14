package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.IndexedPriorityQueue;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;

import java.util.*;

public class Dijkstra {

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

    /*
    given graph 'g' with n elements, and starting node 's':
	visited = [false, ..., false] // n length
	previous = [null, ..., null] // n length, used to rebuild best path to given node
	distance = [null, ..., null] // n length
	distance[s] = 0
	queue = empty indexed priority queue
	queue.add(s, 0)
	while(!queue.isEmpty())
		node, bestDistance = queue.poll()
		visited[node] = true
		if distance[node] < bestDistance
			continue
		for(childNode : g.getLinkedNodes(node))
			if visited[childNode]
				continue
			newDistance = distance[node] + g.getDistance(node, childNode)
			if distance[childNode] == null || newDistance < distance[childNode]
				distance[childNode] = newDistance
				previous[childNode] = node
				if !queue.contains(childNode)
					queue.add(childNode, newDistance)
				else
					queue.updateKey(childNode, newDistance) // updates value if better than existing
	return distance
     */
}
