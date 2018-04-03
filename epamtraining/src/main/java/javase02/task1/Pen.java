package javase02.task1;

import java.util.Objects;

public class Pen {
    private int height;
    private String label;
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pen pen = (Pen) o;
        return height == pen.height &&
                Objects.equals(label, pen.label) &&
                Objects.equals(color, pen.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(height, label, color);
    }

    @Override
    public String toString() {
        return "Pen{" +
                "height=" + height +
                ", label='" + label + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
