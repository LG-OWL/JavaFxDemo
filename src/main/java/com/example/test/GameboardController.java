package com.example.test;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class GameboardController implements EventHandler<MouseEvent> {

    private double x,y;
    public GameboardController(double x,double y) {
       this.x =x;
       this.y =y;
    }

        @Override
        public void handle(MouseEvent mouseEvent) {

            Circle circle = (Circle) mouseEvent.getSource();
            if (mouseEvent.getEventType() == null) {
                Random rand = new Random();
                int r = rand.nextInt((255 + 1));
                int g = rand.nextInt((255 + 1));
                int b = rand.nextInt((255 + 1));
                circle.setFill(Color.rgb(r, g, b));
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED){
                circle.setCenterX(mouseEvent.getX());
                circle.setCenterY(mouseEvent.getY());
                Circle c = HelloController.gameboard.getNearestCircle(circle,mouseEvent.getX(),mouseEvent.getY())[0];
                DropShadow dropShadow = new DropShadow();
                HelloController.gameboard.clearEffect();
                c.setEffect(dropShadow);





            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
                HelloController.gameboard.getNearestCircle(circle,mouseEvent.getX(),mouseEvent.getY())[0].setFill(Color.BLACK);
                HelloController.gameboard.getNearestCircle(circle,mouseEvent.getX(),mouseEvent.getY())[0].setEffect(null);

                circle.setCenterX(x);
                circle.setCenterY(y);
            }
            }
        }
