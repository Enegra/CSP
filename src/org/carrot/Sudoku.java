package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/27/2016.
 */
public class Sudoku extends ConstraintSatisfactionProblem {

    Constraint constraint;
    Solution solution;

    public Sudoku(int size){
        constraint = new Constraint();
        solution = new Solution(size);
        setInitialDomain(size);
    }

    public Sudoku(int[][] partialSolution){
        constraint = new Constraint();
        solution = new Solution(partialSolution);
        setInitialDomain(partialSolution.length);
    }

    @Override
    boolean satisfiesConstraints(int rowNumber, int columnNumber, int number) {
        if (constraint.rowContains(solution.getRow(rowNumber), number)){
            return false;
        }
        if (constraint.columnContains(solution.getColumn(columnNumber),number)){
            return false;
        }
        if (constraint.gridContains(solution.getGrid(rowNumber,columnNumber),number)){
            return false;
        }
        return true;
    }

    @Override
    Domain createDomain(int size) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i=1; i<size+1; i++){
            numbers.add(i);
        }
        return new Domain(numbers);
    }

    @Override
    void setInitialDomain(int size){
        Variable[][] variables = solution.getVariables();
        for (int i=0; i<variables.length; i++){
            for (int j=0; j<variables[i].length; j++){
                variables[i][j].setDomain(createDomain(size));
            }
        }
    }

    @Override
    boolean isSolved() {
        for (int i=0; i<solution.getVariables().length; i++){
            for (int j=0; j<solution.getVariables()[i].length; j++){
                if (solution.getVariables()[i][j].getValue()==0){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Solution getSolution(){
        return solution;
    }

    @Override
    boolean isUnsolvedVariable(int rowNumber, int columnNumber) {
        return solution.getVariables()[rowNumber][columnNumber].getValue()==0;
    }


}
