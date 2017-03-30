import cs3500.hw02.FreecellOperations;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.FreecellModelCreator.GameType;
import cs3500.hw04.IPile;
import cs3500.hw02.PileType;
import cs3500.hw02.Card;
import cs3500.hw02.Suit;
import cs3500.hw02.Value;
import cs3500.hw03.FreecellController;
import cs3500.hw04.Pile;
import java.util.ArrayList;
import org.junit.Test;
import java.util.List;
import java.io.Reader;
import java.io.StringReader;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class to test the public-facing methods in the FreecellModel class.
 */
public class FreecellModelTest {

  private FreecellModelCreator fcmc = new FreecellModelCreator();
  private FreecellOperations fcm1 = fcmc.create(GameType.SINGLEMOVE);
  private FreecellOperations fcmm = fcmc.create(GameType.MULTIMOVE);
  private List<Card> deck1 = fcm1.getDeck();
  private IPile op1 = new Pile();

  private Card c1 = new Card(Suit.SPADES, Value.ACE);
  private Card c2 = new Card(Suit.SPADES, Value.FIVE);
  private Card c3 = new Card(Suit.HEARTS, Value.FIVE);

  private Suit s1 = Suit.CLUBS;
  private Suit s2 = Suit.SPADES;
  private Suit s3 = Suit.HEARTS;
  private Suit s4 = Suit.DIAMONDS;

  private Value v1 = Value.ACE;
  private Value v2 = Value.FOUR;
  private Value v3 = Value.JACK;
  private Value v4 = Value.KING;
  private Value v5 = Value.SEVEN;

  private StringBuffer out = new StringBuffer();
  private Reader in = new StringReader("");

  @Test
  public void fc1() {
    assertEquals("", fcm1.getGameState());
  }

  @Test
  public void fc2() {
    assertEquals(false, fcm1.isGameOver());
  }

  @Test
  public void fc3() {
    fcm1.startGame(deck1, 8, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥",
        fcm1.getGameState());
  }

