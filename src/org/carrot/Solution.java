package org.carrot;

import java.util.ArrayList;

/**
 * Created by agnie on 6/21/2016.
 */
public class Solution {

    private ArrayList<Integer> values;

    public Solution(int size){
        values = new ArrayList<Integer>(size);
    }

    public Solution(ArrayList<Integer> partialSolution){
        values = partialSolution;
    }

}
