package structures

type SNode[T any] struct {
	data T
	prev *SNode[T]
}

type Stack[T any] struct {
	length int
	head   *SNode[T]
}

func NewStack[T any]() *Stack[T] {
	return &Stack[T]{}
}

func (s *Stack[T]) Push(data T) {
	node := &SNode[T]{data: data}
	defer func() {
		s.length++
	}()

	if s.length == 0 {
		s.head = node
		return
	}

	node.prev = s.head
	s.head = node
}

func (s *Stack[T]) Pop() T {
	if s.length == 0 {
		var zero T
		return zero
	}

	node := s.head
	s.head = s.head.prev
	s.length--

	node.prev = nil
	return node.data
}

func (s *Stack[T]) Peek() T {
	if s.length == 0 {
		var zero T
		return zero
	}

	return s.head.data
}
