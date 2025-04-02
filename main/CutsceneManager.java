package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.Entity;
import entity.PlayerDummy;
import monster.Monster_FireWallOverLord;
import object.Obj_IronDoorCutscene;
import object.Obj_Treasure;

public class CutsceneManager extends Entity{
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	int counter = 0;
	float alpha = 0f;
	int y;
	String endCredit;
	
	
	//Scene Number
	public final int NA = 0;
	public final int skeletonLord = 1;
	public final int ending = 2;
	public CutsceneManager(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		endCredit = "Programmer\n"
				+ "Somera, Alord Jhinroy M."
				+ "\n\n\n\n\n\n\n\n\n\n\n"
				+ "Designer\n"
				+ "Somera, Alord Jhinroy M."
				+ "\n\n\n\n\n\n\n\n\n\n\n"
				+ "Sprite's Creator\n"
				+ "Somera, Alord Jhinroy M.\n"
				+ "Martinez, Kurt David\n\n\n\n\n\n"
				+ "THANK YOU FOR PLAYING! :>";
//		setDialogue();
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case skeletonLord: scene_skeletonLod(); break;
		case ending: scene_ending(); break;
		}
		
	}
	public void setDialogue() {

    	dialogues[0][0] = "Welcome foolish traveler";
    	dialogues[0][1] = "Have you come to experiece DEATH";
    	dialogues[0][2] = "Well then, I shall grant you wish";
	}
	public void scene_skeletonLod() {
		
		if(scenePhase == 0) {
			
			gp.bossBattleOn = true;
			
			//SHUTTING THE IRON DOOR
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new Obj_IronDoorCutscene(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*35;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*24;
					gp.obj[gp.currentMap][i].temp = true;
					break;
				}
				
			}
			
			//SEARCH A VACANTSLOT FOR THE DUMMY
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			gp.player.drawing = false;
			
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			
			gp.player.worldY -= 2;
			
			if(gp.player.worldY < gp.tileSize * 10) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			
			//SEARCH THE BOSS
			for(int i = 0; i < gp.monster[1].length; i++) {
				
				if(gp.monster[gp.currentMap][i] != null &&
						gp.monster[gp.currentMap][i].name == Monster_FireWallOverLord.monName) {
					
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
					
				}
				
			}
			
		}
		
		if(scenePhase == 3) {
			//THE BOSS SPEAK
			//GETTING THE SET DIALOGUE FROM THE BOSS
//			startDialogue(this, 0);
			gp.ui.drawDialogueScreen();
//			gp.ui.setDialogue();
//			if(counterReached(300) == true) {
//				scenePhase++;
//			}
			
		}
		
		if(scenePhase == 4) {
			
			//RETURNING THE CAMERA TO THE PLAYE
			
			//SEARCHING FOR THE DUMMY
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					//RESTORING THE CAMERA BACK TO THE PLAYER
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					
					
					//DETECT THE DUMMY
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			
			//START DRAWING THE PLAYER
			gp.player.drawing = true;
			
			
			//RESET
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.PLAY;
			
			
			// change music
//			gp.stopMusic();
//			gp.playSE(18);
		}
		
		
	}
	public void scene_ending() {
		
		if(scenePhase == 0) {
			gp.ui.npc = new Obj_Treasure(gp);
			scenePhase++;
		}
		if(scenePhase == 1) {
			//DISPLAY DIALOGUES
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 2) {
			//WAITING PERIOD
			if(counterReached(300) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 3) {
			// THE SCREEN GETS DARKER
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackground(alpha);
			if(alpha == 1f) {
				alpha = 0;
				scenePhase++;
			}
		}
		if(scenePhase == 4) {
			drawBlackBackground(1f);
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			String text = "Your victory echoes throughout the codebase, "
                                + "\ninspiring developers far and wide. "
                                + "\nYour perseverance and mastery over the "
                                + "\nlanguage have cemented your place "
                                + "\nin the programming history. "
                                + "\nNow, go forth, valiant Java programmer, "
                                + "\nand continue to shape the digital world "
                                + "\nwith your unyielding code! "
                                + "\nThe future awaits your next epic quest.";
			drawString(alpha, 38f, 150, text, 33);
			
			if(counterReached(600) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 5) {
			drawBlackBackground(1f);
			drawString(1f, 65, gp.screenHeight/2, "Java Realms: The Java Chronicles", 40);
			if(counterReached(480) == true) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 6) {
			drawBlackBackground(1f);
//			y = gp.screenHeight/2;
			drawString(1f, 38f, gp.screenHeight/2, endCredit, 40);
			if(counterReached(480) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 7) {
			drawBlackBackground(1f);
			
			// SCROLLING DOWN THE CREDIT
			y--;
			drawString(1f, 38f, y, endCredit, 40);
		}	
	}
	public boolean counterReached(int target) {
		boolean counterReached = false;
		
		counter++;
		if(counter > target) {
			counterReached = true;
			counter = 0;
		}
		return counterReached;
		
		
	}
	public void drawBlackBackground(float alpha) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));
		
		
		for(String line: text.split("\n")) {
			int x = gp.ui.getCenterText(line);
			g2.drawString(line, x, y);
			y += lineHeight;
		}
		
	}
	
}
