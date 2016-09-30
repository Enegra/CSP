package org.carrot;

/**
 * Created by agnie on 6/21/2016.
 */
class Constraint {

    boolean rowContains(Variable[] row, int number) {
        for (Variable rowEntry : row) {
            if (rowEntry.getValue() == number) {
                return true;
            }
        }
        return false;
    }

    boolean columnContains(Variable[] column, int number) {
        for (Variable columnEntry : column) {
            if (columnEntry.getValue() == number) {
                return true;
            }
        }
        return false;
    }

    boolean gridContains(Variable[][] grid, int number) {
        for (Variable[] gridRow : grid) {
            for (Variable rowEntry : gridRow) {
                if (rowEntry.getValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean diagonalContains(Variable[][] variables, int number, int row, int column){
        int currentRow = row;
        int currentColumn = column;
        while (currentRow<variables.length-1 && currentColumn<variables.length-1){
                ++currentRow;
                ++currentColumn;
                if (variables[currentRow][currentColumn].getValue()==number){
                    return true;
                }
        }
        currentRow = row;
        currentColumn = column;
        while (currentRow>0 && currentColumn>0){
            --currentRow;
            --currentColumn;
            if (variables[currentRow][currentColumn].getValue()==number){
                return true;
            }
        }
        currentRow = row;
        currentColumn = column;
        while (currentRow<variables.length-1 && currentColumn>0){
            ++currentRow;
            --currentColumn;
            if (variables[currentRow][currentColumn].getValue()==number){
                return true;
            }
        }
        currentRow = row;
        currentColumn = column;
        while (currentRow>0 && currentColumn<variables.length-1){
            --currentRow;
            ++currentColumn;
            if (variables[currentRow][currentColumn].getValue()==number){
                return true;
            }
        }
        return false;
    }

}
