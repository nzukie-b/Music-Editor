package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Represents a class for keyboard listener.
 */
public class KeyboardListener implements KeyListener {
  private Map<Integer, Runnable> keyPressedMap;

  /**
   * The constructor for the KeyboardListener.
   */
  public KeyboardListener() {
    //empty default constructor
  }

  /**
   * Sets the map for the keys that have an action associated with them.
   *
   * @param map the map keyPressedMap is being set to.
   */
  public void setKeyPressedMap(Map<Integer, Runnable> map) {
    keyPressedMap = map;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //do nothing
  }

}
