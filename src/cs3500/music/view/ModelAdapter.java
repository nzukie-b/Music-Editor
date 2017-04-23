package cs3500.music.view;

import cs3500.music.model.NoteName;
import cs3500.music.provider.model.MusicEditorOperations;
import cs3500.music.provider.model.Note;
import cs3500.music.provider.model.Tone;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Adapts our ModelData to work with the provider's ViewModel.
 */
public class ModelAdapter implements MusicEditorOperations {
  ModelData data;

  /**
   * Constructor for a ModelAdapter.
   *
   * @param data the ModelData that is being adapted into a ViewModel.
   */
  public ModelAdapter(ModelData data) {
    this.data = data;
  }

  @Override
  public void addNote(Object note) {
    throw new IllegalArgumentException("Can't modify model.");
  }

  @Override
  public void removeNote(Object note) {
    throw new IllegalArgumentException("Can't modify model.");
  }

  @Override
  public void combineConsecutively(List other) {
    throw new IllegalArgumentException("Can't modify model.");
  }

  @Override
  public void combineSimultaneously(List other) {
    throw new IllegalArgumentException("Can't modify model.");
  }

  @Override
  public Map<Long, List> getNotesOnMeasure() {
    Map<Long, List> map = new TreeMap<>();
    NoteName[] a = data.getSheetData().keySet()
        .toArray(new NoteName[data.getSheetData().keySet().size()]);

    for (long i = 0; i <= data.getMaxBeat(); i++) {
      map.put(i, new ArrayList());
    }

    for (NoteName nn: a) {
      Set<cs3500.music.model.Note> notes = data.getSheetData().get(nn);
      for (cs3500.music.model.Note n : notes) {
        for (int j = 0; j <= data.getMaxBeat(); j++) {
          if (n.isPlaying(j)) {
            map.get((long) j).add(new Note(intToTone(nn.getPitch()), nn.getOct()));
          }
        }
      }
    }
    return map;
  }

  @Override
  public Object getLowest() {
    NoteName[] a = data.getSheetData().keySet()
        .toArray(new NoteName[data.getSheetData().keySet().size()]);
    NoteName nn = a[0];
    return new Note(intToTone(nn.getPitch()), nn.getOct());
  }

  @Override
  public Object getHighest() {
    NoteName[] a = data.getSheetData().keySet()
        .toArray(new NoteName[data.getSheetData().keySet().size()]);
    NoteName nn = a[a.length - 1];
    return new Note(intToTone(nn.getPitch()), nn.getOct());
  }

  @Override
  public List getNotes() {
    List l = new ArrayList();
    NoteName[] a = data.getSheetData().keySet()
        .toArray(new NoteName[data.getSheetData().keySet().size()]);
    for (NoteName nn : a) {
      Set<cs3500.music.model.Note> notes = data.getSheetData().get(nn);
      for (cs3500.music.model.Note n : notes) {
        l.add(new Note(intToTone(nn.getPitch()), nn.getOct(),
                n.getStart(), n.getDuration(), n.getInstrument(), n.getDynamics() + 20));
      }
    }
    return l;
  }

  @Override
  public int getTempo() {
    return data.getTempo();
  }

  @Override
  public int getLength() {
    return data.getMaxBeat() - 1;
  }

  /**
   * Method to convert and int, which comes from a Pitch, into a Tone.
   *
   * @param i The Pitch's value.
   * @return The corresponding Tone to the value.
   */
  private Tone intToTone(int i) {
    switch (i) {
      case 0:
        return Tone.C;
      case 1:
        return Tone.C_SHARP;
      case 2:
        return Tone.D;
      case 3:
        return Tone.D_SHARP;
      case 4:
        return Tone.E;
      case 5:
        return Tone.F;
      case 6:
        return Tone.F_SHARP;
      case 7:
        return Tone.G;
      case 8:
        return Tone.G_SHARP;
      case 9:
        return Tone.A;
      case 10:
        return Tone.A_SHARP;
      case 11:
        return Tone.B;
      default:
        throw new IllegalArgumentException();
    }
  }
}
