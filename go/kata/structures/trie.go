package structures

import (
	"strings"
	"unicode"
)

type Trie struct {
	root *TrieNode
}

type TrieNode struct {
	char     rune
	isWord   bool
	children map[rune]*TrieNode
}

func NewTrie() *Trie {
	return &Trie{
		root: &TrieNode{children: buildChildrenMap()},
	}
}

func newTrieNode(char rune) *TrieNode {
	return &TrieNode{
		char:     char,
		isWord:   false,
		children: buildChildrenMap(),
	}
}

func (t *Trie) Insert(word string) {
	word = strings.ToLower(word)
	curr := t.root

	for _, char := range word {
		if curr.children[char] == nil {
			curr.children[char] = newTrieNode(char)
		}

		curr = curr.children[char]
	}

	curr.isWord = true
}

func (t *Trie) Find(prefix string) []string {
	buffer := make([]rune, 50)
	bufferIdx := 0

	// The first thing to do is to find the node that will act as the root
	curr := t.root
	for _, chr := range prefix {
		next := curr.children[unicode.ToLower(chr)]

		if next == nil {
			return []string{}
		}

		curr = next
		buffer[bufferIdx] = chr
		bufferIdx++
	}

	bufferIdx--

	// Now that we've found the root node, we make a depth-first search to find all possible words starting from it
	words := make([]string, 0, 10)
	find(curr, &buffer, bufferIdx, &words)

	return words
}

func find(curr *TrieNode, buffer *[]rune, bufferIdx int, words *[]string) int {
	if curr == nil {
		return -1
	}

	(*buffer)[bufferIdx] = curr.char
	bufferIdx++

	if curr.isWord {
		*words = append(*words, string((*buffer)[0:bufferIdx]))
	}

	for _, child := range curr.children {
		idx := find(child, buffer, bufferIdx, words)

		if idx != -1 {
			bufferIdx = idx - 1
		}
	}

	return bufferIdx
}

func buildChildrenMap() map[rune]*TrieNode {
	return make(map[rune]*TrieNode)
}
