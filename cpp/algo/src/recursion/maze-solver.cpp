#include <iostream>
#include <string>
#include "../main.h"

using namespace std;

struct Point {
    int x;
    int y;
};

static constexpr std::array<Point, 4> directions = {{
    { 0, -1 }, // top
    { 1, 0 }, // right
    { 0, 1}, // bottom
    { -1, 0 } // left
}};

bool walk(const std::vector<string> &maze, const char &wall, const Point curr, const Point &end, std::vector<std::vector<bool>> &seen, std::vector<Point> &path) {
    // Base cases
    // 1. Off the map
    if (curr.x < 0 || curr.x >= maze[0].length() || curr.y < 0 || curr.y >= maze.size()) {
        return false;
    }

    // 2. On a wall
    if (maze[curr.y][curr.x] == wall) {
        return false;
    }

    // 3. Visited
    if (seen[curr.y][curr.x] == true) {
        return false;
    }

    // 4. At the end
    if (curr.x == end.x && curr.y == end.y) {
        path.push_back(curr);
        return true;
    }

    // Pre
    seen[curr.y][curr.x] = true;
    path.push_back(curr);

    // Recurse
    for (auto [x, y] : directions) {
        const Point next = {
            .x = curr.x + x,
            .y = curr.y + y,
        };

        if (walk(maze, wall, next, end, seen, path)) {
            return true;
        }
    }

    // Post
    path.pop_back();

    return false;
}

std::vector<Point> solve(const std::vector<string> &maze, const char &wall, const Point start, const Point &end) {
    std::vector seen(maze.size(), std::vector(maze[0].size(), false));
    std::vector<Point> path;

    walk(maze, wall, start, end, seen, path);

    return path;
}

void solveMaze() {
    cout << "Recursion - Maze solver" << endl;

    const std::vector<string> maze = {{
        "...#######", // end
        "##...#####",
        "####.#####",
        "#....#####",
        "#.########", // start
    }};

    constexpr Point start = { .x = 1, .y = 4 };
    constexpr Point end = { .x = 0, .y = 0 };

    std::vector<Point> path = solve(maze, '#', start, end);

    for (auto const &[x, y] : path) {
        cout << y << " - " << x << endl;
    }

    cout << endl;
}
