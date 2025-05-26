package br.com.ari.algo.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeTest {

    @Test
    void shouldInsertAndReturnSortedArray() {
        var tree = new BinaryTree();
        tree.insert(10);
        tree.insert(2);
        tree.insert(1);
        tree.insert(14);
        tree.insert(3);
        tree.insert(9);
        tree.insert(4);
        tree.insert(5);
        tree.insert(99);
        tree.insert(12);

        assertThat(tree.toSortedArray()).isEqualTo(new int[]{ 1, 2, 3, 4, 5, 9, 10, 12, 14, 99 });
    }

    @Test
    void shouldInsertAndBeAbleToVerifyIfValueIsSaved() {
        var tree = new BinaryTree();
        tree.insert(10);
        tree.insert(2);
        tree.insert(1);
        tree.insert(14);
        tree.insert(3);
        tree.insert(9);
        tree.insert(4);
        tree.insert(5);
        tree.insert(99);
        tree.insert(12);

        assertThat(tree.hasValueBreadth(10)).isTrue();
        assertThat(tree.hasValueBreadth(2)).isTrue();
        assertThat(tree.hasValueBreadth(14)).isTrue();
        assertThat(tree.hasValueBreadth(99)).isTrue();
        assertThat(tree.hasValueBreadth(101)).isFalse();
        assertThat(tree.hasValueBreadth(11)).isFalse();
        assertThat(tree.hasValueBreadth(6)).isFalse();
    }

    @Test
    void shouldInsertAndBeAbleToFindValue() {
        var tree = new BinaryTree();
        tree.insert(10);
        tree.insert(2);
        tree.insert(1);
        tree.insert(14);
        tree.insert(3);
        tree.insert(9);
        tree.insert(4);
        tree.insert(5);
        tree.insert(99);
        tree.insert(12);

        assertThat(tree.hasValueDepth(10)).isTrue();
        assertThat(tree.hasValueDepth(2)).isTrue();
        assertThat(tree.hasValueDepth(14)).isTrue();
        assertThat(tree.hasValueDepth(99)).isTrue();
        assertThat(tree.hasValueDepth(101)).isFalse();
        assertThat(tree.hasValueDepth(11)).isFalse();
        assertThat(tree.hasValueDepth(6)).isFalse();
    }

    @Test
    void shouldCompareTwoBinaryTrees() {
        var treeA = new BinaryTree();
        treeA.insert(10);
        treeA.insert(2);
        treeA.insert(1);
        treeA.insert(14);

        var treeB = new BinaryTree();
        treeB.insert(10);
        treeB.insert(2);
        treeB.insert(1);
        treeB.insert(14);

        assertThat(treeA.compare(treeB)).isTrue();

        var treeC = new BinaryTree();
        treeC.insert(10);
        treeC.insert(2);
        treeC.insert(1);
        treeC.insert(14);
        treeC.insert(15);
        treeC.insert(7);

        assertThat(treeA.compare(treeC)).isFalse();
    }

}
