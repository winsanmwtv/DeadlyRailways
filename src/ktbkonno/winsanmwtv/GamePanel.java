package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    // static protected int zeroCount = 0;

    static PlayerLocation player = new PlayerLocation();

    public static int y = 1;
    public static int x = 4;

    public static boolean isEndless = false;

    public static int coin = 0;

    static public boolean isDone = false;
    static public boolean isDead = false;

    private Image loadImage(String path) {
        URL url = this.getClass().getResource(path);
        return new ImageIcon(url).getImage();
    }

    // Load all images using the loadImage method
    private final Image backgroundImage = loadImage("image/background.png");
    private final Image winImage = loadImage("image/win.png");
    private final Image outoftime = loadImage("image/out_of_time.png");
    private final Image outofscore = loadImage("image/out_of_score.png");
    private final Image userImage = loadImage("image/user.png");
    private final Image platformImage = loadImage("image/platform.png");
    private final Image roadImage = loadImage("image/road.png");
    private final Image signalImage = loadImage("image/signal.png");
    private final Image railImage = loadImage("image/rail.png");
    private final Image hsrImage = loadImage("image/railHSR.png");
    private final Image startPlatImage = loadImage("image/startPlat.png");
    private final Image homeSweetHome = loadImage("image/home_sweet_home.png");
    // private final Image home = loadImage("image/home.png");
    private final Image hp = loadImage("image/hp.png");
    private final Image clock = loadImage("image/clock.png");
    private final Image carLeft = loadImage("image/car_left.png");
    private final Image carRight = loadImage("image/car_right.png");
    private final Image trainLeft = loadImage("image/train_left.png");
    private final Image trainRight = loadImage("image/train_right.png");
    private final Image hsrLeft = loadImage("image/bullet_left.png");
    private final Image hsrRight = loadImage("image/bullet_right.png");
    private final Image roadClosed = loadImage("image/roadClosed.png");
    private final Image railClosed = loadImage("image/railClosed.png");
    private final Image hsrClosed = loadImage("image/hsrClosed.png");
    private final Image coinImage = loadImage("image/coin.png");
    private final Image hsrComing = loadImage("image/hsrComing.png");
    private final Image heart = loadImage("image/heart.png");
    private final Image heartCyan = loadImage("image/heartCyan.png");
    private final Image police = loadImage("image/police.png");

    // vertical status: 0 = platform, 1 = road, 2 = rail, 3 = hsr_rail, 9 = endLoc, 8 = startLoc;
    // 4 = void; 5 = roadClosed;
    // horizontal status platform: 0 = void, 1 = signal pole, 2 = assistanceItem;
    // 26-30 would must void;

    static int[] mapVertical = new int[Init.mapEdge];
    static int[][] map = new int[Init.mapEdge][9];
    static int[] vehicleDirection = new int[Init.mapEdge];
    // static boolean[][] vehicleCollapse = new boolean[Init.gameLength][2];
    static int[][] vehicleX = new int[Init.mapEdge][2];
    // 0 left 1 right;

    static boolean isRun = false;

    static int width;
    static int height;

    static int yLoc;

    ImageIcon restartGameIcon = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    ImageIcon restartGameIconClicked = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    private JButton mainMenuButton = new JButton(restartGameIcon);


    GamePanel() {
        isDead = false;
        isRun = true;
        vehicleRunner.start();
        yLoc = player.getY(1);
        // Countdown.countdown.start();
        Countdown.minute = Init.gameTime;

        coin = 0;


        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                int keyCode = e.getKeyCode();

                // Update player location based on key press
                switch (keyCode) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_SPACE:
                        if (isDead) break;
                        if (y == 0) {
                            // Countdown.playStatus = true;
                            if (map[y + 3][x] == 1) break;
                            else if (map[y + 3][x] == 2) {
                                player.setScore(player.getScore() + Init.addScore);
                                map[y + 3][x] = 0;
                            } else if (map[y + 3][x] == 3) {
                                Countdown.addTime(Init.addTime);
                                map[y + 3][x] = 0;
                            } else if (map[y + 3][x] == 4) {
                                coin++;
                                map[y + 3][x] = 0;
                            } else if (map[y + 3][x] == 5) {
                                Countdown.deductTime(Init.addTime * 3);
                                map[y + 3][x] = 0;
                            }
                            yLoc = player.getY(2);
                            repaint();
                            y++;
                            break;
                        }
                        if (map[y + 3][x] == 1) break;
                        else if (map[y + 3][x] == 2) {
                            // Countdown.playStatus = true;
                            player.setScore(player.getScore() + Init.addScore);
                            map[y + 3][x] = 0;
                        } else if (map[y + 3][x] == 3) {
                            // Countdown.playStatus = true;
                            Countdown.addTime(Init.addTime);
                            map[y + 3][x] = 0;
                        } else if (map[y + 3][x] == 4) {
                            // Countdown.playStatus = true;
                            coin++;
                            map[y + 3][x] = 0;
                        } else if (map[y + 3][x] == 5) {
                            // Countdown.playStatus = true;
                            Countdown.deductTime(Init.addTime * 3);
                            map[y + 3][x] = 0;
                        }
                        if (!isDone) {
                            // Countdown.playStatus = true;
                            if (y < Init.gameLength - 6) y++;
                            if (y >= Init.gameLength - 6 && x == 2) isDone = true;
                        }
                        repaint();
                        break;

                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        if (isDead) break;
                        if (!isDone) {
                            // Countdown.playStatus = true;
                            if (x > 0) {
                                if (map[y + 2][x - 1] == 1) break;
                                else if (map[y + 2][x - 1] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y + 2][x - 1] = 0;
                                } else if (map[y + 2][x - 1] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y + 2][x - 1] = 0;
                                } else if (map[y + 2][x - 1] == 4) {
                                    coin++;
                                    map[y + 2][x - 1] = 0;
                                } else if (map[y + 2][x - 1] == 5) {
                                    Countdown.deductTime(Init.addTime * 3);
                                    map[y + 2][x - 1] = 0;
                                }
                                player.MoveLeft();
                                if (y >= Init.gameLength - 6 && x == 3) isDone = true;
                                x--;
                            } else {
                                // System.out.println("to edge");
                                if (map[y + 2][8] == 1) break;
                                else if (map[y + 2][8] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y + 2][x - 1] = 0;
                                } else if (map[y + 2][8] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y + 2][8] = 0;
                                } else if (map[y + 2][8] == 4) {
                                    coin++;
                                    map[y + 2][8] = 0;
                                } else if (map[y + 2][8] == 5) {
                                    Countdown.deductTime(Init.addTime * 3);
                                    map[y + 2][8] = 0;
                                }
                                player.MoveLeft();
                                if (y >= Init.gameLength - 6 && x == 3) isDone = true;
                                x = 8;

                            }
                        }
                        repaint();
                        break;

                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_SHIFT:
                        if (isDead) break;
                        if (y == 1) {
                            // Countdown.playStatus = true;
                            if (map[y][x] == 1) break;
                            else if (map[y][x] == 2) {
                                player.setScore(player.getScore() + Init.addScore);
                                map[y][x] = 0;
                            } else if (map[y][x] == 3) {
                                Countdown.addTime(Init.addTime);
                                map[y][x] = 0;
                            } else if (map[y][x] == 4) {
                                coin++;
                                map[y][x] = 0;
                            } else if (map[y][x] == 5) {
                                Countdown.deductTime(Init.addTime * 3);
                                map[y][x] = 0;
                            }
                            yLoc = player.getY(1);
                            repaint();
                            y--;
                            break;
                        }
                        if (map[y + 1][x] == 1) break;
                        else if (map[y + 1][x] == 2) {
                            // Countdown.playStatus = true;
                            player.setScore(player.getScore() + Init.addScore);
                            map[y + 1][x] = 0;
                        } else if (map[y + 1][x] == 3) {
                            // Countdown.playStatus = true;
                            Countdown.addTime(Init.addTime);
                            map[y + 1][x] = 0;
                        } else if (map[y + 1][x] == 4) {
                            // Countdown.playStatus = true;
                            coin++;
                            map[y + 1][x] = 0;
                        } else if (map[y + 1][x] == 5) {
                            // Countdown.playStatus = true;
                            Countdown.deductTime(Init.addTime * 3);
                            map[y + 1][x] = 0;
                        }
                        if (!isDone) {
                            if (y > 0) y--;
                        }
                        repaint();
                        break;

                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        if (isDead) break;
                        if (!isDone) {
                            //Countdown.playStatus = true;
                            if (x < 8) {
                                if (map[y + 2][x + 1] == 1) break;
                                else if (map[y + 2][x + 1] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y + 2][x + 1] = 0;
                                } else if (map[y + 2][x + 1] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y + 2][x + 1] = 0;
                                } else if (map[y + 2][x + 1] == 4) {
                                    coin++;
                                    map[y + 2][x + 1] = 0;
                                } else if (map[y + 2][x + 1] == 5) {
                                    Countdown.deductTime(Init.addTime * 3);
                                    map[y + 2][x + 1] = 0;
                                }
                                player.MoveRight();
                                if (y >= Init.gameLength - 6 && x == 1) isDone = true;
                                x++;
                            } else {
                                // System.out.println("to edge");
                                if (map[y + 2][0] == 1) break;
                                else if (map[y + 2][0] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y + 2][0] = 0;
                                } else if (map[y + 2][0] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y + 2][0] = 0;
                                } else if (map[y + 2][0] == 4) {
                                    coin++;
                                    map[y + 2][0] = 0;
                                } else if (map[y + 2][0] == 5) {
                                    Countdown.deductTime(Init.addTime * 3);
                                    map[y + 2][0] = 0;
                                }
                                player.MoveRight();
                                if (y >= Init.gameLength - 6 && x == 1) isDone = true;
                                x = 0;
                            }
                        }
                        repaint();
                        break;
                }

                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        addKeyListener(listener);
        setFocusable(true); // Make the panel focusable for key input
        // Make the panel focusable for key input
    }

    // vehicle runner;
    Thread vehicleRunner = new Thread(new Runnable() {
        @Override
        public void run() {
            while (isRun) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < Init.gameLength; i++) {
                    if (mapVertical[i] != 0) {
                        switch (mapVertical[i]) {
                            case 1:
                                int[] vehicleLoc = new int[2];
                                for (int j = 0; j < 2; j++) {
                                    if (vehicleDirection[i] == 0) {
                                        if (vehicleX[i][j] > -100) vehicleX[i][j] -= Init.carSpeed;
                                        else vehicleX[i][j] = PlayerLocation.panelWidth + 100;
                                    } else {
                                        if (vehicleX[i][j] < PlayerLocation.panelWidth + 100)
                                            vehicleX[i][j] += Init.carSpeed;
                                        else vehicleX[i][j] = -100;
                                    }
                                    vehicleLoc[j] = vehicleX[i][j];
                                    if ((y + 2) == i) {
                                        // Check for collision when moving up (W)
                                        if (vehicleX[i][0] < player.getCurrentLocationX() + 45 && vehicleX[i][0] + 45 > player.getCurrentLocationX() - 5) {
                                            mapVertical[i] = 5; // Set the map value indicating a collision
                                            player.deductScore();
                                            break; // Exit the loop since a collision was detected
                                        }
                                        // Check for collision when moving down (S)
                                        else if (vehicleX[i][1] < player.getCurrentLocationX() + 45 && vehicleX[i][1] + 45 > player.getCurrentLocationX() - 5) {
                                            mapVertical[i] = 5; // Set the map value indicating a collision
                                            player.deductScore();
                                            break; // Exit the loop since a collision was detected
                                        }
                                    }


                                }
                                break;
                            case 2:
                                int[] trainLoc = new int[1];
                                for (int j = 0; j < 1; j++) {
                                    if (vehicleDirection[i] == 0) {
                                        if (vehicleX[i][j] > -800.) vehicleX[i][j] -= Init.trainSpeed;
                                        else vehicleX[i][j] = PlayerLocation.panelWidth + 800;
                                    } else {
                                        if (vehicleX[i][j] < PlayerLocation.panelWidth + 800)
                                            vehicleX[i][j] += Init.trainSpeed;
                                        else vehicleX[i][j] = -800;
                                    }
                                    trainLoc[j] = vehicleX[i][j];
                                    if ((y + 2) == i) {
                                        // Check for collision when moving up (W)
                                        if (vehicleX[i][0] < player.getCurrentLocationX() + 45 && vehicleX[i][0] + 100 > player.getCurrentLocationX() - 5) {
                                            mapVertical[i] = 6; // Set the map value indicating a collision
                                            player.deductScore();
                                            break; // Exit the loop since a collision was detected
                                        }
                                        // Check for collision when moving down (S)
//                                        else if (vehicleX[i][1] < player.getCurrentLocationX() + 45 && vehicleX[i][1] + 100 > player.getCurrentLocationX() - 5) {
//                                            mapVertical[i] = 6; // Set the map value indicating a collision
//                                            player.deductScore();
//                                            break; // Exit the loop since a collision was detected
//                                        }
                                    }
                                }
                                break;
                            case 3:
                            case 10:
                                int[] hsrLoc = new int[1];
                                if (vehicleX[i][0] > -1000 && vehicleX[i][0] < PlayerLocation.panelWidth + 1000)
                                    mapVertical[i] = 10;
                                else mapVertical[i] = 3;
                                for (int j = 0; j < 1; j++) {
                                    if (vehicleDirection[i] == 0) {
                                        if (vehicleX[i][j] > -4000) vehicleX[i][j] -= Init.hsrSpeed;
                                        else vehicleX[i][j] = PlayerLocation.panelWidth + 4000;
                                    } else {
                                        if (vehicleX[i][j] < PlayerLocation.panelWidth + 4000)
                                            vehicleX[i][j] += Init.hsrSpeed;
                                        else vehicleX[i][j] = -4000;
                                    }
                                    hsrLoc[j] = vehicleX[i][j];
                                    if ((y + 2) == i) {
                                        // Check for collision when moving up (W)
                                        if (vehicleX[i][0] < player.getCurrentLocationX() + 45 && vehicleX[i][0] + 150 > player.getCurrentLocationX() - 5) {
                                            mapVertical[i] = 7; // Set the map value indicating a collision
                                            player.deductScore();
                                            break; // Exit the loop since a collision was detected
                                        }
                                        // Check for collision when moving down (S)
//                                        else if (vehicleX[i][1] < player.getCurrentLocationX() + 45 && vehicleX[i][1] + 150 > player.getCurrentLocationX() - 5) {
//                                            mapVertical[i] = 7; // Set the map value indicating a collision
//                                            player.deductScore();
//                                            break; // Exit the loop since a collision was detected
//                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
                repaint();
            }
        }
    });

    static int zeroCount; // Declared outside the init method

    static public void init() {

        for (int i = 0; i < Init.mapEdge; i++) {
            mapVertical[i] = 0;
            vehicleDirection[i] = 0;
            vehicleX[i][0] = 0;
            vehicleX[i][1] = 0;
            for (int j = 0; j < 9; j++) {
                map[i][j] = 0;
            }
        }
        yLoc = player.getY(1);
        mapVertical[2] = 8;
        mapVertical[Init.gameLength - 4] = 9;
        int minWalkableZeros = 5;      // Minimum number of 0's required in each row for walkability
        int maxSequenceLength = 3;     // Maximum allowed length of consecutive non-0 values in mapVertical


        // Track the count of consecutive non-zero values (1, 2, or 3)
        int consecutiveCount = 0;

        for (int i = 3; i < Init.gameLength - 4; i++) {
            int value;
            do {
                // Assign values to mapVertical based on specified probabilities
                value = getRandomValue(new int[]{0, 1, 2, 3}, new double[]{0.50, 0.30, 0.15, 0.05});
            } while ((value == 2 || value == 3) && (mapVertical[i - 2] == 2 || mapVertical[i - 2] == 3));

            mapVertical[i] = value;

            // Check for consecutive non-0 sequence length
            if (value != 0) {
                consecutiveCount++;
                if (consecutiveCount > 4) {
                    mapVertical[i] = 0; // Insert a 0 to break the sequence
                    consecutiveCount = 1; // Reset count to 1 (current value)
                }
            } else {
                consecutiveCount = 0; // Reset count if a 0 is encountered
            }

            // Check for max sequence length
            if (mapVertical[i] != 0) {
                int seqCount = 1;
                for (int j = 3; j < i; j++) {
                    if (mapVertical[j] == mapVertical[j - 1] && mapVertical[j] != 0) {
                        seqCount++;
                        if (seqCount > maxSequenceLength) {
                            mapVertical[j] = 0; // Insert a 0 to break the sequence
                            seqCount = 1;
                        }
                    } else {
                        seqCount = 1;
                    }
                }
            }

            // Initialize map[i][j] based on mapVertical[i]
            for (int j = 0; j < 9; j++) {
                if (mapVertical[i] == 0) {
                    // Generate row ensuring minimum number of 0s for walkability
                    boolean coinPlaced = false; // Track if a coin has been placed
                    boolean policePlaced = false;
                    zeroCount = 0; // Reset zero count for this row

                    do {
                        zeroCount = 0;
                        for (int k = 0; k < 9; k++) {
                            // Adjust probabilities for coin (4), `1`, and default to `0`
                            if (!coinPlaced && getRandomValue(new int[]{0, 1, 2, 3, 4, 5}, new double[]{0.511, 0.28, 0.001, 0.001, 0.2, 0.007}) == 4) {
                                map[i][k] = 4; // Place a coin
                                coinPlaced = true; // Mark that a coin has been placed
                            } else if (!policePlaced && getRandomValue(new int[]{0, 1, 2, 3, 4, 5}, new double[]{0.511, 0.28, 0.001, 0.001, 0.2, 0.007}) == 5) {
                                map[i][k] = 5; // Place a coin
                                policePlaced = true; // Mark that a coin has been placed
                            } else {
                                map[i][k] = getRandomValue(new int[]{0, 1, 2, 3}, new double[]{0.818, 0.18, 0.001, 0.001}); // Place `0` or `1`
                            }
                            if (map[i][k] == 0) {
                                zeroCount++;
                            }
                        }
                        // Regenerate if not enough 0s
                    } while (zeroCount < minWalkableZeros);
                } else {
                    map[i][j] = 0; // Non-walkable if mapVertical[i] != 0
                }
            }
        }

        for (int i = Init.gameLength - 3; i < Init.gameLength; i++) mapVertical[i] = 11; // set void

        for (int i = 0; i < Init.gameLength; i++) vehicleDirection[i] = (int) (Math.random() * (2 - 0)) + 0;

        // init car/train/hsr bullet train
        for (int i = 0; i < Init.gameLength; i++) {
            switch (mapVertical[i]) {
                case 1:
                    if (vehicleDirection[i] == 0) {
                        for (int j = 0; j < 2; j++) {
                            vehicleX[i][j] = (PlayerLocation.panelWidth + 100) + (j * 500);
                        }
                    } else {
                        for (int j = 0; j < 2; j++) {
                            vehicleX[i][j] = (-100) - (j * 500);
                        }
                    }
                    break;
                case 2:
                    if (vehicleDirection[i] == 0) vehicleX[i][0] = (PlayerLocation.panelWidth + 400);
                    else vehicleX[i][0] = -400;
                    break;
                case 3:
                case 10:
                    if (vehicleDirection[i] == 0) vehicleX[i][0] = (PlayerLocation.panelWidth + 1200);
                    else vehicleX[i][0] = -1200;
                    break;
            }
        }
    }


    // Helper method to generate random values based on specified probabilities
    private static int getRandomValue(int[] values, double[] probabilities) {
        double random = Math.random();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < values.length; i++) {
            cumulativeProbability += probabilities[i];
            if (random <= cumulativeProbability) {
                return values[i];
            }
        }
        return values[values.length - 1]; // Fallback in case of rounding errors
    }

    public void paintComponent(Graphics g) {
        if (y != 0) yLoc = player.getY(2);
        super.paintComponent(g);
        int xSize = getWidth();
        int ySize = getHeight();
        this.width = xSize;
        this.height = ySize;
        PlayerLocation.panelWidth = getWidth();
        player.setWidth(getWidth());
        player.setHeight(getHeight());
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);
        g.setColor(Color.WHITE);


        // do get Y
        if (y >= 1) {
            for (int i = 1; i <= 5; i++) {
                //System.out.println(mapVertical[y+i]);

                if (mapVertical[y + i] == 0) {
                    g.drawImage(platformImage, 0, player.getY(i), 2000, 70, this);
                    for (int j = 0; j < 9; j++) {
                        if (map[y + i][j] == 1) {
                            g.drawImage(signalImage, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[y + i][j] == 2) {
                            g.drawImage(hp, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[y + i][j] == 3) {
                            g.drawImage(clock, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[y + i][j] == 4) {
                            g.drawImage(coinImage, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[y + i][j] == 5) {
                            g.drawImage(police, player.getActualX(j), player.getY(i) + 10, this);
                        }
                    }
                } else if (mapVertical[y + i] == 1) {
                    g.drawImage(roadImage, 0, player.getY(i), 2000, 70, this);
                    for (int j = 0; j < 2; j++) {
                        if (vehicleDirection[y + i] == 0)
                            g.drawImage(carLeft, vehicleX[y + i][j], player.getY(i), this);
                        else g.drawImage(carRight, vehicleX[y + i][j], player.getY(i), this);
                    }
                } else if (mapVertical[y + i] == 2) {
                    g.drawImage(railImage, 0, player.getY(i), 2000, 70, this);
                    if (vehicleDirection[y + i] == 0) g.drawImage(trainLeft, vehicleX[y + i][0], player.getY(i), this);
                    else g.drawImage(trainRight, vehicleX[y + i][0], player.getY(i), this);
                } else if (mapVertical[y + i] == 3) {
                    g.drawImage(hsrImage, 0, player.getY(i), 2000, 70, this);
                    if (vehicleDirection[y + i] == 0) g.drawImage(hsrLeft, vehicleX[y + i][0], player.getY(i), this);
                    else g.drawImage(hsrRight, vehicleX[y + i][0], player.getY(i), this);
                } else if (mapVertical[y + i] == 5) {
                    g.drawImage(roadClosed, 0, player.getY(i), 2000, 70, this);
                } else if (mapVertical[y + i] == 6) {
                    g.drawImage(railClosed, 0, player.getY(i), 2000, 70, this);
                } else if (mapVertical[y + i] == 7) {
                    g.drawImage(hsrClosed, 0, player.getY(i), 2000, 70, this);
                } else if (mapVertical[y + i] == 8) {
                    g.drawImage(startPlatImage, 0, player.getY(i), 2000, 140, this);
                } else if (mapVertical[y + i] == 10) {
                    g.drawImage(hsrComing, 0, player.getY(i), 2000, 70, this);
                    if (vehicleDirection[y + i] == 0) g.drawImage(hsrLeft, vehicleX[y + i][0], player.getY(i), this);
                    else g.drawImage(hsrRight, vehicleX[y + i][0], player.getY(i), this);
                }


                // end game area
                else if (mapVertical[y + i] == 9) {
                    g.drawImage(homeSweetHome, 0, player.getY(i) - 350, 2000, 420, this);
                    // g.drawImage(home, player.getActualX(4), player.getY(i)+10, this);
                }

            }
        } else if (y == 0) {
            for (int i = 1; i <= 5; i++) {
                //System.out.println(mapVertical[(y+1)+i]);

                if (mapVertical[(y + 1) + i] == 0) {
                    g.drawImage(platformImage, 0, player.getY(i), 2000, 70, this);
                    for (int j = 0; j < 9; j++) {
                        if (map[(y + 1) + i][j] == 1) {
                            g.drawImage(signalImage, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[(y + 1) + i][j] == 2) {
                            g.drawImage(hp, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[(y + 1) + i][j] == 3) {
                            g.drawImage(clock, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[(y + 1) + i][j] == 4) {
                            g.drawImage(coinImage, player.getActualX(j), player.getY(i) + 10, this);
                        } else if (map[(y + 1) + i][j] == 5) {
                            g.drawImage(police, player.getActualX(j), player.getY(i) + 10, this);
                        }
                    }
                } else if (mapVertical[(y + 1) + i] == 1) {
                    g.drawImage(roadImage, 0, player.getY(i), 2000, 70, this);
                    for (int j = 0; j < 2; j++) {
                        if (vehicleDirection[(y + 1) + i] == 0)
                            g.drawImage(carLeft, vehicleX[(y + 1) + i][j], player.getY(i), this);
                        else g.drawImage(carRight, vehicleX[(y + 1) + i][j], player.getY(i), this);
                    }

                } else if (mapVertical[(y + 1) + i] == 2) {
                    g.drawImage(railImage, 0, player.getY(i), 2000, 70, this);
                    if (vehicleDirection[(y + 1) + i] == 0)
                        g.drawImage(trainLeft, vehicleX[(y + 1) + i][0], player.getY(i), this);
                    else g.drawImage(trainRight, vehicleX[(y + 1) + i][0], player.getY(i), this);
                } else if (mapVertical[(y + 1) + i] == 3) {
                    g.drawImage(hsrImage, 0, player.getY(i), 2000, 70, this);
                    if (vehicleDirection[(y + 1) + i] == 0)
                        g.drawImage(hsrLeft, vehicleX[(y + 1) + i][0], player.getY(i), this);
                    else g.drawImage(hsrRight, vehicleX[(y + 1) + i][0], player.getY(i), this);
                } else if (mapVertical[(y + 1) + i] == 5) {
                    g.drawImage(roadClosed, 0, player.getY(i), 2000, 70, this);
                } else if (mapVertical[(y + 1) + i] == 6) {
                    g.drawImage(railClosed, 0, player.getY(i), 2000, 70, this);
                } else if (mapVertical[(y + 1) + i] == 7) {
                    g.drawImage(hsrClosed, 0, player.getY(i), 2000, 70, this);
                } else if (mapVertical[(y + 1) + i] == 8) {
                    g.drawImage(startPlatImage, 0, player.getY(i), 2000, 140, this);
                } else if (mapVertical[(y + 1) + i] == 10) {
                    g.drawImage(hsrComing, 0, player.getY(i), 2000, 70, this);
                    if (vehicleDirection[(y + 1) + i] == 0)
                        g.drawImage(hsrLeft, vehicleX[(y + 1) + i][0], player.getY(i), this);
                    else g.drawImage(hsrRight, vehicleX[(y + 1) + i][0], player.getY(i), this);
                }
            }
        }
        //System.out.println(y+" "+(Init.gameLength-30));
        //System.out.println(y > Init.gameLength-30);
        if (y > Init.gameLength - 10) {
            for (int i = 0; i < player.getScore(); i++) {
                int tmpX = ((getWidth() - 20) - 15) - (i * 30);
                g.drawImage(heartCyan, tmpX, 15, 25, 25, this);
            }
        } else {
            for (int i = 0; i < player.getScore(); i++) {
                int tmpX = ((getWidth() - 20) - 15) - (i * 30);
                g.drawImage(heart, tmpX, 15, 25, 25, this);
            }
        }

        if (y == 0) yLoc = player.getY(1);
        Font font = new Font("Arial", Font.PLAIN, 18);
        g.setFont(font);


        if (!isEndless) g.drawString("Time left: " + Countdown.getMinute() + ":" + Countdown.getSecond(), 15, 20);
        if (!isEndless) g.drawString("Steps left: " + ((Init.gameLength - y) - 6) + ", Coin: " + coin, 15, 40);
        else g.drawString("Total steps so far: " + y + " steps, Coin: " + coin, 15, 20);
        Font font2 = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font2);
        if (Init.isDebug) {


            g.drawString("X: " + x + " Y: " + y, 15, getHeight() - 30);

        }
        g.drawString("Game Version: " + Init.gameVer, 15, getHeight() - 15);
        // g.fillOval(player.getCurrentLocationX()-2, yLoc+8, 54, 54);

        // Draw user image as a circle
        int userImageX = player.getCurrentLocationX();
        int userImageY = yLoc + 7;
        int imageWidth = 54;
        int imageHeight = 54;

        // Create a Graphics2D object
        Graphics2D g2d = (Graphics2D) g.create();


        // Create a circular clip
        int diameter = Math.min(imageWidth, imageHeight);
        int x = userImageX + (imageWidth - diameter) / 2;
        int yI = userImageY + (imageHeight - diameter) / 2;
        g2d.setClip(new Ellipse2D.Double(x, yI, diameter, diameter));

        // Draw the image within the circular clip, adjusting for centering
        g2d.drawImage(userImage, x, yI, diameter, diameter, null);

        // Dispose of the Graphics2D object
        g2d.dispose();
        // System.out.println("Y: "+y+" Max: "+Init.gameLength);

        if (player.getScore() <= 0) {
            isDead = true;
            g.drawImage(outofscore, (getWidth() - 1920) / 2, (getHeight() - 1080) / 2, 1920, 1080, this);
            //Countdown.playStatus = false;
            mainMenuButton.setBounds((getWidth() / 2) - 300, (getHeight() / 2) + 120, 200, 70);
            add(mainMenuButton);
            mainMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMenuButton.setIcon(restartGameIconClicked);
                    Init.onGameEnd();
                    remove(mainMenuButton);
                }
            });
        } else if (isDone) {
            isDead = true;
            g.drawImage(winImage, (getWidth() - 1920) / 2, (getHeight() - 1080) / 2, 1920, 1080, this);
            // Countdown.playStatus = false;
            g.drawString("Your life left: " + player.getScore(), (getWidth() / 2) - 50, (getHeight() / 2) + 140);
            g.drawString("Total used time: " + Countdown.getTotalTime(), (getWidth() / 2) - 50, (getHeight() / 2) + 160);
            g.drawString("You got " + coin + " coins!", (getWidth() / 2) - 50, (getHeight() / 2) + 180);
            mainMenuButton.setBounds((getWidth() / 2) - 300, (getHeight() / 2) + 120, 200, 70);
            add(mainMenuButton);
            mainMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMenuButton.setIcon(restartGameIconClicked);
                    Init.onGameEnd();
                    remove(mainMenuButton);
                }
            });
        } else if (Countdown.getMinute() <= 0 && Countdown.second <= 0) {
            isDead = true;
            g.drawImage(outoftime, (getWidth() - 1920) / 2, (getHeight() - 1080) / 2, 1920, 1080, this);
            //Countdown.playStatus = false;
            g.drawString("Be faster next time!", (getWidth() / 2) - 50, (getHeight() / 2) + 160);
            mainMenuButton.setBounds((getWidth() / 2) - 300, (getHeight() / 2) + 120, 200, 70);
            add(mainMenuButton);
            mainMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainMenuButton.setIcon(restartGameIconClicked);
                    Init.onGameEnd();
                    remove(mainMenuButton);
                }
            });
        }
        // heart

    }

    static int getPlayerX() {
        return player.getCurrentLocationX() + 25;
    }

    static int getPlayerY() {
        return yLoc + 35;
    }
}
