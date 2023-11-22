package Level;

import Maps.NewMap;
import Maps.PrehistoricMap;
import Maps.ScienceLabMap;
import Maps.TestMap;
import Maps.Future.FloorOneMap;
import Maps.Future.FloorThreeMap;
import Maps.Future.FloorTwoMap;
import Maps.Future.FutureMap;
import Maps.Future.ReceptionMap;
import Maps.WildWest.OldCowboyHouseMap;
import Maps.WildWest.SaloonMap;
import Maps.WildWest.WWBuildingOne;
import Maps.WildWest.WWBuildingTwo;
import Maps.WildWest.WildWestMap;

public class LevelManager {

  // public static final Level TEST = new Level(new NewMap());
  public static final Level WILDWEST = new Level() {

    @Override
    protected Map getMapInstance() {
      return new WildWestMap();
    }

  };
  public static final Level LAB = new Level() {

    @Override
    protected Map getMapInstance() {
      return new ScienceLabMap();
    }

  };

    public static final Level PREHISTORIC = new Level() {

    @Override
    protected Map getMapInstance() {
      return new PrehistoricMap();
    }

  };

  public static final Level WWBUILDINGLEFT = new Level() {

    @Override
    protected Map getMapInstance() {
      return new WWBuildingOne();
    }

  };

  public static final Level WWBUILDINGRIGHT = new Level() {

    @Override
    protected Map getMapInstance() {
      return new WWBuildingTwo();
    }

  };

    public static final Level RECEPTION = new Level() {
      @Override
      protected Map getMapInstance() {
        return new ReceptionMap();
      };
    };
    public static final Level FLOOR1 = new Level() {
      @Override
      protected Map getMapInstance() {
        return new FloorOneMap();
      };
    };

    public static final Level FLOOR2 = new Level() {
      @Override
      protected Map getMapInstance() {
        return new FloorTwoMap();
      };
    };
    public static final Level FLOOR3 = new Level() {
      @Override
      protected Map getMapInstance() {
        return new FloorThreeMap();
      };
    };
  // public static final Level OLD_TEST = new Level(new TestMap());
  public static Level SALOON_INSIDE = new Level() {

    @Override
    protected Map getMapInstance() {
      return new SaloonMap();
    }

  };

  public static Level FUTURE = new Level() {

    @Override
    protected Map getMapInstance() {
      return new FutureMap();
    }

  };

  public static Level OCHOUSE = new Level() {

    @Override
    protected Map getMapInstance() {
      return new OldCowboyHouseMap();
    }

  };

  private static Level currentLevel;

  private LevelManager() {
  }

  public static void setLevel(Level level) {
    if (level == null)
      return;
    if (currentLevel != null)
      currentLevel.unload();
    currentLevel = level;
    currentLevel.load();
    if (currentLevel.getSoundPlayer() != null)
      currentLevel.getSoundPlayer().play();
  }

  public static Level getCurrentLevel() {
    return currentLevel;
  }

  public static void initStartMap() {
    setLevel(LevelManager.LAB);
  }

}