package cs3500.hw03;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Controller for the game of Freecell. Allows the user to play the game.
 */
public class FreecellController implements IFreecellController<Card> {

  private Readable rd;
  private Appendable ap;

  /**
   * Constructor for the Controller. Checks to make sure the readable and appendable
   * are not null, initializes them if they are not.
   *
   * @param rd reads the user input
   * @param ap appends the output of the game so the user can see it
   */
  public FreecellController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalStateException("Input cannot be null.");
    }
    this.rd = rd;
    this.ap = ap;
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
      int numOpens, boolean shuffle) {

    if (deck == null || model == null) {
      throw new IllegalArgumentException();
    }

    if (validStart(deck, model, numCascades, numOpens, shuffle)) {
      try {
        ap.append(model.getGameState());
        ap.append("\nEnter your move. Separate all control with spaces.\n");
      }
      catch (IOException e) {
        //can't do that
      }

      Scanner scan = new Scanner(this.rd);
      while (!model.isGameOver() && scan.hasNext()) {

        try {

          String p1 = scan.next();
          hasQ(p1);
          PileType pile1 = getPile(p1, scan);
          int i1 = getInt(p1.substring(1), scan);

          String int2 = scan.next();
          hasQ(int2);
          int i2 = getInt(int2, scan);

          String p2 = scan.next();
          hasQ(p2);
          PileType pile2 = getPile(p2, scan);
          int i3 = getInt(p2.substring(1), scan);

          try {
            model.move(pile1, i1, i2, pile2, i3);
          }
          catch (IllegalArgumentException e) {
            try {
              ap.append("\nInvalid move. Try again. " + e.getMessage() + "\n");
            }
            catch (IOException e1) {
              //can't do that
            }
          }
          try {
            ap.append(model.getGameState());
          }
          catch (IOException e) {
            //can't do that
          }
        }
        catch (IllegalArgumentException e) {
          try {
            ap.append("\nGame quit prematurely.");
          }
          catch (IOException e1) {
            //can't do that
          }
          break;
        }
        catch (NoSuchElementException e1) {
          //waiting for more user input
        }
      }
      if (model.isGameOver()) {
        try {
          ap.append("\nGame over.");
        }
        catch (IOException e) {
          //can't do that
        }
      }
    }
    else {
      try {
        ap.append("Could not start game.");
      }
      catch (IOException e) {
        //can't do that
      }
    }
  }

  /**
   * Determines if the string has a q anywhere in it.
   *
   * @param s String that might have a q in it
   */
  private void hasQ(String s) {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.toLowerCase(c) == 'q') {
        throw new IllegalArgumentException("End");
      }
    }
  }

  /**
   * Gets the int from the scanner.
   *
   * @param s char from the scanner
   * @param scan scanner
   * @return the int value for the char if there is one
   */
  private int getInt(String s, Scanner scan) {
    int i = 0;
    try {
      i = Integer.parseInt(s);
    }
    catch (NumberFormatException e) {
      try {
        ap.append("Enter an int\n");
      }
      catch (IOException e1) {
        //can't do that
      }
      while (!scan.hasNext()) {
        //waiting for user input
      }
      s = scan.next();
      hasQ(s);
      return getInt(s, scan);
    }
    return i - 1;
  }

  /**
   * Gets the pile type from the scanner.
   *
   * @param s the string being passed in
   * @param scan the scanner
   * @return the pile type based on the user input
   */
  private PileType getPile(String s, Scanner scan) {
    switch (s.charAt(0)) {
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      case 'O':
        return PileType.OPEN;
      case 'Q':
      case 'q':
        throw new IllegalArgumentException("End");
      default:
        try {
          ap.append("Enter the pile char C, F, or O\n");
        }
        catch (IOException e) {
          //can't do that
        }
    }
    while (!scan.hasNext()) {
      //waiting for user input
    }
    s = scan.next();
    hasQ(s);
    return getPile(s, scan);
  }

  /**
   * Determines if game has a valid start. Lets the user know if it cannot start.
   *
   * @param deck the deck being played with
   * @param model the model
   * @param numCascades the number of cascade piles being played with
   * @param numOpens the number of open piles being played with
   * @param shuffle shuffles the deck if true
   * @return true if the game was able to start correctly
   */
  private boolean validStart(List<Card> deck, FreecellOperations<Card> model,
      int numCascades, int numOpens, boolean shuffle) {
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
      return true;
    }
    catch (IllegalArgumentException e) {
      return false;
    }
  }
}
