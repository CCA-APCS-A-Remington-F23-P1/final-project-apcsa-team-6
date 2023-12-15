//package main;

import java.net.URL;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

public class Sound{
    Clip clip;
    URL soundURL[] =  new URL[6];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/dog.wav");
        soundURL[1] = getClass().getResource("/sound/cat.wav");
        soundURL[2] = getClass().getResource("/sound/sword.wav");
        soundURL[3] = getClass().getResource("/sound/GameOver.wav");
        soundURL[4] = getClass().getResource("/sound/doom.wav");
        soundURL[5] = getClass().getResource("/sound/superhero.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}