package cs3500.hw02;

/**
 * Defines the different Values that a Card can have. They are:
 *  <ul>
 *    <li> Ace = 1 </li>
 *    <li> Two = 2 </li>
 *    <li> Three = 3 </li>
 *    <li> Four = 4 </li>
 *    <li> Five = 5 </li>
 *    <li> Six = 6 </li>
 *    <li> Seven = 7 </li>
 *    <li> Eight = 8 </li>
 *    <li> Nine = 9 </li>
 *    <li> Ten = 10 </li>
 *    <li> Jack = 11 </li>
 *    <li> Queen = 12 </li>
 *    <li> King = 13 </li>
 *  </ul>
 */
public enum Value {
  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

  private int val;

  Value(int v) {
    this.val = v;
  }

  /**
   * Returns the value of this card, numbered 1 - 13.
   *
   * @return the value of this card.
   */
  public int getVal() {
    return this.val;
  }

  /**
   * Determines if this value is bigger than that value. Positive if this value
   * is bigger than that, negative if that is bigger than this, and zero if
   * they are the same.
   *
   * @param that the value that is being compared to this value;
   * @return an int comparing the two values.
   */
  public int biggerThan(Value that) {
    return val - that.val;
  }

  /**
   * Returns a String of this value, of A, 2-10, J, Q, or K.
   *
   * @return the value written as a String.
   */
  @Override
  public String toString() {
    switch (this) {
      case ACE:
        return "A";
      case TWO:
        return "2";
      case THREE:
        return "3";
      case FOUR:
        return "4";
      case FIVE:
        return "5";
      case SIX:
        return "6";
      case SEVEN:
        return "7";
      case EIGHT:
        return "8";
      case NINE:
        return "9";
      case TEN:
        return "10";
      case JACK:
        return "J";
      case QUEEN:
        return "Q";
      case KING:
        return "K";
      default:
        throw new IllegalArgumentException("Invalid value");
    }
  }
}
