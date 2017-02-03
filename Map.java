/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdefense;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author km
 */
public class Map {

    private int map[][] = {{0, 0, 0, 0, 0, -1, 0, 0, -1, 0, 0, 0, 0, 0}, {0, -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, 0}, {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}, {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}, {0, -1, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, -1, 0}, {0, -1, -1, -1, -1, 0, 0, 0, 0, -1, 0, 0, -1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, -1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, -1, 0},
    {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, -1, 0}, {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}, {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}, {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}, {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    private Enemy enemy[];
    private int holdEnemyNumber = 0;
    private int holdCastleNumber = 0;
    private Text score = new Text(getMap()[0].length * 40 + 20, 80, "Score:");
    private Text money = new Text(getMap()[0].length * 40 + 170, 80, "money:");
    private Text level = new Text(getMap()[0].length * 40 + 20, 120, "level:");
    private Text life = new Text(getMap()[0].length * 40 + 170, 120, "life:");
    private Text tgamer = new Text(getMap()[0].length * 40 + 20, 40, "Player:");
    private Text tower = new Text(getMap()[0].length * 40 + 20, 260, "Towers:");
    private Text instruction = new Text(getMap()[0].length * 40 + 20, 360, "Instruction:\nPress 'Next level' when kill all the enemies\n"
            + "Double click on the towers to upgrade them\nbut with money limitation!\nTo build towers click on above pictures"
            + " then\nclick on the grass(green part) in the map if\nyou have enough money!");
    private Image ground = new Image("/castle pictures/ground.JPG", 40, 40, true, true);
    private Image grass = new Image("/castle pictures/grass.JPG", 40, 40, true, true);
    private Image choose1 = new Image("/castle pictures/arrow.JPG", 40, 40, true, true);
    private Image choose2 = new Image("/castle pictures/cannon.JPG", 40, 40, true, true);
    private Image choose3 = new Image("/castle pictures/air.JPG", 40, 40, true, true);
    private ImageView c1 = new ImageView(choose1);
    private ImageView c2 = new ImageView(choose2);
    private ImageView c3 = new ImageView(choose3);
    private ImageView g;
    private Castle castle[] = new Castle[134];
    private Gamer gamer;
    private int castleType;
    private String[] loadInformation;

    /**
     *
     * @param n
     * @param root
     * @param name
     */
    public Map(int n) {
        gamer = new Gamer();
        enemy = new Enemy[n];
        mouseEvnent();
    }
    //change element of a map

    /**
     *
     * @param e
     * @param x
     * @param y
     */
    public void setMapElement(int e, int x, int y) {
        map[x][y] = e;
    }
    //generate enemy in the map

    /**
     *
     * @param health
     * @param rate
     * @param reward
     * @param air
     * @param root
     */
    public void generateEnemy(int health, int rate, int reward, boolean air, Group root) {
        if (getHoldEnemyNumber() < getEnemy().length) {
            enemy[getHoldEnemyNumber()] = new Enemy(health, rate, root, this, getHoldEnemyNumber() + 1, reward, enemy.length, -1, 5, 0, air);
            map[0][5] = getHoldEnemyNumber() + 1;
            setHoldEnemyNumber(getHoldEnemyNumber() + 1);
        }
    }

    //show some graphical element
    /**
     *
     * @param root
     */
    public void show(Group root) {
        int i, j;
        score.setFill(Color.CRIMSON);
        score.setFont(Font.font(20));
        getLevel().setFill(Color.CRIMSON);
        getLevel().setFont(Font.font(20));
        money.setFill(Color.CRIMSON);
        money.setFont(Font.font(20));
        life.setFill(Color.CRIMSON);
        life.setFont(Font.font(20));
        tgamer.setFill(Color.CRIMSON);
        tgamer.setFont(Font.font(20));
        tower.setFill(Color.CRIMSON);
        tower.setFont(Font.font(20));
        instruction.setFill(Color.CRIMSON);
        instruction.setFont(Font.font(20));

        Rectangle help = new Rectangle(getMap()[0].length * 40, 0, 410, getMap().length * 40);
        c1.setX(getMap()[0].length * 40 + 20);
        c1.setY(280);
        c2.setX(getMap()[0].length * 40 + 70);
        c2.setY(280);
        c3.setX(getMap()[0].length * 40 + 120);
        c3.setY(280);
        root.getChildren().addAll(help, c1, c2, c3, instruction, score, getLevel(), money, life, tower, tgamer);
        this.getGamer().displayInformation(root);
        for (i = 0; i < getMap().length; i++) {
            for (j = 0; j < getMap()[i].length; j++) {
                if (getMap()[i][j] == 0) {
                    g = new ImageView(grass);
                    g.setX(j * 40);
                    g.setY(i * 40);
                    g.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        private int x, y;

                        @Override
                        public void handle(MouseEvent event) {
                            if (getMap()[x = (int) event.getY() / 40][y = (int) event.getX() / 40] == 0) {
                                if (castleType == -2 && getGamer().getMoney() >= 7) {
                                    castle[holdCastleNumber] = new ArrowCastle(x, y, Map.this, root);
                                    map[x][y] = -2;
                                    castle[holdCastleNumber].buildCastle(root, x, y, Map.this);
                                    getGamer().refreshMoney(root, getGamer().getMoney() - castle[holdCastleNumber].getPrice());
                                    holdCastleNumber++;
                                } else if (castleType == -3 && getGamer().getMoney() >= 9) {
                                    castle[holdCastleNumber] = new CannonCastle(x, y, Map.this, root);
                                    map[x][y] = -3;
                                    castle[holdCastleNumber].buildCastle(root, x, y, Map.this);
                                    getGamer().refreshMoney(root, getGamer().getMoney() - castle[holdCastleNumber].getPrice());
                                    holdCastleNumber++;
                                } else if (castleType == -4 && getGamer().getMoney() >= 12) {
                                    castle[holdCastleNumber] = new AirCastle(x, y, Map.this, root);
                                    map[x][y] = -4;
                                    castle[holdCastleNumber].buildCastle(root, x, y, Map.this);
                                    getGamer().refreshMoney(root, getGamer().getMoney() - castle[holdCastleNumber].getPrice());
                                    holdCastleNumber++;
                                }
                                castleType = 0;
                            }
                        }
                    });
                    root.getChildren().add(g);
                } else if (getMap()[i][j] == -1 || getMap()[i][j] >= 1) {
                    g = new ImageView(ground);
                    g.setX(j * 40);
                    g.setY(i * 40);
                    root.getChildren().add(g);
                }
            }
        }
    }

