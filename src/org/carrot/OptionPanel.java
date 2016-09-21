package org.carrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agnie on 9/19/2016.
 */
class OptionPanel extends JPanel {

    private JLabel problemChoiceLabel, heuristicChoiceLabel, problemSizeLabel;
    private JComboBox problemChoiceComboBox, heuristicChoiceComboBox, problemSizeComboBox;
    private JButton setupButton, solveButton;
    UserInterface userInterface;

    OptionPanel(UserInterface userInterface) {
        this.userInterface = userInterface;
        setupPanel();
    }

    private void setupPanel() {
        this.setLayout(new FlowLayout());
        this.setAlignmentX(LEFT_ALIGNMENT);
        setupProblemChoiceLabel();
        setupProblemChoiceComboBox();
        setupHeuristicChoiceLabel();
        setupHeuristicChoiceComboBox();
        setupProblemSizeLabel();
        setupProblemSizeComboBox();
        setupSetupButton();
        this.setVisible(true);
    }

    private void setupProblemChoiceLabel() {
        problemChoiceLabel = new JLabel("Choose the problem");
        this.add(problemChoiceLabel);
    }

    private void setupProblemChoiceComboBox() {
        String[] problems = {"sudoku puzzle", "N queens problem"};
        problemChoiceComboBox = new JComboBox(problems);
        problemChoiceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] problemSize;
                if (problemChoiceComboBox.getSelectedIndex() == 0) {
                    problemSize = new int[]{2, 3};
                } else {
                    problemSize = new int[]{4, 5, 6, 7, 8};
                }
                problemSizeLabel.setVisible(true);
                problemSizeComboBox.removeAllItems();
                for (int item : problemSize) {
                    problemSizeComboBox.addItem(item);
                }
                problemSizeComboBox.setVisible(true);
                heuristicChoiceLabel.setVisible(true);
                heuristicChoiceComboBox.setVisible(true);
                setupButton.setVisible(true);
            }
        });
        problemChoiceComboBox.setPreferredSize(new Dimension(200,30));
        this.add(problemChoiceComboBox);
    }

    private void setupProblemSizeLabel() {
        problemSizeLabel = new JLabel("Choose the size of the problem");
        this.add(problemSizeLabel);
        problemSizeLabel.setVisible(false);
    }

    private void setupProblemSizeComboBox() {
        problemSizeComboBox = new JComboBox();
        problemSizeComboBox.setPreferredSize(new Dimension(200,30));
        this.add(problemSizeComboBox);
        problemSizeComboBox.setVisible(false);
    }

    private void setupHeuristicChoiceLabel() {
        heuristicChoiceLabel = new JLabel("Choose heuristic");
        this.add(heuristicChoiceLabel);
        heuristicChoiceLabel.setVisible(false);
    }

    private void setupHeuristicChoiceComboBox() {
        String[] heuristics = {"none", "random access", "domain sorting"};
        heuristicChoiceComboBox = new JComboBox(heuristics);
        heuristicChoiceComboBox.setPreferredSize(new Dimension(200,30));
        this.add(heuristicChoiceComboBox);
        heuristicChoiceComboBox.setVisible(false);
    }

    private void setupSetupButton(){
        setupButton = new JButton("OK");
        setupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo
                int problemChoice = problemChoiceComboBox.getSelectedIndex();
                int problemSize = (Integer) problemSizeComboBox.getSelectedItem();
                int heuristic = heuristicChoiceComboBox.getSelectedIndex();
                //function to run selected algorithm with chosen values
            }
        });
        this.add(setupButton);
        setupButton.setVisible(false);
    }

    private void setupSolveButton(){
        solveButton = new JButton("SOLVE");
    }


}
