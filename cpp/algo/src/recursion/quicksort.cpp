#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

int partition(std::vector<int> &arr, const int left, const int right) {
    const int pivot = arr[right];
    int index = left - 1;

    for (int i = left; i < right; i++) {
        if (arr[i] <= pivot) {
            index++;
            const int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }

    index++;
    arr[right] = arr[index];
    arr[index] = pivot;

    return index;
}

void sort(std::vector<int> &arr, const int left, const int right) {
    if (left >= right) {
        return;
    }

    const int pivot = partition(arr, left, right);

    sort(arr, left, pivot - 1);
    sort(arr, pivot + 1, right);
}

void quicksort(std::vector<int> &arr) {
    sort(arr, 0, static_cast<int>(arr.size()) - 1);
}

string print(const std::vector<int> &arr) {
    stringstream buffer;

    buffer << "[ " << arr[0];

    for (const int str : arr) {
        buffer << ", " << str;
    }

    buffer << " ]";

    return buffer.str();
}

void quicksortExample() {
    cout << "Quick Sort" << endl;

    std::vector arr = { 4, 10, 15, 1, 14, 22, 8, 7, 19, 5 };
    cout << "Before: " << print(arr) << endl;

    quicksort(arr);

    cout << "After:  " << print(arr) << endl;
    cout << endl;
}
