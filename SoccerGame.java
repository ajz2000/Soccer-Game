import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;

public class SoccerGame extends JPanel{
  
  private int screenWidth;
  private int screenHeight; 
  private Level currentLevel = null;;
  private int scaleRatio = 3;
  private ArrayList<Wall> wallList= new ArrayList<Wall>();
  private Player player1 = new Player(100,100,1);
  static boolean debugOn = false;
  
  public SoccerGame(){
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyReleased(KeyEvent e) {
      player1.keyReleased(e);
      }
      @Override
      public void keyPressed(KeyEvent e) {
        player1.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_Z){
        SoccerGame.debugOn = !SoccerGame.debugOn;
        }
      }
    });
    setFocusable(true); 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenWidth = (int)screenSize.getWidth();
    screenHeight = (int)screenSize.getHeight();
    currentLevel = new Level("level",this);
  }
  
  public void move(){
    player1.move();
    player1.collide(wallList);

  }
  
  @Override
  public void paint(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    g2d.scale(scaleRatio,scaleRatio);
    currentLevel.paint(g2d);
    for(int i = 0; i < wallList.size(); i++){
      wallList.get(i).paint(g2d);
    }
    player1.paint(g2d);
  }
   
  public void addWall(Wall toAdd){
    wallList.add(toAdd);
  }
  
  public static void main(String[] args) throws InterruptedException, IOException {
    JFrame frame = new JFrame("Soccer Game");
    SoccerGame s = new SoccerGame();
    frame.add(s);
    
    //set the window size to match the screen size
    frame.setSize(s.currentLevel.getWidth()*s.scaleRatio,s.currentLevel.getHeight()*s.scaleRatio);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    while (true){
      s.repaint();
      s.move();
      Thread.sleep(10);
    }
  }
  
}