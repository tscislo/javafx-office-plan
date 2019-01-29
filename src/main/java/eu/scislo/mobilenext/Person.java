package eu.scislo.mobilenext;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;


public class Person extends RecursiveTreeObject<Person> {
    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty room = new SimpleStringProperty();
    private SimpleStringProperty startTime = new SimpleStringProperty();
    private SimpleStringProperty endTime = new SimpleStringProperty();


    public Person() {

    }

    public Person(String firstName, String lastName, String room) {
        this.setRoom(room);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }


    public String getRoom() {
        return room.get();
    }

    public SimpleStringProperty roomProperty() {
        return room;
    }

    public void setRoom(String room) {
        this.room.set(room);
    }


    public String getStartTime() {
        return startTime.get();
    }

    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }


    public String getEndTime() {
        return endTime.get();
    }

    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }


    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + " " + this.getRoom();
    }

    public Person clone() {
        Person cloned = new Person();

        // clone mutable fields
        cloned.setFirstName(this.getFirstName());
        cloned.setLastName(this.getLastName());
        cloned.setRoom(this.getRoom());
        cloned.setStartTime(this.getStartTime());
        cloned.setEndTime(this.getEndTime());

        return cloned;
    }
}
