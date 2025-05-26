package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackTest {

    @Test
    void testStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        assertThat(stack.size()).isEqualTo(5);
        assertThat(stack.peek()).isEqualTo(50);

        assertThat(stack.pop()).isEqualTo(50);

        assertThat(stack.size()).isEqualTo(4);
        assertThat(stack.peek()).isEqualTo(40);

        int[] expectedValues = new int[] { 40, 30, 20, 10 };
        for (int expected : expectedValues) {
            assertThat(stack.pop()).isEqualTo(expected);
        }

        assertThat(stack.size()).isZero();
    }
    
}