import java.awt.*;

public class Wall{
  
  private Rectangle hitBox = new Rectangle();

  public Wall(int x, int y, int width, int height){
    hitBox.x=x;
    hitBox.y=y;
    hitBox.width = width;
    hitBox.height = height;
  }
  
  public void paint(Graphics2D g2d){
    
    g2d.setColor(Color.RED);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    g2d.fill(hitBox);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
  }
  
    public Rectangle getHitBox(){
    return hitBox;
  }
    
    public int getX(){
      return hitBox.x;
    }
    public int getY(){
      return hitBox.y;
    }
    public int getHeight(){
      return hitBox.height;
    }
    public int getWidth(){
      return hitBox.width;
    }
}