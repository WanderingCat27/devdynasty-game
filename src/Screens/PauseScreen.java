package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import GameObject.AnimatedSprite;
import GameObject.SpriteSheet;
import Level.LevelManager;
import Level.Map;
import Level.SoundPlayer;
import Maps.TitleScreenMap;
import ui.Button.AnimatedSpriteButton;
import ui.Button.TextButton;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Slider.Slider;

public class PauseScreen extends Screen
{
    protected PlayLevelScreen playLevelScreen;
    protected Map background;
    protected CenterContainer centerContainer;
    protected AnimatedSpriteButton playButton;
    protected PositioningContainer posContainer;
    protected SoundPlayer soundPlayer;
    protected PositioningContainer sliderContainer;
    protected Slider volumeSlider;
    //might turn this into spirteButtons eventually!

    public PauseScreen(PlayLevelScreen playLevelScreen, SoundPlayer soundPlayer) {
        this.playLevelScreen = playLevelScreen;
        this.soundPlayer = soundPlayer;
        initialize();
    }

    public void initialize()
    {
        // background = new WildWestMap(); //just to test
        //might need to set camera to center of the screen
        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);

        playButton = new AnimatedSpriteButton(0, -60, 6, new SpriteSheet(ImageLoader.loadAllowTransparent("resume_button.png"), 48, 26), () ->{
            playLevelScreen.resumeLevel();
          });
        
        centerContainer.addComponent(playButton);

        //volume slider
        // dont re-initialize slider
        if (volumeSlider == null) {
            // Create the volume slider
            volumeSlider = new Slider(0, 0, 200, 0, 100);
            volumeSlider.setValue(volumeSlider.getMax());
            volumeSlider.addChangeListener(() -> {
                playLevelScreen.getSoundPlayer().setVolume((int) volumeSlider.getValue());
                System.out.println(volumeSlider.getValue());
            });
            // position at top of screen and anchor objects to their top center
            sliderContainer = new PositioningContainer(Anchor.CENTER);
            sliderContainer.setYOrigin(100);
            sliderContainer.setfillType(FillType.FILL_SCREEN);
            sliderContainer.setAnchorChildren(true);

            sliderContainer.addComponent(volumeSlider);
        }
        //playLevelScreen.getSoundPlayer().getVolume().getValue()
    }

    public void draw(GraphicsHandler graphicsHandler)
    {
        //background.draw(graphicsHandler);
        centerContainer.draw(graphicsHandler);
        sliderContainer.draw(graphicsHandler);
    }
    private float getScaleFactor() {
      return Math.max(1, Math.min((float)ScreenManager.getScreenHeight() / Config.GAME_WINDOW_HEIGHT, (float)ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH));
    }
    
    public void update()
    {  
        centerContainer.update();
        float scale = getScaleFactor();
        playButton.scale(scale);
        sliderContainer.update();
    }

    public SoundPlayer getSoundPlayer()
    {
        return soundPlayer;
    }

    public Slider getSlider() {
        return volumeSlider;
    }
}
