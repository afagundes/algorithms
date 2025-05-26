function sort(arr: number[]) {
  qs(arr, 0, arr.length - 1);
}

function qs(arr: number[], left: number, right: number) {
  if (left >= right) {
    return;
  }

  const pivot = partition(arr, left, right);

  qs(arr, left, pivot - 1);
  qs(arr, pivot + 1, right);
}

function partition(arr: number[], left: number, right: number): number {
  const pivot = arr[right];
  let index = left - 1;

  for (let i = left; i < right; i++) {
    if (arr[i] <= pivot) {
      index++;

      const temp = arr[i];
      arr[i] = arr[index];
      arr[index] = temp;
    }
  }

  index++;
  arr[right] = arr[index];
  arr[index] = pivot;

  return index;
}

const arr = [10, 5, 4, 1, 9, 8, 2, 0, 7, 8, 1];
sort(arr);

console.log(arr);

