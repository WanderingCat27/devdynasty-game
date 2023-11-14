package Level;

import java.awt.MouseInfo;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import Engine.Config;
import Engine.GameWindow;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Mouse;
import Engine.ScreenManager;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;
import java.awt.Point;
import Maps.NewMap;
import Maps.TitleScreenMap;
import Screens.PlayLevelScreen;
import Level.Map;
import Level.Player;

public abstract class Player extends GameObject {
  // values that affect player movement
  // these should be set in a subclass
  protected float walkSpeed = 0;
  protected int interactionRange = 5;
  protected Direction currentWalkingXDirection;
  protected Direction currentWalkingYDirection;
  protected Direction lastWalkingXDirection;
  protected Direction lastWalkingYDirection;
  protected boolean walking = false;

  // values used to handle player movement
  protected float moveAmountX, moveAmountY;
  protected float lastAmountMovedX, lastAmountMovedY;
  protected GameObject player;

  // values used to keep track of player's current state
  protected PlayerState playerState;
  protected PlayerState previousPlayerState;
  protected Direction facingDirection;
  protected Direction lastMovementDirection;

  // classes that listen to player events can be added to this list
  protected ArrayList<PlayerListener> listeners = new ArrayList<>();

  // define keys
  protected KeyLocker keyLocker = new KeyLocker();
  protected Key MOVE_LEFT_KEY = Key.A;
  protected Key MOVE_RIGHT_KEY = Key.D;
  protected Key MOVE_UP_KEY = Key.W;
  protected Key MOVE_DOWN_KEY = Key.S;
  protected Key INTERACT_KEY = Key.ENTER;
  protected Key SPRINT_KEY = Key.SHIFT;
  public SoundPlayer walkingSoundPlayer;
  protected boolean mouseOnScreen;
  protected Point p;
  private float sprintMultiplier = 1.8f;
  

