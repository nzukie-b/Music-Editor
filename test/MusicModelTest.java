import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelOps;
import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import cs3500.music.model.Pitch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by emily on 3/1/17.
 */
public class MusicModelTest {

  MusicModelOps mm = new MusicModel(4, 4);
  NoteName a2 = new NoteName(Pitch.A, 2);
  NoteName cs2 = new NoteName(Pitch.CS, 3);
  NoteName g1 = new NoteName(Pitch.G, 2);
  NoteName f10 = new NoteName(Pitch.F, 11);
  NoteName b10 = new NoteName(Pitch.B, 10);
  NoteName b2 = new NoteName(Pitch.B, 2);
  NoteName d2 = new NoteName(Pitch.D, 3);
  NoteName fs1 = new NoteName(Pitch.FS, 2);
  NoteName fs10 = new NoteName(Pitch.FS, 11);
  NoteName gs3 = new NoteName(Pitch.GS, 2);

  Note n1 = new Note(3, 4, 80, 4);
  Note n2 = new Note(2, 8, 80, 3);
  Note n3 = new Note(8, 2, 66, 5);

  @Test
  //does an empty sheet print nothing
  public void mm0() {
    assertEquals("", mm.printSheet());
  }

  @Test
  //add note, tests that it will print correctly
  public void mm1() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
  }

  @Test
  //add two notes of the same octave, tests sheet adds between notes
  public void mm2() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
  }

  @Test
  //add two notes of a dif octave, tests sheet adds between notes
  public void mm3() {
    mm.addNote(a2, n1);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |            \n"
            + "9  |            \n",
        mm.printSheet());
  }

  @Test
  //add two notes, octave > 9, test sheet adds between notes + correctly spaces names
  public void mm4() {
    mm.addNote(b10, n1);
    mm.addNote(f10, n3);
    assertEquals("  B10  C11  C#11 D11  D#11 E11  F11 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2                                   \n"
            + "3  X                                \n"
            + "4  |                                \n"
            + "5  |                                \n"
            + "6  |                                \n"
            + "7                                   \n"
            + "8                                X  \n"
            + "9                                |  \n",
        mm.printSheet());
  }

  @Test
  //adds two notes, sheet adds between notes, deletes one note, sheet gets rid of extra
  public void mm5() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("  C#3 \n"
            + "0     \n"
            + "1     \n"
            + "2  X  \n"
            + "3  |  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n"
            + "7  |  \n"
            + "8  |  \n"
            + "9  |  \n",
        mm.printSheet());
  }

  @Test
  //add three notes, prints correctly, delete middle note, prints correctly
  public void mm6() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |                             |  \n"
            + "4  |                             |  \n"
            + "5  |                             |  \n"
            + "6  |                             |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
  }

  @Test
  //add three notes, prints correctly, delete end note, prints correctly
  public void mm7() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
    mm.deleteNote(cs2, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |            \n"
            + "9  |            \n",
        mm.printSheet());
  }

  @Test
  //add note of 2 dif lengths, print correctly
  public void mm8() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n"
            + "7     \n"
            + "8  X  \n"
            + "9  |  \n",
        mm.printSheet());
  }

  @Test
  //add note of 2 dif lengths, print correctly, delete one, print correctly
  public void mm9() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n"
            + "7     \n"
            + "8  X  \n"
            + "9  |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3     \n"
            + "4     \n"
            + "5     \n"
            + "6     \n"
            + "7     \n"
            + "8  X  \n"
            + "9  |  \n",
        mm.printSheet());
  }

  @Test(expected = IllegalArgumentException.class)
  //add two notes that overlap, throws exception
  public void mm10() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n2);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
  }

  @Test(expected = IllegalArgumentException.class)
  //add two notes of same duration and start, throws exception
  public void mm11() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
  }

  @Test
  //add 2 notes of dif lengths, and another note in front, prints correctly
  public void mm12() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |         X  \n"
            + "9  |         |  \n",
        mm.printSheet());
  }

  @Test
  //add 2 notes of dif lengths, and another note after, prints correctly
  public void mm13() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8  X                   |  \n"
            + "9  |                   |  \n",
        mm.printSheet());
  }

  @Test
  //add 2 notes of dif lengths, and another note in front, prints correctly
  //deletes one of them in same pitch, prints correctly
  public void mm14() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |         X  \n"
            + "9  |         |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n3);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |            \n"
            + "9  |            \n",
        mm.printSheet());
  }

  @Test
  //add 2 notes of dif lengths, and another note after, prints correctly
  //deletes one of them, prints correctly
  public void mm15() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8  X                   |  \n"
            + "9  |                   |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3                      |  \n"
            + "4                      |  \n"
            + "5                      |  \n"
            + "6                      |  \n"
            + "7                      |  \n"
            + "8  X                   |  \n"
            + "9  |                   |  \n",
        mm.printSheet());
  }

  @Test(expected = IllegalArgumentException.class)
  //tries to delete a node that's not in the sheet
  public void mm16() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.deleteNote(g1, n1);
  }

  @Test(expected = IllegalArgumentException.class)
  //tries to delete a node of equal pitch, but not same start/dur
  public void mm17() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n2);
  }

  @Test
  //adds three notes, deletes lowest
  public void mm18() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
    mm.deleteNote(g1, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
  }

  @Test
  //adds three notes, deletes hightes,
  public void mm19() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
    mm.deleteNote(cs2, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |            \n"
            + "9  |            \n",
        mm.printSheet());
  }

  @Test
  //adds three notes, deletes middle
  public void mm20() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |                             |  \n"
            + "4  |                             |  \n"
            + "5  |                             |  \n"
            + "6  |                             |  \n"
            + "7  |                             |  \n"
            + "8  |                             |  \n"
            + "9  |                             |  \n",
        mm.printSheet());
  }

  @Test(expected = IllegalArgumentException.class)
  //change a note not in the sheet
  public void mm21() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(cs2, n1, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  //change a note of equal pitch, but not same start/dur
  public void mm22() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n2, 2, 2);
  }

  @Test
  //changes a single note's start earlier
  public void mm23() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 1, 4);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1  X  \n"
            + "2  |  \n"
            + "3  |  \n"
            + "4  |  \n",
        mm.printSheet());
  }

  @Test
  //changes a single note's start later
  public void mm24() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 5, 4);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3     \n"
            + "4     \n"
            + "5  X  \n"
            + "6  |  \n"
            + "7  |  \n"
            + "8  |  \n",
        mm.printSheet());
  }

  @Test
  //changes a single note's duration longer
  public void mm25() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 3, 8);
    assertEquals("    A2 \n"
            + " 0     \n"
            + " 1     \n"
            + " 2     \n"
            + " 3  X  \n"
            + " 4  |  \n"
            + " 5  |  \n"
            + " 6  |  \n"
            + " 7  |  \n"
            + " 8  |  \n"
            + " 9  |  \n"
            + "10  |  \n",
        mm.printSheet());
  }

  @Test
  //changes a single note's duration shorter
  public void mm26() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 3, 2);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n",
        mm.printSheet());
  }

  @Test
  //change both a note's duration and its start
  public void mm27() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 4, 2);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3     \n"
            + "4  X  \n"
            + "5  |  \n",
        mm.printSheet());
  }

  @Test
  //change a note in a sheet with two notes of the same pitch
  public void mm28() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n"
            + "7     \n"
            + "8  X  \n"
            + "9  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n3, 9, 4);
    assertEquals("    A2 \n"
            + " 0     \n"
            + " 1     \n"
            + " 2     \n"
            + " 3  X  \n"
            + " 4  |  \n"
            + " 5  |  \n"
            + " 6  |  \n"
            + " 7     \n"
            + " 8     \n"
            + " 9  X  \n"
            + "10  |  \n"
            + "11  |  \n"
            + "12  |  \n",
        mm.printSheet());
  }

  @Test
  //change a note in the sheet with two notes of a different pitch
  public void mm29() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 5, 2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3                      |  \n"
            + "4                      |  \n"
            + "5  X                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
  }

  @Test(expected = IllegalArgumentException.class)
  //move a note onto another note
  public void mm30() {
    mm.addNote(a2, n1);
    mm.addNote(a2, n3);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n"
            + "7     \n"
            + "8  X  \n"
            + "9  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n3, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  //try to add a note, can't because they overlap, move one, add and succeed
  public void mm31() {
    mm.addNote(a2, n2);
    mm.addNote(a2, n1);
    assertEquals("  A2 \n"
            + "0     \n"
            + "1     \n"
            + "2  X  \n"
            + "3  |  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n"
            + "7  |  \n"
            + "8  |  \n"
            + "9  |  \n",
        mm.printSheet());
    mm.changeNote(a2, n2, 8, 4);
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + " 0     \n"
            + " 1     \n"
            + " 2     \n"
            + " 3  X  \n"
            + " 4  |  \n"
            + " 5  |  \n"
            + " 6  |  \n"
            + " 7     \n"
            + " 8  X  \n"
            + " 9  |  \n"
            + "10  |  \n"
            + "11  |  \n",
        mm.printSheet());
  }

  @Test
  //adds a note, prints correctly, deletes it, prints correctly
  public void mm32() {
    mm.addNote(a2, n1);
    assertEquals("   A2 \n"
            + "0     \n"
            + "1     \n"
            + "2     \n"
            + "3  X  \n"
            + "4  |  \n"
            + "5  |  \n"
            + "6  |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("", mm.printSheet());
  }

  @Test
  //combine two sheets, play simultaneously
  public void mm33() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
  }

  @Test
  //middle note deletion after a combining
  public void mm34() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |                             |  \n"
            + "4  |                             |  \n"
            + "5  |                             |  \n"
            + "6  |                             |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
  }
