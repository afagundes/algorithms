package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinHeapTest {

    @Test
    void testMinHeap() {
        var heap = new MinHeap();
        
        assertThat(heap.getLength()).isZero();

        heap.insert(5);
        heap.insert(3);
        heap.insert(69);
        heap.insert(420);
        heap.insert(4);
        heap.insert(1);
        heap.insert(8);
        heap.insert(7);
        heap.insert(7);
        heap.insert(7);
        heap.insert(7);
        heap.insert(7);

        assertThat(heap.getLength()).isEqualTo(12);
        assertThat(heap.delete()).isEqualTo(1);
        assertThat(heap.delete()).isEqualTo(3);
        assertThat(heap.delete()).isEqualTo(4);
        assertThat(heap.delete()).isEqualTo(5);
        assertThat(heap.getLength()).isEqualTo(8);
        assertThat(heap.delete()).isEqualTo(7);
        assertThat(heap.delete()).isEqualTo(7);
        assertThat(heap.delete()).isEqualTo(7);
        assertThat(heap.delete()).isEqualTo(7);
        assertThat(heap.delete()).isEqualTo(7);
        assertThat(heap.delete()).isEqualTo(8);
        assertThat(heap.delete()).isEqualTo(69);
        assertThat(heap.delete()).isEqualTo(420);
        assertThat(heap.getLength()).isZero();

    }

}
