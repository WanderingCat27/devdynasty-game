package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.Screen;
import Level.Map;
import Level.SoundPlayer;
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
    protected TextButton resumeButton;
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
        TextButton playButton = new TextButton(0, -100, 300, 90, new Color(2, 48, 71), "Resume", new Font("Comic Sans", Font.BOLD, 30), new Color(255, 183, 3), new Runnable() {

            @Override
            public void run()
            {
                playLevelScreen.resumeLevel();
            }
        });
        playButton.getSpriteFont().setOutlineThickness(3);
        playButton.getSpriteFont().setOutlineColor(Color.black);
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

    public void update()
    {
        centerContainer.update();
        sliderContainer.update();
    }
}
