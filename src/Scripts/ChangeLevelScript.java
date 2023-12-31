package Scripts;

import Level.Level;
import Level.LevelManager;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

// Reusable simple interact script
// Just shows text upon interacting with the associated entity
public class ChangeLevelScript extends Script {
    private Level level;
    public ChangeLevelScript(Level level) {

      this.level = level;      
    }


    @Override
    public ScriptState execute() {
        // call setup code
        start();
        LevelManager.getCurrentLevel().getPlayer().stopSound(); // stops walking sound
        

        PlayLevelScreen.doReload = true;
        System.out.println(level);
        LevelManager.setLevel(level);
        setFlag("returnedToLab");

        // script ends
        return ScriptState.COMPLETED;
    }


    @Override
    protected void setup() {
    }


    @Override
    protected void cleanup() {
    
    }
}
