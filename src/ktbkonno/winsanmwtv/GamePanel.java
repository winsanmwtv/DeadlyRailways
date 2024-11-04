package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GamePanel extends JPanel {

    private final URL outoftimeURL = this.getClass().getResource("image/out_of_time.png");
    private final Image outoftime = new ImageIcon(outoftimeURL).getImage();



    ImageIcon restartGameIcon = new ImageIcon(this.getClass().getResource("image/mainmenu.png"));
    ImageIcon restartGameIconClicked = new ImageIcon(this.getClass().getResource("image/mainmenuOn.png"));
    private JButton mainMenuButton = new JButton(restartGameIcon);


    GamePanel() {
        // Countdown.countdown.start();
        timer.start();
        Countdown.countdown.start();
        Countdown.minute = Init.gameTime;
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Time left: "+Countdown.getMinute()+":"+Countdown.getSecond(), 15, 15);
        // g.drawString("Game Version: "+Init.gameVer, 15, getHeight()-15);


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
