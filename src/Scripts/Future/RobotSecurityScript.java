package Scripts.Future;

import Items.BuildingEntrance;
import Level.GlobalFlagManager;
import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class RobotSecurityScript extends Script<NPC> {


   @Override
    protected void setup()
    {
        lockPlayer();
        entity.facePlayer(player);

           if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToSecurityRobot")) {
            BuildingEntrance elevator1 = new BuildingEntrance(0, getMapTile(2, 8).getLocation(), 96, 20, "Floor3");
            showTextbox();
            addTextToTextboxQueue( "ROBOT HEALTH AT MINIMUM CAPACITY");
            addTextToTextboxQueue( "FINAL CODE NUMBER IS 9");
            addTextToTextboxQueue( "POWERING DOWN.....");
            this.map.addItem(elevator1);
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToSecurityRobot")) {
            showTextbox();
            addTextToTextboxQueue("ACCESS TO SECURITY ROOM PROHIBITED");
            addTextToTextboxQueue("ATTACK MODE INITIATED");

        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToSecurityRobot");
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}

