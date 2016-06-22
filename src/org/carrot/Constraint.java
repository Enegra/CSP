package org.carrot;

/**
 * Created by agnie on 6/21/2016.
 */
public class Constraint {

    public boolean rowContains(int row[], int number) {
        for (int rowEntry : row) {
            if (rowEntry == number) {
                return true;
            }
        }
        return false;
    }

    public boolean columnContains(int[]column, int number) {
        for (int columnEntry : column) {
            if (columnEntry == number) {
                return true;
            }
        }
        return false;
    }

    public boolean gridContains(int[][] grid, int number) {
        for (int[] gridRow : grid) {
            for (int rowEntry : gridRow) {
                if (rowEntry == number) {
                    return true;
                }
            }
        }
        return false;
    }

}
