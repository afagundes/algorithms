package sort

func QuickSort(arr *[]int) {
	sort(arr, 0, len(*arr)-1)
}

func sort(arr *[]int, left int, right int) {
	if left >= right {
		return
	}

	pivot := partition(*arr, left, right)

	sort(arr, left, pivot-1)
	sort(arr, pivot+1, right)
}

func partition(arr []int, left int, right int) int {
	pivot := arr[right]
	index := left - 1

	for i := left; i < right; i++ {
		if arr[i] <= pivot {
			index++

			temp := arr[i]
			arr[i] = arr[index]
			arr[index] = temp
		}
	}

	index++
	arr[right] = arr[index]
	arr[index] = pivot

	return index
}
