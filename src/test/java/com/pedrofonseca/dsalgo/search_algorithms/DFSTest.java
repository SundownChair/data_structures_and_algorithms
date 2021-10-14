package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DFSTest {

    private final static String STORMWIND = "Stormwind";
    private final static String IRONFORGE = "Ironforge";
    private final static String DARNASSUS = "Darnassus";
    private final static String ORGRIMMAR = "Orgrimmar";
    private final static String SILVERMOON = "Silvermoon";
    private final static String DALARAN = "Dalaran";

    private IGraph<String> mGraph;

    @Test
    public void testTraverse() {
        mGraph = new AdjacencyList<>(false);
        DFS.traverse(null, null);
        DFS.traverse(null, STORMWIND);
        DFS.traverse(mGraph, null);
        mGraph.addVertex(STORMWIND);
        DFS.traverse(mGraph, STORMWIND);
        DFS.traverse(mGraph, IRONFORGE);
        mGraph.addVertex(IRONFORGE);
        DFS.traverse(mGraph, STORMWIND);
        DFS.traverse(mGraph, IRONFORGE);
        mGraph.addEdge(STORMWIND, IRONFORGE);
        DFS.traverse(mGraph, STORMWIND);
        DFS.traverse(mGraph, IRONFORGE);
        mGraph.addVertex(ORGRIMMAR);
        mGraph.addVertex(DARNASSUS);
        mGraph.addEdge(STORMWIND, ORGRIMMAR);
        mGraph.addEdge(ORGRIMMAR, DARNASSUS);
        DFS.traverse(mGraph, STORMWIND);
    }

    @Test
    public void testGetPath() {
        mGraph = new AdjacencyList<>(false);
        assertEquals(DFS.getPath(null, null, null), new ArrayList());
        assertEquals(DFS.getPath(null, STORMWIND, null), new ArrayList());
        assertEquals(DFS.getPath(null, null, STORMWIND), new ArrayList());
        assertEquals(DFS.getPath(mGraph, null, null), new ArrayList());
        mGraph.addVertex(STORMWIND);
        assertEquals(DFS.getPath(mGraph, "n/a", "n/a"), new ArrayList());
        assertEquals(DFS.getPath(mGraph, STORMWIND, null), new ArrayList());
        assertEquals(DFS.getPath(mGraph, STORMWIND, IRONFORGE), new ArrayList());
        assertEquals(DFS.getPath(mGraph, IRONFORGE, STORMWIND), new ArrayList());
        mGraph.addVertex(IRONFORGE);
        assertEquals(DFS.getPath(mGraph, IRONFORGE, STORMWIND), new ArrayList());
        assertEquals(DFS.getPath(mGraph, STORMWIND, IRONFORGE), new ArrayList());
        mGraph.addEdge(STORMWIND, IRONFORGE);
        assertEquals(DFS.getPath(mGraph, IRONFORGE, STORMWIND), Arrays.asList(IRONFORGE, STORMWIND));
        assertEquals(DFS.getPath(mGraph, STORMWIND, IRONFORGE), Arrays.asList(STORMWIND, IRONFORGE));
        mGraph.addVertex(ORGRIMMAR);
        mGraph.addVertex(DARNASSUS);
        mGraph.addEdge(STORMWIND, ORGRIMMAR);
        mGraph.addEdge(ORGRIMMAR, DARNASSUS);
        assertEquals(DFS.getPath(mGraph, IRONFORGE, DARNASSUS), Arrays.asList(IRONFORGE, STORMWIND, ORGRIMMAR, DARNASSUS));
        mGraph.addVertex(SILVERMOON);
        mGraph.addEdge(IRONFORGE, SILVERMOON);
        assertEquals(DFS.getPath(mGraph, IRONFORGE, DARNASSUS), Arrays.asList(IRONFORGE, STORMWIND, ORGRIMMAR, DARNASSUS));
        assertEquals(DFS.getPath(mGraph, IRONFORGE, SILVERMOON), Arrays.asList(IRONFORGE, SILVERMOON));
        assertEquals(DFS.getPath(mGraph, STORMWIND, DARNASSUS), Arrays.asList(STORMWIND, ORGRIMMAR, DARNASSUS));
        mGraph.addVertex(DALARAN);
        assertEquals(DFS.getPath(mGraph, DALARAN, DALARAN), Arrays.asList(DALARAN));
        assertEquals(DFS.getPath(mGraph, DARNASSUS, DALARAN), new ArrayList());
        assertEquals(DFS.getPath(mGraph, DALARAN, STORMWIND), new ArrayList());
    }

    @Test
    public void testGetConnectedComponents() {
        mGraph = new AdjacencyList<>(false);
        assertEquals(DFS.getConnectedComponents(null), new ArrayList());
        assertEquals(DFS.getConnectedComponents(mGraph), new ArrayList());
        mGraph.addVertex(STORMWIND);
        assertEquals(DFS.getConnectedComponents(mGraph).size(), 1);
        mGraph.addVertex(IRONFORGE);
        assertEquals(DFS.getConnectedComponents(mGraph).size(), 2);
        mGraph.addEdge(STORMWIND, IRONFORGE);
        assertEquals(DFS.getConnectedComponents(mGraph).size(), 1);
        mGraph.addVertex(DARNASSUS);
        mGraph.addVertex(ORGRIMMAR);
        mGraph.addVertex(SILVERMOON);
        mGraph.addEdge(DARNASSUS, ORGRIMMAR);
        mGraph.addEdge(IRONFORGE, SILVERMOON);
        assertEquals(DFS.getConnectedComponents(mGraph).size(), 2);
    }
}
