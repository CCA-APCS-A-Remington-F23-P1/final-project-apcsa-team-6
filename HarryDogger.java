import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.net.URL;
import javax.imageio.ImageIO;

public class HarryDogger extends Canvas implements KeyListener, Runnable{
    private BufferedImage back;
    private String keyPressedString = "";

    private Image background;

    private Hero harry;
    private BackGround bg;


  public HarryDogger() {
    harry = new Hero(350,350,100,100,0);
    bg = new BackGround(0,0,800,800,0);
    
    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

    public void update(Graphics window)
  {
    paint(window);
  }

  public void paint( Graphics window )
  {
    Graphics2D twoDGraph = (Graphics2D)window;

    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if (back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    Graphics graphToBack = back.createGraphics();
    bg.draw(graphToBack);
    harry.draw(graphToBack);
    

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e)
  {
    keyPressedString = "" +  e.getKeyChar();
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    keyPressedString = "";
    repaint();
  }

  public void keyTyped(KeyEvent e) {
    // no code needed here
  }

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5);
        repaint();
      }
    } catch (Exception e) {
    }
  }

}