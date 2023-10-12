package Level;

 import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

 public class SoundPlayer extends JFrame{
     Long currentFrame;
     public String status;
     String soundPath;
     Clip clip;
     public static boolean musicPlaying = false;
     private FloatControl volumeControl;

     private AudioInputStream audioInputStream;

     public SoundPlayer(JFrame frame ,String soundFilePath) {
         try {
             soundPath = soundFilePath;
             System.out.println("getting file");
             audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
             System.out.println("got file");
             clip = AudioSystem.getClip();
             clip.open(audioInputStream);
             volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); //set the volume for the the audio clip
             clip.loop(Clip.LOOP_CONTINUOUSLY);
         } catch (Exception e) {
             System.out.println("Error with creating sound player");
             System.out.println(e);
        }
        

     }

     public SoundPlayer(JFrame frame ,String soundFilePath, int startVolume ) {
        this(frame, soundFilePath);
        setVolume(startVolume);
        

     }

     // volume from 0-100;
     public void setVolume(int volume) {
        // clamp and adjust volume to usable value
        float vol = Utils.MathUtils.clamp(volume, 0, 100) / 100f;
        float dB = (float) (Math.log(vol) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);
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
