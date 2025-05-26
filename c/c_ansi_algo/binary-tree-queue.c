//
// Created by Archimedes Fagundes Junior on 11/03/25.
//

#include "binary-tree-queue.h"
#include <assert.h>
#include <stdlib.h>

void tree_queue_insert(struct SimpleTreeQueue *queue, struct Tree *value) {
    assert(queue);

    ++queue->size;

    struct SimpleNode *node = malloc(sizeof(struct SimpleNode));
    node->value = value;

    if (!queue->tail) {
        queue->head = queue->tail = node;
        return;
    }

    queue->tail->next = node;
    queue->tail = node;
}

struct Tree *tree_queue_poll(struct SimpleTreeQueue *queue) {
    assert(queue);

    if (!queue->head) {
        return 0;
    }

    struct SimpleNode *node = queue->head;
    struct Tree *value = node->value;

    queue->head = queue->head->next;

    if (!queue->head) {
        queue->tail = NULL;
    }

    --queue->size;

    node->next = NULL;
    free(node);

    return value;
}

int tree_queue_size(struct SimpleTreeQueue *queue) {
    return !queue ? 0 : queue->size;
}
