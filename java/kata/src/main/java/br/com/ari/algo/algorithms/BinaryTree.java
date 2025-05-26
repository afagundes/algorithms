package br.com.ari.algo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int length;

    public void insert(int value) {
        this.root = this.insertNode(this.root, value);
    }

    private Node insertNode(Node curr, int value) {
        if (curr == null) {
            this.length++;
            return new Node(value);
        }

        if (value <= curr.value) {
            curr.left = this.insertNode(curr.left, value);
        }
        else {
            curr.right = this.insertNode(curr.right, value);
        }

        return curr;
    }

    public int[] toSortedArray() {
        var path = new int[this.length];
        toArrayDepthFirstInOrder(this.root, path, 0);
        return path;
    }

    private int toArrayDepthFirstInOrder(Node curr, int[] path, int index) {
        if (curr == null) {
            return index;
        }

        index = toArrayDepthFirstInOrder(curr.left, path, index);
        path[index++] = curr.value;
        index = toArrayDepthFirstInOrder(curr.right, path, index);

        return index;
    }

    public boolean hasValueBreadth(int value) {
       return hasValueBreadthFirstSearch(value);
    }

    // O(n)
    private boolean hasValueBreadthFirstSearch(int value) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr == null) {
                continue;
            }

            if (curr.value == value) {
                return true;
            }

            queue.add(curr.left);
            queue.add(curr.right);
        }

        return false;
    }

    public boolean hasValueDepth(int value) {
        return hasValueDepthFirstSearch(this.root, value);
    }

    // O(log n)
    private boolean hasValueDepthFirstSearch(Node curr, int value) {
        if (curr == null) {
            return false;
        }

        if (value == curr.value) {
            return true;
        }

        if (value <= curr.value) {
            return hasValueDepthFirstSearch(curr.left, value);
        }

        return hasValueDepthFirstSearch(curr.right, value);
    }

    public boolean compare(BinaryTree other) {
        return compareNodes(this.root, other.root);
    }

    private boolean compareNodes(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        if (a.value != b.value) {
            return false;
        }

        return compareNodes(a.left, b.left) && compareNodes(a.right, b.right);
    }

}
