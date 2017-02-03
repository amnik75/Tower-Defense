/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author km
 */
public class TowerDefense extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 970, 560, Color.DARKOLIVEGREEN);
        final int n = 15;
        Image main = new Image("/castle pictures/Tower defense.png", 970, 560, true, true, true);
        ImageView imageView = new ImageView(main);
        Button New = new Button("New game");
        Button start = new Button("Start game");
        Button load = new Button("Load game");
        Button savebtn = new Button("Save game");
        Button nextLevel = new Button("Next level");
        Button confirm = new Button("Confirm");
        TextField enter = new TextField();
        Text name = new Text(10, 30, "Player name:");
        Map map = new Map(n);
        File save = new File("save.txt");

        AnimationTimer enemyGenerator = new AnimationTimer() {
            private long update;
            private int number = map.getHoldEnemyNumber();
            private int health = 50 + (map.getGamer().getLevel() - 1) * 20;
            private int rate = 1 + (map.getGamer().getLevel() - 1);
            private int reward = 2 + (map.getGamer().getLevel() - 1) * 2;

            @Override
            public void handle(long now) {
                if (now - update > (800_000_000 - rate * 10) * 2) {
                    update = now;
                    if (map.getGamer().getLevel() % 5 == 0) {
                        map.generateEnemy(health, rate, reward, true, root);
                    } else {
                        map.generateEnemy(health, rate, reward, false, root);
                    }
                    number++;
                }
                if (number == n) {
                    number = 0;
                    health += 20;
                    rate++;
                    reward += 2;
                    this.stop();
                }
            }
        };
        
        start.setLayoutX(580);
        start.setLayoutY(160);
        New.setLayoutX(750);
        New.setLayoutY(40);
        load.setLayoutX(750);
        load.setLayoutY(90);
        nextLevel.setLayoutX(700);
        nextLevel.setLayoutY(160);
        savebtn.setLayoutX(820);
        savebtn.setLayoutY(160);
        confirm.setLayoutX(300);
        confirm.setLayoutY(10);
        enter.setLayoutX(100);
        enter.setLayoutY(10);
        name.setFill(Color.CRIMSON);

        New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                root.getChildren().addAll(imageView, name, enter, confirm);
            }
        });

        savebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    map.save(save);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root.getChildren().clear();
                    map.load(save, root);
                    map.show(root);
                    root.getChildren().addAll(nextLevel, savebtn, start);
                } catch (IOException ex) {
                    Logger.getLogger(TowerDefense.class.getName()).log(Level.SEVERE, null, ex);
                }
                enemyGenerator.start();
            }
        });

        nextLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Enemy.isEmpty()) {
                    enemyGenerator.start();
                    map.setHoldEnemyNumber(0);
                    Enemy.setDiedEnemyNumber(0);
                    Enemy.setIsEmpty(false);
                }
            }
        });

        confirm.setOnAction(new EventHandler<ActionEvent>() {
            String name;

            @Override
            public void handle(ActionEvent event) {
                root.getChildren().clear();
                name = enter.getText();
                if (name.equals("")) {
                    name = "player";
                }

                map.getGamer().setName(name);
                map.show(root);
                root.getChildren().addAll(nextLevel, savebtn, start);
            }
        });

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enemyGenerator.start();
            }
        });
        root.getChildren().addAll(imageView, load, New);

        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
