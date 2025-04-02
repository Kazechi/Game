
package main;

import entity.Entity;
import object.Obj_Chest;
import object.Obj_Coin;
import object.Obj_Door1;
import object.Obj_Door2;
import object.Obj_Door3;
import object.Obj_Fireball;
import object.Obj_HP;
import object.Obj_IronDoor1;
import object.Obj_IronDoor2;
import object.Obj_IronDoor3;
import object.Obj_IronDoor4;
import object.Obj_IronDoor5;
import object.Obj_IronDoor6;
import object.Obj_IronDoor7;
import object.Obj_IronDoorCutscene;
import object.Obj_JavaDagger;
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
import object.Obj_ManaCrystal;
import object.Obj_Potion_HP;
import object.Obj_Rock;
import object.Obj_Saving;
import object.Obj_Shield;
import object.Obj_Tent;
import object.Obj_Treasure;
import object.Obj_Weapon_Sword;
import object.Obj_Weapon_Sword2;

public class EntityGenerator {
    
    GamePanel gp;
    
    
    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }
    public Entity getObject(String itemName) {
         
         Entity obj = null;
         
         switch(itemName) {
         
             case Obj_Weapon_Sword2.objName: obj = new Obj_Weapon_Sword2(gp); break;
             case Obj_Weapon_Sword.objName: obj = new Obj_Weapon_Sword(gp); break;
             case Obj_JavaDagger.objName: obj = new Obj_JavaDagger(gp); break;
             case Obj_Shield.objName: obj = new Obj_Shield(gp); break;
             case Obj_Potion_HP.objName: obj = new Obj_Potion_HP(gp); break;
             case Obj_Key1.objName: obj = new Obj_Key1(gp); break;
             case Obj_Key2.objName: obj = new Obj_Key3(gp); break;
             case Obj_Key3.objName: obj = new Obj_Key2(gp); break;
             case Obj_Chest.objName: obj = new Obj_Chest(gp, new Obj_Lantern(gp)); break;
             case Obj_Coin.objName: obj = new Obj_Coin(gp); break;
             case Obj_Door1.objName: obj = new Obj_Door1(gp); break;
             case Obj_Door2.objName: obj = new Obj_Door2(gp); break;
             case Obj_Door3.objName: obj = new Obj_Door3(gp); break;
             case Obj_HP.objName: obj = new Obj_HP(gp); break;
             case Obj_ManaCrystal.objName: obj = new Obj_ManaCrystal(gp); break;
             case Obj_Rock.objName: obj = new Obj_Rock(gp); break;
             case Obj_Saving.objName: obj = new Obj_Saving(gp); break;
             case Obj_Lantern.objName: obj = new Obj_Lantern(gp);break;
             case Obj_Tent.objName: obj = new Obj_Tent(gp);break;
             case Obj_Fireball.objName: obj = new Obj_Fireball(gp);break;
             case Obj_IronDoor1.objName: obj = new Obj_IronDoor1(gp); break;
             case Obj_IronDoor2.objName: obj = new Obj_IronDoor2(gp); break;
             case Obj_IronDoor3.objName: obj = new Obj_IronDoor3(gp); break;
             case Obj_IronDoor4.objName: obj = new Obj_IronDoor4(gp); break;
             case Obj_IronDoor5.objName: obj = new Obj_IronDoor5(gp); break;
             case Obj_IronDoor6.objName: obj = new Obj_IronDoor6(gp); break;
             case Obj_IronDoor7.objName: obj = new Obj_IronDoor7(gp); break;
             case Obj_IronDoorCutscene.objName: obj = new Obj_IronDoorCutscene(gp); break;
             case Obj_KeyBlue1.objName: obj = new Obj_KeyBlue1(gp); break;
             case Obj_KeyBlue2.objName: obj = new Obj_KeyBlue2(gp); break;
             case Obj_KeyBlue3.objName: obj = new Obj_KeyBlue3(gp); break;
             case Obj_KeyBlue4.objName: obj = new Obj_KeyBlue4(gp); break;
             case Obj_KeyBlue5.objName: obj = new Obj_KeyBlue5(gp); break;
             case Obj_KeyBlue6.objName: obj = new Obj_KeyBlue6(gp); break;
             case Obj_KeyBlue7.objName: obj = new Obj_KeyBlue7(gp); break;
             case Obj_Treasure.objName:obj = new Obj_Treasure(gp); break;
         }
         return obj;
     }
}
