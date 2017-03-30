package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Holds the data from the model so the model is not changed by the view.
 */
public class ModelData implements IModelData {
  private final String sheetAsString;
  private final Map<NoteName, Set<Note>> sheetData;
  private final int measureLength;
  private final int maxBeat;
  private final int tempo;

  /**
   * Constructor for MusicData.
   *
   * @param str The model's data as a string.
   * @param sht The model's data as a map.
   * @param measureLength The length of one measure.
   * @param maxBeat The max beat in this sheet.
   * @param tempo The tempo in bpm.
   */
  public ModelData(String str, Map<NoteName, Set<Note>> sht, int measureLength,
      int maxBeat, int tempo) {
    this.sheetAsString = str;
    this.sheetData = sht;
    this.measureLength = measureLength;
    this.maxBeat = maxBeat;
    this.tempo = tempo;
  }

  @Override
  public String getSheetAsString() {
    return sheetAsString;
  }

  @Override
  public Map<NoteName, Set<Note>> getSheetData() {
    return sheetData;
  }

  @Override
  public int getMeasureLength() {
    return measureLength;
  }

  @Override
  public int getMaxBeat() {
    return maxBeat;
  }

  @Override
  public int getTempo() {
    return tempo;
  }

  @Override
  public Set<NoteName> getNotesAtBeat(int beat) {
    NoteName[] arr = sheetData.keySet().toArray(new NoteName[sheetData.keySet().size()]);
    Set<NoteName> noteSet = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
      Set<Note> notes = sheetData.get(arr[i]);
      for (Note n : notes) {
        if (n.isPlaying(beat)) {
          noteSet.add(arr[i]);
        }
      }
    }
    return noteSet;
  }
}
