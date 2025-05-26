type QNode<T> = {
  value: T;
  next?: QNode<T>;
};

class Queue<T> {
  private length: number;
  private head?: QNode<T>;
  private tail?: QNode<T>;

  constructor() {
    this.length = 0;
    this.head = this.tail = undefined;
  }

  enqueue(value: T): void {
    const node = { value: value } as QNode<T>;

    if (!this.tail) {
      this.head = this.tail = node;
      return;
    }

    this.tail.next = node;
    this.tail = node;
    this.length++;
  }

  dequeue(): T | undefined {
    if (!this.head) {
      return undefined;
    }

    const node = this.head;
    this.head = this.head.next;
    this.length--;

    if (this.length === 0) {
      this.tail = undefined;
    }

    node.next = undefined;
    return node.value;
  }

  peek(): T | undefined {
    return this.head?.value;
  }
}

const queue = new Queue<number>();
queue.enqueue(1);
queue.enqueue(2);
queue.enqueue(3);
queue.enqueue(4);
queue.enqueue(5);
queue.enqueue(6);
queue.enqueue(7);

let value: number | undefined;
while ((value = queue.dequeue()) !== undefined) {
  console.log(value);
}

console.log(queue.peek());
