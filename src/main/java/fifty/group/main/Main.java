package fifty.group.main;

import fifty.group.scene.SceneID;
import fifty.group.scene.SceneManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("ecosystem-simulator");

        window.getContentPane().add(SceneManager.getInstance().getSceneByID(SceneID.MENU));
        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        SceneManager.getInstance().setScene(SceneID.SIMULATION);
        SceneManager.getInstance().start();

    }
}
