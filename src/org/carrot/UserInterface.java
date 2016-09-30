package org.carrot;

import javax.swing.*;

/**
 * Created by agnie on 9/19/2016.
 */
class UserInterface extends JFrame {

    private OptionPanel optionPanel;
    private BoardPanel boardPanel;
    private int optionPanelWidth=200;
    private int optionPanelHeight=300;
    private int[][] boardArray;
    private int[][] solvedPuzzle;

    UserInterface(){
        prepareGUI();
    }

    private void prepareGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(optionPanelWidth + 25, optionPanelHeight+20);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("CSP");
        optionPanel = new OptionPanel(this);
        optionPanel.setBounds(5,5,optionPanelWidth,optionPanelHeight);
        this.add(optionPanel);
        this.setVisible(true);
    }

    void generateRandomSudokuPuzzle(int size){
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        boardArray = sudokuGenerator.generate(size);
        displayPuzzle(boardArray);
    }

    void solveSudokuPuzzle(){
        Sudoku sudoku = new Sudoku(boardArray);
        Solver solver = new Solver(sudoku);
        solver.solve();
        solvedPuzzle = new int[boardArray.length][boardArray.length];
        Variable[][] variables = sudoku.getSolution().getVariables();
        for (int i=0; i<variables.length; i++){
            for (int j=0; j<variables[i].length; j++){
                solvedPuzzle[i][j] = variables[i][j].getValue();
            }
        }
        displayPuzzle(solvedPuzzle);
        boardPanel.revalidate();
    }

    private void displayPuzzle(int[][] array){
        boardPanel = new BoardPanel();
        boardPanel.drawSudoku(array);
        int boardSize = boardPanel.getCanvasSize();
        if (optionPanelHeight > boardSize){
            this.setSize(boardSize+optionPanelWidth + 30,optionPanelHeight + 20);
        }
        else {
            this.setSize(boardSize+optionPanelWidth + 30,boardSize + 20);
        }
        boardPanel.setBounds(optionPanelWidth+10,5,boardSize,boardSize);
        this.add(boardPanel);
        boardPanel.setVisible(true);
    }

}
