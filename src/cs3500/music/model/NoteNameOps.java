package cs3500.music.model;

/**
 * Interface for a NoteName.
 */
public interface NoteNameOps {

  /**
   * The pitch of the note, A A# B C C# D D# E F F# G G#.
   *
   * @return the pitch
   */
  int getPitch();

  /**
   * The octave of the note. Must be greater than 0.
   *
   * @return the octave
   */
  int getOct();

  /**
   * Gets the value of this as an integer. The value is defined to be ((octave + 1) * 12) + pitch.
   *
   * @return the value of this note as an integer.
   */
  int getValue();

  /**
   * Prints the NoteName. Each name is 5 characters long, with spaces padding
   * each side, with more spaces in the beginning if necessary. For example,
   * A2 would be printed as __A2_ and G#10 would be printed as _G#10.
   *
   * @return a string with the names printed.
   */
  String printName();

  /**
   * The next note. A -> A#, F -> F#, G# -> A, etc.
   *
   * @return  The next note.
   */
  NoteName nextNote();

  /**
   * The previous note. A -> G#, B# -> B, G# -> G, etc.
   *
   * @return the previous note.
   */
  NoteName prevNote();
}
