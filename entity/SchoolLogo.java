package entity;

import main.GamePanel;

public class SchoolLogo extends Entity{
	public SchoolLogo(GamePanel gp) {
		 super(gp);
	        this.gp = gp;
	}
	 public void schoolLogo() {
	    	down1 = setUp("/schoolLogo/LOGO", gp.tileSize, gp.tileSize);
	    }

}
