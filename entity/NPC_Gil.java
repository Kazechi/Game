package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Gil extends Entity{

public NPC_Gil(GamePanel gp){
    super(gp);
    
    direction = "left";
    
    speed = 1;
    dialogueSet = -1;
    getImage();
    setDialogue();
    setAction();
}

public void getImage(){
    
    up1 = setUp("/npc_img/npc2_up",gp.tileSize, gp.tileSize);
    up2 = setUp("/npc_img/npc2_up",gp.tileSize, gp.tileSize);
    down1 = setUp("/npc_img/npc2_down",gp.tileSize, gp.tileSize);
    down2 = setUp("/npc_img/npc2_down",gp.tileSize, gp.tileSize);
    right1 = setUp("/npc_img/npc2_right",gp.tileSize, gp.tileSize);
    right2 = setUp("/npc_img/npc2_right",gp.tileSize, gp.tileSize);
    left1 = setUp("/npc_img/npc2_left",gp.tileSize, gp.tileSize);
    left2 = setUp("/npc_img/npc2_left",gp.tileSize, gp.tileSize);
}
public void setDialogue() {
dialogues[0][0] = "Hello there traveler "
        + "\nMy name is CodeJedi"
        + "\nWelcome to the Oak's Dungeon";
dialogues[0][1] = "CodeJedi\n"
        + "In this new lesson you will learn "
		+ "\nhow to create and understand \nthe concept of loop's";
dialogues[0][2] = "CodeJedi\n"
        + "Looping is a fundamental concept in "
		+ "\nprogramming that allows you to execute a \nblock of code repeatedly.";
dialogues[0][3] = "CodeJedi\n"
        + "In Java, there are several looping "
		+ "\nconstructs available, including the for loop, \nwhile loop, and do-while loop..";
dialogues[0][4] = "CodeJedi\n"
        + "Also, in this lesson I will teach "
		+ "\nyou how to create and call methods";
dialogues[0][5] = "CodeJedi\n"
        + "They allow us to break down complex "
		+ "\ntasks into smaller, reusable blocks of code.";
dialogues[0][6] = "CodeJedi\n"
        + "Method signature, return type, "
		+ "\nname, parameters, and body.\r\n";
dialogues[0][7] = "CodeJedi\n"
        + "Example: public static void methodName"
		+ "\n(parametersExample: public static \nvoid methodName";
dialogues[0][8] = "CodeJedi\n"
        + "(parameter1, parameter2, ...){code block}"
        + "\nYou can only open the door with specific Key";
}

public void setAction() {
    
    if(onPath == true){
        int goalCol = 37;
        int goalRow = 33;
//            int goalCol = (gp.player.worldX + gp.player.area.x)/gp.tileSize;
//            int goalRow = (gp.player.worldY + gp.player.area.y)/gp.tileSize;
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
