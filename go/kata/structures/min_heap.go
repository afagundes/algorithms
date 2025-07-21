package structures

import "math"

const (
	bufferSize   = 10
	growthFactor = 1.5
)

type MinHeap struct {
	Length int
	data   []int
}

func NewMinHeap() *MinHeap {
	minHeap := &MinHeap{Length: 0}
	minHeap.allocate()

	return minHeap
}

func (m *MinHeap) allocate() {
	if m.Length == 0 {
		m.data = make([]int, bufferSize)
		return
	}

	newSize := int(math.Floor(float64(cap(m.data)) * growthFactor))
	newData := make([]int, newSize)
	copy(newData, m.data)

	m.data = newData
}

func (m *MinHeap) insert(value int) {
	if m.Length == cap(m.data) {
		m.allocate()
	}

	m.data[m.Length] = value
	m.heapifyUp(m.Length)
	m.Length++
}

func (m *MinHeap) delete() int {
	if m.Length == 0 {
		return -1
	}

	out := m.data[0]
	m.Length--

	if m.Length == 0 {
		return out
	}

	m.data[0] = m.data[m.Length]
	m.heapifyDown(0)

	return out
}

func (m *MinHeap) heapifyUp(index int) {
	if index <= 0 {
		return
	}

	parentIndex := getParentIndex(index)
	parentValue := m.data[parentIndex]
	value := m.data[index]

	if parentValue > value {
		m.data[index] = parentValue
		m.data[parentIndex] = value
		m.heapifyUp(parentIndex)
	}
}

func (m *MinHeap) heapifyDown(index int) {
	leftIndex := getLeftChildIndex(index)
	rightIndex := getRightChildIndex(index)

	if index >= m.Length || leftIndex >= m.Length {
		return
	}

	leftValue := m.data[leftIndex]
	rightValue := m.data[rightIndex]
	value := m.data[index]

	if leftValue >= rightValue && value > rightValue {
		m.data[index] = rightValue
		m.data[rightIndex] = value
		m.heapifyDown(rightIndex)
	} else if rightValue > leftValue && value > leftValue {
		m.data[index] = leftValue
		m.data[leftIndex] = value
		m.heapifyDown(leftIndex)
	}
}

func getParentIndex(index int) int {
	return (index - 1) / 2
}

func getLeftChildIndex(index int) int {
	return index*2 + 1
}

func getRightChildIndex(index int) int {
	return index*2 + 2
}
