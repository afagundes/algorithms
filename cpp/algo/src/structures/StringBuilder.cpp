#include <iostream>
#include "StringBuilder.h"
#include "../main.h";

using namespace std;

void stringBuilderExample() {
    cout << "StringBuilder" << endl;

    const auto builder = new StringBuilder(10);
    builder
        ->append("This ")
        ->append("is ")
        ->append("a ")
        ->append("string ")
        ->append("constructed ")
        ->append("using ")
        ->append("StringBuilder");

    cout << "Size " << builder->size() << endl;
    cout << "Result: " << builder->toString() << endl;
    delete builder;

    StringBuilder otherBuilder;
    otherBuilder += "this is another ";
    otherBuilder += "String constructed by StringBuilder";

    cout << "Other: " << otherBuilder.toString() << endl;
    cout << "Char at index 23 is " << otherBuilder.charAt(23) << endl;
    cout << "Subsequence: " << otherBuilder.subsequence(8, 22) << endl;

    cout << endl;
}
