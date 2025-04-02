
package main;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import object.Obj_Coin;
import object.Obj_HP;
import object.Obj_ManaCrystal;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
public class UI extends javax.swing.JFrame{
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImg;
    public boolean messageOn = false;
//    public String message = "";
//    int messCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int TITLESCREEN= 0; // 0: First Screen - 1: Second Screen
    BufferedImage heart_full, heart_half, heart_blank, crystal_blank, crystal_full, coin;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    public Entity monster;
    int charIndex = 0;
    String combinedText = "";
    
    private Image backgroundImage;
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        arial_40 = new Font("CenturyGothic", Font.PLAIN, 20);
        arial_80B = new Font("CenturyGothic", Font.PLAIN, 80);
//        Obj_Key key1 = new Obj_Key(gp);
//        keyImg = key1.img;

        //Create HUD OBJECT
        Entity heart = new Obj_HP(gp);
        heart_full = heart.img;
        heart_half = heart.img2;
        heart_blank = heart.img3;
        Entity bronzeCoin = new Obj_Coin(gp);
        coin = bronzeCoin.down1;
        Entity crystal = new Obj_ManaCrystal(gp);
        crystal_full = crystal.img;
        crystal_blank = crystal.img2;
    }
    public void addMessage(String text){
//        message = text;
//        messageOn = true;
            message.add(text);
            messCounter.add(0);
    }
    public void drawPauseScreen(){
        String text = "Pause";
        int x = getCenterText(text);
        int y = gp.maxScreenHeight / 2;
        
        g2.drawString(text, x, y);
    }
    public int getCenterText(String text){
        // Calculate the x-coordinate for centering the text horizontally
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int screenWidth = gp.maxScreenWidth;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.maxScreenWidth / 2 - length / 2;
        return x;
    }
    public int getAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public void drawTitleScreen() {
if (TITLESCREEN == 0) {
        // Load the background image
        Image backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("LogoBackgroundImages/Background.png")).getImage();
        
        // Draw the background image
        g2.drawImage(backgroundImage, 0, 0, gp.maxScreenWidth, gp.maxScreenHeight, null);
        
        //MENU BAR
        //MAIN COLOR
        String text;
        g2.setColor(new Color(223, 112, 186));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        
        // Shadow properties
        int shadowOffsetX = 3;  // X-axis shadow offset
        int shadowOffsetY = 3;  // Y-axis shadow offset
        Color shadowColor = Color.BLACK;  // Shadow color
        
        text = "NEW GAME";
        int x = getCenterText(text);
        int y = gp.tileSize * 9;

        // Draw the text shadow
        g2.setColor(shadowColor);
        g2.drawString(text, x + shadowOffsetX, y + shadowOffsetY);
        
        // Draw the actual text
        g2.setColor(new Color(223, 112, 186));
        g2.drawString(text, x, y);
        
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getCenterText(text);
        y += gp.tileSize;
        
        // Draw the text shadow
        g2.setColor(shadowColor);
        g2.drawString(text, x + shadowOffsetX, y + shadowOffsetY);
        
        // Draw the actual text
        g2.setColor(new Color(223, 112, 186));
        g2.drawString(text, x, y);
        
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getCenterText(text);
        y += gp.tileSize;
        
        // Draw the text shadow
        g2.setColor(shadowColor);
        g2.drawString(text, x + shadowOffsetX, y + shadowOffsetY);
        
        // Draw the actual text
        g2.setColor(new Color(223, 112, 186));
        g2.drawString(text, x, y);
        
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

}
    public void drawBackground(){
        
    }
    public void drawCutsceneScreen() {
    	if(TITLESCREEN == 1) {
        	
        	//CUTSCENE SELECTION
    		//BACKGROUND
    		g2.setColor(Color.black);
    		g2.fillRect(0, 0, gp.maxScreenWidth, gp.maxScreenHeight);
    		
    		//FONT
    		g2.setColor(Color.white);
        	g2.setFont(g2.getFont().deriveFont(42F));
        	
        	String text = "Welcome to the Land of Programming";
        	int x = getCenterText(text);
        	int y = gp.tileSize * 3;
        	g2.drawString(text, x, y);
        	
        	text = "When a programmer worker checks";
        	 x = getCenterText(text);
        	 y += gp.tileSize * 2;
        	 g2.drawString(text, x, y);
        	 
        	text = "onto a video game for the final time,";
        	x = getCenterText(text);
        	y += gp.tileSize;
        	g2.drawString(text, x, y);
        	
        	text = "he discovers that he have been";
        	x = getCenterText(text);
        	y += gp.tileSize;
        	g2.drawString(text, x, y);
        	
        	text = "transferred to a different reality.";
        	x = getCenterText(text);
        	y += gp.tileSize;
        	g2.drawString(text, x, y);
                
        	text = "Welcome";
        	x = getCenterText(text);
        	y += gp.tileSize * 2;
        	g2.drawString(text, x, y);
        	if(commandNum == 0) {
        		g2.drawString(">", x - gp.tileSize, y);
        	}
        	
        	text = "Back";
        	x = getCenterText(text);
        	y += gp.tileSize;
        	g2.drawString(text, x, y);
        	if(commandNum == 1) {
        		g2.drawString(">", x - gp.tileSize, y);
        	}
        	
        }
    }
    public void drawDialogueScreen() {
            //WINDOW
    int x = gp.tileSize * 3;
    int y = gp.tileSize / 2;
    int width = gp.maxScreenWidth - (gp.tileSize * 6);
    int height = gp.tileSize * 4;
    drawSubWindow(x, y, width, height);
    
    
    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
    x += gp.tileSize;
    y += gp.tileSize;
    
    if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
        char[] characters = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
        
        // TRANSITION IN DISPLAYING TEXT BY TEXT
        if (charIndex < characters.length) {
            String s = String.valueOf(characters[charIndex]);
            combinedText = combinedText + s;
            currentDialogue = combinedText;
            charIndex++;
        }
        
        if (gp.keyH.enterPressed) {
            charIndex = 0;
            combinedText = "";
            
            if (gp.gameState == gp.DIALOGUE || gp.gameState == gp.cutSceneState) {
                npc.dialogueIndex++;
                gp.keyH.enterPressed = false;
            }
        }
    } else {
        // If no text is in the array
        npc.dialogueIndex = 0;
        if (gp.gameState == gp.cutSceneState) {
            gp.csManager.scenePhase++;
        }
        if (gp.gameState == gp.DIALOGUE) {
            gp.gameState = gp.PLAY;
        }
    }
    
    for (String line : currentDialogue.split("\n")) {
        g2.drawString(line, x, y);
        y += 40;
    }
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        //TITLE STATE
        if(gp.gameState == gp.TITLE) {
            drawBackground();
            drawTitleScreen();
            drawCutsceneScreen();
        }
        
        //PLAY STATE
        if(gp.gameState == gp.PLAY){
            drawPlayerLife();
            drawMonsterLife();
            drawMessage();
        }
        //PAUSE STATE
        if(gp.gameState == gp.PAUSE){
            drawPlayerLife();
            drawPauseScreen();
        }
        
        //DIALOGUE
        if(gp.gameState == gp.DIALOGUE) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        
        //CHARACTER STATE
        if(gp.gameState == gp.CHARACTER) {
            drawCharacterState();
            drawInventory(gp.player, true);
        }
        
        //OPTION STATE
        if(gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        
        //TRANSITION STATE
        if(gp.gameState == gp.transitionState) {
            drawTransition();
        }
        
        //TRADE STATE
        if(gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        
        
      //SLEEP STATE
        if(gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
        
    }
    public void drawMessage() {
        
        
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        
        
        for(int i = 0;i < message.size(); i++) {
            if(message.get(i) != null) {
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX +2, messageY +2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messCounter.get(i) + 1;// messCounter++;
                messCounter.set(i, counter);// set the counter to the array
                messageY += 50;
                
                if(messCounter.get(i) > 180) {
                    message.remove(i);
                    messCounter.remove(i);
                }
                  
            }
        }
        
    }
    
    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        int iconSize = 32;
        
        
        int manaStartX = (gp.tileSize/2)-5;
        int manaStartY = 0;
        
        
        
        //DRAW MAX LIFE
        while(i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, iconSize, iconSize, null);
            i++;
            x += iconSize;
            manaStartY = y + 32;
            
            if(i % 8 == 0) {
            	x = gp.tileSize / 2;
            	y += iconSize;
            }
        }
        
        //RESET the HP when being damage
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        
        
        //Draw Current Life
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, iconSize, iconSize, null);
            i++;
            
            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, iconSize, iconSize, null);
            }
            i++;
            x += iconSize;
        }
        
        //DRAW PLAYER MAX MANA
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, iconSize, iconSize,null);
            i++;
            x += 20;
            
            if(i % 10 == 0) {
            	x = manaStartX;
            	y += iconSize;
            }
            
        }
        
        //DRAW MANA
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full, x, y, iconSize, iconSize, null);
            i++;
            x += 20;
            
            if(i % 10 == 0) {
            	x = manaStartX;
            	y += iconSize;
            }
            
          }
        
    }
    
    public void drawMonsterLife() {
    	
    	for(int i = 0; i < gp.monster[1].length; i++) {
    		
    		Entity monster = gp.monster[gp.currentMap][i];
    		
    		if(monster != null && monster.inCamera() == true) {
    			//MONSTER HP BAR
    	        if(monster.hpBarOn == true && monster.boss == false) {
    	        	
    	            double oneScale = (double)gp.tileSize / monster.maxLife;
    	            double hpBar = oneScale * monster.life;
    	            
    	            g2.setColor(new Color(35, 35, 35));
    	            g2.fillRect(monster.getScreenX() - 1, monster.getScreenY() - 16, gp.tileSize + 2, 12);
    	            
    	            g2.setColor(new Color(255, 0, 30));
    	            g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBar, 10);
    	            
    	            monster.hpBarCounter++;
    	            
    	        if(monster.hpBarCounter > 600) {    
    	        	monster.hpBarCounter = 0;
    	        	monster.hpBarOn = false;
    	        }
    	            
    	        } 
    		
    	        else if(monster.boss == true) {
    	        	double oneScale = (double)gp.tileSize*8 / monster.maxLife;
    	            double hpBar = oneScale * monster.life;
    	            
    	            int x = gp.screenWidth/2 - gp.tileSize*4;
    	            int y = gp.tileSize*10;
    	            
    	            g2.setColor(new Color(35, 35, 35));
    	            g2.fillRect(x-1,y-1 , gp.tileSize * 8 + 2, 22);
    	            
    	            g2.setColor(new Color(255, 0, 30));
    	            g2.fillRect(x, y, (int)hpBar, 20);
    	            
    	            
    	            g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
    	            g2.setColor(Color.white);
    	            g2.drawString(monster.name, x+4, y-10);
    	        }
    		}
    	}
    	
    	 
    }
    
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
        
        text = "Game Over!";
        //Shawdow of the text
        g2.setColor(Color.black);
        x = getCenterText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        
        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        
        //RETRY OPTION
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        //Moving cursor
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
        }
        
        
        //BACK TO TITLE SCREEN
        //QUIT OPTION
        text = "Quit";
        x = getCenterText(text);
        y += 55;
        g2.drawString(text, x, y);
        //Moving cursor
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }
        
    }
    
    public void drawCharacterState() {
        //CREATE FRAME
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //DISPLAY TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30f));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35; //CONSTANT VALUE
        
        //DISPLAY NAME OF STATS
        g2.drawString("Level", textX, textY); textY += lineHeight;
        
        g2.drawString("Mana", textX, textY); textY += lineHeight;
        
        g2.drawString("Life", textX, textY);textY += lineHeight;
        
        g2.drawString("Dexterity", textX, textY);textY += lineHeight;
        
        g2.drawString("Strength", textX, textY);textY += lineHeight;
        
        g2.drawString("Attack", textX, textY);textY += lineHeight;
        
        g2.drawString("Defense", textX, textY);textY += lineHeight;
        
        g2.drawString("Exp", textX, textY);textY += lineHeight;
        
        g2.drawString("Next Level", textX, textY);textY += lineHeight;
        
        g2.drawString("Coin", textX, textY); textY += lineHeight + 10;
        
        g2.drawString("Sword", textX, textY);textY += lineHeight + 15;
        
        g2.drawString("Shield", textX, textY); textY += lineHeight;

        
        
        //STATS VALUES
        int tailX = (frameX + frameWidth) - 30;
        
        //RESET textY
        textY = frameY + gp.tileSize;
        String value;
        
        value =  String.valueOf(gp.player.level);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.strength);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.dexterity);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.attack);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.defense);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value =  String.valueOf(gp.player.exp);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
         value =  String.valueOf(gp.player.nextLevelExp);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
         value =  String.valueOf(gp.player.coin);
        textX = getAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        
        g2.drawImage(gp.player.currentSword.down1, tailX - gp.tileSize, textY - 25, null);
        
        textY += gp.tileSize;
        
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 25, null);
        
    }
    public void drawInventory(Entity entity, boolean cursor) {
        
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        
        if(entity == gp.player){
            //PLAYER FRAME
        frameX = gp.tileSize * 12;
        frameY = gp.tileSize;
        frameWidth = gp.tileSize * 6;
        frameHeight = gp.tileSize *5;
        slotCol = playerSlotCol;
        slotRow = playerSlotRow;
        }
        else{
            //MERCHANT FRAME
        frameX = gp.tileSize * 2;
        frameY = gp.tileSize;
        frameWidth = gp.tileSize * 6;
        frameHeight = gp.tileSize *5;
        slotCol = npcSlotCol;
        slotRow = npcSlotRow;
        }
        
        //FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        
        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;
        
        //DRAW PLAYERS ITEMS
        for(int i = 0; i < entity.inventory.size(); i++) {
            
            //EQUIP CURSOR
            if(entity.inventory.get(i) == entity.currentSword ||
                    entity.inventory.get(i) == entity.currentShield || 
                    entity.inventory.get(i) == entity.currentLight) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            
            
            //DISPLAY AMOUNT
            if(entity == gp.player && entity.inventory.get(i).amount > 1) {
            	g2.setFont(g2.getFont().deriveFont(32f));
            	int amountX;
            	int amountY;
            	
            	String s = "" + entity.inventory.get(i).amount;
            	amountX = getAlignToRightText(s, slotX + 44);
            	amountY = slotY + gp.tileSize;
            	
            	//SHADOW
            	g2.setColor(new Color(60,60,60));
            	g2.drawString(s, amountX, amountY);
            	//NUMBER
            	g2.setColor(Color.white);
            	g2.drawString(s, amountX-3, amountY-3);
            	
            }
            
            
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        //CURSOR
        if(cursor == true){
            int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        
        
        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        
        //DRAW DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 5;
        
        
        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28f));
        
        int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
        
        if(itemIndex < entity.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
            
            //DURABILITY
//            g2.drawString("Durability:" + entity.inventory.get(itemIndex).durability, textX, textY + 80);
            
        }
        }
        
        
    }
    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        
        //SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 10;
        int frameHeight = gp.tileSize * 10;
        
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        switch(subState) {
            case 0: option_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }
        
        gp.keyH.enterPressed = false;
    }
    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;
        
        //TITLE
        String text = "Options";
        textX = getCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        //FULL SCREEN ON / OFF
        textX = frameX +  gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            
            if(gp.keyH.enterPressed == true) {
                if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }
        
        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        
        //END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }
        
        //BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
        }
        
        
        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        
        if(gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }
        
        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        
        
        currentDialogue = "The change will take \neffect after restarting \nthe game.";
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        
        //BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 2;
            }
        }
    }
    public void options_control(int frameX, int frameY) {
        
        int textX;
        int textY;
        
        //TITLE
        String text =  "Control";
        textX = getCenterText(text);
        textY =  frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character \nScreen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;
        
        
        textX = frameX + gp.tileSize * 7;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("T", textX, textY); textY += gp.tileSize;
        g2.drawString("E", textX, textY); textY += gp.tileSize;
        g2.drawString("M", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        //BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 1;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        
        currentDialogue = "Quit the game and \nreturn to the Title Screen";
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }
        
        
        //IF THE PLAYER CHOOSE "YES"
        String text = "Yes";
        textX = getCenterText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.ui.TITLESCREEN = 0;
            }
        }
        
        //IF THE PLAYER CHOOSE "NO"
        text = "No";
        textX = getCenterText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 2;
            }
        }
        
    }
    public void drawSleepScreen() {
    	
    	counter++;
    	
    	if(counter < 120) {
    		gp.eManager.lighting.filterAlpha += 0.01f;
    		if(gp.eManager.lighting.filterAlpha > 1f) {
    			gp.eManager.lighting.filterAlpha = 1f;
    		}
    	}
    	if(counter >= 120) {
    		gp.eManager.lighting.filterAlpha -= 0.01f;
    		if(gp.eManager.lighting.filterAlpha <= 0f) {
    			gp.eManager.lighting.filterAlpha = 0;
    			counter = 0;
    			gp.eManager.lighting.dayState = gp.eManager.lighting.day;
    			gp.eManager.lighting.dayCounter = 0;
    			gp.gameState = gp.PLAY;
    			gp.player.getPlayerImage();
    			
    		}
    	}
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
    public void drawTradeScreen(){
        switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select(){
    	npc.dialogueSet = 0;
        drawDialogueScreen();
        
        //DRAW WINDOW SCREEN
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 4;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, height, width);
        
        // DRAW TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if(commandNum == 0){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }
        y += gp.tileSize;
        
        g2.drawString("Sell", x, y);
        if(commandNum == 1){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += gp.tileSize;
        
        g2.drawString("Leave", x, y);
        if(commandNum == 2){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed == true){
                commandNum = 0;
                npc.startDialogue(npc, 1);
            }
        }
        y += gp.tileSize;
        
    }
    public void trade_buy(){
        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        
        //DRAW MERCHANT INVENTORY
        drawInventory(npc, true);
        
        //DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);
        
        
        //DRAW PLAYER COIN WINDOW
         x = gp.tileSize * 12;
         y = gp.tileSize * 9;
         width = gp.tileSize * 6;
         height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Coin: " + gp.player.coin, x+24, y+60);
        
        
        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int)(gp.tileSize * 5.5);
            y = (int)(gp.tileSize * 5.5);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);
            
            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getAlignToRightText(text, gp.tileSize * 8-20);
            g2.drawString(text, x, y+32);
            
            //BUY ITEM
            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    npc.startDialogue(npc, 2);
                }
                
                else {
                	if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
                		gp.player.coin -= npc.inventory.get(itemIndex).price;
                	}
                	else {
                		subState = 0;
                		npc.startDialogue(npc, 3);
                	}
                }
                
            }
            
        }
        
    }
    public void trade_sell(){
        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);
        
        
        int x;
        int y;
        int width;
        int height;
        
        //DRAW HINT WINDOW
         x = gp.tileSize * 2;
         y = gp.tileSize * 9;
         width = gp.tileSize * 6;
         height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);
        
        
        //DRAW PLAYER COIN WINDOW
         x = gp.tileSize * 12;
         y = gp.tileSize * 9;
         width = gp.tileSize * 6;
         height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Coin: " + gp.player.coin, x+24, y+60);
        
        
        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x = (int)(gp.tileSize * 15.5);
            y = (int)(gp.tileSize * 5.5);
            width = (int)(gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);
            
            int price = gp.player.inventory.get(itemIndex).price / 2;
            String text = "" + price;
            x = getAlignToRightText(text, gp.tileSize * 18-20);
            g2.drawString(text, x, y+32);
            
            //SELLING ITEM
            if(gp.keyH.enterPressed == true){
                if(gp.player.inventory.get(itemIndex) == gp.player.currentSword ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc, 4);
                }
                else{
                	if(gp.player.inventory.get(itemIndex) .amount > 1) {
                		gp.player.inventory.get(itemIndex).amount--;
                	}
                	else {
                		gp.player.inventory.remove(itemIndex);
                	}
                    
                    gp.player.coin += price;
                }
            }
            
        }
        
        
    }
    public void drawTransition(){
        counter++;
        g2.setColor(new Color(0,0,0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        if(counter == 50){ //THE TRANSITION IS DONE
            counter = 0;
            gp.gameState = gp.PLAY;
            gp.currentMap = gp.event.tempMap;
            gp.player.worldX = gp.tileSize * gp.event.tempCol;
            gp.player.worldY = gp.tileSize * gp.event.tempRow;
            gp.event.previousEventX = gp.player.worldX;
            gp.event.previousEventY = gp.player.worldY;
            gp.changeArea();
        }
        
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 220);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25 ,25);
        
    }
}
