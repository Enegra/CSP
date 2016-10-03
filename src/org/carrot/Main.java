package org.carrot;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        int[][] matrix = {{4, 0, 0, 0}, {0, 1, 2, 0}, {0, 4, 3, 0}, {0, 0, 0, 2}};
//        int[][] matrix2 = {{0, 0, 1, 8, 0, 0, 7, 0, 0}, {6, 3, 0, 0, 4, 0, 0, 1, 8}, {2, 0, 0, 0, 9, 0, 0, 0, 4}, {5, 6, 0, 0, 0, 0, 0, 9, 3}, {0, 1, 2, 0, 0, 0, 6, 4, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {7, 0, 0, 0, 3, 0, 0, 0, 6}, {8, 5, 0, 0, 7, 0, 0, 2, 9}, {0, 0, 3, 0, 0, 5, 4, 0, 0}};
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        int[][] matrix3 = sudokuGenerator.generate(3);
        System.out.println(Arrays.deepToString(matrix3));
        Sudoku sudoku = new Sudoku(matrix3);
        sudoku.setDomainHeuristic();
        sudoku.setVariableHeuristic();
        Solver solver = new Solver(sudoku);
        solver.solve();
        System.out.println(Arrays.deepToString(sudoku.getSolution().getVariables()));

//        UserInterface userInterface = new UserInterface();
//        userInterface.displayPuzzle(matrix3);

//        int[][] matrix = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
//        QueensProblem queensProblem = new QueensProblem(8);
//        queensProblem.setDomainHeuristic();
//        System.out.println(Arrays.deepToString(queensProblem.getSolution().getVariables()));
//        Solver solver = new Solver(queensProblem);
//        solver.solve();
//        System.out.println(Arrays.deepToString(queensProblem.getSolution().getVariables()));

    }
}

