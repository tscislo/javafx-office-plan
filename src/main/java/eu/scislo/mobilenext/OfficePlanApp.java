package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class OfficePlanApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        AnchorPane pane = fxmlLoader.load(); // To oznacza, że będziemy podpinać kontoler do AnchorPane
        primaryStage.setTitle("Lista ludzi");
        primaryStage.setScene(new Scene(pane, 400, 600));
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(600);
        MainController mainWindowController = fxmlLoader.getController();
//        mainWindowController.setMain(this);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
