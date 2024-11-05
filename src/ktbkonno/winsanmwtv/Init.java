// made by Phongsiri Loedphongthai
// ID 6604062636453 KMUTNB
// Faculty of Applied Science, Department of Computer Science
// You may fork this code to do anything

// Deadly Railways
package ktbkonno.winsanmwtv;

import javax.swing.*;

public class Init extends JFrame {

    // to declare something
    static protected String gameVer = "dev-h"; // GAME VERSION
    static protected final char devMode = 'c'; // c = canary (dev), b = beta (tested dev), p = preview (alpha), r = release;
    static protected final int gameTime = 5; // game time (do -1 for debug)
    static protected final boolean isDebug = true;
    static protected final int gameLength = 300;
    static protected final int addScore = 1;
    static protected final int addTime = 5; // second
    static protected final int carSpeed = 5;
    static protected final int trainSpeed = 10;
    static protected final int hsrSpeed = 15;

    static JFrame display = new Init();
    static JFrame gameplay = new Gameplay();

    Init() { // main constructor
        add(new Launcher());
        switch(devMode) {
            case 'c':
                gameVer = gameVer+" (Canary Release, use with caution!)";
                break;
            case 'b':
                gameVer = gameVer+" (Beta Release, if you find any bug please report!)";
                break;
            case 'p':
                gameVer = gameVer+" (Release Preview)";
                break;
            default:
                break;
        }
        if (isDebug) gameVer = gameVer+" | Development Mode is turned ON";
    }

    public static void main(String[] args) { // void main runner and init
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Countdown.playStatus = false;
    }

    static public void startGameInit() {
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(false);
        gameplay.setSize(850, 530);
        gameplay.setTitle("Deadly Railways - In Game (Game Version: "+gameVer+")");
        gameplay.setLocationRelativeTo(null);
        gameplay.setVisible(true);
        GamePanel.player.setX(4);
        GamePanel.player.setScore(3);
        GamePanel.isDone = false;
        GamePanel.y = 0;
        GamePanel.x = 4;
        GamePanel.yLoc = GamePanel.player.getY(1);
        Countdown.usedSecond = 0; Countdown.usedMin = 0;
        Countdown.playStatus = true;
    }

    static public void onGameEnd() {
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher - Replay");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
        gameplay.setSize(850, 530);
        gameplay.setTitle("Deadly Railways - In Game (Game Version: "+gameVer+")");
        gameplay.setLocationRelativeTo(null);
        gameplay.setVisible(false);
        GamePanel.isDone = false;
        Countdown.playStatus = false;
    }
}