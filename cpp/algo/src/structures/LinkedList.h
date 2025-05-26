#ifndef LINKEDLIST_H
#define LINKEDLIST_H

template <typename T>
class LinkedList {
    // Inner class
    class Node {
    public:
        T value = T();
        std::shared_ptr<Node> next = nullptr;
    };

    std::shared_ptr<Node> head;
    int length;

public:
    LinkedList(): head(nullptr), length(0) {}

    ~LinkedList() {
        clear();
    }

    [[nodiscard]] int size() const {
        return length;
    }

    [[nodiscard]] bool isEmpty() const {
        return length == 0;
    }

    void add(const T& value) {
        auto node = std::make_shared<Node>();
        node->value = value;

        ++this->length;

        if (head == nullptr) {
            this->head = node;
            return;
        }

        auto curr = head;
        while (curr->next != nullptr) {
            curr = curr->next;
        }

        curr->next = node;
    }

    void insertAt(const int index, const T& value) {
        if (index < 0 || index > length) {
            throw std::runtime_error("Index out of bounds");
        }

        auto node = std::make_shared<Node>();
        node->value = value;

        if (index == 0) {
            node->next = this->head;
            this->head = node;
        }
        else {
            auto curr = head;
            auto prev = head;

            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr->next;
            }

            prev->next = node;
            node->next = curr;
        }

        length++;
    }

    T get() const {
        if (isEmpty()) {
            return T();
        }

        return head->value;
    }

    T remove() {
        if (isEmpty()) {
            throw std::runtime_error("List is empty");
        }

        T value = head->value;
        head = head->next;
        length--;

        return value;
    }

    void clear() {
        head = nullptr;
        length = 0;
    }

    class Iterator {
        std::shared_ptr<Node> current;

    public:
        explicit Iterator(std::shared_ptr<Node> node): current(node) {}

        // Dereference operator (*)
        T& operator*() const {
            return current->value;
        }

        // Member access operator (->)
        T* operator->() const {
            return &current->value;
        }

        // Pre-increment (++it)
        Iterator& operator++() {
            current = current->next;
            return *this;
        }

        // Post-increment (it++)
        Iterator operator++(int) {
            Iterator temp = *this;
            current = current->next;
            return temp;
        }

        // Equality operator (==)
        bool operator==(const Iterator& other) const {
            return current == other.current;
        }

        // Inequality operator (!=)
        bool operator!=(const Iterator& other) const {
            return current != other.current;
        }
    };

    // Return an iterator to the beginning of the list
    Iterator begin() const {
        return Iterator(head);
    }

    // Return an iterator to the end of the list (nullptr)
    Iterator end() const {
        return Iterator(nullptr);
    }

};

#endif //LINKEDLIST_H
