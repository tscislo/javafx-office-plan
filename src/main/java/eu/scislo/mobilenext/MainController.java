package eu.scislo.mobilenext;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;


import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {


    @FXML
    public JFXButton read;

    @FXML
    public JFXButton save;

    @FXML
    public JFXButton add;

    @FXML
    public JFXButton report;

    @FXML
    private JFXTreeTableColumn<Person, String> firstNameColumn;

    @FXML
    private JFXTreeTableColumn<Person, String> lastNameColumn;

    @FXML
    private JFXTreeTableColumn<Person, String> roomColumn;

    @FXML
    private JFXTreeTableView<Person> peopleList;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField roomNo;

    @FXML
    private JFXTextField startTime;

    @FXML
    private JFXTextField endTime;

    public Person selectedPerson = new Person();
    private ObservableList<Person> people = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.read.getStyleClass().add("button-raised");
        this.save.getStyleClass().add("button-raised");
        this.add.getStyleClass().add("button-raised");
        this.report.getStyleClass().add("button-raised");

        firstNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Person, String> param) -> param.getValue().getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Person, String> param) -> param.getValue().getValue().lastNameProperty());
        roomColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Person, String> param) -> param.getValue().getValue().roomProperty());


        peopleList.setRoot(new RecursiveTreeItem<>(people, RecursiveTreeObject::getChildren));
        peopleList.setShowRoot(false);
        peopleList.getColumns().setAll(firstNameColumn, lastNameColumn, roomColumn);

        this.addRequiredValidator(this.name, "Podaj imię!");
        this.addRequiredValidator(this.surname, "Podaj nazwisko!");
        this.addRequiredValidator(this.endTime, "Podaj godzinę!");
        this.addRequiredValidator(this.startTime, "Podaj godzinę!");
        this.addRequiredValidator(this.roomNo, "Podaj pokój!");

        this.addNumberValidator(this.roomNo, "Musi być liczbą!");
        this.addNumberValidator(this.endTime, "Musi być liczbą!");
        this.addNumberValidator(this.startTime, "Musi być liczbą!");

        this.name.textProperty().bindBidirectional(selectedPerson.firstNameProperty());
        this.surname.textProperty().bindBidirectional(selectedPerson.lastNameProperty());
        this.startTime.textProperty().bindBidirectional(selectedPerson.startTimeProperty());
        this.endTime.textProperty().bindBidirectional(selectedPerson.endTimeProperty());
        this.roomNo.textProperty().bindBidirectional(selectedPerson.roomProperty());

        // Enables/disables add button
        this.add.disableProperty().bind(Bindings.createBooleanBinding(
                () ->
                        !this.name.validate() ||
                                !this.startTime.validate() ||
                                !this.surname.validate() ||
                                !this.endTime.validate() ||
                                !this.roomNo.validate(),
                this.selectedPerson.roomProperty(),
                this.selectedPerson.startTimeProperty(),
                this.selectedPerson.endTimeProperty(),
                this.selectedPerson.firstNameProperty(),
                this.selectedPerson.lastNameProperty()
        ));

    }

    private void addRequiredValidator(JFXTextField textField, String customValidationText) {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(customValidationText);
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("1em")
                .styleClass("error")
                .build());
        textField.getValidators().add(validator);
        textField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                textField.validate();
            }
        });
    }

    private void addNumberValidator(JFXTextField textField, String customValidationText) {
        NumberValidator validator = new NumberValidator();
        validator.setMessage(customValidationText);
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("1em")
                .styleClass("error")
                .build());
        textField.getValidators().add(validator);
    }

    public void rename() {

    }

    public void add() {
        this.people.add(this.selectedPerson.clone());

    }


}
