package com.example.test;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Random;

public class Gameboard {

    private Circle[][] playingFields;
    double spacing, radiusFields;
    int radius, lineStrokeWidth;

    public Gameboard(int radius) {
        this.radiusFields = 10.0;
        this.radius = radius;
        this.spacing = 20;
        this.lineStrokeWidth = 3;
        this.playingFields = initGameboard();
    }

    public Circle[][] initGameboard() {
        Circle[][] newPlayingFields = new Circle[this.radius * 2 + 1][this.radius * 2 + 1];
        int size = this.radius + 1, maxLength = size + (size - 1), arrayY = 0, indentation = 2 * (int) radiusFields, amountIndentation = radius;
        double circleX = 0, circleY = radiusFields;

        for (int amountCirclesInRow = size; amountCirclesInRow <= maxLength; amountCirclesInRow++) {
            circleX = indentation * amountIndentation + radiusFields;
            for (int arrayX = 0; arrayX < maxLength; arrayX++) {
                if (arrayX <= amountCirclesInRow - 1) {
                    newPlayingFields[arrayY][arrayX] = new Circle(circleX, circleY, radiusFields);
                } else {
                    newPlayingFields[arrayY][arrayX] = null;
                }
                circleX += 2 * radiusFields + spacing;
            }
            amountIndentation -= 1;
            arrayY++;
            circleY += 2 * radiusFields + spacing;
        }
        int leaveOut = 1;
        amountIndentation = 1;
        for (int amountCirclesInRow = maxLength - 1; amountCirclesInRow >= size; amountCirclesInRow--) {
            circleX = indentation * amountIndentation + radiusFields;
            for (int arrayX = 0; arrayX < maxLength; arrayX++) {
                if (arrayX < leaveOut) {
                    newPlayingFields[arrayY][arrayX] = null;
                } else {
                    newPlayingFields[arrayY][arrayX] = new Circle(circleX, circleY, radiusFields);
                    circleX += 2 * radiusFields + spacing;
                }
            }
            amountIndentation += 1;
            leaveOut++;
            arrayY++;
            circleY += 2 * radiusFields + spacing;
        }
        return newPlayingFields;
    }

    public void draw(Group root) {
        drawLineHorizontal(root);
        drawLineUpperRightToLowerLeft(root);
        drawLineUpperLeftToLowerRight(root);
        for (int arrayY = 0; arrayY <= this.radius * 2; arrayY++) {
            for (int arrayX = 0; arrayX <= this.radius * 2; arrayX++) {
                if (this.playingFields[arrayY][arrayX] != null) {
                    Circle circle = this.playingFields[arrayY][arrayX];
                    circle.setFill(Color.ORANGE);
                    circle.addEventFilter(MouseEvent.MOUSE_CLICKED, new GameboardController(circle.getCenterX(), circle.getCenterY()));
                    circle.addEventFilter(MouseEvent.MOUSE_DRAGGED, new GameboardController(circle.getCenterX(), circle.getCenterY()));
                    circle.addEventFilter(MouseEvent.MOUSE_RELEASED, new GameboardController(circle.getCenterX(), circle.getCenterY()));

                    root.getChildren().add(circle);
                }
            }
        }

    }


    public void drawLineHorizontal(Group root) {
        for (int arrayY = 0; arrayY <= this.radius * 2; arrayY++) {
            for (int arrayX = 0; arrayX <= this.radius * 2 - 1; arrayX++) {
                Circle current = this.playingFields[arrayY][arrayX];
                Circle next = this.playingFields[arrayY][arrayX + 1];
                if (current == null || next == null)
                    continue;
                Line line = new Line();
                line.setStartX(current.getCenterX());
                line.setStartY(current.getCenterY());
                line.setEndX(next.getCenterX());
                line.setEndY(next.getCenterY());
                line.setStrokeWidth(this.lineStrokeWidth);
                line.setStroke(Color.ORANGE);
                root.getChildren().add(line);
            }
        }
    }

    public void drawLineUpperRightToLowerLeft(Group root) {
        for (int arrayY = 0; arrayY <= this.radius * 2 - 1; arrayY++) {
            for (int arrayX = 0; arrayX <= this.radius * 2; arrayX++) {
                Circle current = this.playingFields[arrayY][arrayX];
                Circle next = this.playingFields[arrayY + 1][arrayX];
                if (current == null || next == null)
                    continue;
                Line line = new Line();
                line.setStartX(current.getCenterX());
                line.setStartY(current.getCenterY());
                line.setEndX(next.getCenterX());
                line.setEndY(next.getCenterY());
                line.setStrokeWidth(this.lineStrokeWidth);
                line.setStroke(Color.ORANGE);
                root.getChildren().add(line);
            }
        }
    }

    public void drawLineUpperLeftToLowerRight(Group root) {
        for (int arrayY = 0; arrayY <= this.radius * 2 - 1; arrayY++) {
            for (int arrayX = 0; arrayX <= this.radius * 2 - 1; arrayX++) {
                Circle current = this.playingFields[arrayY][arrayX];
                Circle next = this.playingFields[arrayY + 1][arrayX + 1];
                if (current == null || next == null)
                    continue;
                Line line = new Line();
                line.setStartX(current.getCenterX());
                line.setStartY(current.getCenterY());
                line.setEndX(next.getCenterX());
                line.setEndY(next.getCenterY());
                line.setStrokeWidth(this.lineStrokeWidth);
                line.setStroke(Color.ORANGE);
                root.getChildren().add(line);
            }
        }
    }

    public Circle[][] getPlayingFields() {
        return playingFields;
    }

    public void clearEffect() {
        for (int arrayY = 0; arrayY <= this.radius * 2; arrayY++) {
            for (int arrayX = 0; arrayX <= this.radius * 2; arrayX++) {
                if (this.playingFields[arrayY][arrayX] != null) {
                    playingFields[arrayY][arrayX].setEffect(null);
                }
            }
        }
    }

    public Circle[] getNearestCircle(Circle c,double X, double Y){
        double shortsDistance = 10000000;
        Circle result[] = new Circle[2];
        for (int arrayY = 0; arrayY <= this.radius * 2; arrayY++) {
            for (int arrayX = 0; arrayX <= this.radius * 2; arrayX++) {
                if (this.playingFields[arrayY][arrayX] != null) {
                    double x =  playingFields[arrayY][arrayX].getCenterX();
                    double y =  playingFields[arrayY][arrayX].getCenterY();
                    double currentDistance = Math.sqrt(Math.pow(X-x,2)+Math.pow(Y-y,2));
                    if (shortsDistance > currentDistance && c != playingFields[arrayY][arrayX]){
                        shortsDistance = Math.sqrt(Math.pow(X-x,2)+Math.pow(Y-y,2));
                        result[0] = playingFields[arrayY][arrayX];

                    }

                }
            }
        }
        result[1] = c;
        return result;
    }

}

