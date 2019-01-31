package eu.scislo.mobilenext;

import java.util.Comparator;

public class PersonComparatorByWorkHours implements Comparator<Person> {

    public int compare(Person a, Person b) {
        if (a.getWorkingHours() < b.getWorkingHours()) {
            return -1;
        } else if (a.getWorkingHours() > b.getWorkingHours()) {
            return 1;
        } else {
            return 0;
        }
    }

}
