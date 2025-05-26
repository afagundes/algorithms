//
// Created by Archimedes Fagundes Junior on 18/02/25.
//

#ifndef STRING_UTILS_H
#define STRING_UTILS_H
#include <string>

struct index_out_of_bounds final : std::exception {};

std::string printVector(const std::vector<int>& vec);

#endif //STRING_UTILS_H
