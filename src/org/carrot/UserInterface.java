package org.carrot;

import javax.swing.*;

/**
 * Created by agnie on 9/19/2016.
 */
class UserInterface extends JFrame {

    private OptionPanel optionPanel;
    private BoardPanel boardPanel;
    private int optionPanelWidth=200;
    private int optionPanelHeight=320;
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
        drawSudoku(boardArray);
    }

    void solveSudokuPuzzle(boolean forwardChecking, boolean domainHeuristic, boolean variableHeuristic){
        Sudoku sudoku = new Sudoku(boardArray);
        if (domainHeuristic){
            sudoku.setDomainHeuristic(true);
        }
        if (variableHeuristic){
            sudoku.setVariableHeuristic(true);
        }
        Solver solver = new Solver(sudoku);
        solver.setForwardChecking(forwardChecking);
        solver.solve();
        solvedPuzzle = sudoku.getSolution().getValues();
        drawSudoku(solvedPuzzle);
        boardPanel.revalidate();
    }

    void generateQueensPuzzle(int size){
        QueensProblem queensProblem = new QueensProblem(size);
        boardArray = queensProblem.getSolution().getValues();
        drawQueens(boardArray);
    }

    void solveQueensPuzzle(boolean forwardChecking, boolean domainHeuristic, boolean variableHeuristic){
        QueensProblem queensProblem = new QueensProblem(boardArray);
        if (domainHeuristic){
            queensProblem.setDomainHeuristic(true);
        }
        if (variableHeuristic){
            queensProblem.setVariableHeuristic(true);
        }
        Solver solver = new Solver(queensProblem);
        solver.setForwardChecking(forwardChecking);
        solver.solve();
        solvedPuzzle = queensProblem.getSolution().getValues();
        drawQueens(solvedPuzzle);
        boardPanel.revalidate();
    }

    private void drawSudoku(int[][] array){
        boardPanel = new BoardPanel();
        boardPanel.drawSudoku(array);
        displayPuzzle(array);
    }

    private void drawQueens(int[][] array){
        boardPanel = new BoardPanel();
        boardPanel.drawQueens(array);
        displayPuzzle(array);
    }

    private void displayPuzzle(int[][] array){
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
