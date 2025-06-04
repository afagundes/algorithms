package algorithms

func TwoSum(nums []int, target int) [2]int {
	memo := make(map[int]int)

	for i, num := range nums {
		diff := target - num

		if seenIndex, ok := memo[diff]; ok {
			return [2]int{seenIndex, i}
		}

		memo[num] = i
	}

	return [2]int{-1, -1}
}
