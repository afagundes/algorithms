package algorithms

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestTwoCrystalBalls(t *testing.T) {
	tests := []struct {
		distances []bool
		expected  int
	}{
		{
			distances: []bool{false, false, false, false, false, false, false, false, false, false, false, true, true, true, true},
			expected:  11,
		},
		{
			distances: []bool{false, false, false, true, true},
			expected:  3,
		},
		{
			distances: []bool{false, false, true},
			expected:  2,
		},
		{
			distances: []bool{false, false, false},
			expected:  -1,
		},
		{
			distances: []bool{true, true, true},
			expected:  0,
		},
		{
			distances: []bool{false, true},
			expected:  1,
		},
		{
			distances: []bool{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true},
			expected:  19,
		},
	}

	for _, tt := range tests {
		result := TwoCrystalBalls(tt.distances)
		assert.Equal(t, tt.expected, result, fmt.Sprintf("expected %d; got %d", tt.expected, result))
	}
}
