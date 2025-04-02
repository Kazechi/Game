
package main;

import entity.Entity;
import entity.Player;
public class CollisionChecker {
    
    GamePanel gp;
    

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    
    public void checkTile(Entity entity){
        
        int entityLeftX = entity.worldX + entity.area.x;
        int entityRightX = entity.worldX + entity.area.x + entity.area.width;
        
        int entityTopY = entity.worldY + entity.area.y;
        int entityBottomY = entity.worldY + entity.area.y + entity.area.height;
        
        
        
        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;
        
        
        int tileNum1, tileNum2;
        
        // use a temporal direction when its being knockBack
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }
        
        switch(direction){
            
            case "up": 
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                entity.colliOn = true;
            }
                break;
                
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
               tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                entity.colliOn = true;
            }
                break;
                
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                entity.colliOn = true;
            }
                break;
                
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                entity.colliOn = true;
            }
                break;
        }
    }
        public int checkObj(Entity entity, boolean player){
         
            int index = 999;
            String direction = entity.direction;
            if(entity.knockBack == true){
                direction = entity.knockBackDirection;
            }
            for(int i = 0; i < gp.obj[1].length; i++){ //FIXED
                
                if(gp.obj[gp.currentMap][i] != null){//FIXED
                    
                    // entity solid position
                    entity.area.x = entity.worldX + entity.area.x;
                    entity.area.y = entity.worldY + entity.area.y;
                    
                    
                    // object solid position
                    gp.obj[gp.currentMap][i].area.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].area.x;//FIXED
                    gp.obj[gp.currentMap][i].area.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].area.y;//FIXED
                    
                    
                    // making the collision for all direction
                    // checking the collision between the object and the character
                    switch(direction){
                        
                        case "up": entity.area.y -= entity.speed;break;
                            
                        case "down": entity.area.y += entity.speed; break;
                            
                        case "left": entity.area.x -= entity.speed; break;
                            
                        case "right": entity.area.x += entity.speed;break;
                    }
                    if(entity.area.intersects(gp.obj[gp.currentMap][i].area)){//FIXED
                                if(gp.obj[gp.currentMap][i].colliOn == true){//FIXED
                                    entity.colliOn = true;
                                }
                                if(player == true){
                                    index = i;
                                }
                               
                            }
           
                
                    entity.area.x = entity.solidAreaX;
                    entity.area.y = entity.solidAreaY;
                    
                    gp.obj[gp.currentMap][i].area.x = gp.obj[gp.currentMap][i].solidAreaX;//FIXED
                    gp.obj[gp.currentMap][i].area.y = gp.obj[gp.currentMap][i].solidAreaY;//FIXED
                }
//                
        }
            return index;
        }

        //Collision checker
        //NPC or Monster
        public int checkEntity(Entity entity, Entity[][] target) {
            int index = 999;
            
            // use a temporal direction when its being knockBack
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }
            
            for(int i = 0; i < target[1].length; i++){ 
                
                if(target[gp.currentMap][i] != null){ //FIXED
                    
                    // entity solid position
                    entity.area.x = entity.worldX + entity.area.x;
                    entity.area.y = entity.worldY + entity.area.y;
                    
                    // object solid position
                    target[gp.currentMap][i].area.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].area.x;//FIXED
                    target[gp.currentMap][i].area.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].area.y;//FIXED
                    
                    
                    // making the collision for all direction
                    // checking the collision between the object and the character
                    switch(direction){
                        
                        case "up":
                            entity.area.y -= entity.speed;
                            break;
                            
                        case "down":
                            entity.area.y += entity.speed;
                            break;
                            
                        case "left":
                            entity.area.x -= entity.speed;
                            break;
                            
                        case "right":
                            entity.area.x += entity.speed;
                                break;
                            
                    }
                
                if(entity.area.intersects(target[gp.currentMap][i].area)) {//FIXED
                    if(target[gp.currentMap][i] != entity) {//FIXED
                    entity.colliOn = true;
                    index = i;
                }  
                }
                    entity.area.x = entity.solidAreaX;
                    entity.area.y = entity.solidAreaY;
                    
                    target[gp.currentMap][i].area.x = target[gp.currentMap][i].solidAreaX;//FIXED
                    target[gp.currentMap][i].area.y = target[gp.currentMap][i].solidAreaY;//FIXED
                }
//                
        }
            return index;
        }
        
        public boolean checkPlayer(Entity entity) {
            
            boolean contactPlayer = false;
            
             // entity solid position
                    entity.area.x = entity.worldX + entity.area.x;
                    entity.area.y = entity.worldY + entity.area.y;
                    
                    
                    // object solid position
                    gp.player.area.x = gp.player.worldX + gp.player.area.x;
                    gp.player.area.y = gp.player.worldY + gp.player.area.y;
                    
                    
                    // making the collision for all direction
                    // checking the collision between the object and the character
                    switch(entity.direction){
                        
                        case "up":
                            entity.area.y -= entity.speed;
                            break;
                            
                        case "down":
                            entity.area.y += entity.speed;
                            break;
                            
                        case "left":
                            entity.area.x -= entity.speed;
                            break;
                            
                        case "right":
                            entity.area.x += entity.speed;
                                break;
                            }
                    
                    
                    if(entity.area.intersects(gp.player.area)){
                                entity.colliOn = true;
                                contactPlayer = true;
                            }   
                    
                    entity.area.x = entity.solidAreaX;
                    entity.area.y = entity.solidAreaY;
                    
                    gp.player.area.x = gp.player.solidAreaX;
                    gp.player.area.y = gp.player.solidAreaY;
            return contactPlayer;
        }
        
}
