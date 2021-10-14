package com.pedrofonseca.dsalgo.search_algorithms;

import com.pedrofonseca.dsalgo.search_algorithms.helpers.Validators;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple maze solver that applies BFS directly in array. Implementation only detects if path exists from start to end,
 * but can be expanded to have move memory like {@link com.pedrofonseca.dsalgo.search_algorithms.BFS#mazeSolver(Comparable[][], Comparable, Comparable, Validators)}.
 */
public class BFSMaze {

    private static int I_START_INDEX = 0;
    private static int M_START_INDEX = 0;
    private static int[] iMoves = {-1,1,0,0};
    private static int[] mMoves = {0,0,-1,1};

    public static <T extends Comparable<? super T>> boolean canReachEnd(T[][] pMaze, Validators<T> validator){
        return canReachEnd(pMaze, validator, I_START_INDEX, M_START_INDEX);
    }

    public static <T extends Comparable<? super T>> boolean canReachEnd(T[][] pMaze, Validators<T> validator,
                                                                        int pIStartIndex, int pMStartIndex){
        if(pMaze == null || validator == null || pMaze.length < 1 || pMaze[0].length < 1) {
            return false;
        }

        int iLen = pMaze.length;
        int mLen = pMaze[0].length;

        // Init visited array
        boolean[][] visited = new boolean[iLen][mLen];

        // Init index queues
        Queue<Integer> iQueue = new LinkedList<>();
        Queue<Integer> mQueue = new LinkedList<>();

        iQueue.add(pIStartIndex);
        mQueue.add(pMStartIndex);

        boolean endReached = false;
        while(!iQueue.isEmpty()) {
            // Dequeue positions and update visited array
            int i = iQueue.poll();
            int m = mQueue.poll();
            visited[i][m] = true;

            // Check for end
            if(validator.validate(pMaze[i][m]) == 0) {
                return true;
            }

            // Explore neighbour nodes
            for(int mov = 0; mov < iMoves.length; mov++) {
                int movI = i + iMoves[mov];
                int movM = m + mMoves[mov];

                // Ignore moves beyond array limits
                if(movI >= iLen || movI < 0 || movM >= mLen || movM < 0) {
                    continue;
                }

                // Only queue nodes if they are valid moves
                if(validator.validate(pMaze[movI][movM]) >= 0 && !visited[movI][movM]) {
                    iQueue.add(movI);
                    mQueue.add(movM);
                }
            }
        }

        return false;
    }
}
