import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Enemy extends MovingThing {
    private ArrayList<String> letters;
    private int xSpeed, ySpeed;
    private Image image;
    private int topLeftX = 350;
    private int topLeftY = 350;
    private int botRightX = 350+80;
    private int botRightY = 350+80;
    private Sound sound;

    static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String lower = upper.toLowerCase();
    static final String symbols = upper + lower;

    public Enemy(int x, int y, int w, int h, int xS, int yS, int difficulty){
        super(x, y, w, h);
        xSpeed=xS;
        ySpeed=yS;
        sound = new Sound();
        try
        {
        URL url = getClass().getResource("Cat_wizard.png");
        image = ImageIO.read(url);
        }
        catch(Exception e)
        {
        System.out.println("Enemy failed to load");
        //feel free to do something here
        }
        
        letters = new ArrayList<String>();

        int times = (int)(Math.random()*5) + difficulty;
        if(times>5){
            times=5;
        }

        for(int i=0;i<times;i++){
            int index = (int)(Math.random()*symbols.length());
            String letter = symbols.substring(index,index+1);
            letters.add(letter);
        }
    }

    public String displayLetters(){
        String output = "";
        for(String letter:letters){
            output+=letter;
        }
        return output;
    }

    public void removeLetter(String letter){
        if(letters.get(0).equals(letter)){
            letters.remove(0);
        }
    }

    public boolean detectHit(){
        if ((super.getX()<=botRightX && super.getY()<=botRightY && super.getX()>=topLeftX && super.getY()>=topLeftY)
        || (super.getX()+super.getWidth()>=topLeftX && super.getY()>=botRightY && super.getX()+super.getWidth()<=botRightX && super.getY()<=botRightY)
        || (super.getX()+super.getWidth()>=topLeftX && super.getY()+super.getHeight()>=topLeftY && super.getX()+super.getWidth()<=botRightX && super.getY()+super.getHeight()<=botRightY)
        || (super.getX()<=botRightX && super.getY()+super.getHeight()>=topLeftY && super.getX()>=topLeftX && super.getY()+super.getHeight()<=botRightY)){
            sound.setFile(0);
            sound.setVolume(1f);
            sound.play();
            return true;
        }
        return false;
    }


    public void move(String direction){
        setX(getX()+xSpeed);
        setY(getY()+ySpeed);
    }

    public void draw( Graphics window )
    {
        window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
        window.setColor(Color.WHITE);
        window.drawString(displayLetters(),getX()+getWidth()/2-(5*letters.size()/2),getY()+getHeight()/2);
    }
}
