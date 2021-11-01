package com.example.test;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.lang.invoke.LambdaMetafactory;

public class Gameboard {

    private Circle[][] playingFields;
    double spacing, radiusFields;
    int radius, lineStrokeWidth;

    public Gameboard(int radius){
        this.radiusFields = 10.0;
        this.radius = radius;
        this.spacing = 20;
        this.lineStrokeWidth = 3;
        this.playingFields = initGameboard();
    }

    public Circle[][] initGameboard(){
        Circle[][] newPlayingFields = new Circle[this.radius*2+1][this.radius*2+1];
        int size = this.radius + 1, maxLength = size + (size-1), arrayY = 0, indentation = 2* (int) radiusFields, amountIndentation = radius;
        double circleX = 0, circleY = radiusFields;

        for(int amountCirclesInRow = size; amountCirclesInRow <= maxLength; amountCirclesInRow++){
            circleX = indentation * amountIndentation + radiusFields;
            for(int arrayX = 0; arrayX < maxLength; arrayX++){
                if (arrayX <= amountCirclesInRow-1){
                    newPlayingFields[arrayY][arrayX] = new Circle(circleX, circleY, radiusFields);
                }
                else{
                    newPlayingFields[arrayY][arrayX] = null;
                }
                circleX += 2*radiusFields + spacing;
            }
            amountIndentation -= 1;
            arrayY++;
            circleY += 2*radiusFields + spacing;
        }
        int leaveOut = 1;
        amountIndentation = 1;
        for(int amountCirclesInRow = maxLength-1; amountCirclesInRow >= size; amountCirclesInRow--){
            circleX = indentation * amountIndentation + radiusFields;
            for(int arrayX = 0; arrayX < maxLength; arrayX++){
                if (arrayX < leaveOut){
                    newPlayingFields[arrayY][arrayX] = null;
                }
                else{
                    newPlayingFields[arrayY][arrayX] = new Circle(circleX, circleY, radiusFields);
                    circleX += 2*radiusFields + spacing;
                }
            }
            amountIndentation += 1;
            leaveOut++;
            arrayY++;
            circleY += 2*radiusFields + spacing;
        }
        return newPlayingFields;
    }

    public void draw(Group root){
        for (int arrayY = 0; arrayY <= this.radius*2; arrayY++){
            for (int arrayX = 0; arrayX <= this.radius*2; arrayX++){
                if (this.playingFields[arrayY][arrayX] != null) {
                    Circle circle = this.playingFields[arrayY][arrayX];
                    circle.setFill(Color.ORANGE);
                    root.getChildren().add(circle);
                }
            }
        }
        drawLineHorizontal(root);
        drawLineUpperRightToLowerLeft(root);
        drawLineUpperLeftToLowerRight(root);
    }

    public void drawLineHorizontal(Group root){
        for(int arrayY = 0; arrayY <= this.radius*2; arrayY++){
            for(int arrayX = 0; arrayX <= this.radius*2-1; arrayX++){
                Circle current = this.playingFields[arrayY][arrayX];
                Circle next = this.playingFields[arrayY][arrayX +1];
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

    public void drawLineUpperRightToLowerLeft(Group root){
        for(int arrayY = 0; arrayY <= this.radius*2-1; arrayY++){
            for(int arrayX = 0; arrayX <= this.radius*2; arrayX++){
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

    public void drawLineUpperLeftToLowerRight(Group root){
        for(int arrayY = 0; arrayY <= this.radius*2-1; arrayY++){
            for(int arrayX = 0; arrayX <= this.radius*2-1; arrayX++){
                Circle current = this.playingFields[arrayY][arrayX];
                Circle next = this.playingFields[arrayY + 1][arrayX +1];
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
}
