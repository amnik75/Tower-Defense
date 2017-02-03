/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author km
 */
public class CannonCastle extends Castle {

    private Circle bullet;
    private Image up = new Image("/castle pictures/up cannon.JPG", 40, 40, true, true);
    private ImageView c = new ImageView(up);

    /**
     *
     * @param row
     * @param column
     * @param map
     * @param root
     */
    public CannonCastle(int row, int column, Map map, Group root) {
        super(1, 1, 3, false, 15, 9, -3, row, column, map, root);
    }

    /**
     *
     * @param rate
     * @param range
     * @param damage
     * @param air
     * @param upgaradePrice
     * @param price
     * @param type
     * @param row
     * @param column
     * @param map
     * @param root
     */
    public CannonCastle(int rate, int range, int damage, boolean air, int upgaradePrice, int price, int type, int row, int column, Map map, Group root) {
        super(rate, range, damage, air, upgaradePrice, price, type, row, column, map, root);
        this.bullet = bullet;
    }

    @Override
    public void buildCastle(Group root, int x, int y, Map map) {
        c.setX(y * 40);
        c.setY(x * 40);
        c.setOnMouseClicked(new EventHandler<MouseEvent>() {
            private int clicked = 0;

            @Override
            public void handle(MouseEvent event) {
                clicked++;
                if (clicked == 1) {
                    root.getChildren().add(CannonCastle.super.getUpgrade());
                } else {
                    if (map.getGamer().getMoney() >= CannonCastle.super.getUpgaradePrice()) {
                        CannonCastle.super.setRate(CannonCastle.super.getRate() + 1);
                        map.getGamer().refreshMoney(root, map.getGamer().getMoney() - CannonCastle.super.getUpgaradePrice());
                        CannonCastle.super.setUpgaradePrice(CannonCastle.super.getUpgaradePrice() * 2 + 1);
                    }
                    root.getChildren().remove(CannonCastle.super.getUpgrade());
                    clicked = 0;
                }
            }
        });
        root.getChildren().add(c);
    }

    @Override

    public void attack(Enemy aim, Group root, char direction) {
        super.attack(aim, root, direction);
        bullet = new Circle(super.getColumn() * 40 + 20, super.getRow() * 40 + 20, 10, Color.BLACK);
        root.getChildren().add(bullet);

        switch (direction) {
            case 'u':
                moveBulletud(root, 'u', super.getRow() * 40 + 20);
                break;
            case 'd':
                moveBulletud(root, 'd', super.getRow() * 40 + 20);
                break;
            case 'r':
                moveBulletrl(root, 'r', super.getColumn() * 40 + 20);
                break;
            case 'l':
                moveBulletrl(root, 'l', super.getColumn() * 40 + 20);
                break;
        }

    }

    //move bullet up and down

    /**
     *
     * @param root
     * @param direction
     * @param y
     */
    public void moveBulletud(Group root, char direction, int y) {
        AnimationTimer move = new AnimationTimer() {
            private long update;
            private int Y = y;

            @Override
            public void handle(long now) {
                if (now - update > 30_000_000) {
                    update = now;
                    root.getChildren().remove(bullet);
                    if (direction == 'u') {
                        Y -= 10;
                    } else {
                        Y += 10;
                    }
                    bullet.setCenterY(Y);
                    root.getChildren().add(bullet);
                }
                if (Math.abs(Y - y) == 30) {
                    root.getChildren().remove(bullet);
                    this.stop();
                }
            }
        };
        move.start();
    }

    //move bullet right or left

    /**
     *
     * @param root
     * @param direction
     * @param x
     */
    public void moveBulletrl(Group root, char direction, int x) {
        AnimationTimer move = new AnimationTimer() {
            private long update;
            private int X = x;

            @Override
            public void handle(long now) {
                if (now - update > 30_000_000) {
                    update = now;
                    root.getChildren().remove(bullet);
                    if (direction == 'l') {
                        X -= 10;
                    } else {
                        X += 10;
                    }
                    bullet.setCenterX(X);
                    root.getChildren().add(bullet);
                }
                if (Math.abs(X - x) == 30) {
                    root.getChildren().remove(bullet);
                    this.stop();
                }
            }
        };
        move.start();
    }
}
