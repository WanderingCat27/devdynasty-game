package Scripts.NewMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Maps.WildWestMap;
import Maps.CombatMap;
import Screens.PlayLevelScreen;
import Screens.CombatScreen;
import Game.ScreenCoordinator;


// script for talking to walrus npc
public class DinoCombatScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("Dino combat script has started");
        lockPlayer();
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("has talked to dino2")) {
            addTextToTextboxQueue( "Im gonna fight you");
        }
        else {
            addTextToTextboxQueue( "I wanna fight you");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("has talked to dino2");
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        System.out.println("executing");
        PlayLevelScreen.changeMap();
        PlayLevelScreen.doReload = true;
        PlayLevelScreen.changeMapType = new CombatMap();
        return ScriptState.COMPLETED;
    }
}

