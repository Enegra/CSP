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
        if (isSolved()){
            return true;
        }
        if (!isUnsolvedVariable(rowNumber,columnNumber)){
            ArrayList<Integer> nextPosition = getNextPosition(rowNumber,columnNumber);
            return backtrack(nextPosition.get(0), nextPosition.get(1));
        }
        for (int number : constraintSatisfactionProblem.getDomain().getValues()){
            if (!satisfiesConstraints(rowNumber,columnNumber,number)){
                continue;
            }
            setValue(rowNumber,columnNumber,number);
            ArrayList<Integer> nextPosition = getNextPosition(rowNumber, columnNumber);
            if (nextPosition!=null){
                if (backtrack(nextPosition.get(0), nextPosition.get(1))){
                    return true;
                }
                else {
                    setValue(rowNumber,columnNumber,0);
                }
            }
            else {
                if (isSolved()){
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

    private ArrayList<Integer> getNextPosition(int rowNumber, int columnNumber){
        return constraintSatisfactionProblem.getSolution().getNextPosition(rowNumber,columnNumber);
    }

    private void setValue(int rowNumber, int columnNumber, int value){
        constraintSatisfactionProblem.getSolution().getVariables()[rowNumber][columnNumber].setValue(value);
    }

    private boolean satisfiesConstraints(int rowNumber, int columnNumber, int number){
        return constraintSatisfactionProblem.satisfiesConstraints(rowNumber,columnNumber, number);
    }

    private boolean isUnsolvedVariable(int rowNumber, int columnNumber){
        return constraintSatisfactionProblem.isUnsolvedVariable(rowNumber,columnNumber);
    }

    private boolean isSolved(){
        return constraintSatisfactionProblem.isSolved();
    }

}
