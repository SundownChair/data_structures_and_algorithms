package com.pedrofonseca.dsalgo.data_structures;

import com.pedrofonseca.dsalgo.data_structures.interfaces.IGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Graph represented as an n*n array (where n is the number of nodes). The position [A][B] represents the weight of the
 * A->B edge.</p>
 * <p>(Traditional integer based) Pros: More space efficient for dense graphs; Edge weight lookup is O(1);
 * simplest representation</p>
 * <p>(Traditional integer based) Cons: O(vertex²) space complexity; Iterating over all edges takes O(vertex²)</p>
 * <p>Space complexity: O(num of vertices²) (always contains n*n array)</p>
 * <p></p>
 * <p>Average Vertex Access Time Complexity: O(1)</p>
 * <p>Average Vertex Search Time Complexity: O(1)</p>
 * <p>Average Vertex Insert Time Complexity: O(num of vertices)</p>
 * <p>Average Vertex Delete Time Complexity: O(num of vertices)</p>
 * <p>Average Edge Access Time Complexity: O(1)</p>
 * <p>Average Edge Search Time Complexity: O(1)</p>
 * <p>Average Edge Insert Time Complexity: O(1)</p>
 * <p>Average Edge Delete Time Complexity: O(1)</p>
 */
public class AdjacencyMatrix<T extends Comparable<? super T>> implements IGraph<T> {

    private final boolean IS_DIRECTED;
    private final Integer DEFAULT_WEIGHT = 1;
    private final Integer DEFAULT_MATRIX_SIZE = 10;

    private boolean[] vert;
    private Map<T, VertexRef> vertices;
    private Map<Integer, VertexRef> indexLookupVertices;

    private Integer[][] adjMatrix;

    private int numOfVertices;
    private int numOfEdges;

    public AdjacencyMatrix() {
        this(false);
    }

    public AdjacencyMatrix(Boolean pIsDirected) {
        vertices = new HashMap<>();
        indexLookupVertices = new HashMap<>();
        adjMatrix = new Integer[DEFAULT_MATRIX_SIZE][DEFAULT_MATRIX_SIZE];
        vert = new boolean[DEFAULT_MATRIX_SIZE];
        numOfVertices = 0;
        numOfEdges = 0;

        IS_DIRECTED = pIsDirected;
    }

    @Override
    public void addVertex(T pLabel) {
        if(pLabel != null && !vertices.containsKey(pLabel)) {
            int iToAdd = findFirstEmptyIndex();

            vert[iToAdd] = true;
            VertexRef<T> ref = new VertexRef<>(pLabel, iToAdd);
            vertices.put(pLabel, ref);
            // adjMatrix[iToAdd][iToAdd] = DEFAULT_SELF_WEIGHT; // Removed since it adds 1 to Edge count
            numOfVertices++;

            indexLookupVertices.put(iToAdd, ref);

            adjustMatrix();
        }
    }

    private int findFirstEmptyIndex() {
        for(int i = 0; i < vert.length; i++) {
            if(!vert[i]) {
                return i;
            }
        }
        return 0;
    }

    private void adjustMatrix() {
        if(numOfVertices > adjMatrix.length / 2) {
            int newSize = adjMatrix.length * 2;
            Integer[][] newMatrix = new Integer[newSize][newSize];
            for(int i = 0; i < adjMatrix.length; i++) {
                System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, adjMatrix[i].length);
            }
            adjMatrix = newMatrix;
        } else if(numOfVertices < adjMatrix.length / 4) {
            int newSize = adjMatrix.length / 2;
            Integer[][] newMatrix = new Integer[newSize][newSize];
            for(int i = 0; i < newSize; i++) {
                System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, newMatrix[i].length);
            }
            adjMatrix = newMatrix;
        }
    }

    @Override
    public void removeVertex(T pLabel) {
        if(pLabel != null && vertices.containsKey(pLabel)) {
            VertexRef refToRemove = vertices.get(pLabel);
            int iToRemove = refToRemove.getIndex();

            vert[iToRemove] = false;
            vertices.remove(pLabel);
            indexLookupVertices.remove(iToRemove);
            // Remove self edges
            int i = 0;
            while(i < adjMatrix.length) {
                adjMatrix[iToRemove][i] = null;
                adjMatrix[i][iToRemove] = null;
                i++;
            }
            numOfVertices--;

            adjustMatrix();
        }
    }

    @Override
    public void addEdge(T pLabel1, T pLabel2) {
        addEdge(pLabel1, pLabel2, DEFAULT_WEIGHT);
    }

    @Override
    public void addEdge(T pLabel1, T pLabel2, Integer pEdgeValue) {
        if (pLabel1 == null || pLabel2 == null) return;

        addVertex(pLabel1);
        addVertex(pLabel2);

        VertexRef ref1 = vertices.get(pLabel1);
        VertexRef ref2 = vertices.get(pLabel2);

        if(adjMatrix[ref1.getIndex()][ref2.getIndex()] == null) numOfEdges++;
        adjMatrix[ref1.getIndex()][ref2.getIndex()] = pEdgeValue;
        if(!IS_DIRECTED) {
            if(adjMatrix[ref2.getIndex()][ref1.getIndex()] == null) numOfEdges++;
            adjMatrix[ref2.getIndex()][ref1.getIndex()] = pEdgeValue;
        }
    }

    @Override
    public void removeEdge(T pLabel1, T pLabel2) {
        if (pLabel1 == null || pLabel2 == null) return;

        VertexRef ref1 = vertices.get(pLabel1);
        VertexRef ref2 = vertices.get(pLabel2);

        if(ref1 == null || ref2 == null) return;

        if(adjMatrix[ref1.getIndex()][ref2.getIndex()] != null) numOfEdges--;
        adjMatrix[ref1.getIndex()][ref2.getIndex()] = null;
        if(!IS_DIRECTED) {
            if(adjMatrix[ref2.getIndex()][ref1.getIndex()] != null) numOfEdges--;
            adjMatrix[ref2.getIndex()][ref1.getIndex()] = null;
        }
    }

    @Override
    public int countVertex() {
        return vertices.keySet().size();
    }

    @Override
    public int countEdge() {
        return numOfEdges;
    }

    @Override
    public List<T> getLinkedVertices(T pLabel) {
        List<T> labelList = new ArrayList<>();
        if (pLabel == null) return labelList;

        VertexRef ref = vertices.get(pLabel);
        if (ref == null) return labelList;

        for(int i = 0; i < numOfVertices; i++) {
            if (adjMatrix[ref.getIndex()][i] != null) {
                labelList.add((T) indexLookupVertices.get(i).getVertex());
            }
        }

        return labelList;
    }

    @Override
    public boolean contains(T pLabel) {
        return vertices.containsKey(pLabel);
    }

    @Override
    public List<T> getVertices() {
        return new ArrayList<>(vertices.keySet());
    }

    @Override
    public Integer getWeight(T from, T to) {
        if(contains(from) && contains(to)) {
            return adjMatrix[vertices.get(from).getIndex()][vertices.get(to).getIndex()];
        }
        return null;
    }

    private static class VertexRef<J extends Comparable<? super J>> {
        private J vertex;
        private int index;

        VertexRef(J pVertex, int pIndex) {
            vertex = pVertex;
            index = pIndex;
        }

        public J getVertex() {
            return vertex;
        }

        public int getIndex() {
            return index;
        }
    }
}
