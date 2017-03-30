package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * Creates a freecell model depending on the game type. If SINGLEMOVE, then only
 * one card can be moved at a time. If MULTIMOVE, then multiple cards can be
 * moved at a time.
 */
public class FreecellModelCreator {

  /**
   * Constructor for a FreecellModelCreator. Has nothing to initialize.
   */
  public FreecellModelCreator() {
    //No members to initialize
  }

  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Creates a Freecell game based on the gametype that is passed in.
   *
   * @param type type of game to be played, either single-move or multi-move
   * @return a freecell model, with either single- or multi-move capabilty
   */
  public static FreecellOperations create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new FreecellModel();
      case MULTIMOVE:
        return new FreecellMultiModel();
      default:
        throw new IllegalArgumentException("Wrong game type");
    }
  }
}
