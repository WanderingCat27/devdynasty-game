package ui;

public class Margin {
    protected int leftMargin, rightMargin, topMargin, bottomMargin;

    public Margin(int leftMargin, int rightMargin, int topMargin, int bottomMargin) {
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        this.topMargin = topMargin;
        this.bottomMargin = bottomMargin;
    }

    public Margin(int xMargin,int yMargin) {
        this(xMargin, xMargin, yMargin, yMargin);
    }
    public Margin() {
        
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public int getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(int rightMargin) {
        this.rightMargin = rightMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    public void setYMargin(int marginY) {
        setBottomMargin(marginY);
        setTopMargin(marginY);
    }

    public void setXMargin(int marginX) {
        setLeftMargin(marginX);
        setRightMargin(marginX);
    }

    public void setMargin(int margin) {
        setXMargin(margin);
        setYMargin(margin);
    }

}
