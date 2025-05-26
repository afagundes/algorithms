#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include "binary-tree.h"
#include "binary-tree-queue.h"
#include "utils.h"

struct Tree * tree_insert_node(struct Tree *curr, const int value) {
    if (curr == NULL) {
        struct Tree *node = malloc(sizeof(struct Tree));
        node->value = value;
        node->size = 0;

        return node;
    }

    if (value <= curr->value) {
        curr->left = tree_insert_node(curr->left, value);
    }
    else {
        curr->right = tree_insert_node(curr->right, value);
    }

    return curr;
}

struct Tree *tree_insert(struct Tree *root, const int value) {
    root = tree_insert_node(root, value);
    ++root->size;

    return root;
}

// ToArray depth-first in-order
int tree_to_array_depth_first(const struct Tree *curr, int *path, int index) {
    assert(path);

    if (!curr) {
        return index;
    }

    index = tree_to_array_depth_first(curr->left, path, index);
    path[index++] = curr->value;
    index = tree_to_array_depth_first(curr->right, path, index);

    return index;
}

int *tree_to_array(const struct Tree *root, int *path) {
    tree_to_array_depth_first(root, path, 0);
    return path;
}

// HasValue breadth-first search
bool tree_has_value(struct Tree *root, const int value) {
    assert(root);

    struct SimpleTreeQueue *queue = malloc(sizeof(struct SimpleTreeQueue));
    tree_queue_insert(queue, root);

    while (tree_queue_size(queue) > 0) {
        struct Tree *curr = tree_queue_poll(queue);

        if (!curr) {
            continue;
        }

        if (curr->value == value) {
            return true;
        }

        tree_queue_insert(queue, curr->left);
        tree_queue_insert(queue, curr->right);
    }

    free(queue);
    return false;
}

void print_tree(const struct Tree *root) {
    assert(root);

    int *path = malloc(root->size);
    int *values = tree_to_array(root, path);

    printf("[ ");
    printf("%d", values[0]);

    for (int i = 1; i < root->size; i++) {
        printf(", %d", values[i]);
    }

    printf(" ]");

    free(values);
}

// Free depth-first post-order
void free_node(struct Tree *curr) {
    if (!curr) return;

    free_node(curr->left);
    free_node(curr->right);
    free(curr);
}

void free_tree(struct Tree *root) {
    assert(root);
    free_node(root);
}

void binaryTreeExample() {
    printf("Binary tree example\n");

    struct Tree *tree = NULL;
    tree = tree_insert(tree, 10);
    tree = tree_insert(tree, 5);
    tree = tree_insert(tree, 1);
    tree = tree_insert(tree, 9);
    tree = tree_insert(tree, 3);
    tree = tree_insert(tree, 4);
    tree = tree_insert(tree, 15);

    print_tree(tree);
    printf("\n\n");

    printf("has 1: %s\n", bool_to_str(tree_has_value(tree, 1)));
    printf("has 5: %s\n", bool_to_str(tree_has_value(tree, 5)));
    printf("has 6: %s\n", bool_to_str(tree_has_value(tree, 6)));
    printf("has 12: %s\n", bool_to_str(tree_has_value(tree, 12)));
    printf("has 15: %s\n", bool_to_str(tree_has_value(tree, 15)));

    free_tree(tree);
}
