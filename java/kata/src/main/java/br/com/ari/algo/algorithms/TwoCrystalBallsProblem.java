package br.com.ari.algo.algorithms;

public class TwoCrystalBallsProblem {

    private TwoCrystalBallsProblem() {}

    public static int solve(boolean[] distances) {
        final int step = (int) Math.floor(Math.sqrt(distances.length));

        int i = 0;
        for (; i < distances.length; i += step) {
            if (distances[i]) {
                break;
            }
        }

        i -= step;

        for (int j = 0; j <= step && i >= 0 && i < distances.length; j++, i++) {
            if (distances[i]) {
                return i;
            }
        }

        return -1;
    }

}
