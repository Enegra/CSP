package org.carrot;

import java.util.ArrayList;

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
            ArrayList<Integer> nextPosition = constraintSatisfactionProblem.getSolution().getNextPosition(rowNumber,columnNumber);
            return backtrack(nextPosition.get(0), nextPosition.get(1));
        }
        for (int number : constraintSatisfactionProblem.getDomain().getValues()){
            if (!constraintSatisfactionProblem.satisfiesConstraints(rowNumber,columnNumber, number)){
                continue;
            }
            constraintSatisfactionProblem.getSolution().getVariables()[rowNumber][columnNumber].setValue(number);
            ArrayList<Integer> nextPosition = constraintSatisfactionProblem.getSolution().getNextPosition(rowNumber,columnNumber);
            if (nextPosition!=null){
                if (backtrack(nextPosition.get(0), nextPosition.get(1))){
                    return true;
                }
                else {
                    constraintSatisfactionProblem.getSolution().getVariables()[rowNumber][columnNumber].setValue(0);
                }
            }
            else {
                if (constraintSatisfactionProblem.isSolved()){
                    return true;
                }
            }
        }
        return false;
    }

    public void solve(){
        if (backtrack(0,0)){
            System.out.println("Problem solved");
        }
        else {
            System.out.println("No solution available");
        }
    }

}
