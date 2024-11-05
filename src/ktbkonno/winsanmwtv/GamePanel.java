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

    public static int coin = 0;

    static public boolean isDone = false;

    private final URL bgURL = this.getClass().getResource("image/background.png");
    private final Image backgroundImage = new ImageIcon(bgURL).getImage();

    private final URL winURL = this.getClass().getResource("image/win.png");
    private final Image winImage = new ImageIcon(winURL).getImage();

    private final URL outoftimeURL = this.getClass().getResource("image/out_of_time.png");
    private final Image outoftime = new ImageIcon(outoftimeURL).getImage();

    private final URL outofscoreURL = this.getClass().getResource("image/out_of_score.png");
    private final Image outofscore = new ImageIcon(outofscoreURL).getImage();

    private final URL userURL = this.getClass().getResource("image/user.png");
    private final Image userImage = new ImageIcon(userURL).getImage();

    private final URL platformURL = this.getClass().getResource("image/platform.png");
    private final Image platformImage = new ImageIcon(platformURL).getImage();

    private final URL roadURL = this.getClass().getResource("image/road.png");
    private final Image roadImage = new ImageIcon(roadURL).getImage();

    private final URL signalURL = this.getClass().getResource("image/signal.png");
    private final Image signalImage = new ImageIcon(signalURL).getImage();

    private final URL railURL = this.getClass().getResource("image/rail.png");
    private final Image railImage = new ImageIcon(railURL).getImage();

    private final URL hsrURL = this.getClass().getResource("image/railHSR.png");
    private final Image hsrImage = new ImageIcon(hsrURL).getImage();

    private final URL startPlatURL = this.getClass().getResource("image/startPlat.png");
    private final Image startPlatImage = new ImageIcon(startPlatURL).getImage();

    private final URL homeSweetHomeURL = this.getClass().getResource("image/home_sweet_home.png");
    private final Image homeSweetHome = new ImageIcon(homeSweetHomeURL).getImage();

    private final URL myHome = this.getClass().getResource("image/home.png");
    private final Image home = new ImageIcon(myHome).getImage();

    private final URL hpURL = this.getClass().getResource("image/hp.png");
    private final Image hp = new ImageIcon(hpURL).getImage();

    private final URL clockURL = this.getClass().getResource("image/clock.png");
    private final Image clock = new ImageIcon(clockURL).getImage();

    private final URL carLeftURL = this.getClass().getResource("image/car_left.png");
    private final Image carLeft = new ImageIcon(carLeftURL).getImage();

    private final URL carRightURL = this.getClass().getResource("image/car_right.png");
    private final Image carRight = new ImageIcon(carRightURL).getImage();

    private final URL trainLeftURL = this.getClass().getResource("image/train_left.png");
    private final Image trainLeft = new ImageIcon(carLeftURL).getImage();

    private final URL trainRightURL = this.getClass().getResource("image/train_right.png");
    private final Image trainRight = new ImageIcon(carRightURL).getImage();

    private final URL hsrLeftURL = this.getClass().getResource("image/bullet_left.png");
    private final Image hsrLeft = new ImageIcon(carLeftURL).getImage();

    private final URL hsrRightURL = this.getClass().getResource("image/bullet_right.png");
    private final Image hsrRight = new ImageIcon(carRightURL).getImage();

    private final URL roadClosedURL = this.getClass().getResource("image/roadClosed.png");
    private final Image roadClosed = new ImageIcon(roadClosedURL).getImage();

    private final URL coinURL = this.getClass().getResource("image/coin.png");
    private final Image coinImage = new ImageIcon(coinURL).getImage();

    // vertical status: 0 = platform, 1 = road, 2 = rail, 3 = hsr_rail, 9 = endLoc, 8 = startLoc;
    // 4 = void; 5 = roadClosed;
    // horizontal status platform: 0 = void, 1 = signal pole, 2 = assistanceItem;
    // 26-30 would must void;

    static int[] mapVertical = new int[Init.gameLength];
    static int[][] map = new int[Init.gameLength][9];
    static int[] vehicleDirection = new int[Init.gameLength];
    static boolean[][] vehicleCollapse = new boolean[Init.gameLength][2];
    static int[][] vehicleX = new int[Init.gameLength][2];
    // 0 left 1 right;

    static boolean isRun = false;

    static int width;
    static int height;

    static int yLoc;

    ImageIcon restartGameIcon = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    ImageIcon restartGameIconClicked = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    private JButton mainMenuButton = new JButton(restartGameIcon);


    GamePanel() {
        isRun = true;
        vehicleRunner.start();
        yLoc = player.getY(1);
        // Countdown.countdown.start();
        Countdown.minute = Init.gameTime;

        coin = 0;



        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                int keyCode = e.getKeyCode();

                // Update player location based on key press
                switch (keyCode) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        if (y == 0) {
                            // Countdown.playStatus = true;
                            if (map[y+3][x] == 1) break;
                            else if (map[y+3][x] == 2) {
                                player.setScore(player.getScore() + Init.addScore);
                                map[y+3][x] = 0;
                            } else if (map[y+3][x] == 3) {
                                Countdown.addTime(Init.addTime);
                                map[y+3][x] = 0;
                            } else if (map[y+3][x] == 4) {
                                coin++;
                                map[y+3][x] = 0;
                            }
                            yLoc = player.getY(2);
                            repaint();
                            y++;
                            break;
                        }
                        if (map[y+3][x] == 1) break;
                        else if (map[y+3][x] == 2) {
                            // Countdown.playStatus = true;
                            player.setScore(player.getScore() + Init.addScore);
                            map[y+3][x] = 0;
                        } else if (map[y+3][x] == 3) {
                            // Countdown.playStatus = true;
                            Countdown.addTime(Init.addTime);
                            map[y+3][x] = 0;
                        } else if (map[y+3][x] == 4) {
                            // Countdown.playStatus = true;
                            coin++;
                            map[y+3][x] = 0;
                        }
                        if (!isDone) {
                            // Countdown.playStatus = true;
                            if (y < Init.gameLength-6) y++;
                            if (y >= Init.gameLength-6 && x == 4) isDone = true;
                        }
                        repaint();
                        break;

                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        if (!isDone) {
                            // Countdown.playStatus = true;
                            if (x > 0) {
                                if (map[y+2][x-1] == 1) break;
                                else if (map[y+2][x-1] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y+2][x-1] = 0;
                                } else if (map[y+2][x-1] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y+2][x-1] = 0;
                                }
                                else if (map[y+2][x-1] == 4) {
                                    coin++;
                                    map[y+2][x-1] = 0;
                                }
                                player.MoveLeft();
                                if (y >= Init.gameLength-6 && x == 5) isDone = true;
                                x--;
                            }
                            else {
                                // System.out.println("to edge");
                                if (map[y+2][8] == 1) break;
                                else if (map[y+2][8] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y+2][x-1] = 0;
                                } else if (map[y+2][8] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y+2][8] = 0;
                                } else if (map[y+2][8] == 4) {
                                    coin++;
                                    map[y+2][8] = 0;
                                }
                                player.MoveLeft();
                                if (y >= Init.gameLength-6 && x == 5) isDone = true;
                                x = 8;

                            }
                        }
                        repaint();
                        break;

                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
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
                            }
                            yLoc = player.getY(1);
                            repaint();
                            y--;
                            break;
                        }
                        if (map[y+1][x] == 1) break;
                        else if (map[y+1][x] == 2) {
                            // Countdown.playStatus = true;
                            player.setScore(player.getScore() + Init.addScore);
                            map[y+1][x] = 0;
                        } else if (map[y+1][x] == 3) {
                            // Countdown.playStatus = true;
                            Countdown.addTime(Init.addTime);
                            map[y+1][x] = 0;
                        } else if (map[y+1][x] == 4) {
                            // Countdown.playStatus = true;
                            coin++;
                            map[y+1][x] = 0;
                        }
                        if (!isDone) {
                            if (y > 0) y--;
                        }
                        repaint();
                        break;

                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        if (!isDone) {
                            //Countdown.playStatus = true;
                            if (x < 8) {
                                if (map[y+2][x+1] == 1) break;
                                else if (map[y+2][x+1] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y+2][x+1] = 0;
                                } else if (map[y+2][x+1] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y+2][x+1] = 0;
                                } else if (map[y+2][x+1] == 4) {
                                    coin++;
                                    map[y+2][x+1] = 0;
                                }
                                player.MoveRight();
                                if (y >= Init.gameLength-6 && x == 3) isDone = true;
                                x++;
                            } else {
                                // System.out.println("to edge");
                                if (map[y+2][0] == 1) break;
                                else if (map[y+2][0] == 2) {
                                    player.setScore(player.getScore() + Init.addScore);
                                    map[y+2][0] = 0;
                                } else if (map[y+2][0] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y+2][0] = 0;
                                } else if (map[y+2][0] == 4) {
                                    coin++;
                                    map[y+2][0] = 0;
                                }
                                player.MoveRight();
                                if (y >= Init.gameLength-6 && x == 3) isDone = true;
                                x = 0;
                            }
                        }
                        repaint();
                        break;
                }

                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        addKeyListener(listener);
        setFocusable(true); // Make the panel focusable for key input
        // Make the panel focusable for key input
    }

    // vehicle runner;
    Thread vehicleRunner = new Thread(new Runnable() {
        @Override
        public void run() {
            while(isRun) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < Init.gameLength; i++) {
                    if (mapVertical[i] != 0) {
                        switch(mapVertical[i]) {
                            case 1:
                                int[] vehicleLoc = new int[2];
                                for (int j = 0; j < 2; j++) {
                                    if (vehicleDirection[i] == 0) {
                                        if (vehicleX[i][j] > -100) vehicleX[i][j] -= Init.carSpeed;
                                        else vehicleX[i][j] = PlayerLocation.panelWidth+100;
                                    }
                                    else {
                                        if (vehicleX[i][j] < PlayerLocation.panelWidth+100) vehicleX[i][j] += Init.carSpeed;
                                        else vehicleX[i][j] = -100;
                                    }
                                    vehicleLoc[j] = vehicleX[i][j];
                                    if ((y + 2) == i) {
                                        // Check for collision when moving up (W)
                                        if (vehicleX[i][0] < player.getCurrentLocationX() + 45 && vehicleX[i][0] + 90 > player.getCurrentLocationX() - 5) {
                                            mapVertical[i] = 5; // Set the map value indicating a collision
                                            player.deductScore();
                                            break; // Exit the loop since a collision was detected
                                        }
                                        // Check for collision when moving down (S)
                                        else if (vehicleX[i][1] < player.getCurrentLocationX() + 45 && vehicleX[i][1] + 90 > player.getCurrentLocationX() - 5) {
                                            mapVertical[i] = 5; // Set the map value indicating a collision
                                            player.deductScore();
                                            break; // Exit the loop since a collision was detected
                                        }
                                    }



                                }
                                break;
                            case 2:
                                break;
                            case 3:
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
            } while ((value == 2 || value == 3) && (mapVertical[i - 1] == 2 || mapVertical[i - 1] == 3));

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
                    do {
                        zeroCount = 0;
                        for (j = 0; j < 9; j++) {
                            // Adjust probabilities for coin, `1`, and default to `0`
                            map[i][j] = getRandomValue(new int[]{0, 1, 4}, new double[]{0.80, 0.17, 0.03});
                            if (map[i][j] == 0) {
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

        for (int i = Init.gameLength - 3; i < Init.gameLength; i++) mapVertical[i] = 7; // set void

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
                    if (vehicleDirection[i] == 0) vehicleX[i][0] = (PlayerLocation.panelWidth + 200);
                    else vehicleX[i][0] = -200;
                    break;
                case 3:
                    if (vehicleDirection[i] == 0) vehicleX[i][0] = (PlayerLocation.panelWidth + 300);
                    else vehicleX[i][0] = -300;
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
        player.setWidth(getWidth());
        player.setHeight(getHeight());
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);
        g.setColor(Color.WHITE);

        // do get Y
        if (y >= 1) {for (int i = 1; i <= 5; i++) {
            //System.out.println(mapVertical[y+i]);

            if (mapVertical[y+i] == 0) {
                g.drawImage(platformImage, 0, player.getY(i), 2000, 70, this);
                for (int j = 0; j < 9; j++) {
                    if (map[y+i][j] == 1) {
                        g.drawImage(signalImage, player.getActualX(j), player.getY(i) + 10, this);
                    }
                    else if (map[y+i][j] == 2) {
                        g.drawImage(hp, player.getActualX(j), player.getY(i)+10, this);
                    }
                    else if (map[y+i][j] == 3) {
                        g.drawImage(clock, player.getActualX(j), player.getY(i)+10, this);
                    }
                    else if (map[y+i][j] == 4) {
                        g.drawImage(coinImage, player.getActualX(j), player.getY(i)+10, this);
                    }
                }
            }
            else if (mapVertical[y+i] == 1) {
                g.drawImage(roadImage, 0, player.getY(i), 2000, 70, this);
                for (int j = 0; j < 2; j++) {
                    if (vehicleDirection[y+i] == 0) g.drawImage(carLeft, vehicleX[y+i][j], player.getY(i), this);
                    else g.drawImage(carRight, vehicleX[y+i][j], player.getY(i), this);
                }
            }
            else if (mapVertical[y+i] == 2) {
                g.drawImage(railImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[y+i] == 3) {
                g.drawImage(hsrImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[y+i] == 5) {
                g.drawImage(roadClosed, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[y+i] == 8) {
                g.drawImage(startPlatImage, 0, player.getY(i), 2000, 140, this);
            }

            // end game area
            else if (mapVertical[y+i] == 9) {
                g.drawImage(homeSweetHome, 0, player.getY(i)-350, 2000, 420, this);
                g.drawImage(home, player.getActualX(4), player.getY(i)+10, this);
            }

        }}

        else if (y == 0) {for (int i = 1; i <= 5; i++) {
            //System.out.println(mapVertical[(y+1)+i]);

            if (mapVertical[(y+1)+i] == 0) {
                g.drawImage(platformImage, 0, player.getY(i), 2000, 70, this);
                for (int j = 0; j < 9; j++) {
                    if (map[(y+1)+i][j] == 1) {
                        g.drawImage(signalImage, player.getActualX(j), player.getY(i) + 10, this);
                    }
                    else if (map[(y+1)+i][j] == 2) {
                        g.drawImage(hp, player.getActualX(j), player.getY(i)+10, this);
                    }
                    else if (map[(y+1)+i][j] == 3) {
                        g.drawImage(clock, player.getActualX(j), player.getY(i)+10, this);
                    }
                    else if (map[(y+1)+i][j] == 4) {
                        g.drawImage(coinImage, player.getActualX(j), player.getY(i)+10, this);
                    }
                }
            }
            else if (mapVertical[(y+1)+i] == 1) {
                g.drawImage(roadImage, 0, player.getY(i), 2000, 70, this);
                for (int j = 0; j < 2; j++) {
                    if (vehicleDirection[(y+1)+i] == 0) g.drawImage(carLeft, vehicleX[(y+1)+i][j], player.getY(i), this);
                    else g.drawImage(carRight, vehicleX[(y+1)+i][j], player.getY(i), this);
                }

            }
            else if (mapVertical[(y+1)+i] == 2) {
                g.drawImage(railImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[(y+1)+i] == 3) {
                g.drawImage(hsrImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[(y+1)+i] == 5) {
                g.drawImage(roadClosed, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[(y+1)+i] == 8) {
                g.drawImage(startPlatImage, 0, player.getY(i), 2000, 140, this);
            }

        }}

        if (y == 0) yLoc = player.getY(1);

        g.drawString("Time left: " + Countdown.getMinute() + ":" + Countdown.getSecond(), 15, 15);
        g.drawString("HP Left: "+player.getScore()+", Coin: "+coin, 15, 30);
        if (Init.isDebug) {
            g.drawString("X: "+x+" Y: "+y, 15, 45);
            g.drawString("Game Version: "+ Init.gameVer, 15, getHeight()-15);
        }

        g.fillOval(player.getCurrentLocationX()-2, yLoc+8, 54, 54);

        // Draw user image as a circle
        int userImageX = player.getCurrentLocationX();
        int userImageY = yLoc+10;
        int imageWidth = 50;
        int imageHeight = 50;

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

        if (player.getScore() == 0) {
            g.drawImage(outofscore, (getWidth() - 1920)/2, (getHeight() - 1080)/2, 1920, 1080, this);
            //Countdown.playStatus = false;
            mainMenuButton.setBounds(getWidth() / 2, (getHeight() / 2) + 30, 100, 40);
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

        else if (isDone) {
            g.drawImage(winImage, (getWidth() - 1920)/2, (getHeight() - 1080)/2, 1920, 1080, this);
            // Countdown.playStatus = false;
            g.drawString("Your HP left: "+player.getScore(), (getWidth()/2)-150, (getHeight()/2)+50);
            g.drawString("Total used time: "+Countdown.getTotalTime(), (getWidth()/2)-150, (getHeight()/2)+70);
            g.drawString("You got "+coin+" coins!", (getWidth()/2)-150, (getHeight()/2)+90);
            mainMenuButton.setBounds(getWidth() / 2, (getHeight() / 2) + 30, 100, 40);
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

        else if (Countdown.getMinute() == 0 && Countdown.second == 0) {
            g.drawImage(outoftime, (getWidth() - 1920)/2, (getHeight() - 1080)/2, 1920, 1080, this);
            //Countdown.playStatus = false;
            mainMenuButton.setBounds(getWidth() / 2, (getHeight() / 2) + 30, 100, 40);
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
    }
    static int getPlayerX() {
        return player.getCurrentLocationX()+25;
    }

    static int getPlayerY() {
        return yLoc+35;
    }
}
