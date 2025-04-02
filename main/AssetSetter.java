
package main;

import entity.NPC_OldMan;
import accessories.House_Back;
import accessories.House_Front;
import accessories.Tree1;
import accessories.Tree2;
import accessories.Tree3;
import data.Progress;
import entity.NPC_Gil;
import entity.NPC_Merchant1;
import monster.Monster_BinaryDroid;
import monster.Monster_CodeChampion;
import monster.Monster_ByteBlitz;
import monster.Monster_FireWallOverLord;
import monster.Monster_CodeEnigma;
import object.Obj_Chest;
import object.Obj_Door1;
import object.Obj_Door2;
import object.Obj_Door3;
import object.Obj_IronDoor1;
import object.Obj_IronDoor2;
import object.Obj_IronDoor3;
import object.Obj_IronDoor4;
import object.Obj_IronDoor5;
import object.Obj_IronDoor6;
import object.Obj_IronDoor7;
import object.Obj_IronDoorCutscene;
import object.Obj_Key1;
import object.Obj_Key2;
import object.Obj_Key3;
import object.Obj_KeyBlue1;
import object.Obj_KeyBlue2;
import object.Obj_KeyBlue3;
import object.Obj_KeyBlue4;
import object.Obj_KeyBlue5;
import object.Obj_KeyBlue6;
import object.Obj_KeyBlue7;
import object.Obj_Lantern;
import object.Obj_Potion_HP;
import object.Obj_Saving;
import object.Obj_Tent;
import object.Obj_Treasure;
import object.Obj_Weapon_Sword2;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setAccessory() {
    	//SETTING UP ACCESSORY
        int mapNum = 1;
        int i = 0;
        gp.obj[mapNum][i] = new Tree1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 16;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        gp.obj[mapNum][i] = new Tree1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 18;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        gp.obj[mapNum][i] = new Tree1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        
        gp.obj[mapNum][i] = new Tree2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 17;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        gp.obj[mapNum][i] = new Tree2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 28;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        gp.obj[mapNum][i] = new Tree2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        
        gp.obj[mapNum][i] = new Tree3(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 34;
        i++;
        
        gp.obj[mapNum][i] = new Tree3(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 23;
        gp.obj[mapNum][i].worldY = gp.tileSize * 34;
        i++;
    }
    public void setObj(){
        int mapNum = 0;
        //Printing objects and their Locations
        int i = 0;
        gp.obj[mapNum][i] = new Obj_Key1(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 43 * gp.tileSize;
        i++;
        
       
        gp.obj[mapNum][i] = new Obj_Key2(gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Key3(gp);
        gp.obj[mapNum][i].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
        
         gp.obj[mapNum][i] = new Obj_Lantern(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 38 * gp.tileSize;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Door1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 17;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Door2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Door3(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 10;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Chest(gp, new Obj_Potion_HP(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 15;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Chest(gp, new Obj_Potion_HP(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Chest(gp, new Obj_Potion_HP(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize * 9;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;
        
        
        //SAVING
        gp.obj[mapNum][i] = new Obj_Saving(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 31;
        gp.obj[mapNum][i].worldY = gp.tileSize * 43;
        i++;
        
        
        //HOUSE BACK
        gp.obj[mapNum][i] = new House_Back(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 30;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        
        gp.obj[mapNum][i] = new House_Back(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 28;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        
        gp.obj[mapNum][i] = new House_Back(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 34;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        
        
        //HOUSE FRONT
        gp.obj[mapNum][i] = new House_Front(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 27;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        
        gp.obj[mapNum][i] = new House_Front(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 32;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        
        gp.obj[mapNum][i] = new House_Front(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 20;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        
        gp.obj[mapNum][i] = new House_Front(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 18;
        gp.obj[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        
        
        
        mapNum = 2;
        i = 0;
        // KEYS
        gp.obj[mapNum][i] = new Obj_KeyBlue7(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.obj[mapNum][i] = new Obj_KeyBlue3(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
        i++;
        gp.obj[mapNum][i] = new Obj_KeyBlue1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.obj[mapNum][i] = new Obj_KeyBlue4(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 15;
        gp.obj[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        gp.obj[mapNum][i] = new Obj_KeyBlue5(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 44;
        gp.obj[mapNum][i].worldY = gp.tileSize * 33;
        i++;
        gp.obj[mapNum][i] = new Obj_KeyBlue2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 41;
        i++;
        gp.obj[mapNum][i] = new Obj_KeyBlue6(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 39;
        gp.obj[mapNum][i].worldY = gp.tileSize * 47;
        i++;
        
        
        // IRON DOORS
        gp.obj[mapNum][i] = new Obj_IronDoor7(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        gp.obj[mapNum][i] = new Obj_IronDoor3(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 17;
        gp.obj[mapNum][i].worldY = gp.tileSize * 13;
        i++;
        gp.obj[mapNum][i] = new Obj_IronDoor1(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 9;
        i++;
        gp.obj[mapNum][i] = new Obj_IronDoor4(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 40;
        gp.obj[mapNum][i].worldY = gp.tileSize * 26;
        i++;
        gp.obj[mapNum][i] = new Obj_IronDoor5(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        gp.obj[mapNum][i] = new Obj_IronDoor2(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.obj[mapNum][i] = new Obj_IronDoor6(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 39;
        gp.obj[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        
        mapNum = 3;
        i = 0;
        gp.obj[mapNum][i] = new Obj_IronDoorCutscene(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        
        
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 13;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;
        
        
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 14;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;
        
        
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 15;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 15;
        gp.obj[mapNum][i].worldY = gp.tileSize * 18;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 15;
        gp.obj[mapNum][i].worldY = gp.tileSize * 15;
        i++;
        gp.obj[mapNum][i] = new Obj_Treasure(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 15;
        gp.obj[mapNum][i].worldY = gp.tileSize * 14;
        i++;
        
    }
    public void setNPC(){
        //MAP 0
        int mapNum = 0;
        //Printing objects and their Locations
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 27;
        gp.npc[mapNum][i].worldY = gp.tileSize * 42;
        i++;

        
        //MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant1(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 22;
        gp.npc[mapNum][i].worldY = gp.tileSize * 43;
        i++;
       
        
        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Gil(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 23;
        gp.npc[mapNum][i].worldY = gp.tileSize * 5;
        i++;
        
    }
    public void setMonster() {
        int mapNum = 0;
        int i =0;
        gp.monster[mapNum][i] = new Monster_CodeEnigma(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;
        i++;
        
        gp.monster[mapNum][i] = new Monster_CodeEnigma(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;
        i++;
        
        gp.monster[mapNum][i] = new Monster_CodeEnigma(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        
        gp.monster[mapNum][i] = new Monster_CodeEnigma(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;

        gp.monster[mapNum][i] = new Monster_ByteBlitz(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;
        i++;
        
        gp.monster[mapNum][i] = new Monster_ByteBlitz(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 37;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;
        i++;
        
        gp.monster[mapNum][i] = new Monster_ByteBlitz(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        
        gp.monster[mapNum][i] = new Monster_ByteBlitz(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        
        gp.monster[mapNum][i] = new Monster_CodeChampion(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 9;
        gp.monster[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        
        gp.monster[mapNum][i] = new Monster_CodeChampion(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        
        //MONSTER IN DUNGEON
        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new Monster_BinaryDroid(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;
        
        gp.monster[mapNum][i] = new Monster_BinaryDroid(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 19;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38;
        i++;
        
        gp.monster[mapNum][i] = new Monster_BinaryDroid(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        
        gp.monster[mapNum][i] = new Monster_BinaryDroid(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 43;
        i++;
        
        mapNum = 3;
        i = 0;
        
        i++;
        if(Progress.skeletonLordDefeated == false) {
        	gp.monster[mapNum][i] = new Monster_FireWallOverLord(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;
        i++;
        }
        
    }
    
    	
}
