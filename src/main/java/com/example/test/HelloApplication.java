package com.example.test;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import com.example.test.Gameboard;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();


        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("First Demo");
        stage.setScene(scene);
        stage.show();

        Gameboard gameboard = new Gameboard(6);


       // gameboard.draw((Group) controller.getGameGroup());


    }

    public static void main(String[] args) {

        launch(args);
    }
}