package Scripts.Future;

import Items.BuildingEntrance;
import Level.GlobalFlagManager;
import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class RobotSecurity extends Script<NPC> {


   @Override
    protected void setup()
    {
        lockPlayer();
        entity.facePlayer(player);

           if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("securityRobotDefeated")) {
            showTextbox();
            addTextToTextboxQueue( "ROBOT HEALTH AT MINIMUM CAPACITY");
            addTextToTextboxQueue( "FINAL CODE NUMBER IS 9");
            addTextToTextboxQueue( "POWERING DOWN.....");
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToRobotEnemy")) {
            BuildingEntrance elevator1 = new BuildingEntrance(0, getMapTile(37, 2).getLocation(), 96, 20, "Floor3");
            showTextbox();
            addTextToTextboxQueue("ACCESS TO SECURITY ROOM PROHIBITED");
            addTextToTextboxQueue("ATTACK MODE INITIATED");
            this.map.addItem(elevator1);
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToRobotSecurity");
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

