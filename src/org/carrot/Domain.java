package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/21/2016.
 */
public class Domain {

    private ArrayList<Integer> values;
    private ArrayList<Domain> states;

    public Domain(ArrayList<Integer> values) {
        this.values = values;
        states = new ArrayList<Domain>();
    }

    public Domain(ArrayList<Integer> values, ArrayList<Domain> states) {
        this.values = values;
        this.states = states;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    private void addState() {
        states.add(new Domain(values,states));
    }
    
    public void revertState(){
        values = states.get(states.size()-1).values;
        states.remove(states.size()-1);
    }

    public void remove(int index){
        addState();
        values.remove(index);
    }
    //// TODO: 6/21/2016 wykreślanie z domeny 

}
