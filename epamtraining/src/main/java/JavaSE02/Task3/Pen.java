package JavaSE02.Task3;

import java.util.Comparator;

public class Pen extends Stationery implements Comparator<Pen> {
    private String name;
    private String color;
    private int length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compare(Pen o1, Pen o2) {
        int price1 = o1.getPrice();
        int price2 = o2.getPrice();
        return price1 - price2;
    }
}
