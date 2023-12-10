import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Enemies {
    private ArrayList<Enemy> enemies;

    public Enemies(int size){
        enemies = new ArrayList<Enemy>();
        for (i = 0; i <size; i++){
            enemies.add(new Enemy())
        }
    }

    public void add(Enemy en){
        enemies.add(en);
    }

    public void move(){
        for (Enemy en : enemies){
            en.move();
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