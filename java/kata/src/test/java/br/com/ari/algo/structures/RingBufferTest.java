package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RingBufferTest {

    @Test
    void testRingBuffer() {
        RingBuffer<Integer> buffer = new RingBuffer<>();
        buffer.write(1);
        buffer.write(2);

        assertThat(buffer.read()).isEqualTo(1);
        assertThat(buffer.read()).isEqualTo(2);
        assertThat(buffer.read()).isNull();

        buffer = new RingBuffer<>(2);
        buffer.write(1);
        buffer.write(2);

        assertThat(buffer.size()).isEqualTo(2);

        assertThat(buffer.read()).isEqualTo(1);
        assertThat(buffer.read()).isEqualTo(2);

        // Returned to init of the buffer
        assertThat(buffer.read()).isEqualTo(1);
        assertThat(buffer.read()).isEqualTo(2);

        buffer.write(100);

        assertThat(buffer.read()).isEqualTo(100);
        assertThat(buffer.read()).isEqualTo(2);
    }

    @Test
    void testLockUnread() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3, true);
        buffer.write(1);
        buffer.write(2);
        buffer.write(3);

        assertThat(buffer.size()).isEqualTo(3);
        assertThat(buffer.read()).isEqualTo(1);

        buffer.write(100);
        assertThatThrownBy(() -> buffer.write(200)).isInstanceOf(IllegalStateException.class); // can't write at an unread position

        assertThat(buffer.read()).isEqualTo(2);
        buffer.write(200);

        assertThatThrownBy(() -> buffer.write(300)).isInstanceOf(IllegalStateException.class); // can't write at an unread position

        assertThat(buffer.read()).isEqualTo(3);
        buffer.write(300);

        // read all
        for (int i = 0; i < buffer.size(); i++) {
            buffer.read();
        }

        // now I can write all positions again
        buffer.write(1000);
        buffer.write(2000);
        buffer.write(3000);

        assertThatThrownBy(() -> buffer.write(4000)).isInstanceOf(IllegalStateException.class); // can't write at an unread position
    }

    @Test
    void testCircularBehavior() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3);
        buffer.write(1);
        buffer.write(2);
        buffer.write(3);

        var expectedSequence = new int[] { 1, 2, 3 };

        for (int i = 0, index = 0; i < 1000; i++, index = i % expectedSequence.length) {
            assertThat(buffer.read()).isEqualTo(expectedSequence[index]);
        }
    }

}
