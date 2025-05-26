#ifndef ARRAYLIST_H
#define ARRAYLIST_H
#include <algorithm>
#include <iostream>

template <typename T>
class ArrayList {
    T* data;
    int length;
    int capacity;

    void resize() {
        this->capacity *= 2;
        T* newArr = new T[this->capacity];

        for (int i = 0; i < this->capacity / 2; i++) {
            newArr[i] = std::move(this->data[i]);
        }

        delete[] this->data;
        this->data = newArr;
    }

public:
    explicit ArrayList(const int cap = 10) {
        length = 0;
        capacity = cap;
        data = new T[capacity];
    }

    ArrayList(const ArrayList& list) {
        std::cout << "Criando um novo ArrayList a partir do anterior" << std::endl;

        length = list.length;
        capacity = list.capacity;
        data = new T[capacity];

        for (int i = 0; i < length; i++) {
            data[i] = std::move(list.data[i]);
        }
    }

    ~ArrayList() {
        delete[] data;
    }

    void prepend(T value) {
        if (this->length == this->capacity) {
            this->resize();
        }

        for (int i = this->length - 1; i >= 0; --i) {
            this->data[i + 1] = this->data[i];
        }

        this->data[0] = std::move(value);
        ++this->length;
    }

    void insertAt(int idx, T value) {
        if (this->length == this->capacity) {
            resize();
        }

        for (int i = this->length - 1; i >= idx; --i) {
            this->data[i + 1] = this->data[i];
        }

        this->data[idx] = std::move(value);
        ++this->length;
    }

    void append(T value) {
        if (this->length == this->capacity) {
            this->resize();
        }

        this->data[this->length++] = std::move(value);
    }

    T get(int idx) {
        if (idx >= this->length) {
            std::cerr << "Index out of bounds\n";
            return T();
        }

        return this->data[idx];
    }

    T removeAt(int idx) {
        if (idx <= 0 || idx >= length) {
            std::cerr << "Index out of bounds\n";
            return T();
        }

        T value = this->data[idx];

        for (int i = idx; i < length - 1; ++i) {
            this->data[i] = std::move(this->data[i + 1]);
        }

        --this->length;

        return value;
    }

    [[nodiscard]] int size() const {
        return this->length;
    }

    [[nodiscard]] int getCapacity() const {
        return this->capacity;
    }
};

#endif //ARRAYLIST_H
