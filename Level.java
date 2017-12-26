import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;
import java.awt.*;

public class Level{
  
  private BufferedImage levelImage = null;
  private char[][] levelArray = null;
  private int width;
  private int height;
  private SoccerGame sg;
  
  public Level(String levelName, SoccerGame toAdd){  
    sg = toAdd;
    try {
      levelImage = ImageIO.read(new File(levelName + ".png"));
    } catch (IOException e) {
    }
    width = levelImage.getWidth();
    height = levelImage.getHeight();
    loadCollision(levelName);
  }
  
  public void paint(Graphics2D g2d){
    g2d.drawImage(levelImage,0,0,null);
  }
  
  public void loadCollision(String levelName){
    
    int tileHeight = 0;
    int tileWidth = 0;
    int currentLine = 0;
    String line = "";
    
    try{
      FileReader fr = new FileReader(levelName + ".txt");
      LineNumberReader lnr = new LineNumberReader(fr);
      
      
      while((line = lnr.readLine()) != null){
        tileHeight++;
        tileWidth = line.length();        
      }

      levelArray = new char[tileHeight][tileWidth];
      line = "";
      
      FileReader fr2 = new FileReader(levelName + ".txt");
      BufferedReader br = new BufferedReader(fr2);
      
      while ((line=br.readLine()) != null){
        for(int i = 0; i<= tileWidth-1; i++){
          levelArray[currentLine][i] = line.charAt(0);
          line = line.substring(1);
        }
        currentLine++;
      }
            
      lnr.close();
      br.close();
    } catch (Exception e){
      System.out.println("Error on collision load");
    }
    for(int i = 0; i <= tileHeight-1; i++){
      for(int j = 0; j <= tileWidth-1; j++){
        if(levelArray[i][j] == 'x'){
              sg.addWall(new Wall(j*32,i*32,32,32));
        }
      }
    }
    
  }
  
  public int getHeight(){
    return height;
  }
  
  public int getWidth(){
    return width;
  }
}