#include <sstream>
#include "string-utils.h"

std::string printVector(const std::vector<int>& vec) {
    if (vec.empty()) {
        throw index_out_of_bounds();
    }

    std::stringstream buffer;
    buffer << "[ " << vec[0];

    for (int i = 1; i < vec.size(); i++) {
        buffer << ", " << vec[i];
    }

    buffer << " ]";

    return buffer.str();
}
