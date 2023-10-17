package Utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

// This class has some useful image methods that are used when loading in images to the game
public class ImageUtils {
  // changes desired color to be transparent (the chosen color will not be seen in
  // game when drawn)
  public static BufferedImage transformColorToTransparency(BufferedImage image, Color transparentColor) {
    BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    int transparentColorIndex = transparentColor.getRGB();

    // iterates through each pixel of the image
    // if pixel is equal to the transparent color, changes that pixel to be fully
    // transparent
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        int rgb = image.getRGB(i, j);
        if (rgb == transparentColorIndex) {
          g.setColor(new Color(0, true));
          g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        } else {
          g.setColor(new Color(rgb, false));
          g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        }
        g.drawRect(i, j, 1, 1);
      }
    }
    g.dispose();
    return newImage;
  }

  // https://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage
  public static BufferedImage deepCopy(BufferedImage bi) {
    ColorModel cm = bi.getColorModel();
    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = bi.copyData(null);
    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
  }

  public static void darken(BufferedImage image) {
    for(int x = 0; x < image.getWidth(); x++)
      for(int y = 0; y < image.getHeight(); y++)
        image.setRGB(x, y, new Color(image.getRGB(x, y)).darker().getRGB());
  }


  public static void brighten(BufferedImage image) {
    for(int x = 0; x < image.getWidth(); x++)
      for(int y = 0; y < image.getHeight(); y++)
        image.setRGB(x, y, new Color(image.getRGB(x, y)).brighter().getRGB());
  }


  // https://stackoverflow.com/a/4216315
  // resizes an image
  public static BufferedImage resizeImage(BufferedImage image, int newWidth, int newHeight) {
    BufferedImage resized = new BufferedImage(newWidth, newHeight, image.getType());
    Graphics2D g = resized.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth(), image.getHeight(), null);
    g.dispose();
    return resized;
  }

  // Not my code, taken from:
  // https://stackoverflow.com/questions/11271329/converting-image-to-bufferedimage
  public static BufferedImage resizeImageNearestNeighbor(BufferedImage image, int newWidth, int newHeight) {
    BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at = new AffineTransform();
    at.scale((float) newWidth / image.getWidth(), (float) newHeight / image.getHeight());
    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    return scaleOp.filter(image, scaledImage);
  }

  public static BufferedImage scaleImage(BufferedImage image, float scale) {
    return resizeImageNearestNeighbor(image, (int) (image.getWidth() * scale), (int) (image.getHeight() * scale));
  }

  // resize image but dont resize the hieght of top bottom edge and width of left
  // right edge
  public static BufferedImage resizeImagesIgnoreEdges(BufferedImage image, int width, int height, int ignoreEdgeWidth,
      int ignoreEdgeHeight) {
    // crop edges off
    // resize center of image without edges
    // draw the edgese back on only scaled in the one direction
    return null;
  }

  public static BufferedImage createSolidImage(Color color) {
    BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    image = ImageUtils.transformColorToTransparency(image, color);
    return image;
  }

  public static BufferedImage createSolidImage(Color color, int width, int height) {
    BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    image = ImageUtils.transformColorToTransparency(image, color);
    return resizeImage(image, width, height);
  }

}
