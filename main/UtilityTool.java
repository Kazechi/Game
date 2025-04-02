
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    public BufferedImage imgScale(BufferedImage orig, int width, int height){
        BufferedImage imgScale = new BufferedImage(width, height, orig.getType());
        
        Graphics2D g2 = imgScale.createGraphics();
        g2.drawImage(orig, 0, 0, width, height, null);
        g2.dispose();
        
        return imgScale;
        
    }
    
}
