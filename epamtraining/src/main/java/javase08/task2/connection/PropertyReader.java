package javase08.task2.connection;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Function;

public class PropertyReader {

    @SneakyThrows
    public static <T> T getFromProperties(Class<T> aClass, String fileName) {

        val properties = new Properties();
        String name = String.format("/%s.properties", fileName);
        try (InputStream resourceAsStream = PropertyReader.class.getResourceAsStream(name)) {
            properties.load(resourceAsStream);
        }

        boolean seen = false;
        Constructor<T> best = null;
        Comparator<Constructor<T>> comparator = Comparator.comparingInt(Constructor::getParameterCount);
        //noinspection unchecked
        for (Constructor<T> constructor : (Constructor<T>[]) aClass.getConstructors()) {
            if (!seen || comparator.compare(constructor, best) > 0) {
                seen = true;
                best = constructor;
            }
        }
        Constructor<T> tConstructor = (seen ? Optional.of(best) : Optional.<Constructor<T>>empty())
                .orElseThrow(RuntimeException::new);

        List<Serializable> list = new ArrayList<>();
        Function<Parameter, ? extends Serializable> mapper = getParameterFunction(properties);
        for (Parameter parameter : tConstructor.getParameters()) {
            Serializable serializable = mapper.apply(parameter);
            list.add(serializable);
        }

        return tConstructor.newInstance(list.toArray());
    }

    @NonNull
    private static Function<Parameter, ? extends Serializable> getParameterFunction(Properties properties) {
        return parameter -> {
            String name = parameter.getName();
            String value = properties.getProperty(name);
            Class<?> type = parameter.getType();
            if (type == String.class) return value;
            if (type == Integer.class || type == int.class)
                return Integer.parseInt(value);
            //...
            return value;
        };
    }
}
