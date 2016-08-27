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

}
