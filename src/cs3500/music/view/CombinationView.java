package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

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
    if (paused) {
      currBeat = beat;
      gui.setBeat(beat);
      midi.setBeat(gui.getBeat());
    }
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
    return currBeat;
  }

  @Override
  public void toggleMusic() {
    paused = !paused;
    gui.toggleMusic();
    midi.toggleMusic();
  }

  @Override
  public void scrollWithMusic() {
    if (!paused) {
      gui.setBeat(gui.getBeat() + 1);
      currBeat++;
    }
  }
}
