// made by Phongsiri Loedphongthai
// ID 6604062636453 KMUTNB
// Faculty of Applied Science, Department of Computer Science
// You may fork this code to do anything

// Deadly Railways
package ktbkonno.winsanmwtv;

import javax.swing.*;

public class Main extends JFrame {

    Main() { // main constructor
        add(new HomePage());
    }

    public static void main(String[] args) { // void main runner and init
        Main display = new Main();
        display.setSize(850, 530);
        display.setTitle("Deadly Railways");
        display.setLocationRelativeTo(null);
        display.setVisible(true);
    }
}