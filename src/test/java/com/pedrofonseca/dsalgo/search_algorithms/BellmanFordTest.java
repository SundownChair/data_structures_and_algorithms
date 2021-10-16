package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BellmanFordTest {

    private static final String STORMWIND = "Stormwind";
    private static final String IRONFORGE = "Ironforge";
    private static final String DARNASSUS = "Darnassus";
    private static final String EXODAR = "Exodar";
    private static final String ORGRIMMAR = "Orgrimmar";
    private static final String UNDERCITY = "Undercity";
    private static final String SILVERMOON = "Silvermoon";
    private static final String THUNDERBLUFF = "Thunder Bluff";
    private static final String DALARAN = "Dalaran";
    private static final String SHATTRATH = "Shattrath";

    @Test
    public void testRunWithNegativeCycles() {
        IGraph<String> graph = new AdjacencyList<>(true);
        graph.addVertex(STORMWIND);
        graph.addVertex(IRONFORGE);
        graph.addVertex(DARNASSUS);
        graph.addVertex(EXODAR);
        graph.addVertex(ORGRIMMAR);
        graph.addVertex(UNDERCITY);
        graph.addVertex(SILVERMOON);
        graph.addVertex(THUNDERBLUFF);
        graph.addVertex(DALARAN);
        graph.addVertex(SHATTRATH);
        graph.addEdge(STORMWIND, IRONFORGE, 5);
        graph.addEdge(IRONFORGE, DARNASSUS, 60);
        graph.addEdge(IRONFORGE, UNDERCITY, 30);
        graph.addEdge(IRONFORGE, DALARAN, 20);
        graph.addEdge(UNDERCITY, DARNASSUS, 5);
        graph.addEdge(UNDERCITY, ORGRIMMAR, 50);
        graph.addEdge(UNDERCITY, SHATTRATH, 25);
        graph.addEdge(DARNASSUS, EXODAR, -50);
        graph.addEdge(EXODAR, ORGRIMMAR, -10);
        graph.addEdge(THUNDERBLUFF, DALARAN, -15);
        graph.addEdge(DALARAN, THUNDERBLUFF, 10);
        graph.addEdge(DALARAN, SHATTRATH, 75);
        graph.addEdge(SHATTRATH, SILVERMOON, 100);

        Map<String, BellmanFord.DOutput<String>> output = BellmanFord.run(graph, STORMWIND);
        assertEquals(6, output.size());
        assertEquals(Integer.valueOf(0), output.get(STORMWIND).getDistanceFromStart());
        assertEquals(Integer.valueOf(40), output.get(DARNASSUS).getDistanceFromStart());
        assertEquals(Integer.valueOf(35), output.get(UNDERCITY).getDistanceFromStart());
        assertEquals(Integer.valueOf(-10), output.get(EXODAR).getDistanceFromStart());
        assertEquals(Integer.valueOf(-20), output.get(ORGRIMMAR).getDistanceFromStart());
        assertEquals(Integer.valueOf(5), output.get(IRONFORGE).getDistanceFromStart());
        assertNull(output.get(SILVERMOON));
        assertNull(output.get(SHATTRATH));
        assertNull(output.get(DALARAN));
        assertNull(output.get(THUNDERBLUFF));

        List<String> shortestPathToOrgrimmar = new ArrayList<>();
        String current = ORGRIMMAR;
        while(current != null) {
            shortestPathToOrgrimmar.add(current);
            current = (String) output.get(current).getPrevious();
        }
        Collections.reverse(shortestPathToOrgrimmar);
        assertEquals(Arrays.asList(STORMWIND, IRONFORGE, UNDERCITY, DARNASSUS, EXODAR, ORGRIMMAR),
                shortestPathToOrgrimmar);
    }

    @Test
    public void testRunWithoutNegativeCycles() {
        IGraph<String> graph = new AdjacencyList<>(true);
        graph.addVertex(STORMWIND);
        graph.addVertex(IRONFORGE);
        graph.addVertex(DARNASSUS);
        graph.addVertex(EXODAR);
        graph.addVertex(ORGRIMMAR);
        graph.addVertex(UNDERCITY);
        graph.addVertex(SILVERMOON);
        graph.addVertex(THUNDERBLUFF);
        graph.addVertex(DALARAN);
        graph.addVertex(SHATTRATH);
        graph.addEdge(STORMWIND, IRONFORGE, 5);
        graph.addEdge(IRONFORGE, DARNASSUS, 60);
        graph.addEdge(IRONFORGE, UNDERCITY, 30);
        graph.addEdge(IRONFORGE, DALARAN, 20);
        graph.addEdge(UNDERCITY, DARNASSUS, 5);
        graph.addEdge(UNDERCITY, ORGRIMMAR, 50);
        graph.addEdge(UNDERCITY, SHATTRATH, 25);
        graph.addEdge(DARNASSUS, EXODAR, 50);
        graph.addEdge(EXODAR, ORGRIMMAR, 10);
        graph.addEdge(THUNDERBLUFF, DALARAN, 15);
        graph.addEdge(DALARAN, THUNDERBLUFF, 10);
        graph.addEdge(DALARAN, SHATTRATH, 75);
        graph.addEdge(SHATTRATH, SILVERMOON, 100);

        Map<String, BellmanFord.DOutput<String>> output = BellmanFord.run(graph, STORMWIND);
        assertEquals(10, output.size());
        assertEquals(Integer.valueOf(0), output.get(STORMWIND).getDistanceFromStart());
        assertEquals(Integer.valueOf(40), output.get(DARNASSUS).getDistanceFromStart());
        assertEquals(Integer.valueOf(35), output.get(UNDERCITY).getDistanceFromStart());
        assertEquals(Integer.valueOf(90), output.get(EXODAR).getDistanceFromStart());
        assertEquals(Integer.valueOf(85), output.get(ORGRIMMAR).getDistanceFromStart());
        assertEquals(Integer.valueOf(5), output.get(IRONFORGE).getDistanceFromStart());
        assertEquals(Integer.valueOf(160), output.get(SILVERMOON).getDistanceFromStart());
        assertEquals(Integer.valueOf(60), output.get(SHATTRATH).getDistanceFromStart());
        assertEquals(Integer.valueOf(25), output.get(DALARAN).getDistanceFromStart());
        assertEquals(Integer.valueOf(35), output.get(THUNDERBLUFF).getDistanceFromStart());

        List<String> shortestPathToSilvermoon = new ArrayList<>();
        String current = SILVERMOON;
        while(current != null) {
            shortestPathToSilvermoon.add(current);
            current = (String) output.get(current).getPrevious();
        }
        Collections.reverse(shortestPathToSilvermoon);
        assertEquals(Arrays.asList(STORMWIND, IRONFORGE, UNDERCITY, SHATTRATH, SILVERMOON),
                shortestPathToSilvermoon);
    }
}
