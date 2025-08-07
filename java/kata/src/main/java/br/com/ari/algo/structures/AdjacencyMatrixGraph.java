package br.com.ari.algo.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class AdjacencyMatrixGraph<T> {

    private T[] vertexes;
    private int[][] edges;

    // Stores the vertex index. Useful to avoid doing a linear search to find the vertex.
    private final Map<T, Integer> vertexIndexes;

    public AdjacencyMatrixGraph() {
        this.vertexes = (T[]) new Object[0];
        this.edges = new int[0][];
        this.vertexIndexes = new HashMap<>();
    }

    public void addVertex(T value) {
        this.adjustMatrix();

        int index = this.vertexes.length - 1;
        this.vertexes[index] = value;
        this.vertexIndexes.put(value, index);
    }

    public void addEdge(T from, T to, int weight) {
        int fromIndex = this.vertexIndexes.get(from);
        int toIndex = this.vertexIndexes.get(to);
        this.edges[fromIndex][toIndex] = weight;
    }

    /**
     * Uses Breadth-first search to find a path between two vertices
     */
    public List<T> findPath(T from, T to) {
        boolean[] seen = new boolean[this.vertexes.length];

        int[] prev = new int[this.vertexes.length];
        Arrays.fill(prev, -1);

        int fromIndex = this.vertexIndexes.get(from);
        int toIndex = this.vertexIndexes.get(to);

        seen[fromIndex] = true;

        Queue<Integer> q = new Queue<>();
        q.enqueue(fromIndex);

        while (q.size() > 0) {
            int curr = q.dequeue();

            if (curr == toIndex) {
                break;
            }

            int[] adjs = this.edges[curr];
            for (int i = 0; i < adjs.length; i++) {
                if (adjs[i] == 0 || seen[i]) {
                    continue;
                }

                seen[i] = true;
                prev[i] = curr;
                q.enqueue(i);
            }

            seen[curr] = true;
        }

        if (prev[toIndex] == -1) {
            return Collections.emptyList();
        }

        // Build the output array from back to front
        int curr = toIndex;
        List<T> out = new ArrayList<>(prev.length);

        while (prev[curr] != -1) {
            out.add(this.vertexes[curr]);
            curr = prev[curr];
        }

        out.add(from);
        out = out.reversed();

        return out;
    }

    private void adjustMatrix() {
        var newVertexes = new Object[this.vertexes.length + 1];
        var newEdges = new int[this.edges.length + 1][];

        System.arraycopy(this.vertexes, 0, newVertexes, 0, this.vertexes.length);
        System.arraycopy(this.edges, 0, newEdges, 0, this.edges.length);

        this.vertexes = (T[]) newVertexes;
        this.edges = newEdges;

        for (int i = 0; i < this.edges.length; i++) {
            int[] adjacencyRow = this.edges[i];

            if (adjacencyRow == null) {
                this.edges[i] = new int[this.edges.length];
                return;
            }

            int[] newAdjacencyRow = new int[adjacencyRow.length + 1];
            System.arraycopy(adjacencyRow, 0, newAdjacencyRow, 0, adjacencyRow.length);

            this.edges[i] = newAdjacencyRow;
        }
    }

}
