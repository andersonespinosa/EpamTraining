package javase01;

public class Task2 {

    public static void main(String[] args) {
        double maxValue = 0.005;
        Task2 task = new Task2();
        System.out.println("Minimal value is: " + String.format("%.4f", task.getMinValue(maxValue)));
    }

    private double getMinValue(double maxValue){
        int count = 0;
        double[] array = new double[25];
        do {
            array[count] = 1.0 / ((count + 1.0) * (count + 1.0));
            count++;
        } while (array[count - 1] > maxValue);

        double minValue = 0.0;

        for (double arrayElement : array) {
            if (arrayElement != 0.0) {
                minValue = arrayElement;
            }
        }
        return minValue;
    }
}
