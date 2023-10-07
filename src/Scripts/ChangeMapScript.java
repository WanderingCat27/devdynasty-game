package Scripts;

import Level.Map;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

// script for talking to tree with hole in it
public class ChangeMapScript extends Script {

    @Override
    protected void setup() {
        lockPlayer();
    }

    @Override
    protected void cleanup() {
        map.flagManager.setFlag("changeMap");
        System.out.println("setting flag");
        hideTextbox();
        unlockPlayer();
    }

    public Map currentMap;
    public Map newMap;

    @Override
    public ScriptState execute() {
        System.out.println("executing");
        PlayLevelScreen.changeMap();
        return ScriptState.COMPLETED;
    }
}

