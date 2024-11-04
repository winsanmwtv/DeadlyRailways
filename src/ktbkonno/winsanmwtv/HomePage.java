// class for handle background
package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomePage extends JPanel {
    private final URL bgURL = this.getClass().getResource("image/background.png");
    private final Image backgroundImage = new ImageIcon(bgURL).getImage();


    HomePage() {
        add(new Launcher());
        runner.start();
        repaint();
    }

    public Thread runner = new Thread(new Runnable() {
        public void run() {
            while(true){
                try {
                    runner.sleep(10);
                } catch(InterruptedException e) {
                    repaint();
                }
            }
        }
    });

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xSize = getWidth();
        int ySize = getHeight();
        g.drawImage(backgroundImage, 0, 0, xSize, ySize, this);
    }
}