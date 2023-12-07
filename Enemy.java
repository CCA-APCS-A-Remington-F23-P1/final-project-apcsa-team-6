import java.util.ArrayList;
public class Enemy extends MovingThing implements Collideable<HarryDogger> {
    private ArrayList<String> letters;
    private pair position;
    private int xSpeed, ySpeed;

    public Enemy(pair position, int s){
        Super(position.get(0), position.get(1), 100, 100, s);
        try {
            URL url = getClass().getResource("Cat_wizard.png");
            Image = ImageIO.read(url);
        } catch (Exception e){
            //nothing
        }
        xSpeed = position.get(0) - Hero.getX();
        ySpeed = position.get(1) - Hero.getY();
    }

    public void move(){
        setX(getX()+xSpeed);
        setY(getY()+ySpeed);
    }

    public void collide(HarryDogger h){
        if (getX() < h.getX() + h.getWidth() && getX() + getWidth() > h.getX() && getY() < h.getY() + h.getHeight() && getY() + getHeight() > h.getY()){
            h.setHealth(h.getHealth()-1);
        }
    }
}
