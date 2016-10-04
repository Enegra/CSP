package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/21/2016.
 */
class Solution {

    private Variable[][] variables;

    Solution(int size) {
        variables = new Variable[size][size];
        for (int i = 0; i < variables.length; i++) {
            for (int j = 0; j < variables[i].length; j++) {
                variables[i][j] = new Variable(0);
            }
        }
    }

    Solution(int[][] partialSolution) {
        variables = new Variable[partialSolution.length][partialSolution.length];
        for (int i = 0; i < partialSolution.length; i++) {
            for (int j = 0; j < partialSolution[i].length; j++) {
                variables[i][j] = new Variable(partialSolution[i][j]);
            }
        }
    }

    Variable[][] getVariables() {
        return variables;
    }

    Variable[] getRow(int rowNumber) {
        return variables[rowNumber];
    }

    Variable[] getColumn(int columnNumber) {
        Variable[] column = new Variable[variables.length];
        for (int i = 0; i < variables.length; i++) {
            column[i] = variables[i][columnNumber];
        }
        return column;
    }

    Variable[][] getGrid(int rowNumber, int columnNumber) {
        int size = (int) Math.sqrt(variables.length);
        int gridNumber = getGridNumber(rowNumber, columnNumber, size);
        int xDeviation = (gridNumber % size) * size;
        int yDeviation = (int) Math.floor(gridNumber / size) * size;
        return calculateGrid(xDeviation, yDeviation, variables);
    }

    private Variable[][] calculateGrid(int xDeviation, int yDeviation, Variable[][] content) {
        Variable[][] grid = new Variable[(int) Math.sqrt(content.length)][(int) Math.sqrt(content.length)];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = content[i + yDeviation][j + xDeviation];
            }
        }
        return grid;
    }

    int getGridNumber(int rowNumber, int columnNumber, int size) {
        int xPosition = columnNumber / size;
        int yPosition = rowNumber / size;
        int gridNumber = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == yPosition && j == xPosition) {
                    break;
                }
                gridNumber++;
            }
            if (i == yPosition) {
                break;
            }
        }
        return gridNumber;
    }

    ArrayList<Integer> getNextPosition(int rowNumber, int columnNumber) {
        if (rowNumber == variables.length - 1 && columnNumber == variables.length - 1) {
            return null;
        }
        ArrayList<Integer> nextPosition = new ArrayList<Integer>();
        if (columnNumber == variables.length - 1) {
            nextPosition.add(rowNumber + 1);
            nextPosition.add(0);
        } else {
            nextPosition.add(rowNumber);
            nextPosition.add(columnNumber + 1);
        }
        return nextPosition;
    }

    int[][] getValues(){
        int[][] values = new int[variables.length][variables.length];
        for (int i=0; i<variables.length; i++){
            for (int j=0; j<variables.length; j++){
                values[i][j] = variables[i][j].getValue();
            }
        }
        return values;
    }

}
