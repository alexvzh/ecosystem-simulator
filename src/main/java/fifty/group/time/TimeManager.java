package fifty.group.time;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TimeManager {

    private int time;
    private int day;
    private int tickCounter;
    private String clockString;
    private final Font font;
    private int shadeValue;
    private int transitionShadeValue;
    private final List<Runnable> dayStartListeners;


    public TimeManager() {
        this.font = new Font("Monospaced", Font.PLAIN, 30);
        this.dayStartListeners = new ArrayList<>();
    }

    public void update() {
        updateTime();
        updateClockString();
        updateShade();
    }

    public void draw(Graphics2D g2d) {
        drawShade(g2d);
        drawClock(g2d);
        drawDay(g2d);
    }

    public void onDayStart(Runnable listener) {
        dayStartListeners.add(listener);
    }

    private void updateTime() {
        // 1 real life second = 10 in-game minutes
        if (tickCounter % 12 == 0) {
            time++;
        }
    
        if (time >= 950) {
            endDay();
        }

        tickCounter++;
    }

    private void updateClockString() {
        int hours = 8 + time / 60;
        int minutes = time % 60;

        String hourString = String.format("%02d", hours);
        String minuteString = String.format("%02d", minutes);
        clockString = hourString + ":" + minuteString;
    }

    private void updateShade() {
        if (time - 400 < 0) return;
        shadeValue = (time - 400) / 4;
    }

    public void drawClock(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString(clockString, 150, 30);
    }

    public void drawDay(Graphics2D g2d) {
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Day " + day, 20, 30);
    }

    public void drawShade(Graphics2D g2d) {
        int finalShadeValue = shadeValue + transitionShadeValue;
        if (finalShadeValue > 255) finalShadeValue = 255;
        g2d.setColor(new Color(0, 0, 0, finalShadeValue));
        g2d.fillRect(0, 0, 1400, 800);
    }

    private void startDay() {
        time = 0;
        tickCounter = 0;
        shadeValue = 0;
        transitionShadeValue = 0;
        day++;

        for (Runnable listener : dayStartListeners) {
            listener.run();
        }
    }

    private void endDay() {
        if (time < 960) {
             transitionShadeValue++;
        } else
            startDay();
    }

    public void init() {
        this.time = 850;
        this.day = 1;
        this.tickCounter = 0;
        this.clockString = "";
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
