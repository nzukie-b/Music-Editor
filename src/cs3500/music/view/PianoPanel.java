package cs3500.music.view;

import cs3500.music.model.NoteName;
import cs3500.music.model.NoteNameComp;
import cs3500.music.model.Pitch;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;


/**
 * Represents the view of the piano in the gui.
 */
public class PianoPanel extends JPanel {
  private ModelData data;
  private int currBeat;
  private boolean practice;

  //Made the dimensions of the piano static in assignment 8 so that they could be easily accessed
  //outside the view.
  private static final int WHITEKEYHEIGHT = 300;
  private static final int WHITEKEYWIDTH = 20;
  private static final int BLACKKEYHEIGHT = 180;
  private static final int BLACKKEYWIDTH = 10;
  private static final int MARGIN = 200;

  /**
   * Constructor for PianoPanel.
   */
  public PianoPanel(ModelData data) {
    super();
    this.currBeat = 0;
    this.data = data;
    this.setBackground(Color.white);
    this.setPreferredSize(new Dimension(1800, WHITEKEYHEIGHT + 50));
    this.practice = false;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D gPen = (Graphics2D) g;
    gPen.setColor(Color.BLACK);
    gPen.fillRect(MARGIN, 1, 1400, WHITEKEYHEIGHT);
    Set<NoteName> notes = data.getNotesAtBeat(currBeat);
    NoteNameComp nnComp = new NoteNameComp();

    for (int i = 0; i < 12; i++) { // for 12 different pitches, i is the ordinal of enum Pitch
      for (int j = 0; j < 10; j++) { // each pitch has 10 different octaves
        int y = 290;
        // The flat pitch should be white keys, the sharp pitch should be black keys
        if (i == 0) { //for pitch C
          gPen.setColor(Color.WHITE);
          int x = MARGIN + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.C, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);

          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("A", x + 5, y);
            gPen.drawString(Integer.toString(j), x + 5, y - 20);
          }
        } else if (i == 2) { // for pitch D
        gPen.setColor(Color.WHITE);
          int x = MARGIN + 20 + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.D, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("S", x + 5, y);
          }
        } else if (i == 4) { //for pitch E
        gPen.setColor(Color.WHITE);
          int x = MARGIN + 40 + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.E, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("D", x + 5, y);
          }
        } else if (i == 5) { //for pitch F
          gPen.setColor(Color.WHITE);
          int x = MARGIN + 60 + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.F, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("F", x + 5, y);
          }
        } else if (i == 7) { //for pitch G
          gPen.setColor(Color.WHITE);
          int x = MARGIN + 80 + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.G, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("G", x + 5, y);
          }
        } else if (i == 9) { // for pitch A
          gPen.setColor(Color.WHITE);
          int x = MARGIN + 100 + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.A, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("H", x + 5, y);
          }
        } else if (i == 11) { // for pitch B
          gPen.setColor(Color.WHITE);
          int x = MARGIN + 120 + 140 * j;
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.B, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 19, WHITEKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.BLACK);
            gPen.drawString("J", x + 5, y);
          }
        }
      }
    }

    for (int i = 1; i < 11; i++) {
      for (int j = 0; j < 10; j++) {
        int y = 170;
        if (i == 1) {  //for pitch CS
          int x = MARGIN + 12 + 140 * j;
          gPen.setColor(Color.black);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.CS, j)) == 0) {
              gPen.setColor(Color.yellow);
              gPen.fillRect(x, 1, 10, BLACKKEYHEIGHT);
            }
          }
          gPen.fillRect(x, 1, 10, BLACKKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.WHITE);
            gPen.drawString("W", x, y);
          }
        }

        else if (i == 3) { //for pitch DS
          int x = 200 + 32 + 140 * j;
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.DS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 10, BLACKKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.WHITE);
            gPen.drawString("E", x + 1, y);
          }
        }
        else if (i == 6) { //for pitch FS
          int x = MARGIN + 72 + 140 * j;
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.FS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 10, BLACKKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.WHITE);
            gPen.drawString("T", x + 1, y);
          }
        }
        else if (i == 8) { //for pitch GS
          int x = MARGIN + 92 + 140 * j;
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.GS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 10, BLACKKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.WHITE);
            gPen.drawString("Y", x + 2, y);
          }
        }
        else if (i == 10) { //for pitch AS
          int x = MARGIN + 112 + 140 * j;
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.AS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(x, 1, 10, BLACKKEYHEIGHT);
          if (practice) {
            gPen.setColor(Color.WHITE);
            gPen.drawString("U", x + 1, y);
          }
        }
      }
    }
  }

  /**
   * Updates the music sheet based on the data given, updates the display to reflect
   * the new data.
   *
   * @param data The new data to display.
   */
  public void updateSheet(ModelData data) {
    this.data = data;
    repaint();
  }

  /**
   * Changes the beat that the notes are being highlighted on and updates the view
   * to reflect that.
   *
   * @param beat the new beat to highlight the notes playing during.
   */
  public void updateBeat(int beat) {
    int lastBeat = data.getMaxBeat();
    while (lastBeat % data.getMeasureLength() != 0) {
      lastBeat++;
    }
    if ((beat >= 0) && (beat <= lastBeat)) {
      this.currBeat = beat;
      repaint();
    }
  }

  public void togglePractice() {
    practice = !practice;
    repaint();
  }

  public boolean getPractice() {
    return practice;
  }

  public Set<NoteName> getCurrentNotes() {
    return data.getNotesAtBeat(currBeat);
  }

  public int getWhiteKeyHeight() {
    return WHITEKEYHEIGHT;
  }

  public int getBlackKeyHeight() {
    return BLACKKEYHEIGHT;
  }

  public int getBlackKeyWidth() {
    return BLACKKEYWIDTH;
  }

  public int getWhiteKeyWidth() {
    return WHITEKEYWIDTH;
  }

  public int getMargin() {
    return MARGIN;
  }

  public int getOffset() {
    return 12;
  }
}
