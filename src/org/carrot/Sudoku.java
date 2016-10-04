package org.carrot;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by agnie on 6/27/2016.
 */
class Sudoku extends ConstraintSatisfactionProblem {

    private Constraint constraint;
    private Solution solution;
    private boolean domainHeuristic;
    private boolean variableHeuristic;
    private ArrayList<ArrayList<Integer>> accessPoints;
    private int[][] accessTable;

    Sudoku(int size) {
        constraint = new Constraint();
        solution = new Solution(size);
        setInitialDomain(size);
        domainHeuristic = false;
    }

    Sudoku(int[][] partialSolution) {
        constraint = new Constraint();
        solution = new Solution(partialSolution);
        setInitialDomain(partialSolution.length);
        domainHeuristic = false;
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
    ArrayList<Integer> getDomain(int rowNumber, int columnNumber) {
        ArrayList<Integer> domain = solution.getVariables()[rowNumber][columnNumber].getDomain().getValues();
        if (domainHeuristic){
            int[][] values = solution.getValues();
            int[] numbers = Utils.sortByOccurrence(values);
            ArrayList<Integer> allowedNumbers = new ArrayList<Integer>();
            for (int number : numbers){
                if (domain.contains(number)){
                    allowedNumbers.add(number);
                }
            }
            domain = allowedNumbers;
        }
        return domain;
    }

    @Override
    Domain createDomain(int size) {
        ArrayList<Integer> domain = new ArrayList<Integer>();
        for (int i = 1; i < size + 1; i++) {
            domain.add(i);
        }
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
        for (int i = rowNumber+1; i < solution.getVariables().length; i++) {
            if (isUnsolvedVariable(i, columnNumber)) {
                solution.getVariables()[i][columnNumber].getDomain().removeValue(number);
            }
        }
        for (int i = columnNumber+1; i < solution.getVariables()[rowNumber].length; i++) {
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
                    if (i+yDeviation<=rowNumber || j+xDeviation<=columnNumber){
                        continue;
                    }
                    solution.getVariables()[i+yDeviation][j+xDeviation].getDomain().removeValue(number);
                }
            }
        }
    }

    @Override
    void resetDomain(int rowNumber, int columnNumber) {
        for (int i = rowNumber+1; i < solution.getVariables().length; i++) {
//            if (isUnsolvedVariable(i, columnNumber)) {
                solution.getVariables()[i][columnNumber].getDomain().revertState();
//            }
        }
        for (int i = columnNumber+1; i < solution.getVariables()[rowNumber].length; i++) {
//            if (isUnsolvedVariable(rowNumber, i)) {
                solution.getVariables()[rowNumber][i].getDomain().revertState();
//            }
        }
        int gridSize = (int) Math.sqrt(solution.getVariables().length);
        int gridNumber = solution.getGridNumber(rowNumber,columnNumber,gridSize);
        int xDeviation = (gridNumber % gridSize) * gridSize;
        int yDeviation = (int) Math.floor(gridNumber / gridSize) * gridSize;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
//                if (isUnsolvedVariable(i+yDeviation, j+xDeviation)){
                    if (i+yDeviation<=rowNumber || j+xDeviation<=columnNumber){
                        continue;
                    }
                    solution.getVariables()[i+yDeviation][j+xDeviation].getDomain().revertState();
//                }
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

    @Override
    void setDomainHeuristic(boolean value) {
        domainHeuristic = value;
    }

    @Override
    boolean checkDomainHeuristic() {
        return domainHeuristic;
    }

    @Override
    void setVariableHeuristic(boolean value) {
        variableHeuristic=value;
        if (variableHeuristic){
            setAccessPoints();
        }
    }

    @Override
    boolean checkVariableHeuristic() {
        return variableHeuristic;
    }


    private void setAccessPoints(){
        accessPoints = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<solution.getVariables().length; i++){
            for (int j=0; j<solution.getVariables().length; j++){
                ArrayList<Integer> coordinates = new ArrayList<Integer>();
                coordinates.add(i);
                coordinates.add(j);
                accessPoints.add(coordinates);
            }
        }
        Collections.shuffle(accessPoints);
        createAccessTable();
    }

    private void createAccessTable(){
        accessTable = new int[solution.getVariables().length][solution.getVariables().length];
        for (int i=0; i<accessPoints.size(); i++){
            accessTable[accessPoints.get(i).get(0)][accessPoints.get(i).get(1)] = i;
        }
    }

    @Override
    ArrayList<Integer> getNextPosition(int rowNumber, int columnNumber) {
        if (!variableHeuristic){
            return solution.getNextPosition(rowNumber, columnNumber);
        }
        int index = accessTable[rowNumber][columnNumber];
        if (index!=accessPoints.size()-1){
            return accessPoints.get(index+1);
        }
        else return null;
    }

    @Override
    ArrayList<ArrayList<Integer>> getAccessPoints() {
        return accessPoints;
    }


}
