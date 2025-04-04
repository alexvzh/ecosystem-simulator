package fifty.group.main;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import fifty.group.scene.SceneID;
import fifty.group.scene.SceneManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        FlatMacDarkLaf.setup();

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("ecosystem-simulator");

        SceneManager.getInstance().initialize(window);

        SceneManager.getInstance().setScene(SceneID.MENU);
        SceneManager.getInstance().start();

    }
}
