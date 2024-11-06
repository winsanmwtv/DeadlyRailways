package ktbkonno.winsanmwtv;

public class PlayerLocation {
    private int x = 4;
    private int y = 4;
    private int[] xLoc = new int[9];
    private int[] yLoc = new int[5];
    static public int panelWidth;
    private int panelHeight;
    private boolean whileMove = false;
    private int loc;
    private int score = Init.score;

    PlayerLocation() {

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

    public void setHeight(int panelHeight) {
        this.panelHeight = panelHeight;
        int topMargin = 50; // Top margin for HP area
        int bottomMargin = 20; // Adjust bottom margin as needed

        // Calculate the total space available for the 5 elements
        int totalSpace = panelHeight - topMargin - bottomMargin;

        // Calculate the spacing between elements
        int spacing = totalSpace / 5;

        for (int i = 0; i < 5; i++) {
            yLoc[i] = topMargin + i * spacing;
        }
    }

    public int getY(int where) {
        switch (where) {
            case 1:
                return yLoc[4];
            case 2:
                return yLoc[3];
            case 3:
                return yLoc[2];
            case 4:
                return yLoc[1];
            case 5:
                return yLoc[0];
        }
        return 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void deductScore() {
        score += Init.deductScore;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentLocationX() {
        if (!whileMove) return xLoc[x] + 25;
        else return loc;
    }

    public void MoveLeft() {
        // System.out.println(x>0);
        if (x > 0) {
            whileMove = true;
            loc = xLoc[x];
            int newLoc = xLoc[x - 1];
            int t = newLoc - loc;
            for (int i = 0; i < t; i++) {
                loc += i;
            }
            whileMove = false;
            x--;
            // System.out.println(x);
        } else {
            // System.out.println("to edge");
            x = 8;
            // System.out.println(x);
        }
    }

    public void MoveRight() {
        // System.out.println(x<8);
        if (x < 8) {
            whileMove = true;
            loc = xLoc[x];
            int newLoc = xLoc[x + 1];
            int t = newLoc - loc;
            for (int i = 0; i < t; i++) {
                loc += i;
            }
            whileMove = false;
            x++;
            // System.out.println(x);
        } else {
            // System.out.println("to edge");
            x = 0;
            // System.out.println(x);
        }
    }

    public int getActualX(int x) {
        return xLoc[x] + 25;
    }

}
