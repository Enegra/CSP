package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/22/2016.
 */
public abstract class ConstraintSatisfactionProblem {

    abstract boolean satisfiesConstraints(int rowNumber, int columnNumber, int number);

    abstract ArrayList<Integer> getDomain(int rowNumber, int columnNumber);

    abstract Domain createDomain(int size);

    abstract void setInitialDomain(int size);

    abstract boolean isSolved();

    abstract Solution getSolution();

    abstract boolean isUnsolvedVariable(int rowNumber, int columnNumber);

    abstract void limitDomain(int number, int rowNumber, int columnNumber);

    abstract void resetDomain(int rowNumber, int columnNumber);

    abstract boolean domainsValid();

    abstract void setDomainHeuristic();

    abstract void setVariableHeuristic();
}
