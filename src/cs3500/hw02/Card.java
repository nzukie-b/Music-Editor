package cs3500.hw02;


/**
 * Defines a card class. A card has a Suit @link {Suit} and a
 * Value @link {Value}.
 */
public class Card {
  private final Suit s;
  private final Value v;

  public Card(int c) {
    this.s = defSuit(c);
    this.v = defVal(c);
  }

  public Card(Suit s, Value v) {
    this.s = s;
    this.v = v;
  }

  /**
   * Determines if this card is the same suit as that card.
   *
   * @param that the card this suit is being compared to.
   * @return true if the suits are the same.
   */
  public boolean sameSuit(Card that) {
    return this.s == that.s;
  }

  /**
   * Determines if this card has the opposite suit as that card.
   * SPADES and CLUBS are opposit suits of HEARTS and DIAMONDS.
   *
   * @param that the card this suit is being compared to.
   * @return true if the suits are opposite.
   */
  public boolean oppSuit(Card that) {
    return s.opp(that.s);
  }

  /**
   * Compares the value of this card to that card. The result is positive if
   * this value is bigger than that, negative if that value is bigger than
   * this, and zero if the values are equal.
   *
   * @param that the card this value is being compared to.
   * @return an int comparing the values of this card.
   */
  public int compValue(Card that) {
    return v.getVal() - that.v.getVal();
  }

  /**
   * Creates a String of this card, with the value before the suit with no
   * space between.
   *
   * @return a String of this card.
   */
  @Override
  public String toString() {
    return v.toString() + s.toString();
  }

  /**
   * Defines the suit for this card based on the value of the count.
   *
   * @param count A counter that helps define the suit of the card.
   * @return Which suit this card is.
   */
  private Suit defSuit(int count) {
    count = count % 4;
    switch (count) {
      case 0:
        return Suit.SPADES;
      case 1:
        return Suit.CLUBS;
      case 2:
        return Suit.DIAMONDS;
      case 3:
        return Suit.HEARTS;
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * Defines the value for this card based on the value of the count.
   *
   * @param count A counter that helps define the value of this card.
   * @return which value this card is.
   */
  private Value defVal(int count) {
    count = count % 13;
    switch (count) {
      case 0:
        return Value.ACE;
      case 1:
        return Value.TWO;
      case 2:
        return Value.THREE;
      case 3:
        return Value.FOUR;
      case 4:
        return Value.FIVE;
      case 5:
        return Value.SIX;
      case 6:
        return Value.SEVEN;
      case 7:
        return Value.EIGHT;
      case 8:
        return Value.NINE;
      case 9:
        return Value.TEN;
      case 10:
        return Value.JACK;
      case 11:
        return Value.QUEEN;
      case 12:
        return Value.KING;
      default:
        throw new IllegalArgumentException();
    }
  }

  /**
   * Determines if this card is an Ace. An Ace has a value of 1.
   *
   * @return true if this card is an Ace.
   */
  public boolean isAce() {
    return (v.getVal() == 1);
  }
}
