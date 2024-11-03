// class for handle background
package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

class HomePage extends JPanel {
    URL bgURL = this.getClass().getResource("image/background.png");
    Image backgroundImage = new ImageIcon(bgURL).getImage();


    HomePage() {
        add(new Launcher());
        runner.start();
        repaint();
    }

    Thread runner = new Thread(new Runnable() {
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