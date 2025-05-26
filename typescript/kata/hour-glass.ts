function solve(input: string): number {
  const arr = parseInput(input)
  let sum = 0

  // linhas
  for (let i = 0; i < arr.length - 3; i++) {
    // colunas
    for (let j = 0; j < arr[0].length - 3; j++) {
      sum = Math.max(sum, sumHourGlass(arr, j, i));
    }
  }

  return sum;
}

// Transforma a string do input em um array numérico bidimensional. Descarta linhas em branco
function parseInput(input: string): number[][] {
  return input
    .split("\n")
    .map(line => line.trim())
    .filter(line => line !== "")
    .map(line => line.split(" ").map(num => parseInt(num)));
}

// Pega a posição atual da iteração do array e tenta somar os índices do formato de uma ampulheta
function sumHourGlass(input: number[][], currX: number, currY: number): number {
  // Array que define o formato de uma ampulheta
  // Ajuda a deixar a iteração mais limpa, sem muitos ifs.
  const hourGlassPath = [
    [true, true, true],
    [false, true, false],
    [true, true, true],
  ];

  let sum = 0;
  const hourGlassSize = 3;

  // Linhas
  for (let i = 0, indexY = currY; i < hourGlassSize && indexY < input.length; i++, indexY++) {
    // colunas
    for (let j = 0, indexX = currX; j < hourGlassSize && indexX < input[0].length; j++, indexX++) {
      // Se os índices atuais corresponderem ao formato da ampulheta
      if (hourGlassPath[i][j]) {
        sum += input[indexY][indexX];
      }
    }
  }

  return sum;
}

let input = `
  1 1 1 0 0 0
  0 1 0 0 0 0
  1 1 1 0 0 0
  0 0 0 0 0 0
  0 0 0 0 0 0
  0 0 0 0 0 0
`;

console.log("Input 1 deve ser 7:", solve(input) === 7);

input = `
  1 1 1 0 0 0
  0 1 0 0 0 0
  1 1 1 1 2 0
  0 0 0 5 0 0
  0 0 4 1 1 0
  0 0 0 0 0 0
`;

console.log("Input 2 deve ser 15:", solve(input) === 15);
