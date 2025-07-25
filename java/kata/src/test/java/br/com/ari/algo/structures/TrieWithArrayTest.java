package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrieWithArrayTest {

    @Test
    void testTrie_autocompleteWords() {
        var trie = new TrieWithArray();

        String[] words = new String[] {
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
                "mariana"
        };

        for (String word : words) {
            trie.insert(word);
        }

        var found = trie.find("ari");
        assertThat(found).containsExactly("ari", "ariel", "ariosvaldo", "aristóteles");

        found = trie.find("cas");
        assertThat(found).containsExactly("casa", "casaco", "casinha",  "caso");

        found = trie.find("mari");
        assertThat(found).containsExactly("mari", "mariana");

        // works for lower/upper case
        found = trie.find("Mari");
        assertThat(found).containsExactly("Mari", "Mariana");

        found = trie.find("inexistente");
        assertThat(found).isEmpty();
    }

}