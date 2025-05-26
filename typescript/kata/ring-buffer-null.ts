type RNode<T> = {
  value: T | undefined;
  read: boolean;
};

class NullableRungBuffer<T> {
  private arr: Array<RNode<T>>;
  private length: number;
  private writeIndex: number;
  private readIndex: number;

  constructor(length: number = 10) {
    this.length = length;
    this.arr = new Array<RNode<T>>();
    this.writeIndex = 0;
    this.readIndex = 0;
  }

  public write(value: T | undefined) {
    const node = this.arr[this.writeIndex];

    if (node && node.read === false) {
      throw new Error("Cannot override unread value");
    }

    this.arr[this.writeIndex] = { value: value, read: false } as RNode<T>;
    this.writeIndex = (this.writeIndex + 1) % this.length;
  }

  public read(): T | undefined {
    const node = this.arr[this.readIndex];

    if (node === undefined || node.read === true) {
      throw new Error("No values to read");
    }

    node.read = true;
    this.readIndex = (this.readIndex + 1) % this.length;

    return node.value;
  }
}

const ring = new NullableRungBuffer<number>(4);
ring.write(1);
ring.write(undefined);
ring.write(3);
ring.write(4);

console.log(ring.read());
console.log(ring.read());
console.log(ring.read());
console.log(ring.read());

ring.write(10);
ring.write(25);
ring.write(undefined);
ring.write(14);

while (true) {
  try {
    console.log(ring.read());
  } catch (_err) {
    break;
  }
}
