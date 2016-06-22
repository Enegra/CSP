package org.carrot;

/**
 * Created by agnie on 6/22/2016.
 */
public class Variable {
    private int value;
    private Domain domain;

    public Variable(int value, Domain domain){
        this.value = value;
        this.domain = domain;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public Domain getDomain(){
        return domain;
    }

}
