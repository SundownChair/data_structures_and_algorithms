package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DAGExtremePathFinderTest {

    @Test
    public void testShortestPath() {
        assertEquals(DAGExtremePathFinder.shortestPath(null, null), new HashMap<String, Integer>());
        IGraph<String> graph1 = new AdjacencyList<>(true);
        assertEquals(DAGExtremePathFinder.shortestPath(graph1, "A"), new HashMap<String, Integer>());
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");
        graph1.addVertex("E");
        graph1.addVertex("F");
        graph1.addVertex("G");
        graph1.addVertex("H");
        graph1.addEdge("A", "B", 3);
        graph1.addEdge("A", "C", 6);
        graph1.addEdge("B", "C", 4);
        graph1.addEdge("B", "D", 4);
        graph1.addEdge("B", "E", 11);
        graph1.addEdge("C", "D", 8);
        graph1.addEdge("C", "G", 11);
        graph1.addEdge("D", "E", -4);
        graph1.addEdge("D", "F", 5);
        graph1.addEdge("D", "G", 2);
        graph1.addEdge("E", "H", 9);
        graph1.addEdge("F", "H", 1);
        graph1.addEdge("G", "H", 2);
        Map<String, Integer> result1 = DAGExtremePathFinder.shortestPath(graph1, "A");
        assertEquals(result1.get("A"), Integer.valueOf(0));
        assertEquals(result1.get("B"), Integer.valueOf(3));
        assertEquals(result1.get("C"), Integer.valueOf(6));
        assertEquals(result1.get("D"), Integer.valueOf(7));
        assertEquals(result1.get("E"), Integer.valueOf(3));
        assertEquals(result1.get("F"), Integer.valueOf(12));
        assertEquals(result1.get("G"), Integer.valueOf(9));
        assertEquals(result1.get("H"), Integer.valueOf(11));

        IGraph<String> graph2 = new AdjacencyList<>(true);
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addEdge("A", "B", 1);
        graph2.addEdge("A", "C", 2);
        graph2.addEdge("B", "D", 3);
        graph2.addEdge("C", "D", 0);
        graph2.addEdge("D", "E", 5);
        Map<String, Integer> result2 = DAGExtremePathFinder.shortestPath(graph2, "B");
        assertNull(result2.get("A"));
        assertEquals(result2.get("B"), Integer.valueOf(0));
        assertNull(result2.get("C"));
        assertEquals(result2.get("D"), Integer.valueOf(3));
        assertEquals(result2.get("E"), Integer.valueOf(8));
    }

    @Test
    public void testLongestPath() {
        assertEquals(DAGExtremePathFinder.longestPath(null, null), new HashMap<String, Integer>());
        IGraph<String> graph1 = new AdjacencyList<>(true);
        assertEquals(DAGExtremePathFinder.longestPath(graph1, "A"), new HashMap<String, Integer>());
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");
        graph1.addVertex("E");
        graph1.addVertex("F");
        graph1.addVertex("G");
        graph1.addVertex("H");
        graph1.addEdge("A", "B", 3);
        graph1.addEdge("A", "C", 6);
        graph1.addEdge("B", "C", 4);
        graph1.addEdge("B", "D", 4);
        graph1.addEdge("B", "E", 11);
        graph1.addEdge("C", "D", 8);
        graph1.addEdge("C", "G", 11);
        graph1.addEdge("D", "E", -4);
        graph1.addEdge("D", "F", 5);
        graph1.addEdge("D", "G", 2);
        graph1.addEdge("E", "H", 9);
        graph1.addEdge("F", "H", 1);
        graph1.addEdge("G", "H", 2);
        Map<String, Integer> result1 = DAGExtremePathFinder.longestPath(graph1, "A");
        assertEquals(result1.get("A"), Integer.valueOf(0));
        assertEquals(result1.get("B"), Integer.valueOf(3));
        assertEquals(result1.get("C"), Integer.valueOf(7));
        assertEquals(result1.get("D"), Integer.valueOf(15));
        assertEquals(result1.get("E"), Integer.valueOf(14));
        assertEquals(result1.get("F"), Integer.valueOf(20));
        assertEquals(result1.get("G"), Integer.valueOf(18));
        assertEquals(result1.get("H"), Integer.valueOf(23));

        IGraph<String> graph2 = new AdjacencyList<>(true);
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("C");
        graph2.addVertex("D");
        graph2.addVertex("E");
        graph2.addEdge("A", "B", 1);
        graph2.addEdge("A", "C", 2);
        graph2.addEdge("B", "D", 3);
        graph2.addEdge("C", "D", 0);
        graph2.addEdge("D", "E", 5);
        Map<String, Integer> result2 = DAGExtremePathFinder.longestPath(graph2, "B");
        assertNull(result2.get("A"));
        assertEquals(result2.get("B"), Integer.valueOf(0));
        assertNull(result2.get("C"));
        assertEquals(result2.get("D"), Integer.valueOf(3));
        assertEquals(result2.get("E"), Integer.valueOf(8));
    }
}
