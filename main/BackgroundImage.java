
package main;
import javax.swing.*;
import java.awt.*;
public class BackgroundImage extends JFrame{
    private Image backgroundImage;
    public BackgroundImage(){
        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("/LogoBackgroundImages/Background.png");
    }
    @Override
    public void paint(Graphics g) {
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Call the superclass's paint method
        super.paint(g);
    }
}
