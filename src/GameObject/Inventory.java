package GameObject;


import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Utils.Colors;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Utils.Point;
import Maps.NewMap;
import Level.Map;

public class Inventory extends Sprite
{

    //Each will be a different image
    Sprite noSelection;
    Sprite oneSlot;
    Sprite twoSlot;
    Sprite threeSlot;
    Sprite fourSlot;
    Point location;

    //Keeps track of which inventory key is pressed and draws the correct image
    protected static String keyNumber = "`";

    //Define keyboard buttons
    protected KeyLocker keyLocker = new KeyLocker();
    protected static Key ONE = Key.ONE;
    protected static Key TWO = Key.TWO;
    protected static Key THREE = Key.THREE;
    protected static Key FOUR = Key.FOUR;
    //protected static Key TAB = Key.TAB;
    protected static Key BACK_QUOTE = Key.BACK_QUOTE;

    
    public Inventory(String noSelection, String oneSlot, String twoSlot, String threeSlot, String fourSlot, Map map)
    {
        super(ImageLoader.load("noSelectionHUD.png", Colors.MAGENTA));
        this.noSelection = new Sprite(ImageLoader.load(noSelection, Colors.MAGENTA));
        this.oneSlot = new Sprite(ImageLoader.load(oneSlot, Colors.MAGENTA));
        this.twoSlot = new Sprite(ImageLoader.load(twoSlot, Colors.MAGENTA));
        this.threeSlot = new Sprite(ImageLoader.load(threeSlot, Colors.MAGENTA));
        this.fourSlot = new Sprite(ImageLoader.load(fourSlot, Colors.MAGENTA));
        this.location = map.getMapTile(1, 2).getLocation().subtractX(6).subtractY(7);
    }

    //This will track which key is pressed and set the keyNumber to the corresponding number to be used in the draw function
    public static void keyCheck()
    {
        if(Keyboard.isKeyDown(ONE))
        {
            Inventory.keyNumber = "1";
        }
        else if(Keyboard.isKeyDown(TWO))
        {
            Inventory.keyNumber = "2";
        }
        else if(Keyboard.isKeyDown(THREE))
        {
            Inventory.keyNumber = "3";
        }
        else if(Keyboard.isKeyDown(FOUR))
        {
            Inventory.keyNumber = "4";
        }
        else if(Keyboard.isKeyDown(BACK_QUOTE))
        {
            Inventory.keyNumber = "`";
        }
    }
    
    public void drawHud(GraphicsHandler graphicsHandler)
    {

        if(Inventory.keyNumber.equals("1"))
        {
            this.oneSlot.setLocation(this.location.x, this.location.y);
            this.oneSlot.setScale(2);
            this.oneSlot.draw(graphicsHandler);
        }
        else if(Inventory.keyNumber.equals("2"))
        {
            this.twoSlot.setLocation(this.location.x, this.location.y);
            this.twoSlot.setScale(2);
            this.twoSlot.draw(graphicsHandler);
        }
        else if(Inventory.keyNumber.equals("3"))
        {
            this.threeSlot.setLocation(this.location.x, this.location.y);
            this.threeSlot.setScale(2);
            this.threeSlot.draw(graphicsHandler);
        }
        else if(Inventory.keyNumber.equals("4"))
        {
            this.fourSlot.setLocation(this.location.x, this.location.y);
            this.fourSlot.setScale(2);
            this.fourSlot.draw(graphicsHandler);
        }
        else if(Inventory.keyNumber.equals("`"))
        {
            this.noSelection.setLocation(this.location.x, this.location.y);
            this.noSelection.setScale(2);
            this.noSelection.draw(graphicsHandler);
        }
    }
}
