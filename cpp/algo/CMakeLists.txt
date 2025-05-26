cmake_minimum_required(VERSION 3.30)
project(algo)

set(CMAKE_CXX_STANDARD 20)

# Main executable configuration
add_executable(algo src/main.cpp
        src/simple-algos/binary-search.cpp
        src/simple-algos/two-crystal-balls-problem.cpp
        src/main.h
        src/simple-algos/bubble-sort.cpp
        src/structures/queue.cpp
        src/structures/queue.h
        src/structures/Stack.cpp
        src/structures/Stack.h
        src/structures/ArrayList.cpp
        src/structures/ArrayList.h
        src/utils/program-info.h
        src/utils/program-info.cpp
        src/structures/RingBuffer.cpp
        src/structures/RingBuffer.h
        src/recursion/maze-solver.cpp
        src/recursion/quicksort.cpp
        src/structures/StringBuilder.cpp
        src/structures/StringBuilder.h
        src/structures/LinkedList.cpp
        src/structures/LinkedList.h
        src/structures/SimpleBinaryTree.cpp
        src/structures/SimpleBinaryTree.h
        src/utils/string-utils.h
        src/utils/string-utils.cpp
)
