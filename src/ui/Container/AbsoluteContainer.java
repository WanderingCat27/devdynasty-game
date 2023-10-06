package ui.Container;

import Engine.GraphicsHandler;

// container that draws components in absolute screen space not relative to self or parents
public class AbsoluteContainer extends UIContainer {

    public AbsoluteContainer() {
        super(0, 0);
        this.setfIllType(FillType.FILL_SCREEN);
    }

    @Override
    public void draw(GraphicsHandler g) {
        for(UIContainer c : components) {
            c.setParentData(0, 0, getWidth(), getHeight());
            c.draw(g);
        }
    }
    
}
