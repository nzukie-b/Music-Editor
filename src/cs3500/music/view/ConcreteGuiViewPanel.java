package cs3500.music.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A dummy view that simply draws a string.
 */
public class ConcreteGuiViewPanel extends JPanel {

  protected String sht;

  /**
   * The constructor for the ConcreteGuiViewPanel.
   */
  public ConcreteGuiViewPanel() {
    super();
    this.sht = "";
    this.setPreferredSize(new Dimension(1000, 1000));
    this.setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(new Font("monospaced", Font.PLAIN, 12));
    this.setBackground(Color.white);
    this.drawSheet(g, 25, 25);
  }

  /**
   * Helps to paint the components by breaking up the String line by line. Draws
   * the string in the Panel.
   *
   * @param g the Graphics to be used.
   * @param x the upper-left x-coordinate.
   * @param y the upper-left y-coordinate.
   */
  protected void drawSheet(Graphics g, int x, int y) {
    for (String line : sht.split("\n")) {
      g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
  }

  /**
   * Updates the music viewe based on teh music model.
   *
   * @param data The data that is being drawn.
   */
  public void updateView(ModelData data) {
    this.sht = data.getSheetAsString();
  }

  /**
   * Gets the sheet held by this Panel.
   *
   * @return the Panel's sheet stored as a string
   */
  public String getSheet() {
    return sht;
  }

}
