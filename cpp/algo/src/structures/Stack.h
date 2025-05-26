#ifndef STACK_H
#define STACK_H

template <typename T> struct SNode {
    T value;
    SNode<T>* next;
};

template <typename T> class Stack {
    int length;
    SNode<T>* head;

public:
    Stack(): length(0), head(nullptr) {}

    T peek() {
        if (this->head == nullptr) {
            throw std::runtime_error("A pilha está vazia");
        }

        return this->head->value;
    }

    void push(T value) {
        auto node = new SNode<T>;
        node->value = value;
        node->next = this->head;

        this->head = node;

        ++this->length;
    }

    T pop() {
        if (this->head == nullptr) {
            throw std::runtime_error("A pilha está vazia");
        }

        const SNode<T>* node = this->head;
        T value = node->value;

        this->head = this->head->next;
        delete node;

        --this->length;

        return value;
    }

    [[nodiscard]] int size() const {
        return this->length;
    }
};

#endif //STACK_H
