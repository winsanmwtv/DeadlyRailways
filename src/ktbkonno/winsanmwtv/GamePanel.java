package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

public class GamePanel extends JPanel {

    PlayerLocation player = new PlayerLocation();

    private final URL bgURL = this.getClass().getResource("image/background.png");
    private final Image backgroundImage = new ImageIcon(bgURL).getImage();

    private final URL outoftimeURL = this.getClass().getResource("image/out_of_time.png");
    private final Image outoftime = new ImageIcon(outoftimeURL).getImage();

    private final URL userURL = this.getClass().getResource("image/user.png");
    private final Image userImage = new ImageIcon(userURL).getImage();

    // vertical status: 0 = platform, 1 = road, 2 = rail, 3 = hsr_rail, 9 = end;
    // horizontal status platform: 0 = void, 1 = signal pole, 2 = token;
    // 26-30 would must void;

    static int[] mapVertical = new int[25];
    static int[][] map = new int[25][9];

    static int width;
    static int height;

    ImageIcon restartGameIcon = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    ImageIcon restartGameIconClicked = new ImageIcon(this.getClass().getResource("image/mainmenuOn.png"));
    private JButton mainMenuButton = new JButton(restartGameIcon);


    GamePanel() {
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
                System.out.println("Pressed: " + keyChar);

                        // Update player location based on key press (optional)
                switch (keyChar) {
                    case 'w':
                        // Move player up
                        repaint();
                        break;
                    case 'a':
                        player.MoveLeft();
                        repaint();
                        // Move player left
                        break;
                    case 's':
                        repaint();
                        // Move player down
                        break;
                    case 'd':
                        // Move player right
                        player.MoveRight();
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



    static public void init() {
        mapVertical[0] = 0;
        mapVertical[24] = 9;
        for (int i = 1; i < 25; i++) {
            mapVertical[i] = (int) (0 + (Math.random() * (4 - 0)));
            if (mapVertical[i] == 0) {
                for (int j = 0; j < 9; j++) {
                    map[i][j] = (int) (0 + (Math.random() * (3 - 0)));
                }
            }
        }
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
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);
        g.setColor(Color.WHITE);
        g.drawString("Time left: " + Countdown.getMinute() + ":" + Countdown.getSecond(), 15, 15);

        // Draw user image as a circle
        int userImageX = player.getCurrentLocationX();
        int userImageY = getHeight() - 100;
        int imageWidth = 40;
        int imageHeight = 40;

        // Create a Graphics2D object
        Graphics2D g2d = (Graphics2D) g.create();

        // Create a circular clip
        int diameter = Math.min(imageWidth, imageHeight);
        int x = userImageX + (imageWidth - diameter) / 2;
        int y = userImageY + (imageHeight - diameter) / 2;
        g2d.setClip(new Ellipse2D.Double(x, y, diameter, diameter));

        // Draw the image within the circular clip
        g2d.drawImage(userImage, userImageX, userImageY, null);

        // Dispose of the Graphics2D object
        g2d.dispose();

        if (Countdown.getMinute() == 0 && Countdown.second == 0) {
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
