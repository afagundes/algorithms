package br.com.ari.algo.algorithms;

import java.util.Arrays;

public class HourGlassSum {

    private final String input;
    private int[][] arr;

    public HourGlassSum(String input) {
        this.input = input;
    }

    /**
     * Retorna a maior soma de ampulhetas em um determinado input
     */
    public int solve() {
        int sum = 0;
        this.parseInput();

        // linhas
        for (int i = 0; i < input.length(); i++) {
            // colunas
            for (int j = 0; j < input.length(); j++) {
                sum = Math.max(sum, this.sumHourGlass(j, i));
            }
        }

        return sum;
    }

    /**
     * Transforma a string do input em um array bidimensional
     */
    private void parseInput() {
        this.arr = Arrays.stream(input.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
    }

    private int sumHourGlass(int currX, int currY) {
        boolean[][] hourGlassPath = new boolean[][]{
                {true, true, true},
                {false, true, false},
                {true, true, true},
        };

        int sum = 0;
        final int hourGlassSize = 3;

        // linhas
        for (int i = 0, indexY = currY; i < hourGlassSize && indexY < arr.length; i++, indexY++) {
            // colunas
            for (int j = 0, indexX = currX; j < hourGlassSize && indexX < arr.length; j++, indexX++) {
                if (hourGlassPath[i][j]) {
                    sum += arr[indexY][indexX];
                }
            }
        }

        return sum;
    }

}
