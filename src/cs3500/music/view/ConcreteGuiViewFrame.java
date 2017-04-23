package cs3500.music.view;

import cs3500.music.model.NoteName;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    //throw new IllegalArgumentException();
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    //throw new IllegalArgumentException();
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    //throw new IllegalArgumentException();
  }

  @Override
  public int getBeat() {
    return 0;
  }

  @Override
  public void toggleMusic() {
    //throw new IllegalArgumentException();
  }

  @Override
  public int getMargin() {
    return 0;
  }

  @Override
  public int getWhiteLength() {
    return 0;
  }

  @Override
  public int getBlackLength() {
    return 0;
  }

  @Override
  public int getScoreHeight() {
    return 0;
  }

  @Override
  public int getBlackWidth() {
    return 0;
  }

  @Override
  public int getOffset() {
    return 0;
  }

  @Override
  public int getWhiteWidth() {
    return 0;
  }

  @Override
  public void scrollWithMusic() {
    //throw new IllegalArgumentException();
  }

  @Override
  public void togglePractice() {
    //no practice in concrete
  }

  @Override
  public boolean getPractice() {
    return false;
  }

  @Override
  public Set<NoteName> getCurrentNotes() {
    return new HashSet<>();
  }
}
