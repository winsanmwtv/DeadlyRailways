package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GamePanel extends JPanel {

    private final URL bgURL = this.getClass().getResource("image/background.png");
    private final Image backgroundImage = new ImageIcon(bgURL).getImage();

    private final URL outoftimeURL = this.getClass().getResource("image/out_of_time.png");
    private final Image outoftime = new ImageIcon(outoftimeURL).getImage();

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
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);
        g.setColor(Color.WHITE);
        g.drawString("Time left: "+Countdown.getMinute()+":"+Countdown.getSecond(), 15, 15);
        // g.drawString("Game Version: "+Init.gameVer, 15, getHeight()-15);

        PlayerLocation player = new PlayerLocation();

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
