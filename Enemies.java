import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Enemies {
    private ArrayList<Enemy> enemies;

    public Enemies(){
        enemies = new ArrayList<Enemy>();
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

    public void damageEnemies(String letter){
        for (Enemy en : enemies){
            en.removeLetter(letter);
        }
        for(int i=enemies.size()-1;i>=0;i--){
            Enemy en = enemies.get(i);
            if(en.displayLetters().equals("")){
                enemies.remove(i);
            }
        }
    }

    public void detectHit(){
        for (int i=0; i<enemies.size(); i++) {
            Enemy en = enemies.get(i);
            if (en.detectHit()){
                enemies.remove(i);
                break;
            }
        }
    }
}