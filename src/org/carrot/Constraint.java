package org.carrot;

/**
 * Created by agnie on 6/21/2016.
 */
public class Constraint {

    public boolean rowContains(Variable[] row, int number) {
        for (Variable rowEntry : row) {
            if (rowEntry.getValue() == number) {
                return true;
            }
        }
        return false;
    }

    public boolean columnContains(Variable[] column, int number) {
        for (Variable columnEntry : column) {
            if (columnEntry.getValue() == number) {
                return true;
            }
        }
        return false;
    }

    public boolean gridContains(Variable[][] grid, int number) {
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
