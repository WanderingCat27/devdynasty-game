package Maps;

import java.util.ArrayList;

import Items.FireStaff;
import Items.PurplePotion;
import Items.RedPotion;
import Items.Sword;
import Items.TimeMachine;
import Level.Map;
import Level.NPC;
import NPCs.Dinosaur;
import NPCs.MadScientist;
import NPCs.Walrus;
import Scripts.ChangeMapScript;
import Scripts.NewMap.FireStaffScript;
import Scripts.NewMap.PurplePotionScript;
import Scripts.NewMap.RedPotionScript;
import Scripts.NewMap.SwordScript;
import Scripts.NewMap.madScientistScript;
import Scripts.TestMap.DinoScript;
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
        this.setFlagManager(flagManager);

    } 
    
    //No real reason to call the load Item function here since it seems to be oddly buggy
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Sword sword = new Sword(3, getMapTile(10, 10).getLocation());
        npcs.add(sword);

        RedPotion redPotion = new RedPotion(4, getMapTile(4, 4).getLocation());
        npcs.add(redPotion);

        MadScientist madScientist = new MadScientist(1, getMapTile(11, 7).getLocation().subtractY(40));
        npcs.add(madScientist);
        madScientist.setInteractScript(new madScientistScript());

        PurplePotion purplePotion = new PurplePotion(5, getMapTile(10, 9).getLocation());
        npcs.add(purplePotion);

        TimeMachine timeMachine = new TimeMachine(6, getMapTile(4, 5).getLocation());
        timeMachine.setInteractScript(new ChangeMapScript());
        npcs.add(timeMachine);

        FireStaff fireStaff = new FireStaff(6, getMapTile(7, 2).getLocation());
        npcs.add(fireStaff);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        //dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        return npcs;
    }

}
