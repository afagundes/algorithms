#include "iostream"
#include "../main.h"

using namespace std;

// O(nˆ2)
void sort(int arr[], size_t size) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                const int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

string print(int arr[], size_t size) {
    string str = "[ ";
    str += std::to_string(arr[0]);

    for (int i = 1; i < size; i++) {
        str += ", " + std::to_string(arr[i]);
    }

    str += " ]";

    return str;
}

void bubbleSort() {
    cout << "Bubble sort" << endl;

    int arr[] = {9, 1, 15, 16, 1000, 10, 50, 13, 12, 27, 39, 40, 44, 43, 78, 25, 1, 0};
    constexpr size_t size =  sizeof(arr) / sizeof(arr[0]);

    cout << "Antes:  " << print(arr, size) << endl;

    sort(arr, size);
    cout << "Depois: " << print(arr, size) << endl << endl;
}
