package Level;

import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Utils.Direction;

import java.util.HashMap;

// This class is a base class for all npcs in the game -- all npcs should extend from it
public class NPC extends MapEntity {
    protected int id = 0;
    protected String pathToImage;
    protected double health;

    public NPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.id = id;
    }

    public NPC(int id, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.id = id;
    }

    public NPC(int id, float x, float y, Frame[] frames) {
        super(x, y, frames);
        this.id = id;
    }

    public NPC(int id, float x, float y, Frame frame) {
        super(x, y, frame);
        this.id = id;
    }

    public NPC(int id, float x, float y) {
        super(x, y);
        this.id = id;
    }

    public NPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, double startingHealth) {
        super(x, y, spriteSheet, startingAnimation);
        this.id = id;
        this.health = startingHealth;
    }

    public double getHealth(){
        return health;
    }
    
    public int getId() { return id; }


    public void facePlayer(Player player)
    {
        double npcCenterX = getBounds().getX() + (getBounds().getWidth() / 2);
        double npcCenterY = getBounds().getY() + (getBounds().getHeight() / 2);

        double playerCenterX = player.getBounds().getX() + (player.getBounds().getWidth() / 2);
        double playerCenterY = player.getBounds().getY() + (player.getBounds().getHeight() / 2);

        double deltaX = playerCenterX - npcCenterX;
        double deltaY = playerCenterY - npcCenterY;

        // Calculate the angle between NPC and player
        double angle = Math.atan2(deltaY, deltaX);

        // Convert angle to degrees
        double angleDegrees = Math.toDegrees(angle);

        // Determine the direction based on the angle
        if (angleDegrees >= -45 && angleDegrees < 45) {
            this.currentAnimationName = "STAND_RIGHT";
        } else if (angleDegrees >= 45 && angleDegrees < 135) {
            this.currentAnimationName = "STAND_DOWN";
        } else if (angleDegrees >= -135 && angleDegrees < -45) {
            this.currentAnimationName = "STAND_UP";
        } else {
            this.currentAnimationName = "STAND_LEFT";
        }
    }

    public void stand(Direction direction) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        }
        else if(direction == Direction.DOWN)
        {
            this.currentAnimationName = "STAND_DOWN";
        }
        else if(direction == Direction.UP)
        {
            this.currentAnimationName = "STAND_UP";
        }
        else {
            if (this.currentAnimationName.contains("RIGHT")) {
                this.currentAnimationName = "STAND_RIGHT";
            }
            else {
                this.currentAnimationName = "STAND_LEFT";
            }
        }
    }

    public void walk(Direction direction, float speed) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        }
        else if(direction == Direction.DOWN) {
            this.currentAnimationName = "WALK_DOWN";
        }
        else if(direction == Direction.UP)
        {
            this.currentAnimationName = "WALK_UP";
        }
        else {
            if (this.currentAnimationName.contains("RIGHT")) {
                this.currentAnimationName = "WALK_RIGHT";
            }
            else {
                this.currentAnimationName = "WALK_LEFT";
            }
        }
        if (direction == Direction.UP) {
            moveY(-speed);
        }
        else if (direction == Direction.DOWN) {
            moveY(speed);
        }
        else if (direction == Direction.LEFT) {
            moveX(-speed);
        }
        else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    public void update(Player player) {
        super.update();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
