import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;
import java.awt.event.*; 
import java.util.*;

public class Player{
  
  private int velocity;
  private double angle;
  private BufferedImage sprite;
  private int team;
  private int x;
  private int xa;
  private int y;
  private int ya;
  private int width = 18;
  private int height = 20;
  private boolean movingRight = false;
  private boolean movingLeft = false;
  private Rectangle hitBox = new Rectangle();
  private int jumpTimer = 0;
  private boolean jumping = false;
  
  public Player (int x, int y, int team){
    this.x = x;
    this.y = y;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    this.team = team;
    
    try {
      sprite = ImageIO.read(new File("Char.png"));
    } catch (IOException e) {
    }    
  }
  
  public void move(){
    if(movingLeft){
      xa = -1;
    } 
    else if (movingRight){
      xa = 1;
    }
    else{
      xa = 0;
    }
    
    if(movingRight&&movingLeft){
      xa = 0;
    }
    ya = 2;
    if(jumping){
      ya = -3;
      jumpTimer++;
    }
    if(jumpTimer>10){
      jumping = false;
      jumpTimer = 0;
    }
    x += xa;
    y += ya;
    hitBox.x = x;
    hitBox.y = y;
  }
  
  public void collide(ArrayList<Wall> toCollide){
    for(int i = 0; i < toCollide.size(); i++){
      if(hitBox.intersects((toCollide.get(i)).getHitBox())){
        Rectangle intersection = hitBox.intersection(toCollide.get(i).getHitBox());
        if(intersection.height>ya+1){
          if(toCollide.get(i).getX()<x){
            adjustX(intersection.width);
          }
          else{
          adjustX(-intersection.width);
          }
        }
        else if (intersection.width>2){
          adjustY(intersection.height);
        }
        
      }
    }   
  }
  
  public void paint(Graphics2D g2d){
    g2d.drawImage(sprite,x,y,null);
    
    g2d.setColor(Color.GREEN);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    g2d.fill(hitBox);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
  }
  
  public void keyPressed(KeyEvent e){
    if (e.getKeyCode() == KeyEvent.VK_A){
      movingLeft = true;
    }
    else if (e.getKeyCode() == KeyEvent.VK_D){
      movingRight = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE){
      jumping = true;
    }
  }
  
  public void keyReleased(KeyEvent e){
    if (e.getKeyCode() == KeyEvent.VK_A){
      movingLeft = false;
    }
    else if (e.getKeyCode() == KeyEvent.VK_D){
      movingRight = false;
    }
  }
  public void adjustY(int toAdjust){
    y-=toAdjust;
    hitBox.y=y;
  }
  public void adjustX(int toAdjust){
    x+=toAdjust;
    hitBox.x=x;
  }
  public Rectangle getHitBox(){
    return hitBox;
  }
}