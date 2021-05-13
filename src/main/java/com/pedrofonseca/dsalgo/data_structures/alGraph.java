package com.pedrofonseca.dsalgo.data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple graph class based in an array of hashmaps representing vertexes and edges, respectively.
 */
public class alGraph {

    private enum GraphDirection {
        DIRECTED,
        UNDIRECTED;
    }

    private enum GraphWeight {
        WEIGHTED,
        UNWEIGHTED
    }

    private final static int DEFAULT_WEIGHT = 1;

    private GraphDirection mGraphDirection;

    private GraphWeight mGraphWeight;

    private List<Map<Integer, Integer>> mGraph;

    public alGraph() {
        this(GraphDirection.UNDIRECTED, GraphWeight.UNWEIGHTED);
    }

    public alGraph(GraphDirection direction, GraphWeight weight) {
        mGraphDirection = direction;
        mGraphWeight = weight;
        mGraph = new ArrayList<>();
    }

    public void addVertex(int index) {
        if(mGraph.size() <= index) {
            for(int i = mGraph.size(); i <= index; i++) {
                mGraph.add(i, null);
            }
        }
        mGraph.set(index, new HashMap<>());
    }

    public void removeVertex(int index) {
        if(index > mGraph.size()) {
            System.out.println("Vertex does not exist.");
            return;
        }

//        for(mGraph) {
//
//        }
    }

    public void addEdge(int source, int destination) {
        addEdge(source, destination, DEFAULT_WEIGHT);
    }

    public void addEdge(int source, int destination, int weight) {
        if(source > mGraph.size() || destination > mGraph.size()) {
            System.out.println("Vertex does not exist.");
            return;
        }

        mGraph.get(source).put(destination, weight);
        if(mGraphDirection.equals(GraphDirection.UNDIRECTED)) {
            mGraph.get(destination).put(source, weight);
        }
    }

    public void removeEdge(int source, int destination) {
        if(source > mGraph.size() || destination > mGraph.size()) {
            System.out.println("Vertex does not exist.");
            return;
        }

        mGraph.get(source).remove(destination);
        if(mGraphDirection.equals(GraphDirection.UNDIRECTED)) {
            mGraph.get(destination).remove(source);
        }
    }

    public void printGraph() {
        StringBuilder sb = new StringBuilder();
        for(int vIndex = 0; vIndex < mGraph.size(); vIndex++) {
            if(mGraph.get(vIndex) == null) {
                // skip
            } else {
                sb.append("Vertex ");
                sb.append(vIndex);
                sb.append(" connections: ");
                for(Integer key : mGraph.get(vIndex).keySet()) {
                    sb.append(key);
                    sb.append("(");
                    sb.append(mGraph.get(vIndex).get(key));
                    sb.append(") ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    /*
    https://stackoverflow.com/questions/14783831/java-implementation-of-adjacency-list

    https://algorithms.tutorialhorizon.com/graph-representation-adjacency-matrix-and-adjacency-list/
    https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-list-better-set-2/
    https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-matrix-set-3/

    https://github.com/phishman3579/java-algorithms-implementation/blob/master/src/com/jwetherell/algorithms/data_structures/Graph.java
    https://www.youtube.com/watch?v=09_LlHjoEiY
    https://www.geeksforgeeks.org/graph-and-its-representations/
    https://www.youtube.com/watch?v=1n5XPFcvxds
    https://www.hackerrank.com/domains/algorithms?filters%5Bsubdomains%5D%5B%5D=graph-theory
    https://www.youtube.com/watch?v=zaBhtODEL0w&list=PLOuZYwbmgZWXvkghUyMLdI90IwxbNCiWK&index=7&t=0s&app=desktop
     */
}
