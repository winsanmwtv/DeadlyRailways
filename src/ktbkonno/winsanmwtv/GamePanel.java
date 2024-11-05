package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

public class GamePanel extends JPanel {

    // static protected int zeroCount = 0;

    static PlayerLocation player = new PlayerLocation();

    public static int y = 1;
    public static int x = 4;

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

    // vertical status: 0 = platform, 1 = road, 2 = rail, 3 = hsr_rail, 9 = endLoc, 8 = startLoc;
    // horizontal status platform: 0 = void, 1 = signal pole, 2 = assistanceItem;
    // 26-30 would must void;

    static int[] mapVertical = new int[Init.gameLength];
    static int[][] map = new int[Init.gameLength][9];

    static int width;
    static int height;

    static int yLoc;

    ImageIcon restartGameIcon = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    ImageIcon restartGameIconClicked = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    private JButton mainMenuButton = new JButton(restartGameIcon);


    GamePanel() {

        yLoc = player.getY(1);
        // Countdown.countdown.start();
        timer.start();
        Countdown.countdown.start();
        Countdown.minute = Init.gameTime;

        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent
                    e) {
                char keyChar = e.getKeyChar();
                // System.out.println("Pressed: " + keyChar);

                        // Update player location based on key press (optional)
                switch (keyChar) {
                    case 'w':

                        if (y == 0) {
                            if (map[y+3][x] == 1) break;
                            else if (map[y+3][x] == 2) {
                                player.setScore(player.getScore()+Init.addScore);
                                map[y+3][x] = 0;
                            }
                            else if (map[y+3][x] == 3) {
                                Countdown.addTime(Init.addTime);
                                map[y+3][x] = 0;
                            }
                            yLoc = player.getY(2);
                            repaint();
                            y++;
                            break;
                        }

                        if (map[y+3][x] == 1) break;
                        else if (map[y+3][x] == 2) {
                            player.setScore(player.getScore()+Init.addScore);
                            map[y+3][x] = 0;
                        }
                        else if (map[y+3][x] == 3) {
                            Countdown.addTime(Init.addTime);
                            map[y+3][x] = 0;
                        }

                        if (!isDone) {// Move player up
                        if (y < Init.gameLength-6) y++;
                        if (y >= Init.gameLength-6 && x == 4) isDone = true;
                        }
                        repaint();
                        break;
                    case 'a':

                        if (!isDone) {
                            if (x > 0) {
                                if (map[y+2][x-1] == 1) break;
                                else if (map[y+2][x-1] == 2) {
                                    player.setScore(player.getScore()+Init.addScore);
                                    map[y+2][x-1] = 0;
                                }
                                else if (map[y+2][x-1] == 3) {
                                    Countdown.addTime(Init.addTime);
                                    map[y+2][x-1] = 0;
                                }
                            player.MoveLeft();
                                if (y >= Init.gameLength-6 && x == 5) isDone = true;
                             x--;}
                        }
                        // Move player left
                        repaint();
                        break;
                    case 's':
                        if (y == 1) {
                            if (map[y][x] == 1) break;
                            else if (map[y][x] == 2) {
                                player.setScore(player.getScore()+Init.addScore);
                                map[y][x] = 0;
                            }
                            else if (map[y][x] == 3) {
                                Countdown.addTime(Init.addTime);
                                map[y][x] = 0;
                            }
                            yLoc = player.getY(1);
                            repaint();
                            y--;
                            break;
                        }
                        if (map[y+1][x] == 1) break;
                        else if (map[y+1][x] == 2) {
                            player.setScore(player.getScore()+Init.addScore);
                            map[y+1][x] = 0;
                        }
                        else if (map[y+1][x] == 3) {
                            Countdown.addTime(Init.addTime);
                            map[y+1][x] = 0;
                        }
                        if (!isDone) {if (y > 0) y--;
                        }
                        // Move player down
                        repaint();
                        break;
                    case 'd':

                        // Move player right
                        if (!isDone) {if (x < 8) {
                            if (map[y+2][x+1] == 1) break;
                            else if (map[y+2][x+1] == 2) {
                                player.setScore(player.getScore()+Init.addScore);
                                map[y+2][x+1] = 0;
                            }
                            else if (map[y+2][x+1] == 3) {
                                Countdown.addTime(Init.addTime);
                                map[y+2][x+1] = 0;
                            }
                            player.MoveRight();
                            if (y >= Init.gameLength-6 && x == 3) isDone = true;

                            x++;}
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
    }



    static int zeroCount; // Declared outside the init method

    static public void init() {
        yLoc = player.getY(1);
        mapVertical[2] = 8;
        mapVertical[Init.gameLength - 4] = 9;
        int minWalkableZeros = 5;      // Minimum number of 0's required in each row for walkability
        int maxSequenceLength = 3;     // Maximum allowed length of consecutive non-0 values in mapVertical

        for (int i = 3; i < Init.gameLength-4; i++) {
            // Assign values to mapVertical based on specified probabilities
            mapVertical[i] = getRandomValue(new int[] { 0, 1, 2, 3 }, new double[] { 0.50, 0.30, 0.15, 0.05 });

            // Check for consecutive non-0 sequence length
            int consecutiveCount = 1;
            for (int j = 3; j < i; j++) {
                if (mapVertical[j] == mapVertical[j - 1] && mapVertical[j] != 0) {
                    consecutiveCount++;
                    if (consecutiveCount > maxSequenceLength) {
                        mapVertical[j] = 0; // Insert a 0 to break the sequence
                        consecutiveCount = 1;
                    }
                } else {
                    consecutiveCount = 1;
                }
            }

            // Initialize map[i][j] based on mapVertical[i]
            for (int j = 0; j < 9; j++) {
                if (mapVertical[i] == 0) {
                    // Generate row ensuring minimum number of 0s for walkability
                    do {
                        zeroCount = 0;
                        for (j = 0; j < 9; j++) {
                            map[i][j] = getRandomValue(new int[] { 0, 1, 2, 3 }, new double[] { 0.83, 0.15, 0.01, 0.01 });
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

        for (int i = Init.gameLength-3; i < Init.gameLength; i++) mapVertical[i] = 7; // set void
        // map[Init.gameLength-5][4] = 4;
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




    public Thread timer = new Thread(new Runnable() {
        public void run() {

            while(true){
                try {
                    timer.sleep(10);
                } catch(InterruptedException e) {

                }
                repaint();
                if (Countdown.getMinute() == 0 && Countdown.second == 0) {
                    // Init.onGameEnd();
                    Countdown.countdown.interrupt();
                    // break;
                }
                if (y != 0) yLoc = player.getY(2);
                repaint();
            }
        }
    });

    public static int getWidthSize() {
        return width;
    }
    public static int getHeightSize() {
        return height;
    }

    public void paintComponent(Graphics g) {
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
                }
            }
            else if (mapVertical[y+i] == 1) {
                g.drawImage(roadImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[y+i] == 2) {
                g.drawImage(railImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[y+i] == 3) {
                g.drawImage(hsrImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[y+i] == 8) {
                g.drawImage(startPlatImage, 0, player.getY(i), 2000, 140, this);
            }
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
                    else if (map[y+i][j] == 2) {
                        g.drawImage(hp, player.getActualX(j), player.getY(i)+10, this);
                    }
                    else if (map[y+i][j] == 3) {
                        g.drawImage(clock, player.getActualX(j), player.getY(i)+10, this);
                    }
                }
            }
            else if (mapVertical[(y+1)+i] == 1) {
                g.drawImage(roadImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[(y+1)+i] == 2) {
                g.drawImage(railImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[(y+1)+i] == 3) {
                g.drawImage(hsrImage, 0, player.getY(i), 2000, 70, this);
            }
            else if (mapVertical[(y+1)+i] == 8) {
                g.drawImage(startPlatImage, 0, player.getY(i), 2000, 140, this);
            }

        }}

        if (y == 0) yLoc = player.getY(1);

        g.drawString("Time left: " + Countdown.getMinute() + ":" + Countdown.getSecond(), 15, 15);
        g.drawString("HP Left: "+player.getScore(), 15, 30);
        if (Init.isDebug) {
            g.drawString("X: "+x+" Y: "+y, 15, 45);
            g.drawString("Game Version: "+ Init.gameVer, 15, getHeight()-15);
        }


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
            g.drawString("Your HP left: "+player.getScore(), (getWidth()/2)-150, (getHeight()/2)+50);
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
}
