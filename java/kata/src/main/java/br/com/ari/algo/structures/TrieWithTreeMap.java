package br.com.ari.algo.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Retrieval Tree - used for prefix searches, like autocomplete or caches
 */
public class TrieWithTreeMap {

    private static class Node {
        char value;
        boolean isWord;

        final Map<Character, Node> children;

        Node() {
            this.children = new TreeMap<>();
        }

        Node(char value) {
            this();
            this.value = value;
        }
    }

    private Node root;

    public TrieWithTreeMap() {
        this.root = new Node();
    }

    public void insert(String word) {
        word = word.toLowerCase(Locale.ROOT);
        Node curr = this.root;

        for (char chr : word.toCharArray()) {
            if (curr.children.containsKey(chr)) {
                curr = curr.children.get(chr);
            } else {
                var node = new Node(chr);
                curr.children.put(chr, node);
                curr = node;
            }
        }

        curr.isWord = true;
    }

    public List<String> find(String prefix) {
        char[] buffer = new char[50];
        int bufferIdx = 0;

        // The first thing to do is to find the node that will act as the root
        Node curr = this.root;
        for (char chr : prefix.toCharArray()) {
            Node next = curr.children.get(Character.toLowerCase(chr));

            if (next == null) {
                return Collections.emptyList();
            }

            curr = next;
            buffer[bufferIdx++] = chr;
        }

        bufferIdx--;

        // Now that we've found the root node, we make a depth-first search to find all possible words starting from it
        List<String> words = new ArrayList<>();
        find(curr, buffer, bufferIdx, words);

        return words;
    }

    private int find(Node curr, char[] buffer, int bufferIdx, List<String> words) {
        if (curr == null) {
            return -1;
        }

        buffer[bufferIdx++] = curr.value;

        if (curr.isWord) {
            words.add(new String(buffer, 0, bufferIdx));
        }

        for (Node next : curr.children.values()) {
            int idx = find(next, buffer, bufferIdx, words);

            if (idx != -1) {
                bufferIdx = idx - 1;
            }
        }

        return bufferIdx;
    }

}
