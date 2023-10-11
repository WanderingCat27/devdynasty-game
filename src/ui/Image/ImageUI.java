package ui.Image;

import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import ui.Container.UIContainer;

public class ImageUI extends UIContainer {

    protected BufferedImage image;

    public ImageUI(int x, int y) {
        super(0, 0, 0, 0);
        image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().fillRect(0, 0, getWidth(), getHeight());
    }

    
    @Override
    public int getHeight() {
        return image.getWidth();
    }


    @Override
    public int getWidth() {
        return image.getHeight();
    }


    @Override
    public void draw(GraphicsHandler g) {
        g.drawImage(image, getXAbs(), getYAbs());
    }




    


    
}
