package br.com.ari.algo.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TwoCrystalBallsProblemTest {

    @Test
    void testTwoCrystalBallsProblem() {
        boolean[] distances = new boolean[] { false, false, false, false, false, false, true, true, true, true };
        assertThat(TwoCrystalBallsProblem.solve(distances)).isEqualTo(6);

        distances = new boolean[] { false, false, true, true, true, true };
        assertThat(TwoCrystalBallsProblem.solve(distances)).isEqualTo(2);

        distances = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true };
        assertThat(TwoCrystalBallsProblem.solve(distances)).isEqualTo(12);

        distances = new boolean[] { false, false, false, false, false, true };
        assertThat(TwoCrystalBallsProblem.solve(distances)).isEqualTo(5);

        distances = new boolean[] { false, false, false, false, false, false };
        assertThat(TwoCrystalBallsProblem.solve(distances)).isEqualTo(-1);
    }

}
