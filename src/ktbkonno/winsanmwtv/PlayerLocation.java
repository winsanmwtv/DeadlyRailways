package ktbkonno.winsanmwtv;

public class PlayerLocation {
    private int x = 4;
    private int[] xLoc = new int[9];
    private int panelWidth;
    private boolean whileMove = false;

    PlayerLocation() {
        /**/
    }

    public void setWidth(int panelWidth) {
        this.panelWidth = panelWidth;
        int margin = 100; // Adjust the margin as needed
        int spacing = (panelWidth - 2 * margin) / 9; // Adjust spacing as needed
        //System.out.print("Map: ");
        for (int i = 0; i < 9; i++) {
            xLoc[i] = margin + i * spacing;
            //System.out.print(xLoc[i]+" ");
        }
        //System.out.println();
    }

    public int getCurrentLocationX() {
        if (!whileMove) return xLoc[x];
        else {
            int loc = xLoc[x];
            whileMove = true;
            int locMoved = xLoc[x+1];
            for (int i = 0; i < locMoved-loc; i++) loc += i;
            return loc;
        }
    }

    public void MoveLeft() {
        if (x > 1) x--;
    }

    public void MoveRight() {
        if (x < 9) x++;
    }
}
