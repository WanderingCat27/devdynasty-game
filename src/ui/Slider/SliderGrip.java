package ui.Slider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Engine.Config;
import Engine.GraphicsHandler;
import Utils.ImageUtils;
import ui.Container.Anchor;
import ui.Container.UIContainer;

public class SliderGrip extends UIContainer {

    private static BufferedImage DEF_IMAGE;


    protected BufferedImage thumbImage;
    static {
        try {
            DEF_IMAGE = ImageUtils.transformColorToTransparency(ImageIO.read(new File("Resources/thumb.png")), Config.TRANSPARENT_COLOR);
        } catch (IOException e) {
            System.err.println("Could not find thumb.png in resources where did it go?");
            e.printStackTrace();
        }
    }

    public SliderGrip(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.thumbImage = DEF_IMAGE;
        this.setAnchor(Anchor.CENTER);
    }

    @Override
    public void draw(GraphicsHandler g) {
        g.drawImage(this.thumbImage, getXAbs(), getYAbs());;
    }

    
    
}
