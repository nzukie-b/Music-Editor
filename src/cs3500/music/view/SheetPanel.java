package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.NoteName;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Set;
import javax.swing.JPanel;

/**
 * The Panel in the GUI to show the notes on a modified staff.
 */
public class SheetPanel extends JPanel {

  private int redLineBeat;
  private ModelData data;
  private int panelWidth;
  private int panelHeight;

  /**
   * Constructor for the SheetPanel.
   * @param data the data that this panel is showing
   */
  public SheetPanel(ModelData data) {
    super();
    this.data = data;
    this.redLineBeat = 0;
    this.panelWidth = 1802;
    this.panelHeight = 500;
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    this.setFocusable(true);
  }

  /**
   * Updates the view to reflect the new data given and stores it in the sheet.
   *
   * @param data the new data to be drawn.
   */
  public void updateSheet(ModelData data) {
    this.setPreferredSize(new Dimension(data.getMaxBeat() * 40, 500));
    this.revalidate();
    this.data = data;
    repaint();
  }

  /**
   * The new beat to move the red line to in the GUI.
   *
   * @param beat the new beat.
   */
  public void updateBeat(int beat) {
    int lastBeat = data.getMaxBeat();
    while (lastBeat % data.getMeasureLength() != 0) {
      lastBeat++;
    }
    if ((beat >= 0) && (beat <= lastBeat)) {
      this.redLineBeat = beat;
    }
    repaint();
  }

  /**
   * Gets the beat the red line is currently on.
   *
   * @return the redLineBeat
   */
  public int getBeat() {
    return redLineBeat;
  }

  @Override
  public void paintComponent(Graphics gr) {
    super.paintComponent(gr);
    this.setBackground(Color.white);
    Graphics2D g = (Graphics2D) gr;
    int lastBeat = data.getMaxBeat();
    int pitches = data.getSheetData().size();
    int measure = data.getMeasureLength();
    while (lastBeat % measure != 0) {
      lastBeat++;
    }
    int noteHeight = 15;
    int noteWidth = 40;
    int widthBuffer = noteWidth * 2;
    int heightBuffer = noteHeight * 2;
    this.panelWidth = Math.max(lastBeat, 40) * noteWidth + widthBuffer + 2;
    this.panelHeight = pitches * noteHeight + heightBuffer + 2;
    NoteName[] a = data.getSheetData().keySet()
        .toArray(new NoteName[data.getSheetData().keySet().size()]);

    this.setPreferredSize(new Dimension(panelWidth, panelHeight));

    //draws the notes onto the sheet
    for (int i = 0; i < pitches; i++) {
      for (int j = 0; j < lastBeat; j++) {
        Set<Note> notes = data.getSheetData().get(a[i]);
        for (Note n : notes) {
          if (n.drawNote(j).contains("X")) {
            g.setColor(Color.blue);
            g.fillRect(j * noteWidth + widthBuffer, panelHeight - (i + 1) * noteHeight,
                noteWidth, noteHeight);
          }
          if (n.drawNote(j).contains("|")) {
            g.setColor(Color.green);
            g.fillRect(j * noteWidth + widthBuffer, panelHeight - (i + 1) * noteHeight,
                noteWidth, noteHeight);
          }
        }
      }
    }

    g.setColor(Color.black);
    g.setStroke(new BasicStroke(3));

    //draw horizontal lines
    for (int i = heightBuffer; i <= panelHeight; i += noteHeight) {
      g.drawLine(widthBuffer, i, panelWidth, i);
    }

    //draw vertical lines
    for (int i = widthBuffer; i <= panelWidth; i += noteWidth * measure) {
      g.drawLine(i, heightBuffer, i, panelHeight);
    }

    //draw the note names
    for (int i = 0; i < pitches; i++) {
      String str = a[i].printName();
      g.drawString(str, widthBuffer / 2, panelHeight - i * noteHeight - noteHeight / 2);
    }

    //draw the beats above each measure
    for (int i = 0; i < lastBeat; i += measure) {
      g.drawString(Integer.toString(i), i * noteWidth + widthBuffer, widthBuffer / 3);
    }

    //draws the red line showing the current beat
    g.setColor(Color.red);
    g.drawLine(redLineBeat * noteWidth + widthBuffer, heightBuffer,
        redLineBeat * noteWidth + widthBuffer, panelHeight);

  }
}
