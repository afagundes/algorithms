#ifndef BINARY_TREE_QUEUE_H
#define BINARY_TREE_QUEUE_H
#include "binary-tree.h"

struct SimpleNode {
    struct Tree *value;
    struct SimpleNode *next;
};

struct SimpleTreeQueue {
    int size;
    struct SimpleNode *head;
    struct SimpleNode *tail;
};

void tree_queue_insert(struct SimpleTreeQueue *queue, struct Tree *value);

struct Tree *tree_queue_poll(struct SimpleTreeQueue *queue);

int tree_queue_size(struct SimpleTreeQueue *queue);

#endif //BINARY_TREE_QUEUE_H
