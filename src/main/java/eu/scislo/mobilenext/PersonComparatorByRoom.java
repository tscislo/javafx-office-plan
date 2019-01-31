package eu.scislo.mobilenext;

import java.util.Comparator;

public class PersonComparatorByRoom implements Comparator<Person> {

    public int compare(Person a, Person b) {
        if (Integer.parseInt(a.getRoom()) < Integer.parseInt(b.getRoom())) {
            return -1;
        } else if (Integer.parseInt(a.getRoom()) > Integer.parseInt(b.getRoom())) {
            return 1;
        } else {
            return 0;
        }
    }

}
