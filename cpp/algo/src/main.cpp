#include "main.h"
#include "utils/program-info.h"

void simpleAlgorithms() {
    binarySearch();
    twoCrystalBallsProblem();
    bubbleSort();
}

void recursion() {
    solveMaze();
    quicksortExample();
}

void structures() {
    queueExample();
    stackExample();
    arrayListExample();
    ringBufferExample();
    stringBuilderExample();
    linkedListExample();
    simpleBinaryTreeExample();
}

int main() {
    simpleAlgorithms();
    recursion();
    structures();

    printProgramInfo();

    return 0;
}
