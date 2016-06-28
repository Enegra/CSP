package org.carrot;

/**
 * Created by agnie on 6/27/2016.
 */
public class Solver {

    private ConstraintSatisfactionProblem constraintSatisfactionProblem;

    public Solver(ConstraintSatisfactionProblem constraintSatisfactionProblem){
        this.constraintSatisfactionProblem = constraintSatisfactionProblem;
    }

    public boolean backtrack(int rowNumber, int columnNumber){
        if (constraintSatisfactionProblem.isSolved()){
            return true;
        }
        if (!constraintSatisfactionProblem.isUnsolvedVariable(rowNumber,columnNumber)){

        }
        return false;
    }

}
