package cs3500.music.model;


/**
 * Is the actual name for each note. Has a pitch and an octave.
 * Pitch an be A, A#, B, C, C#, D, D#, E, F, F#, G, G#. Octave can be
 * anything greater than 0.
 */
public class NoteName implements NoteNameOps {

  private Pitch pitch;
  private int octave;

  /**
   * Constructor for the note.
   *
   * @param pitch the pitch of the note.
   * @param octave the octave the note is in
   */
  public NoteName(Pitch pitch, int octave) {
    this.pitch = pitch;
    if (octave < 0) {
      throw new IllegalArgumentException("Octave can't be neg.");
    } else {
      this.octave = octave;
    }
  }

  /**
   * Second constructor for a NoteName. Creates a pitch and octave based on
   * the given note value.
   *
   * @param noteVal the value of the note.
   */
  public NoteName(int noteVal) {
    int p = noteVal % 12;
    this.octave = (noteVal / 12) - 1;
    switch (p) {
      case 0:
        this.pitch = Pitch.C;
        break;
      case 1:
        this.pitch = Pitch.CS;
        break;
      case 2:
        this.pitch = Pitch.D;
        break;
      case 3:
        this.pitch = Pitch.DS;
        break;
      case 4:
        this.pitch = Pitch.E;
        break;
      case 5:
        this.pitch = Pitch.F;
        break;
      case 6:
        this.pitch = Pitch.FS;
        break;
      case 7:
        this.pitch = Pitch.G;
        break;
      case 8:
        this.pitch = Pitch.GS;
        break;
      case 9:
        this.pitch = Pitch.A;
        break;
      case 10:
        this.pitch = Pitch.AS;
        break;
      case 11:
        this.pitch = Pitch.B;
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public int getPitch() {
    return pitch.getVal();
  }

  @Override
  public int getOct() {
    return octave;
  }

  @Override
  public int getValue() {
    return (octave + 1) * 12 + pitch.getVal();
  }

  @Override
  public String printName() {
    StringBuilder s = new StringBuilder();
    s.append(pitch.printPitch());
    s.append(octave);
    String str = s.toString();
    return center(str);
  }

  @Override
  public NoteName nextNote() {
    if (pitch.getVal() == 11) {
      return new NoteName(pitch.nextPitch(), octave + 1);
    }
    return new NoteName(pitch.nextPitch(), octave);
  }

  @Override
  public NoteName prevNote() {
    if (pitch.getVal() == 0) {
      return new NoteName(pitch.prevPitch(), octave - 1);
    }
    return new NoteName(pitch.prevPitch(), octave);
  }

  /**
   * Centers the note's name. The character width is 5, if s is even then
   * an extra space is added in the beginning, eg __A2_ where the __ are spaces.
   *
   * @param s the string that is being centered.
   * @return A string of width 5 with s centered in it.
   */
  private String center(String s) {
    double pad = 5 - s.length();
    int padStart = (int) (s.length() + Math.ceil(pad / 2.0));

    s = String.format("%" + padStart + "s", s);
    s = String.format("%-" + 5 + "s", s);
    return s;
  }
}
