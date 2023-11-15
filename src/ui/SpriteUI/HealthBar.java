package ui.SpriteUI;

import java.awt.Color;

import Engine.GraphicsHandler;

public class HealthBar extends SolidSpriteUI{

    private Color innerColor;
    private Color outerColor;
    private int startingHealth;
    private int startWidth;
    private int currHealthWidth;

    public HealthBar(int x, int y, int width, int height, Color innerColor, Color outerColor, int startingHealth){
        super(x, y, width, height, outerColor);
        this.innerColor = innerColor;
        this.outerColor = outerColor;
        this.startingHealth = startingHealth;
        this.startWidth = width;
        this.currHealthWidth = width;
    }





    @Override
    public void draw(GraphicsHandler g) {
        g.drawFilledRectangle(getXAbs(), getYAbs(), startWidth+10, getHeight(), outerColor);
        g.drawFilledRectangle(getXAbs(), getYAbs()+5, currHealthWidth, getHeight()-10, innerColor);
        //super.draw(g);
    }


    public void update(int health){
        System.out.println("Enemy Health: "+ health + " Starting Health: " + startingHealth);
        double ratio = ((health*1.0)/(startingHealth*1.0)); 
        currHealthWidth = (int)Math.round(startWidth*ratio);
        System.out.println("Health Bar Decreased, currHealthWidth = " + currHealthWidth);
    }
    
}
