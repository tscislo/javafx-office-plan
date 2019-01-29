package eu.scislo.mobilenext;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import org.json.simple.JSONObject;


public class Person extends RecursiveTreeObject<Person> {
    public static Person fromJSON(JSONObject personObj) {
        return new Person(
                personObj.get("name").toString(),
                personObj.get("surname").toString(),
                personObj.get("startTime").toString(),
                personObj.get("endTime").toString(),
                personObj.get("room").toString()
        );
    }

    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();
    private SimpleStringProperty room = new SimpleStringProperty();
    private SimpleStringProperty startTime = new SimpleStringProperty();
    private SimpleStringProperty endTime = new SimpleStringProperty();


    public Person(String firstName, String lastName, String statTime, String endTime, String roomNo) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setStartTime(statTime);
        this.setEndTime(endTime);
        this.setRoom(roomNo);
    }

    public Person() {

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

    public JSONObject toJSON() {
        JSONObject personJSON = new JSONObject();
        personJSON.put("name", this.getFirstName());
        personJSON.put("surname", this.getLastName());
        personJSON.put("startTime", this.getStartTime());
        personJSON.put("endTime", this.getEndTime());
        personJSON.put("room", this.getRoom());
        return personJSON;
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
