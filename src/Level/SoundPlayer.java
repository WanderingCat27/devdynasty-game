package Level;

 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;

 import javax.sound.sampled.AudioInputStream;
 import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;
 import javax.sound.sampled.LineUnavailableException;
 import javax.sound.sampled.UnsupportedAudioFileException;

 public class SoundPlayer {
     Long currentFrame;
     public String status;
     String soundPath;
     Clip clip;
     public static boolean musicPlaying = false;

     private AudioInputStream audioInputStream;

     public SoundPlayer(String soundFilePath) {
         try {
             soundPath = soundFilePath;
             System.out.println("getting file");
             audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
             System.out.println("got file");
             clip = AudioSystem.getClip();
             clip.open(audioInputStream);
             clip.loop(Clip.LOOP_CONTINUOUSLY);
         } catch (Exception e) {
             System.out.println("Error with creating sound player");
             System.out.println(e);
         }

     }

     public void play() {
         clip.start();
         status = "play";
     }
     public void pause() {
     this.currentFrame = this.clip.getMicrosecondPosition();
     clip.stop();
     System.out.println("paused");
     status = "paused";
     }
     public void resume() {
           try {
             if (status.equals("play")) {
                 return;
             }
             clip.close();
             resetAudioStream();
             clip.setMicrosecondPosition(currentFrame);
             this.play();
         } catch (Exception e) {
             System.out.println("resume error");
         } 
     }

     public void resetAudioStream() {
         try {
             audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
             clip.open(audioInputStream);
             clip.loop(Clip.LOOP_CONTINUOUSLY);
         } catch (Exception e) {
             System.out.println("reset error");
         }
     }



 }
