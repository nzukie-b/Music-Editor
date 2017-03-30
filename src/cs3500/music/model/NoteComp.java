package cs3500.music.model;

import java.util.Comparator;

/**
 * A comparator for notes. Uses the start to determine which one is greater.
 */
public class NoteComp implements Comparator<Note> {

  /**
   * Constructor for the comparator.
   */
  public NoteComp() {
    //
  }

  /**
   * Compares the two notes using their starts. Returns a positive if n1 > n2,
   * a negative if n1 < n2, and 0 if n1 == n2.
   *
   * @param n1 the first note being compared
   * @param n2 the second note being compared
   * @return an int that shows if they are equal, n1 > n2, or n1 < n2
   */
  @Override
  public int compare(Note n1, Note n2) {
    return n1.getStart() - n2.getStart();
  }
}
