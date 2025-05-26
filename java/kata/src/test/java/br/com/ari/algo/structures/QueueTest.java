package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueTest {

    @Test
    void testQueue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        assertThat(queue.size()).isEqualTo(5);
        assertThat(queue.peek()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.peek()).isEqualTo(2);
        assertThat(queue.size()).isEqualTo(4);

        int[] expectedValues = new int[] { 2, 3, 4, 5 };
        for (int expected : expectedValues) {
            assertThat(queue.dequeue()).isEqualTo(expected);
        }

        assertThat(queue.size()).isZero();
    }

}