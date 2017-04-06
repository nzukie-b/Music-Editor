package cs3500.music.view;

import cs3500.music.model.NoteName;
import cs3500.music.model.NoteNameComp;
import cs3500.music.model.Pitch;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Set;
import javax.swing.JPanel;


/**
 * Represents the view of the piano in the gui.
 */
public class PianoPanel extends JPanel {
  private ModelData data;
  private int currBeat;

  /**
   * Constructor for PianoPanel.
   */
  public PianoPanel(ModelData data) {
    super();
    this.currBeat = 0;
    this.data = data;
    this.setBackground(Color.white);
    this.setPreferredSize(new Dimension(1800, 300));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    int offset = 200;
    Graphics2D gPen = (Graphics2D) g;
    gPen.setColor(Color.BLACK);
    gPen.fillRect(offset, 1, 1400, 300);
    Set<NoteName> notes = data.getNotesAtBeat(currBeat);
    NoteNameComp nnComp = new NoteNameComp();

    for (int i = 0; i < 12; i++) { // for 12 different pitches, i is the ordinal of enum Pitch
      for (int j = 0; j < 10; j++) { // each pitch has 10 different octaves

        // The flat pitch should be white keys, the sharp pitch should be white keys
        if (i == 0) { //for pitch C
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.C, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 140 * j, 1, 19, 300);
        } else if (i == 2) { // for pitch D
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.D, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 20 + 140 * j, 1, 19, 300);
        } else if (i == 4) { //for pitch E
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.E, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 40 + 140 * j, 1, 19, 300);
        } else if (i == 5) { //for pitch F
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.F, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 60 + 140 * j, 1, 19, 300);
        } else if (i == 7) { //for pitch G
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.G, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 80 + 140 * j, 1, 19, 300);
        } else if (i == 9) { // for pitch A
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.A, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 100 + 140 * j, 1, 19, 300);
        } else if (i == 11) { // for pitch B
          gPen.setColor(Color.WHITE);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.B, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 120 + 140 * j, 1, 19, 300);
        }
      }
    }

    for (int i = 1; i < 11; i++) {
      for (int j = 0; j < 10; j++) {
        if (i == 1) {  //for pitch CS
          gPen.setColor(Color.black);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.CS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 12 + 140 * j, 1, 10, 180);
        }

        else if (i == 3) { //for pitch DS
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.DS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(200 + 32 + 140 * j, 1, 10, 180);
        }
        else if (i == 6) { //for pitch FS
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.FS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 72 + 140 * j, 1, 10, 180);
        }
        else if (i == 8) { //for pitch GS
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.GS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 92 + 140 * j, 1, 10, 180);
        }
        else if (i == 10) { //for pitch AS
          gPen.setColor(Color.BLACK);
          for (NoteName n : notes) {
            if (nnComp.compare(n, new NoteName(Pitch.AS, j)) == 0) {
              gPen.setColor(Color.yellow);
            }
          }
          gPen.fillRect(offset + 112 + 140 * j, 1, 10, 180);
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
}
