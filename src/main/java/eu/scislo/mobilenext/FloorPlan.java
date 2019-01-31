package eu.scislo.mobilenext;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class FloorPlan {
    private VBox vbox;
    private ObservableList<Person> people = FXCollections.observableArrayList();

    public FloorPlan(VBox vbox, ObservableList<Person> sourcePeople) {
        this.vbox = vbox;
        sourcePeople.addListener((ListChangeListener<? super Person>) change -> {
            this.people.clear();
            for (Person p : sourcePeople) {
                this.people.add(p.clone(true));
            }
            FXCollections.sort(this.people, new PersonComparatorByRoom());
            this.draw(-1);
        });
    }

    public void draw(int selectedPersonId) {
        this.vbox.getChildren().clear();
        for (Person p : this.people) {
            PersonRoom personRoom = new PersonRoom(p);
            personRoom.setSelection(p.id == selectedPersonId);
            this.vbox.getChildren().add(personRoom.label);
        }
    }

}
