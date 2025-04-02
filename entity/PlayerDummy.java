package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{
	public static final String npcName = "Dummy";
	
	
	public PlayerDummy(GamePanel gp) {
		super(gp);
		
		name = npcName;
		getPlayerImage();
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
}

