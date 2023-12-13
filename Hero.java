import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hero extends MovingThing{
    private int speed;
    private Image image;   
    private int health;

    public Hero(int x, int y, int w, int h, int s)
  {
    super(x, y, w, h);
    speed=s;
    health = 10;
    try
    {
      URL url = getClass().getResource("Dog_knight.png");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
      System.out.println("Hero failed to load");
      //feel free to do something here
    }
  }

  public int getHealth(){
    return health;
  }

  public void setHealth(int h){
    health = h;
  }

  public void setSpeed(int s)
  {
    speed = s;
    //add more code
  }

  public int getSpeed()
  {
    return speed;
  }

  public void move(String direction){};

  public void draw( Graphics window )
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }


}
