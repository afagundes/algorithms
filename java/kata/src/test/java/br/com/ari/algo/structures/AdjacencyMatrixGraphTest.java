package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdjacencyMatrixGraphTest {

    @Test
    void testAdjacencyMatrix() {
        // Draws a graph as shown in the image docs/graph.png
        var graph = new AdjacencyMatrixGraph<Integer>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        // graph.addEdge(1, 2, 1); -> como não há conexão entre os vértices 1 e 2, o caminho entre eles deverá ser mais longo
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 3);
        graph.addEdge(5, 2, 1);

        assertThat(graph.findPath(1, 2)).containsExactly(1, 3, 5, 2);
    }

}
