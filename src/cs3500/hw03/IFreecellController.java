package cs3500.hw03;

import cs3500.hw02.FreecellOperations;
import java.util.List;

/**
 * The interface of the Freecell Controller, parameterized over type K so that
 * it works with different Card implementations.
 */
public interface IFreecellController<K> {

  /**
   * Starts the game and allows the player to play.
   *
   * @param deck          The deck of cards the game will be played with
   * @param model         The Freecell model
   * @param numCascades   Number of Cascade piles, 4-8
   * @param numOpens      Number of Open piles, 1-4
   * @param shuffle       Shuffles the cards if true, does not if false
   * @throws IllegalArgumentException   Throws exception if deck, numCascades,
   *                                    or numOpens is invalid
   */
  public void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
          int numOpens, boolean shuffle)
          throws IllegalArgumentException;

}
