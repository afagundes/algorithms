function hasVowels_bruteForce(strArr, query) {
  const vowels = ['a', 'e', 'i', 'o', 'u'];
  const result = [];

  for (let interval of query) {
    let matchCount = 0;

    const tokens = interval.split('-');
    const start = parseInt(tokens[0]) - 1; // o enunciado diz que os índices estão com base 1 e arrays tem base 0, por isso subtraio 1 desse intervalo
    const end = parseInt(tokens[1]) - 1;

    // o enunciado diz que os intervalos são "inclusivos", ou seja é para considerar o primeiro e o último índice
    // normalmente em arrays o último índice é "exclusivo" no sentido de que não deve ser considerado.
    //
    // Por exemlo:
    // [1-3] inclusivo - considero os elementos 1, 2 e 3
    // [1-3] exclusivo - considero os elementos 1 e 2, pois 3 não deve ser considerado
    for (let i = start; i <= end; i++) {
      const str = strArr[i];
      const lastCharPosition = str.length - 1;

      if (vowels.includes(str.charAt(0)) && vowels.includes(str.charAt(lastCharPosition))) {
        matchCount++;
      }
    }

    result.push(matchCount);
  }

  return result;
}

function hasVowels_sexyAndElegant(strArr, query) {
  // Aqui já otimizo aquele array de vogais pra um Set que tem um método .has() mais rápido pra verificar se um elemento está dentro dele
  const vowels = new Set(['a', 'e', 'i', 'o', 'u']);

  // Aqui eu inicializo um array em que eu vou guardar quantas vezes a regra é cumprima em um intervalo,
  // por exemplo ['aba', 'bcb', 'ece', 'aa', 'e']
  // ficaria:
  //
  // indexes[0] = 0 -> não vamos utilizar o índice 0 pq o enunciado diz que os intervalos são base 1
  // indexes[1] = 1 -> o primeiro elemento atende a regra
  // indexes[2] = 0 -> o segundo elemento não atende a regra
  // indexes[3] = 1 -> o terceiro elemento atende a regra
  // indexes[4] = 2 -> o quarto elemento atende a regra e antes dele um já atendia, então nesse intervalo tem 2 ocorrências
  // indexes[5] = 3 -> mesmo caso acima
  const ocurrences = new Array(strArr.length + 1).fill(0);

  // Primeiro e único loop pelas string
  for (let i = 0; i < strArr.length; i++) {
    const str = strArr[i];
    const lastCharPosition = str.length - 1;

    // Verifica se a string começa e termina com vogal
    const matches = vowels.has(str.charAt(0)) && vowels.has(str.charAt(lastCharPosition));

    // Se a string começa e termina com vogal, coloca 1 no "slot" dela, senão 0
    // Somamos com o resultado anterior para criar a sequências de matches pelos intervalos
    ocurrences[i + 1] = ocurrences[i] + (matches ? 1 : 0);
  }

  const result = [];

  // Agora é só fazer a diferença entre os intervalos
  for (let interval of query) {
    const [start, end] = interval.split('-').map(x => parseInt(x) - 1);
    result.push(ocurrences[end + 1] - ocurrences[start]);
  }

  return result;
}

const strArr = ['aba', 'bcb', 'ece', 'aa', 'e'];
const queries = ['1-3', '2-5', '2-2'];

console.log(hasVowels_bruteForce(strArr, queries));
console.log(hasVowels_sexyAndElegant(strArr, queries));

