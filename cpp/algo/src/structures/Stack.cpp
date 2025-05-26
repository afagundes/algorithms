#include "iostream"
#include "Stack.h"
#include "../main.h"

using namespace std;

void stackExample() {
    cout << "Stack" << endl;

    Stack<int> stack;

    cout << "Tamanho da stack: " << stack.size() << endl;

    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    stack.push(6);
    stack.push(7);
    stack.push(8);
    stack.push(9);
    stack.push(10);

    cout << stack.peek() << endl;

    while (stack.size() > 0) {
        cout << stack.pop() << endl;
    }

    cout << endl;
}
