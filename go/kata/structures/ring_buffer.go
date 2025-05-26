package structures

import "errors"

type RingBuffer[T any] struct {
	data       []T
	allocated  []bool
	capacity   int
	readIndex  int
	writeIndex int
}

func NewRingBuffer[T any](capacity int) *RingBuffer[T] {
	return &RingBuffer[T]{
		data:       make([]T, capacity),
		allocated:  make([]bool, capacity),
		capacity:   capacity,
		readIndex:  0,
		writeIndex: 0,
	}
}

func (r *RingBuffer[T]) Write(data T) {
	r.data[r.writeIndex] = data
	r.allocated[r.writeIndex] = true
	r.writeIndex = (r.writeIndex + 1) % r.capacity
}

func (r *RingBuffer[T]) Read() (T, error) {
	if !r.allocated[r.readIndex] {
		var zero T
		return zero, errors.New("no data to read")
	}

	data := r.data[r.readIndex]
	r.readIndex = (r.readIndex + 1) % r.capacity

	return data, nil
}
