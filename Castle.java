/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author km
 */
public class Castle {

    private int price;
    private int rate;
    private int range;
    private int damage;
    private boolean air;
    private int upgaradePrice;
    private int type;
    private int row;
    private int column;
    private Text upgrade;

    ;

    /**
     *
     */
    public Castle() {
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
    public Castle(int rate, int range, int damage, boolean air, int upgaradePrice, int price, int type, int row, int column, Map map, Group root) {
        this.rate = rate;
        this.range = range;
        this.damage = damage;
        this.air = air;
        this.upgaradePrice = upgaradePrice;
        this.price = price;
        this.type = type;
        this.column = column;
        this.row = row;
        upgrade = new Text(580, 225, "click another time to upgrade this tower");
        upgrade.setFill(Color.CRIMSON);
        upgrade.setFont(Font.font(20));
        aim(map, root);
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
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the isAir
     */
    public boolean isAir() {
        return air;
    }

    /**
     * @param air
     */
    public void setIsAir(boolean air) {
        this.air = air;
    }

    /**
     * @return the upgaradePrice
     */
    public int getUpgaradePrice() {
        return upgaradePrice;
    }

    /**
     * @param upgaradePrice the upgaradePrice to set
     */
    public void setUpgaradePrice(int upgaradePrice) {
        this.upgaradePrice = upgaradePrice;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @param root
     * @param x
     * @param y
     * @param map
     */
    public void buildCastle(Group root, int x, int y, Map map) {

    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    //choose enemy to attack it
    /**
     *
     * @param map
     * @param root
     */
    public void aim(Map map, Group root) {

        AnimationTimer aim = new AnimationTimer() {
            private int i;
            private int direction;
            private long update;
            private int hold;
            private boolean isChosen = false;

            @Override
            public void handle(long now) {
                if (now - update > 500_000_000 - rate * 10) {
                    update = now;
                    if (!isChosen) {
                        direction = 1;
                        for (i = 1; i <= range; i++) {
                            if ((row + direction) <= 13 && (hold = map.getMap()[row + direction][column]) >= 1 && (Castle.this.air == map.getEnemy()[hold - 1].isAir() || Castle.this.getClass().getSimpleName().equals("ArrowCastle"))) {
                                attack(map.getEnemy()[hold - 1], root, 'd');
                                isChosen = true;
                            }
                            direction++;
                        }
                    }
                    if (!isChosen) {
                        direction = -1;
                        for (i = 1; i <= range; i++) {
                            if ((row + direction) >= 0 && (hold = map.getMap()[row + direction][column]) >= 1 && (Castle.this.air == map.getEnemy()[hold - 1].isAir() || Castle.this.getClass().getSimpleName().equals("ArrowCastle"))) {
                                attack(map.getEnemy()[hold - 1], root, 'u');
                                isChosen = true;

                            }
                            direction--;
                        }
                    }
                    if (!isChosen) {
                        direction = -1;
                        for (i = 1; i <= range; i++) {
                            if ((column + direction) >= 0 && (hold = map.getMap()[row][column + direction]) >= 1 && (Castle.this.air == map.getEnemy()[hold - 1].isAir() || Castle.this.getClass().getSimpleName().equals("ArrowCastle"))) {
                                attack(map.getEnemy()[hold - 1], root, 'l');
                                isChosen = true;

                            }
                            direction--;
                        }
                    }
                    if (!isChosen) {
                        direction = 1;
                        for (i = 1; i <= range; i++) {
                            if ((column + direction) <= 13 && (hold = map.getMap()[row][column + direction]) >= 1 && (Castle.this.air == map.getEnemy()[hold - 1].isAir() || Castle.this.getClass().getSimpleName().equals("ArrowCastle"))) {
                                attack(map.getEnemy()[hold - 1], root, 'r');
                                isChosen = true;

                            }
                            direction++;
                        }
                    }
                    isChosen = false;
                }
            }
        };
        aim.start();
    }

    //decrease the health of an enemy
    /**
     *
     * @param aim
     * @param root
     * @param direction
     */
    public void attack(Enemy aim, Group root, char direction) {
        aim.setHealth(aim.getHealth() - damage * 10);

    }

    /**
     * @return the upgrade
     */
    public Text getUpgrade() {
        return upgrade;
    }

    /**
     * @param upgrade the upgrade to set
     */
    public void setUpgrade(Text upgrade) {
        this.upgrade = upgrade;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + price + " " + rate + " " + range + " " + damage + " " + air + " " + upgaradePrice + " "
                + type + " " + row + " " + column;
        return s;
    }

}
