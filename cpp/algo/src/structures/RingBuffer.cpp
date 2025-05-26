#include "RingBuffer.h"

#include "iostream"
#include "../main.h"

using namespace std;

void ringBufferExample() {
    cout << "Ring Buffer" << endl;

    try {
        RingBuffer<int> ring(4, false);

        ring.write(1);
        ring.write(2);
        ring.write(3);
        ring.write(4);

        while (ring.canRead()) {
            cout << ring.read() << endl;
        }

        ring.write(5);
        ring.write(6);

        while (ring.canRead()) {
            cout << ring.read() << endl;
        }

        ring.write(100);
        ring.write(200);
        ring.write(300);
        ring.write(400);

        while (ring.canRead()) {
            cout << ring.read() << endl;
        }

        if (int value; ring.tryWrite(12) && ring.tryRead(value)) {
            cout << value << endl;
        }
    }
    catch ([[maybe_unused]] const std::exception& e) {
        // unused
        std::cerr << "Erro: " << e.what() << endl;
    }

    cout << endl;
}
