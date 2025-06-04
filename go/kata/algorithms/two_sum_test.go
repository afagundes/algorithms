package algorithms

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestTwoSum(t *testing.T) {
	nums := []int{2, 7, 11, 15}
	target := 9
	result := TwoSum(nums, target)

	assert.Equal(t, [2]int{0, 1}, result)
}
