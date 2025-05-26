function binarySearch(arr: number[], target: number): boolean {
  let left = 0;
  let right = arr.length;

  while (left < right) {
    const mid = Math.floor(left + (right - left) / 2);
    const value = arr[mid];

    if (value === target) {
      return true;
    }

    if (value > target) {
      right = mid;
    } else {
      left = mid + 1;
    }
  }

  return false;
}

const arr = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 45, 56, 67, 78, 89, 100];

console.log(4, binarySearch(arr, 4));
console.log(8, binarySearch(arr, 8));
console.log(9, binarySearch(arr, 9));
console.log(15, binarySearch(arr, 15));
console.log(20, binarySearch(arr, 20));
console.log(100, binarySearch(arr, 100));
console.log(99, binarySearch(arr, 99));
console.log();

// -----------------

function twoCrystalBallsProblem(distances: boolean[]): number {
  const gap = Math.floor(Math.sqrt(distances.length));

  let i = gap;
  for (; i < distances.length; i += gap) {
    if (distances[i]) {
      break;
    }
  }

  i -= gap;

  for (let j = 0; j <= gap && i < distances.length; j++, i++) {
    if (distances[i]) {
      return i;
    }
  }

  return -1;
}

const distances = [
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  false,
  true,
  true,
  true,
];

console.log("Ball breaks at 10?", twoCrystalBallsProblem(distances) === 10);

// ----------------

function bubbleSort(arr: number[]): void {
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr.length - 1 - i; j++) {
      if (arr[j] > arr[j + 1]) {
        const temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
  }
}

const unsorted = [10, 12, 9, 5, 4, 1000, 13, 99, 4325, 12, 18, 45, 54, 99, 73];

console.log("unsorted", unsorted);

bubbleSort(unsorted);
console.log(unsorted);
