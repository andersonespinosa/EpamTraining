package Task1;

import java.util.Arrays;

public class Task4 {

    public static void main(String[] args) {
        int initArraySize = 30;

        Task4 task = new Task4();
        int[] initialNumbers =  task.getFilledArray(initArraySize);
        int[] sumNumbers =  task.getSumNumbers(initialNumbers);
        int maxValue = task.getArrayMaxValue(sumNumbers);
        System.out.println(maxValue);
    }

    private int[] getFilledArray(int initArraySize){
        int[] initialNumbers = new int[initArraySize];
        for (int i = 0; i < initialNumbers.length; i++) {
            initialNumbers[i] = (int) (Math.random() * 100);
        }
        return initialNumbers;
    }

    private int[] getSumNumbers(int[] initialNumbers){
        int[] sumNumbers = new int[30];
        for (int i = 0; i < initialNumbers.length; i++) {
            sumNumbers[i] = initialNumbers[initialNumbers.length - i - 1] + initialNumbers[i];
        }
        return sumNumbers;
    }

    private int getArrayMaxValue(int[] sumNumbers){
        Arrays.sort(sumNumbers);
        return sumNumbers[sumNumbers.length - 1];
    }
}
