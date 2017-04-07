package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class for a Music Model. Has the capability to add a note, delete a note,
 * change a note, combine with another Model to play simultaneously or consecutively,
 * and print the notes in the music.
 */
public class MusicModel implements MusicModelOps {
  private Map<NoteName, Set<Note>> sheet;
  private int tsNum;
  private int tsDen;
  //bpm added to support tempo functionality.
  private int bpm;

  /**
   * Constructor for a Music Model.
   *
   * @param timeSigNum the numerator in the time signature.
   * @param timeSigDen the denominator in the time signature.
   */
  public MusicModel(int timeSigNum, int timeSigDen) {
    if (timeSigNum <= 0 || timeSigDen <= 0) {
      throw new IllegalArgumentException("Must be greater than zero.");
    }
    this.sheet = new TreeMap<>(new NoteNameComp());
    this.tsNum = timeSigNum;
    this.tsDen = timeSigDen;
    this.bpm = 60;
  }

  @Override
  public void addNote(NoteName name, Note note) {
    Note newn = new Note(note);
    if (sheet.containsKey(name)) {
      sheet.get(name).add(newn);
    }
    else {
      Comparator<Note> nc = new NoteComp();
      Set<Note> noteList = new TreeSet<>(nc);
      noteList.add(newn);
      sheet.put(name, noteList);
      normalize();
    }
  }

  @Override
  public void deleteNote(NoteName name, Note note) {
    if (sheet.containsKey(name)) {
      Set<Note> notes = sheet.get(name);
      if (notes.contains(note)) {
        notes.remove(note);
        normalize();
        if (sheet.size() == 1) {
          if (notes.isEmpty()) {
            sheet.remove(name);
          }
        }
      }
      else {
        throw new IllegalArgumentException("Can't delete. Note not in "
            + name.printName());
      }
    }
    else {
      throw new IllegalArgumentException("Can't delete. " + name.printName()
          + " not in music.");
    }
  }

  @Override
  public void changeNote(NoteName name, Note curr, int newStart, int newDur) {
    if (sheet.containsKey(name)) {
      Set<Note> notes = sheet.get(name);
      if (notes.contains(curr)) {
        for (Note n : notes) {
          Comparator<Note> c = new NoteComp();
          if (((n.getDuration() + n.getStart() > newStart && n.getStart() < newStart)
              || (newDur  + newStart > n.getStart()) && newStart < n.getStart())
              && (c.compare(curr, n) != 0)) {
            throw new IllegalArgumentException("Can't move onto another note.");
          }
          else if (c.compare(curr, n) == 0) {
            n.change(newStart, newDur);
          }
        }
      }
      else {
        throw new IllegalArgumentException("Can't change. Note not in "
            + name.printName());
      }
    }
    else {
      throw new IllegalArgumentException("Can't change. " + name.printName()
          + " not in music.");
    }
  }

  @Override
  public void combineSheets(MusicModelOps m) {
    m.combineHelp(this);
  }

  @Override
  public void combineHelp(MusicModel m) {
    if (tsNum != m.tsNum || tsDen != m.tsDen) {
      throw new IllegalArgumentException("Must have same time sig.");
    }
    NoteName[] a = sheet.keySet().toArray(new NoteName[sheet.keySet().size()]);
    for (NoteName nn : a) {
      Set<Note> val = sheet.get(nn);
      for (Note n : val) {
        m.addNote(nn, new Note(n));
      }
    }
    normalize();
  }

  @Override
  public void consecSheets(MusicModelOps m) {
    m.consecSheetsHelp(this);
  }

  @Override
  public void consecSheetsHelp(MusicModel m) {
    NoteName[] a = m.sheet.keySet().toArray(new NoteName[m.sheet.keySet().size()]);
    NoteName[] arr = sheet.keySet().toArray(new NoteName[sheet.keySet().size()]);
    int max = m.maxNote();
    for (NoteName nn : arr) {
      Set<Note> val = sheet.get(nn);
      for (Note n : val) {
        Note newn = new Note(n.getStart() + max, n.getDuration(),
            n.getDynamics(), n.getInstrument());
        m.addNote(nn, newn);
      }
    }
    normalize();
  }

