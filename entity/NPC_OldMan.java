
package entity;

import java.util.Random;
import main.GamePanel;

public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp){
        super(gp);
        
        direction = "right";
        
        speed = 1;
        dialogueSet = -1;
        getImage();
        setDialogue();
        setAction();
    }
    
    public void getImage(){
        
        up1 = setUp("/npc_img/oldman_up_1",gp.tileSize, gp.tileSize);
        up2 = setUp("/npc_img/oldman_up_2",gp.tileSize, gp.tileSize);
        down1 = setUp("/npc_img/oldman_down_1",gp.tileSize, gp.tileSize);
        down2 = setUp("/npc_img/oldman_down_2",gp.tileSize, gp.tileSize);
        right1 = setUp("/npc_img/oldman_right_1",gp.tileSize, gp.tileSize);
        right2 = setUp("/npc_img/oldman_right_2",gp.tileSize, gp.tileSize);
        left1 = setUp("/npc_img/oldman_left_1",gp.tileSize, gp.tileSize);
        left2 = setUp("/npc_img/oldman_left_2",gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue() {
    	dialogues[0][0] = "Hello there young traveler"
                + "\nMy name is Binary Wizard";
        dialogues[0][1] = "Binary Wizard\n"
                + "Welcome to the Programming Kingdom, "
                + "\nwhere coding is everything";
        dialogues[0][2] = "Binary Wizard\n"
                + "You must be quite shock reincarnating here,";
        dialogues[0][3] = "Binary Wizard\n"
                + "But don't worry I will guide you "
                + "\nto become a great Byte Warrior.";
        dialogues[0][4] = "Binary Wizard\n"
                + "Let's start with quick lesson. In this lesson \nyou will learn the Java Language.";
        dialogues[0][5] = "Binary Wizard\n"
                + "Starting with Java Basic, you must use the key"
                + "\nSystem.out.println(\" \").";
        dialogues[0][6] = "Binary Wizard\n"
                + "Remember to always "
                + "\nend it with semi-colon \";\".";
        dialogues[0][7] = "Binary Wizard\n"
                + "Try solving all the problem to get all the Key. "
                + "\nBut remember this, to get the key you must "
                + "\nfirst get the correct code to acquire it.";
        dialogues[0][8] = "Binary Wizard\n"
                + "You will get a boost for every correct code";
        dialogues[0][9] = "Binary Wizard\n"
                + "Well then traveler, talk to me again \nto know the basic Key's on "
                + "\nhow to play this game.";
    	
        dialogues[1][0] = "Binary Wizard\n"
                + "The key to move your character \n W, S, A, D or ARROW Key.";
        dialogues[1][1] = "Binary Wizard\n"
                + "The key to attack is [T], \ndon't worry about pressing the key \nwhen talking to any npc.";
        dialogues[1][2] = "Binary Wizard\n"
                + "The key for option is [ESC].";
        dialogues[1][3] = "Binary Wizard\n"
                + "The key to shoot projectile is [E]. \nRemember to conserve your Mana";
        dialogues[1][4] = "Binary Wizard\n"
                + "The key to view the map is [P].";
        dialogues[1][5] = "Binary Wizard\n"
                + "To open mini-map press [X].";
        dialogues[1][6] = "Binary Wizard\n"
                + "The key to open inventory is [M].";
        dialogues[1][7] = "Binary Wizard\n"
                + "You can already try your prior skills in \ngetting that lamp";
        dialogues[1][7] = "Binary Wizard\n"
                + "Also, before I forgot \nyou can save your items/level by interacting "
        		+ "\nwith some monument across the map.";
        dialogues[1][8] = "Binary Wizard\n"
                + "I think you're already prepared. \n I wish you luck traveler. \nAnd don't forget to have fun Coding";
        dialogues[1][9] = "Binary Wizard\n"
                + "Have fun traveling the "
                + "\nKingdom of Programming";;
    }
    
    
    public void setAction() {
        
        if(onPath == true){
            int goalCol = 37;
            int goalRow = 33;
//                int goalCol = (gp.player.worldX + gp.player.area.x)/gp.tileSize;
//                int goalRow = (gp.player.worldY + gp.player.area.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
        }
        else{
            actionCounter ++;
            if(actionCounter == 120) {
            Random random = new Random();// get a random number
        
            int i = random.nextInt(100)+1;// randomly pick a number from 1 - 100
        
            if(i <= 25) {
            direction = "up";
            }
            if(i > 25 && i <= 50) {
            	direction = "down";
            }
            if(i > 50 && i <= 75) {
            direction = "left";
            }
            if(i > 75 && i <= 100) {
            	direction = "right";
            }
        
            actionCounter = 0;
        
            }
        }
    }
    
    
    public void speak() {
        
        facePlayer();
        startDialogue(this, dialogueSet);
    	
    	
        dialogueSet++;
        if(dialogues[dialogueSet][0] == null) {
        	dialogueSet--;
        }
        
        
        // make the npc move to specific location
        onPath = true;
    }
}