  @Test
  public void fc4() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcm1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc5() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 6, PileType.CASCADE,
        0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc6() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 8, 6, PileType.CASCADE,
        0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc7() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 6, PileType.CASCADE,
        0);
  }

  @Test
  public void fc8() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥",
        fcm1.getGameState());

  }

  @Test
  public void fc9() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        1);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: K♥\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcm1.getGameState());
  }

  @Test
  public void fc10() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 2, 6, PileType.OPEN,
        1);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: K♥\n"
            + "O2: Q♦\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcm1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc11() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc12() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 2, 5, PileType.CASCADE,
        3);
  }

  @Test
  public void fc13() {
    assertEquals(deck1.size(), 52);
  }

  @Test
  public void fc14() {
    fcm1.startGame(deck1, 6, 3, true);
    assertEquals(deck1.size(), 52);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc15() {
    fcm1.startGame(deck1, 2, 2, false);
  }

  @Test
  public void fc16() {
    fcm1.startGame(deck1, 10, 2, false);
    assertEquals(fcm1.getGameState().contains("C10:"),
        true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc17() {
    fcm1.startGame(deck1, -1, 3, true);
  }

  @Test
  public void fc18() {
    fcm1.startGame(deck1, 8, 5, true);
    assertEquals(fcm1.getGameState().contains("O5:"), true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc19() {
    fcm1.startGame(deck1, 7, -3, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc20() {
    fcm1.startGame(deck1, 6, 3, true);
    fcm1.move(PileType.CASCADE, 3, 2, PileType.OPEN,
        0);
  }

  @Test
  public void fc21() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        1);
    fcm1.move(PileType.OPEN, 1, 0, PileType.OPEN,
        3);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4: K♥\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcm1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc22() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.FOUNDATION, 0, 0, PileType.OPEN,
        2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc23() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 2, 6, PileType.OPEN,
        0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc24() {
    List<Card> deck2 = new ArrayList<>();
    deck2.add(c1);
    deck2.add(c2);
    deck2.add(c3);
    fcm1.startGame(deck2, 6, 3, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc25() {
    List<Card> deck2 = new ArrayList<>();
    deck2.add(c1);
    deck2.add(c2);
    deck2.add(c3);
    deck2.add(c3);
    deck2.add(c3);
    fcm1.startGame(deck2, 6, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc26() {
    deck1.remove(50);
    deck1.add(c2);
    fcm1.startGame(deck1, 4, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc27() {
    fcm1.startGame(deck1, 3, 1, false);
  }

  @Test
  public void fc28() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "O2: 3♥\n"
            + "O3: 8♥\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8:",
        fcm1.getGameState());
  }

  @Test
  public void fc29() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    assertEquals(false,
        fcm1.isGameOver());
  }

  @Test
  public void fc30() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.CASCADE,
        7);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "O2: 3♥\n"
            + "O3: 8♥\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: K♥",
        fcm1.getGameState());
  }

  @Test
  public void fc31() {
    fcm1.startGame(deck1, 8, 4, true);
    assertNotEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥",
        fcm1.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void fc32() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION,
        0);
  }

  @Test
  public void fc33() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 5, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 3, 4, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        0);
    assertEquals("F1: A♥, 2♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "O2: 3♥\n"
            + "O3: 8♥\n"
            + "O4: 10♥\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠, 5♥\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: K♥",
        fcm1.getGameState());
  }

  @Test
  public void fc34() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 5, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 3, 4, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 1, 6, PileType.CASCADE,
        2);
    fcm1.move(PileType.OPEN, 3, 0, PileType.CASCADE,
        2);
    fcm1.move(PileType.CASCADE, 5, 6, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 5, 5, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 5, 4, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 5, 3, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 5, 2, PileType.CASCADE,
        2);
    fcm1.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.OPEN, 0, 0, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 1, 5, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 1, 4, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 5, 0, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 1, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 1, 0, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 1, 2, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 1, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 4, 7, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 6, 8, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.OPEN, 3, 0, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 2, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 1, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 4, 6, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 4, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 4, 4, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 1, 0, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 6, 7, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 6, 6, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 4, 3, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 4, 2, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 4, 1, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 2, 9, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 2, 8, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 3, 0, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 2, 0, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 2, 7, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 2, 6, PileType.CASCADE,
        3);
    fcm1.move(PileType.OPEN, 2, 0, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 0, 10, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 0, 9, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 0, 8, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 2, 5, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 2, 4, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 6, 5, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 6, 4, PileType.CASCADE,
        1);
    fcm1.move(PileType.OPEN, 0, 0, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 6, 3, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 6, 2, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 6, 1, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 1, 0, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 6, 0, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 2, 2, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 2, 1, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 4, 2, PileType.CASCADE,
        2);
    fcm1.move(PileType.CASCADE, 4, 1, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 0, 9, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 5, 4, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 0, 8, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 0, 6, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 0, 5, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 0, 4, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 0, 3, PileType.CASCADE,
        1);
    fcm1.move(PileType.CASCADE, 0, 2, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 0, 1, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 2, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 5, 4, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 5, 3, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 5, 2, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION,
        3);
    assertEquals("F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
            + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
            + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1:\n"
            + "C2:\n"
            + "C3:\n"
            + "C4:\n"
            + "C5:\n"
            + "C6:\n"
            + "C7:\n"
            + "C8:",
        fcm1.getGameState());
    assertEquals(fcm1.isGameOver(), true);
  }

  @Test
  public void fc35() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥",
        fcm1.getGameState());
    fcm1.startGame(deck1, 8, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥",
        fcm1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fc36() {
    fcm1.startGame(deck1, 3, 3, true);
    assertEquals(fcm1.getGameState(), "");
  }

  @Test
  public void pile1() {
    assertEquals(true, op1.isEmpty());
  }

  @Test
  public void card1() {
    assertEquals(c1.isAce(), true);
  }

  @Test
  public void card2() {
    assertEquals(c1.compValue(c2), -4);
  }

  @Test
  public void card3() {
    assertEquals(c2.compValue(c3), 0);
  }

  @Test
  public void card4() {
    assertEquals(c3.compValue(c1), 4);
  }

  @Test
  public void card5() {
    assertEquals(c1.oppSuit(c2), false);
  }

  @Test
  public void card6() {
    assertEquals(c1.oppSuit(c3), true);
  }

  @Test
  public void card7() {
    assertEquals(c1.toString(), "A♠");
  }

  @Test
  public void card8() {
    assertEquals(c3.toString(), "5♥");
  }

  @Test
  public void card9() {
    assertEquals(c1.sameSuit(c2), true);
  }

  @Test
  public void card10() {
    assertEquals(c1.sameSuit(c3), false);
  }

  @Test
  public void suit1() {
    assertEquals(s1.toString(), "♣");
  }

  @Test
  public void suit2() {
    assertEquals(s2.toString(), "♠");
  }

  @Test
  public void suit3() {
    assertEquals(s3.toString(), "♥");
  }

  @Test
  public void suit4() {
    assertEquals(s4.toString(), "♦");
  }

  @Test
  public void suit5() {
    assertEquals(s1.opp(s2), false);
  }

  @Test
  public void suit6() {
    assertEquals(s1.opp(s4), true);
  }

  @Test
  public void suit7() {
    assertEquals(s3.opp(s4), false);
  }

  @Test
  public void value1() {
    assertEquals(v1.toString(), "A");
  }

  @Test
  public void value2() {
    assertEquals(v2.toString(), "4");
  }

  @Test
  public void value3() {
    assertEquals(v3.toString(), "J");
  }

  @Test
  public void value4() {
    assertEquals(v4.toString(), "K");
  }

  @Test
  public void value5() {
    assertEquals(v5.toString(), "7");
  }

  @Test
  public void value6() {
    assertEquals(v1.getVal(), 1);
  }

  @Test
  public void value7() {
    assertEquals(v2.getVal(), 4);
  }

  @Test
  public void value8() {
    assertEquals(v3.getVal(), 11);
  }

  @Test
  public void value9() {
    assertEquals(v1.biggerThan(v2), -3);
  }

  @Test
  public void value10() {
    assertEquals(v4.biggerThan(v3), 2);
  }

  @Test
  public void value11() {
    assertEquals(v5.biggerThan(v4), -6);
  }

  @Test
  public void value12() {
    assertEquals(v3.biggerThan(v3), 0);
  }

  @Test
  public void fcc1() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, fcm1, 3, 2, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void fcc2() {
    Reader in1 = new StringReader("q");
    FreecellController cntrl1 = new FreecellController(in1, out);
    cntrl1.playGame(deck1, fcm1, 5, 4, true);
    assertEquals(out.toString().contains("Game quit prematurely."), true);
  }

  @Test(expected = IllegalStateException.class)
  public void fcc3() {
    FreecellController cntrl = new FreecellController(null, out);
    cntrl.playGame(deck1, fcm1, 8, 4, true);
    assertEquals(out.toString(), "");
  }

  @Test(expected = IllegalStateException.class)
  public void fcc4() {
    FreecellController cntrl = new FreecellController(in, null);
    cntrl.playGame(deck1, fcm1, 8, 4, true);
    assertEquals(out.toString(), "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcc5() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(null, fcm1, 5, 4, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcc6() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, null, 5, 4, true);
  }

  @Test
  public void fcc7() {
    Reader in1 = new StringReader("C3 q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 5, 3, false);
    assertEquals(out.toString().contains("Game quit prematurely."), true);
  }

  @Test
  public void fcc8() {
    FreecellController cntrl = new FreecellController(in, out);
    cntrl.playGame(deck1, fcm1, 5, 4, false);
    assertEquals(out.toString()
        .contains("Enter your move. Separate all control with spaces."), true);
  }

  @Test
  public void fcc9() {
    Reader in2 = new StringReader("C8 6 C1");
    FreecellController cntrl = new FreecellController(in2, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥"),
        true);
  }

  @Test
  public void fcc10() {
    FreecellController cntrl = new FreecellController(in, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥"),
        true);
  }

  @Test
  public void fcc11() {
    Reader in1 = new StringReader("C8 7 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. Wrong index."),
        true);
  }

  @Test
  public void fcc12() {
    Reader in1 = new StringReader("C9 6 C19");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. Wrong pile number."),
        true);
  }

  @Test
  public void fcc13() {
    Reader in1 = new StringReader("C8 4 C5");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. "
            + "Can't move that many cards."),
        true);
  }

  @Test
  public void fcc14() {
    Reader in3 = new StringReader("C8 6 C1 C8 5 F1");
    FreecellController cntrl = new FreecellController(in3, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥"),
        true);
  }

  @Test
  public void fcc15() {
    Reader in1 = new StringReader("C8 6 C1 C4 7 O2");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: K♥\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc16() {
    Reader in1 = new StringReader("C8 6 C1 C4 7 O2 C3 7 O3");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: K♥\n"
            + "O3: Q♦\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc17() {
    Reader in1 = new StringReader("C8 7 C1 C8 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. Wrong index."),
        true);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc18() {
    Reader in1 = new StringReader("C9 6 C1 C8 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. Wrong pile number."),
        true);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc19() {
    Reader in1 = new StringReader("C8 4 C1 C8 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. "
            + "Can't move that many cards."),
        true);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc20() {
    Reader in1 = new StringReader("C3 7 C4");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. Can't move that here."),
        true);
  }

  @Test
  public void fcc21() {
    Reader in1 = new StringReader("08 C 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter the pile char C, F, or O"),
        true);
  }

  @Test
  public void fcc22() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, fcm1, 9, 2, false);
    assertEquals(out.toString().contains("Enter your move. "
            + "Separate all control with spaces."),
        true);
  }

  @Test
  public void fcc23() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, fcm1, -9, 2, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void fcc24() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, fcm1, 6, -4, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void fcc25() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, fcm1, 6, 0, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void fcc26() {
    FreecellController cntrl1 = new FreecellController(in, out);
    cntrl1.playGame(deck1, fcm1, 6, -6, false);
    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void fcc27() {
    Reader in1 = new StringReader("C8 6 C1 C4 7 O2 O2 1 O4");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4: K♥\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc28() {
    Reader in1 = new StringReader("C8 6 C1 C8 5 F1 F1 1 O3");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Can't move from foundation."),
        true);
  }

  @Test
  public void fcc29() {
    Reader in1 = new StringReader("C8 6 C1 C4 7 O1 C3 7 O1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Can't move that here."),
        true);
  }

  @Test
  public void fcc30() {
    Reader in1 = new StringReader("c8 C 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter the pile char C, F, or O"),
        true);
  }

  @Test
  public void fcc31() {
    Reader in1 = new StringReader("Cu 8 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter an int"),
        true);
  }

  @Test
  public void fcc32() {
    Reader in1 = new StringReader("C8 u 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter an int"),
        true);
  }

  @Test
  public void fcc33() {
    Reader in1 = new StringReader("C8 6 o1 O");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter the pile char C, F, or O"),
        true);
  }

  @Test
  public void fcc34() {
    Reader in1 = new StringReader("C8 6 Oi e 1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter an int"),
        true);
  }

  @Test
  public void fcc35() {
    Reader in1 = new StringReader("Cq");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc36() {
    Reader in1 = new StringReader("C8 q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc37() {
    Reader in1 = new StringReader("C8 6 C1 Q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc38() {
    FreecellController cntrl = new FreecellController(in, out);
    List<Card> deck2 = new ArrayList<>();
    deck2.add(c1);
    deck2.add(c2);
    deck2.add(c3);
    cntrl.playGame(deck2, fcm1, 8, 5, true);
    assertEquals(out.toString().contains("Could not start game"),
        true);
  }

  @Test
  public void fcc39() {
    deck1.remove(50);
    deck1.add(c2);
    FreecellController cntrl = new FreecellController(in, out);
    cntrl.playGame(deck1, fcm1, 6, 2, true);
    assertEquals(out.toString().contains("Could not start game"),
        true);
  }

  @Test
  public void fcc40() {
    Reader in1 = new StringReader("C8 6 C1 C8 5 F1 C3 7 F1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Can't move that here"),
        true);
  }

  @Test
  public void fcc41() {
    Reader in1 = new StringReader("C9 6 C3");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥"),
        true);
  }

  @Test
  public void fcc42() {
    Reader in1 = new StringReader("C8 6 C1 q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc43() {
    Reader in1 = new StringReader("C4Q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc44() {
    Reader in1 = new StringReader("C4 5q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc45() {
    Reader in1 = new StringReader("C4 5 C5q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc46() {
    Reader in1 = new StringReader("CQ");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc47() {
    Reader in1 = new StringReader("C8 6 C1");
    Reader in2 = new StringReader("C7 6 O3");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
    cntrl = new FreecellController(in2, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3: 8♦\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥"),
        true);
  }

  @Test
  public void fcc48() {
    Reader in1 = new StringReader("C8 3 q4");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc49() {
    Reader in1 = new StringReader("C8 6 C1 C8 5 O1 C8 4 C6 C8 3 O2 C8 2 O3q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc50() {
    Reader in1 = new StringReader("C8 6 C1 C8 5 O1 C8 4 C6 C8 3 O2 C8 2 O q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc51() {
    Reader in1 = new StringReader("C9q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc52() {
    Reader in1 = new StringReader("C3");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥"),
        true);
  }

  @Test
  public void fcc53() {
    Reader in1 = new StringReader("Ckrr esgag lkdsj 8 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Enter an int\nEnter an int\nEnter an int"),
        true);
  }

  @Test
  public void fcc54() {
    Reader in1 = new StringReader("C8 6 C1");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    deck1.remove(50);
    assertEquals(out.toString().contains("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥"),
        true);
  }

  @Test
  public void fcc55() {
    Reader in1 = new StringReader("C-1 5 C3");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Invalid move. Try again. Can't be negative"),
        true);
  }

  @Test
  public void fcc56() {
    Reader in1 = new StringReader("C-1q");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 8, 4, false);
    assertEquals(out.toString().contains("Game quit prematurely."),
        true);
  }

  @Test
  public void fcc57() {
    Reader in1 = new StringReader("C1 1 F1 "
        + "C41 1 F1 "
        + "C29 1 F1 "
        + "C17 1 F1 "
        + "C5 1 F1 "
        + "C45 1 F1 "
        + "C33 1 F1 "
        + "C21 1 F1 "
        + "C9 1 F1 "
        + "C49 1 F1 "
        + "C37 1 F1 "
        + "C25 1 F1 "
        + "C13 1 F1 "
        + "C14 1 F2 "
        + "C2 1 F2 "
        + "C42 1 F2 "
        + "C30 1 F2 "
        + "C18 1 F2 "
        + "C6 1 F2 "
        + "C46 1 F2 "
        + "C34 1 F2 "
        + "C22 1 F2 "
        + "C10 1 F2 "
        + "C50 1 F2 "
        + "C38 1 F2 "
        + "C26 1 F2 "
        + "C27 1 F3 "
        + "C15 1 F3 "
        + "C3 1 F3 "
        + "C43 1 F3 "
        + "C31 1 F3 "
        + "C19 1 F3 "
        + "C7 1 F3 "
        + "C47 1 F3 "
        + "C35 1 F3 "
        + "C23 1 F3 "
        + "C11 1 F3 "
        + "C51 1 F3 "
        + "C39 1 F3 "
        + "C40 1 F4 "
        + "C28 1 F4 "
        + "C16 1 F4 "
        + "C4 1 F4 "
        + "C44 1 F4 "
        + "C32 1 F4 "
        + "C20 1 F4 "
        + "C8 1 F4 "
        + "C48 1 F4 "
        + "C36 1 F4 "
        + "C24 1 F4 "
        + "C12 1 F4 "
        + "C52 1 F4 ");
    FreecellController cntrl = new FreecellController(in1, out);
    cntrl.playGame(deck1, fcm1, 52, 4, false);
    assertEquals(out.toString().contains("F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
            + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
            + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F4: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1:\n"
            + "C2:\n"
            + "C3:\n"
            + "C4:\n"
            + "C5:\n"
            + "C6:\n"
            + "C7:\n"
            + "C8:"),
        true);
    assertEquals(out.toString().contains("Game over."), true);
  }

  @Test
  public void fcmm1() {
    assertEquals("", fcmm.getGameState());
  }

  @Test
  public void fcmm2() {
    assertEquals(false, fcmm.isGameOver());
  }

  @Test
  public void fcmm3() {
    fcmm.startGame(deck1, 8, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥",
        fcmm.getGameState());
  }

  @Test
  public void fcmm4() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcmm.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm5() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 6, PileType.CASCADE,
        0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm6() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 8, 6, PileType.CASCADE,
        0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm7() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 6, PileType.CASCADE,
        0);
  }

  @Test
  public void fcmm8() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥",
        fcmm.getGameState());

  }

  @Test
  public void fcmm9() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        1);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: K♥\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcmm.getGameState());
  }

  @Test
  public void fcmm10() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 2, 6, PileType.OPEN,
        1);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: K♥\n"
            + "O2: Q♦\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcmm.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm11() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm12() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 2, 5, PileType.CASCADE,
        3);
  }

  @Test
  public void fcmm13() {
    assertEquals(deck1.size(), 52);
  }

  @Test
  public void fcmm14() {
    fcmm.startGame(deck1, 6, 3, true);
    assertEquals(deck1.size(), 52);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm15() {
    fcmm.startGame(deck1, 2, 2, false);
  }

  @Test
  public void fcmm16() {
    fcmm.startGame(deck1, 10, 2, false);
    assertEquals(fcm1.getGameState(),
        "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm17() {
    fcmm.startGame(deck1, -1, 3, true);
  }

  @Test
  public void fcmm18() {
    fcmm.startGame(deck1, 8, 5, true);
    assertEquals(fcmm.getGameState().contains("O5:"), true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm19() {
    fcmm.startGame(deck1, 7, -3, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm20() {
    fcmm.startGame(deck1, 6, 3, true);
    fcmm.move(PileType.CASCADE, 3, 2, PileType.OPEN,
        0);
  }

  @Test
  public void fcmm21() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        1);
    fcmm.move(PileType.OPEN, 1, 0, PileType.OPEN,
        3);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4: K♥\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥",
        fcmm.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm22() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.FOUNDATION, 0, 0, PileType.OPEN,
        2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm23() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 2, 6, PileType.OPEN,
        0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm24() {
    List<Card> deck2 = new ArrayList<>();
    deck2.add(c1);
    deck2.add(c2);
    deck2.add(c3);
    fcmm.startGame(deck2, 6, 3, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm25() {
    List<Card> deck2 = new ArrayList<>();
    deck2.add(c1);
    deck2.add(c2);
    deck2.add(c3);
    deck2.add(c3);
    deck2.add(c3);
    fcmm.startGame(deck2, 6, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm26() {
    deck1.remove(50);
    deck1.add(c2);
    fcmm.startGame(deck1, 4, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm27() {
    fcmm.startGame(deck1, 3, 1, false);
  }

  @Test
  public void fcmm28() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcmm.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "O2: 3♥\n"
            + "O3: 8♥\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8:",
        fcmm.getGameState());
  }

  @Test
  public void fcmm29() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcmm.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    assertEquals(false,
        fcmm.isGameOver());
  }

  @Test
  public void fcmm30() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcmm.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.CASCADE,
        7);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "O2: 3♥\n"
            + "O3: 8♥\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: K♥",
        fcmm.getGameState());
  }

  @Test
  public void fcmm31() {
    fcmm.startGame(deck1, 8, 4, true);
    assertNotEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥",
        fcmm.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm32() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION,
        0);
  }

  @Test
  public void fcmm33() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcmm.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    fcmm.move(PileType.CASCADE, 3, 6, PileType.CASCADE,
        7);
    fcmm.move(PileType.CASCADE, 3, 5, PileType.CASCADE,
        4);
    fcmm.move(PileType.CASCADE, 3, 4, PileType.OPEN,
        3);
    fcmm.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        0);
    assertEquals("F1: A♥, 2♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: J♥\n"
            + "O2: 3♥\n"
            + "O3: 8♥\n"
            + "O4: 10♥\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠, 5♥\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: K♥",
        fcmm.getGameState());
  }

  @Test
  public void fcmm34() {
    fcm1.startGame(deck1, 8, 4, false);
    fcm1.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 3, 6, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 5, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 3, 4, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 1, 6, PileType.CASCADE,
        2);
    fcm1.move(PileType.OPEN, 3, 0, PileType.CASCADE,
        2);
    fcm1.move(PileType.CASCADE, 5, 6, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 5, 5, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 5, 4, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 5, 3, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 5, 2, PileType.CASCADE,
        2);
    fcm1.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.OPEN, 0, 0, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 1, 5, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 1, 4, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 5, 0, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 1, 3, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 1, 0, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 1, 2, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 1, 1, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 4, 7, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 6, 8, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.OPEN, 3, 0, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 2, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 3, 1, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 4, 6, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 4, 5, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 4, 4, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 1, 0, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 6, 7, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 6, 6, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 4, 3, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 4, 2, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 4, 1, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 2, 9, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 2, 8, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 3, 0, PileType.CASCADE,
        5);
    fcm1.move(PileType.OPEN, 2, 0, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 2, 7, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 2, 6, PileType.CASCADE,
        3);
    fcm1.move(PileType.OPEN, 2, 0, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 0, 10, PileType.OPEN,
        2);
    fcm1.move(PileType.CASCADE, 0, 9, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 0, 8, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 2, 5, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 2, 4, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 6, 5, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 6, 4, PileType.CASCADE,
        1);
    fcm1.move(PileType.OPEN, 0, 0, PileType.CASCADE,
        4);
    fcm1.move(PileType.CASCADE, 6, 3, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 6, 2, PileType.CASCADE,
        3);
    fcm1.move(PileType.CASCADE, 6, 1, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 1, 0, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 6, 0, PileType.CASCADE,
        0);
    fcm1.move(PileType.CASCADE, 2, 2, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 2, 1, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 4, 2, PileType.CASCADE,
        2);
    fcm1.move(PileType.CASCADE, 4, 1, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 0, 9, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 5, 5, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 5, 4, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 0, 8, PileType.OPEN,
        0);
    fcm1.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 0, 6, PileType.CASCADE,
        7);
    fcm1.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 0, 5, PileType.OPEN,
        1);
    fcm1.move(PileType.CASCADE, 0, 4, PileType.CASCADE,
        6);
    fcm1.move(PileType.CASCADE, 0, 3, PileType.CASCADE,
        1);
    fcm1.move(PileType.CASCADE, 0, 2, PileType.OPEN,
        3);
    fcm1.move(PileType.CASCADE, 0, 1, PileType.CASCADE,
        5);
    fcm1.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 1, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 3, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 2, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 6, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.OPEN, 0, 0, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 5, 4, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 7, 3, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 5, 3, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 2, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 7, 1, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 5, 2, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION,
        3);
    fcm1.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 7, 0, PileType.FOUNDATION,
        0);
    fcm1.move(PileType.CASCADE, 5, 0, PileType.FOUNDATION,
        1);
    fcm1.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION,
        2);
    fcm1.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION,
        3);
    assertEquals("F1: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
            + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
            + "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1:\n"
            + "C2:\n"
            + "C3:\n"
            + "C4:\n"
            + "C5:\n"
            + "C6:\n"
            + "C7:\n"
            + "C8:",
        fcm1.getGameState());
    assertEquals(fcm1.isGameOver(), true);
  }

  @Test
  public void fcmm35() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠, 9♥\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥",
        fcmm.getGameState());
    fcmm.startGame(deck1, 8, 4, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠, 10♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 6♥, A♥, 9♥",
        fcmm.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm36() {
    fcmm.startGame(deck1, 3, 3, true);
    assertEquals(fcmm.getGameState(), "");
  }

  @Test
  public void fcmm37() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 0, 6, PileType.CASCADE,
        7);
    assertEquals("F1: A♥\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 9♠, 4♠, Q♠, 7♠, 2♠\n"
            + "C2: 2♣, 10♣, 5♣, K♣, 8♣, 3♣, J♣\n"
            + "C3: 3♦, J♦, 6♦, A♦, 9♦, 4♦, Q♦\n"
            + "C4: 4♥, Q♥, 7♥, 2♥, 10♥, 5♥, K♥\n"
            + "C5: 5♠, K♠, 8♠, 3♠, J♠, 6♠\n"
            + "C6: 6♣, A♣, 9♣, 4♣, Q♣, 7♣, 6♥\n"
            + "C7: 7♦, 2♦, 10♦, 5♦, K♦, 8♦\n"
            + "C8: 8♥, 3♥, J♥, 10♠, 9♥",
        fcmm.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm38() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 2, 6, PileType.OPEN,
        0);
    fcmm.move(PileType.CASCADE, 2, 5, PileType.OPEN,
        1);
    fcmm.move(PileType.CASCADE, 2, 4, PileType.OPEN,
        2);
    fcmm.move(PileType.CASCADE, 2, 3, PileType.OPEN,
        3);
    fcmm.move(PileType.CASCADE, 0, 6, PileType.CASCADE,
        7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fcmm39() {
    fcmm.startGame(deck1, 8, 4, false);
    fcmm.move(PileType.CASCADE, 7, 5, PileType.CASCADE,
        0);
    fcmm.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION,
        0);
    fcmm.move(PileType.CASCADE, 7, 3, PileType.CASCADE,
        5);
    fcmm.move(PileType.CASCADE, 0, 6, PileType.CASCADE,
        7);
    fcmm.move(PileType.CASCADE, 0, 4, PileType.CASCADE,
        6);
  }
}
