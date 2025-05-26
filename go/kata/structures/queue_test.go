package structures

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestQueue(t *testing.T) {
	queue := NewQueue[int]()
	assert.Equal(t, 0, queue.peek())
	assert.Equal(t, 0, queue.dequeue())

	queue.enqueue(1)
	queue.enqueue(2)
	queue.enqueue(3)
	queue.enqueue(4)
	queue.enqueue(5)

	assert.Equal(t, 1, queue.peek())
	assert.Equal(t, 1, queue.peek())
	assert.Equal(t, 1, queue.peek())

	assert.Equal(t, 1, queue.dequeue())
	assert.Equal(t, 2, queue.dequeue())
	assert.Equal(t, 3, queue.dequeue())
	assert.Equal(t, 4, queue.dequeue())
	assert.Equal(t, 5, queue.dequeue())
	assert.Equal(t, 0, queue.dequeue())
	assert.Equal(t, 0, queue.dequeue())
	assert.Equal(t, 0, queue.dequeue())

	queue.enqueue(5)
	queue.enqueue(4)
	queue.enqueue(3)
	queue.enqueue(2)
	queue.enqueue(1)

	assert.Equal(t, 5, queue.dequeue())
	assert.Equal(t, 4, queue.dequeue())
	assert.Equal(t, 3, queue.dequeue())
	assert.Equal(t, 2, queue.dequeue())
	assert.Equal(t, 1, queue.dequeue())
	assert.Equal(t, 0, queue.dequeue())
}
