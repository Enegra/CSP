package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/27/2016.
 */
public class Sudoku extends ConstraintSatisfactionProblem {

    Constraint constraint;
    Solution solution;
    Domain domain;

    public Sudoku(int size){
        constraint = new Constraint();
        domain = createDomain(size);
        solution = new Solution(size,domain);
    }

    public Sudoku(int[][] partialSolution){
        constraint = new Constraint();
        domain = createDomain(partialSolution.length);
        solution = new Solution(partialSolution,domain);
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
    public Domain getDomain(){
        return domain;
    }

    @Override
    public Solution getSolution(){
        return solution;
    }

    @Override
    boolean isUnsolvedVariable() {
        return false;
    }


}
