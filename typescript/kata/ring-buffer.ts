class RingBuffer<T> {
  private arr: Array<T | undefined>;
  private size: number;
  private writeIdx: number;
  private readIdx: number;

  constructor(size: number = 10) {
    this.size = size;
    this.arr = new Array<T>(size);
    this.writeIdx = 0;
    this.readIdx = 0;
  }

  public write(value: T) {
    this.arr[this.writeIdx] = value;
    this.writeIdx = (this.writeIdx + 1) % this.size;
  }

  public read(): T | undefined {
    const value = this.arr[this.readIdx];

    if (value === undefined) {
      return undefined;
    }

    this.arr[this.readIdx] = undefined;
    this.readIdx = (this.readIdx + 1) % this.size;

    return value;
  }

  public reset() {
    this.readIdx = 0;
    this.writeIdx = 0;
  }
}

const ring = new RingBuffer<number>(4);

ring.write(1);
ring.write(2);
ring.write(3);

console.log(ring.read());
console.log(ring.read());
console.log(ring.read());
console.log(ring.read());

ring.write(12);
ring.write(15);
ring.write(17);
ring.write(17);

console.log(ring.read());
console.log(ring.read());
console.log(ring.read());
console.log(ring.read());

console.log();

ring.reset();
ring.write(1);
ring.write(2);
ring.write(3);
ring.write(4);

let value: number | undefined;
while ((value = ring.read()) !== undefined) {
  console.log(value);
}
