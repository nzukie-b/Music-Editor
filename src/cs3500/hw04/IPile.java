package cs3500.hw04;

    import cs3500.hw02.Card;
    import cs3500.hw02.PileType;
    import java.util.List;

/**
 * Interface for a @link{Pile}.
 */
public interface IPile {
  void set(PileType type, int length);

  /**
   * Deals the cards to the Cascade Piles. They are dealt so that if there are,
   * say, 6 cascade piles, cards 0, 6, 12, etc. go to pile 1. 1, 7, 13, etc. go
   * to pile 2, and so forth.
   *
   * @param deck a valid deck is dealt to the cascade piles.
   */
  void deal(List<Card> deck);

  /**
   * Creates a String with the cards shown in the piles.
   *
   * @return a String of the current game state.
   */
  @Override
  String toString();

  /**
   * Determines if the pile is empty.
   *
   * @return true if the pile is empty.
   */
  boolean isEmpty();

  /**
   * Determines if the pile is full.
   *
   * @return true if all 13 cards are all 4 piles.
   */
  boolean isFull();

  /**
   * Moves a card from one pile onto another pile.
   *
   * @param pileNumber the pile number of the card to be moved.
   * @param cardIndex the index of the card to be moved.
   * @param destPile the pile the card is being moved onto.
   * @param destPileNumber the pile number the card is being moved onto.
   * @param empties the number of cascade / open piles
   */
  void move(int pileNumber, int cardIndex, IPile destPile,
      int destPileNumber, int empties);

  /**
   * Helper method to move. Removes the card(s) from the pile, and adds them to
   * the destination.
   *
   * @param cardIndex index of the first card that is being moved. In a single move
   *                  game, it is the last index. Multi move games can be any, as
   *                  long as the cards moved have a valid build.
   * @param innerList The stack of cards that the card(s) are being moved from.
   * @param destPileNumber The destination pile.
   * @param empties Number of empty piles necessary for the move to happen.
   */
  void moveHelp(int cardIndex, List<Card> innerList, int destPileNumber, int empties);

  /**
   * Number of empty stacks this pile has.
   *
   * @return the number of empty stacks
   */
  int numEmpty();
}
