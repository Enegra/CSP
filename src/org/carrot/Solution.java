package org.carrot;

/**
 * Created by agnie on 6/21/2016.
 */
public class Solution {

    private Variable[][] variables;
    private Domain domain;

    public Solution(int size, Domain domain) {
        variables = new Variable[size][size ];
        this.domain = domain;
    }

    public Solution(int[][] partialSolution, Domain domain) {
        this.domain = domain;
        variables = new Variable[partialSolution.length][partialSolution.length];
        for (int i=0; i<partialSolution.length; i++) {
            for (int j=0; j<partialSolution[i].length; j++){
                variables[i][j] = new Variable(partialSolution[i][j], domain);
            }
        }
    }

    public Variable[][] getVariables(){
        return variables;
    }

    public Variable[] getRow(int rowNumber){
        return variables[rowNumber];
    }

    public Variable[] getColumn(int columnNumber){
        Variable[] column = new Variable[variables.length];
        for (int i=0; i<variables.length; i++){
            column[i] = variables[i][columnNumber];
        }
        return column;
    }

    public Variable[][] getGrid(int rowNumber, int columnNumber){
        return null;
    }

}
