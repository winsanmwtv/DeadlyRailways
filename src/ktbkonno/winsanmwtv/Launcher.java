// class for handle background
package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Launcher extends JPanel {
    private final URL bgURL = this.getClass().getResource("image/background.png");
    private final Image backgroundImage = new ImageIcon(bgURL).getImage();

    ImageIcon startTitleIcon = new ImageIcon(this.getClass().getResource("image/drTitle.png"));
    JLabel startTitle = new JLabel(startTitleIcon);

    ImageIcon startIcon = new ImageIcon(this.getClass().getResource("image/start.png"));

    ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("image/exit.png"));

    ImageIcon startClick = new ImageIcon(this.getClass().getResource("image/start.png"));
    ImageIcon exitClick = new ImageIcon(this.getClass().getResource("image/exit.png"));

    private JButton startButton = new JButton(startIcon);
    private JButton exitButton = new JButton(exitIcon);

    Launcher() {
        // add(new LauncherUtil());
        runner.start();
        repaint();
    }

    public Thread runner = new Thread(new Runnable() {
        public void run() {
            while(true){
                try {
                    runner.sleep(10);
                } catch(InterruptedException e) {

                }
                repaint();
            }
        }
    });

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xSize = getWidth();
        int ySize = getHeight();
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);

        startTitle.setBounds((getWidth() - 550) / 2, 20, 550, 120);
        add(startTitle);

        g.setColor(Color.WHITE);
        g.drawString("Game Version: "+Init.gameVer, 15, getHeight()-15);

        startButton.setBounds((getWidth() - 100) / 2, (getHeight() / 2), 100, 40);
        add(startButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setIcon(startClick);
                System.out.println("User clicked start");
                Init.startGameInit();
                Countdown.minute = Init.gameTime;
                Countdown.second = 0;
                remove(startButton);
                startButton.setIcon(startIcon);
                GamePanel.init();
            }
        });

        exitButton.setBounds((getWidth() - 100) / 2, (getHeight() / 2) + 50, 100, 40);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton.setIcon(exitClick);
                System.out.println("User clicked exit");
                System.exit(0);
                remove(exitButton);
            }
        });
    }
}