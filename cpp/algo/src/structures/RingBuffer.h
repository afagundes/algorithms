#ifndef RINGBUFFER_H
#define RINGBUFFER_H
#include <vector>

template<typename T>
class RingBuffer {
    std::vector<T> buffer;
    std::vector<bool> readFlags;
    size_t length;
    size_t readIndex;
    size_t writeIndex;
    bool overrideUnread;

public:
    explicit RingBuffer(size_t length, const bool overrideUnread = false):
        buffer(length), readFlags(length, true), length(length), readIndex(0), writeIndex(0), overrideUnread(overrideUnread)
    {
        if (length == 0) {
            throw std::invalid_argument("Length must be greater than zero");
        }
    }

    void write(const T& value) {
        if (!overrideUnread && !readFlags[writeIndex]) {
            throw std::runtime_error("Cannot override unread value");
        }

        buffer[writeIndex] = value;
        readFlags[writeIndex] = false;
        writeIndex = (writeIndex + 1) % length;
    }

    [[nodiscard]] bool tryWrite(const T& value) {
        if (!overrideUnread && !readFlags[writeIndex]) {
            return false;
        }

        try {
            write(value);
        }
        catch (std::exception& e) {
            return false;
        }

        return true;
    }

    T read() {
        if (!canRead()) {
            throw std::runtime_error("No new values to read");
        }

        T value = buffer[readIndex];
        readFlags[readIndex] = true;
        readIndex = (readIndex + 1) % length;

        return value;
    }

    bool tryRead(T& value) {
        if (!canRead()) {
            return false;
        }

        try {
            value = read();
        }
        catch (std::exception& e) {
            return false;
        }

        return true;
    }

    [[nodiscard]] bool canRead() const {
        return !readFlags[readIndex];
    }
};

#endif //RINGBUFFER_H
