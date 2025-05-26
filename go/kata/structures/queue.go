package structures

type QNode[T any] struct {
	data T
	next *QNode[T]
}

type Queue[T any] struct {
	length int
	head   *QNode[T]
	tail   *QNode[T]
}

func NewQueue[T any]() *Queue[T] {
	return &Queue[T]{}
}

func (q *Queue[T]) Enqueue(data T) {
	node := &QNode[T]{data: data}
	defer func() {
		q.length++
	}()

	if q.length == 0 {
		q.head = node
		q.tail = node
		return
	}

	q.tail.next = node
	q.tail = node
}

func (q *Queue[T]) Dequeue() T {
	if q.length == 0 {
		var zero T
		return zero
	}

	node := q.head
	q.head = q.head.next
	q.length--

	node.next = nil
	return node.data
}

func (q *Queue[T]) Peek() T {
	if q.length == 0 {
		var zero T
		return zero
	}

	return q.head.data
}
