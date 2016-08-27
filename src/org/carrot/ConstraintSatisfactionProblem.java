package org.carrot;

/**
 * Created by agnie on 6/22/2016.
 */
public abstract class ConstraintSatisfactionProblem {

    abstract boolean satisfiesConstraints(int rowNumber, int columnNumber, int number);

    abstract Domain createDomain(int size);

    abstract void setInitialDomain(int size);

    abstract boolean isSolved();

    abstract Solution getSolution();

    abstract boolean isUnsolvedVariable(int rowNumber, int columnNumber);

    abstract void limitDomain(int number, int rowNumber, int columnNumber);

    abstract void resetDomain(int number, int rowNumber, int columnNumber);

    abstract boolean domainsValid();
}
