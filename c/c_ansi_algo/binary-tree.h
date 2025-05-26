#ifndef BINARY_TREE_H
#define BINARY_TREE_H
#include <stdbool.h>

struct Tree {
    int value;
    int size;
    struct Tree *left;
    struct Tree *right;
};

struct Tree *tree_insert(struct Tree *root, int value);

int *tree_to_array(const struct Tree *root, int *path);

bool tree_has_value(struct Tree *root, int value);

void free_tree(struct Tree *root);

void binaryTreeExample();

#endif //BINARY_TREE_H
