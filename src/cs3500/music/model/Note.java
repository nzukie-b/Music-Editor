package cs3500.music.model;

/**
 * A Note on a sheet of music. Has two fields, its start beat and the duration it
 * plays for.
 */
public class Note implements NoteOps {
  private int start;
  private int duration;
  private int dynamics;
  private int instrument;

  /**
   * Constructor for a Note.
   *
   * @param start the starting beat for the note.
   * @param duration How long the note is sustained.
   */
  public Note(int start, int duration, int dynamics, int instrument) {
    if (start < 0 || duration < 0 || dynamics < 0) {
      throw new IllegalArgumentException("Can't be neg.");
    }
    this.start = start;
    this.duration = duration;
    this.dynamics = dynamics;
    this.instrument = instrument;
  }

  /**
   * Copy constructor for this note.
   *
   * @param n the note being copied into this.
   */
  public Note(Note n) {
    this.start = n.getStart();
    this.duration = n.getDuration();
    this.dynamics = n.getDynamics();
    this.instrument = n.getInstrument();
  }

  @Override
  public int getStart() {
    return start;
  }

  @Override
  public int getDuration() {
    return duration;
  }

  @Override
  public int getDynamics() {
    return Math.min(dynamics, 127);
  }

  @Override
  public int getInstrument() {
    return instrument;
  }

  @Override
  public String drawNote(int beat) {
    if (beat == start) {
      return "  X  ";
    }
    else if ((beat > start) && beat < (start + duration)) {
      return "  |  ";
    }
    return "";
  }

  @Override
  public void change(int newStart, int newDur) {
    start = newStart;
    duration = newDur;
  }

  @Override
  public boolean isPlaying(int beat) {
    return ((beat >= start) && beat < (start + duration));
  }
}
