package eu.scislo.mobilenext;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    public Person tomasz = new Person("Tomasz", "Ścisło", "125");

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


        this.people.add(tomasz);
        this.people.add(tomasz);
        this.people.add(tomasz);
        this.people.add(tomasz);
        this.people.add(tomasz);


        peopleList.setRoot(new RecursiveTreeItem<>(people, RecursiveTreeObject::getChildren));
        peopleList.setShowRoot(false);
        peopleList.getColumns().setAll(firstNameColumn, lastNameColumn, roomColumn);

    }

    public void rename() {
        this.tomasz.setFirstName("Karol");
    }


}
