package fortyfour.group.main;

import fortyfour.group.scene.scenes.MenuScene;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("ecosystem-simulator");

        window.getContentPane().add(new MenuScene());
        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);

    }
}
