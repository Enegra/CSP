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
            return backtrack(nextPosition.get(0), nextPosition.get(1));
        }
        for (int number : getDomain(rowNumber, columnNumber).getValues()) {
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
            return checkForward(nextPosition.get(0), nextPosition.get(1));
        }
        if (!getDomain(rowNumber, columnNumber).isEmpty()) {
            ArrayList<Integer> domain = getDomain(rowNumber, columnNumber).getValues();
            System.out.println(domain.toString());
            for (int number : domain) {
                if (!satisfiesConstraints(rowNumber, columnNumber, number)) {
                    continue;
                }
                System.out.println("ROW " + rowNumber + " COLUMN " + columnNumber + " NUMBER " + number);
                setValue(rowNumber, columnNumber, number);
                limitDomain(number, rowNumber, columnNumber);
//                if (!constraintSatisfactionProblem.domainsValid()) {
//                    setValue(rowNumber, columnNumber, 0);
//                    resetDomain(rowNumber, columnNumber);
//                    continue;
//                }
                if (nextPosition != null) {
                    if (checkForward(nextPosition.get(0), nextPosition.get(1))) {
                        return true;
                    } else {
                        System.out.println("ROW " + rowNumber + " COLUMN " + columnNumber + " FAILED");
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
            if (checkForward(0, 0)) {
//        if (backtrack(0, 0)) {
            System.out.println("Problem solved");
        } else {
            System.out.println("No solution available");
        }
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

    private Domain getDomain(int rowNumber, int columnNumber) {
        return constraintSatisfactionProblem.getSolution().getVariables()[rowNumber][columnNumber].getDomain();
    }

    private void limitDomain(int number, int rowNumber, int columnNumber) {
        constraintSatisfactionProblem.limitDomain(number, rowNumber, columnNumber);
    }

    private void resetDomain(int rowNumber, int columnNumber) {
        constraintSatisfactionProblem.resetDomain(rowNumber, columnNumber);
    }


}
