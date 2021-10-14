package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.search_algorithms.helpers.Validators;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BFSMazeTest {

    @Test
    public void testSolveMaze() {
        // Validation function
        Validators<String> validate = (a) -> {
            if(a.matches("^#.$"))
                return -1;
            else if (a.equals("EN"))
                return 0;
            return 1;
        };

        String[][] maze1 = {{"ST", "01", "02", "#0", "03", "04", "05"},
                            {"11", "#1", "12", "13", "14", "#2", "15"},
                            {"21", "#3", "22", "23", "24", "25", "26"},
                            {"31", "32", "#4", "#5", "33", "34", "35"},
                            {"#6", "41", "#7", "EN", "42", "#8", "43"}};

        assertTrue(BFSMaze.canReachEnd(maze1, validate));

        String[][] maze2 = {{"EN", "01", "02", "#0", "03", "04", "05"},
                            {"11", "#1", "12", "13", "14", "#2", "15"},
                            {"21", "#3", "22", "23", "24", "25", "26"},
                            {"31", "32", "#4", "#5", "33", "34", "35"},
                            {"#6", "41", "#7", "42", "43", "#8", "44"}};

        assertTrue(BFSMaze.canReachEnd(maze2, validate));

        String[][] maze3 = {{"ST", "01", "02", "#0", "03", "04", "05"},
                            {"11", "#1", "12", "13", "14", "#2", "15"},
                            {"21", "#3", "22", "23", "24", "25", "26"},
                            {"31", "32", "#4", "#5", "33", "34", "35"},
                            {"#6", "41", "#7", "42", "43", "#8", "44"}};

        assertFalse(BFSMaze.canReachEnd(maze3, validate));

        String[][] maze4 = {{"ST", "01", "02", "#0", "03", "04", "05"},
                            {"11", "#1", "12", "13", "14", "#2", "15"},
                            {"21", "#3", "22", "23", "24", "25", "26"},
                            {"31", "32", "#4", "#5", "33", "34", "#6"},
                            {"#7", "41", "#8", "42", "43", "#9", "EN"}};

        assertFalse(BFSMaze.canReachEnd(maze4, validate));
    }
}
