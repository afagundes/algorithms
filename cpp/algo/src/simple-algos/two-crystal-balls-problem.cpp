#include "iostream"
#include "../main.h"

using namespace std;

template <std::size_t SIZE>
int problem(const std::array<bool, SIZE> &distances) {
    const auto size = static_cast<int>(distances.size());
    const auto step = static_cast<int>(floor(sqrt(size)));

    int i = step;
    for (; i < size; i += step) {
        if (distances[i]) {
            break;
        }
    }

    i -= step;

    for (int j = 0; j <= step && i < size; j++, i++) {
        if (distances[i]) {
            return i;
        }
    }

    return -1;
}

void twoCrystalBallsProblem() {
    cout << "Two Crystal Balls Problem" << endl;

    constexpr std::array distances = { false, false, false, false, false, true, true, true, true, true };

    cout << "A bola quebra a partir de " << problem(distances) + 1 << " metros" << endl << endl;
}
