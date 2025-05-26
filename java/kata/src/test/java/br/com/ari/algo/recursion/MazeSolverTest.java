package br.com.ari.algo.recursion;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MazeSolverTest {

    @Test
    void testMazeSolver() {
        String[] maze = {
                "...#######", // end
                "##...#####",
                "####.#####",
                "#....#####",
                "#.########", // start
        };

        MazeSolver.Point start = new MazeSolver.Point(1, 4);
        MazeSolver.Point end = new MazeSolver.Point(0, 0);

        MazeSolver solver = new MazeSolver(maze, '#', start, end);
        List<MazeSolver.Point> traveledPath = solver.solve();

        MazeSolver.Point[] expectedTraveledPath = new MazeSolver.Point[] {
                new MazeSolver.Point(1, 4),
                new MazeSolver.Point(1, 3),
                new MazeSolver.Point(2, 3),
                new MazeSolver.Point(3, 3),
                new MazeSolver.Point(4, 3),
                new MazeSolver.Point(4, 2),
                new MazeSolver.Point(4, 1),
                new MazeSolver.Point(3, 1),
                new MazeSolver.Point(2, 1),
                new MazeSolver.Point(2, 0),
                new MazeSolver.Point(1, 0),
                new MazeSolver.Point(0, 0)
        };

        assertThat(traveledPath).containsExactly(expectedTraveledPath);
    }

}
