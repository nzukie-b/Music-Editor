package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.PileType;
import java.util.List;
import java.util.ArrayList;

/**
 * Creates a pile. A Pile holds the list of cards for that PileType.
 * see @link {PileType} It also keeps track of the number of piles of a specific
 * type; there are 4 foundation piles, 4+ Cascade piles, and 1+ open piles.
 */
public class Pile implements IPile {
  private List<List<Card>> cards;
  private PileType pt;
  private int len;

  /**
   * Constructor for a pile. Other constructor for a Pile was removed, a method
   * set() was created to change the fields instead of creating a new Pile.
   */
  public Pile() {
    this.cards = new ArrayList<>();
    this.pt = null;
    this.len = 0;
  }

  @Override
  public void set(PileType type, int length) {
    this.cards = initCards(length);
    this.pt = type;
    this.len = length;
  }

  /**
   * Initializes the cards in the pile.
   *
   * @param length How many stacks are in the pile.
   * @return The List of List of Cards, to be assigned to the cards.
   */
  private List<List<Card>> initCards(int length) {
    List<List<Card>> cardList = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      List<Card> innerCardList = new ArrayList<>();
      cardList.add(innerCardList);
    }
    return cardList;
  }

  @Override
  public void deal(List<Card> deck) {
    for (int i = 0; i < len; i++) {
      List<Card> innerList = new ArrayList<>();
      for (int j = 0; j < 52; j++) {
        if ((j % len) == i ) {
          innerList.add(deck.get(j));
        }
      }
      cards.add(i, innerList);
    }
  }

  @Override
  public String toString() {
    if (len == 0) {
      return "";
    }
    else {
      String pileLet;
      switch (pt) {
        case CASCADE:
          pileLet = "C";
          break;
        case FOUNDATION:
          pileLet = "F";
          break;
        case OPEN:
          pileLet = "O";
          break;
        default:
          throw new IllegalArgumentException();
      }

      String gs = "";

      for (int i = 0; i < len; i++) {
        List<Card> pilei = cards.get(i);
        gs = gs + String.format("%s%d:", pileLet, i + 1);
        if (pilei.size() == 0) {
          if (!(pileLet.equals("C") && (i == (len - 1)))) {
            gs = gs + '\n';
          }
        }
        else {
          for (int j = 0; j < pilei.size(); j++) {
            if ((j == (pilei.size() - 1))
                && (pileLet.equals("C"))
                && (i == (len - 1))) {
              gs = gs + " " + pilei.get(j).toString();
            }
            else if (j == (pilei.size() - 1)) {
              gs = gs + " " + pilei.get(j).toString() + '\n';
            }
            else {
              gs = gs + " " + pilei.get(j).toString() + ",";
            }
          }
        }
      }
      return gs;
    }
  }

  @Override
  public boolean isEmpty() {
    boolean empty = true;
    for (int i = 0; i < len; i++) {
      empty = empty && cards.get(i).size() == 0;
    }
    return empty;
  }

  @Override
  public boolean isFull() {
    if (len == 0) {
      return false;
    }
    else {
      boolean full = true;
      for (int i = 0; i < 4; i++) {
        full = cards.get(i).size() == 13;
        if (!full) {
          break;
        }
      }
      return full;
    }
  }

  @Override
  public void move(int pileNumber, int cardIndex, IPile destPile,
      int destPileNumber, int empties) {

    if (pileNumber >= len) {
      throw new IllegalArgumentException("Wrong pile number.");
    }

    else {
      List<Card> innerList = cards.get(pileNumber);
      destPile.moveHelp(cardIndex, innerList, destPileNumber, empties);
    }
  }

  @Override
  public void moveHelp(int cardIndex, List<Card> innerList, int destPileNumber,
      int empties) {

    if (destPileNumber >= len) {
      throw new IllegalArgumentException("Wrong destination pile number");
    }
    if (cardIndex > innerList.size() - 1) {
      throw new IllegalArgumentException("Wrong index.");
    }

    List<Card> destInnerList = cards.get(destPileNumber);
    List<Card> cardsMoved = new ArrayList<>(innerList
        .subList(cardIndex, innerList.size()));

    if (cardsMoved.size() > empties) {
      throw new IllegalArgumentException("Can't move that many cards.");
    }
    if (this.isValidMove(cardsMoved, destInnerList, this)) {
      innerList.removeAll(cardsMoved);
      destInnerList.addAll(cardsMoved);
    }
    else {
      throw new IllegalArgumentException("Can't move that here.");
    }
  }


  /**
   * Determines if a move is valid. A valid move is one of:
   * <ul>
   *   <li> An Ace Card being moved onto a foundation pile. </li>
   *   <li> A Card being moved onto a foundation pile. It must be the same suit
   *        and the value must be one higher than the destination card. </li>
   *   <li> A Card being moved onto an open pile. </li>
   *   <li> A card being moved onto a cascade pile from either a cascade or
   *        open pile. The card must be the opposite suit (SPADES and CLUBS are
   *        opposite HEARTS and DIAMONDS) and have a value of one less than
   *        the destination card.</li>
   * </ul>
   *
   * @param moveList the card that is being moved.
   * @param destList the list of cards in the pil the card is being moved onto.
   * @param destPile the pile the card is being moved onto.
   * @return true if the card can be moved onto the pile.
   */
  private boolean isValidMove(List<Card> moveList, List<Card> destList, Pile destPile) {
    Card firstCard = moveList.get(0);

    switch (destPile.pt) {
      case FOUNDATION:
        if (moveList.size() > 1) {
          throw new IllegalArgumentException("Move one card onto foundation.");
        }
        if (destList.isEmpty()) {
          return firstCard.isAce();
        }
        else {
          Card lastCard = destList.get(destList.size() - 1);
          return ((firstCard.compValue(lastCard) == 1) && firstCard.sameSuit(lastCard));
        }
      case OPEN:
        if (moveList.size() > 1) {
          throw new IllegalArgumentException("Move one card onto open.");
        }
        return destList.size() < 1;
      case CASCADE:
        if (destList.isEmpty()) {
          return true;
        }
        else {
          Card lastCard = destList.get(destList.size() - 1);
          return ((firstCard.oppSuit(lastCard) && (firstCard.compValue(lastCard) == -1))
              && (validBuild(moveList)))
              || (destPile.cards.size() == 0);
        }
      default:
        throw new IllegalArgumentException("Can't move that here");
    }
  }

  private boolean validBuild(List<Card> cardsMoved) {
    List<Card> moveList = new ArrayList<>(cardsMoved);
    if (moveList.size() > 1) {
      Card last = moveList.get(moveList.size() - 1);
      Card seclast = moveList.get(moveList.size() - 2);
      if (last.oppSuit(seclast) && (last.compValue(seclast)) == -1) {
        moveList.remove(moveList.size() - 1);
        validBuild(moveList);
      }
      else {
        throw new IllegalArgumentException("Not valid build.");
      }
    }
    return true;
  }

  @Override
  public int numEmpty() {
    int empties = 0;
    for (int i = 0; i < len; i++) {
      if (cards.get(i).size() == 0) {
        empties++;
      }
    }
    return empties;
  }
}
