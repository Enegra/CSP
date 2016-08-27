package org.carrot;

/**
 * Created by agnie on 6/22/2016.
 */
class Variable {
    private int value;
    private Domain domain;

    Variable(int value, Domain domain) {
        this.value = value;
        this.domain = domain;
    }

    Variable(int value) {
        this.value = value;
        this.domain = null;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    Domain getDomain() {
        return domain;
    }

    void setDomain(Domain domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
