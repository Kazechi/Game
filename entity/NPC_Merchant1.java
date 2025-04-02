package entity;

import main.GamePanel;
import object.Obj_JavaDagger;
import object.Obj_Lantern;
import object.Obj_Potion_HP;
import object.Obj_Shield;
import object.Obj_Tent;
import object.Obj_Weapon_Sword;
import object.Obj_Weapon_Sword2;

public class NPC_Merchant1 extends Entity{
	public NPC_Merchant1(GamePanel gp){
        super(gp);
        
        direction = "down";
        
        
        getImage();
        setDialogue();
        setItems();
    }
    
    public void getImage(){
        
        up1 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        up2 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        down1 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        down2 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        right1 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        right2 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        left1 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
        left2 = setUp("/npc_img/npc_merchant",gp.tileSize, gp.tileSize);
    }
    
    public void setDialogue() {
        dialogues[0][0] = "Hello there traveler "
        		+ "\nWelcome to my Wonderful Shop "
        		+ "\nI have some good stuff just for you "
        		+ "\nHave a look";
        dialogues[1][0] = "Come Again. Thank You!";
        dialogues[2][0] = "Not Enough Coins";
		dialogues[3][0] = "You cannot carry any more!";
		dialogues[4][0] = "You cannot sell an equipped item!";
    }
    
    public void setItems(){
        inventory.add(new Obj_Potion_HP(gp));
        inventory.add(new Obj_JavaDagger(gp));
        inventory.add(new Obj_Tent(gp));
    }
    
    
    public void speak(){
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
        
    }
}
