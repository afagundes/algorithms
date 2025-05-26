#include <stdio.h>
#include <string.h>
#include "quick-sort.h"

void print(const int* arr, const size_t size) {
    char buffer[255] = {0};

    strcpy(&buffer, "[ ");
    sprintf(&buffer[strlen(buffer)], "%d", arr[0]);

    for (size_t i = 1; i < size; i++) {
        sprintf(&buffer[strlen(buffer)], ", %d", arr[i]);
    }

    strcat(buffer, " ]");

    printf("%s\n", buffer);
}

size_t partition(int* arr, const size_t left, const size_t right) {
    const int pivot = arr[right];
    size_t index = left - 1;

    for (size_t i = left; i < right; i++) {
        if (arr[i] <= pivot) {
            ++index;
            const int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    ++index;
    arr[right] = arr[index];
    arr[index] = pivot;

    return index;
}

void sort(int* arr, const size_t left, const size_t right) {
    if (left >= right) {
        return;
    }

    const size_t pivot = partition(arr, left, right);

    sort(arr, left, pivot - 1);
    sort(arr, pivot + 1, right);
}

void quickSort(int* arr, const size_t size) {
    sort(arr, 0, size - 1);
}

void quickSortExample() {
    printf("Quicksort example\n");

    int arr[] = { 10, 1, 5, 4, 12, 9, 17, 44, 36, 2 };
    const size_t size = sizeof(arr) / sizeof(arr[0]);

    print(arr, size);
    quickSort(arr, size);
    print(arr, size);

    printf("\n");
}
