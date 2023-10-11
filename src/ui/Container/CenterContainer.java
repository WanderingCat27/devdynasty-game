package ui.Container;

import ui.Anchor;

// is just a CENTER positioning container, with anchor children enabled
// just for easy access to center container 
public class CenterContainer extends PositioningContainer {

    public CenterContainer() {
        super(Anchor.CENTER);
        this.anchorChildren = true;
    }

}
