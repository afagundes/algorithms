package br.com.ari.algo.recursion;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    public record Point(int x, int y) {}

    private static final Point[] DIRECTIONS = {
            new Point(0, -1), // top
            new Point(1, 0), // right
            new Point(0, 1), // bottom
            new Point(-1, 0), // left
    };

    private final String[] maze;
    private final char wall;
    private final Point start;
    private final Point end;

    public MazeSolver(String[] maze, char wall, Point start, Point end) {
        this.maze = maze;
        this.wall = wall;
        this.start = start;
        this.end = end;
    }

    public List<Point> solve() {
        List<Point> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length()];

        walk(start, visited, path);

        return path;
    }

    private boolean walk(Point curr, boolean[][] visited, List<Point> path) {
        // Base cases
        // 1. Off the map
        if (curr.x < 0 || curr.x >= maze[0].length() || curr.y < 0 || curr.y >= maze.length) {
            return false;
        }

        // 2. On wall
        if (maze[curr.y].charAt(curr.x) == wall) {
            return false;
        }

        // 3. Visited before
        if (visited[curr.y][curr.x]) {
            return false;
        }

        // 4. Is the end
        if (curr.equals(end)) {
            path.addLast(curr);
            return true;
        }

        // Pre
        visited[curr.y][curr.x] = true;
        path.addLast(curr);

        // Recurse
        for (Point direction : DIRECTIONS) {
            var next = new Point(curr.x + direction.x, curr.y + direction.y);
            if (walk(next, visited, path)) {
                return true;
            }
        }

        // Post
        path.removeLast();

        return false;
    }

}
