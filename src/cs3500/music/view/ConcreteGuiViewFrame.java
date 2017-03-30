package cs3500.music.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * The class to represent the console output for the music.
 */
public class ConcreteGuiViewFrame extends JFrame implements IMusicView {
  private final ConcreteGuiViewPanel stringPanel;

  /**
   * Constructor for the ConcreteGuiViewFrame.
   */
  public ConcreteGuiViewFrame() {
    super();
    this.setTitle("Music as String");
    this.setSize(new Dimension(1000, 1000));

    this.stringPanel = new ConcreteGuiViewPanel();
    this.add(stringPanel);
    stringPanel.setBackground(Color.white);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setFocusable(true);
    this.setVisible(true);
  }

  /**
   * Second constructor for the ConcreteGuiViewFrame, is passed a Panel instead
   * of creating one.
   *
   * @param panel the panel to be used by this Frame.
   */
  public ConcreteGuiViewFrame(ConcreteGuiViewPanel panel) {
    this.stringPanel = panel;
  }

  @Override
  public void updateView(ModelData data) {
    stringPanel.updateView(data);
  }

  @Override
  public void setBeat(int beat) {
    //not relevant for this view
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    //not relevant for this view
  }

  @Override
  public int getBeat() {
    return 0;
  }
}
