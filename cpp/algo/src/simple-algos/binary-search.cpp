#include "iostream"
#include "../main.h"

using namespace std;

int search(const std::vector<int> &arr, const int &target) {
    int left = 0;
    auto right = static_cast<int>(arr.size());

    while (left < right) {
        const auto mid = static_cast<int>(floor(left + (right - left) / 2));
        const int value = arr[mid];

        if (value == target) {
            return mid;
        }

        if (value > target) {
            right = mid;
        }
        else {
            left = mid + 1;
        }
    }

    return -1;
}

void binarySearch() {
    cout << std::boolalpha;
    cout << "Binary Search" << endl;

    const std::vector arr = {0, 1, 2, 5, 9, 12, 15, 19, 23, 45, 94, 112, 443, 9};

    cout << "O número 09 está na posição 04: " << (search(arr, 9) == 4) << endl;
    cout << "O número 45 está na posição 10: " << (search(arr, 45) == 9) << endl;
    cout << "O número 20 está na posição -1: " << (search(arr, 20) == -1) << endl << endl;
}
