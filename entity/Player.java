package entity;

import static Calculator.CountVowels.countVowels;
import static Calculator.Sum.calculateSum;
import static Calculator.Sum.sum;
import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import main.Handler;
import main.GamePanel;
import object.Obj_Fireball;
import object.Obj_Lantern;
import object.Obj_Shield;
import object.Obj_Weapon_Sword;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player extends Entity {

    Handler keyH;
    public final int screenX;
    public final int screenY;
    public int getItem = 0;
    public boolean attackCancel = false;
    public int consumable;
    public boolean lightUpdated = false;
    String user1, user2, user3, user4, user5, user6, user7;
    Matcher match1, match2, match3, match4, match5, match6, match7;
    String pattern1, pattern2, pattern3, pattern4, pattern5;
    Pattern regex1, regex2, regex3, regex4;
    boolean patternMatched1, patternMatched2, patternMatched3;
    String message;
    private String input1;
    private double input2;
    private int input3;
    private boolean match;

    public Player(GamePanel gp, Handler keyH) {
        super(gp);//calling the gamepanel that will inherit the "super" class
        this.keyH = keyH;

        // Print the screen to center
        screenX = gp.maxScreenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.maxScreenHeight / 2 - (gp.tileSize / 2);
        //SOLID AREA
        area = new Rectangle();
        area.x = 8;
        area.y = 16;
        solidAreaX = area.x;
        solidAreaY = area.y;
        area.width = 32;
        area.height = 32;
        /*
        //ATTACK AREA
        attackArea.width = 36;
        attackArea.height = 36;
         */
        projectile = new Obj_Fireball(gp);

        setDefaultValue();
        getPlayerImage();
        getPlayerAttack();
        setItems();
        setDialogue();
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentSword);
        inventory.add(currentShield);
    }

    public void setDialogue() {
        dialogues[0][0] = "You have Level Up!";
    }

    public void setDefaultValue() {

        //Player Status / Health Points
        maxLife = 8;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        // print the player, from starter
        worldX = gp.tileSize * 29;
        worldY = gp.tileSize * 42;

        //print the playe near the dungeon entrance
//        worldX = gp.tileSize * 9;
//        worldY = gp.tileSize * 11;
        //PLAYER STATUS
        level = 1;
        strength = 2; //The more stregnth he has the more damage he gives
        dexterity = 1;//The more dexterity he has the more defense he gets
        exp = 0;
        nextLevelExp = 5;
        defaultSpeed = 3;
        speed = defaultSpeed;
        coin = 100;
        
        currentSword = new Obj_Weapon_Sword(gp);
        currentShield = new Obj_Shield(gp);
//        currentShield = new Obj_Lantern(gp);
        direction = "up";

        attack = getAttack(); //Get the total attack value
        defense = getDefense(); //Get the total defense value
    }

    public void setDefaultPositions() {
        gp.currentMap = 0;
        worldX = gp.tileSize * 29;
        worldY = gp.tileSize * 42;
        direction = "up";
    }

    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        lightUpdated = false;
        invincible = false;
    }

    public int getAttack() {
        attackArea = currentSword.attackArea;
        return attack = strength * currentSword.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentSword) {
                currentWeaponSlot = i;

            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield) {
                currentShieldSlot = i;

            }
        }
        return currentShieldSlot;
    }

    public void getPlayerImage() {
        up1 = setUp("/player/up1", gp.tileSize, gp.tileSize);
        up2 = setUp("/player/up3", gp.tileSize, gp.tileSize);
        down1 = setUp("/player/down1", gp.tileSize, gp.tileSize);
        down2 = setUp("/player/down2", gp.tileSize, gp.tileSize);
        left1 = setUp("/player/Left1", gp.tileSize, gp.tileSize);
        left2 = setUp("/player/Left2", gp.tileSize, gp.tileSize);
        right1 = setUp("/player/Right1", gp.tileSize, gp.tileSize);
        right2 = setUp("/player/right3", gp.tileSize, gp.tileSize);
    }

    public void getSleepImage(BufferedImage img) {
        up1 = img;
        up2 = img;
        up3 = img;
        down1 = img;
        down2 = img;
        down3 = img;
        left1 = img;
        left2 = img;
        left3 = img;
        right1 = img;
        right2 = img;
        right3 = img;
    }

    /*
    public void getDeadImage() {
        up1 = setUp("/playerDead_img/mikadead2", gp.tileSize, gp.tileSize);
        up2 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);

        down1 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);
        down2 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);

        left1 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);
        left2 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);

        right1 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);
        right2 = setUp("/playerDead_img/mikadead2",gp.tileSize, gp.tileSize);
    }
     */
    public void getPlayerAttack() {
//        atkUp1 = setUp("/playerAttack/AtkUp1", gp.tileSize, (int) (gp.tileSize * 1.9));
//        atkUp2 = setUp("/playerAttack/AtkUp2", gp.tileSize, (int) (gp.tileSize * 1.9));
        
        atkDown1 = setUp("/playerAttack/AtkDown1", gp.tileSize, gp.tileSize * 2);
        
        
        atkUp1 = setUp("/playerAttack/ATKup1_1", gp.tileSize, (int) (gp.tileSize * 2.8));
        atkUp2 = setUp("/playerAttack/ATKup2_1", gp.tileSize, (int) (gp.tileSize * 2.8));
        
        
        atkDown2 = setUp("/playerAttack/AtkDown2", gp.tileSize, gp.tileSize * 2);
        atkRight1 = setUp("/playerAttack/AtkRight1", gp.tileSize * 2, gp.tileSize);
        atkRight2 = setUp("/playerAttack/AtkRight2", gp.tileSize * 2, gp.tileSize);
        atkLeft1 = setUp("/playerAttack/AtkLeft1", gp.tileSize * 2, gp.tileSize);
        atkLeft2 = setUp("/playerAttack/AtkLeft2", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {

        if (attacking == true) {
            attacking();
        }

        // Move only when the key is being press
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed || keyH.rightPressed == true
                || keyH.arrowUpPressed == true || keyH.arrowDownPressed == true || keyH.arrowRightPressed == true
                || keyH.arrowLeftPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }

            if (keyH.arrowUpPressed == true) {
                direction = "up";
            }
            if (keyH.arrowDownPressed == true) {
                direction = "down";
            }
            if (keyH.arrowRightPressed == true) {
                direction = "right";
            }
            if (keyH.arrowLeftPressed == true) {
                direction = "left";
            }

            // Check tile collision
            colliOn = false;
            gp.colliCheck.checkTile(this);

            // Check Object Collision
            int objIndex = gp.colliCheck.checkObj(this, true);
            pickObj(objIndex);

            //CHECK NPC COLLISION
            int indexNPC = gp.colliCheck.checkEntity(this, gp.npc);
            interactNPC(indexNPC);

            //CHECK EVENT
            gp.event.checkEvent();
//            gp.keyH.enterPressed = false;

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.colliCheck.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // if collision is false, player can move
            if (colliOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;

                }
            }

            if (keyH.enterPressed == true && attackCancel == false) {
                attacking = true;
                charCounter = 0;
                currentSword.durability--;
            }

            attackCancel = false;
            gp.keyH.enterPressed = false;
            // make the character move smoothly
            charCounter++;
            if (charCounter > 15) {
                if (charNum == 1) {
                    charNum = 2;
                } else if (charNum == 2) {
                    charNum = 1;
                }
                charCounter = 0;
            }

        }
        if (gp.keyH.shotKeyPressed == true && projectile.alive == false
                && shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
            // SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            projectile.subtractResource(this);
//            gp.playSE(10);

            //CHECK VACANCY
            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }

        //check the tile
        gp.colliCheck.checkTile(this);

        // Needs to be outside of the if else
        //check after hit
        //making no continous damage
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (life > maxLife) {
            life = maxLife;
        }
        if (mana > maxMana) {
            mana = maxMana;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
//            getDeadImage();
        }

    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.DIALOGUE;
                attackCancel = true;
                gp.npc[gp.currentMap][i].speak();
            } else {
                attacking = false;
            }
        }

    }

    // Interactive object
    public void pickObj(int index) {
        //should be the same value as the default value of the index
        //Collision Checker Class
        //checkObj method
        if (index != 999) {
            speed = defaultSpeed;
            String objName = gp.obj[gp.currentMap][index].name; //FIXED

            //PICK UP ONLY ITEMS
            String text = "";
            if (gp.obj[gp.currentMap][index].type == type_pickupOnly) { //FIXED
//                gp.playSE(1);
                gp.obj[gp.currentMap][index].use(this); //FIXED
                gp.obj[gp.currentMap][index] = null; //FIXED

            } //OBSTACLE
            else if (gp.obj[gp.currentMap][index].type == type_obstacle) {
                if (keyH.enterPressed == true) {
                    gp.obj[gp.currentMap][index].interact();
                }
            } //INVENTORY ITEMS
            else {

                if (canObtainItem(gp.obj[gp.currentMap][index]) == true) {
                    switch (objName) {
                        case "Lantern": // "Key" is the variable value inside the Obj_Key class
                            //getting the user input
                            String lantern = JOptionPane.showInputDialog(null, "prints the string \"Hello world\" on screen");
                            // Check if the user input is align to the default String
                            //ANSWER
                            //System.out.print("Hello world");
                            if(lantern != null && !lantern.isEmpty()){
                            pattern1 = "System\\.out\\.(print|println)\\s*\\(\\s*\"(.*)\"\\s*\\);";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(lantern);
                            if (match1.matches()) {
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text =  "Key Acquired!"; //FIXED
                                speed = defaultSpeed;
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                            }else{
                                JOptionPane.showMessageDialog(null, "Please provide an input.");
                                break;
                            }
                            break;

                        case "Key of Variables": // "Key" is the variable value inside the Obj_Key class
                            String key1 = JOptionPane.showInputDialog(null, "Make a Java Programm that will add "
                                    + "\nAlways remember the variable name that your are using"
                                    + "\nInput \"Enter\" to proceed");

                            String input1 = JOptionPane.showInputDialog("Enter the first variable declaration:");
                            String input2 = JOptionPane.showInputDialog("Enter the second variable declaration:");
                        if(input1 != null && input2 != null && !input1.isEmpty() &&!input2.isEmpty()){
                            // Regular expression pattern to match variable declaration
                            pattern1 = "^(int|double)\\s+(\\w+)\\s*=\\s*([\\d.]+);$";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(input1);

                            if (match1.matches()) {
                                String variableType1 = match1.group(1);
                                String variableName1 = match1.group(2);
                                String valueStr1 = match1.group(3);

                                // Convert value to double
                                double value1 = Double.parseDouble(valueStr1);

                                // Process the second variable declaration
                                match2 = regex1.matcher(input2);
                                if (match2.matches()) {
                                    String variableType2 = match2.group(1);
                                    String variableName2 = match2.group(2);
                                    String valueStr2 = match2.group(3);

                                    // Convert value to double
                                    double value2 = Double.parseDouble(valueStr2);

                                    // Perform the addition of the two values
                                    double sum = value1 + value2;

                                    JOptionPane.showMessageDialog(null, "Variable 1:\nType: " + variableType1 + "\nName: " + variableName1 + "\nValue: " + value1
                                            + "\n\nVariable 2:\nType: " + variableType2 + "\nName: " + variableName2 + "\nValue: " + value2
                                            + "\n\nSum: " + sum, "Variable Declaration and Sum", JOptionPane.INFORMATION_MESSAGE);
//                                    gp.playSE(1);
                                    speed = defaultSpeed;
                                    text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                    gp.obj[gp.currentMap][index] = null; //FIXED
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid second variable declaration. Please enter in the format 'int/double variableName = value;'", "Input Validation", JOptionPane.ERROR_MESSAGE);
                                }
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid first variable declaration. Please enter in the format 'int/double variableName = value;'", "Input Validation", JOptionPane.ERROR_MESSAGE);
                            break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Please provide an input.");
                        }
                            break;

                            
                        case "Key of Data Types": // "Key" is the variable value inside the Obj_Key class
                            //getting the user input
                            String key2 = JOptionPane.showInputDialog(null, "Complete the code to declare two variables of "
                                    + "type int and print their sum using the sum variable.\n"
                                    + "(1)_________ name;"
                                    + "name = \"Roi\""
                                    + "(2)_____________.out.print(_______);(3)");
                            // Check if the user input is align to the default String
                            // ANSWER
                            // String, System, name
                            if (("String, System, name").equals(key2) || ("String,System,name".equals(key2))) {
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = "Key Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                            break;

                        case "Key of Operators": // "Key" is the variable value inside the Obj_Key class
                            //getting the user input
                            String key3 = JOptionPane.showInputDialog(null, "Write a Java program to calculate and "
                                    + "print the average of three numbers. \nYou must use the variable type "
                                    + "double since we are finding the average "
                                    + "\nAlways remember the variable name that your are using"
                                    + "\nInput \"Enter\" to proceed");
                            // Check if the user input is align to the default String
                            user1 = JOptionPane.showInputDialog(null, "Create the First Value");
                            // double first = 10.1;
                            user2 = JOptionPane.showInputDialog(null, "Create the Second Value");
                            // double second = 11.1;
                            user3 = JOptionPane.showInputDialog(null, "Create the Third Value");
                            // double third = 12.1;
                            user4 = JOptionPane.showInputDialog(null, "Create the average statement");
                            // double ave = (a + b + c) / 3;
                            user5 = JOptionPane.showInputDialog(null, "Print the average of the three numbers");
                            // System.out.println(ave);
                        if(user1 != null && user2 != null && user3 != null && user4 != null&& user5 != null
                               && !user1.isEmpty() && !user2.isEmpty() && !user3.isEmpty() && !user4.isEmpty() && !user5.isEmpty()){
                            pattern1 = "double [a-zA-Z_][a-zA-Z0-9_]* = \\d+(\\.\\d+)?;";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(user1);
                            match2 = regex1.matcher(user2);
                            match3 = regex1.matcher(user3);

                            pattern2 = "double\\s+\\w+\\s*=\\s*\\(\\s*\\w+\\s*\\+\\s*\\w+\\s*\\+\\s*\\w+\\s*\\)\\s*/\\s*3\\s*;";
                            regex2 = Pattern.compile(pattern2);
                            match4 = regex2.matcher(user4);

                            pattern3 = "System\\.out\\.(println|print)\\(.*\\);";
                            regex3 = Pattern.compile(pattern3);
                            match5 = regex3.matcher(user5);

                            if (("Enter").equals(key3)
                                    && match1.matches()
                                    && match2.matches()
                                    && match3.matches()
                                    && match4.matches()
                                    && match5.matches()) {
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = "Key Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Please provide an input.");
                        }
                            break;

                        case "Key of Java Methods": // "Key" is the variable value inside the Obj_Key class
                            //getting the user input
                            String blueKey1 = JOptionPane.showInputDialog(null, "declare an integer, and pass it as a "
                                    + "parameter to the test() method. "
                                    + "\nAlways remember the variable name that your are using"
                                    + "\nInput \"Enter\" to proceed");

                            // Check if the user input is align to the default String
                            user1 = JOptionPane.showInputDialog(null, "Declare an integer");
                            // int x = 1;

                            user2 = JOptionPane.showInputDialog(null, "Create the test() method");
                            // static void test(int x) { System.out.println(x); }

                            user3 = JOptionPane.showInputDialog(null, "Pass it as a parameter in the test() method");
                            // test(x);
                            
                        if(user1 != null && user2 != null && user3 != null
                               && !user1.isEmpty() && !user2.isEmpty() &&!user3.isEmpty()){
                            
                            // check the patterns of the user inputs
                            pattern1 = "int [a-zA-Z_][a-zA-Z0-9_]* = \\d+;";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(user1);

                            pattern2 = "^\\s*static\\s+void\\s+test\\(\\s*int\\s+.*\\)\\s*\\{\\s*System\\.out\\.(println|print)\\(\\s*.*\\s*\\);\\s*\\}\\s*$";
                            regex2 = Pattern.compile(pattern2);
                            match2 = regex2.matcher(user2);

                            pattern3 = "^test\\(.*\\);$";
                            regex3 = Pattern.compile(pattern3);
                            match3 = regex3.matcher(user3);

                            if (("Enter").equals(blueKey1)
                                    && match1.matches()
                                    && match2.matches()
                                    && match3.matches()) {
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                            break;
                        }else{
                            JOptionPane.showMessageDialog(null, "Please provide an input.");
                        }
                        break;
                        
                        
                        case "Key of Java Packages": // "Key" is the variable value inside the Obj_Key class
                            //getting the user input
                            String blueKey2 = JOptionPane.showInputDialog(null, "What outputs results from this code?"
                                    + "public static void main(String[] args){"
                                    + "\nint x = 10;"
                                    + "\nint y = myFunc(y);"
                                    + "\nSystem.out.println(y);}"
                                    + "\npublic static int myFunc(int x){"
                                    + "\nreturn x*3;}");
                            // Check if the user input is align to the default String
                            if (("30").equals(blueKey2)) {
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                            break;

                        case "Key of Java Classes":
                            String blueKey3 = JOptionPane.showInputDialog(null, "This is the Main Class"
                                    + "\nCall the method \"hello\" from main:"
                                    + "\nstatic void hello(){System.out.println(\"Hi\"};");

                            if (("hello();").equals(blueKey3)) {
                                JOptionPane.showMessageDialog(null, "Hi");
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                            break;

                            
                        case "Key of Java Conditionals":
                            String blueKey4 = JOptionPane.showInputDialog(null, "Print Numbers: Write a program that prints "
                                    + "\nnumbers from 1 to 10 on separate lines."
                                    + "\nInput \"Enter\" to proceed");
                            // ANSWER
                            // for   ( int i = 0; i < 10; i++ )   {   System.out.println(i);   }
                            user1 = JOptionPane.showInputDialog("Code the loop");
                            if(user1 != null && !user1.isEmpty()){
                            pattern1 = "^\\s*for\\s*\\(\\s*int\\s+\\w+\\s*=\\s*\\d+\\s*;\\s*\\w+\\s"
                                    + "*(?:<|<=)\\s*\\d+\\s*;\\s*\\w+\\+\\+\\s*\\)\\s*\\{\\s*System\\"
                                    + ".out\\.println\\(\\w+\\)\\s*;\\s*\\}\\s*$";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(user1);

                            if (("Enter").equals(blueKey4) && match1.matches()) {
                                String loopType = user1.replaceAll("\\s+", "").split("\\(")[0];
                                String loopCondition = user1.substring(user1.indexOf('(') + 1, user1.indexOf(')')).trim();
                                String loopBody = user1.substring(user1.indexOf('{') + 1, user1.lastIndexOf('}')).trim();

                                String output = "Loop Type: " + loopType + "\n"
                                        + "Loop Condition: " + loopCondition + "\n"
                                        + "Loop Body: " + loopBody;

                                JOptionPane.showMessageDialog(null, output, "Loop Statement Details", JOptionPane.INFORMATION_MESSAGE);
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                    }else{
                            JOptionPane.showMessageDialog(null, "Please provide an input.");
                            }
                        
                            break;

                            
                        case "Key of Java Arrays":
                            String blueKey5 = JOptionPane.showInputDialog(null, "Write a Java program that initializes "
                                    + "an array of integers and finds the average of all the elements in the array"
                                    + "\nint[] arr = {10, 5, 8, 12, 6};"
                                    + "\nAlways remember the variable name that your are using"
                                    + "\nInput \"Enter\" to proceed");
                            // ANSWER
                            user1 = JOptionPane.showInputDialog(null, "Declare an integer");
                            // int sum = 0;
                            user2 = JOptionPane.showInputDialog("Enter the loop statement:");
                            // for (int i = 0; i < arr.length; i++) { sum += arr[i]; }
                            
                            if(user1 != null && user2 != null
                                    && !user1.isEmpty() && !user2.isEmpty()){
                            pattern1 = "int [a-zA-Z_][a-zA-Z0-9_]* = 0;";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(user1);

                            pattern2 = "^\\s*"
                                    + "(for|while|do\\s*\\.\\.\\s*while)"
                                    + // Matches loop type: for, while, or do...while
                                    "\\s*\\((.*?)\\)\\s*"
                                    + // Matches loop condition within parentheses
                                    "\\{\\s*(.*?)\\s*;?\\s*\\}"
                                    + // Matches loop body within curly braces, allowing for an optional semi-colon
                                    "\\s*$";

                            regex2 = Pattern.compile(pattern2);
                            match2 = regex2.matcher(user2);

                            if (("Enter").equals(blueKey5)
                                    && match1.matches()
                                    && match2.matches()) {
                                String loopType = user2.replaceAll("\\s+", "").split("\\(")[0];
                                String loopCondition = user2.substring(user2.indexOf('(') + 1, user2.indexOf(')')).trim();
                                String loopBody = user2.substring(user2.indexOf('{') + 1, user2.lastIndexOf('}')).trim();

                                String output = "Loop Type: " + loopType + "\n"
                                        + "Loop Condition: " + loopCondition + "\n"
                                        + "Loop Body: " + loopBody;

                                JOptionPane.showMessageDialog(null, output, "Loop Statement Details", JOptionPane.INFORMATION_MESSAGE);
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                            }else{
                                JOptionPane.showMessageDialog(null, "Please provide an input.");
                            }
                            break;

                        case "Key of Java Objects":
                            String blueKey6 = JOptionPane.showInputDialog(null, "Write a Java function called calculateSum that takes two integers as "
                                    + "\nparameters and returns their sum. Then, call the function and print the result."
                                    + "\nAlways remember the variable name that your are using"
                                    + "\n Input \"Enter\" to proceed");

                            user1 = JOptionPane.showInputDialog("Enter the method declaration:");
                            // ANSWER
//                        public static int sum(int num1, int num2) {int result = num1 + num2;return result;}
                            user2 = JOptionPane.showInputDialog("This is the Main Class"
                                    + "\nEnter the value for variable1");
                            user3 = JOptionPane.showInputDialog("This is the Main Class"
                                    + "\nEnter the value for variable2");
                            
                        if(user1 != null && user2 != null && user3 != null &&
                                !user1.isEmpty() && !user2.isEmpty() && !user3.isEmpty()){
                            pattern1 = "^public\\s+static\\s+(int|double)\\s+(\\w+)\\s*\\((int|double)\\s+(\\w+),\\s*(int|double)\\s+(\\w+)\\)\\s*\\{\\s*(int|double)\\s+(\\w+)\\s*=\\s*\\4\\s*\\+\\s*\\6;\\s*\\}$";
                            regex1 = Pattern.compile(pattern1);

                            // Check if the user input matches the pattern
                            match1 = regex1.matcher(user1);

                            // Regular expression pattern
                            pattern2 = "^(int|double)\\s+(\\w+)\\s*=\\s*(\\d+\\.?\\d*|\\d*\\.\\d+)\\s*;$";
                            regex2 = Pattern.compile(pattern2);

                            // Check if variable1 input matches the pattern
                            match2 = regex2.matcher(user2);
                            if (!match2.matches()) {
                                JOptionPane.showMessageDialog(null, "Invalid input for variable1. Please enter in the "
                                        + "correct format.", "Input Validation", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Check if variable2 input matches the pattern
                            match3 = regex2.matcher(user3);
                            if (!match3.matches()) {
                                JOptionPane.showMessageDialog(null, "Invalid input for variable2. "
                                        + "Please enter in the correct format.", "Input Validation", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            if (("Enter").equals(blueKey6)
                                    && match2.matches()
                                    && match3.matches()) {

                                // Extract variable values from input
                                String variable1Type = match2.group(1);
                                String variable1Name = match2.group(2);
                                double variable1Value = Double.parseDouble(match2.group(3));

                                String variable2Type = match3.group(1);
                                String variable2Name = match3.group(2);
                                double variable2Value = Double.parseDouble(match3.group(3));

                                // Compute sum using the provided function
                                double sum = sum(variable1Value, variable2Value);

                                // Display the result
                                JOptionPane.showMessageDialog(null, "The sum of "
                                        + variable1Name + " and " + variable2Name + " is: " + sum,
                                        "Sum Calculation", JOptionPane.INFORMATION_MESSAGE);
//                                gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Please provide an input.");
                        }
                            break;

                        case "Key of Java Inheritance":
                            String blueKey7 = JOptionPane.showInputDialog(null, "Write a program to accept a string from that "
                                    + "\nuser then the program will count the number of vowels using Inheritance."
                                    + "\nInput \"Enter\" to proceed");
                                // FIRST QUESTION
                            user1 = JOptionPane.showInputDialog("Create the Inheritance here:"
                                    + "\nCreate the program that will count the vowels"
                                    + "\nIn this JFrame only input the method that you will use and declare an integer"
                                    + "\nAlways remember the variable name that you are using"
                                    + "\nDon't input the final \"}\" yet, the code will continue");
                            // Regular expression pattern
                            // ANSWER
// public void calculateSum(String input) { int count = 0;
                            
                            // SECOND QUESTION
                            user2 = JOptionPane.showInputDialog("Create the Loop Statement"
                                    + "\nCreate the Condition that will count the Vowels"
                                    + "\nThis will accept even if the given Vowels that you inputted is Upper or Lower Case"
                                    + "\nAlways remember the variable name that you are using"
                                    + "\nDon't end your input with the double \"}\" "
                                    + "\njust put one at the end, the code will continue");
                            //ANSWER
//for(char ch : input.toCharArray()){ if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'){ ++count; }
                            
                            //THIRD QUESTION
                            user3 = JOptionPane.showInputDialog("Print Final output"
                                    + "\nRemember the variable name your are using"
                                    + "\nThis Frame you can already end it with the final \"}\""
                                    + "\nYou must start your statement with \"}\""
                                    + "\nSince the Loop don't didn't get to close yet");
                            
                            // ANSWER
// }     System.out.println(   Hello, World!   )    ; }
// }   System.out.print(anyString);   }
                            
                        if(user1 != null && user2 != null && user2 != null &&
                                !user1.isEmpty() && !user2.isEmpty() && !user3.isEmpty()){
                            pattern1 = "^public\\s+void\\s+(\\w+)\\s*\\(\\s*String\\s+(\\w+)\\s*\\)\\s*\\{\\s*int\\s+(\\w+)\\s*=\\s*0\\s*;\\s*";
                            regex1 = Pattern.compile(pattern1);
                            match1 = regex1.matcher(user1);

                            pattern2 = "^\\s*for\\s*\\(\\s*char\\s+(\\w+)"
                                    + "\\s*:\\s*(\\w+)\\.toCharArray\\"
                                    + "(\\)\\s*\\)\\s*\\{\\s*if\\s*\\"
                                    + "(\\s*([^)]+)\\s*\\)\\s*\\{\\s*\\+\\+"
                                    + "(\\w+)\\s*;\\s*\\}\\s*$";
                            regex2 = Pattern.compile(pattern2);
                            match2 = regex2.matcher(user2);
                            
                            pattern3 = "\\}\\s*System\\.out\\.(println|print)\\s*\\(\\s*([^);\\s]+)\\s*\\)\\s*;\\s*\\}";
                            regex3 = Pattern.compile(pattern3);
                            match3 = regex3.matcher(user3);
                            
                            if (("Enter").equals(blueKey7)
                                    && match1.matches()
                                    && match2.matches()
                                   && match3.matches()
                                    ) {
                                // FOR USER 1
                                // THIS WILL CHECK AND SHOW THE INPUTTED VALUE OF THE USER
                                String methodName = match1.group(1);
                                String parameterName = match1.group(2);
                                String variableName = match1.group(3);

                                message = "User input: " + user1 + "\n"
                                        + "Method Name: " + methodName + "\n"
                                        + "Parameter Name: " + parameterName + "\n"
                                        + "Variable Name: " + variableName;

                            JOptionPane.showMessageDialog(null, message, "Valid Input", JOptionPane.INFORMATION_MESSAGE);
                            
                            // FOR USER 2
                            // THIS WILL CHECK AND SHOW THE INPUTTED VALUE OF THE USER
                            String variable1 = match2.group(1);
                            String variable2 = match2.group(2);
                            String condition = match2.group(3);
                            String incrementVariable = match2.group(4);

                            message = "User input: " + user2 + "\n"
                                    + "Variable 1: " + variable1 + "\n"
                                    + "Variable 2: " + variable2 + "\n"
                                    + "Condition: " + condition + "\n"
                                    + "Increment Variable: " + incrementVariable;

                            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.INFORMATION_MESSAGE);
                        
                            // FOR USER 3
                            // THIS WILL CHECK AND SHOW THE INPUTTED VALUE OF THE USER
                             String inputtedValue = match3.group(2);
                            JOptionPane.showMessageDialog(null, "User input: " + user3 + "\nInputted value: " + inputtedValue);

//                            gp.playSE(1);
                                speed = defaultSpeed;
                                text = gp.obj[gp.currentMap][index].name + " Acquired!"; //FIXED
                                gp.obj[gp.currentMap][index] = null; //FIXED
                            } else {
                                JOptionPane.showMessageDialog(null, "Wrong Answer");
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Please provide an input.");
                        }
                            break;
                    }
                } else {
                    text = "Storage Full!";
                }

            }
            gp.ui.addMessage(text);
//            gp.obj[index] = null;
        }

    }

    private static boolean validateInputs(String loopType, String loopCondition, String loopBody) {
        String loopTypePattern = "(for|while|do\\.\\.\\.while)";
        String loopConditionPattern = ".*";
        String loopBodyPattern = ".*";

        boolean isLoopTypeValid = Pattern.matches(loopTypePattern, loopType);
        boolean isLoopConditionValid = Pattern.matches(loopConditionPattern, loopCondition);
        boolean isLoopBodyValid = Pattern.matches(loopBodyPattern, loopBody);

        return isLoopTypeValid && isLoopConditionValid && isLoopBodyValid;
    }
//GET DAMAGE FROM MONSTER

    public void contactMonster(int i) {
        if (i != 999) {

            if (invincible == false) {

                int damage = gp.monster[gp.currentMap][i].attack - defense;  //FIXED
                if (damage < 0) {
                    damage = 0;
                }
//                gp.playSE(6);
                life -= damage;
                invincible = true;
            }
        }
    }

    //COMPUTING DAMAGE TAKEN
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
        if (i != 999) {

            if (gp.monster[gp.currentMap][i].invincible == false) { //FIXED
//                gp.playSE(5);
                if (knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;  //FIXED
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage; //FIXED
                gp.ui.addMessage(damage + " damage!");
                gp.monster[gp.currentMap][i].invincible = true; //FIXED
                /*
                gp.monster[gp.currentMap][i].damageReaction();//FIXED
                 */
                if (gp.monster[gp.currentMap][i].life <= 0) { //FIXED
                    gp.monster[gp.currentMap][i].dying = true; //FIXED

//                    gp.monster[i] = null;
                    gp.ui.addMessage("You have slain " + gp.monster[gp.currentMap][i].name + "!"); //FIXED
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp); //FIXED
                    exp += gp.monster[gp.currentMap][i].exp;//FIXED
                    checkLevelUp();

                }
            }

        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {

            level++;
            nextLevelExp = nextLevelExp * 2;
            coin = gp.player.level * 3;
            strength++;
            dexterity++;
            maxLife++;
            maxMana++;
            attack = getAttack();
            defense = getDefense();
//            gp.playSE(8);
            startDialogue(this, 0);

        }
    }

    public void selectedItems() {

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {
            Entity selectedItems = inventory.get(itemIndex);

            if (selectedItems.type == type_sword || selectedItems.type == type_sword2) {

                currentSword = selectedItems;
                attack = getAttack();
            }

            if (selectedItems.type == type_shield) {
                currentShield = selectedItems;
                defense = getDefense();
            }
            if (selectedItems.type == type_light) {

                if (currentLight == selectedItems) {
                    currentLight = null;
                } else {
                    currentLight = selectedItems;
                }
                lightUpdated = true;
            }
            if (selectedItems.type == type_consumable) {
                //WILL PUT SOME CONSUMABLE ITEMS 

                if (selectedItems.use(this) == true) {
                    if (selectedItems.amount > 1) {
                        selectedItems.amount--;
                    } else {
                        inventory.remove(itemIndex);
                    }
                }

            }
        }
    }

    public int searchItemInventory(String itemName) {
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        Entity newItem = gp.eGenerator.getObject(item.name);

        //CHECK IF STACKABLE
        if (newItem.stackable == true) {
            int index = searchItemInventory(newItem.name);

            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {//New item so need to check vacancy
                if (inventory.size() != inventorySize) {
                    inventory.add(newItem);
                    canObtain = true;
                }

            }
        } else {//NOT STACKABLE SO CHECK VACANCY
            if (inventory.size() != inventorySize) {
                inventory.add(newItem);
                canObtain = true;
            }

        }
        return canObtain;
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (charNum == 1) {
                        img = up1;
                    }
                    if (charNum == 2) {
                        img = up2;
                    }
                    if (charNum == 3) {
                        img = up3;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (charNum == 1) {
                        img = atkUp1;
                    }
                    if (charNum == 2) {
                        img = atkUp2;
                    }
                }

                break;

            case "down":
                if (attacking == false) {
                    if (charNum == 1) {
                        img = down1;
                    }
                    if (charNum == 2) {
                        img = down2;
                    }
                    if (charNum == 3) {
                        img = down3;
                    }
                }

                if (attacking == true) {
                    if (charNum == 1) {
                        img = atkDown1;
                    }
                    if (charNum == 2) {
                        img = atkDown2;
                    }
                }

                break;
            case "right":
                if (attacking == false) {
                    if (charNum == 1) {
                        img = right1;
                    }
                    if (charNum == 2) {
                        img = right2;
                    }
                    if (charNum == 3) {
                        img = right3;
                    }
                }

                if (attacking == true) {
                    if (charNum == 1) {
                        img = atkRight1;
                    }
                    if (charNum == 2) {
                        img = atkRight2;
                    }
                }

                break;
            case "left":
                if (attacking == false) {
                    if (charNum == 1) {
                        img = left1;
                    }
                    if (charNum == 2) {
                        img = left2;
                    }
                    if (charNum == 3) {
                        img = left3;
                    }
                }

                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (charNum == 1) {
                        img = atkLeft1;
                    }
                    if (charNum == 2) {
                        img = atkLeft2;
                    }
                }
                break;
        }
        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        if (drawing == true) {
            g2.drawImage(img, tempScreenX, tempScreenY, null);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    // MATCHING USING STRING
    public String getInput1() {
        return input1;
    }

    public void setInput1(String input) {
        this.input1 = input;
    }

    public boolean isMatch1() {
        return match;
    }

    public void setMatch1(boolean match) {
        this.match = match;
    }

    //MATCHING USING DOUBLE
    public double getInput2() {
        return input2;
    }

    public void setInput2(double input) {
        this.input2 = input;
    }

    public boolean isMatch2() {
        return match;
    }

    public void setMatch2(boolean match) {
        this.match = match;
    }

    //MATCHING USING INTEGER
    public int getInput3() {
        return input3;
    }

    public void setInput(int input) {
        this.input3 = input;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }
}
