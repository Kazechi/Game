/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.GamePanel;

/**
 *
 * @author admin
 */
public class Particle extends Entity{
    
    Entity generator;
    Color color;
    int size;
    double xd;
    double yd;
    public Particle(GamePanel gp, Entity generator, Color color, 
            int size, int speed,int maxLife, double xd, double yd) {
        
        super(gp);
        
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;
        
        
        life = maxLife;
        int offset = (gp.tileSize / 2) - (size / 2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }
 
    
    public void update(){
        
        life--;
        if(life < maxLife/ 3){
            yd++;
        }
        
        worldX += xd * speed;
        worldY += yd * speed;
        
        if(life == 0){
            alive = false;
        }
    }
    
    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        
        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
        
    }
    
}
