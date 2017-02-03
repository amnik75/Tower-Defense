/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author km
 */
public class Gamer {

    private int money;
    private int score;
    private int level;
    private int life;
    private Text tmoney;
    private Text tscore;
    private Text tlevel;
    private Text tlife;
    private Text tname;
    private Text loose;
    private String name;

    /**
     *
     * @param s
     */
    public Gamer() {
        money = 40;
        score = 0;
        level = 1;
        life = 20;
        name = "";
        loose = new Text(275, 260, "You loose!");
    }

    /**
     *
     * @param money
     * @param score
     * @param level
     * @param life
     * @param s
     */
    public Gamer(int money, int score, int level, int life, String s) {
        this.money = money;
        this.score = score;
        this.level = level;
        this.life = life;
        name = s;
        loose = new Text(275, 260, "You loose!");
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(int life) {
        this.life = life;
    }
    
    
    //show the information of the gamer

    /**
     *
     * @param root
     */
    public void displayInformation(Group root) {
        tname = new Text(650, 40, getName());
        tmoney = new Text(805, 80, "" + money);
        tscore = new Text(640, 80, "" + score);
        tlevel = new Text(630, 120, "" + level);
        tlife = new Text(770, 120, "" + life);
        loose = new Text(380, 260, "You loose!");
        
        getTscore().setFill(Color.CRIMSON);
        getTscore().setFont(Font.font(20));
        getTlevel().setFill(Color.CRIMSON);
        getTlevel().setFont(Font.font(20));
        getTmoney().setFill(Color.CRIMSON);
        getTmoney().setFont(Font.font(20));
        getTlife().setFill(Color.CRIMSON);
        getTlife().setFont(Font.font(20));
        getTname().setFill(Color.CRIMSON);
        getTname().setFont(Font.font(20));
        root.getChildren().addAll(getTlevel(), getTlife(), getTmoney(), getTscore(), getTname());
    }

    //modify money

    /**
     *
     * @param root
     * @param money
     */
    public void refreshMoney(Group root, int money) {
        this.money = money;
        root.getChildren().remove(getTmoney());
        getTmoney().setText("" + money);
        root.getChildren().add(getTmoney());

    }

    //modify score 

    /**
     *
     * @param root
     */
    public void refreshScore(Group root) {
        score += 20;
        root.getChildren().remove(getTscore());
        getTscore().setText("" + score);
        root.getChildren().add(getTscore());

    }
    
    //modify level

    /**
     *
     * @param root
     */
    public void refreshLevle(Group root) {
        level++;
        root.getChildren().remove(getTlevel());
        getTlevel().setText("" + level);
        root.getChildren().add(getTlevel());

    }

    //modify life

    /**
     *
     * @param root
     */
    public void refreshLife(Group root) {
        life--;
        root.getChildren().remove(getTlife());
        getTlife().setText("" + life);
        root.getChildren().add(getTlife());
        if (life == 0) {
            getLoose().setFill(Color.CRIMSON);
            getLoose().setFont(Font.font(50));
            root.getChildren().clear();
            root.getChildren().add(getLoose());
        }

    }

    @Override
    public String toString() {
        String s = "";
        s = s + money + " " + score + " " + level + " " + life + " " + getName();
        return s;
    }

    /**
     * @return the tname
     */
    public Text getTname() {
        return tname;
    }

    /**
     * @param tname the tname to set
     */
    public void setTname(Text tname) {
        this.tname = tname;
    }

    /**
     * @return the tmoney
     */
    public Text getTmoney() {
        return tmoney;
    }

    /**
     * @param tmoney the tmoney to set
     */
    public void setTmoney(Text tmoney) {
        this.tmoney = tmoney;
    }

    /**
     * @return the tscore
     */
    public Text getTscore() {
        return tscore;
    }

    /**
     * @param tscore the tscore to set
     */
    public void setTscore(Text tscore) {
        this.tscore = tscore;
    }

    /**
     * @return the tlevel
     */
    public Text getTlevel() {
        return tlevel;
    }

    /**
     * @param tlevel the tlevel to set
     */
    public void setTlevel(Text tlevel) {
        this.tlevel = tlevel;
    }

    /**
     * @return the tlife
     */
    public Text getTlife() {
        return tlife;
    }

    /**
     * @param tlife the tlife to set
     */
    public void setTlife(Text tlife) {
        this.tlife = tlife;
    }

    /**
     * @return the loose
     */
    public Text getLoose() {
        return loose;
    }

    /**
     * @param loose the loose to set
     */
    public void setLoose(Text loose) {
        this.loose = loose;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
