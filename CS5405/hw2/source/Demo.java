/**
	Mario Stoyanov
	CS5405 - Java GUIs
	Homework 2
*/

package code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Demo extends Application  {

    @Override
    public void start(Stage primaryStage) {

        Label label = new Label("Mario Stoyanov");

        Scene scene = new Scene(label, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}