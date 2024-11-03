package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Launcher extends JPanel {

    JLabel startTitle = new JLabel();
    ImageIcon startTitleImage = new ImageIcon(this.getClass().getResource("image/drTitle.png"));

    JLabel startButton = new JLabel();
    ImageIcon startIcon = new ImageIcon(this.getClass().getResource("image/start.png"));

    JLabel exitButton = new JLabel();
    ImageIcon exitIcon = new ImageIcon(this.getClass().getResource("image/exit.png"));

    Launcher() {
        setLayout(new GridLayout(3, 1));
        startButton.setIcon(startIcon);
        startTitle.setIcon(startTitleImage);
        exitButton.setIcon(exitIcon);
        add(startTitle);
        add(startButton);
        add(exitButton);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here
                System.out.println("Image clicked!");
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here
                System.exit(0);
            }
        });
    }
}
