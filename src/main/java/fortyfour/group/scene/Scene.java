package fortyfour.group.scene;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel implements Runnable {

    Thread thread;
    boolean running;

    public Scene() {

        this.setPreferredSize(new Dimension(640, 640));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);

        startThread();

    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2d);

    @Override
    public void run() {
        final int TARGET_FPS = 60; // Frames per second
        final int TARGET_UPS = 60; // Updates per second
        final double TIME_PER_UPDATE = 1_000_000_000.0 / TARGET_UPS; // Nanoseconds per update
        final double TIME_PER_FRAME = 1_000_000_000.0 / TARGET_FPS; // Nanoseconds per frame

        long lastUpdateTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();

        double delta = 0;
        int frames = 0;
        int updates = 0;

        long fpsTimer = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastUpdateTime) / TIME_PER_UPDATE;
            lastUpdateTime = currentTime;

            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }

            if (currentTime - lastRenderTime >= TIME_PER_FRAME) {
                repaint();
                frames++;
                lastRenderTime = currentTime;
            }

            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                fpsTimer += 1000;
                frames = 0;
                updates = 0;
            }

            // Save CPU...
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
