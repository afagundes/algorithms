/*
 * Explicação: arrays são estruturas de tamanho fixo em memória.
 * Quando vc cria um array, uma determinada porção de memória é alocada para sua variável. 
 * Logo, se vc cria um array de tamanho 10, por exemplo "new Array(10)", 10 "slots" de memória contínuos
 * são alocados pra vc. Portanto, arrays tem tamanho fixo e não podem ser redimensionados.
 *
 * Quando vc vê algo como [1, 2, 3].concat([4, 5, 6]), (ou seja um array de tamanho 3 tendo outro array de tamanho 3 adicionado) 
 * o que vc está vendo na verdade é uma estrutura de dados conhecida como array dinâmico ou ArrayList.
 * Esse tipo de estrutura possui um array clássico por baixo, e quando ele identifica que chegou no limite, 
 * ele cria um novo array e copia todos os elementos do antigo pro novo.
 *
 * Mas como javascript é uma mãe e cuida de todos nós, foi convencionado chamar esses arrays dinâmicos de simplesmente array,
 * mas é sempre bom saber a lógica por trás das funções que ele disponibiliza.
 *
 * "Ah, mas na prática eu vou usar concat mesmo e foda-se". E deve usar mesmo, mas é sempre importante lembrar qye essas cópias
 * que o array dinâmico faz tem um custo de processamento e podem ser a causa de problemas de performance caso ele esteja sendo usado
 * dentro de um loop, por exemplo:
 *
 * for (let i = 0; i < 10; i++) {
 *    a.concat(b);
 * }
 *
 * Esse código acima faz o pobre Alan Turing espumar de raiva no túmulo huahuauhahua.
 *
 * Nesse caso é melhor calcular o tamanho do array final com base em quantos elementos ele vai receber.
 */

function mergeArrays(a, b) {
  // Cria um array cujo tamanho é a soma dos tamanhos dos dois arrays de input
  // dá pra criar assim const result = [] e adicionar elementos nele, com concat...
  // caso você esteja tentado a fazer isso, leia a explicação acima.
  const result = new Array(a.length + b.length);

  // Precisamos de dois ponteiros, um para cada array
  // cada um vai ser incrementado individualmente à medida que
  // os elementos para os quais eles apontam são inseridos no array de resultado.
  let indexA = 0;
  let indexB = 0;

  // Esse é o ponteiro pro array final
  let index = 0;

  // Aqui é coisa de maluco huahuuaha
  // Nós vamos fazer um loop que verifica se um elemento de um array é maior, menor ou igual a outro do outro array.
  // Quem for menor vai ser inserido no array de resultado final e seu ponteiro vai ser incrementado para apontar para o próximo elemento.
  // Se forem iguais, os dois entram e os dois ponteiros são incrementados.
  // Pelo menos 1 array inteiro vai ser inserido no array final nesse loop. Quando isso acontecer, o loop vai parar.
  while (indexA < a.length && indexB < b.length) {
    // Se A for menor que B, o A entra no array final e o ponteiro dele é incrementado
    if (a[indexA] < b[indexB]) {
      result[index] = a[indexA];
      indexA++; // incrementa o ponteiro para o array A
    }
    // Se A for maior que B, então quem entra é o B
    else if (a[indexA] > b[indexB]) {
      result[index] = b[indexB];
      indexB++; // incrementa o ponteiro para o array B
    }
    // aqui eles são iguais, todo mundo entra
    else {
      result[index] = a[indexA];
      result[++index] = b[indexB]; // nota que eu estou pre-incrementando o index aqui, se ele é 1, ele vira 2 nesse momento

      indexA++;
      indexB++;
    }

    // Sempre incrementa o índice principal
    index++;
  }

  // Aqui acabou o loop maior, pelo menos um array foi adicionado por completo no array final,
  // mas é possível que algum array não tenha sido adicionado por inteiro. A gente verifica isso aqui:

  // Aqui o array A não foi adicionado por inteiro
  if (indexA < a.length - 1) {
    adicionaQuemTaFaltando(result, index, a, indexA);
  }

  // Aqui o array B não foi adicionado por inteiro
  if (indexB < b.length - 1) {
    adicionaQuemTaFaltando(result, index, b, indexB);
  }

  // tecnicamente nem precisaria desses dois ifs acima, pq se todos os elementos de todos 
  // os arrays forem inseridos, a variável index estaria com um valor igual à soma dos tamanhos dois dois arrys,
  // logo a função não faria nada.

  return result;
}

// Essa funçãozinha marota recebe o array principal e adiciona todos os elementos do array "remainingArr"
// a partir do índice de onde ele parou de ser acessado.
function adicionaQuemTaFaltando(result, index, arrayQueSobrou, indiceOndeParouOArrayQueSobrou) {
  for (let i = index, j = indiceOndeParouOArrayQueSobrou; i < result.length && j < arrayQueSobrou.length; i++, j++) {
    result[i] = arrayQueSobrou[j];
  }
}

let a = [1, 2, 3];
let b = [2, 5, 5];

console.log(mergeArrays(a, b));


a = [1, 3, 5, 7, 9];
b = [2, 4, 5, 6, 8, 10, 12, 14];

console.log(mergeArrays(a, b));

/**
 * Debug:
 *
 * let a = [1, 2, 3];
 * let b = [2, 5, 5];
 *
 * indexA = 0;
 * indexB = 0;
 * index = 0;
 *
 * O loop começa:
 *
 * a[0] < b[o] -> portanto quem entra no array final é a[0], aí ficamos com os índices assim:
 *
 * indexA = 1;
 * indexB = 0;
 * index = 1;
 *
 * O loop continua:
 *
 * a[1] == b[1] -> como são iguais, todo mundo entra e os índices ficam:
 *
 * indexA = 2;
 * indexB = 1;
 * index = 3; -> esse é incrementado 2 vezes
 *
 * O loop continua:
 *
 * a[2] < b[1] -> a[1] é menor, ele entra. Os índices ficam:
 *
 * indexA = 3;
 * indexB = 1;
 * index = 4;
 *
 * indexA agora é igual ao tamanho do array A, portanto não cumpre mais a cláusula do while e o loop para.
 *
 * Nessa hora, o array A entrou inteiro no array final, mas ainda faltam 2 elementos do array b que não foram inseridos.
 * mas como já sabemos que o array b parou no índice 1, é fácil inserir os elementos que faltam.
 */
