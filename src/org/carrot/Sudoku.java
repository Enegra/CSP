package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/27/2016.
 */
class Sudoku extends ConstraintSatisfactionProblem {

    Constraint constraint;
    Solution solution;

    Sudoku(int size) {
        constraint = new Constraint();
        solution = new Solution(size);
        setInitialDomain(size);
    }

    Sudoku(int[][] partialSolution) {
        constraint = new Constraint();
        solution = new Solution(partialSolution);
        setInitialDomain(partialSolution.length);
    }

    @Override
    boolean satisfiesConstraints(int rowNumber, int columnNumber, int number) {
        if (constraint.rowContains(solution.getRow(rowNumber), number)) {
            return false;
        }
        if (constraint.columnContains(solution.getColumn(columnNumber), number)) {
            return false;
        }
        if (constraint.gridContains(solution.getGrid(rowNumber, columnNumber), number)) {
            return false;
        }
        return true;
    }

    @Override
    Domain createDomain(int size) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i < size + 1; i++) {
            numbers.add(i);
        }
        return new Domain(numbers);
    }

    @Override
    void setInitialDomain(int size) {
        Variable[][] variables = solution.getVariables();
        for (int i = 0; i < variables.length; i++) {
            for (int j = 0; j < variables[i].length; j++) {
                variables[i][j].setDomain(createDomain(size));
            }
        }
    }

    @Override
    boolean isSolved() {
        for (int i = 0; i < solution.getVariables().length; i++) {
            for (int j = 0; j < solution.getVariables()[i].length; j++) {
                if (solution.getVariables()[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    Solution getSolution() {
        return solution;
    }

    @Override
    boolean isUnsolvedVariable(int rowNumber, int columnNumber) {
        return solution.getVariables()[rowNumber][columnNumber].getValue() == 0;
    }

    @Override
    void limitDomain(int number, int rowNumber, int columnNumber) {
        //do that for every conflict
        for (int i = 0; i < solution.getVariables().length; i++) {
            if (isUnsolvedVariable(i, columnNumber)) {
                solution.getVariables()[i][columnNumber].getDomain().removeValue(number);
            }
        }
        for (int i = 0; i < solution.getVariables()[rowNumber].length; i++) {
            if (isUnsolvedVariable(rowNumber, i)) {
                solution.getVariables()[rowNumber][i].getDomain().removeValue(number);
            }
        }
        int gridSize = (int) Math.sqrt(solution.getVariables().length);
        int gridNumber = solution.getGridNumber(rowNumber,columnNumber,gridSize);
        int xDeviation = (gridNumber % gridSize) * gridSize;
        int yDeviation = (int) Math.floor(gridNumber / gridSize) * gridSize;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (isUnsolvedVariable(i+yDeviation, j+xDeviation)){
                    if (i+yDeviation==rowNumber || j+xDeviation==columnNumber){
                        continue;
                    }
                    solution.getVariables()[i+yDeviation][j+xDeviation].getDomain().removeValue(number);
                }
            }
        }
    }

    @Override
    void resetDomain(int number, int rowNumber, int columnNumber) {
        for (int i = 0; i < solution.getVariables().length; i++) {
            if (isUnsolvedVariable(i, columnNumber)) {
                solution.getVariables()[i][columnNumber].getDomain().revertState(number);
            }
        }
        for (int i = 0; i < solution.getVariables()[rowNumber].length; i++) {
            if (isUnsolvedVariable(rowNumber, i)) {
                solution.getVariables()[i][columnNumber].getDomain().revertState(number);
            }
        }
        int gridSize = (int) Math.sqrt(solution.getVariables().length);
        int gridNumber = solution.getGridNumber(rowNumber,columnNumber,gridSize);
        int xDeviation = (gridNumber % gridSize) * gridSize;
        int yDeviation = (int) Math.floor(gridNumber / gridSize) * gridSize;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (isUnsolvedVariable(i+yDeviation, j+xDeviation)){
                    if (i+yDeviation==rowNumber || j+xDeviation==columnNumber){
                        continue;
                    }
                    solution.getVariables()[i][columnNumber].getDomain().revertState(number);
                }
            }
        }
    }

    @Override
    boolean domainsValid() {
        Variable[][] variables = solution.getVariables();
        for (Variable[] variableRow : variables){
            for (Variable variable : variableRow){
                if (variable.getDomain().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }


}
