package structures

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestQueue(t *testing.T) {
	queue := NewQueue[int]()
	assert.Equal(t, 0, queue.Peek())
	assert.Equal(t, 0, queue.Dequeue())

	queue.Enqueue(1)
	queue.Enqueue(2)
	queue.Enqueue(3)
	queue.Enqueue(4)
	queue.Enqueue(5)

	assert.Equal(t, 1, queue.Peek())
	assert.Equal(t, 1, queue.Peek())
	assert.Equal(t, 1, queue.Peek())

	assert.Equal(t, 1, queue.Dequeue())
	assert.Equal(t, 2, queue.Dequeue())
	assert.Equal(t, 3, queue.Dequeue())
	assert.Equal(t, 4, queue.Dequeue())
	assert.Equal(t, 5, queue.Dequeue())
	assert.Equal(t, 0, queue.Dequeue())
	assert.Equal(t, 0, queue.Dequeue())
	assert.Equal(t, 0, queue.Dequeue())

	queue.Enqueue(5)
	queue.Enqueue(4)
	queue.Enqueue(3)
	queue.Enqueue(2)
	queue.Enqueue(1)

	assert.Equal(t, 5, queue.Dequeue())
	assert.Equal(t, 4, queue.Dequeue())
	assert.Equal(t, 3, queue.Dequeue())
	assert.Equal(t, 2, queue.Dequeue())
	assert.Equal(t, 1, queue.Dequeue())
	assert.Equal(t, 0, queue.Dequeue())
}
