package org.carrot;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by agnie on 9/22/2016.
 */
public class QueensProblem extends ConstraintSatisfactionProblem {

    private Constraint constraint;
    private Solution solution;
    private boolean domainHeuristic;
    private boolean variableHeuristic;
    private ArrayList<ArrayList<Integer>> accessPoints;
    private int[][] accessTable;

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
    ArrayList<Integer> getDomain(int rowNumber, int columnNumber) {
        ArrayList<Integer> domain = solution.getVariables()[rowNumber][columnNumber].getDomain().getValues();
        if (domainHeuristic){
            //todo
            int[][] values = solution.getValues();
            int[] occurences = Utils.getOccurrences(values);
            if (occurences[0]/values.length > 0.75){
                Collections.swap(domain,0,1);
            }
        }
        return domain;
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
                if (solution.getVariables()[i][j].getValue() == 1){
                    queenCount++;
                }
            }
        }
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
            for (int i = rowNumber+1; i < variables.length; i++) {
                variables[i][columnNumber].getDomain().removeValue(number);
            }
            for (int i = columnNumber+1; i < variables[rowNumber].length; i++) {
                variables[rowNumber][i].getDomain().removeValue(number);
            }
            int currentRow = rowNumber;
            int currentColumn = columnNumber;
            while (currentRow<variables.length-1 && currentColumn<variables.length-1){
                ++currentRow;
                ++currentColumn;
                variables[currentRow][currentColumn].getDomain().removeValue(number);
            }
//            currentRow = rowNumber;
//            currentColumn = columnNumber;
//            while (currentRow>0 && currentColumn>0){
//                --currentRow;
//                --currentColumn;
//                variables[currentRow][currentColumn].getDomain().removeValue(number);
//            }
            currentRow = rowNumber;
            currentColumn = columnNumber;
            while (currentRow<variables.length-1 && currentColumn>0){
                ++currentRow;
                --currentColumn;
                variables[currentRow][currentColumn].getDomain().removeValue(number);
            }
//            currentRow = rowNumber;
//            currentColumn = columnNumber;
//            while (currentRow>0 && currentColumn<variables.length-1){
//                --currentRow;
//                ++currentColumn;
//                variables[currentRow][currentColumn].getDomain().removeValue(number);
//            }
        }
    }

    @Override
    void resetDomain(int rowNumber, int columnNumber) {
        Variable[][] variables = solution.getVariables();
        for (int i = rowNumber+1; i < variables.length; i++) {
            variables[i][columnNumber].getDomain().revertState();
        }
        for (int i = columnNumber+1; i < variables[rowNumber].length; i++) {
            variables[rowNumber][i].getDomain().revertState();
        }
        int currentRow = rowNumber;
        int currentColumn = columnNumber;
        while (currentRow<variables.length-1 && currentColumn<variables.length-1){
            ++currentRow;
            ++currentColumn;
            variables[currentRow][currentColumn].getDomain().revertState();
        }
//        currentRow = rowNumber;
//        currentColumn = columnNumber;
//        while (currentRow>0 && currentColumn>0){
//            --currentRow;
//            --currentColumn;
//            variables[currentRow][currentColumn].getDomain().revertState();
//        }
        currentRow = rowNumber;
        currentColumn = columnNumber;
        while (currentRow<variables.length-1 && currentColumn>0){
            ++currentRow;
            --currentColumn;
            variables[currentRow][currentColumn].getDomain().revertState();
        }
//        currentRow = rowNumber;
//        currentColumn = columnNumber;
//        while (currentRow>0 && currentColumn<variables.length-1){
//            --currentRow;
//            ++currentColumn;
//            variables[currentRow][currentColumn].getDomain().revertState();
//        }
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

    @Override
    void setDomainHeuristic(boolean value) {
        domainHeuristic=value;
    }

    @Override
    boolean checkDomainHeuristic() {
        return domainHeuristic;
    }

    @Override
    void setVariableHeuristic(boolean value) {
        variableHeuristic=value;
        setAccessPoints();
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
