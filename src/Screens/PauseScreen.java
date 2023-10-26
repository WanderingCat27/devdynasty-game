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
import Level.Level;
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
import ui.SpriteFont.SpriteFont;

public class PauseScreen extends Screen
{
    protected PlayLevelScreen playLevelScreen;
    protected Map background;
    protected CenterContainer centerContainer;
    protected AnimatedSpriteButton playButton;
    protected PositioningContainer posContainer;
    protected SoundPlayer soundPlayer;
    protected PositioningContainer sliderContainer;
    protected PositioningContainer musicLabelContainer;
    protected PositioningContainer walkingLabelContainer;
    protected PositioningContainer walkSliderContainer;
    protected Slider volumeSlider;
    protected Slider walkSlider;
    protected SoundPlayer walkingSoundPlayer;
    protected SpriteFont musicLabel;
    protected SpriteFont walkingLabel;
    //might turn this into spirteButtons eventually!

    public PauseScreen(PlayLevelScreen playLevelScreen, SoundPlayer soundPlayer, SoundPlayer walkingSoundPlayer) {
        this.playLevelScreen = playLevelScreen;
        this.soundPlayer = soundPlayer;
        this.walkingSoundPlayer = walkingSoundPlayer;
        initialize();
    }

    public void initialize()
    {
        // background = new WildWestMap(); //just to test
        //might need to set camera to center of the screen
        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);

        playButton = new AnimatedSpriteButton(0, -110, 6, new SpriteSheet(ImageLoader.loadAllowTransparent("resume_button.png"), 48, 26), () ->{
            playLevelScreen.resumeLevel();
          });
        
        centerContainer.addComponent(playButton);

        //volume slider
        // dont re-initialize slider
        if (volumeSlider == null) {
            // Create the volume slider
            volumeSlider = new Slider(0, 0, 200, 0, 100);
            volumeSlider.setValue(this.playLevelScreen.getCurrentVolume());
            volumeSlider.addChangeListener(() -> {
                LevelManager.getCurrentLevel().getSoundPlayer().setVolume((int) volumeSlider.getValue());
                this.playLevelScreen.setCurrentVolume(volumeSlider.getValue());

            });
            // position at top of screen and anchor objects to their top center
            sliderContainer = new PositioningContainer(Anchor.CENTER);
            sliderContainer.setYOrigin(100);
            sliderContainer.setfillType(FillType.FILL_SCREEN);
            sliderContainer.setAnchorChildren(true);
            sliderContainer.addComponent(volumeSlider);
            
            //music container
            musicLabel = new SpriteFont("Music", 0, 0, new Font("Times New Roman", Font.BOLD,30), Color.YELLOW);
            musicLabelContainer = new PositioningContainer(Anchor.CENTER);
            musicLabelContainer.setYOrigin(75);
            musicLabelContainer.setfillType(FillType.FILL_SCREEN);
            musicLabelContainer.setAnchorChildren(true);
            musicLabelContainer.addComponent(musicLabel);
        }
        if(walkSlider == null)
        {
            walkSlider = new Slider(0, 0, 200, 0, 100);
            walkSlider.setValue(this.playLevelScreen.getCurrentWalkVolume());
            walkSlider.addChangeListener(() -> {
                LevelManager.getCurrentLevel().getPlayer().getWalkingSoundPlayer().setVolume((int) walkSlider.getValue());
                this.playLevelScreen.setCurrentWalkVolume(walkSlider.getValue());
            });

            walkSliderContainer = new PositioningContainer(Anchor.CENTER);
            walkSliderContainer.setYOrigin(200);
            walkSliderContainer.setfillType(FillType.FILL_SCREEN);
            walkSliderContainer.setAnchorChildren(true);
            walkSliderContainer.addComponent(walkSlider);
            
            //Sound FX container
            walkingLabel = new SpriteFont("Sound FX", 0, 0, new Font("Times New Roman", Font.BOLD,30), Color.YELLOW);
            walkingLabelContainer = new PositioningContainer(Anchor.CENTER);
            walkingLabelContainer.setYOrigin(175);
            walkingLabelContainer.setfillType(FillType.FILL_SCREEN);
            walkingLabelContainer.setAnchorChildren(true);
            walkingLabelContainer.addComponent(walkingLabel);

        }
    }

    public void draw(GraphicsHandler graphicsHandler)
    {
        //background.draw(graphicsHandler);
        centerContainer.draw(graphicsHandler);
        sliderContainer.draw(graphicsHandler);
        walkSliderContainer.draw(graphicsHandler);
        musicLabelContainer.draw(graphicsHandler);
        walkingLabelContainer.draw(graphicsHandler);
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
        walkSliderContainer.update();
        musicLabelContainer.update();
        walkingLabelContainer.update();
    }

    public SoundPlayer getSoundPlayer()
    {
        return soundPlayer;
    }

    public void setSoundPlayer(SoundPlayer soundPlayer)
    {
        this.soundPlayer = soundPlayer;
    }

    public Slider getSlider() {
        return volumeSlider;
    }

    public void setWalkingSoundPlayer(SoundPlayer walkingSoundPlayer)
    {
        this.walkingSoundPlayer = walkingSoundPlayer;
    }
}
