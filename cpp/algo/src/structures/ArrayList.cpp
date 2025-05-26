#include "ArrayList.h"

#include "iostream"
#include "../main.h"

using namespace std;

string print(ArrayList<int> &list) {
    string str = "[ ";
    str += std::to_string(list.get(0));

    for (int i = 1; i < list.size(); i++) {
        str += ", " + std::to_string(list.get(i));
    }

    str += " ]";

    return str;
}

void arrayListExample() {
    cout << "ArrayList" << endl;

    auto list = ArrayList<int>(3);
    cout << "Tamanho: " << list.size() << endl;
    cout << "Capacidade: " << list.getCapacity() << endl;

    list.append(1);
    list.append(2);
    list.append(3);
    list.append(4);
    list.append(5);

    cout << "Capacidade: " << list.getCapacity() << endl;
    cout << list.get(0) << endl;
    cout << list.get(1) << endl;
    cout << "Tamanho: " << list.size() << endl;
    cout << print(list) << endl;

    cout << "Removido elemento " << list.removeAt(1) << endl;
    cout << "Tamanho: " << list.size() << endl;
    cout << "Primeiro elemento " << list.get(0) << endl;
    cout << print(list) << endl;

    list.prepend(10);
    cout << print(list) << endl;
    cout << "Primeiro elemento " << list.get(0) << endl;
    cout << "Ultimo elemento " << list.get(list.size() - 1) << endl;

    list.insertAt(1, 999);
    cout << print(list) << endl;

    list.insertAt(3, 2);
    cout << print(list) << endl;

    list.append(6);
    cout << print(list) << endl;
    cout << "Tamanho: " << list.size() << endl;
    cout << "Capacidade " << list.getCapacity() << endl;

    auto newList = list;
    cout << "Nova lista " << print(newList) << endl;

    cout << endl;
}
