package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Launcher extends JPanel {

    JLabel startTitle = new JLabel();
    ImageIcon startTitleImage = new ImageIcon(this.getClass().getResource("image/drTitle.png"));

    JLabel startButton = new JLabel();
    ImageIcon startIcon = new ImageIcon(this.getClass().getResource("image/start.png"));

    JLabel exitButton = new JLabel();
    ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("image/exit.png"));

    JLabel spaces = new JLabel();
    ImageIcon spacesIcon = new ImageIcon(this.getClass().getResource("image/spaces.png"));

    ImageIcon startClick = new ImageIcon(this.getClass().getResource("image/startClick.png"));
    ImageIcon exitClick = new ImageIcon(this.getClass().getResource("image/exitClick.png"));

    Launcher() {
        setLayout(new GridLayout(4, 1));
        startButton.setIcon(startIcon);
        startTitle.setIcon(startTitleImage);
        exitButton.setIcon(exitIcon);
        spaces.setIcon(spacesIcon);
        add(startTitle);
        add(spaces);
        add(startButton);
        add(exitButton);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startButton.setIcon(startClick);

                Timer timer = new Timer(25, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startButton.setIcon(startIcon);
                        System.out.println("tf");
                    }
                });
                timer.setRepeats(false); // Ensure the timer triggers only once
                timer.start();
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exitButton.setIcon(exitClick);

                Timer timer = new Timer(25, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exitButton.setIcon(exitIcon);
                        System.exit(0);
                    }
                });
                timer.setRepeats(false); // Ensure the timer triggers only once
                timer.start();
            }
        });
    }
}
