import JavaSE01.Task2;
import org.junit.Test;
import static org.junit.Assert.*;

 public class JavaSE01Task2Test{

     private Task2 task = new Task2();

     @Test
     public void getMinValueTest() {
        assertTrue("getMinValue should work", task.getMinValue(0.005) == 0.0044444444444444444);
     }
}
