import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Enemies {
    private ArrayList<Enemy> enemies;
    private Sound sound;

    public Enemies(){
        enemies = new ArrayList<Enemy>();
        sound = new Sound();
    }

    public void add(Enemy en){
        enemies.add(en);
    }

    public void move(){
        for (Enemy en : enemies){
            en.move("");
        }
    }
    
    public void draw(Graphics window){
        for (Enemy en : enemies){
            en.draw(window);
        }
    }

    public ArrayList<Integer> damageEnemies(String letter){
        ArrayList<Integer> deadArr = new ArrayList<Integer>();
        for (Enemy en : enemies){
            en.removeLetter(letter);
        }
        for(int i=enemies.size()-1;i>=0;i--){
            Enemy en = enemies.get(i);
            if(en.displayLetters().equals("")){
                sound.setFile((int)(Math.random() * 2));
                sound.setVolume(1f);
                sound.play();
                enemies.remove(i);
                deadArr.add(1);
            }
        }
        return deadArr;
    }

    public boolean detectHit(){
        for (int i=0; i<enemies.size(); i++) {
            Enemy en = enemies.get(i);
            if (en.detectHit()){
                enemies.remove(i);
                return true;
            }
        }
        return false;
    }
}