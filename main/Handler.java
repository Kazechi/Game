package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Handler implements KeyListener{
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, cPressed, 
            arrowUpPressed, arrowDownPressed, arrowRightPressed, arrowLeftPressed, shotKeyPressed,
            attackKeyPressed;

    
    public Handler(GamePanel gp) {
        
        this.gp = gp;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    // Creating a key to move the character
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        //TITLE STATE
        if(gp.gameState == gp.TITLE) {
            titleState(code);
//            cutsceneState(code);
        }
        //PLAY STATE
        else if(gp.gameState == gp.PLAY) {
            playState(code);
            
        }
        //PAUSE STATE
        else if(gp.gameState == gp.PAUSE) {
            pauseState(code);
        }
        
        //DIALOGUE STATE
        else if(gp.gameState == gp.DIALOGUE || gp.gameState == gp.cutSceneState) {
            dialogueState(code);
        }
        
        //CHARACTER STATE / CHARACTER STATUS
        else if(gp.gameState == gp.CHARACTER) {
            characterState(code);
        }
        
        //OPTION STATE
        else if(gp.gameState == gp.optionState) {
            optionState(code);
        }
        //GAME OVER STATE
        else if(gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        
        //TRADE STATE
        else if(gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        
        //MAP STATE
        else if(gp.gameState == gp.mapState) {
            mapState(code);
        }
    }
    
    public void titleState(int code) {
    	
        if(gp.ui.TITLESCREEN == 0) {
                if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
            
            }
            if(code == KeyEvent.VK_S){
           gp.ui.commandNum++;
            if(gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
           
            }
            
            if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
            
            }
            if(code == KeyEvent.VK_DOWN){
           gp.ui.commandNum++;
            if(gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
           
            }
            
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.ui.TITLESCREEN = 1;
                }
                if(gp.ui.commandNum == 1){
                    // will be adding later
                    gp.saveLoad.load();
                    gp.gameState = gp.PLAY;
                }
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                    }
                }
            }
        else if(gp.ui.TITLESCREEN == 1) {
        	if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
                
                }
                if(code == KeyEvent.VK_S){
               gp.ui.commandNum++;
                if(gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
               
                }
                
                if(code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
                
                }
                if(code == KeyEvent.VK_DOWN){
               gp.ui.commandNum++;
                if(gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
               
                }
                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                    	gp.ui.currentDialogue = "WELCOME TO THE LAND OF PROGRAMMING, TRAVELER";
                        gp.gameState = gp.PLAY;
                        
                    }
                    if(gp.ui.commandNum == 1){
                        
                    	gp.ui.TITLESCREEN = 0;
                    }
                    }
        }
        
    }
    public void cutsceneState(int code) {
    
    }
    public void playState(int code) {
        //KEY HANDLER
        //MAKING THE PLAYER MOVE
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
            
        }
        if(code == KeyEvent.VK_UP){
            arrowUpPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            arrowDownPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            arrowRightPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            arrowLeftPressed = true;
        }
        if(code == KeyEvent.VK_E){
            shotKeyPressed = true;
        }
        
        //KEY FOR OPENING INVENTORY
        if(code == KeyEvent.VK_M) {
            gp.gameState = gp.CHARACTER;
        }
        
        
        
        //KEY FOR PAUSING THE GAME
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PAUSE;
            
        }
        
        //KEY FOR TALKING TO NPC
        if(code == KeyEvent.VK_T){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_Q) {
            attackKeyPressed = true;
        }
        //KEY FOR DRINKING WATER
        if(code == KeyEvent.VK_C){
            cPressed = true;
            
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionState;
        }
        
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.mapState;
        }
        
        if(code == KeyEvent.VK_X){
            if(gp.map.miniMapOn == false){
                gp.map.miniMapOn = true;
            }
            else{
                gp.map.miniMapOn = false;
            }
        }
        
        if(code == KeyEvent.VK_R){
            switch(gp.currentMap){
                case 0:gp.tileManager.loadMap("/map/worldMap.txt", 0); break;
                case 1: gp.tileManager.loadMap("map/interior01.txt",1); break;
            }
            
            
        }
            
            /*
            if(gp.gameState == gp.PLAY){
                
            }else if(gp.gameState == gp.PAUSE){
                gp.gameState = gp.PLAY;
            }  
            */
    }
    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
    }
    public void pauseState(int code) {
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PLAY;
            
        }
    }
    public void characterState(int code) {
        
        if(gp.gameState == gp.CHARACTER) {
                    if(code == KeyEvent.VK_M) {
                gp.gameState = gp.PLAY;
            }
        }
       
        if(code == KeyEvent.VK_ENTER) {
            gp.player.selectedItems();
        }
        
        playerInventory(code);

    }
    public void optionState(int code) {
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.PLAY;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        
        
        int maxCommandNum = 0;
        switch(gp.ui.subState){
            case 0: maxCommandNum = 3; break;
            case 3: maxCommandNum = 1; break;
        }
        //WORD KEY
        
        try{
            if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
             gp.ui.commandNum++;
             if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        
        //ARROW KEY
        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_DOWN){
             gp.ui.commandNum++;
             if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    public void gameOverState(int code){
        //MOVING CURSOR
        //USING WORD KEY TO MOVE
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        
        //USING ARROWS TO MOVE THE CURSOR
        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        
        
        if(code == KeyEvent.VK_ENTER){
            //IF PLAYER CHOOSE RETRY
            //IT WILL RESUME THE GAME
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.PLAY;
                gp.retry(true);
                gp.player.getPlayerImage();
            	gp.player.getPlayerAttack();
                
            //ELSE THE PLAYER CHOOSE QUIT
            //IT WILL RESET THE GAME AND GO TO TITLE SCREEN
            }else if(gp.ui.commandNum == 1){
                gp.gameState = gp.TITLE;
                gp.restart();
            }
        }
        
    }
    
    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        //MOVING CURSOR USING LETTER
        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
        }
        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
        }
        
        //MOVING CURSOR USING ARROWS
        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
        }
        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
        }
        
        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
        
        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }
    
    public void mapState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.PLAY;
        }
    }
    
    public void playerInventory(int code){
         //MOVING THE CURSOR INSIDE THE INVENTORY
        //FOR W A S D KEYS
        if(code == KeyEvent.VK_W) {
            if(gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
//                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.playerSlotCol != 0){
                gp.ui.playerSlotCol--;
//                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
//                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.playerSlotCol != 4){
                gp.ui.playerSlotCol++;
//                gp.playSE(9);
            }
        }
        
        //FOR KEY ARROW
        if(code == KeyEvent.VK_UP) {
            if(gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
//                gp.playSE(9);
            }
        }
        
        if(code == KeyEvent.VK_DOWN) {
            if(gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
//                gp.playSE(9);
            }
        }
        
        if(code == KeyEvent.VK_RIGHT) {
            if(gp.ui.playerSlotCol != 4){
                gp.ui.playerSlotCol++;
//                gp.playSE(9);
            }
        }
        
        if(code == KeyEvent.VK_LEFT) {
            if(gp.ui.playerSlotCol != 0){
                gp.ui.playerSlotCol--;
//                gp.playSE(9);
            }
            
        }
    }
    
     public void npcInventory(int code){
         //MOVING THE CURSOR INSIDE THE INVENTORY
        //FOR W A S D KEYS
        if(code == KeyEvent.VK_W) {
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
            }
        }
        
        //FOR KEY ARROW
        if(code == KeyEvent.VK_UP) {
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
            }
        }
        
        if(code == KeyEvent.VK_DOWN) {
            if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
            }
        }
        
        if(code == KeyEvent.VK_RIGHT) {
            if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
            }
        }
        
        if(code == KeyEvent.VK_LEFT) {
            if(gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
            }
            
        }
    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }if(code == KeyEvent.VK_S){
            downPressed = false;
        }if(code == KeyEvent.VK_D){
            rightPressed = false;
        }if(code == KeyEvent.VK_A){
            leftPressed = false;
        }if(code == KeyEvent.VK_UP){
            arrowUpPressed = false;
        }if(code == KeyEvent.VK_DOWN){
            arrowDownPressed = false;
        }if(code == KeyEvent.VK_RIGHT){
            arrowRightPressed = false;
        }if(code == KeyEvent.VK_LEFT){
            arrowLeftPressed = false;
        }if(code == KeyEvent.VK_E){
            shotKeyPressed = false;
        }
        
        
    }
    
}
