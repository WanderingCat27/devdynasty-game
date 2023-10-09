package Level;

 import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;

 import javax.sound.sampled.AudioInputStream;
 import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
 import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

import Engine.GameWindow;
import Engine.ImageLoader;
import GameObject.Rectangle;
import Utils.Colors;

 public class SoundPlayer extends JFrame{
     Long currentFrame;
     public String status;
     String soundPath;
     Clip clip;
     public static boolean musicPlaying = false;
     private FloatControl volumeControl;
     private JSlider volumeSlider;

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
        CustomSliderUI sliderUI = new CustomSliderUI(volumeSlider);
        // Create the volume slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setBackground(new Color(0, 0, 0, 0));
        volumeSlider.setUI(sliderUI);
        volumeSlider.setFocusable(false); // Set the focusable property to false
        volumeSlider.addChangeListener(e -> {
            // Set the volume to the value of the slider
            float volume = (float) volumeSlider.getValue() / 100.0f;
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            volumeControl.setValue(dB);
        });

        // Add the volume slider to the frame
        frame.add(volumeSlider);
     }

     public JSlider getVolumeSlider() {
         return volumeSlider;
     }

     public void reset()
     {
        GameWindow.getGameWindow().remove(volumeSlider);
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


    private static class CustomSliderUI extends BasicSliderUI {
        private static final int TRACK_HEIGHT = 8;
        private static final int THUMB_SIZE = 16;

        private BufferedImage trackImage;
        private BufferedImage thumbImage;

        public CustomSliderUI(JSlider slider) {
            super(slider);

            try
            {
                trackImage = ImageLoader.load("track.png", Colors.MAGENTA);
                thumbImage = ImageLoader.load("thumb.png", Colors.MAGENTA);
            }
            catch (Exception e)
            {
                System.out.println("Error loading slider images");
            }
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            java.awt.Rectangle trackBounds = trackRect;
            int trackLeft = trackBounds.x;
            int trackRight = trackBounds.x + trackBounds.width - 1;
            int trackTop = trackBounds.y + (trackBounds.height - TRACK_HEIGHT) / 2;
            int trackBottom = trackTop + TRACK_HEIGHT - 1;
    
            g2d.drawImage(trackImage, trackLeft, trackTop, trackRight - trackLeft + 1, trackBottom - trackTop + 1, null);
        }

        @Override
        public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int thumbLeft = thumbRect.x;
        int thumbTop = thumbRect.y + (thumbRect.height - THUMB_SIZE) / 2;

        g2d.drawImage(thumbImage, thumbLeft, thumbTop, THUMB_SIZE, THUMB_SIZE, null);
        }
    }


 }
