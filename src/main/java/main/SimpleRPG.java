package main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleRPG extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Simple RPG");
        primaryStage.show();
    }
}
