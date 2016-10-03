package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/27/2016.
 */
class Solver {

    private ConstraintSatisfactionProblem constraintSatisfactionProblem;

    Solver(ConstraintSatisfactionProblem constraintSatisfactionProblem) {
        this.constraintSatisfactionProblem = constraintSatisfactionProblem;
    }

    private boolean backtrack(int rowNumber, int columnNumber) {
        if (isSolved()) {
            return true;
        }
        ArrayList<Integer> nextPosition = getNextPosition(rowNumber, columnNumber);
        if (!isUnsolvedVariable(rowNumber, columnNumber)) {
            if (nextPosition!=null){
                return backtrack(nextPosition.get(0), nextPosition.get(1));
            }
        }
        ArrayList<Integer> domain = getDomain(rowNumber, columnNumber);
        for (int number : domain) {
            if (!satisfiesConstraints(rowNumber, columnNumber, number)) {
                continue;
            }
            setValue(rowNumber, columnNumber, number);
            if (nextPosition != null) {
                if (backtrack(nextPosition.get(0), nextPosition.get(1))) {
                    return true;
                } else {
                    setValue(rowNumber, columnNumber, 0);
                }
            } else {
                if (isSolved()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkForward(int rowNumber, int columnNumber) {
        if (isSolved()) {
            return true;
        }
        ArrayList<Integer> nextPosition = getNextPosition(rowNumber, columnNumber);
        if (!isUnsolvedVariable(rowNumber, columnNumber)) {
            if (nextPosition!=null)
            return checkForward(nextPosition.get(0), nextPosition.get(1));
        }
        if (!getDomain(rowNumber, columnNumber).isEmpty()) {
            ArrayList<Integer> domain = getDomain(rowNumber, columnNumber);
            for (int number : domain) {
                if (!satisfiesConstraints(rowNumber, columnNumber, number)) {
                    continue;
                }
                setValue(rowNumber, columnNumber, number);
                limitDomain(number, rowNumber, columnNumber);
                if (!constraintSatisfactionProblem.domainsValid()) {
                    setValue(rowNumber, columnNumber, 0);
                    resetDomain(rowNumber, columnNumber);
                    continue;
                }
                if (nextPosition != null) {
                    if (checkForward(nextPosition.get(0), nextPosition.get(1))) {
                        return true;
                    } else {
                        setValue(rowNumber, columnNumber, 0);
                        resetDomain(rowNumber, columnNumber);
                    }
                } else {
                    if ((isSolved())) {
                        return true;
                    }
                }
            }
        }
    return false;
}


    void solve() {
//            if (checkForward(getStartingPosition().get(0), getStartingPosition().get(1))) {
        if (backtrack(getStartingPosition().get(0), getStartingPosition().get(1))) {
            System.out.println("Problem solved");
        } else {
            System.out.println("No solution available");
        }
    }

    private ArrayList<Integer>getStartingPosition(){
        ArrayList<Integer> startPoint = new ArrayList<Integer>();
        if (!constraintSatisfactionProblem.checkVariableHeuristic()){
            startPoint.add(0);
            startPoint.add(0);
        }
        else {
            startPoint.add(constraintSatisfactionProblem.getAccessPoints().get(0).get(0));
            startPoint.add(constraintSatisfactionProblem.getAccessPoints().get(0).get(1));
        }
        return startPoint;
    }

    private ArrayList<Integer> getNextPosition(int rowNumber, int columnNumber) {
        return constraintSatisfactionProblem.getSolution().getNextPosition(rowNumber, columnNumber);
    }

    private void setValue(int rowNumber, int columnNumber, int value) {
        constraintSatisfactionProblem.getSolution().getVariables()[rowNumber][columnNumber].setValue(value);
    }

    private boolean satisfiesConstraints(int rowNumber, int columnNumber, int number) {
        return constraintSatisfactionProblem.satisfiesConstraints(rowNumber, columnNumber, number);
    }

    private boolean isUnsolvedVariable(int rowNumber, int columnNumber) {
        return constraintSatisfactionProblem.isUnsolvedVariable(rowNumber, columnNumber);
    }

    private boolean isSolved() {
        return constraintSatisfactionProblem.isSolved();
    }

    private ArrayList<Integer> getDomain(int rowNumber, int columnNumber) {
        return constraintSatisfactionProblem.getDomain(rowNumber,columnNumber);
    }

    private void limitDomain(int number, int rowNumber, int columnNumber) {
        constraintSatisfactionProblem.limitDomain(number, rowNumber, columnNumber);
    }

    private void resetDomain(int rowNumber, int columnNumber) {
        constraintSatisfactionProblem.resetDomain(rowNumber, columnNumber);
    }


}
