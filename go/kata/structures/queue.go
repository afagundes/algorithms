package structures

type QNode[T any] struct {
	value T
	next  *QNode[T]
}

type Queue[T any] struct {
	length int
	head   *QNode[T]
	tail   *QNode[T]
}

func NewQueue[T any]() *Queue[T] {
	return &Queue[T]{length: 0}
}

func (q *Queue[T]) enqueue(value T) {
	node := &QNode[T]{value: value}
	q.length++

	if q.tail == nil {
		q.head = node
		q.tail = node
		return
	}

	q.tail.next = node
	q.tail = node
}

func (q *Queue[T]) dequeue() T {
	if q.head == nil {
		var zero T
		return zero
	}

	node := q.head

	q.head = q.head.next
	q.length--

	if q.head == nil {
		q.tail = nil
	}

	node.next = nil

	return node.value
}

func (q *Queue[T]) peek() T {
	if q.head == nil {
		var zero T
		return zero
	}

	return q.head.value
}
