package JavaSE02.Task3;

import java.util.ArrayList;

public class BeginnerSet {
    private ArrayList<Stationery> beginnerSet = new ArrayList<>();

    public void setBeginnerSet(ArrayList<Stationery> beginnerSet) {
        Pen pen = new Pen();
        Pencil pencil = new Pencil();
        Ruler ruler = new Ruler();
        beginnerSet.add(0, pen);
        beginnerSet.add(1, pencil);
        beginnerSet.add(2, ruler);
    }

    public ArrayList<Stationery> getBeginnerSet(){
        return beginnerSet;
    }
}
