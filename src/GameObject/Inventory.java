package GameObject;


import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Utils.Colors;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Utils.Point;
import Maps.NewMap;
import Level.Map;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Screens.PlayLevelScreen;

public class Inventory extends Sprite
{
    //Sets the max size that the inventory can be
    protected static final int MAX_SIZE = 4;

    //Each will be a different image
    Sprite noSelection;
    Sprite oneSlot;
    Sprite twoSlot;
    Sprite threeSlot;
    Sprite fourSlot;

    Sprite item1;
    Sprite item2;
    Sprite item3;
    Sprite item4;
    Point location;
    Player player;

    //Map being used
    protected Map map = new NewMap();

    //List of items in the map
    protected ArrayList<NPC> itemsInMap = map.loadNPCs();

    //This will be used to keep track of the items in the inventory
    protected static ArrayList<Item> itemsInInventory = new ArrayList<Item>();
    public static ArrayList<Sprite> itemsInInventorySprites = new ArrayList<Sprite>();


    //Keeps track of which inventory key is pressed and draws the correct image
    protected static String keyNumber = "`";
    //protected static Key removeCheck = Key.P;

    //Define keyboard buttons
    protected KeyLocker keyLocker = new KeyLocker();
    protected static Key ONE = Key.ONE;
    protected static Key TWO = Key.TWO;
    protected static Key THREE = Key.THREE;
    protected static Key FOUR = Key.FOUR;
    //protected static Key TAB = Key.TAB;
    protected static Key BACK_QUOTE = Key.BACK_QUOTE;
    protected static Key REMOVE = Key.R;

    
    public Inventory(String noSelection, String oneSlot, String twoSlot, String threeSlot, String fourSlot, Map map, Player player)
    {
        super(ImageLoader.load("noSelectionHUD.png", Colors.MAGENTA));
        this.noSelection = new Sprite(ImageLoader.load(noSelection, Colors.MAGENTA));
        this.oneSlot = new Sprite(ImageLoader.load(oneSlot, Colors.MAGENTA));
        this.twoSlot = new Sprite(ImageLoader.load(twoSlot, Colors.MAGENTA));
        this.threeSlot = new Sprite(ImageLoader.load(threeSlot, Colors.MAGENTA));
        this.fourSlot = new Sprite(ImageLoader.load(fourSlot, Colors.MAGENTA));
        this.location = map.getMapTile(1, 2).getLocation().subtractX(6).subtractY(7);
        this.map = map;
        this.player = player;
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
        remove();
        displayItems(graphicsHandler);
        //System.out.println(map.getMapTile(1, 2).getLocation().subtractX(6).subtractY(7));
        // this.item1 = new Sprite(ImageLoader.load(itemsInInventory.get(0).getPathToImage(), Colors.MAGENTA));
            // Point item1Location = new Point(57f, 105f);
            // this.item1.setLocation(item1Location.x, item1Location.y);
            // this.item1.setScale(2);
            // this.item1.draw(graphicsHandler);
    }

    public void displayItems(GraphicsHandler graphicsHandler)
    {
        // Point item1Location = new Point(57f, 105f);
        float yLocation = 135f;
        float xLocation = 72f;
        for(Sprite item : itemsInInventorySprites)
        {
            Point spriteLocation = new Point(xLocation, yLocation);
            item.setLocation(spriteLocation.x, spriteLocation.y);
            item.setScale(2);
            item.draw(graphicsHandler);
            yLocation += 70f;
        }        
    }

    public static void addItem(Item item)
    {
        //This will be used to add an item to the inventory
        if(canAdd())
        {
            itemsInInventory.add(item);
            itemsInInventorySprites.add(new Sprite(ImageLoader.load(item.getPathToImage(), Colors.MAGENTA)));

        }
    }

    public void remove()
    {   
        try
        {
            if(Keyboard.isKeyDown(REMOVE) && keyNumber.equals("1") && itemsInInventory.size() > 0 && !keyLocker.isKeyLocked(REMOVE))
            {
                keyLocker.lockKey(REMOVE);
                Item item = itemsInInventory.remove(0);
                itemsInInventorySprites.remove(0);
                Point playerLocation = this.player.getLocation();
                item.setLocation(playerLocation.x, playerLocation.y);
                this.map.addNPC(item);
                item.setMapEntityStatus(MapEntityStatus.ACTIVE);
            }
            else if(Keyboard.isKeyDown(REMOVE) && keyNumber.equals("2") && itemsInInventory.size() > 1 && !keyLocker.isKeyLocked(REMOVE))
            {
                keyLocker.lockKey(REMOVE);
                Item item = itemsInInventory.remove(1);
                itemsInInventorySprites.remove(1);
                Point playerLocation = this.player.getLocation();
                item.setLocation(playerLocation.x, playerLocation.y);
                this.map.addNPC(item);
                item.setMapEntityStatus(MapEntityStatus.ACTIVE);

            }
            else if(Keyboard.isKeyDown(REMOVE) && keyNumber.equals("3") && itemsInInventory.size() > 2 && !keyLocker.isKeyLocked(REMOVE))
            {
                keyLocker.lockKey(REMOVE);
                Item item = itemsInInventory.remove(2);
                itemsInInventorySprites.remove(2);
                Point playerLocation = this.player.getLocation();
                item.setLocation(playerLocation.x, playerLocation.y);
                this.map.addNPC(item);
                item.setMapEntityStatus(MapEntityStatus.ACTIVE);
            }
            else if(Keyboard.isKeyDown(REMOVE) && keyNumber.equals("4") && itemsInInventory.size() > 3 && !keyLocker.isKeyLocked(REMOVE))
            {
                keyLocker.lockKey(REMOVE);
                Item item = itemsInInventory.remove(3);
                itemsInInventorySprites.remove(3);
                Point playerLocation = this.player.getLocation();
                item.setLocation(playerLocation.x, playerLocation.y);
                this.map.addNPC(item);
                item.setMapEntityStatus(MapEntityStatus.ACTIVE);
            }
            if(Keyboard.isKeyUp(REMOVE))
            {
                keyLocker.unlockKey(REMOVE);
            }
        }
        catch(Exception e)
        {
            System.out.println("No item in that slot");
        }
       

    }

    public static boolean canAdd()
    {
        return itemsInInventory.size() < 4;
    }
}
