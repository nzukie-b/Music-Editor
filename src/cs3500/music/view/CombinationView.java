package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Class that shows the gui while playing midi.
 */
public class CombinationView implements IMusicView {
  MidiViewImpl midi;
  GuiViewFrame gui;
  int currBeat;

  CombinationView(MidiViewImpl midi, GuiViewFrame gui) {
    this.midi = midi;
    this.gui = gui;
    this.currBeat = 0;
    syncBeats();
  }

  @Override
  public void updateView(ModelData data) {
    gui.updateView(data);
    midi.updateView(data);
    syncBeats();
  }

  @Override
  public void setBeat(int beat) {
    midi.setBeat(beat);
    syncBeats();
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
    gui.toggleMusic();
    midi.toggleMusic();
    syncBeats();
  }

  private void syncBeats() {
    currBeat = midi.getBeat();
    gui.setBeat(currBeat);
  }
}
