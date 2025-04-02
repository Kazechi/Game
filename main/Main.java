package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
public class Main {
public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JaveRealms: The Java Chronicles");
        new Main().setIcon();
        
        GamePanel gamePan = new GamePanel();
        window.add(gamePan);
        gamePan.config.loadConfig();
        if(gamePan.fullScreenOn == true) {
            window.setUndecorated(true);
        }
        window.pack();
        
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePan.setUpGame();
        gamePan.gameStartThread();

    
    }
    public void setIcon() {
    	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("LogoBackgroundImages/LOGO.png"));
    	window.setIconImage(icon.getImage());
    }
    public void setBackgroundImage(){
        ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("LogoBackgroundImages/Background.png"));
    Image image = backgroundImage.getImage();
    
    // Create a custom JPanel to set the background image
    JPanel backgroundPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    };
    
    // Set the custom JPanel as the content pane of the window
    window.setContentPane(backgroundPanel);
    }
}
//colliOn - For collision to set as true

// area = for the rectangle that will create the collision

// getItem - when the character get the item

// super(gp) - to instatiate to all class the following GamePanel