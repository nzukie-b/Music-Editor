package cs3500.hw02;

import cs3500.hw04.IPile;
import cs3500.hw04.Pile;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Creates the model for a freecell game. Creates a valid deck, starts the
 * game, moves the cards, checks if the game is over, and prints the game
 * state as a string.
 */
public class FreecellModel implements FreecellOperations<Card> {

  /*
   * Access to these members (deck, casPile, foundPile, openPile) were changed
   * from private to protected so that the new class FreecellMultiModel() could
   * access them.
   */
  protected IPile casPile;
  protected IPile foundPile;
  protected IPile openPile;

  /**
   * Constructor for a FreecellModel.
   */
  public FreecellModel() {
    this.casPile = new Pile();
    this.foundPile = new Pile();
    this.openPile = new Pile();
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      deck.add(i, new Card(i));
    }
    return deck;
  }

  /*
   * Instead of setting the piles equal to new piles, changed to use the void
   * method set().
   */
  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles,
      boolean shuffle) {
    if (numCascadePiles < 4) {
      throw new IllegalArgumentException("Must have more than 4 cascades");
    }

    if (numOpenPiles < 1) {
      throw new IllegalArgumentException("Must have more than 1 open");
    }

    if (deck.size() != 52 || this.dupCard(deck)) {
      throw new IllegalArgumentException("Incomplete deck ");
    }

    if (shuffle) {
      Collections.shuffle(deck);
    }

    casPile.set(PileType.CASCADE, numCascadePiles);
    foundPile.set(PileType.FOUNDATION, 4);
    openPile.set(PileType.OPEN, numOpenPiles);

    casPile.deal(deck);
  }

  /**
   * Determines if this deck is valid. A valid deck has exactly 52 cards and there
   * are no duplicates.
   *
   * @param deck The list of cards beinc checked if it is valid.
   * @return true if the deck is invalid.
   */
  protected boolean dupCard(List<Card> deck) {
    boolean invDeck = false;
    for (int i = 0; i < deck.size(); i++) {
      for (int j = 1; j < deck.size(); j++) {
        if (i == j) {
          break;
        }
        else {
          invDeck = invDeck
            || (deck.get(i).sameSuit(deck.get(j))
               && (deck.get(i).compValue(deck.get(j)) == 0));
        }
      }
    }
    return invDeck;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
        PileType destination, int destPileNumber) {
    if (pileNumber < 0 || cardIndex < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("Can't be negative");
    }
    switch (source) {
      case FOUNDATION:
        throw new IllegalArgumentException("Can't move from foundation.");
      case OPEN:
        switch (destination) {
          case FOUNDATION:
            openPile.move(pileNumber, cardIndex, foundPile, destPileNumber, 1);
            break;
          case OPEN:
            openPile.move(pileNumber, cardIndex, openPile, destPileNumber, 1);
            break;
          case CASCADE:
            openPile.move(pileNumber, cardIndex, casPile, destPileNumber, 1);
            break;
          default:
            throw new IllegalArgumentException();
        }
        break;
      case CASCADE:
        switch (destination) {
          case FOUNDATION:
            casPile.move(pileNumber, cardIndex, foundPile, destPileNumber, 1);
            break;
          case OPEN:
            casPile.move(pileNumber, cardIndex, openPile, destPileNumber, 1);
            break;
          case CASCADE:
            casPile.move(pileNumber, cardIndex, casPile, destPileNumber, 1);
            break;
          default:
            throw new IllegalArgumentException();
        }
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public boolean isGameOver() {
    return casPile.isEmpty() && openPile.isEmpty() && foundPile.isFull();
  }

  @Override
  public String getGameState() {
    return foundPile.toString() + openPile.toString() + casPile.toString();
  }
}
