package ui.Textbox;

import java.awt.image.BufferedImage;

import Engine.ImageLoader;
import ui.Container.CenterContainer;
import ui.Container.UIContainer;

public class CustomImage extends UIContainer
{
    protected boolean isActive;
    private BufferedImage image;

    public CustomImage(int x, int y, int width, int height, String path)
    {
        super(x, y, width, height);
        image = ImageLoader.load(path);
    }
    
}
