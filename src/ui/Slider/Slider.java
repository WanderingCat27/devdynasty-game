package ui.Slider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.Mouse;
import Utils.ImageUtils;
import Utils.MathUtils;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer;

public class Slider extends UIContainer {

    private float min, max;
    protected Runnable sliderChanged;
    private SliderGrip grip;
    private boolean isPressed = false;
    protected BufferedImage trackImage;

    private static BufferedImage DEF_IMAGE;

    static {
        try {
            DEF_IMAGE = ImageUtils.transformColorToTransparency(ImageIO.read(new File("Resources/track.png")), Config.TRANSPARENT_COLOR);
        } catch (IOException e) {
            System.err.println("Could not find track.png in resources where did it go?");
            e.printStackTrace();
        }
    }

    public Slider(int x, int y, int width, float min, float max) {
        super(x + (int) (DEF_IMAGE.getHeight() * .5f), y, width, DEF_IMAGE.getHeight());
        // resize track sprite to width defined
        this.trackImage = ImageUtils.resizeImageNearestNeighbor(DEF_IMAGE, width, DEF_IMAGE.getHeight());

        this.min = min;
        this.max = max;
        grip = new SliderGrip(x, y, 10, (int) (DEF_IMAGE.getHeight() * 1.5f));
        PositioningContainer container = new PositioningContainer(Anchor.CENTER_LEFT);
        container.addComponent(grip);
        this.addComponent(container);
    }

    protected SliderGrip getSliderGrip() {
        return grip;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    @Override
    public void update() {
        super.update();

        // mouse is over (also allows for mouse to move off of slider after pressing
        // while dragging, qol)
        if (MathUtils.isInBounds(Mouse.getMouseLoction(), getXAbs(), getYAbs(), getWidth(), getHeight())
                && Mouse.isButtonDown(Mouse.LEFT_MOUSE_BUTTON)) {
            isPressed = true;
        } else if (Mouse.isButtonUp(Mouse.LEFT_MOUSE_BUTTON)) {
            isPressed = false;
        }

        if (isPressed) {
            int pastVal = grip.getXOrigin();
            grip.setXOrigin(MathUtils.clamp(Mouse.getMouseLoction().x - getXAbs(), 0, getWidth()));
            // if slider value changed run event
            if (pastVal != grip.getXOrigin())
                sliderChanged.run();
        }
    }

    @Override
    public void draw(GraphicsHandler g) {
        g.drawImage(this.trackImage, getXAbs(), getYAbs());
        // draw components (Slider grip)
        super.draw(g);
    }

    public float getValue() {
        // get the x position of Slider's SliderGrip relative to the Slider
        float sliderPercent = (float) getSliderGrip().getXOrigin() / getWidth();
        // apply to max and min to get the current value
        return sliderPercent * (max - min) + min;
    }

    public void setValue(float value) {
        // get percentage value is of the range min to max
        float valuePercentage = (value - min) / (max - min);
        // apply to max and min to get the current value
        grip.setXOrigin((int) (valuePercentage * getWidth()));
    }

    public void addChangeListener(Runnable runnable) {
        this.sliderChanged = runnable;
    }
}
