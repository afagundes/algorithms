#include "iostream"
#include "queue.h"
#include "../main.h"

using namespace std;

int peek(Queue<int> &queue) {
    try {
        return queue.peek();
    }
    catch (std::runtime_error &err) {
        cout << err.what() << endl;
        return -1;
    }
}

void queueExample() {
    cout << "Queue" << endl;

    Queue<int> queue;
    peek(queue);

    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);
    queue.enqueue(7);
    queue.enqueue(8);
    queue.enqueue(9);
    queue.enqueue(10);

    cout << peek(queue) << endl;

    try {
        int value;
        while ((value = queue.dequeue()) > -1) {
            cout << value << endl;
        }
    }
    catch (std::runtime_error& err) {
        cout << err.what() << endl;
    }

    cout << endl;
}
