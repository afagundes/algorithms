package structures

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestTrie(t *testing.T) {
	words := []string{
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
	}

	trie := NewTrie()

	for _, word := range words {
		trie.Insert(word)
	}

	found := trie.Find("ari")
	assert.ElementsMatch(t, []string{"ari", "ariel", "ariosvaldo", "aristóteles"}, found)

	found = trie.Find("cas")
	assert.ElementsMatch(t, []string{"casa", "casaco", "casinha", "caso"}, found)

	found = trie.Find("mari")
	assert.ElementsMatch(t, []string{"mari", "mariana"}, found)

	// works for lower/upper case
	found = trie.Find("Mari")
	assert.ElementsMatch(t, []string{"Mari", "Mariana"}, found)

	found = trie.Find("inexistente")
	assert.Zero(t, len(found))
}
