package org.carrot;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnie on 9/20/2016.
 */
public class BoardPanel extends JPanel{

    private int puzzleSize;
    private int cellSize = 30;
    private int canvasSize;
    private int emptyField;
    private Color blankCellColor = new Color(255,153,51);
    private Color cellColor = new Color(224,224,224);
    private Font numberFont = new Font("Monospaced", Font.BOLD, 20);

    private JTextField[][] sudokuCells;

    BoardPanel(){
        this.setVisible(false);
    }

    private void prepareBoard(int[][] array){
        this.puzzleSize = array.length;
        setLayout(new GridLayout(puzzleSize,puzzleSize));
        canvasSize = cellSize*puzzleSize;
        setSize(new Dimension(canvasSize,canvasSize));
    }

    void drawSudoku(int[][] array){
        prepareBoard(array);
        this.removeAll();
        sudokuCells = new JTextField[puzzleSize][puzzleSize];
        for(int i=0; i<puzzleSize; i++){
            for (int j=0; j<puzzleSize; j++){
                sudokuCells[i][j] = new JTextField();
                this.add(sudokuCells[i][j]);
                if (array[i][j] == 0){
                    sudokuCells[i][j].setBackground(blankCellColor);
                    sudokuCells[i][j].setText("");
                }
                else {
                    sudokuCells[i][j].setBackground(cellColor);
                    sudokuCells[i][j].setText(String.valueOf(array[i][j]));
                }
                sudokuCells[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuCells[i][j].setFont(numberFont);
                sudokuCells[i][j].setEditable(false);
                sudokuCells[i][j].setVisible(true);
            }
        }
        this.setVisible(true);
    }

    int getCanvasSize(){
        return canvasSize;
    }
}
