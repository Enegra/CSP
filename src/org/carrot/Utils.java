package org.carrot;

/**
 * Created by agnie on 9/30/2016.
 */
public class Utils {

    static int[] getOccurrences(int[][] values) {
        int[] occurrences = new int[values.length];
        for (int[] row : values) {
            for (int cell : row) {
                for (int i = 1; i < row.length + 1; i++) {
                    if (cell == i) {
                        occurrences[i - 1] += 1;
                        break;
                    }
                }
            }
        }
        return occurrences;
    }

    static int[] sortByOccurrence(int[][] values){
        int[] occurrences = getOccurrences(values);
        int[] numbers = new int[occurrences.length];
        for (int i=0; i<numbers.length; i++){
            numbers[i]=i+1;
        }
        boolean swapped = true;
        while (swapped) {
            swapped=false;
            for (int i = 0; i < occurrences.length - 1; i++) {
                if (occurrences[i] < occurrences[i + 1]) {
                    int temp = occurrences[i];
                    occurrences[i] = occurrences[i + 1];
                    occurrences[i + 1] = temp;
                    temp = numbers[i];
                    numbers[i] = numbers[i+1];
                    numbers[i+1] = temp;
                    swapped=true;
                }
            }
        }
        return numbers;
    }

}
