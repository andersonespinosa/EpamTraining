package JavaSE02.Task3;

import java.util.Objects;

public class GelPen extends Pen{
    private String inkType;

    public String getInkType() {
        return inkType;
    }

    public void setInkType(String inkType) {
        this.inkType = inkType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GelPen gelPen = (GelPen) o;
        return Objects.equals(inkType, gelPen.inkType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(inkType);
    }

    @Override
    public String toString() {
        return "GelPen{" +
                "inkType='" + inkType + '\'' +
                '}';
    }
}
