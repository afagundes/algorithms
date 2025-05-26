package algorithms

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestBinarySearch(t *testing.T) {
	input := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 22, 37, 44, 56, 92, 101}

	tests := []struct {
		target   int
		expected bool
	}{
		{target: 8, expected: true},
		{target: 1, expected: true},
		{target: 10, expected: true},
		{target: 23, expected: false},
		{target: 24, expected: false},
		{target: 22, expected: true},
		{target: 101, expected: true},
		{target: 102, expected: false},
		{target: 36, expected: false},
		{target: 37, expected: true},
	}

	for _, tt := range tests {
		result := BinarySearch(input, tt.target)
		assert.Equal(t, tt.expected, result, fmt.Sprintf("want %d = %v; got = %v", tt.target, tt.expected, result))
	}

}
