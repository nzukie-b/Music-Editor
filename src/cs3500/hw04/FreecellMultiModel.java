package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

/**
 * Freecell model that allows for multiple cards to be moved at once. They must
 * have a valid build in order to be moved.
 */
public class FreecellMultiModel extends FreecellModel {

  /**
   * Constructor for a FreecellMultiModel. Has the same arguments as a FreecellModel.
   */
  FreecellMultiModel() {
    super();
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
      PileType destination, int destPileNumber) {
    if (pileNumber < 0 || cardIndex < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("Can't be negative");
    }

    int mtCas = casPile.numEmpty();
    int mtOpen = openPile.numEmpty();
    int empty = (int) ((mtOpen + 1) * Math.pow(2, mtCas));

    switch (source) {
      case FOUNDATION:
        throw new IllegalArgumentException("Can't move from foundation.");
      case OPEN:
        switch (destination) {
          case FOUNDATION:
            openPile.move(pileNumber, cardIndex, foundPile, destPileNumber, empty);
            break;
          case OPEN:
            openPile.move(pileNumber, cardIndex, openPile, destPileNumber, empty);
            break;
          case CASCADE:
            openPile.move(pileNumber, cardIndex, casPile, destPileNumber, empty);
            break;
          default:
            throw new IllegalArgumentException();
        }
        break;
      case CASCADE:
        switch (destination) {
          case FOUNDATION:
            casPile.move(pileNumber, cardIndex, foundPile, destPileNumber, empty);
            break;
          case OPEN:
            casPile.move(pileNumber, cardIndex, openPile, destPileNumber, empty);
            break;
          case CASCADE:
            casPile.move(pileNumber, cardIndex, casPile, destPileNumber, empty);
            break;
          default:
            throw new IllegalArgumentException();
        }
        break;
      default:
        throw new IllegalArgumentException();
    }
  }
}
