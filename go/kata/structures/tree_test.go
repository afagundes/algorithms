package structures

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestTree(t *testing.T) {
	tree := NewTree()
	tree.Insert(5)
	tree.Insert(4)
	tree.Insert(3)
	tree.Insert(2)
	tree.Insert(1)
	tree.Insert(10)
	tree.Insert(8)
	tree.Insert(7)
	tree.Insert(6)
	tree.Insert(9)

	sortedArr := tree.AsArray()

	assert.Equal(t, 10, cap(sortedArr), "capacity must be 10")
	assert.Equal(t, 10, len(sortedArr), "length must be 10")

	expected := [10]int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
	for i, value := range sortedArr {
		assert.Equal(t, expected[i], value, fmt.Sprintf("want %d; got %d\n", expected[i], value))
	}

	assert.Equal(t, true, tree.SearchBreadthFirst(5))
	assert.Equal(t, true, tree.SearchBreadthFirst(1))
	assert.Equal(t, true, tree.SearchBreadthFirst(10))
	assert.Equal(t, true, tree.SearchBreadthFirst(9))
	assert.Equal(t, false, tree.SearchBreadthFirst(11))
	assert.Equal(t, false, tree.SearchBreadthFirst(15))

	assert.Equal(t, true, tree.Search(5))
	assert.Equal(t, true, tree.Search(1))
	assert.Equal(t, true, tree.Search(10))
	assert.Equal(t, true, tree.Search(9))
	assert.Equal(t, false, tree.Search(11))
	assert.Equal(t, false, tree.Search(15))
}

func TestCompare(t *testing.T) {
	a := NewTree()
	a.Insert(5)
	a.Insert(3)
	a.Insert(7)
	a.Insert(9)

	b := NewTree()
	b.Insert(5)
	b.Insert(3)
	b.Insert(7)
	b.Insert(9)

	assert.Equal(t, true, Compare(a, b))

	a = NewTree()
	a.Insert(5)
	a.Insert(3)
	a.Insert(7)
	a.Insert(9)

	b = NewTree()
	b.Insert(5)
	b.Insert(3)
	b.Insert(7)
	b.Insert(5)

	assert.Equal(t, false, Compare(a, b))
}
