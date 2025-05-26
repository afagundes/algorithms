type MiniQueueNode<T> = {
  value: T;
  next: MiniQueueNode<T> | undefined;
}

class MiniQueue<T> {
  private head: MiniQueueNode<T> | undefined;
  private tail: MiniQueueNode<T> | undefined;
  private size: number;

  constructor() {
    this.head = undefined;
    this.tail = undefined;
    this.size = 0;
  }

  public enqueue(value: T) {
    const node = { value: value } as MiniQueueNode<T>;

    this.size++;

    if (!this.tail) {
      this.head = this.tail = node;
      return;
    }

    this.tail.next = node;
    this.tail = node;
  }

  public dequeue(): T | undefined {
    if (!this.head) {
      return undefined;
    }

    const node = this.head;
    this.head = this.head.next;

    if (this.head === undefined) {
      this.tail = undefined;
    }

    this.size--;

    return node.value;
  }

  public getSize(): number {
    return this.size;
  }
}

type BNode = {
  value: number;
  left: BNode | undefined;
  right: BNode | undefined;
}

class BinaryTree {
  private root: BNode | undefined;

  public insert(value: number) {
    this.root = this.insertNode(this.root, value);
  }

  private insertNode(curr: BNode | undefined, value: number): BNode {
    if (curr === undefined) {
      return { value } as BNode;
    }

    if (value <= curr.value) {
      curr.left = this.insertNode(curr.left, value);
    }
    else {
      curr.right = this.insertNode(curr.right, value);
    }

    return curr;
  }

  public asArray(): number[] {
    const path = [];
    this.asArrayDepthFirst(this.root, path);
    return path;
  }

  private asArrayDepthFirst(curr: BNode | undefined, path: number[]) {
    if (curr === undefined) {
      return;
    }

    this.asArrayDepthFirst(curr.left, path);
    path.push(curr.value);
    this.asArrayDepthFirst(curr.right, path);
  }

  // Breadth-first search 
  public search(target: number): boolean {
    if (!this.root) {
      return false;
    }

    const q = new MiniQueue<BNode | undefined>();
    q.enqueue(this.root);

    while (q.getSize()) {
      const curr = q.dequeue();

      if (!curr) {
        continue;
      }

      if (curr.value === target) {
        return true;
      }

      q.enqueue(curr.left);
      q.enqueue(curr.right);
    }

    return false;
  }

  public asArrayBreadthFirst(): number[] {
    const path: number[] = [];

    const q = new MiniQueue<BNode | undefined>();
    q.enqueue(this.root);

    while (q.getSize()) {
      const curr = q.dequeue();

      if (!curr) {
        continue;
      }

      path.push(curr.value);

      q.enqueue(curr.left);
      q.enqueue(curr.right);
    }

    return path;
  }

}

const tree = new BinaryTree();
tree.insert(10);
tree.insert(5);
tree.insert(3);
tree.insert(11);
tree.insert(9);
tree.insert(4);

console.log(tree.asArray());

console.log("Has 1:", tree.search(1));
console.log("Has 2:", tree.search(2));
console.log("Has 3:", tree.search(3));
console.log("Has 4:", tree.search(4));
console.log("Has 11:", tree.search(11));
console.log("Has 21:", tree.search(21));

console.log(tree.asArrayBreadthFirst());

