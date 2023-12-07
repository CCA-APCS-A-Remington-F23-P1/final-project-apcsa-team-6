import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingThing implements Moveable, Collideable<MovingThing>
{
  private int xPos;
  private int yPos;
  private int width;
  private int height;

  public MovingThing()
  {
    xPos = 10;
    yPos = 10;
    width = 10;
    height = 10;
  }

  public MovingThing(int x, int y)
  {
    xPos = x;
    yPos = y;
    width = 10;
    height = 10;
  }

  public MovingThing(int x, int y, int w, int h)
  {
      xPos = x;
      yPos = y;
      width = w;
      height = h;
  }

  public void setPos( int x, int y)
  {
      xPos = x;
      yPos = y;
  }

  public void setX(int x)
  {
      xPos = x;
  }

  public void setY(int y)
  {
      yPos = y;
  }

  public int getX()
  {
      return xPos;
  }

  public int getY()
  {
    return yPos;
  }

  public void setWidth(int w)
  {
      width = w;
  }

  public void setHeight(int h)
  {
      height = h;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public abstract void move(String direction);
  public abstract void draw(Graphics window);

    private boolean segmentsOverlap(int s1, int e1, int s2, int e2){
        if(s1 < s2){
            return e1 >= s2;
        } else{
            return e2 >= s1;
        }
    }

    public boolean didCollide(MovingThing a){
        return segmentsOverlap(getX(), getX()+getWidth(), a.getX(), a.getX()+a.getWidth()) &&
            segmentsOverlap(getY(), getY()+getHeight(), a.getY(), a.getY()+a.getHeight());
    }


  public String toString()
  {
    return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
  }
}
