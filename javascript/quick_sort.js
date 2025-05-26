function quickSort(arr) {
  sort(arr, 0, arr.length - 1);
}

function sort(arr, start, end) {
  if (start >= end) {
    return;
  }

  const pivot = partition(arr, start, end);

  sort(arr, start, pivot - 1);
  sort(arr, pivot + 1, end);
}

function partition(arr, start, end) {
  const pivot = arr[end];
  let index = start - 1;

  for (let i = start; i < end; i++) {
    if (arr[i] <= pivot) {
      index++;

      const temp = arr[i];
      arr[i] = arr[index];
      arr[index] = temp;
    }
  }

  index++;
  arr[end] = arr[index];
  arr[index] = pivot;

  return index;
}

const arr = [9, 1, 5, 13, 14, 19, 2, 8, 7, 3, 0, 21, 8, 75, 10];
quickSort(arr)
console.log(arr);

