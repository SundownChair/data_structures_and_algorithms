package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class AdjacencyMatrixTest {

    private final static String STORMWIND = "Stormwind";
    private final static String IRONFORGE = "Ironforge";
    private final static String DARNASSUS = "Darnassus";
    private final static String ORGRIMMAR = "Orgrimmar";

    private AdjacencyMatrix<String> mTestedClass;

    @Test
    public void testAddVertex() {
        mTestedClass = new AdjacencyMatrix<>();
        assertEquals(0, mTestedClass.countVertex());
        mTestedClass.addVertex(null);
        assertEquals(0, mTestedClass.countVertex());
        mTestedClass.addVertex(STORMWIND);
        assertEquals(1, mTestedClass.countVertex());
        mTestedClass.addVertex(IRONFORGE);
        assertEquals(2, mTestedClass.countVertex());
        mTestedClass.addVertex(DARNASSUS);
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addVertex(DARNASSUS);
        assertEquals(3, mTestedClass.countVertex());
    }

    @Test
    public void testRemoveVertex() {
        mTestedClass = new AdjacencyMatrix<>();
        mTestedClass.removeVertex(null);
        assertEquals(0, mTestedClass.countVertex());
        mTestedClass.removeVertex(STORMWIND);
        assertEquals(0, mTestedClass.countVertex());
        mTestedClass.addVertex(STORMWIND);
        mTestedClass.addVertex(IRONFORGE);
        mTestedClass.addVertex(DARNASSUS);
        mTestedClass.removeVertex(ORGRIMMAR);
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeVertex(STORMWIND);
        assertEquals(2, mTestedClass.countVertex());
        mTestedClass.removeVertex(IRONFORGE);
        mTestedClass.removeVertex(DARNASSUS);
        assertEquals(0, mTestedClass.countVertex());
    }

    @Test
    public void testAddEdgeDirected() {
        mTestedClass = new AdjacencyMatrix<>(true);
        assertEquals(0, mTestedClass.countEdge());
        mTestedClass.addEdge(null, null, 300);
        assertEquals(0, mTestedClass.countEdge());
        mTestedClass.addEdge(STORMWIND, IRONFORGE, 300);
        assertEquals(1, mTestedClass.countEdge());
        assertEquals(2, mTestedClass.countVertex());
        mTestedClass.addEdge(IRONFORGE, STORMWIND, 300);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(2, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, DARNASSUS, 300);
        assertEquals(3, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(IRONFORGE, DARNASSUS, 300);
        assertEquals(4, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(IRONFORGE, DARNASSUS, 300);
        assertEquals(4, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(IRONFORGE, null, 300);
        mTestedClass.addEdge(null, DARNASSUS, 300);
        assertEquals(4, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, STORMWIND, 0);
        assertEquals(5, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, STORMWIND, 0);
        assertEquals(5, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
    }

    @Test
    public void testAddEdgeUndirected() {
        mTestedClass = new AdjacencyMatrix<>();
        assertEquals(0, mTestedClass.countEdge());
        mTestedClass.addEdge(null, null, 300);
        assertEquals(0, mTestedClass.countEdge());
        mTestedClass.addEdge(STORMWIND, IRONFORGE, 300);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(2, mTestedClass.countVertex());
        mTestedClass.addEdge(IRONFORGE, STORMWIND, 300);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(2, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, DARNASSUS, 300);
        assertEquals(4, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(DARNASSUS, IRONFORGE, 300);
        assertEquals(6, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, STORMWIND, 0);
        assertEquals(7, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, STORMWIND, 0);
        assertEquals(7, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
    }

    @Test
    public void testRemoveEdgeDirected() {
        mTestedClass = new AdjacencyMatrix<>(true);
        mTestedClass.removeEdge(STORMWIND, IRONFORGE);
        mTestedClass.removeEdge(null, null);
        assertEquals(0, mTestedClass.countEdge());
        assertEquals(0, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, IRONFORGE, 300);
        mTestedClass.addEdge(IRONFORGE, STORMWIND, 300);
        mTestedClass.addEdge(STORMWIND, DARNASSUS, 300);
        assertEquals(3, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, DARNASSUS);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, DARNASSUS);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(DARNASSUS, STORMWIND);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, ORGRIMMAR);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, IRONFORGE);
        mTestedClass.removeEdge(IRONFORGE, STORMWIND);
        assertEquals(0, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, STORMWIND, 0);
        assertEquals(1, mTestedClass.countEdge());
        mTestedClass.removeEdge(STORMWIND, STORMWIND);
        assertEquals(0, mTestedClass.countEdge());
    }

    @Test
    public void testRemoveEdgeUndirected() {
        mTestedClass = new AdjacencyMatrix<>(false);
        mTestedClass.removeEdge(STORMWIND, IRONFORGE);
        mTestedClass.removeEdge(null, null);
        assertEquals(0, mTestedClass.countEdge());
        assertEquals(0, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, IRONFORGE, 300);
        mTestedClass.addEdge(IRONFORGE, STORMWIND, 300);
        mTestedClass.addEdge(STORMWIND, DARNASSUS, 300);
        assertEquals(4, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, IRONFORGE);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, IRONFORGE);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, ORGRIMMAR);
        assertEquals(2, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.removeEdge(STORMWIND, DARNASSUS);
        assertEquals(0, mTestedClass.countEdge());
        assertEquals(3, mTestedClass.countVertex());
        mTestedClass.addEdge(STORMWIND, STORMWIND, 0);
        assertEquals(1, mTestedClass.countEdge());
        mTestedClass.removeEdge(STORMWIND, STORMWIND);
        assertEquals(0, mTestedClass.countEdge());
    }

    @Test
    public void testGetLinkedVertices() {
        mTestedClass = new AdjacencyMatrix<>(true);
        assertTrue(mTestedClass.getLinkedVertices(STORMWIND).isEmpty());
        mTestedClass.addVertex(STORMWIND);
        assertTrue(mTestedClass.getLinkedVertices(STORMWIND).isEmpty());
        mTestedClass.addVertex(IRONFORGE);
        mTestedClass.addEdge(STORMWIND, IRONFORGE);
        assertEquals(mTestedClass.getLinkedVertices(STORMWIND).size(), 1);
        assertEquals(mTestedClass.getLinkedVertices(IRONFORGE).size(), 0);
        mTestedClass.addVertex(DARNASSUS);
        mTestedClass.addEdge(STORMWIND, DARNASSUS);
        assertEquals(mTestedClass.getLinkedVertices(STORMWIND).size(), 2);
        mTestedClass.addVertex(ORGRIMMAR);
        assertEquals(mTestedClass.getLinkedVertices(STORMWIND).size(), 2);
        mTestedClass.addEdge(IRONFORGE, DARNASSUS);
        assertEquals(mTestedClass.getLinkedVertices(STORMWIND).size(), 2);
        mTestedClass.removeEdge(STORMWIND, DARNASSUS);
        assertEquals(mTestedClass.getLinkedVertices(STORMWIND).size(), 1);
    }

    @Test
    public void testGetWeight() {
        mTestedClass = new AdjacencyMatrix<>(true);
        assertNull(mTestedClass.getWeight(null, null));
        assertNull(mTestedClass.getWeight(STORMWIND, null));
        assertNull(mTestedClass.getWeight(null, STORMWIND));
        assertNull(mTestedClass.getWeight(STORMWIND, IRONFORGE));
        mTestedClass.addVertex(STORMWIND);
        assertNull(mTestedClass.getWeight(STORMWIND, null));
        assertNull(mTestedClass.getWeight(null, STORMWIND));
        assertNull(mTestedClass.getWeight(STORMWIND, IRONFORGE));
        mTestedClass.addVertex(IRONFORGE);
        assertNull(mTestedClass.getWeight(STORMWIND, IRONFORGE));
        mTestedClass.addEdge(IRONFORGE, STORMWIND, 10);
        assertNull(mTestedClass.getWeight(STORMWIND, IRONFORGE));
        mTestedClass.addEdge(STORMWIND, IRONFORGE, 20);
        assertEquals(mTestedClass.getWeight(STORMWIND, IRONFORGE), Integer.valueOf(20));
        assertEquals(mTestedClass.getWeight(IRONFORGE, STORMWIND), Integer.valueOf(10));
        mTestedClass.addVertex(DARNASSUS);
        mTestedClass.addEdge(STORMWIND, DARNASSUS, 30);
        assertEquals(mTestedClass.getWeight(STORMWIND, DARNASSUS), Integer.valueOf(30));
        assertNull(mTestedClass.getWeight(IRONFORGE, DARNASSUS));
    }

    @Test
    public void testGetEdgeList() {
        mTestedClass = new AdjacencyMatrix<>(true);

        List<IGraph.Edge> edgeList = mTestedClass.getEdgeList();
        assertEquals(0, edgeList.size());

        mTestedClass.addVertex(STORMWIND);
        mTestedClass.addVertex(IRONFORGE);
        mTestedClass.addVertex(ORGRIMMAR);
        mTestedClass.addEdge(STORMWIND, IRONFORGE, 1);
        mTestedClass.addEdge(IRONFORGE, ORGRIMMAR, 1);
        mTestedClass.addEdge(ORGRIMMAR, STORMWIND, 1);
        mTestedClass.addEdge(STORMWIND, ORGRIMMAR, 1);

        edgeList = mTestedClass.getEdgeList();
        assertEquals(4, edgeList.size());
    }
}
