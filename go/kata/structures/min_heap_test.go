package structures

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestMinHeap(t *testing.T) {
	heap := NewMinHeap()
	assert.Zero(t, heap.Length)

	heap.insert(5)
	heap.insert(3)
	heap.insert(69)
	heap.insert(420)
	heap.insert(4)
	heap.insert(1)
	heap.insert(8)
	heap.insert(7)
	heap.insert(7)
	heap.insert(7)
	heap.insert(7)
	heap.insert(7)

	assert.Equal(t, 12, heap.Length)
	assert.Equal(t, 1, heap.delete())
	assert.Equal(t, 3, heap.delete())
	assert.Equal(t, 4, heap.delete())
	assert.Equal(t, 5, heap.delete())
	assert.Equal(t, 8, heap.Length)
	assert.Equal(t, 7, heap.delete())
	assert.Equal(t, 7, heap.delete())
	assert.Equal(t, 7, heap.delete())
	assert.Equal(t, 7, heap.delete())
	assert.Equal(t, 7, heap.delete())
	assert.Equal(t, 8, heap.delete())
	assert.Equal(t, 69, heap.delete())
	assert.Equal(t, 420, heap.delete())
	assert.Zero(t, heap.Length)
}
