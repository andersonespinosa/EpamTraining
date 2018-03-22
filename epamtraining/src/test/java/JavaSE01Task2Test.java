import JavaSE01.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 public class JavaSE01Task2Test{

     private Task2 task = new Task2();

     @Test
     void getMinValueTest() {
        Assertions.assertTrue(task.getMinValue(0.005) == 0.0044444444444444444);
     }
}
