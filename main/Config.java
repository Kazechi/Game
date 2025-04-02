
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    
    GamePanel gp;
    
    public Config(GamePanel gp) {
        this.gp = gp;
    }
    
    public void saveConfig() {
        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"));
            
            //FULL SCREEN
            if(gp.fullScreenOn == true) {
                bw.write("On");
            }
            if(gp.fullScreenOn == false) {
                bw.write("Off");
            }
            
            bw.newLine();
            
            bw.close();
            
            
        }catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    public void loadConfig() {
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader("Config.txt"));
            
            String s = br.readLine();
            
            //FULL SCREEN
            if(s.equals("On")) {
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")) {
                gp.fullScreenOn = false;
            }
            
        }catch(Exception e) {
            
        }
        
    }
    
    
}
