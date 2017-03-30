package cs3500.music.model;

import java.util.Comparator;

/**
 * A comparator for the NoteNames. Uses the pitch and octave to determine which
 * is greater.
 */
public class NoteNameComp implements Comparator<NoteName> {

  /**
   * Constructor for a NoteNameComp.
   */
  public NoteNameComp() {
    //
  }

  /**
   * Compares the two note names. Returns a positive if o2 > o2, a negative if
   * o1 < o2, and 0 if o1 == o2.
   * @param o1 the first NoteName being compared.
   * @param o2 the second NoteName being compared.
   * @return An int showing which is greater or if they're equal.
   */
  @Override
  public int compare(NoteName o1, NoteName o2) {
    if (o1.getOct() != o2.getOct()) {
      if (Math.abs(o1.getPitch() - o2.getPitch()) == 11) {
        return (o1.getOct() - o2.getOct());
      }
      else {
        return ((o1.getOct() * 12) + o1.getPitch()) - ((o2.getOct() * 12) + o2.getPitch());
      }
    }

    return o1.getPitch() - o2.getPitch();
  }
}