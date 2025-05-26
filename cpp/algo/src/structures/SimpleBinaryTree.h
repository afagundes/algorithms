//
// Created by Archimedes Fagundes Junior on 14/02/25.
//

#ifndef SIMPLEBINARYTREE_H
#define SIMPLEBINARYTREE_H
#include <string>
#include <sstream>
#include <iostream>
#include "../utils/string-utils.h"

template<typename T>
class MiniQueue {
    class MiniNode {
    public:
        T value;
        std::shared_ptr<MiniNode> next;

        explicit MiniNode(T value): value(std::move(value)) {
        }
    };

    size_t size = 0;
    std::shared_ptr<MiniNode> head;
    std::shared_ptr<MiniNode> tail;

public:
    void add(const T &value) {
        ++this->size;
        auto node = std::make_shared<MiniNode>(value);

        if (this->tail == nullptr) {
            this->head = this->tail = node;
            return;
        }

        this->tail->next = node;
        this->tail = node;
    }

    T poll() {
        if (this->head == nullptr) {
            throw std::runtime_error("queue is empty");
        }

        auto node = this->head;
        this->head = this->head->next;

        if (this->head == nullptr) {
            this->tail = nullptr;
        }

        --this->size;

        node->next = nullptr;
        return node->value;
    }

    [[nodiscard]] size_t getSize() const {
        return this->size;
    }
};

class SimpleBinaryTree {
    class Node {
    public:
        int value;
        std::shared_ptr<Node> left = nullptr;
        std::shared_ptr<Node> right = nullptr;

        explicit Node(const int value): value(value) {
        }
    };

    std::shared_ptr<Node> root = nullptr;

    std::shared_ptr<Node> insertNode(const std::shared_ptr<Node> &curr, const int &value) {
        if (curr == nullptr) {
            return std::make_shared<Node>(value);
        }

        if (value <= curr->value) {
            curr->left = insertNode(curr->left, value);
        } else {
            curr->right = insertNode(curr->right, value);
        }

        return curr;
    }

    // Depth-first in-order
    void toSortedVectorDepthFirst(const std::shared_ptr<Node> &curr, std::vector<int> &path) {
        if (curr == nullptr) {
            return;
        }

        toSortedVectorDepthFirst(curr->left, path);
        path.push_back(curr->value);
        toSortedVectorDepthFirst(curr->right, path);
    }

public:
    void insert(const int value) {
        this->root = this->insertNode(this->root, value);
    }

    std::string toString() {
        std::vector<int> path;
        this->toSortedVectorDepthFirst(this->root, path);
        return printVector(path);
    }

    [[nodiscard]] bool hasValue(const int value) const {
        MiniQueue<std::shared_ptr<Node> > queue;
        queue.add(this->root);

        while (queue.getSize() > 0) {
            auto curr = queue.poll();

            if (curr == nullptr) {
                continue;
            }

            if (curr->value == value) {
                return true;
            }

            queue.add(curr->left);
            queue.add(curr->right);
        }

        return false;
    }
};

#endif //SIMPLEBINARYTREE_H
