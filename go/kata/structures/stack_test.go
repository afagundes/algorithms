package structures

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestStack(t *testing.T) {
	stack := NewStack[int]()
	assert.Equal(t, 0, stack.Peek())
	assert.Equal(t, 0, stack.Pop())

	stack.Push(1)
	stack.Push(2)
	stack.Push(3)
	stack.Push(4)
	stack.Push(5)

	assert.Equal(t, 5, stack.Peek())
	assert.Equal(t, 5, stack.Peek())
	assert.Equal(t, 5, stack.Pop())
	assert.Equal(t, 4, stack.Pop())
	assert.Equal(t, 3, stack.Pop())
	assert.Equal(t, 2, stack.Pop())
	assert.Equal(t, 1, stack.Pop())
	assert.Equal(t, 0, stack.Pop())
	assert.Equal(t, 0, stack.Pop())

	stack.Push(10)
	stack.Push(20)
	stack.Push(30)
	stack.Push(40)
	stack.Push(50)

	assert.Equal(t, 50, stack.Pop())
	assert.Equal(t, 40, stack.Pop())
	assert.Equal(t, 30, stack.Pop())

	stack.Push(100)

	assert.Equal(t, 100, stack.Pop())
	assert.Equal(t, 20, stack.Pop())
	assert.Equal(t, 10, stack.Pop())
	assert.Equal(t, 0, stack.Pop())
}
