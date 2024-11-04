// made by Phongsiri Loedphongthai
// ID 6604062636453 KMUTNB
// Faculty of Applied Science, Department of Computer Science
// You may fork this code to do anything

// Deadly Railways
package ktbkonno.winsanmwtv;

import javax.swing.*;
import java.awt.*;

public class Init extends JFrame {

    static public final String gameVer = "dev-3c"; // GAME VERSION
    static public final int gameTime = 5; // game time
    static public final boolean isDebug = true;

    static JFrame display = new Init();
    static JFrame gameplay = new Gameplay();

    Init() { // main constructor
        add(new Launcher());
    }

    public static void main(String[] args) { // void main runner and init
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gameplay.setFocusable(false);
        // Countdown.countdown.interrupt();
    }

    static public void startGameInit() {
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(false);
        gameplay.setSize(850, 530);
        gameplay.setTitle("Deadly Railways - In Game (version "+gameVer+")");
        gameplay.setLocationRelativeTo(null);
        gameplay.setVisible(true);
        //gameplay.setFocusable(true);
        // Countdown.countdown.start();

    }

    static public void onGameEnd() {
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
        gameplay.setSize(850, 530);
        gameplay.setTitle("Deadly Railways - In Game (version "+gameVer+")");
        gameplay.setLocationRelativeTo(null);
        gameplay.setVisible(false);
        //gameplay.setFocusable(false);
        // Countdown.countdown.interrupt();
    }
}