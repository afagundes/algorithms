#include <iostream>
#include "SimpleBinaryTree.h"
#include "../main.h"

void simpleBinaryTreeExample() {
    std::cout << "Simple binary tree" << std::endl;

    SimpleBinaryTree tree;
    tree.insert(5);
    tree.insert(1);
    tree.insert(4);
    tree.insert(100);
    tree.insert(-1);
    tree.insert(2);
    tree.insert(9);
    tree.insert(3);

    std::cout << tree.toString() << std::endl;

    std::cout << "Has 1: " << tree.hasValue(1) << std::endl;
    std::cout << "Has 5: " << tree.hasValue(5) << std::endl;
    std::cout << "Has 9: " << tree.hasValue(9) << std::endl;
    std::cout << "Has 101: " << tree.hasValue(101) << std::endl;
    std::cout << "Has 112: " << tree.hasValue(112) << std::endl;
    std::cout << "Has 44: " << tree.hasValue(44) << std::endl;
    std::cout << "Has 100: " << tree.hasValue(100) << std::endl;
    
    std::cout << std::endl;
}
