// class for handle background
package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Launcher extends JPanel {
    private final URL bgURL = this.getClass().getResource("image/background2.png");
    private final Image backgroundImage = new ImageIcon(bgURL).getImage();

    public static boolean isRunning = false;

    ImageIcon startTitleIcon = new ImageIcon(this.getClass().getResource("image/drTitle.png"));
    JLabel startTitle = new JLabel(startTitleIcon);

    ImageIcon startIcon = new ImageIcon(this.getClass().getResource("image/start.png"));

    ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("image/exit.png"));

    ImageIcon startClick = new ImageIcon(this.getClass().getResource("image/start.png"));
    ImageIcon exitClick = new ImageIcon(this.getClass().getResource("image/exit.png"));

    private JButton startButton = new JButton(startIcon);
    private JButton exitButton = new JButton(exitIcon);
    private JButton hardButton = new JButton("Hardcore Mode");
    private JButton endlessButton = new JButton("Endless Mode");

    Launcher() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xSize = getWidth();
        int ySize = getHeight();
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);

        startTitle.setBounds((getWidth() - 550) / 2, 20, 550, 120);
        add(startTitle);

        Font font = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font);

        g.setColor(Color.WHITE);
        g.drawString("Game Version: "+ Init.gameVer, 15, getHeight()-15);

        startButton.setBounds(((getWidth()/2)-100)-120, (getHeight() / 2), 200, 70);
        add(startButton);
        endlessButton.setBounds(((getWidth()/2)-100)-120, (getHeight() / 2)+75, 200, 70);
        add(endlessButton);
        hardButton.setBounds(((getWidth()/2) -100)+120, (getHeight() / 2)+75, 200, 70);
        add(hardButton);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    return;
                }
                isRunning = true;
                    // remove(startButton);
                    //startButton.setIcon(startClick);
                    // System.out.println("User clicked start");
                Init.gameTime = 5;
                Init.gameLength = 200+6;
                Init.score = 3;
                Init.startGameInit();
                Countdown.minute = 5;
                Countdown.second = 0;
                    //remove(startButton);
                    // startButton.setIcon(startIcon);
                    GamePanel.init();

            }
        });

        endlessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    return;
                }
                isRunning = true;
                // remove(endlessButton);
                //startButton.setIcon(startClick);
                // System.out.println("User clicked start");
                Countdown.minute = 59;
                Countdown.second = 59;
                Init.gameTime = 59;
                Init.gameLength = Init.mapEdge;
                Init.score = 10;
                Init.startGameInit();

                //remove(startButton);
                // startButton.setIcon(startIcon);
                GamePanel.init();

            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    return;
                }
                isRunning = true;
                // remove(startButton);
                //startButton.setIcon(startClick);
                // System.out.println("User clicked start");
                Init.gameTime = 2;
                Init.gameLength = 200+6;
                Init.score = 1;
                Init.startGameInit();
                Countdown.minute = 2;
                Countdown.second = 0;
                //remove(startButton);
                // startButton.setIcon(startIcon);
                GamePanel.init();

            }
        });

        exitButton.setBounds(((getWidth()/2) -100)+120, (getHeight() / 2), 200, 70);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton.setIcon(exitClick);
                // System.out.println("User clicked exit");
                System.exit(0);
                remove(exitButton);
            }
        });
    }
}