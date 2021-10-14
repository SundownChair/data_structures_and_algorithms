package com.pedrofonseca.dsalgo.sort_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TopSortTest {

    @Test
    public void testSort() {
        IGraph<String> graph1 = new AdjacencyList<>(true);
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");
        graph1.addVertex("E");
        graph1.addVertex("F");
        graph1.addVertex("G");
        graph1.addVertex("H");
        graph1.addVertex("I");
        graph1.addVertex("J");
        graph1.addVertex("K");
        graph1.addVertex("L");
        graph1.addVertex("M");
        graph1.addEdge("C", "A");
        graph1.addEdge("C", "B");
        graph1.addEdge("A", "D");
        graph1.addEdge("B", "D");
        graph1.addEdge("D", "G");
        graph1.addEdge("D", "H");
        graph1.addEdge("G", "I");
        graph1.addEdge("H", "I");
        graph1.addEdge("H", "J");
        graph1.addEdge("I", "L");
        graph1.addEdge("E", "A");
        graph1.addEdge("E", "D");
        graph1.addEdge("E", "F");
        graph1.addEdge("F", "K");
        graph1.addEdge("F", "J");
        graph1.addEdge("K", "J");
        graph1.addEdge("J", "L");
        graph1.addEdge("J", "M");

        assertEquals(TopSort.sort(graph1), Arrays.asList("E", "F", "K", "C", "B", "A", "D", "G", "H", "J", "M", "I", "L"));

        IGraph<String> graph2 = null;
        assertEquals(TopSort.sort(graph2), new ArrayList<String>());

        IGraph<String> graph3 = new AdjacencyList<>(true);
        graph3.addVertex("A");
        graph3.addVertex("B");
        graph3.addEdge("A", "B");
        assertEquals(TopSort.sort(graph3), Arrays.asList("A", "B"));
    }
}