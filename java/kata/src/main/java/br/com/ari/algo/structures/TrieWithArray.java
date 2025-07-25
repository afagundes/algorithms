package br.com.ari.algo.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Used in autocomplete or caches
 */
public class TrieWithArray {

    private static class Node {
        char value;
        boolean isWord;
        final Node[] children;

        Node() {
            this.children = this.allocateNodes();
        }

        Node(char value) {
            this();
            this.value = value;
        }

        Node[] allocateNodes() {
            return new Node[256]; // initialize one slot for each letter of the alphabet
        }
    }

    private final Node root;

    public TrieWithArray() {
        this.root = new Node();
    }

    public void insert(String word) {
        word = word.toLowerCase(Locale.ROOT);
        Node curr = this.root;

        for (char chr : word.toCharArray()) {
            int index = charIndex(chr);

            if (curr.children[index] == null) {
                Node node = new Node(chr);
                curr.children[index] = node;
                curr = node;
            } else {
                curr = curr.children[index];
            }
        }

        curr.isWord = true;
    }

    public List<String> find(String prefix) {
        Node curr = this.root;
        List<String> found = new ArrayList<>();
        char[] buffer = new char[50];
        int bufferIdx = 0;

        // The first thing to do is to find the node that will act as the root
        for (char chr : prefix.toCharArray()) {
            int index = charIndex(Character.toLowerCase(chr));

            if (curr.children[index] == null) {
                return Collections.emptyList();
            }

            curr = curr.children[index];
            buffer[bufferIdx++] = chr;
        }

        // Now that we've found the root node, we make a depth-first search to find all possible words starting from it
        bufferIdx--;
        findWordsFrom(curr, buffer, bufferIdx, found);

        return found;
    }

    private int findWordsFrom(Node curr, char[] buffer, int bufferIdx, List<String> words) {
        if (curr == null) {
            return -1;
        }

        buffer[bufferIdx++] = curr.value;

        if (curr.isWord) {
            words.add(new String(buffer, 0, bufferIdx));
        }

        for (Node next : curr.children) {
            int idx = findWordsFrom(next, buffer, bufferIdx, words);

            if (idx == -1) {
                continue;
            }

            bufferIdx = idx - 1;
        }

        return bufferIdx;
    }

    private static int charIndex(char theChar) {
        int zero = 'a'; // code 97
        return theChar - zero; // b, for example, is 98... so 98 - 97 = 1 <-- this is the array index
    }

}
