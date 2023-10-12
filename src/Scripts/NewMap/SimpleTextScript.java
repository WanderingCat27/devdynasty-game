//package Scripts;

import Level.Script;
import Level.ScriptState;

// Reusable script for displaying two text boxes, one above the other
public class SimpleTextScript extends Script {
    private String[] textItems1;
    private String[] textItems2;

    public SimpleTextScript(String[] text1, String[] text2) {
        this.textItems1 = text1;
        this.textItems2 = text2;
    }

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue(textItems1);
    }

    @Override
    protected void cleanup() {
        hideTextbox();
        unlockPlayer();
    }

    @Override
    public ScriptState execute() {
        // Call the setup code
        start();

        // While the first textbox is not finished displaying all text, the script keeps running
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }

        // When the first textbox is finished, call the cleanup code to show the second textbox
        end();

        // While the second textbox is not finished displaying all text, the script keeps running
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }

        // When the second textbox is finished, call the cleanup code to hide it
        hideTextbox();

        // The script ends
        return ScriptState.COMPLETED;
    }
}