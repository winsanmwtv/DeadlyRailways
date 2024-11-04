package ktbkonno.winsanmwtv;

public class Countdown {
    static public int second;
    static public int minute;

    public static boolean isNotHalt = false;

    Countdown() {
        //countdown.start();
    }

    static void setHaltStatus(boolean halt) {
        isNotHalt = halt;
    }

    static String getSecond() {
        if (second < 10) return "0"+second;
        return ""+second;
    }

    static int getMinute() {
        return minute;
    }

    public static Thread countdown = new Thread(new Runnable() {
        public void run() {
            second = 0;
            minute = Init.gameTime;
            while(true){
                try {
                    countdown.sleep(1000);
                } catch(InterruptedException e) {

                }
                if (second > 0) {
                    second--;
                    // System.out.println("Timer: "+minute+":"+getSecond());
                }
                else if (second == 0 && minute > 0) {
                    second = 59;
                    minute--;
                    // System.out.println("Timer: "+minute+":"+getSecond());
                }
                else if (minute == 0 && second == 0) {
                    // break;
                    System.out.println("TIME OUT");
                }

            }
        }
    });
}