    //add mouse event to the pictures
    /**
     *
     */
    public void mouseEvnent() {

        c1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                castleType = -2;
            }
        });

        c2.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                castleType = -3;
            }
        });

        c3.setOnMouseClicked(new EventHandler() {

            @Override
            public void handle(Event event) {
                castleType = -4;
            }
        });
    }

    /**
     * @return the gamer
     */
    public Gamer getGamer() {
        return gamer;
    }

    /**
     * @param gamer the gamer to set
     */
    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    /**
     * @return the map
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * @return the enemy
     */
    public Enemy[] getEnemy() {
        return enemy;
    }

    /**
     * @return the holdEnemyNumber
     */
    public int getHoldEnemyNumber() {
        return holdEnemyNumber;
    }

    /**
     * @param holdEnemyNumber the holdEnemyNumber to set
     */
    public void setHoldEnemyNumber(int holdEnemyNumber) {
        this.holdEnemyNumber = holdEnemyNumber;
    }

    /**
     * @return the level
     */
    public Text getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(Text level) {
        this.level = level;
    }

    @Override
    public String toString() {
        String s = "";
        int i, j;
        for (i = 0; i < map.length; i++) {
            for (j = 0; j < map[0].length; j++) {
                s += map[i][j];
            }
            s += "\n";
        }
        s = s + holdEnemyNumber + "\n";
        s = s + holdCastleNumber + "\n";

        checkEnemies();
        for (i = 0; i < holdEnemyNumber; i++) {
            s = s + enemy[i] + "\n";
        }

        for (i = 0; i < holdCastleNumber; i++) {
            s = s + castle[i].toString() + "\n";
        }

        s += gamer.toString();

        return s;
    }

    //save game to the file
    /**
     *
     * @param save
     * @throws FileNotFoundException
     */
    public void save(File save) throws FileNotFoundException {
        int i, j;
        PrintWriter printWriter = new PrintWriter(save);

        for (i = 0; i < 14; i++) {
            for (j = 0; j < 14; j++) {
                printWriter.print(map[i][j] + " ");
            }
            printWriter.println();
        }

        printWriter.println(holdEnemyNumber);
        printWriter.println(holdCastleNumber);

        checkEnemies();
        for (i = 0; i < holdEnemyNumber; i++) {
            printWriter.println(enemy[i]);
        }

        for (i = 0; i < holdCastleNumber; i++) {
            printWriter.println(castle[i]);
        }

        printWriter.println(gamer);

        printWriter.close();
    }

    // count how many lines the file has
    /**
     *
     * @param f
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int countLinesOfFile(File f) throws FileNotFoundException, IOException {
        int n = 0;
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (br.readLine() != null) {
            n++;
        }
        br.close();
        return n;
    }

    //load the game from the file
    /**
     *
     * @param save
     * @param root
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void load(File save, Group root) throws FileNotFoundException, IOException {
        String temp;
        int i = 0;
        loadInformation = new String[countLinesOfFile(save)];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(save));
        while ((temp = bufferedReader.readLine()) != null) {
            loadInformation[i] = temp;
            i++;
        }

        bufferedReader.close();
        loadInformation(root);
    }

    //get information and creat your objects
    /**
     *
     * @param root
     */
    public void loadInformation(Group root) {
        int i, j;
        String hold;
        String information[];
        for (i = 0; i < map.length; i++) {
            hold = loadInformation[i];
            information = hold.split(" ");
            for (j = 0; j < map[0].length; j++) {
                map[i][j] = Integer.parseInt(information[j]);
            }
        }

        holdEnemyNumber = Integer.parseInt(loadInformation[map.length]);
        holdCastleNumber = Integer.parseInt(loadInformation[map.length + 1]);

        for (i = map.length + 2; i < map.length + 2 + holdEnemyNumber; i++) {
            if (!loadInformation[i].equals("null")) {
                hold = loadInformation[i];
                information = hold.split(" ");
                enemy[Integer.parseInt(information[4]) - 1] = new Enemy(Integer.parseInt(information[0]), Integer.parseInt(information[1]), root, this, Integer.parseInt(information[4]), Integer.parseInt(information[5]),
                        Integer.parseInt(information[7]), Integer.parseInt(information[2]), Integer.parseInt(information[3]), Integer.parseInt(information[10]), Boolean.parseBoolean(information[6]));
                Enemy.setDiedEnemyNumber(Integer.parseInt(information[8]));
                Enemy.setIsEmpty(Boolean.parseBoolean(information[9]));
            }
        }

        j = 0;
        for (i = map.length + 2 + holdEnemyNumber; i < map.length + 2 + holdEnemyNumber + holdCastleNumber; i++) {
            hold = loadInformation[i];
            information = hold.split(" ");
            switch (Integer.parseInt(information[6])) {
                case -2:
                    castle[j] = new ArrowCastle(Integer.parseInt(information[1]), Integer.parseInt(information[2]), Integer.parseInt(information[3]),
                            Boolean.parseBoolean(information[4]), Integer.parseInt(information[5]), Integer.parseInt(information[0]),
                            Integer.parseInt(information[6]), Integer.parseInt(information[7]), Integer.parseInt(information[8]), this, root);
                    castle[j].buildCastle(root, Integer.parseInt(information[7]), Integer.parseInt(information[8]), this);
                    break;
                case -3:
                    castle[j] = new CannonCastle(Integer.parseInt(information[1]), Integer.parseInt(information[2]), Integer.parseInt(information[3]),
                            Boolean.parseBoolean(information[4]), Integer.parseInt(information[5]), Integer.parseInt(information[0]),
                            Integer.parseInt(information[6]), Integer.parseInt(information[7]), Integer.parseInt(information[8]), this, root);
                    castle[j].buildCastle(root, Integer.parseInt(information[7]), Integer.parseInt(information[8]), this);
                    break;
                case -4:
                    castle[j] = new AirCastle(Integer.parseInt(information[1]), Integer.parseInt(information[2]), Integer.parseInt(information[3]),
                            Boolean.parseBoolean(information[4]), Integer.parseInt(information[5]), Integer.parseInt(information[0]),
                            Integer.parseInt(information[6]), Integer.parseInt(information[7]), Integer.parseInt(information[8]), this, root);
                    castle[j].buildCastle(root, Integer.parseInt(information[7]), Integer.parseInt(information[8]), this);
                    break;

            }
            j++;
        }
        hold = loadInformation[map.length + 2 + holdEnemyNumber + holdCastleNumber];
        information = hold.split(" ");
        gamer = new Gamer(Integer.parseInt(information[0]), Integer.parseInt(information[1]), Integer.parseInt(information[2]),
                Integer.parseInt(information[3]), information[4]);
    }

    // check wheter the enemy diead or not
    /**
     *
     */
    public void checkEnemies() {
        int i;
        for (i = 0; i < holdEnemyNumber; i++) {
            if (!enemy[i].isAlive()) {
                enemy[i] = null;
            }
        }
    }
}
