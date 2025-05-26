type StackNode<T> = {
  value: T;
  prev?: StackNode<T>
}

class Stack<T> {
  private length: number;
  private head?: StackNode<T>;

  constructor() {
    this.length = 0;
    this.head = undefined;
  }

  public peek(): T | undefined {
    return this.head?.value;
  }

  public push(value: T): void {
    const node = { value: value } as StackNode<T>;

    this.length++;

    if (this.head === undefined) {
      this.head = node;
      return;
    }

    node.prev = this.head;
    this.head = node;
  }

  public pop(): T | undefined {
    this.length = Math.max(0, this.length - 1);

    if (this.head === undefined) {
      return undefined;
    }

    const node = this.head;
    this.head = this.head.prev;

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
