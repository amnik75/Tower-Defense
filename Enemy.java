/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;
// remove pathfinder

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author km
 */
public class Enemy {

    /**
     * @return the diedEnemyNumber
     */
    public static int getDiedEnemyNumber() {
        return diedEnemyNumber;
    }

    /**
     * @param aDiedEnemyNumber the diedEnemyNumber to set
     */
    public static void setDiedEnemyNumber(int aDiedEnemyNumber) {
        diedEnemyNumber = aDiedEnemyNumber;
    }

    /**
     * @return the isEmpty
     */
    public static boolean isEmpty() {
        return isEmpty;
    }

    /**
     * @param aIsEmpty the isEmpty to set
     */
    public static void setIsEmpty(boolean aIsEmpty) {
        isEmpty = aIsEmpty;
    }

    private int pathNumber;
    private int health;
    private int rate;
    private int row, column;
    private int enemyNumber;
    private int reward;
    private boolean air;
    private boolean alive;
    private final int holeEnemyNumber;
    private static int diedEnemyNumber = 0;
    private static boolean isEmpty = true;
    private Text airLevel = new Text(580, 210, "Attention this is an air level!");
    private String path = "ddllllddddrrrurrrrrddddllllllllddddrrrrrrrrrrruuuuuuuuuuulllluu";
    private Image right = new Image("/enemy/right.JPG", 40, 40, true, true);
    private Image left = new Image("/enemy/left.JPG", 40, 40, true, true);
    private Image up = new Image("/enemy/up.JPG", 40, 40, true, true);
    private Image down = new Image("/enemy/down.JPG", 40, 40, true, true);
    private ImageView enemy;

    /**
     *
     * @param root
     * @param map
     * @param enemyNumber
     * @param reward
     * @param holeEnemyNumber
     * @param air
     */
    public Enemy(Group root, Map map, int enemyNumber, int reward, int holeEnemyNumber, boolean air) {
        this.enemyNumber = enemyNumber;
        this.holeEnemyNumber = holeEnemyNumber;
        this.air = air;
        health = 100;
        rate = 5;
        row = -1;
        column = 5;
        reward = 1;
        pathNumber = 0;
        alive = true;
        enemy = new ImageView();
        pathFinder(root, map);
    }

    /**
     *
     * @param health
     * @param rate
     * @param root
     * @param map
     * @param enemyNumber
     * @param reward
     * @param holeEnemyNumber
     * @param row
     * @param column
     * @param pathNumber
     * @param air
     */
    public Enemy(int health, int rate, Group root, Map map, int enemyNumber, int reward, int holeEnemyNumber, int row, int column, int pathNumber, boolean air) {
        this.enemyNumber = enemyNumber;
        this.health = health;
        this.rate = rate;
        this.reward = reward;
        this.holeEnemyNumber = holeEnemyNumber;
        this.air = air;
        this.row = row;
        this.column = column;
        this.pathNumber = pathNumber;
        alive = true;
        enemy = new ImageView();
        pathFinder(root, map);

    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     *
     * @param root
     * @param map
     */
    
    //find a path for enemy
    public void pathFinder(Group root, Map map) {

        AnimationTimer finder;
        finder = new AnimationTimer() {
            private long update;
            private int r = row;
            private int c = column;

            @Override
            public void handle(long now) {
                if (map.getGamer().getLife() != 0 && health > 0) {
                    if (now - update > 800_000_000 - rate * 10) {
                        update = now;
                        switch (path.charAt(pathNumber)) {
                            case 'l':
                                c--;
                                break;
                            case 'r':
                                c++;
                                break;
                            case 'u':
                                r--;
                                break;
                            case 'd':
                                r++;
                                break;
                        }
                        if (pathNumber == 0) {
                            map.setMapElement(enemyNumber, r, c);
                            move(root, r, c);
                            pathNumber++;
                            row = r;
                            column = c;
                        } else if (pathNumber == 62) {
                            map.setMapElement(-1, row, column);
                            root.getChildren().remove(enemy);
                            pathNumber = 0;
                            row = r;
                            c = column = 5;
                            map.getGamer().refreshLife(root);
                        } else {
                            map.setMapElement(-1, row, column);
                            map.setMapElement(enemyNumber, r, c);
                            root.getChildren().remove(enemy);
                            move(root, r, c);
                            pathNumber++;
                            row = r;
                            column = c;
                        }
                    }
                } else if (health <= 0) {
                    map.setMapElement(-1, row, column);
                    root.getChildren().remove(enemy);
                    map.getGamer().refreshMoney(root, map.getGamer().getMoney() + reward);
                    map.getGamer().refreshScore(root);
                    setDiedEnemyNumber(getDiedEnemyNumber() + 1);
                    if (getDiedEnemyNumber() == holeEnemyNumber) {
                        setIsEmpty(true);
                        map.getGamer().refreshLevle(root);
                        if (map.getGamer().getLevel() % 5 == 0) {
                            airLevel.setFill(Color.CRIMSON);
                            airLevel.setFont(Font.font(20));
                            root.getChildren().add(airLevel);
                        } else {
                            root.getChildren().remove(airLevel);
                        }
                    }
                    alive = false;
                    this.stop();
                } else {
                    this.stop();
                }
            }
        };
        finder.start();
    }

    /**
     *
     * @param root
     * @param row
     * @param column
     */
    
    //move enemy to next step
    public void move(Group root, int row, int column) {
        if (this.row == row && column > this.column) {
            enemy.setImage(right);
        } else if (this.row == row && column < this.column) {
            enemy.setImage(left);
        } else if (this.row < row && column == this.column) {
            enemy.setImage(down);
        } else {
            enemy.setImage(up);
        }
        enemy.setX(column * 40);
        enemy.setY(row * 40);
        root.getChildren().add(enemy);
    }

    /**
     * @return the air
     */
    public boolean isAir() {
        return air;
    }

    /**
     * @param air the air to set
     */
    public void setAir(boolean air) {
        this.air = air;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + health + " " + rate + " " + row + " " + column + " " + enemyNumber + " " + reward + " " + air
                + " " + holeEnemyNumber + " " + diedEnemyNumber + " " + isEmpty + " " + pathNumber;
        return s;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

}
