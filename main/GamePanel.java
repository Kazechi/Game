package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import entity.SchoolLogo;
import environment.EnvironmentManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import tile.Map;
import tile.TileManage;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
public class GamePanel extends JPanel implements Runnable {

    // Screen Setting
    final int origTileSize = 16; //16 x 161 tiles
    final int scale = 3;
    public final int tileSize = origTileSize * scale; // 48 x 48 tile
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int maxScreenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int maxScreenHeight = tileSize * maxScreenRow; // 576 pixels

    //FOR FULL SCREEN
    public int screenWidth = maxScreenWidth;
    public int screenHeight = maxScreenHeight;

    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    //FPS
    final int FPS = 60;

    // World Setting
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;

    //SCREEN SETTING
    public final int worldWitdh = tileSize * maxWorldCol; // 2,400 pixel
    public final int worldHeight = tileSize * maxWorldRow; // 2,400 pixel

    // System that will function the whole game
    public TileManage tileManager = new TileManage(this); //Object for creating tiles
    public Sound music = new Sound();
    public Sound se = new Sound();
    public Handler keyH = new Handler(this); //object for key
    public SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    public EnvironmentManager eManager = new EnvironmentManager(this);
    public CollisionChecker colliCheck = new CollisionChecker(this);//detect the bumping of the char in an object
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Event event = new Event(this);
    Map map = new Map(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SchoolLogo sLogo = new SchoolLogo(this);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity accessory[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    public Entity projectile[][] = new Entity[maxMap][20];
//    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //Game State for Play and Pause
    public int gameState;
    public final int TITLE = 0;
    public final int PLAY = 1;
    public final int PAUSE = 2;
    //Game State for Dialogues
    public final int DIALOGUE = 3;
    public final int CHARACTER = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutSceneState = 11;

    //OTHERs
    public boolean bossBattleOn = false;
    //AREA
    public int currentArea;
    public int nextArea;
    public final int outdoor = 50;
    public final int indoor = 51;
    public final int dungeon = 52;

    // set;s the players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    private Image backgroundImage;
    public GamePanel() {
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
    this.setDoubleBuffered(true);
    
    // Load the background image
    String imagePath = "/LogoBackgroundImages/Background.png";
    URL imageUrl = getClass().getResource(imagePath);
    backgroundImage = new ImageIcon(imageUrl).getImage();
    
    this.addKeyListener(keyH);
    this.setFocusable(true);
    }
    
//    public void playMusic(int i) {
//        music.setFile(i);
//        music.play();
//        music.loop();
//    }
//
//    public void stopMusic() {
//        music.stop();
//    }
//
//    public void playSE(int i) {
//        se.setFile(i);
//        se.play();
//
//    }

    public void setUpGame() {
        aSetter.setObj();
        aSetter.setNPC();
        aSetter.setMonster();
        eManager.setUp();

        gameState = TITLE;
        currentArea = outdoor;
        //play the music
//        playMusic(0);
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if (fullScreenOn == true) {
            setFullScreen();
        }

    }
    public void retry(boolean restart) {
        currentArea = outdoor;
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();
        
        if(restart == true){
            player.setDefaultPositions();
            aSetter.setObj();
        }
    }

    public void restart() {
    	currentArea = outdoor;
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultValue();
        player.setItems();
        aSetter.setObj();
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void setFullScreen() {

        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth = Main.window.getWidth();
        screenHeight = Main.window.getHeight();

    }

    public void gameStartThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //0.01666 seconds

        double delta = 0;

        long lastTime = System.nanoTime();

        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen(); //DRAW EVERYTHING TO THE BUFFERED SCREEN
                drawToScreen(); //DRAW THE BUFFERED IMAGE TO THE SCREEN
                delta--;
                drawCount++;
            }
            // check the number of FPS
            if (timer >= 1000000000) {
                /*
                System.out.println("FPS " + drawCount);//print the number of FPS getting
                 */
                drawCount = 0;
                timer = 0;
            }
        }

        update();
        repaint();
    }

    public void update() {

        if (gameState == PLAY) {
            //Update the player location
            player.update();
            //THIS WILL MAKE THE NPC'S MONSTERS TO MOVE
            /*
                //NPC
                for(int i = 0; i < npc[1].length; i++) {
                 if(npc[currentMap][i] != null) {
                     npc[currentMap][i].update();
                     
                 }
             }
             */
            //MONSTER
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
//                     monster[i].update();
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }

                }
            }
            //PROJECTILE
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive == true) {
                        projectile[currentMap][i].update();
                    }
                    if (projectile[currentMap][i].alive == false) {
                        projectile[currentMap][i] = null;
                    }

                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
//                     monster[i].update();
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }

                }
            }

            eManager.update();
        }
        if (gameState == PAUSE) {
            //nothing to be put yet
        }
    }

    public void drawToTempScreen() {
        //Title Screen
        if (gameState == TITLE) {
            ui.draw(g2);
        } //MAP SCREEN
        else if (gameState == mapState) {
            map.drawFullMapScreen(g2);
        } //Other variables
        else {
//                int playerY = player.worldY;

            // Paint / Print the TILE
            tileManager.draw(g2);

            entityList.add(player);
            // Paint / Print the OBJECT
            // This will check if the slot is empty
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            // Paint the NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            //Paint the Monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            //PAINT PROJECTILE
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            //PAINT FOR PARTICLES
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            //SORTING
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            //EMPTHY ENTITY LIST
            entityList.clear();

            // Paint / Print the CHARACTER
            player.draw(g2);
            //ENVIRONMENT
            eManager.draw(g2);
            //MINI MAP
            map.drawMiniMap(g2);

            //CUTSCENE
            csManager.draw(g2);

            
            

            // UI (User Interface)
            ui.draw(g2);

        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth, screenHeight, null);
        g.dispose();
    }

    public void changeArea() {
        currentArea = nextArea;
        
        if(nextArea != currentArea) {
//        	stopMusic();
        	if(nextArea == outdoor) {
//        		playMusic(0);
        	}if(nextArea == indoor) {
//        		playMusic(15);
        	}
        	if(nextArea == dungeon) {
//        		playMusic(16);
        	}
        }
        
        aSetter.setMonster();
    }

    public void removeTempEntity() {
        for (int mapNum = 0; mapNum < maxMap; mapNum++) {

            for (int i = 0; i < obj[1].length; i++) {

                if (obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
                    obj[mapNum][i] = null;
                }
            }
        }
    }
}
