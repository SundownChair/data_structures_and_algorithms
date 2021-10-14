package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.data_structures.AdjacencyList;
import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;
import com.pedrofonseca.dsalgo.search_algorithms.helpers.Validators;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BFSTest {

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
        BFS.traverse(null, null);
        BFS.traverse(null, STORMWIND);
        BFS.traverse(mGraph, null);
        mGraph.addVertex(STORMWIND);
        BFS.traverse(mGraph, STORMWIND);
        BFS.traverse(mGraph, IRONFORGE);
        mGraph.addVertex(IRONFORGE);
        BFS.traverse(mGraph, STORMWIND);
        BFS.traverse(mGraph, IRONFORGE);
        mGraph.addEdge(STORMWIND, IRONFORGE);
        BFS.traverse(mGraph, STORMWIND);
        BFS.traverse(mGraph, IRONFORGE);
        mGraph.addVertex(ORGRIMMAR);
        mGraph.addVertex(DARNASSUS);
        mGraph.addEdge(STORMWIND, ORGRIMMAR);
        mGraph.addEdge(ORGRIMMAR, DARNASSUS);
        BFS.traverse(mGraph, STORMWIND);
    }

    @Test
    public void testGetPath() {
        mGraph = new AdjacencyList<>(false);
        assertEquals(BFS.getPath(null, null, null), new ArrayList());
        assertEquals(BFS.getPath(null, STORMWIND, null), new ArrayList());
        assertEquals(BFS.getPath(null, null, STORMWIND), new ArrayList());
        assertEquals(BFS.getPath(mGraph, null, null), new ArrayList());
        mGraph.addVertex(STORMWIND);
        assertEquals(BFS.getPath(mGraph, "n/a", "n/a"), new ArrayList());
        assertEquals(BFS.getPath(mGraph, STORMWIND, null), new ArrayList());
        assertEquals(BFS.getPath(mGraph, STORMWIND, IRONFORGE), new ArrayList());
        assertEquals(BFS.getPath(mGraph, IRONFORGE, STORMWIND), new ArrayList());
        mGraph.addVertex(IRONFORGE);
        assertEquals(BFS.getPath(mGraph, IRONFORGE, STORMWIND), new ArrayList());
        assertEquals(BFS.getPath(mGraph, STORMWIND, IRONFORGE), new ArrayList());
        mGraph.addEdge(STORMWIND, IRONFORGE);
        assertEquals(BFS.getPath(mGraph, IRONFORGE, STORMWIND), Arrays.asList(IRONFORGE, STORMWIND));
        assertEquals(BFS.getPath(mGraph, STORMWIND, IRONFORGE), Arrays.asList(STORMWIND, IRONFORGE));
        mGraph.addVertex(ORGRIMMAR);
        mGraph.addVertex(DARNASSUS);
        mGraph.addEdge(STORMWIND, ORGRIMMAR);
        mGraph.addEdge(ORGRIMMAR, DARNASSUS);
        assertEquals(BFS.getPath(mGraph, IRONFORGE, DARNASSUS), Arrays.asList(IRONFORGE, STORMWIND, ORGRIMMAR, DARNASSUS));
        mGraph.addVertex(SILVERMOON);
        mGraph.addEdge(IRONFORGE, SILVERMOON);
        assertEquals(BFS.getPath(mGraph, IRONFORGE, DARNASSUS), Arrays.asList(IRONFORGE, STORMWIND, ORGRIMMAR, DARNASSUS));
        assertEquals(BFS.getPath(mGraph, IRONFORGE, SILVERMOON), Arrays.asList(IRONFORGE, SILVERMOON));
        assertEquals(BFS.getPath(mGraph, STORMWIND, DARNASSUS), Arrays.asList(STORMWIND, ORGRIMMAR, DARNASSUS));
        mGraph.addVertex(DALARAN);
        assertEquals(BFS.getPath(mGraph, DALARAN, DALARAN), Arrays.asList(DALARAN));
        assertEquals(BFS.getPath(mGraph, DARNASSUS, DALARAN), new ArrayList());
        assertEquals(BFS.getPath(mGraph, DALARAN, STORMWIND), new ArrayList());
    }

    @Test
    public void testGetConnectedComponents() {
        mGraph = new AdjacencyList<>(false);
        assertEquals(BFS.getConnectedComponents(null), new ArrayList());
        assertEquals(BFS.getConnectedComponents(mGraph), new ArrayList());
        mGraph.addVertex(STORMWIND);
        assertEquals(BFS.getConnectedComponents(mGraph).size(), 1);
        mGraph.addVertex(IRONFORGE);
        assertEquals(BFS.getConnectedComponents(mGraph).size(), 2);
        mGraph.addEdge(STORMWIND, IRONFORGE);
        assertEquals(BFS.getConnectedComponents(mGraph).size(), 1);
        mGraph.addVertex(DARNASSUS);
        mGraph.addVertex(ORGRIMMAR);
        mGraph.addVertex(SILVERMOON);
        mGraph.addEdge(DARNASSUS, ORGRIMMAR);
        mGraph.addEdge(IRONFORGE, SILVERMOON);
        assertEquals(BFS.getConnectedComponents(mGraph).size(), 2);
    }

    @Test
    public void testMazeSolver() {
        String[][] maze =  {{"ST", "01", "02", "#0", "03", "04", "05"},
                            {"11", "#1", "12", "13", "14", "#2", "15"},
                            {"21", "#3", "22", "23", "24", "25", "26"},
                            {"31", "32", "#4", "#5", "33", "34", "35"},
                            {"#6", "41", "#7", "EN", "42", "#8", "43"}};

        // Validation function
        Validators<String> validate = (a) -> {
          if(a.matches("^#.$"))
              return -1;
          else if (a.equals("EN"))
              return 0;
          return 1;
        };

        assertEquals(BFS.mazeSolver(maze, "ST", "EN", validate),
                Arrays.asList("ST","01","02","12","22","23","24","33","42","EN"));
    }

    public interface StringValidator {
        public int validate(String pValue);
    }
}
