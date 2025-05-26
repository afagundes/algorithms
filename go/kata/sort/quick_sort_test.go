package sort

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestQuickSort(t *testing.T) {
	arr := []int{10, 5, 4, 13, 2, 12, 99, 44, 7, 14, 1, 9, 0, -1}
	QuickSort(&arr)
	assert.IsIncreasing(t, arr)
}
