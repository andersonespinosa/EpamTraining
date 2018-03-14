package JavaSE02.Task4;

import JavaSE02.Task3.Stationery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    public ArrayList<Stationery> sortBeginnerSetByName(ArrayList<Stationery> beginnerSet){
        Comparator<Stationery> stationeryComparator = Comparator.comparing(Stationery::getName);

        Collections.sort(beginnerSet, stationeryComparator);
        return beginnerSet;
    }

    public ArrayList<Stationery> sortBeginnerSetByPrice(ArrayList<Stationery> beginnerSet){
        Comparator<Stationery> priceCompare = Comparator.comparing(Stationery::getPrice);
        Collections.sort(beginnerSet, priceCompare);
        return beginnerSet;
    }

    public ArrayList<Stationery> sortBeginnerSetByNameAndPrice(ArrayList<Stationery> beginnerSet) {
        Comparator<Stationery> stationeryComparator = Comparator.comparing(Stationery::getName).thenComparing(Stationery::getPrice);
        Collections.sort(beginnerSet, stationeryComparator);
        return beginnerSet;
    }


}
