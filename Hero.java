import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hero extends MovingThing implements Health{
    private int speed;
    private Image image;   
    private Sound sound;
    private int health;

  public Hero(int x, int y, int w, int h, int s){
    super(x, y, w, h);
    speed=s;
    health = 10;
    sound = new Sound();
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
    if(health==0){
      // game over
      health = 0;
    }else if (health<0){
      health = 0;
    }else{
      health = h;
    }
  }

  public void setSpeed(int s){
    speed = s;
    //add more code
  }

  public int getSpeed(){
    return speed;
  }

  public void move(String direction){};

  public void draw( Graphics window ){
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }

  public void swordSwing(){
    sound.setFile(2);
    sound.setVolume(0.7f);
    sound.play();
  }
}
