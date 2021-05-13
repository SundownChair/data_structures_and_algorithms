package com.pedrofonseca.dsalgo.data_structures;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class alGraphTest {

    private alGraph mTestedClass;

    @Before
    public void setup() {
        mTestedClass = new alGraph();
    }

    @Test
    public void testAddVertex() {
        mTestedClass.addVertex(0);
        mTestedClass.addVertex(1);
        mTestedClass.addVertex(10);
        mTestedClass.printGraph();
    }

    @Test
    public void testAddEdge() {
        mTestedClass.addVertex(0);
        mTestedClass.addVertex(1);
        mTestedClass.addVertex(2);

        mTestedClass.addEdge(0,1);
        mTestedClass.addEdge(0,2);
        mTestedClass.addEdge(1,2);

        mTestedClass.addEdge(10, 20);

        mTestedClass.printGraph();
    }

    @Test
    public void testRemoveEdge() {
        mTestedClass.addVertex(0);
        mTestedClass.addVertex(1);
        mTestedClass.addVertex(2);

        mTestedClass.addEdge(0,1);
        mTestedClass.addEdge(0,2);

        mTestedClass.printGraph();

        mTestedClass.removeEdge(0,2);

        mTestedClass.printGraph();
    }
}