  @Override
  public String printSheet() {
    if (sheet.isEmpty()) {
      return "";
    }
    else {
      StringBuilder s = new StringBuilder();
      NoteName[] a = sheet.keySet().toArray(new NoteName[sheet.keySet().size()]);
      int maxNote = maxNote();
      int pad = String.valueOf(maxNote).length();
      if (maxNote == 10) {
        pad = pad - 1;
      }
      for (int i = 0; i < pad; i++) {
        s.append(" ");
      }
      for (NoteName nn : a) {
        s.append(nn.printName());
      }
      s.append("\n");

      for (int i = 0; i < maxNote; i++) {
        s.append(String.format("%" + pad + "d", i));
        for (NoteName nn : a) {
          Set<Note> vals = sheet.get(nn);
          if (vals.isEmpty()) {
            s.append("     ");
          }
          else {
            boolean printed = false;
            for (Note n : vals) {
              if (n.getStart() <= i && (i < n.getStart() + n.getDuration())) {
                s.append(n.drawNote(i));
                printed = true;
                break;
              }
            }
            if (!printed) {
              s.append("     ");
            }
          }
        }
        s.append("\n");
      }
      return s.toString();
    }
  }

  @Override
  //getSheet() method added so the controller can pass data to the view
  public Map<NoteName, Set<Note>> getSheet() {
    Map<NoteName, Set<Note>> s = new TreeMap<>(new NoteNameComp());
    s.putAll(sheet);
    return s;
  }

  @Override
  //maxNote() method added so the controller can pass data to the view, changed in
  //assignment 7 to not take in an array of NoteNames.
  public int maxNote() {
    NoteName[] a = sheet.keySet().toArray(new NoteName[sheet.keySet().size()]);
    int maxNote = 0;
    for (NoteName nn : a) {
      Set<Note> v = sheet.get(nn);
      if (v != null) {
        for (Note n : v) {
          if (n.getStart() + n.getDuration() > maxNote) {
            maxNote = n.getStart() + n.getDuration();
          }
        }
      }
    }
    return maxNote;
  }

  @Override
  //measureLength() method added so the controller can pass data to the view
  public int measureLength() {
    return tsNum;
  }

  @Override
  //getTempo() method added so the controller can pass data to the view
  public int getTempo() {
    return bpm;
  }

  /**
   * Normalizes the tree so that if there are any gaps between notes being printed,
   * it will fill in the middle notes. So a tree of A C -> A A# B C.
   */
  private void normalize() {
    Comparator<NoteName> c = new NoteNameComp();
    Comparator<Note> nc = new NoteComp();
    NoteName[] arr = sheet.keySet().toArray(new NoteName[sheet.keySet().size()]);
    if (arr.length > 1) {
      if (sheet.get(arr[0]).isEmpty()) {
        sheet.remove(arr[0]);
        normalize();
      }
      else if (sheet.get(arr[arr.length - 1]).isEmpty()) {
        sheet.remove(arr[arr.length - 1]);
        normalize();
      }
      else {
        if (c.compare(arr[0], arr[1]) != -1) {
          sheet.put(arr[1].prevNote(), new TreeSet<>(nc));
          normalize();
        } else if (c.compare(arr[arr.length - 1], arr[arr.length - 2]) != 1) {
          sheet.put(arr[arr.length - 2].nextNote(), new TreeSet<>(nc));
          normalize();
        }
      }
    }
  }

  /**
   * Builder helps create a music model from data that is read in from a file.
   */
  public static final class Builder implements CompositionBuilder<MusicModelOps> {
    MusicModel mm;

    /**
     * Constructor for a Builder.
     *
     * @param model the music model that is being built.
     */
    public Builder(MusicModel model) {
      this.mm = model;
    }

    @Override
    public MusicModelOps build() {
      return mm;
    }

    @Override
    public CompositionBuilder<MusicModelOps> setTempo(int tempo) {
      mm.bpm = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<MusicModelOps> addNote(int start, int end, int instrument, int pitch,
        int volume) {
      try {
        mm.addNote(new NoteName(pitch), new Note(start, end - start, volume,
            instrument));
      }
      catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
      return this;
    }

  }
}
