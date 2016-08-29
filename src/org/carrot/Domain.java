package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/21/2016.
 */
class Domain {

    private ArrayList<Integer> values;
    private ArrayList<Domain> states;

    Domain(ArrayList<Integer> values) {
        this.values = values;
        states = new ArrayList<Domain>();
    }

    Domain(ArrayList<Integer> values, ArrayList<Domain> states) {
        this.values = values;
        this.states = states;
    }

    ArrayList<Integer> getValues() {
        return values;
    }

    private void addState() {
        ArrayList<Integer> oldValues = (ArrayList<Integer>)values.clone();
        states.add(new Domain(oldValues, states));
    }

    void revertState() {
        if (states.size()>0){
            System.out.println("CURRENT VALUES: " + values.toString());
                values = states.get(states.size() - 1).values;
            System.out.println("OLD VALUES: " + values.toString());
                states.remove(states.size() - 1);
            }
    }

    void remove(int index) {
        addState();
        values.remove(index);
    }

    void removeValue(int value) {
       if (values.contains(value)){
           addState();
           values.remove(values.indexOf(value));
       }
    }

    boolean isEmpty() {
        return values.isEmpty();
    }

    void setValues(ArrayList<Integer> values){
        this.values = values;
    }

}
