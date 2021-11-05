package com.example.test;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    @FXML
    private Group gameGroup;
    @FXML
    private ListView playerList;

    public static Gameboard gameboard;


    public void initialize(){

       String[] names = {"Lorenz (20 Punkte)","Alexander (20 Punkte)", "Mathai (20 Punkte)","Felix (20 Punkte)"};

       playerList.getItems().addAll(names);


       System.out.println(gameGroup);
       gameboard = new Gameboard(3);
       gameboard.draw(gameGroup);

   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }
}