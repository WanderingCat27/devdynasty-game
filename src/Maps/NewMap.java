package Maps;

import java.util.ArrayList;

import Items.FireStaff;
import Items.PurplePotion;
import Items.RedPotion;
import Items.Sword;
import Level.Map;
import Level.NPC;
import Scripts.NewMap.FireStaffScript;
import Scripts.NewMap.PurplePotionScript;
import Scripts.NewMap.RedPotionScript;
import Scripts.NewMap.SwordScript;
import Tilesets.AnimatedTileset;
//import Tilesets.CommonTileset;


public class NewMap extends Map
{   
    
    public NewMap()
    {   
        super("proof_map.txt", new AnimatedTileset());
        this.playerStartPosition = getMapTile(10, 10).getLocation();
        // Links background sound to map
        this.soundPath = "Resources/Audio/AmTronic_-_Caribbean_Dub.wav";
        System.out.println(this.soundPath);

    } 
    
    //No real reason to call the load Item function here since it seems to be oddly buggy
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Sword sword = new Sword(3, getMapTile(10, 10).getLocation());
        sword.setInteractScript(new SwordScript());
        npcs.add(sword);

        RedPotion redPotion = new RedPotion(4, getMapTile(4, 4).getLocation());
        redPotion.setInteractScript(new RedPotionScript());
        npcs.add(redPotion);

        PurplePotion purplePotion = new PurplePotion(5, getMapTile(10, 9).getLocation());
        purplePotion.setInteractScript(new PurplePotionScript());
        npcs.add(purplePotion);

        FireStaff fireStaff = new FireStaff(6, getMapTile(7, 2).getLocation());
        fireStaff.setInteractScript(new FireStaffScript());
        npcs.add(fireStaff);

        return npcs;
    }

}
