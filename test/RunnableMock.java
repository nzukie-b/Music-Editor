/**
 * Mocks a Runnable.
 */
public class RunnableMock implements Runnable {
  String s;

  /**
   * Constructor for a RunnableMock. Sets the string to be empty initially.
   */
  RunnableMock() {
    this.s = "";
  }

  @Override
  public void run() {
    s = "Run";
  }

  /**
   * Returns the String stored in this Runnable.
   * @return the string.
   */
  public String getString() {
    return s;
  }
}
