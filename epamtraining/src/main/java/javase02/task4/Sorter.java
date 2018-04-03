package javase02.task4;

import javase02.task3.Stationery;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorter {

    public List<Stationery> sortBeginnerSetByName(List<Stationery> beginnerSet) {
        Comparator<Stationery> stationeryComparator = Comparator.comparing(Stationery::getName);

        Collections.sort(beginnerSet, stationeryComparator);
        return beginnerSet;
    }

    public List<Stationery> sortBeginnerSetByPrice(List<Stationery> beginnerSet) {
        Comparator<Stationery> priceCompare = Comparator.comparing(Stationery::getPrice);
        Collections.sort(beginnerSet, priceCompare);
        return beginnerSet;
    }

    public List<Stationery> sortBeginnerSetByNameAndPrice(List<Stationery> beginnerSet) {
        Comparator<Stationery> stationeryComparator = Comparator.comparing(Stationery::getName).thenComparing(Stationery::getPrice);
        Collections.sort(beginnerSet, stationeryComparator);
        return beginnerSet;
    }


}
