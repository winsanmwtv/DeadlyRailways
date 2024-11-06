// made by Phongsiri Loedphongthai
// ID 6604062636453 KMUTNB
// Faculty of Applied Science, Department of Computer Science
// You may fork this code to do anything

// Deadly Railways
package ktbkonno.winsanmwtv;

import javax.swing.*;

public class Init extends JFrame {
    static protected String gameVer = "dev-l"; // GAME VERSION
    static protected final char devMode = 'c'; // Game mode
    static protected int gameTime = 5; // Game time (use -1 for debug)
    static protected final boolean isDebug = true;
    static protected int gameLength = 150+6;
    static protected final int addScore = 1; //HP
    static protected final int deductScore = -1; //HP
    static protected final int addTime = 10; // Additional time in seconds
    static protected final int carSpeed = 4;
    static protected final int trainSpeed = 8;
    static protected final int hsrSpeed = 19;
    static protected int score = 3; // Default HP
    static protected final int mapEdge = 2000+6; // max map load
    static public boolean isThisRunning = true;

    static JFrame display = new Init();  // Launcher frame
    static JFrame gameplay;  // Game frame

    Init() { // main constructor
        add(new Launcher());
        switch(devMode) {
            case 'c':
                gameVer += " (Canary Release, use with caution!)";
                break;
            case 'b':
                gameVer += " (Beta Release, if you find any bug please report!)";
                break;
            case 'p':
                gameVer += " (Release Preview)";
                break;
            default:
                break;
        }
        if (isDebug) gameVer += " | Development Mode is turned ON";
    }

    public static void main(String[] args) { // main method and initialization
        display.setSize(850, 530);
        display.setTitle("Deadly Railways Launcher");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static public void startGameInit() {
        display.setVisible(false);  // Hide the launcher frame

        gameplay = new Gameplay();  // Initialize the gameplay frame
        gameplay.setSize(850, 530);
        gameplay.setTitle("Deadly Railways - In Game (Game Version: " + gameVer + ")");
        gameplay.setLocationRelativeTo(null);
        gameplay.setVisible(true);
        gameplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize game settings
        GamePanel.player.setX(4);
        GamePanel.player.setScore(Init.score);
        GamePanel.isDone = false;
        GamePanel.y = 0;
        GamePanel.x = 4;
        GamePanel.yLoc = GamePanel.player.getY(1);

        Countdown.usedSecond = 0;
        Countdown.usedMin = 0;
        Countdown countdown = new Countdown();

        isThisRunning = false;  // Set to indicate game is running
    }

    static public void onGameEnd() {
        if (isThisRunning) return;
        isThisRunning = true;

      gameplay.dispose();  // Close and dispose of the game frame

        Launcher.isRunning = false;
        display.setVisible(true);  // Show the launcher frame again
        GamePanel.isDone = false;  // Reset game state if necessary
        Countdown.playStatus = false;
        GamePanel.isRun = false;
    }
}
