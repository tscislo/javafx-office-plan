package eu.scislo.mobilenext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class OfficePlanApp extends Application {

    private int width = 1000;
    private int height = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        AnchorPane pane = fxmlLoader.load(); // To oznacza, że będziemy podpinać kontoler do AnchorPane
        primaryStage.setTitle("Office Plan");
        Scene scene = new Scene(pane, width, height);
        scene.getStylesheets().add("css/jfoenix-customizations.css");
        scene.getStylesheets().add("css/jfx-tree-table-view.css");
        scene.getStylesheets().add("css/jfx-text-field.css");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        MainController mainWindowController = fxmlLoader.getController();
        mainWindowController.setStage(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
