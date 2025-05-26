package br.com.ari.algo.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HourGlassSumTest {

    @Test
    void testHourGlassSum() {
        var input1 = """
                  1 1 1 0 0 0
                  0 1 0 0 0 0
                  1 1 1 0 0 0
                  0 0 0 0 0 0
                  0 0 0 0 0 0
                  0 0 0 0 0 0
                """;

        var input2 = """
                  1 1 1 0 0 0
                  0 1 0 0 0 0
                  1 1 1 1 2 0
                  0 0 0 5 0 0
                  0 0 4 1 1 0
                  0 0 0 0 0 0
                """;

        var result1 = new HourGlassSum(input1).solve();
        var result2 = new HourGlassSum(input2).solve();

        assertThat(result1).isEqualTo(7);
        assertThat(result2).isEqualTo(15);
    }

}
