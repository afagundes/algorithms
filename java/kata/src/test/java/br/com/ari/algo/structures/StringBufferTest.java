package br.com.ari.algo.structures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringBufferTest {

    @Test
    void testStringBuffer() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("This is ");
        buffer.append("a simple ");
        buffer.append("StringBuffer test");

        String result = buffer.toString();

        assertThat(buffer.length()).isEqualTo(34);

        assertThat(result)
                .isEqualTo("This is a simple StringBuffer test")
                .hasSize(34);
    }

    @Test
    void testCharAt() {
        StringBuffer buffer = new StringBuffer("Initial value. ")
                .append("Second value coming append. ")
                .append("Third value coming from append chain.");

        assertThat(buffer.charAt(0)).isEqualTo('I');
        assertThat(buffer.charAt(19)).isEqualTo('n');
        assertThat(buffer.charAt(buffer.length() - 1)).isEqualTo('.');

        assertThatThrownBy(() -> buffer.charAt(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> buffer.charAt(1000)).isInstanceOf(IndexOutOfBoundsException.class);

        StringBuffer empty = new StringBuffer();
        assertThatThrownBy(() -> empty.charAt(0)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testSubSequence() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("José ");
        buffer.append("de Souza ");
        buffer.append("Alencar");

        String middleName = (String) buffer.subSequence(5, 13);
        assertThat(middleName).isEqualTo("de Souza");
    }

    @Test
    void testTrim() {
        StringBuffer buffer = new StringBuffer("   One line test   \n");
        buffer.trim();
        assertThat(buffer).hasToString("One line test");

        buffer = new StringBuffer();
        buffer.append("     \n  \tHello. ");
        buffer.append("A trim happened   here\r\n   ");

        buffer.trim();

        assertThat(buffer).hasToString("Hello. A trim happened   here");

        buffer = new StringBuffer()
                .append("    First   ")
                .trim()
                .append("  Second  ")
                .trim()
                .append("  Third   ")
                .trim();

        assertThat(buffer).hasToString("First  Second  Third");
    }

}
