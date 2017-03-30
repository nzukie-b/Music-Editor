package cs3500.music.model;

/**
 * Enum to hold the pitches.
 */
public enum Pitch {
    C, CS, D, DS, E, F, FS, G, GS, A, AS, B;

  /**
   * Gets the value of the pitch.
   *
   * @return the value of the pitch.
   */
  public int getVal() {
    return ordinal();
  }

  /**
   * Finds the next pitch.
   *
   * @return the next pitch
   */
  public Pitch nextPitch() {
    return values()[(ordinal() + 1) % values().length];
  }

  /**
   * Finds the previous pitch.
   *
   * @return the previous pitch
   */
  public Pitch prevPitch() {
    return values()[(ordinal() + (values().length - 1)) % values().length];
  }

  /**
   * Prints the pitch.
   *
   * @return the pitch as a string.
   */
  public String printPitch() {
    switch (this) {
      case A:
        return "A";
      case AS:
        return "A#";
      case B:
        return "B";
      case C:
        return "C";
      case CS:
        return "C#";
      case D:
        return "D";
      case DS:
        return "D#";
      case E:
        return "E";
      case F:
        return "F";
      case FS:
        return "F#";
      case G:
        return "G";
      case GS:
        return "G#";
      default:
        throw new IllegalArgumentException("Bad pitch.");
    }
  }
}
