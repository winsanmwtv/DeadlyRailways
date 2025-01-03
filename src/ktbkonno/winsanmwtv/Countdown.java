package ktbkonno.winsanmwtv;

public class Countdown {
    static public int second;
    static public int minute;

    static public int usedMin = 0;
    static public int usedSecond = 0;


    public static boolean isNotHalt = false;
    static public boolean playStatus = false;

    public static boolean isNotStart = false;

    static public boolean isRunning = false;

    Countdown() {

        second = 0;
        minute = Init.gameTime;
        if (!isNotStart) {
            countdown.start();
            isNotStart = true;
        }
    }

    static public void deductTime(int time) {
        if (second - time >= 0) {
            second -= time;
        } else {
            if (minute > 0) { // Only decrease minute if it's greater than 0
                minute--;
                second = 60 - (time - second);
            } else {
                // If there are no more minutes left, set both minute and second to zero
                second = 0;
            }
        }
    }


    static void setHaltStatus(boolean halt) {
        isNotHalt = halt;
    }

    static String getSecond() {
        if (second < 10) return "0" + second;
        return "" + second;
    }

    static int getMinute() {
        return minute;
    }

    static public void addTime(int time) {
        if (second + time < 60) second += time;
        else {
            minute++;
            second = (second + time) - 60;
        }
    }

    static public String getTotalTime() {
        if (usedSecond < 10) return usedMin + ":0" + usedSecond;
        return usedMin + ":" + usedSecond;
    }

    public Thread countdown = new Thread(new Runnable() {
        public void run() {
            playStatus = true;
            if (true) {
                while (true) {
                    isRunning = true;
                    // System.out.println(playStatus);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    if (second > 0) {
                        second--;
                        // System.out.println("Timer: "+minute+":"+getSecond());
                    } else if (second == 0 && minute > 0) {
                        second = 59;
                        minute--;
                        // System.out.println("Timer: "+minute+":"+getSecond());
                    } else if (minute == 0 && second == 0) {
                        // playStatus = false;
                        // break;
                        // System.out.println("TIME OUT");
                    }
                    if (!GamePanel.isDone) {
                        if (usedSecond < 60) {
                            usedSecond++;
                        } else if (usedSecond == 60) {
                            usedSecond = 0;
                            usedMin++;
                        }
                    }
                    // System.out.println(minute+" "+second);

                }
            }
            playStatus = false;
        }
    });
}
