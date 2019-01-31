package eu.scislo.mobilenext;

import javafx.scene.control.Label;

public class PersonRoom {


    public Label label = new Label();

    public PersonRoom(Person person) {
        label.setText(person.toString());
        label.getStyleClass().add("room");
    }

    public void setSelection(boolean isSelected) {
        if(isSelected) {
            this.label.getStyleClass().add("selected");
        } else {
            this.label.getStyleClass().remove("selected");
        }
    }

}
