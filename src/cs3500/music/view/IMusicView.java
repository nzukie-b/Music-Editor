package cs3500.music.view;

import java.awt.event.KeyListener;

/**
 * The represents all of the operations for the music view.
 */
public interface IMusicView {

  /**
   * Updates the data in the view so that it will reflect any changes made.
   *
   * @param data The new data to be shown.
   */
  void updateView(ModelData data);

  /**
   * Sets the current beat of the view.
   *
   * @param beat the beat the view should highlight.
   */
  void setBeat(int beat);

  /**
   * Sets the KeyListener in the view so that it can detece key events.
   *
   * @param kl the KeyListener being added.
   */
  void setKeyListener(KeyListener kl);

  /**
   * Get the specific beat shown in the view.
   *
   * @return get the beat the view is currently highlighting.
   */
  int getBeat();
}
