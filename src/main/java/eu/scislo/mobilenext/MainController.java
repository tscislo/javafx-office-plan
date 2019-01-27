package eu.scislo.mobilenext;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.read.getStyleClass().add("button-raised");
        this.save.getStyleClass().add("button-raised");
        this.add.getStyleClass().add("button-raised");
        this.report.getStyleClass().add("button-raised");

    }


}
