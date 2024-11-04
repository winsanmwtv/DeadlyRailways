package ktbkonno.winsanmwtv;

public class PlayerLocation {
    private int x = 4;
    private int[] xLoc = new int[9];
    private int panelWidth;
    private boolean whileMove = false;
    private int loc;

    PlayerLocation() {
        /**/
    }

    public void setWidth(int panelWidth) {
        this.panelWidth = panelWidth;
        int margin = 20; // Adjust the margin as needed
        int spacing = (panelWidth - 2 * margin) / 9; // Adjust spacing as needed
        //System.out.print("Map: ");
        for (int i = 0; i < 9; i++) {
            xLoc[i] = margin + (i) * spacing;
            //System.out.print(xLoc[i]+" ");
        }
        //System.out.println();
    }

    public int getCurrentLocationX() {
        if (!whileMove) return xLoc[x];
        else return loc;
    }

    public void MoveLeft() {
        System.out.println(x>0);
        if (x > 0) {
            whileMove = true;
            loc = xLoc[x];
            int newLoc = xLoc[x-1];
            int t = newLoc - loc;
            for (int i = 0; i < t; i++) {
                loc += i;
            }
            whileMove = false;
            x--;
            System.out.println(x);
        }
    }

    public void MoveRight() {
        System.out.println(x<8);
        if (x < 8) {
            whileMove = true;
            loc = xLoc[x];
            int newLoc = xLoc[x+1];
            int t = newLoc - loc;
            for (int i = 0; i < t; i++) {
                loc += i;
            }
            whileMove = false;
            x++;
            System.out.println(x);
        }
    }
}
