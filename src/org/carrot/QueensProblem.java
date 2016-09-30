package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 9/22/2016.
 */
public class QueensProblem extends ConstraintSatisfactionProblem {

    Constraint constraint;
    Solution solution;

    QueensProblem(int size){
        constraint = new Constraint();
        solution = new Solution(size);
        setInitialDomain(size);
    }

    QueensProblem(int[][] partialSolution){
        constraint = new Constraint();
        solution = new Solution(partialSolution);
        setInitialDomain(partialSolution.length);
    }

    @Override
    boolean satisfiesConstraints(int rowNumber, int columnNumber, int number) {
        if (number==1){
            if (constraint.rowContains(solution.getRow(rowNumber), 1)) {
                return false;
            }
            if (constraint.columnContains(solution.getColumn(columnNumber), 1)) {
                return false;
            }
            if (constraint.diagonalContains(solution.getVariables(), 1, rowNumber,columnNumber)){
                return false;
            }
        }

        return true;
    }

    @Override
    Domain createDomain(int size) {
        ArrayList<Integer> domain = new ArrayList<Integer>();
        domain.add(1);
        domain.add(2);
        return new Domain(domain);
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
        int queenCount=0;
        for (int i = 0; i < solution.getVariables().length; i++) {
            for (int j = 0; j < solution.getVariables()[i].length; j++) {
//                if (solution.getVariables()[i][j].getValue() == 0) {
//                    return false;
//                }
                if (solution.getVariables()[i][j].getValue() == 1){
                    queenCount++;
                }
            }
        }
//        return true;
        return queenCount==solution.getVariables().length;
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
        if (number==1){
            Variable[][] variables = solution.getVariables();
            for (int i = 0; i < variables.length; i++) {
                variables[i][columnNumber].getDomain().removeValue(number);
            }
            for (int i = 0; i < variables[rowNumber].length; i++) {
                variables[rowNumber][i].getDomain().removeValue(number);
            }
            int currentRow = rowNumber;
            int currentColumn = columnNumber;
            while (currentRow<variables.length-1 && currentColumn<variables.length-1){
                ++currentRow;
                ++currentColumn;
                variables[currentRow][currentColumn].getDomain().removeValue(number);
            }
            currentRow = rowNumber;
            currentColumn = columnNumber;
            while (currentRow>0 && currentColumn>0){
                --currentRow;
                --currentColumn;
                variables[currentRow][currentColumn].getDomain().removeValue(number);
            }
            currentRow = rowNumber;
            currentColumn = columnNumber;
            while (currentRow<variables.length-1 && currentColumn>0){
                ++currentRow;
                --currentColumn;
                variables[currentRow][currentColumn].getDomain().removeValue(number);
            }
            currentRow = rowNumber;
            currentColumn = columnNumber;
            while (currentRow>0 && currentColumn<variables.length-1){
                --currentRow;
                ++currentColumn;
                variables[currentRow][currentColumn].getDomain().removeValue(number);
            }
        }
    }

    @Override
    void resetDomain(int rowNumber, int columnNumber) {
        Variable[][] variables = solution.getVariables();
        for (int i = 0; i < variables.length; i++) {
            variables[i][columnNumber].getDomain().revertState();
        }
        for (int i = 0; i < variables[rowNumber].length; i++) {
            variables[rowNumber][i].getDomain().revertState();
        }
        int currentRow = rowNumber;
        int currentColumn = columnNumber;
        while (currentRow<variables.length-1 && currentColumn<variables.length-1){
            ++currentRow;
            ++currentColumn;
            variables[currentRow][currentColumn].getDomain().revertState();
        }
        currentRow = rowNumber;
        currentColumn = columnNumber;
        while (currentRow>0 && currentColumn>0){
            --currentRow;
            --currentColumn;
            variables[currentRow][currentColumn].getDomain().revertState();
        }
        currentRow = rowNumber;
        currentColumn = columnNumber;
        while (currentRow<variables.length-1 && currentColumn>0){
            ++currentRow;
            --currentColumn;
            variables[currentRow][currentColumn].getDomain().revertState();
        }
        currentRow = rowNumber;
        currentColumn = columnNumber;
        while (currentRow>0 && currentColumn<variables.length-1){
            --currentRow;
            ++currentColumn;
            variables[currentRow][currentColumn].getDomain().revertState();
        }
    }

    @Override
    boolean domainsValid() {
        Variable[][] variables = solution.getVariables();
        boolean eachRowContains = true;
        for (Variable[] variableRow : variables){
            boolean rowContains = false;
            for (Variable variable : variableRow){
                if (variable.getDomain().getValues().contains(1)){
                    rowContains = true;
                }
            }
            if (!rowContains){
                eachRowContains = false;
            }
        }
        return eachRowContains;
    }
}
