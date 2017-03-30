package cs3500.hw02;

/**
 * Defines the Suit that a Card can have. They are:
 *  <ul>
 *    <li> Spades - Black </li>
 *    <li> Clubs - Black </li>
 *    <li> Diamonds - Red </li>
 *    <li> Hearts - Red </li>
 *  </ul>
 */
public enum Suit {
  //defines each Suit
  SPADES, CLUBS, DIAMONDS, HEARTS;

  /**
   * Writes the Suit as a String.
   *
   * @return the Suit as a String.
   */
  @Override
  public String toString() {
    switch (this) {
      case SPADES:
        return "♠";
      case CLUBS:
        return "♣";
      case DIAMONDS:
        return "♦";
      case HEARTS:
        return "♥";
      default:
        throw new IllegalArgumentException("Invalid Suit");
    }
  }

  /**
   * Determines if two suits are opposites. SPADES and CLUBS are opposite
   * HEARTS and DIAMONDS.
   *
   * @param that the suit this suit is being compared to.
   * @return true if the suits are opposite.
   */
  public boolean opp(Suit that) {
    if ((this == SPADES) || (this == CLUBS)) {
      return ((that == DIAMONDS) || (that == HEARTS));
    }
    else if ((this == DIAMONDS) || (this == HEARTS)) {
      return ((that == SPADES) || (that == CLUBS));
    }
    else {
      throw new IllegalArgumentException("Suits not opposite. ");
    }
  }
}
