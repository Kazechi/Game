
package entity;

import main.GamePanel;

public class Projectile extends Entity{
    
    Entity user;
    
    public Projectile(GamePanel gp) {
        super(gp);
    }
    
    
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
        
    }
    
    public void update() {
        if(user == gp.player) {
            int monsterIndex = gp.colliCheck.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, this, attack*(gp.player.level/1), knockBackPower);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false;
            }
        }
        if(user != gp.player) {
            boolean contactPlayer = gp.colliCheck.checkPlayer(this);
            if(gp.player.invincible == false && contactPlayer == true){
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
        }
        
        
        switch(direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "right": worldX += speed; break;
            case "left": worldX -= speed; break;
        }
        
        life--;
        if(life <= 0) {
            alive = false;
        }
        
        charCounter++;
        if(charCounter > 12) {
            if(charNum == 1) {
                charNum = 2;
            }
        }
    }
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user){}
    
}
