type TrieNode = {
  value: string;
  isWord: boolean;
  children: Map<string, TrieNode>
}

class Trie {
  private root: TrieNode;

  constructor() {
    this.root = { children: new Map() } as TrieNode;
  }

  insert(word: string) {
    word = word.toLowerCase();

    let curr = this.root;
    for (const char of word) {
      let node = curr.children.get(char);

      if (node === undefined) {
        node = { value: char, children: new Map() } as TrieNode;
        curr.children.set(char, node);
      }

      curr = node as TrieNode;
    }

    curr.isWord = true;
  }

  find(prefix: string): string[] {
    const buffer: string[] = [];
    let bufferIdx = 0;

    let curr = this.root;
    for (const char of prefix) {
      const next = curr.children.get(char.toLowerCase());

      if (next === undefined) {
        return [];
      }

      curr = next;
      buffer[bufferIdx++] = char;
    }

    bufferIdx--;

    const words: string[] = [];
    this.findDepthFirst(curr, buffer, bufferIdx, words);

    return words;
  }

  private findDepthFirst(curr: TrieNode | undefined, buffer: string[], bufferIdx: number, words: string[]): number {
    if (curr === undefined) {
      return -1;
    }

    buffer[bufferIdx++] = curr.value;

    if (curr.isWord) {
      words.push(buffer.slice(0, bufferIdx).join(''));
    }

    for (const child of curr.children.values()) {
      const idx = this.findDepthFirst(child, buffer, bufferIdx, words);

      if (idx !== -1) {
        bufferIdx = idx - 1;
      }
    }

    return bufferIdx;
  }

}

const words = [
  "archimedes",
  "ari",
  "aristóteles",
  "ariosvaldo",
  "ariel",
  "antes",
  "avião",
  "aliás",
  "após",
  "apoio",
  "apoiar",
  "casinha",
  "casa",
  "casaco",
  "caso",
  "camarão",
  "cadastro",
  "comida",
  "coisa",
  "mari",
  "mariana",
];

const trie = new Trie();
for (const word of words) {
  trie.insert(word);
}

console.log(trie.find("ari"));
console.log(trie.find("mari"));
console.log(trie.find("Ma"));
console.log(trie.find("ca"));
