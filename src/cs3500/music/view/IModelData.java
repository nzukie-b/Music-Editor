package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import java.util.Map;
import java.util.Set;

/**
 * Holds the Model's data, so that the model is not accidentally modified.
 */
public interface IModelData {

  /**
   * Gets the music sheet in String format.
   *
   * @return the String format of a music view
   */
  String getSheetAsString();

  /**
   * Get the music sheet's data as a map.
   *
   * @return the data as a map.
   */
  Map<NoteName, Set<Note>> getSheetData();

  /**
   * Get the measure length of the model.
   *
   * @return How many beats per measure
   */
  int getMeasureLength();

  /**
   * Gets the max number of beats in the model.
   *
   * @return the max beat
   */
  int getMaxBeat();

  /**
   * Gets the tempo in bpm.
   *
   * @return the music's tempo
   */
  int getTempo();

  /**
   * Gets the names of the notes being played at the current beat.
   *
   * @param beat the beat the sheet is on.
   * @return A set of the all the notes being played at the current beat.
   */
  Set<NoteName> getNotesAtBeat(int beat);
}
