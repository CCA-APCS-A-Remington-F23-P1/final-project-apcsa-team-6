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
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HarryDogger extends Canvas implements KeyListener, Runnable {
  private BufferedImage back;
  private String keyPressedString = "";

  private Hero harry;
  private BackGround bg;

  private Sound sound;

  // private Enemy enemy1;

  private Enemies enemies;
  private FileWriter scoreWriter;

  private int score;
  private int highScore;

  // top left, top middle, top right
  // middle left, middle right
  // bottom left, bottom middle, bottom right
  private int[] xPos = { -90, 350, 780, -90, 780, -90, 350, 780 };
  private int[] yPos = { -90, -90, -90, 350, 350, 750, 750, 750 };
  private int[] xS = { 2, 0, -2, 2, -2, 2, 0, -2 };
  private int[] yS = { 2, 2, 2, 0, 0, -2, -2, -2 };

  private int difficulty = 1;
  private int difficultyBuffer = 0;

  private int moveBuffer = 10;
  private int spawnBuffer = 1200;

  private boolean gameOver = false;
  private boolean gameOvered = false;

  public HarryDogger() {
    try {
      scoreWriter = new FileWriter("Scores.txt", true);
    } catch (IOException e) {
      System.out.println("Error creating file");
    }

    try {
      File file = new File("Scores.txt");
      Scanner scan = new Scanner(file);

      while (scan.hasNextInt()){
        highScore = scan.nextInt();
        System.out.println(highScore);
      }
      System.out.println(highScore);
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error reading file");
    }

    harry = new Hero(350, 350, 100, 100, 0);
    bg = new BackGround(0, 0, 800, 800, 0);
    enemies = new Enemies();
    score = 0;
    sound = new Sound();

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);

    //bg music
    sound.setFile(4); //doom
    //sound.setFile(5); //superhero
    sound.setVolume(0.45f);
    sound.loop();
  }

  

  public void update(Graphics window) {
    paint(window);
  }

  public void paint(Graphics window) {
    Graphics2D twoDGraph = (Graphics2D) window;

    // take a snap shop of the current screen and same it as an image
    // that is the exact same width and height as the current screen
    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));

    Graphics graphToBack = back.createGraphics();
    graphToBack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

    if (harry.getHealth() == 0) {
      graphToBack.setColor(Color.BLACK);
      graphToBack.fillRect(0, 0, 800, 800);
      
      if(!gameOvered){
        sound.setFile(3);
        sound.setVolume(1f);
        sound.play();
        try {
          if(score> highScore) scoreWriter.write("" + score + "\n");
          scoreWriter.close();
        } catch (IOException e) {
          System.out.println("Error writing to file");
        }
        gameOvered = true;
      }

      graphToBack.setColor(Color.red);
      graphToBack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
      graphToBack.drawString("GAME OVER!", 300, 300);
      graphToBack.drawString("Score = "+ score, 300, 360);
      graphToBack.drawString("Diff = "+ difficulty, 300, 420);

      twoDGraph.drawImage(back, null, 0, 0);
      return;
    }


    bg.draw(graphToBack);
    harry.draw(graphToBack);
    enemies.draw(graphToBack);
    graphToBack.setColor(Color.WHITE);
    graphToBack.drawString("Score: " + score, 150, 30);
    graphToBack.drawString("Difficulty: " + difficulty, 150, 60);
    graphToBack.drawString("High score: " + highScore, 150, 90);
    graphToBack.drawString("Health: " + harry.getHealth(), 450, 30);
    graphToBack.setColor(Color.WHITE);
    if (!keyPressedString.equals("")) {
      graphToBack.drawString(keyPressedString, 390, 350);
    }
    // enemy1.draw(graphToBack);

    if(difficulty % 3 == 0){
      if(difficulty == 4){
      }else{
      for(int x: xS){
        if(x>0){
          x++;
        }else if(x<0){
          x--;
        }
      }
      for(int y: yS){
        if(y>0){
          y++;
        }else if(y<0){
          y--;
        }
      }
      }
      
    }

    if (spawnBuffer == 1200) {
      int[] tracker = new int[8];
      int amountSpawn = (int) (Math.random() * 3) + difficulty;
      if (amountSpawn > 4) {
        amountSpawn = 4;
      }
      for (int i = 0; i < amountSpawn; i++) {
        int index = (int) (Math.random() * 8);
        while (tracker[index] == 1) {
          index = (int) (Math.random() * 8);
        }
        tracker[index] = 1;
        Enemy en = new Enemy(xPos[index], yPos[index], 100, 100, xS[index], yS[index], difficulty);
        enemies.add(en);
      }
      spawnBuffer = 0;
    } else {
      spawnBuffer++;
    }

    if (moveBuffer == 10) {
      enemies.move();
      moveBuffer = 0;
    } else {
      moveBuffer++;
    }

    ArrayList<Integer> deadArr = enemies.damageEnemies(keyPressedString);
    for (int i : deadArr) score += 100;
    
    harry.setHealth(harry.getHealth() - (enemies.detectHit()?1:0));

    if (difficultyBuffer == 6000) {
      difficulty++;
      difficultyBuffer = 0;
    } else {
      difficultyBuffer++;
    }

    

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e) {
    keyPressedString = "" + e.getKeyChar();
    repaint();
    harry.swordSwing();
  }

  public void keyReleased(KeyEvent e) {
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