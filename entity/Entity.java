package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
     GamePanel gp;
    public String name;
    public BufferedImage img, img2, img3;//detect the collision between the object and the character
    public boolean colliOn = false;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage atkUp1, atkUp2, atkDown1, atkDown2, atkRight1, atkRight2, atkLeft1, atkLeft2;
    public int charNum = 1;
    public Rectangle area = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public String dialogues[][] = new String[30][30];
    public boolean invincible = false;
    public Entity attacker;
    public boolean temp = false;
    
    
    
    //TYPE
     public int type; // 0 = player, 1 = npc, 2 = monster
     public final int type_player = 0;
     public final int type_npc = 1;
     public final int type_monster = 2;
     public final int type_sword = 3;
     public final int type_sword2 = 4;
     public final int type_shield = 5;  
     public final int type_consumable = 6;
     public final int type_pickupOnly = 7;
     public final int type_obstacle = 8;
     public final int type_light = 9;
     
     //STATE
     public String direction = "down";
     public int solidAreaX, solidAreaY;
     public int worldX, worldY;
     public int dialogueSet = 0;
     public int dialogueIndex = 0;
     public boolean attacking = false;
     public boolean alive = true;
     public boolean dying = false;
     public boolean hpBarOn = false;
     public boolean onPath = false;
     public boolean knockBack = false;
     public String knockBackDirection;
     public boolean inRage = false;
     public boolean sleep = false;
     public boolean drawing = true;
     
     
    //CHARACTER STATUS
    public int defaultSpeed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int speed;
    public int level;
    public int strength;
    public int attack;
    public int defense;
    public int dexterity;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int maxCoin;
    public Entity currentSword;
    public Entity currentShield;
    public Entity currentSpear;
    public Entity currentLight;
    public Projectile projectile; //variable name when calling the projectile class
    public boolean boss;
    
    
    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    public int value;
    public int attackValue;
    public int rangeValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public boolean stackable = false;
    public int amount = 1;
    public int durability = 100;
    public int lightRadius;
    
    
    //COUNTER
    public int invincibleCounter = 0;
    public int charCounter = 0;
    public int actionCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    int knockBackCounter = 0;
	public int knockBackPower = 0;

    
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public int getScreenX() {
    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
    	return screenX;
    }
    public int getScreenY() {
    	int screenY = worldY - gp.player.worldY + gp.player.screenY;
    	return screenY;
    }
    public int getLeftX() {
    	return worldX + area.x;
    }
    
    public int getRightX() {
    	return worldX + area.x + area.width;
    }
    
    public int getTopY() {
    	return worldY + area.y;
    }
    
    public int getBottomY() {
    	return worldY + area.y + area.height;
    }
    
    public int getCol() {
    	return(worldX + area.x)/gp.tileSize;
    }
    
    public int getRow() {
    	return (worldY + area.y)/gp.tileSize;
    }
    public int getCenterX() {
    	int centerX = worldX + left1.getWidth()/2;
    	return centerX;
    }
    public int getCenterY() {
    	int centerY = worldY + up1.getHeight()/2;
    	return centerY;
    }
    public int getXdistance(Entity target) {
    	int xDistance = Math.abs(getCenterX() - target.getCenterX());
    	
    	return xDistance;
    }
    public int getYdistance(Entity target) {
    	int yDistance = Math.abs(getCenterY() - target.getCenterY());
    	
    	return yDistance;
    }
    
    public int getTileDistance(Entity target) {
    	int tileDistance = (getXdistance(target) + getYdistance(target) / gp.tileSize);
    	return tileDistance;
    }
    
    public int getGoalCol(Entity target) {
    	int goalCol = (target.worldX + target.area.x) / gp.tileSize;
    	return goalCol;
    }
    
    public int getGoalRow(Entity target) {
    	int goalRow = (target.worldY + target.area.y) / gp.tileSize;
    	return goalRow;
    }
    public void resetCounter() {
    	invincibleCounter = 0;
        charCounter = 0;
        actionCounter = 0;
         dyingCounter = 0;
         hpBarCounter = 0;
         shotAvailableCounter = 0;
         knockBackCounter = 0;
    	 knockBackPower = 0;
    }
    public void speak(){}
    public void facePlayer() {
    	switch(gp.player.direction) {
        case "up" : direction = "down"; break;
         case "down":direction = "up";break;
         	case "left": direction = "right";break;
         		case "right":direction = "left";break;
    	}
    }
    
    public void startDialogue(Entity entity, int setNum) {
    	
    	gp.gameState = gp.DIALOGUE;
    	gp.ui.npc = entity;
    	dialogueSet = setNum;
    	
    }
    public void interact() {
    	
    }
    public boolean use(Entity entity) {
        return false;
    }
    public void setAction() {
    }
    public void checkCollision(){
        colliOn = false;
        gp.colliCheck.checkTile(this);
        gp.colliCheck.checkObj(this, false);
        gp.colliCheck.checkEntity(this, gp.npc);
        gp.colliCheck.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.colliCheck.checkPlayer(this);
        
        if(this.type == type_monster && contactPlayer == true) {
//        	gp.playSE(6);
            damagePlayer(attack);
        }
    }
    public void update() {
    	
    	if(sleep == false) {
    		
    		if(knockBack == true) {
//                gp.playSE(6);
        		checkCollision();
        		if(colliOn == true) {
        			knockBackCounter = 0;
        			knockBack = false;
        			speed = defaultSpeed;
        		}
        		else if(colliOn == false) {
        			switch(knockBackDirection) {
        			case "up": worldY -= speed; break;
        			case "down": worldY += speed;break;
        			case "left": worldX -= speed; break;
        			case "right": worldX += speed; break;
        			}
        		}
        		
        		knockBackCounter++;
                    
        		if(knockBackCounter == 10) {
        			knockBackCounter = 0;
        			knockBack = false;
        			speed = defaultSpeed;
        		}
        		
        		
        	}
            else if(attacking == true){
                attacking();
            }
            else {
        		 setAction();
        	        checkCollision();
        	        // if collision is false, NPC can move
        	        if(colliOn == false){
        	            switch(direction){
        	                case "up":worldY -= speed; break;
        	                case "down":worldY += speed;break;
        	                case "left":worldX -= speed; break;
        	                case "right":worldX += speed;break;
                        }
        	        }
                    // make the character move smoothly
            charCounter++;
            if(charCounter > 24){
                if(charNum == 1){
                    charNum = 2;
                }
                else if(charNum == 2){
                    charNum = 1;
                }
                charCounter = 0;
            }
        	}
        	
           
                
            
            if(invincible == true) {
                invincibleCounter++;
                if(invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
                    if(shotAvailableCounter < 30) {
                shotAvailableCounter++;
            }
    	}
    	
    	
        
    }
    public void checkDrop() {
        
    }
    public void dropItem(Entity droppedItem) {
        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; //worldX OF THE DEAD MONSTER
                gp.obj[gp.currentMap][i].worldY = worldY; //worldY OF THE DEAD MONSTER
                break;
            }
        }
    }
    public Color getParticleColor() {
        Color color = null;
        return color;
    }
    public int getParticleSize() {
        int size = 0; //COMPUTE AS PIXEL
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();
        
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public BufferedImage setUp(String imagePath, int width, int height){
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage img = null;
        
        try{
            
            img = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
            img = uTool.imgScale(img, width, height);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal){
        
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);
        
        
        switch(direction){
            case "up": if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){ 
                    targetInRange = true;
            }
            break;
            case "down": if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){ 
                    targetInRange = true;
            }
                break;
            case "left":  if(gp.player.getCenterX() < getCenterX()  && xDis < straight && yDis < horizontal){
                targetInRange = true;
            }
                break;
            case "right":  if(gp.player.getCenterX() > getCenterX()  && xDis < straight && yDis < horizontal){
                targetInRange = true;
            }
                break;
        }
        
        if(targetInRange == true){
            // check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                charNum = 1;
                charCounter = 0;
                shotAvailableCounter = 0;
            }
            
            
        }
        
    }
    public void checkShootOrNot(int rate, int shotInterval) {
    	
    	int i = new Random().nextInt(rate);
		if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
			projectile.set(worldX, worldY, direction, true, this);
			
			
//			CHECK VACANCY
			for(int ii = 0; ii < gp.projectile[1].length; ii++) {
				if(gp.projectile[gp.currentMap][ii] == null) {
					gp.projectile[gp.currentMap][ii] = projectile;
					break;
				}
			}
			shotAvailableCounter = 0;
		}
    	
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
    	
    	if(getTileDistance(target) < distance) {
    		int i = new Random().nextInt(rate);
    		if(i == 0) {
    			onPath = true;
    		}
    	}
    	
    	
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
    	
    	if(getTileDistance(target) > distance) {
    		int i = new Random().nextInt(rate);
    		if(i == 0) {
    			onPath = false;
    		}
    	}
    	
    	
    }
    public void getRandomDirection(int interval) {
    	actionCounter++;
        if(actionCounter > interval) {
            Random random = new Random();// get a random number
        
        int i = random.nextInt(100)+1;// randomly pick a number from 1 - 100
        
        if(i <= 25) { direction = "up"; }
        if(i > 25 && i <= 50) {direction = "down"; }
        if(i > 50 && i <= 75) { direction = "left";}
        if(i > 75 && i <= 100) {direction = "right"; }
        
        actionCounter = 0;
        
        }
    }

    public void moveTowardPlayer(int interval) {
    	actionCounter++;
        if(actionCounter > interval) {
        	if(getXdistance(gp.player) > getYdistance(gp.player)) {
        		if(gp.player.getCenterX() < getCenterX()) {
        			direction = "left";
        		}
        		else {
        			direction = "right";
        		}
        	}
        	else if(getXdistance(gp.player) < getYdistance(gp.player)) {
        		if(gp.player.getCenterY() < getCenterY()) {
        			direction = "up";
        		}
        		else {
        			direction = "down";
        		}
        	}
        	actionCounter = 0;
        }
    }
    public void attacking() {
        charCounter++;
        
        if(charCounter <= 5) {
            charNum = 1;
        }
        if(charCounter > 5 && charNum <= 25) {
            charNum = 2;
            
            
            // SAVE THE CURRENT worldX, worldY, area
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = area.width;
            int solidAreaHeight = area.height;
            
            // adjust player worldX/Y for attack area
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "right": worldX += attackArea.width; break;
                case "left": worldX -= attackArea.width; break;
            }
            
            // attack area become area
            area.width = attackArea.width;
            area.height = attackArea.height;
            
            if(type == type_monster){
                if(gp.colliCheck.checkPlayer(this) == true){
                    damagePlayer(attack);
                }
            }
            else{ //PLAYER
                // check collision between monster and the sword
            int monsterIndex = gp.colliCheck.checkEntity(this, gp.monster);
            gp.player.damageMonster(monsterIndex, this, attack, currentSword.knockBackPower);
            
            int projectileIndex = gp.colliCheck.checkEntity(this, gp.projectile);
             gp.player.damageProjectile(projectileIndex);
            }
            
            
            
            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            area.width = solidAreaWidth;
            area.height = solidAreaHeight;
            
        }
        if(charCounter > 25) {
            charNum = 1;
            charCounter= 0;
            attacking = false;
            
            
        }
        
    }
    public void damagePlayer(int attack) {
        if(gp.player.invincible == false) {
                //Give damage to player
                int damage = attack - gp.player.defense;
                
                if(damage < 0) {
                	damage = 0;
                }
                
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
    }
    
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
        
    }
    public boolean inCamera() {
    	boolean inCamera = false;
    	
    	if(worldX + gp.tileSize*5 > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
                worldY + gp.tileSize*5 > gp.player.worldY - gp.player.screenY && 
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
    		
    		inCamera = true;
    	}
    	return inCamera;
    	
    }
    public void draw(Graphics2D g2) {
        BufferedImage img = null;
            
            if(inCamera() == true){
                
                
        int tempScreenX = getScreenX();
        int tempScreenY = getScreenY();
        switch(direction){
            case "up":
                if(attacking == false) {
                    if(charNum == 1){img = up1;}
                    if(charNum == 2){img = up2;}
                }
                if(attacking == true) {
                    tempScreenY = getScreenY() - up1.getHeight();
                    if(charNum == 1){img = atkUp1;}
                    if(charNum == 2){img = atkUp2;}
                }
                
                break;
                
            case "down":
                if(attacking == false) {
                    if(charNum == 1){img = down1;}
                    if(charNum == 2){img = down2;}
                }
                
                if(attacking == true) {
                    if(charNum == 1){img = atkDown1;}
                    if(charNum == 2){img = atkDown2;}
                }
                
                break;
            case "right":
                if(attacking == false) {
                    if(charNum == 1){img = right1; }
                if(charNum == 2){img = right2;}
                }
                
                if(attacking == true) {
                    if(charNum == 1){img = atkRight1; }
                if(charNum == 2){img = atkRight2;}
                }
                
                break;
            case "left":
                if(attacking == false) {
                    if(charNum == 1){img = left1;}
                if(charNum == 2){img = left2;}
                }
                
                if(attacking == true) {
                    tempScreenX = getScreenX() - left1.getWidth();
                    if(charNum == 1){img = atkLeft1;}
                if(charNum == 2){img = atkLeft2;}
                }
                break;
        }
        if(invincible == true) {
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);

        }
        if(dying == true) {
            dyingAnimation(g2);
        }
        
                 g2.drawImage(img, tempScreenX, tempScreenY, null);
                 changeAlpha(g2, 1f);
            }
        
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        
        int counter = 5;//default value for dying counter
        if(dyingCounter <= counter) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > counter && dyingCounter <= counter*2) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            changeAlpha(g2, 1f);
        }
        
        if(dyingCounter > counter*2 && dyingCounter <= counter*3) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > counter*3 && dyingCounter <= counter*4) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > counter*4 && dyingCounter <= counter*5) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > counter*5 && dyingCounter <= counter*6) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > counter*6 && dyingCounter <= counter*7) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > counter*7 && dyingCounter <= counter*8) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            changeAlpha(g2, 1f);
        }
        
        if(dyingCounter > counter*8) {
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
        
    }
    public void searchPath(int goalCol, int goalRow){

        int startCol = (worldX + area.x)/gp.tileSize;
        int startRow = (worldY + area.y)/gp.tileSize;
        
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        
        if(gp.pFinder.search() == true){
            
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
            
            int enLeftX = worldX + area.x;
            int enRightX = worldX + area.x + area.width;
            int enTopY = worldY + area.y;
            int enBottomY = worldY + area.y + area.height;
            
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize){
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextY){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                direction = "up";
                checkCollision();
                if(colliOn == true){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                direction = "up";
                checkCollision();
                if(colliOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                direction = "down";
                checkCollision();
                if(colliOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                direction = "down";
                checkCollision();
                if(colliOn == true){
                    direction = "right";
                }
            }
            
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            
            if(nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }
            
        }
    
}
    public int getDetected(Entity user, Entity target[][], String targetName) {
    	
    	int index = 999;
    	
    	//CHECKING THE SURROUNDING OBJECTS
    	int nextWorldX = user.getLeftX();
    	int nextWorldY = user.getTopY();
    	
    	
    	switch(user.direction) {
    	case "up": nextWorldY = user.getTopY() - 1; break;
    	case "down": nextWorldY = user.getBottomY() + 1; break;
    	case "left": nextWorldX = user.getLeftX() - 1; break;
    	case "right": nextWorldX = user.getRightX() + 1; break;
    	}
    	
    	int col = nextWorldX / gp.tileSize;
    	int row= nextWorldY / gp.tileSize;
    	
    	for(int i = 0; i < target[1].length; i++) {
    		if(target[gp.currentMap][i] != null) {
    			if(target[gp.currentMap][i].getCol() == col &&
    					target[gp.currentMap][i].getRow() == row &&
    					target[gp.currentMap][i].name.equals(targetName)) {
    				
    				index = i;
    				break;
    				
    			}
    		}
    	}
    	return index;
    	
    }
}  






