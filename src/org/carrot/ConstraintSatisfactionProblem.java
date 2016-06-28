package org.carrot;

/**
 * Created by agnie on 6/22/2016.
 */
public abstract class ConstraintSatisfactionProblem {

    abstract boolean satisfiesConstraints(int rowNumber, int columnNumber, int number);
    abstract Domain createDomain(int size);
    abstract boolean isSolved();
    abstract Domain getDomain();
    abstract Solution getSolution();
    abstract boolean isUnsolvedVariable();
}
