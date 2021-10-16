package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class DijkstraTest {

    private final static String STORMWIND = "Stormwind";
    private final static String IRONFORGE = "Ironforge";
    private final static String ORGRIMMAR = "Orgrimmar";
    private final static String DARNASSUS = "Darnassus";
    private final static String UNDERCITY = "Undercity";
    private final static String DALARAN = "Dalaran";

    private IGraph<String> mGraph;

    @Test
    public void testDijkstra() {
        try {
            Dijkstra.run(null, null);
            fail();
        } catch (NullPointerException e) {
            // all good
        }

        mGraph = new AdjacencyList<>(true);

        try {
            Dijkstra.run(mGraph, STORMWIND);
            fail();
        } catch (NoSuchElementException e) {
            // all good
        }

        mGraph.addVertex(STORMWIND); // 0
        mGraph.addVertex(IRONFORGE); // 1
        mGraph.addVertex(ORGRIMMAR); // 2
        mGraph.addVertex(DARNASSUS); // 3
        mGraph.addVertex(UNDERCITY); // 4
        mGraph.addVertex(DALARAN);   // 5
        mGraph.addEdge(STORMWIND, IRONFORGE, 5);
        mGraph.addEdge(STORMWIND, ORGRIMMAR, 1);
        mGraph.addEdge(IRONFORGE, ORGRIMMAR, 2);
        mGraph.addEdge(IRONFORGE, UNDERCITY, 20);
        mGraph.addEdge(IRONFORGE, DARNASSUS, 3);
        mGraph.addEdge(ORGRIMMAR, IRONFORGE, 3);
        mGraph.addEdge(ORGRIMMAR, UNDERCITY, 12);
        mGraph.addEdge(DARNASSUS, ORGRIMMAR, 3);
        mGraph.addEdge(DARNASSUS, UNDERCITY, 2);
        mGraph.addEdge(DARNASSUS, DALARAN, 6);
        mGraph.addEdge(UNDERCITY, DALARAN, 1);

        Map<String, Dijkstra.DOutput> dMap = Dijkstra.run(mGraph, STORMWIND);
        assertEquals(Integer.valueOf(0), dMap.get(STORMWIND).getDistanceFromStart());
        assertEquals(Integer.valueOf(4), dMap.get(IRONFORGE).getDistanceFromStart());
        assertEquals(Integer.valueOf(1), dMap.get(ORGRIMMAR).getDistanceFromStart());
        assertEquals(Integer.valueOf(7), dMap.get(DARNASSUS).getDistanceFromStart());
        assertEquals(Integer.valueOf(9), dMap.get(UNDERCITY).getDistanceFromStart());
        assertEquals(Integer.valueOf(10), dMap.get(DALARAN).getDistanceFromStart());

        List<String> shortestPathToDalaran = new ArrayList<>();
        String current = DALARAN;
        while(current != null) {
            shortestPathToDalaran.add(current);
            current = (String) dMap.get(current).getPrevious();
        }
        Collections.reverse(shortestPathToDalaran);
        assertEquals(Arrays.asList(STORMWIND, ORGRIMMAR, IRONFORGE, DARNASSUS, UNDERCITY, DALARAN),
                shortestPathToDalaran);
    }

    @Test
    public void testNegativeEdgeDijkstra() {
        mGraph = new AdjacencyList<>(true);
        mGraph.addVertex(STORMWIND);
        mGraph.addVertex(IRONFORGE);
        mGraph.addEdge(STORMWIND, IRONFORGE, -1);

        try {
            Dijkstra.run(mGraph, STORMWIND);
            fail();
        } catch (IllegalArgumentException e) {
            // all good
        }
    }
}
