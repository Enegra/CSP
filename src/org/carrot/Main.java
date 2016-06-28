package org.carrot;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = {{4, 0, 0, 0}, {0, 1, 2, 0}, {0, 4, 3, 0}, {0, 0, 0, 2}};
        int[][] matrix2 = {{0, 0, 1, 8, 0, 0, 7, 0, 0}, {6, 3, 0, 0, 4, 0, 0, 1, 8}, {2, 0, 0, 0, 9, 0, 0, 0, 4}, {5, 6, 0, 0, 0, 0, 0, 9, 3}, {0, 1, 2, 0, 0, 0, 6, 4, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {7, 0, 0, 0, 3, 0, 0, 0, 6}, {8, 5, 0, 0, 7, 0, 0, 2, 9}, {0, 0, 3, 0, 0, 5, 4, 0, 0}};
        Sudoku sudoku = new Sudoku(matrix2);
        Solver solver = new Solver(sudoku);
        solver.solve();
        System.out.println(Arrays.deepToString(sudoku.getSolution().getVariables()));
    }
}
