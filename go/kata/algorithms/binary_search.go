package algorithms

import (
	"math"
)

func BinarySearch(arr []int, target int) bool {
	left := 0
	right := len(arr)

	for left < right {
		mid := int(math.Floor(float64(left + (right-left)/2)))
		value := arr[mid]

		if value == target {
			return true
		}

		if value > target {
			right = mid
		} else {
			left = mid + 1
		}
	}

	return false
}
