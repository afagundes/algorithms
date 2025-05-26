#ifndef STRINGBUILDER_H
#define STRINGBUILDER_H
#include <vector>
#include <string>

using namespace std;

class StringBuilder {
    vector<string> buffer;
    size_t length;

public:
    explicit StringBuilder(const int capacity = 10): buffer(capacity), length(0) {}

    StringBuilder* append(const string &str) {
        buffer.push_back(str);
        length += str.size();
        return this;
    }

    StringBuilder* operator+=(const string &str) {
        return append(str);
    }

    [[nodiscard]] string toString() const {
        char result[length];
        int index = 0;

        for (const string& str : buffer) {
            for (const char i : str) {
                result[index++] = i;
            }
        }

        return {result, length};
    }

    [[nodiscard]] char charAt(const int idx) const {
        if (idx < 0 || idx >= length) {
            throw std::invalid_argument("Index out of bounds");
        }

        string::size_type charIndex = 0;

        for (const string& str : buffer) {
            if (idx > str.size() + charIndex - 1) {
                charIndex += str.size();
                continue;
            }

            for (const char i : str) {
                if (charIndex == idx) {
                    return i;
                }

                charIndex++;
            }
        }

        return 0;
    }

    [[nodiscard]] string subsequence(const int start, const int end) const {
        if (start < 0 || start >= length || end > length || end < 1 || end < start) {
            throw std::invalid_argument("Index out of bounds");
        }

        int index = 0;
        string::size_type charIndex = 0;

        const int resultLength = end - start;
        char result[resultLength];

        for (int i = 0; i < buffer.size() && charIndex < end; i++) {
            const string str = buffer[i];

            if (start > str.length() + charIndex - 1) {
                charIndex += str.length();
                continue;
            }

            for (const char character : str) {
                if (charIndex >= start) {
                    result[index++] = character;
                }

                charIndex++;
            }
        }

        return {result, static_cast<string::size_type>(resultLength)};
    }

    [[nodiscard]] size_t size() const {
        return length;
    }
};

#endif //STRINGBUILDER_H
