package eu.scislo.mobilenext;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private Stage stage;

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

    private Person selectedPerson = new Person();
    private ObservableList<Person> people = FXCollections.observableArrayList();

    private SimpleStringProperty filePath = new SimpleStringProperty();

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

        this.bindPerson();

        // Enables/disables add button
        this.add.disableProperty().bind(Bindings.createBooleanBinding(
                () -> !this.name.validate() ||
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

        this.save.disableProperty().bind(Bindings.createBooleanBinding(
                () -> this.filePath.getValue() == null,
                this.filePath));

        this.peopleList.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            if (this.peopleList.getSelectionModel().getSelectedItem() != null) {
                this.selectedPerson = this.peopleList.getSelectionModel().getSelectedItem().getValue().clone();
                this.bindPerson();
            }
        });

    }

    private void bindPerson() {
        this.name.textProperty().bindBidirectional(selectedPerson.firstNameProperty());
        this.surname.textProperty().bindBidirectional(selectedPerson.lastNameProperty());
        this.startTime.textProperty().bindBidirectional(selectedPerson.startTimeProperty());
        this.endTime.textProperty().bindBidirectional(selectedPerson.endTimeProperty());
        this.roomNo.textProperty().bindBidirectional(selectedPerson.roomProperty());
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

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING, msg);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Błąd!");
        alert.showAndWait();
    }

    private void showMsg(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setTitle("Informacja");
        alert.setHeaderText("Sukces!");
        alert.showAndWait();
    }

    private void parseFromJSON(File file) {
        try {
            JSONParser jsonParser = new JSONParser();
            Object peopleArr = jsonParser.parse(new FileReader(file.getPath()));
            JSONArray jsonArray = (JSONArray) peopleArr;
            Iterator it = jsonArray.iterator();
            this.people.clear();
            while (it.hasNext()) {
                this.people.add(Person.fromJSON((JSONObject) it.next()));
            }
        } catch (Exception e) {
            this.showError("Błędy w pliku wejściowym!");
        }
    }

    private JSONArray parseToJSON() {
        JSONArray peopleArr = new JSONArray();
        for (Person p : people) {
            peopleArr.add(p.toJSON());
        }
        return peopleArr;
    }

    private void writeToFile(String path) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Czy na pewno chcesz zapisać do pliku?");
        alert.setContentText(path);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK) {
            FileWriter file = null;
            try {
                file = new FileWriter(path);
                file.write(this.parseToJSON().toJSONString());
            } catch (IOException e) {
                this.showError("Błąd zapisu do pliku!");
            } finally {
                try {
                    file.close();
                    this.showMsg("Zapisano poprawnie!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void rename() {

    }

    public void add() {
        this.people.add(this.selectedPerson.clone());
    }

    public void read() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                String fileType = Files.probeContentType(file.toPath());
                if (!(fileType.equals("application/json"))) {
                    this.showError("Nieobsługiwany format pliku!");
                } else {
                    this.parseFromJSON(file);
                    this.filePath.set(file.toPath().toString());
                }
            } catch (IOException e) {
                this.showError("Nie można sprawdzić typu pliku!");
            }
        }
    }

    public void write() {
        this.writeToFile(this.filePath.getValue());
    }


}
