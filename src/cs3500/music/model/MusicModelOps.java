package cs3500.music.model;

import java.util.Map;
import java.util.Set;

/**
 * Interface for a music model.
 */
public interface MusicModelOps {

  /**
   * Adds a note to the sheet. Throws an exception if the note is already
   * in the sheet or if it overlaps with on already in the sheet.
   *
   * @param name Name of the note to be added
   * @param note note that is being added
   */
  void addNote(NoteName name, Note note)
      throws IllegalArgumentException;

  /**
   * Deletes the note in the sheet. Throws an exception if the name is not in the
   * sheet or if the note is not associated with the name.
   *
   * @param name The name of the note that is being deleted
   * @param note The note that is being deleted
   */
  void deleteNote(NoteName name, Note note)
      throws IllegalArgumentException;

  /**
   * Changes  the note. Throws an exception if the name is not in the sheet
   * or if the note is not associated with the name.
   * @param name the name of the note being changed.
   * @param curr the current note, that will be changed.
   * @param newStart the new start for the note.
   * @param newDur the new duration for the note.
   */
  void changeNote(NoteName name, Note curr, int newStart, int newDur)
      throws IllegalArgumentException;

  /**
   * Combines the two sheets so that they will play simultaneously. Throws an
   * exception if any notes overlap.
   *
   * @param m model that is being addded to this.
   */
  void combineSheets(MusicModelOps m)
      throws IllegalArgumentException;

  /**
   * A helper for the combine sheets method.
   *
   * @param m the model that this is being added to.
   */
  void combineHelp(MusicModel m);

  /**
   * Combines two sheets so that they play one after another.
   * @param m the sheet being added to this.
   */
  void consecSheets(MusicModelOps m)
      throws IllegalArgumentException;

  /**
   * A helper for the combine sheets method.
   * @param m the model this is being added to.
   */
  void consecSheetsHelp(MusicModel m);

  /**
   * Prints the music in the sheet. The names are printed on top, followed by the
   * notes. At the start of a note, an X is printed, and while it is sustaining,
   * a | is printed. If A2 and C2 are the only notes being played, it will still
   * print the notes in between, showing A2 A#2 B2 C2.
   * @return the string with the printed notes.
   */
  String printSheet();

  /**
   * Finds the maximum beat of a note in a sheet.
   *
   * @param a An array of sheetnames.
   * @return The beat of the maximum note.
   */
  int maxNote(NoteName[] a);


  /**
   * Gets all of the notes in the sheet.
   *
   * @return the notes in the sheet of music.
   */
  Map<NoteName, Set<Note>> getSheet();

  /**
   * Gets the length of a measure.
   *
   * @return the number of beats in a measure.
   */
  int measureLength();

  /**
   * Gets the tempo of the piece in Beats per Minute.
   *
   * @return the tempo of the music
   */
  int getTempo();

}