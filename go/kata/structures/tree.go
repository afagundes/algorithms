package structures

type TNode struct {
	data  int
	left  *TNode
	right *TNode
}

type Tree struct {
	root *TNode
	size int
}

func NewTree() *Tree {
	return &Tree{}
}

func (t *Tree) Insert(value int) {
	t.root = insertNode(t.root, value)
	t.size++
}

func insertNode(node *TNode, value int) *TNode {
	if node == nil {
		return &TNode{data: value}
	}

	if value <= node.data {
		node.left = insertNode(node.left, value)
	} else {
		node.right = insertNode(node.right, value)
	}

	return node
}

// AsArray performs a Depth-First search "in-order" algorithm that returns an ordered array
func (t *Tree) AsArray() []int {
	path := make([]int, 0, t.size)
	t.walkInOrder(t.root, &path)
	return path
}

func (t *Tree) walkInOrder(node *TNode, path *[]int) {
	if node == nil {
		return
	}

	t.walkInOrder(node.left, path)
	*path = append(*path, node.data)
	t.walkInOrder(node.right, path)
}

// SearchBreadthFirst performs a Breadth-First search algorithm
func (t *Tree) SearchBreadthFirst(target int) bool {
	if t.size == 0 {
		return false
	}

	queue := NewQueue[*TNode]()
	queue.Enqueue(t.root)

	for queue.Size() > 0 {
		node := queue.Dequeue()

		if node == nil {
			continue
		}

		if node.data == target {
			return true
		}

		queue.Enqueue(node.left)
		queue.Enqueue(node.right)
	}

	return false
}

func (t *Tree) Search(target int) bool {
	return searchNode(t.root, target)
}

func searchNode(node *TNode, target int) bool {
	if node == nil {
		return false
	}

	if node.data == target {
		return true
	}

	if target <= node.data {
		return searchNode(node.left, target)
	} else {
		return searchNode(node.right, target)
	}
}

func Compare(a *Tree, b *Tree) bool {
	return compareNodes(a.root, b.root)
}

// compareNodes performs a depth-first comparison
// depth-first algorithms preserve the tree structure
func compareNodes(a *TNode, b *TNode) bool {
	if a == nil && b == nil {
		return true
	}

	if a == nil || b == nil {
		return false
	}

	if a.data != b.data {
		return false
	}

	return compareNodes(a.left, b.left) && compareNodes(a.right, b.right)
}
