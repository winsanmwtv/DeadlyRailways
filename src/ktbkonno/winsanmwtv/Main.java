// made by Phongsiri Loedphongthai
// ID 6604062636453 KMUTNB
// Faculty of Applied Science, Department of Computer Science
// You may fork this code to do anything

// Deadly Railways
package ktbkonno.winsanmwtv;

import javax.swing.*;

public class Main extends JFrame {

    static public final String gameVer = "dev-1";

    static JFrame display = new Main();
    static JFrame gameplay = new Gameplay();

    Main() { // main constructor
        add(new HomePage());
    }

    public static void main(String[] args) { // void main runner and init
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }

    static public void startGameInit() {
        display.setVisible(false);
        gameplay.setSize(850, 530);
        gameplay.setTitle("Deadly Railways - In Game (version "+gameVer+")");
        gameplay.setLocationRelativeTo(null);
        gameplay.setVisible(true);
    }

    static public void onGameEnd() {
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
        gameplay.setVisible(false);
    }
}