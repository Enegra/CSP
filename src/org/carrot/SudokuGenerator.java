package org.carrot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by agnie on 9/10/2016.
 */
public class SudokuGenerator {

    int[][] two = {{3,1,2,4},{2,4,3,1}, {4,2,1,3}, {1,3,4,2}};
    int[][] three = {{3,4,5,7,1,2,6,9,8},{7,8,2,9,6,3,4,5,1}, {9,6,1,8,5,4,7,2,3},{2,1,9,3,7,5,8,4,6},{6,5,3,2,4,8,1,7,9},{8,7,4,1,9,6,5,3,2},{4,9,6,5,2,1,3,8,7},{1,2,8,4,3,7,9,6,5},{5,3,7,6,8,9,2,1,4}};

    int[][] generate(int size){
        int[][] sample=new int[size*size][size*size];
        switch (size){
            case 2:
                sample = permutate(two);
                break;
            case 3:
                sample = permutate(three);
                break;
        }
        Random random = new Random();
        int blankCount = (int)Math.pow(size,4)/2;
        for (int i=blankCount; i>0; i--){
            boolean valid=false;
            int row = random.nextInt(size*size);
            int column = random.nextInt(size*size);
            if (sample[row][column]!=0){
                valid = true;
            }
            while (!valid){
                row = random.nextInt(size*size);
                column = random.nextInt(size*size);
                if (sample[row][column]!=0){
                    valid = true;
                }
            }
            sample[row][column] = 0;
        }
        return sample;
    }

    private int[][] permutate(int[][] array){
        Random random = new Random();
        int size = array.length;
        int gridSize = (int)Math.sqrt(array.length);
        int numberOfSwaps = random.nextInt(size*size);
        for (int i=0; i<numberOfSwaps; i++){
            int numberOne = random.nextInt(9)+1;
            int numberTwo = random.nextInt(9)+1;
            swapNumbers(numberOne, numberTwo, array);
        }
        System.out.println(Arrays.deepToString(array));
        int numberOfRotations = random.nextInt(gridSize)+1;
        System.out.println(numberOfRotations);
        for (int i=0; i<numberOfRotations; i++){
            int one = random.nextInt(gridSize-1);
            int two = random.nextInt(gridSize-1);
            if (one!=two){
                swapRow(one, two, array);
                swapColumn(one,two,array);
            }
        }
        return array;
    }

    private void swapNumbers(int numberOne, int numberTwo, int[][] array){
        ArrayList<ArrayList<Integer>> positionsOne = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> positionsTwo = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++){
                ArrayList<Integer> position = new ArrayList<Integer>();
                position.add(i);
                position.add(j);
                if (array[i][j] == numberOne){
                    positionsOne.add(position);
                }
                if (array[i][j] == numberTwo){
                    positionsTwo.add(position);
                }
            }
        }
        for (int i=0; i<positionsOne.size(); i++){
            array[positionsOne.get(i).get(0)][positionsOne.get(i).get(1)] = numberTwo;
            array[positionsTwo.get(i).get(0)][positionsTwo.get(i).get(1)] = numberOne;
        }
    }

    private void swapRow(int rowOne, int rowTwo, int[][] array){
        int size = (int)Math.sqrt(array.length);
        for (int i=0; i<size; i++){
            int[] temp = array[rowOne*size+i];
            array[rowOne*size+i] = array[rowTwo*size+i];
            array[rowTwo*size+i] = temp;
        }
    }

    private void swapColumn(int columnOne, int columnTwo, int[][] array){
        int size = (int)Math.sqrt(array.length);
        for (int i=0; i<size; i++){
            for (int j=0; j<array.length; j++){
                int temp = array[j][columnOne+size*i];
                array[j][columnOne+size*i] = array[j][columnTwo+size*i];
                array[j][columnTwo+size*i] = temp;
            }
        }
    }

}
