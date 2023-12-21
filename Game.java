import javax.swing.JFrame;
import java.awt.Component;

public class Game extends JFrame{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    public Game(){
        super("HARRY DOGGER");
        setSize(WIDTH,HEIGHT);

        HarryDogger theGame = new HarryDogger();
        ((Component)theGame).setFocusable(true);

        getContentPane().add(theGame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        Game run = new Game();
    }
}
