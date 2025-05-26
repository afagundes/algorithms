cmake_minimum_required(VERSION 3.30)
project(c_ansi_algo C)

set(CMAKE_C_STANDARD 11)

add_executable(c_ansi_algo main.c
        quick-sort.h
        quick-sort.c
        binary-tree.c
        binary-tree.h
        utils.h
        binary-tree-queue.c
        binary-tree-queue.h
)
