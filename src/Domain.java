import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by agnie on 6/21/2016.
 */
public class Domain {

    private ArrayList<Integer> domain;
    private ArrayList<Domain> states;

    public Domain(ArrayList<Integer> values) {
        domain = values;
        states = new ArrayList<Domain>();
    }

    public Domain(ArrayList<Integer> values, ArrayList<Domain> states) {
        domain = values;
        this.states = states;
    }

    public ArrayList<Integer> getDomain() {
        return domain;
    }

    private void addState() {
        states.add(new Domain(domain,states));
    }
    
    public void revertState(){
        domain = states.get(states.size()-1).domain;
        states.remove(states.size()-1);
    }
    
    //// TODO: 6/21/2016 wykreślanie z domeny 

}
