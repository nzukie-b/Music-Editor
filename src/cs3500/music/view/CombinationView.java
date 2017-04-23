package cs3500.music.view;

import cs3500.music.model.NoteName;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Set;

/**
 * Class that shows the gui while playing midi.
 */
public class CombinationView implements IMusicView {
  private MidiViewImpl midi;
  private GuiViewFrame gui;
  private int currBeat;
  private boolean paused;

  /**
   * Constructor for the Combination view.
   *
   * @param midi the midi view to be used.
   * @param gui the gui view to be used.
   */
  public CombinationView(MidiViewImpl midi, GuiViewFrame gui) {
    this.midi = midi;
    this.gui = gui;
    this.currBeat = 0;
    this.paused = false;
  }

  @Override
  public void updateView(ModelData data) {
    gui.updateView(data);
    if (!paused) {
      midi.updateView(data);
    }
  }

  @Override
  public void setBeat(int beat) {
    gui.setBeat(beat);
    midi.setBeat(beat);
    currBeat = gui.getBeat();
  }

  @Override
  public void setKeyListener(KeyListener kl) {
    gui.setKeyListener(kl);
  }

  @Override
  public void setMouseListener(MouseListener ml) {
    gui.setMouseListener(ml);
  }

  @Override
  public int getBeat() {
    return gui.getBeat();
  }

  @Override
  public void toggleMusic() {
    paused = !paused;
    gui.toggleMusic();
    midi.toggleMusic();
  }

  @Override
  public int getMargin() {
    return gui.getMargin();
  }

  @Override
  public int getWhiteLength() {
    return gui.getWhiteLength();
  }

  @Override
  public int getBlackLength() {
    return gui.getBlackLength();
  }

  @Override
  public int getScoreHeight() {
    return 0;
  }

  @Override
  public int getBlackWidth() {
    return gui.getBlackWidth();
  }

  @Override
  public int getWhiteWidth() {
    return gui.getWhiteWidth();
  }

  @Override
  public int getOffset() {
    return gui.getOffset();
  }

  @Override
  public void scrollWithMusic() {
    if (!paused) {
      gui.setBeat(gui.getBeat() + 1);
      currBeat = gui.getBeat();
    }
  }

  @Override
  public void togglePractice() {
    gui.togglePractice();
  }

  @Override
  public boolean getPractice() {
    return gui.getPractice();
  }

  @Override
  public Set<NoteName> getCurrentNotes() {
    return gui.getCurrentNotes();
  }
}