/*
  @Test
  //lower note deletion after combining
  public void mm35() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.deleteNote(g1, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8  X                   |  \n"
            + "9  |                   |  \n",
        mm.printSheet());
  }

  @Test
  //upper note deletion after combining
  public void mm36() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.deleteNote(cs2, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |         X  \n"
            + "9  |         |  \n",
        mm.printSheet());
  }

  @Test
  //change a note after combining
  public void mm37() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 0, 5);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0            X                      \n"
            + "1            |                      \n"
            + "2  X         |                   X  \n"
            + "3  |         |                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |                             |  \n"
            + "6  |                             |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
  }

  @Test
  //add a note after combining
  public void mm38() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.addNote(b2, n1);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X         X         |  \n"
            + "4  |         |         |         |  \n"
            + "5  |         |         |         |  \n"
            + "6  |         |         |         |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
  }

  @Test
  //add a note greater than last in sheet after combining
  public void mm39() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.addNote(d2, n1);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3   D3 \n"
            + "0                                        \n"
            + "1                                        \n"
            + "2  X                             X       \n"
            + "3  |         X                   |    X  \n"
            + "4  |         |                   |    |  \n"
            + "5  |         |                   |    |  \n"
            + "6  |         |                   |    |  \n"
            + "7  |                             |       \n"
            + "8  |         X                   |       \n"
            + "9  |         |                   |       \n",
        mm.printSheet());
  }

  @Test
  //add a note lower than first in sheet after combining
  public void mm40() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.combineSheets(mm2);
    assertEquals("   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                   \n"
            + "1                                   \n"
            + "2  X                             X  \n"
            + "3  |         X                   |  \n"
            + "4  |         |                   |  \n"
            + "5  |         |                   |  \n"
            + "6  |         |                   |  \n"
            + "7  |                             |  \n"
            + "8  |         X                   |  \n"
            + "9  |         |                   |  \n",
        mm.printSheet());
    mm.addNote(fs1, n1);
    assertEquals("  F#2   G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + "0                                        \n"
            + "1                                        \n"
            + "2       X                             X  \n"
            + "3  X    |         X                   |  \n"
            + "4  |    |         |                   |  \n"
            + "5  |    |         |                   |  \n"
            + "6  |    |         |                   |  \n"
            + "7       |                             |  \n"
            + "8       |         X                   |  \n"
            + "9       |         |                   |  \n",
        mm.printSheet());
  }

  @Test(expected = IllegalArgumentException.class)
  //try to combine sheets with overlapping notes
  public void mm41() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(a2, n2);
    mm.combineSheets(mm2);
  }

  @Test(expected = IllegalArgumentException.class)
  //try to combine sheets with same note
  public void mm42() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(a2, n1);
    mm.combineSheets(mm2);
  }

  @Test(expected = IllegalArgumentException.class)
  //have a tsn less than 0
  public void mm43() {
    MusicModelOps mm2 = new MusicModel(-2, 4, 60);
  }

  @Test(expected = IllegalArgumentException.class)
  //have a tsd less than 0
  public void mm44() {
    MusicModelOps mm2 = new MusicModel(4, -2, 60);
  }

  @Test(expected = IllegalArgumentException.class)
  //have a tsn = 0
  public void mm45() {
    MusicModelOps mm2 = new MusicModel(0, 4, 60);
  }

  @Test(expected = IllegalArgumentException.class)
  //have a tsd = 0
  public void mm46() {
    MusicModelOps mm2 = new MusicModel(9, 0, 60);
  }

  @Test(expected = IllegalArgumentException.class)
  //tries to combine and tsnum not equal
  public void mm47() {
    mm.addNote(a2, n1);
    MusicModelOps mm2 = new MusicModel(3, 4, 60);
    mm.combineSheets(mm2);
  }

  @Test(expected = IllegalArgumentException.class)
  //tries to combine and tsden not equal
  public void mm48() {
    mm.addNote(a2, n1);
    MusicModelOps mm2 = new MusicModel(4, 2, 60);
    mm.combineSheets(mm2);
  }

  @Test
  //sheets combine consecutively
  public void mm49() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    assertEquals("   A2  A#2   B2   C3  C#3 \n"
            + "0                         \n"
            + "1                         \n"
            + "2                      X  \n"
            + "3  X                   |  \n"
            + "4  |                   |  \n"
            + "5  |                   |  \n"
            + "6  |                   |  \n"
            + "7                      |  \n"
            + "8                      |  \n"
            + "9                      |  \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
  }

  @Test
  //sheets combine with new note higher
  public void mm50() {
    mm.addNote(a2, n1);
    mm.addNote(g1, n2);
    assertEquals("   G2  G#2   A2 \n"
            + "0               \n"
            + "1               \n"
            + "2  X            \n"
            + "3  |         X  \n"
            + "4  |         |  \n"
            + "5  |         |  \n"
            + "6  |         |  \n"
            + "7  |            \n"
            + "8  |            \n"
            + "9  |            \n",
        mm.printSheet());
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(cs2, n1);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2  X                                \n"
            + " 3  |         X                      \n"
            + " 4  |         |                      \n"
            + " 5  |         |                      \n"
            + " 6  |         |                      \n"
            + " 7  |                                \n"
            + " 8  |                                \n"
            + " 9  |                                \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12                                   \n"
            + "13                                X  \n"
            + "14                                |  \n"
            + "15                                |  \n"
            + "16                                |  \n",
        mm.printSheet());
  }

  @Test
  //sheets combine with new note lower
  public void mm51() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n1);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12                                   \n"
            + "13  X                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n",
        mm.printSheet());
  }

  @Test
  //combine sheets with new note in middle
  public void mm52() {
    mm.addNote(g1, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(a2, n1);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3  X                             |  \n"
            + " 4  |                             |  \n"
            + " 5  |                             |  \n"
            + " 6  |                             |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12                                   \n"
            + "13            X                      \n"
            + "14            |                      \n"
            + "15            |                      \n"
            + "16            |                      \n",
        mm.printSheet());
  }

  @Test
  //combine sheets properly, change a note
  public void mm53() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
    mm.changeNote(a2, n1, 2, 5);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2            X                   X  \n"
            + " 3            |                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
  }

  @Test
  //combine sheets properly, add a note
  public void mm54() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
    mm.addNote(d2, n3);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3   D3 \n"
            + " 0                                        \n"
            + " 1                                        \n"
            + " 2                                X       \n"
            + " 3            X                   |       \n"
            + " 4            |                   |       \n"
            + " 5            |                   |       \n"
            + " 6            |                   |       \n"
            + " 7                                |       \n"
            + " 8                                |    X  \n"
            + " 9                                |    |  \n"
            + "10                                        \n"
            + "11                                        \n"
            + "12  X                                     \n"
            + "13  |                                     \n"
            + "14  |                                     \n"
            + "15  |                                     \n"
            + "16  |                                     \n"
            + "17  |                                     \n"
            + "18  |         X                           \n"
            + "19  |         |                           \n",
        mm.printSheet());
  }

  @Test
  //combine sheets properly, add note in middle
  public void mm56() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
    mm.addNote(b2, n1);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X         X         |  \n"
            + " 4            |         |         |  \n"
            + " 5            |         |         |  \n"
            + " 6            |         |         |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
  }

  @Test
  //combine sheets properly, delete a note
  public void mm57() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
    mm.deleteNote(cs2, n2);
    assertEquals("    G2  G#2   A2 \n"
            + " 0               \n"
            + " 1               \n"
            + " 2               \n"
            + " 3            X  \n"
            + " 4            |  \n"
            + " 5            |  \n"
            + " 6            |  \n"
            + " 7               \n"
            + " 8               \n"
            + " 9               \n"
            + "10               \n"
            + "11               \n"
            + "12  X            \n"
            + "13  |            \n"
            + "14  |            \n"
            + "15  |            \n"
            + "16  |            \n"
            + "17  |            \n"
            + "18  |         X  \n"
            + "19  |         |  \n",
        mm.printSheet());
  }

  @Test
  //combines sheets properly, delete from middle
  public void mm58() {
    mm.addNote(a2, n1);
    mm.addNote(cs2, n2);
    MusicModelOps mm2 = new MusicModel(4, 4, 60);
    mm2.addNote(g1, n2);
    mm2.addNote(a2, n3);
    mm.consecSheets(mm2);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3            X                   |  \n"
            + " 4            |                   |  \n"
            + " 5            |                   |  \n"
            + " 6            |                   |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
    mm.deleteNote(a2, n1);
    assertEquals("    G2  G#2   A2  A#2   B2   C3  C#3 \n"
            + " 0                                   \n"
            + " 1                                   \n"
            + " 2                                X  \n"
            + " 3                                |  \n"
            + " 4                                |  \n"
            + " 5                                |  \n"
            + " 6                                |  \n"
            + " 7                                |  \n"
            + " 8                                |  \n"
            + " 9                                |  \n"
            + "10                                   \n"
            + "11                                   \n"
            + "12  X                                \n"
            + "13  |                                \n"
            + "14  |                                \n"
            + "15  |                                \n"
            + "16  |                                \n"
            + "17  |                                \n"
            + "18  |         X                      \n"
            + "19  |         |                      \n",
        mm.printSheet());
  }

  @Test
  //tests that printing a single note works correctly on sustain
  public void note1() {
    assertEquals("  |  ", n1.drawNote(4));
  }

  @Test
  //tests that printing a single note works correctly on start
  public void note2() {
    assertEquals("  X  ", n1.drawNote(3));
  }

  @Test
  //tests that printing a single note works correctly when not playing
  public void note3() {
    assertEquals("", n1.drawNote(0));
  }

  @Test
  //get start works correctly
  public void note4() {
    assertEquals(3, n1.getStart());
  }

  @Test
  //get start works correctly
  public void note5() {
    assertEquals(2, n2.getStart());
  }

  @Test
  //get duration works
  public void note6() {
    assertEquals(4, n1.getDuration());
  }

  @Test
  //get duration works
  public void note7() {
    assertEquals(8, n2.getDuration());
  }

  @Test
  //change works
  public void note8() {
    n1.change(5, 4);
    assertEquals(5, n1.getStart());
    assertEquals(4, n1.getDuration());
  }

  @Test
  //change works
  public void note9() {
    n1.change(3, 1);
    assertEquals(3, n1.getStart());
    assertEquals(1, n1.getDuration());
  }

  @Test
  //print note name 2 letters works
  public void nn1() {
    assertEquals("  A2 ", a2.printName());
  }

  @Test
  //print note name 3 letters works
  public void nn2() {
    assertEquals(" F#2 ", fs1.printName());
  }

  @Test
  //print note name 4 letters works
  public void nn3() {
    assertEquals(" F#11", fs10.printName());
  }

  @Test
  //get oct works
  public void nn4() {
    assertEquals(2, a2.getOct());
  }

  @Test
  //get oct works
  public void nn5() {
    assertEquals(11, fs10.getOct());
  }

  @Test
  //get pitch works
  public void nn6() {
    assertEquals(9, a2.getPitch());
  }
  */
}
