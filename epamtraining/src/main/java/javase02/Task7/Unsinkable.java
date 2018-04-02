package javase02.Task7;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Unsinkable {
    //do not sink
}
