public class Task3 {

    public static void main(String[] args) {
        double start = 0.0;
        double finish = 15.0;
        double step = 0.5;
        Task3 task = new Task3();
        task.printFunctionResult(start,finish,step);
    }

    public void printFunctionResult(double start, double finish, double step){
        double result = 0;
        for(; start<=finish; start+=step) {
            result = Math.tan(2 * start) - 3;
            System.out.println("Argument value: " + start + "\t" + "Fuction value: " + result);
        }
    }

}