  public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
    super(spriteSheet, x, y, startingAnimationName);
    facingDirection = Direction.RIGHT;
    playerState = PlayerState.STANDING;
    previousPlayerState = playerState;
    this.affectedByTriggers = true;
    walkingSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/footstep.wav", (int) PlayLevelScreen.getCurrentWalkVolume());
    walkingSoundPlayer.pause();
  }

  public void update() {
    moveAmountX = 0;
    moveAmountY = 0;

    // if player is currently playing through level (has not won or lost)
    // update player's state and current actions, which includes things like
    // determining how much it should move each frame and if its walking or jumping
    do {
      previousPlayerState = playerState;
      handlePlayerState();
      if (previousPlayerState != PlayerState.WALKING && playerState == PlayerState.WALKING) {
        walkingSoundPlayer.clip.loop(Clip.LOOP_CONTINUOUSLY);
        // walkingSoundPlayer.play();
      } else if (playerState != PlayerState.WALKING) {
        walkingSoundPlayer.pause();
      }
    } while (previousPlayerState != playerState);

    // move player with respect to map collisions based on how much player needs to
    // move this frame
    if (playerState != PlayerState.INTERACTING) {
      lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
      lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
    }

    handlePlayerAnimation();
    updateLockedKeys();

    // update player's animation
    super.update();
  }

  // based on player's current state, call appropriate player state handling
  // method
  protected void handlePlayerState() {
    switch (playerState) {
      case STANDING:
        playerStanding();
        break;
      case WALKING:
        playerWalking();
        break;
      case INTERACTING:
        playerInteracting();
        break;
    }
  }

  // player STANDING state logic
  protected void playerStanding() {
    if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
      keyLocker.lockKey(INTERACT_KEY);
      map.entityInteract(this);
    }

    // if a walk key is pressed, player enters WALKING state
    if (isWalking()) {
      playerState = PlayerState.WALKING;
    }
  }

  private boolean isWalking() {
    // ^ = Xor
    // will pass if any of the movement keys are pressed but not if both of either
    // pair are pressed
    return ((Keyboard.isKeyDown(MOVE_LEFT_KEY) ^ Keyboard.isKeyDown(MOVE_RIGHT_KEY)
        || (Keyboard.isKeyDown(MOVE_UP_KEY)) ^ Keyboard.isKeyDown(MOVE_DOWN_KEY)) && Mouse.mouseOnScreen);
  }

  // player WALKING state logic
  protected void playerWalking() {
    if (!isWalking()) {
      // walkingSoundPlayer.pause();
      playerState = PlayerState.STANDING;
    }

    float adjustedSpeed = Keyboard.isKeyDown(SPRINT_KEY) ? walkSpeed * sprintMultiplier : walkSpeed;
    if(Config.debugFastSprint && Keyboard.isKeyDown(Key.Q)) adjustedSpeed = walkSpeed*5;

    if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
      keyLocker.lockKey(INTERACT_KEY);
      map.entityInteract(this);
    }

    // if walk left key is pressed, move player to the left
    if (Keyboard.isKeyDown(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
      if (this.getX() > 0) {
        moveAmountX -= adjustedSpeed;

        facingDirection = Direction.LEFT;
        currentWalkingXDirection = Direction.LEFT;
        lastWalkingXDirection = Direction.LEFT;
      } else {
        moveAmountX = 0;
        // moveAmountY = 0;
      }
    }

    // if walk right key is pressed, move player to the right
    else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY) && Keyboard.isKeyUp(MOVE_LEFT_KEY)) {
      if (this.getX2() < map.getWidthPixels()) {

        moveAmountX += adjustedSpeed;
        facingDirection = Direction.RIGHT;
        currentWalkingXDirection = Direction.RIGHT;
        lastWalkingXDirection = Direction.RIGHT;
      } else {
        moveAmountX = 0;
        // moveAmountY = 0;
      }
    } else {
      currentWalkingXDirection = Direction.NONE;
    }

    if (Keyboard.isKeyDown(MOVE_UP_KEY) && Keyboard.isKeyUp(MOVE_DOWN_KEY)) {
      if (this.getY() > 0) {

        moveAmountY -= adjustedSpeed;

        facingDirection = Direction.UP;
        currentWalkingYDirection = Direction.UP;
        lastWalkingYDirection = Direction.UP;
      } else {
        moveAmountY = 0;
        // moveAmountX = 0;
      }

    } else if (Keyboard.isKeyDown(MOVE_DOWN_KEY) && Keyboard.isKeyUp(MOVE_UP_KEY)) {
      if (this.getY2() < map.getHeightPixels()) {

        moveAmountY += adjustedSpeed;

        facingDirection = Direction.DOWN;
        currentWalkingYDirection = Direction.DOWN;
        lastWalkingYDirection = Direction.DOWN;
      } else {
        moveAmountY = 0;
        // moveAmountX = 0;
      }
    } else {
      currentWalkingYDirection = Direction.NONE;
    }

    if ((currentWalkingXDirection == Direction.RIGHT || currentWalkingXDirection == Direction.LEFT)
        && currentWalkingYDirection == Direction.NONE) {
      lastWalkingYDirection = Direction.NONE;
    }

    if ((currentWalkingYDirection == Direction.UP || currentWalkingYDirection == Direction.DOWN)
        && currentWalkingXDirection == Direction.NONE) {
      lastWalkingXDirection = Direction.NONE;
    }
  }

  // player INTERACTING state logic -- intentionally does nothing so player is
  // locked in place while a script is running
  protected void playerInteracting() {
  }

  protected void updateLockedKeys() {
    if (Keyboard.isKeyUp(INTERACT_KEY) && playerState != PlayerState.INTERACTING) {
      keyLocker.unlockKey(INTERACT_KEY);
    }
  }

  // anything extra the player should do based on interactions can be handled here
  protected void handlePlayerAnimation() {
    if (playerState == PlayerState.STANDING) {
      // sets animation to a STAND animation based on which way player is facing
      if (facingDirection == Direction.RIGHT) {
        this.currentAnimationName = "STAND_RIGHT";
      } else if (facingDirection == Direction.LEFT) {
        this.currentAnimationName = "STAND_LEFT";
      } else if (facingDirection == Direction.UP) {
        this.currentAnimationName = "STAND_UP";
      } else if (facingDirection == Direction.DOWN) {
        this.currentAnimationName = "STAND_DOWN";
      }
    } else if (playerState == PlayerState.WALKING) {
      // sets animation to a WALK animation based on which way player is facing
      if (facingDirection == Direction.RIGHT) {
        this.currentAnimationName = "WALK_RIGHT";
      } else if (facingDirection == Direction.LEFT) {
        this.currentAnimationName = "WALK_LEFT";
      } else if (facingDirection == Direction.UP) {
        this.currentAnimationName = "WALK_UP";
      } else if (facingDirection == Direction.DOWN) {
        this.currentAnimationName = "WALK_DOWN";
      }
    } else if (playerState == PlayerState.INTERACTING) {
      if (walking) {
        if (facingDirection == Direction.RIGHT) {
          this.currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
          this.currentAnimationName = "WALK_LEFT";
        } else if (facingDirection == Direction.UP) {
          this.currentAnimationName = "WALK_UP";
        } else if (facingDirection == Direction.DOWN) {
          this.currentAnimationName = "WALK_DOWN";
        }
      } else {
        if (facingDirection == Direction.RIGHT) {
          this.currentAnimationName = "STAND_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
          this.currentAnimationName = "STAND_LEFT";
        } else if (facingDirection == Direction.UP) {
          this.currentAnimationName = "STAND_UP";
        } else if (facingDirection == Direction.DOWN) {
          this.currentAnimationName = "STAND_DOWN";
        }
      }
    }
  }

  @Override
  public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
  }

  @Override
  public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
  }

  // other entities can call this method to hurt the player
  public void hurtPlayer(MapEntity mapEntity) {

  }

  public SoundPlayer getWalkingSoundPlayer() {
    return walkingSoundPlayer;
  }

  public PlayerState getPlayerState() {
    return playerState;
  }

  public void setPlayerState(PlayerState playerState) {
    this.playerState = playerState;
  }

  public Direction getFacingDirection() {
    return facingDirection;
  }

  public void setFacingDirection(Direction facingDirection) {
    this.facingDirection = facingDirection;
  }

  public void addListener(PlayerListener listener) {
    listeners.add(listener);
  }

  public boolean setWalking(boolean walking) {
    this.walking = walking;
    return walking;
  }

  public Rectangle getInteractionRange() {
    return new Rectangle(
        getBounds().getX1() - interactionRange,
        getBounds().getY1() - interactionRange,
        getBounds().getWidth() + (interactionRange * 2),
        getBounds().getHeight() + (interactionRange * 2));
  }

  public Key getInteractKey() {
    return INTERACT_KEY;
  }

  public Direction getCurrentWalkingXDirection() {
    return currentWalkingXDirection;
  }

  public Direction getCurrentWalkingYDirection() {
    return currentWalkingYDirection;
  }

  public Direction getLastWalkingXDirection() {
    return lastWalkingXDirection;
  }

  public Direction getLastWalkingYDirection() {
    return lastWalkingYDirection;
  }

  public void stand(Direction direction) {
    facingDirection = direction;
    if (direction == Direction.RIGHT) {
      this.currentAnimationName = "STAND_RIGHT";
    } else if (direction == Direction.LEFT) {
      this.currentAnimationName = "STAND_LEFT";
    } else if (direction == Direction.DOWN) {
      this.currentAnimationName = "STAND_DOWN";
    } else if (direction == Direction.UP) {
      this.currentAnimationName = "STAND_UP";
    }
  }

  public void walk(Direction direction, float speed) {
    facingDirection = direction;
    if (direction == Direction.RIGHT) {
      this.currentAnimationName = "WALK_RIGHT";
    } else if (direction == Direction.LEFT) {
      this.currentAnimationName = "WALK_LEFT";
    } else if (direction == Direction.DOWN) {
      this.currentAnimationName = "WALK_DOWN";
    } else if (direction == Direction.UP) {
      this.currentAnimationName = "WALK_UP";
    }
    // else {
    // if (this.currentAnimationName.contains("RIGHT")) {
    // this.currentAnimationName = "WALK_RIGHT";
    // }
    // else {
    // this.currentAnimationName = "WALK_LEFT";
    // }
    // }
    if (direction == Direction.UP) {
      moveY(-speed);
    } else if (direction == Direction.DOWN) {
      moveY(speed);
    } else if (direction == Direction.LEFT) {
      moveX(-speed);
    } else if (direction == Direction.RIGHT) {
      moveX(speed);
    }
  }

  public void setMoveAmountX(float moveAmountX2) {
  }

  public void setMoveAmountY(float moveAmountY2) {
  }

  public float getMoveAmountX() {
    return 0;
  }

  public float getMoveAmountY() {
    return 0;
  }

  public void stopSound() {
    walkingSoundPlayer.pause();
  }

}
