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
import javafx.scene.shape.Line;

/**
 *
 * @author km
 */
public class ArrowCastle extends Castle {

    private Line arrow;
    private Image up = new Image("/castle pictures/up arrow.JPG", 40, 40, true, true);
    private ImageView c = new ImageView(up);

    /**
     *
     * @param row
     * @param column
     * @param map
     * @param root
     */
    public ArrowCastle(int row, int column, Map map, Group root) {
        super(3, 2, 1, true, 13, 7, -2, row, column, map, root);
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
    public ArrowCastle(int rate, int range, int damage, boolean air, int upgaradePrice, int price, int type, int row, int column, Map map, Group root) {
        super(rate, range, damage, air, upgaradePrice, price, type, row, column, map, root);
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
                    root.getChildren().add(ArrowCastle.super.getUpgrade());
                } else {
                    if (map.getGamer().getMoney() >= ArrowCastle.super.getUpgaradePrice()) {
                        ArrowCastle.super.setRate(ArrowCastle.super.getRate() + 1);
                        map.getGamer().refreshMoney(root, map.getGamer().getMoney() - ArrowCastle.super.getUpgaradePrice());
                        ArrowCastle.super.setUpgaradePrice(ArrowCastle.super.getUpgaradePrice() * 2 + 1);
                    }
                    root.getChildren().remove(ArrowCastle.super.getUpgrade());
                    clicked = 0;
                }
            }
        });
        root.getChildren().add(c);
    }

    @Override
    public void attack(Enemy aim, Group root, char direction) {
        super.attack(aim, root, direction);
        switch (direction) {
            case 'u':
                arrow = new Line(super.getColumn() * 40 + 20, super.getRow() * 40 + 20, super.getColumn() * 40 + 20, super.getRow() * 40 + 10);
                moveArrowud(root, super.getColumn() * 40 + 20, super.getRow() * 40 + 10, 'u');
                break;
            case 'd':
                arrow = new Line(super.getColumn() * 40 + 20, super.getRow() * 40 + 20, super.getColumn() * 40 + 20, super.getRow() * 40 + 30);

                moveArrowud(root, super.getColumn() * 40 + 20, super.getRow() * 40 + 30, 'd');
                break;
            case 'r':
                arrow = new Line(super.getColumn() * 40 + 20, super.getRow() * 40 + 20, super.getColumn() * 40 + 30, super.getRow() * 40 + 20);

                moveArrowrl(root, super.getColumn() * 40 + 30, super.getRow() * 40 + 20, 'r');
                break;
            case 'l':
                arrow = new Line(super.getColumn() * 40 + 20, super.getRow() * 40 + 20, super.getColumn() * 40 + 10, super.getRow() * 40 + 20);

                moveArrowrl(root, super.getColumn() * 40 + 10, super.getRow() * 40 + 20, 'l');
                break;
        }
        root.getChildren().add(arrow);
    }

    //move arrow line to right or left

    /**
     *
     * @param root
     * @param x
     * @param y
     * @param c
     */
    public void moveArrowrl(Group root, int x, int y, char c) {
        AnimationTimer move = new AnimationTimer() {
            private long update;
            private int X = x;
            private int Y = y;

            @Override
            public void handle(long now) {
                if (now - update > 30_000_000) {
                    update = now;
                    root.getChildren().remove(arrow);
                    arrow.setStartX(X);
                    arrow.setStartY(Y);
                    if (c == 'l') {
                        X -= 10;
                    } else {
                        X += 10;
                    }
                    arrow.setEndX(X);
                    root.getChildren().add(arrow);
                }
                if (Math.abs(X - x) == 30) {
                    root.getChildren().remove(arrow);
                    this.stop();
                }
            }
        };
        move.start();
    }

    //move arrow line up or down

    /**
     *
     * @param root
     * @param x
     * @param y
     * @param c
     */
    public void moveArrowud(Group root, int x, int y, char c) {
        AnimationTimer move = new AnimationTimer() {
            private long update;
            private int X = x;
            private int Y = y;

            @Override
            public void handle(long now) {
                if (now - update > 30_000_000) {
                    update = now;
                    root.getChildren().remove(arrow);
                    arrow.setStartX(X);
                    arrow.setStartY(Y);
                    if (c == 'u') {
                        Y -= 10;
                    } else {
                        Y += 10;
                    }
                    arrow.setEndY(Y);
                    root.getChildren().add(arrow);
                }
                if (Math.abs(Y - y) == 30) {
                    root.getChildren().remove(arrow);
                    this.stop();
                }
            }
        };
        move.start();
    }
}
