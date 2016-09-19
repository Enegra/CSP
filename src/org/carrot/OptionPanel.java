package org.carrot;

import javax.swing.*;

/**
 * Created by agnie on 9/19/2016.
 */
public class OptionPanel extends JPanel {

    private JComboBox problemChoiceComboBox;
    private JComboBox heuristicChoiceComboBox;

    void setupPanel(){
        //todo
    }

    private void setupProblemChoiceComboBox() {
        String[] problems = {"sudoku puzzle", "N queens problem"};
        problemChoiceComboBox = new JComboBox(problems);
        this.add(problemChoiceComboBox);

    }

    private void setupHeuristicChoiceComboBox(){
        String[] algorithms = {"sudoku puzzle", "N queens problem"};
        heuristicChoiceComboBox = new JComboBox(algorithms);
        this.add(problemChoiceComboBox);
    }

}
