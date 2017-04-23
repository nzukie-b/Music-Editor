package cs3500.music.view;

import cs3500.music.model.NoteName;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Set;

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
  void setKeyListener(KeyListener kl) throws IllegalArgumentException;

  void setMouseListener(MouseListener ml) throws IllegalArgumentException;

  /**
   * Get the specific beat shown in the view.
   *
   * @return get the beat the view is currently highlighting.
   */
  int getBeat();

  /**
   * Scrolls the gui view to be in time with the Midi View in a combination view.
   * Added in Assignment 7.
   */
  void scrollWithMusic();

  /**
   * Toggles the music so that it will go between being paused and playing when a p
   * is pressed.
   */
  void toggleMusic();
  /**
   * Gets the margin of the components of the view. Added in Assignment 8 to Adapt the other
   * view and clean up the code a bit.
   *
   * @return The margins of this view.
   */
  int getMargin();

  /**
   * Gets the length of a white piano key. Used for determining whether a key has been clicked.
   * Added in Assignment 8 to adapt the provider's view and clean up the code.
   *
   * @return The length of a white piano key.
   */
  int getWhiteLength();


  /**
   * Gets the length of a black piano key. Used for determining whether a key has been clicked.
   * Added in Assignment 8 to adapt the provider's view and clean up the code.
   *
   * @return The length of a black piano key.
   */
  int getBlackLength();

  /**
   * Gets the heignt of the score. Used for helping determine mouse position.
   * Added in Assignment 8 to adapt the provider's view and clean up the code.
   *
   * @return The height of the score area.
   */
  int getScoreHeight();

  /**
   * Gets the width of the black keys in the views. Added in Assignment 8 to adapt the provider's
   * view and clean up the code.
   *
   * @return The width of each black key in pixels.
   */
  int getBlackWidth();

  /**
   * Gets the width of the white keys in the views. Added in Assignment 8 to adapt the provider's
   * view and clean up the code.
   *
   * @return The width of each white key in pixels.
   */
  int getWhiteWidth();

  /**
   * Gets the distance from the beginning of the first white key to the beginning of the
   * first black key. Added in Assignment 8 to adapt the provider's view and clean up the code.
   *
   * @return The offset of the black keys on white keys.
   */
  int getOffset();

  void togglePractice();

  boolean getPractice();

  Set<NoteName> getCurrentNotes();

}
