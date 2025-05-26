type BNode = {
  value: number;
  left: BNode;
  right: BNode;
}

class BinaryTree {

  private root?: BNode;

  constructor() {
    this.root = undefined;
  }

  public insert(value: number): void {
    this.root = this.insertNode(this.root, value);
  }

  private insertNode(curr: BNode | undefined, value: number): BNode {
    if (curr == undefined) {
      return { value: value } as BNode;
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
    const path: number[] = [];
    this.walk(this.root, path);
    return path;
  }

  /**
  * Depth first - In order algorithm
  */
  private walk(curr: BNode | undefined, path: number[]) {
    if (curr === undefined) {
      return;
    }

    this.walk(curr.left, path);
    path.push(curr.value);
    this.walk(curr.right, path);
  }

}

const tree = new BinaryTree();
tree.insert(5);
tree.insert(1);
tree.insert(4);
tree.insert(100);
tree.insert(2);
tree.insert(9);
tree.insert(3);

console.log(tree.asArray());

