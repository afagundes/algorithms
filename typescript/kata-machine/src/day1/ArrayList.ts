export default class ArrayList<T> {
    public length: number;
    public capacity: number;

    private arr: T[];

    constructor(cap: number = 10) {
        this.length = 0;
        this.capacity = cap;
        this.arr = new Array(this.capacity);
    }

    prepend(item: T): void {
        this.resize(this.capacity * 2);

        for (let i = this.length; i > 0; i--) {
            this.arr[i] = this.arr[i - 1];
        }

        this.arr[0] = item;
        this.length++;
    }

    insertAt(item: T, idx: number): void {
        this.resize(this.capacity * 2);

        for (let i = this.length; i > idx; i--) {
            this.arr[i] = this.arr[i - 1];
        }

        this.arr[idx] = item;
        this.length++;
    }

    append(item: T): void {
        this.resize(this.capacity * 2);
        this.arr[this.length++] = item;
    }

    remove(item: T): T | undefined {
        let value = undefined;

        let i = 0;
        for (; i < this.length; i++) {
            if (this.arr[i] === item) {
                value = this.arr[i];
                break;
            }
        }

        if (value !== undefined) {
            for (; i < this.length - 1; i++) {
                this.arr[i] = this.arr[i + 1];
            }

            this.length--;
        }

        return value;
    }

    get(idx: number): T | undefined {
        if (idx >= this.length) {
            return undefined;
        }

        return this.arr[idx];
    }

    removeAt(idx: number): T | undefined {
        if (idx >= this.length) {
            return undefined;
        }

        const value = this.arr[idx];

        for (let i = idx; i < this.length - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }

        this.length--;

        return value;
    }

    private resize(newSize: number) {
        if (this.length === this.capacity) {
            this.capacity = newSize;
            const doubled = new Array(this.capacity);

            for (let i = 0; i < this.length; i++) {
                doubled[i] = this.arr[i];
            }

            this.arr = doubled;
        }
    }
}
