#include <iostream>
#include "LinkedList.h"
#include "../main.h"

using namespace std;

void linkedListExample() {
    cout << "LinkedList" << endl;

    LinkedList<int> list;
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);

    list.insertAt(4, 0);

    cout << "Size: " << list.size() << endl;
    cout << "First: " << list.get() << endl;

    while (list.size() > 0) {
        cout << list.remove() << endl;
    }

    cout << "Size: " << list.size() << endl;

    cout << endl << "Iterator" << endl;
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);

    for (const auto value : list) {
        cout << value << endl;
    }

    cout << endl << "Iterator directly: " << endl;
    for (auto it = list.begin(); it != list.end(); ++it) {
        cout << *it << endl;
    }

    cout << endl;
}
