package javase02.task3;

import java.util.*;

public class BeginnerSet {
    private List<Stationery> beginnerSet = new ArrayList<>();

    public void setBeginnerSet(List<Stationery> beginnerSet) {
        Pen pen = new Pen();
        Pencil pencil = new Pencil();
        Ruler ruler = new Ruler();
        beginnerSet.add(0, pen);
        beginnerSet.add(1, pencil);
        beginnerSet.add(2, ruler);
    }

    public List<Stationery> getBeginnerSet() {
        return beginnerSet;
    }
}
