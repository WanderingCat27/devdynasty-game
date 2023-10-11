package ui.Container;

import Engine.GraphicsHandler;
import ui.Anchor;

// positions all children relative to self based on anchor position
// i.e. TOP_LEFT draws everything
public class PositioningContainer extends UIContainer {

    // does not change the position of this container itself but specifies how the
    // children components should be offset
    // can use anchor because inverting it and add its own offset to its children
    // from the anchor will produce the result of
    // positioning the object relative to that point within the container
    // i.e. TOP_CENTER will draw all elements relative to the top center of this
    // container
    protected Anchor drawPosition;

    // if true, every child added to container will have thier anchor set to the
    // same anchor as the drawPosition
    protected boolean anchorChildren = false;

    // by default just positions 0, 0 and fills parent by parent constructor
    // definition
    public PositioningContainer(Anchor drawPosition) {
        super(0, 0);
        this.drawPosition = drawPosition;
    }

    // inverting Anchor offset methods will move objects away from origin which in
    // this case is the top left of the screen
    @Override
    public void draw(GraphicsHandler g) {
        for (UIContainer comp : components) {
            comp.setParentData(getXAbs() - drawPosition.getXOffset(this), getYAbs() - drawPosition.getYOffset(this),
                    getWidth(), getHeight());
            comp.draw(g);
        }
    }

    // if should change anchor then change anchor of children
    @Override
    public void addComponent(UIContainer container) {
        super.addComponent(container);
        if (anchorChildren)
            container.setAnchor(this.drawPosition);
    }

    protected void updateChildrenAnchors() {
        if (!anchorChildren)
            return;
        components.forEach((c) -> c.setAnchor(this.drawPosition));
    }

    public boolean isAnchorChildren() {
        return anchorChildren;
    }

    public void setAnchorChildren(boolean anchorChildren) {
        this.anchorChildren = anchorChildren;
        if (anchorChildren)
            updateChildrenAnchors();
    }

    public Anchor getDrawPosition() {
        return drawPosition;
    }

    public void setDrawPosition(Anchor drawPosition) {
        if (drawPosition == this.drawPosition)
            return;
        this.drawPosition = drawPosition;
        updateChildrenAnchors();
    }

}
