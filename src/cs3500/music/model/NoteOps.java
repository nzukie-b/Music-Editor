package cs3500.music.model;

/**
 * Interface for a note, has all of the note operations.
 */
public interface NoteOps {
  /**
   * Gets the starting int of the Note.
   *
   * @return the starting int.
   */
  int getStart();

  /**
   * Gets the duration of the note.
   *
   * @return the note's duration
   */
  int getDuration();

  /**
   * Gets the dynamics of the note.
   *
   * @return how loud the note is.
   */
  int getDynamics();

  /**
   * Gets the instrument of the note as an int, as configured by the midi settings.
   *
   * @return the instrument playing during this note.
   */
  int getInstrument();

  /**
   * Draws the note on the console. If the note is starting during the beat, a X
   * is drawn. If the note is being sustained during the beat, a | is drawn.
   *
   * @param beat the beat that is currently being drawn
   * @return a String with the note's representation
   */
  String drawNote(int beat);

  /**
   * Changes the note to the newStart and newDur.
   *
   * @param newStart the new starting beat for the note
   * @param newDur the new duration of the note
   */
  void change(int newStart, int newDur);

  /**
   * Determines if the note is playing at the specific beat.
   *
   * @param beat the beat the song is at
   * @return true if the note is currently being played, false if not
   */
  boolean isPlaying(int beat);
}
