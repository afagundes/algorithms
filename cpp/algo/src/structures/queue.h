#ifndef QUEUE_H
#define QUEUE_H

template <typename T> struct QNode {
    T value;
    QNode<T>* next;
};

template <typename T> class Queue {
    int length;
    QNode<T>* head;
    QNode<T>* tail;

public:
    Queue(): length(0), head(nullptr), tail(nullptr) {}

    ~Queue() {
        while (this->head != nullptr) {
            const QNode<T>* temp = head;
            head = head->next;
            delete temp;
        }
    }

    T peek() {
        if (this->head == nullptr) {
            throw std::runtime_error("A fila está vazia");
        }

        return this->head->value;
    }

    void enqueue(T value) {
        auto node = new QNode<T>;
        node->value = value;
        node->next = nullptr;

        ++this->length;

        if (this->tail == nullptr) {
            this->head = this->tail = node;
            return;
        }

        this->tail->next = node;
        this->tail = node;
    }

    T dequeue() {
        if (this->head == nullptr) {
            throw std::runtime_error("A fila está vazia");
        }

        QNode<T>* node = this->head;
        T value = node->value;

        this->head = this->head->next;
        delete node;

        if (this->head == nullptr) {
            this->tail = nullptr;
        }

        --this->length;

        return value;
    }
};

#endif //QUEUE_H
