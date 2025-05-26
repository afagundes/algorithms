type QNode<T> = {
  value: T;
  prev?: QNode<T>;
};

class Stack<T> {
  private length: number;
  private head?: QNode<T>;

  constructor() {
    this.length = 0;
    this.head = undefined;
  }

  peek(): T | undefined {
    return this.head?.value;
  }

  push(item: T): void {
    const node = { value: item } as QNode<T>;

    this.length++;

    if (!this.head) {
      this.head = node;
      return;
    }

    node.prev = this.head;
    this.head = node;
  }

  pop(): T | undefined {
    this.length = Math.max(0, this.length - 1);

    if (this.length === 0) {
      const node = this.head;
      this.head = undefined;
      return node?.value;
    }

    const node = this.head as QNode<T>;
    this.head = node.prev;

    return node.value;
  }
}

const stack = new Stack<number>();
stack.push(1);
stack.push(2);
stack.push(3);
stack.push(4);
stack.push(5);
stack.push(6);
stack.push(7);

console.log(stack.peek());
console.log();

let item: number | undefined;
while ((item = stack.pop()) !== undefined) {
  console.log(item);
}

console.log();
console.log(stack.peek());
