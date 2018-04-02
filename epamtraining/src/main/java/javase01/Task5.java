package javase01;

public class Task5 {
    public static void main(String[] args) {
        Task5 matrix = new Task5();
        int side = 8;
        matrix.printArray(side);
    }

    public void printArray(int side) {
        int[][] a = new int[side][side];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[i][j] = i == j || i == a.length - j - 1 ? 1 : 0;
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
