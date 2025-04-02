
package main;

import data.Progress;
import entity.Entity;


public class Event {
    
    GamePanel gp;
    EventRect eventRect[][][];
    int eventRectX, eventRectY;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public int tempMap, tempCol, tempRow;
    public Entity npc;
    Entity eventMaster;
    
    public Event(GamePanel gp) {
        this.gp = gp;
        eventMaster = new Entity(gp);
        
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        
        
        
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
        eventRect[map][col][row].y = 23;
        eventRect[map][col][row].width = 2;
        eventRect[map][col][row].height = 2;
        
        eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
        eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
        
        col++;
        if(col == gp.maxWorldCol){
            col = 0;
            row++;
            
            if(row == gp.maxWorldRow){
                row = 0;
                map++;
                }
            }   
        }
        setDialogue();
    }
    public void setDialogue() {
    	//LOCATION TELLER
    	eventMaster.dialogues[0][0] = "WELCOME TO THE "
                + "\nLAND OF PROGRAMMING "
                + "\nPress [ENTER] to continue the game";
    	eventMaster.dialogues[0][1] = "The key to talk to npc's is [T]";
    	eventMaster.dialogues[1][0] = "Code Oasis";
    	eventMaster.dialogues[2][0] = "Hack Ville";
    	
    	//HEALING EVENT
    	eventMaster.dialogues[3][0] = "You have drink water, \nYour HP and Mana have been restored.";
    	
    	//BOSS LINES
    	eventMaster.dialogues[4][0] = "Welcome you foolish traveler";
    	eventMaster.dialogues[4][1] = "No one can steal my Treasure!";
    	eventMaster.dialogues[4][2] = "Prepare yourself";
    	eventMaster.dialogues[4][3] = "You faith is in my hands";
    	eventMaster.dialogues[4][4] = "You death is inevitable the moment you enter my chamber";
    }
    public void checkEvent() {
        // Check if the player is 1 tile away from the event
        int distanceX = Math.abs(gp.player.worldX - previousEventX);
        int distanceY = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(distanceX, distanceY);
        if(distance > gp.tileSize) {
            canTouchEvent = true;
            
            if(canTouchEvent == true) {
            	 if(hit(0, 29, 42, "any")== true) {locationTeller1(gp.DIALOGUE);}//STARTING POINT
                else if(hit(0, 24, 12, "any") == true){locationTeller2(gp.DIALOGUE);}//HIGH REALM
                else if(hit(0, 41, 9, "any") == true){locationTeller2(gp.DIALOGUE);}//HIGH REALM
                else if(hit(0, 14, 35, "any")== true) {locationTeller3(gp.DIALOGUE);}//FORSAKEN REALM
            }
            
        }
        
        if(canTouchEvent == true) {

        if(hit(0, 21, 41, "up") == true) {healing(gp.DIALOGUE);}
        
        
      //teleport to the SHOP / OTHER HOUSES
        else if(hit(0, 30, 13, "down") == true){teleport(1, 22, 33, gp.indoor);}
        
        //teleport back to the main world
        else if(hit(1, 22, 33, "up") == true){teleport(0, 30, 13, gp.outdoor);}
        
    	
        // TO THE DUNGEON 1 // GOING BACK FROM DUNGEON 1
        else if(hit(0, 11, 11, "any") == true) {teleport(2, 16, 5, gp.dungeon);}
        else if(hit(2, 16, 5, "any") == true) {teleport(0, 11, 11, gp.outdoor);}
        
        else if(hit(2, 42, 14, "right") == true) {teleport(3, 14, 39, gp.dungeon);}
        else if(hit(3, 14, 39, "left") == true) {teleport(2, 42, 14, gp.dungeon);}
        //SKELETON LORD
        //CUTSCENE
        else if(hit(3, 35, 23, "up") == true) {skeletonLord();}
        
        //TALKING THE NPC
        else if(hit(1, 22, 26, "any") == true){speak(gp.npc[1][0]);}
        }
    }
    public boolean hit(int map,int eventCol, int eventRow, String reqDirection) {
        
        boolean hit = false;
        
        if(map == gp.currentMap){
            
        //Location of the PIT
        gp.player.area.x = gp.player.worldX + gp.player.area.x;
        gp.player.area.y = gp.player.worldY + gp.player.area.y;
        eventRect[map][eventCol][eventRow].x = eventCol * gp.tileSize + eventRect[map][eventCol][eventRow].x;
        eventRect[map][eventCol][eventRow].y = eventRow * gp.tileSize + eventRect[map][eventCol][eventRow].y;
        
        //Checks if the character hit the specific location
        if(gp.player.area.intersects(eventRect[map][eventCol][eventRow])) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
                
            }
        }
        
        gp.player.area.x = gp.player.solidAreaX;
        gp.player.area.y = gp.player.solidAreaY;
        eventRect[map][eventCol][eventRow].x = eventRect[map][eventCol][eventRow].eventRectDefaultX;
        eventRect[map][eventCol][eventRow].y = eventRect[map][eventCol][eventRow].eventRectDefaultY;
        
        }
        
        return hit;
    }
    public void teleport(int map, int col, int row, int area) {
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
       canTouchEvent = false;
//       gp.playSE(12);
    }
    public void locationTeller1(int gameState) {
        
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        canTouchEvent = false;
        
    }
    public void locationTeller2(int gameState) {
    	gp.gameState = gameState;
    	eventMaster.startDialogue(eventMaster, 1);
        canTouchEvent = false;
    }
    public void locationTeller3(int gameState) {
        
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 2);
        canTouchEvent = false;
    }
    
    public void healing(int gameState) {
        if(gp.keyH.cPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCancel = true;
            eventMaster.startDialogue(eventMaster, 3);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
//            gp.saveLoad.save();
        }
        gp.keyH.cPressed = false;
    }
    public void speak(Entity entity){
        if(gp.keyH.enterPressed == true){
        	npc.startDialogue(npc, 1);
            gp.player.attackCancel = true;
            entity.speak();
        }
    }
    public void skeletonLord() {
    	if(gp.bossBattleOn == false && Progress.skeletonLordDefeated == false) {
    		
    		gp.gameState = gp.cutSceneState;
    		gp.csManager.sceneNum = gp.csManager.skeletonLord;
    	}
    	
    	
    	
    }
}


