package structures

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestRingBuffer(t *testing.T) {
	rb := NewRingBuffer[int](5)
	value, err := rb.Read()

	assert.Zero(t, value)
	assert.NotNil(t, err)
	assert.Equal(t, "no data to read", err.Error())

	rb.Write(1)
	rb.Write(2)
	rb.Write(3)
	rb.Write(4)
	rb.Write(5)

	readAndAssert(t, 1, rb)
	readAndAssert(t, 2, rb)
	readAndAssert(t, 3, rb)
	readAndAssert(t, 4, rb)
	readAndAssert(t, 5, rb)

	// return to start
	readAndAssert(t, 1, rb)
	readAndAssert(t, 2, rb)
	readAndAssert(t, 3, rb)
	readAndAssert(t, 4, rb)
	readAndAssert(t, 5, rb)

	// one last time
	readAndAssert(t, 1, rb)
	readAndAssert(t, 2, rb)
	readAndAssert(t, 3, rb)
	readAndAssert(t, 4, rb)
	readAndAssert(t, 5, rb)

	// override first and second values (write index is pointing to the first value at this time)
	rb.Write(10)
	rb.Write(100)

	readAndAssert(t, 10, rb)
	readAndAssert(t, 100, rb)
	readAndAssert(t, 3, rb)
	readAndAssert(t, 4, rb)
	readAndAssert(t, 5, rb)
}

func readAndAssert(t *testing.T, expected int, rb *RingBuffer[int]) {
	read, err := rb.Read()
	if err != nil {
		t.Errorf("Error: %v", err)
	}

	assert.Equal(t, expected, read)
}
